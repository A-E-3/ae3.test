package ru.myx.ae3.base;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A hash table supporting full concurrency of retrievals and adjustable
 * expected concurrency for updates. This class obeys the same functional
 * specification as {@link java.util.Hashtable}, and includes versions of
 * methods corresponding to each method of <tt>Hashtable</tt>. However, even
 * though all operations are thread-safe, retrieval operations do <em>not</em>
 * entail locking, and there is <em>not</em> any support for locking the entire
 * table in a way that prevents all access. This class is fully interoperable
 * with <tt>Hashtable</tt> in programs that rely on its thread safety but not on
 * its synchronization details.
 *
 * <p>
 * Retrieval operations (including <tt>get</tt>) generally do not block, so may
 * overlap with update operations (including <tt>put</tt> and <tt>remove</tt>).
 * Retrievals reflect the results of the most recently <em>completed</em> update
 * operations holding upon their onset. For aggregate operations such as
 * <tt>putAll</tt> and <tt>clear</tt>, concurrent retrievals may reflect
 * insertion or removal of only some entries. Similarly, Iterators and
 * Enumerations return elements reflecting the state of the hash table at some
 * point at or since the creation of the iterator/enumeration. They do
 * <em>not</em> throw {@link ConcurrentModificationException}. However,
 * iterators are designed to be used by only one thread at a time.
 *
 * <p>
 * The allowed concurrency among update operations is guided by the optional
 * <tt>concurrencyLevel</tt> constructor argument (default <tt>16</tt>), which
 * is used as a hint for internal sizing. The table is internally partitioned to
 * try to permit the indicated number of concurrent updates without contention.
 * Because placement in hash tables is essentially random, the actual
 * concurrency will vary. Ideally, you should choose a value to accommodate as
 * many threads as will ever concurrently modify the table. Using a
 * significantly higher value than you need can waste space and time, and a
 * significantly lower value can lead to thread contention. But overestimates
 * and underestimates within an order of magnitude do not usually have much
 * noticeable impact. A value of one is appropriate when it is known that only
 * one thread will modify and all others will only read. Also, resizing this or
 * any other kind of hash table is a relatively slow operation, so, when
 * possible, it is a good idea to provide estimates of expected table sizes in
 * constructors.
 *
 * <p>
 * This class and its views and iterators implement all of the <em>optional</em>
 * methods of the {@link Map} and {@link Iterator} interfaces.
 *
 * <p>
 * Like {@link Hashtable} but unlike {@link HashMap}, this class does
 * <em>not</em> allow <tt>null</tt> to be used as a key or value.
 *
 * <p>
 * This class is a member of the
 * <a href="{@docRoot} /../technotes/guides/collections/index.html"> Java
 * Collections Framework</a>.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class CIIntegerHashIntConcurrentExact implements CacheIntegerImplEx, Serializable {
	/*
	 * The basic strategy is to subdivide the table among Segments, each of
	 * which itself is a concurrently readable hash table.
	 */
	
	/* ---------------- Constants -------------- */
	
	/**
	 * ConcurrentHashMap list entry. Note that this is never exported out as a
	 * user-visible Map.Entry.
	 *
	 * Because the value field is volatile, not final, it is legal wrt the Java
	 * Memory Model for an unsynchronized reader to see null instead of initial
	 * value when read via a data race. Although a reordering leading to this is
	 * not likely to ever actually occur, the Segment.readValueUnderLock method
	 * is used as a backup in case a null (pre-initialized) value is ever seen
	 * in an unsynchronized access method.
	 */
	static final class HashEntry {

		final int key;
		
		final int hash;
		
		volatile CEIntegerWeak value;
		
		final HashEntry next;
		
		HashEntry(final int key, final int hash, final HashEntry next, final CEIntegerWeak value) {
			this.key = key;
			this.hash = hash;
			this.next = next;
			this.value = value;
		}
	}
	
	abstract class HashIterator {

		int nextSegmentIndex;
		
		int nextTableIndex;
		
		HashEntry[] currentTable;
		
		HashEntry nextEntry;
		
		HashEntry lastReturned;
		
		HashIterator() {
			this.nextSegmentIndex = CIIntegerHashIntConcurrentExact.this.segments.length - 1;
			this.nextTableIndex = -1;
			this.advance();
		}
		
		final void advance() {

			if (this.nextEntry != null && (this.nextEntry = this.nextEntry.next) != null) {
				return;
			}
			
			while (this.nextTableIndex >= 0) {
				if ((this.nextEntry = this.currentTable[this.nextTableIndex--]) != null) {
					return;
				}
			}
			
			while (this.nextSegmentIndex >= 0) {
				final Segment seg = CIIntegerHashIntConcurrentExact.this.segments[this.nextSegmentIndex--];
				if (seg.count != 0) {
					this.currentTable = seg.table;
					for (int j = this.currentTable.length - 1; j >= 0; --j) {
						if ((this.nextEntry = this.currentTable[j]) != null) {
							this.nextTableIndex = j - 1;
							return;
						}
					}
				}
			}
		}
		
		public boolean hasMoreElements() {

			return this.hasNext();
		}
		
		public boolean hasNext() {

			return this.nextEntry != null;
		}
		
		HashEntry nextEntry() {

			if (this.nextEntry == null) {
				throw new NoSuchElementException();
			}
			this.lastReturned = this.nextEntry;
			this.advance();
			return this.lastReturned;
		}
		
		public void remove() {

			if (this.lastReturned == null) {
				throw new IllegalStateException();
			}
			CIIntegerHashIntConcurrentExact.this.remove(this.lastReturned.key);
			this.lastReturned = null;
		}
	}
	
	/**
	 * Segments are specialized versions of hash tables. This subclasses from
	 * ReentrantLock opportunistically, just to simplify some locking and avoid
	 * separate construction.
	 */
	static final class Segment extends ReentrantLock {
		/*
		 * Segments maintain a table of entry lists that are ALWAYS kept in a
		 * consistent state, so can be read without locking. Next fields of
		 * nodes are immutable (final). All list additions are performed at the
		 * front of each bin. This makes it easy to check changes, and also fast
		 * to traverse. When nodes would otherwise be changed, new nodes are
		 * created to replace them. This works well for hash tables since the
		 * bin lists tend to be short. (The average length is less than two for
		 * the default load factor threshold.) Read operations can thus proceed
		 * without locking, but rely on selected uses of volatiles to ensure
		 * that completed write operations performed by other threads are
		 * noticed. For most purposes, the "count" field, tracking the number of
		 * elements, serves as that volatile variable ensuring visibility. This
		 * is convenient because this field needs to be read in many read
		 * operations anyway: - All (unsynchronized) read operations must first
		 * read the "count" field, and should not look at table entries if it is
		 * 0. - All (synchronized) write operations should write to the "count"
		 * field after structurally changing any bin. The operations must not
		 * take any action that could even momentarily cause a concurrent read
		 * operation to see inconsistent data. This is made easier by the nature
		 * of the read operations in Map. For example, no operation can reveal
		 * that the table has grown but the threshold has not yet been updated,
		 * so there are no atomicity requirements for this with respect to
		 * reads. As a guide, all critical volatile reads and writes to the
		 * count field are marked in code comments.
		 */
		
		private static final long serialVersionUID = 2249069246763182397L;
		
		static final Segment[] newArray(final int i) {

			return new Segment[i];
		}
		
		/**
		 * The number of elements in this segment's region.
		 */
		transient volatile int count;
		
		/**
		 * Number of updates that alter the size of the table. This is used
		 * during bulk-read methods to make sure they see a consistent snapshot:
		 * If modCounts change during a traversal of segments computing size or
		 * checking containsValue, then we might have an inconsistent view of
		 * state so (usually) must retry.
		 */
		transient int modCount;
		
		/**
		 * The table is rehashed when its size exceeds this threshold. (The
		 * value of this field is always <tt>(int)(capacity *
		 * loadFactor)</tt>.)
		 */
		transient int threshold;
		
		/**
		 * The per-segment table.
		 */
		transient volatile HashEntry[] table;
		
		/**
		 * The load factor for the hash table. Even though this value is same
		 * for all segments, it is replicated to avoid needing links to outer
		 * object.
		 *
		 * @serial
		 */
		final float loadFactor;
		
		Segment(final int initialCapacity, final float lf) {
			this.loadFactor = lf;
			this.setTable(new HashEntry[initialCapacity]);
		}
		
		void clear() {

			if (this.count != 0) {
				this.lock();
				try {
					final HashEntry[] tab = this.table;
					for (int i = 0; i < tab.length; i++) {
						tab[i] = null;
					}
					++this.modCount;
					this.count = 0; // write-volatile
				} finally {
					this.unlock();
				}
			}
		}
		
		boolean containsKey(final int key, final int hash) {

			if (this.count != 0) { // read-volatile
				HashEntry e = this.getFirst(hash);
				while (e != null) {
					if (e.hash == hash && key == e.key) {
						return true;
					}
					e = e.next;
				}
			}
			return false;
		}
		
		boolean containsValue(final Object value) {

			if (this.count != 0) { // read-volatile
				final HashEntry[] tab = this.table;
				final int len = tab.length;
				for (int i = 0; i < len; i++) {
					for (HashEntry e = tab[i]; e != null; e = e.next) {
						CEIntegerWeak v = e.value;
						if (v == null) {
							v = this.readValueUnderLock(e);
						}
						if (value.equals(v)) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
		/* Specialized implementations of map methods */
		
		CEIntegerWeak get(final int key, final int hash) {

			if (this.count != 0) { // read-volatile
				HashEntry e = this.getFirst(hash);
				while (e != null) {
					if (e.hash == hash && e.key == key) {
						final CEIntegerWeak v = e.value;
						if (v != null) {
							return v;
						}
						return this.readValueUnderLock(e); // recheck
					}
					e = e.next;
				}
			}
			return null;
		}
		
		/**
		 * Returns properly casted first entry of bin for given hash.
		 */
		HashEntry getFirst(final int hash) {

			final HashEntry[] tab = this.table;
			return tab[hash & tab.length - 1];
		}
		
		CEIntegerWeak put(final int key, final int hash) {

			this.lock();
			try {
				int c = this.count;
				if (c++ > this.threshold) {
					this.rehash();
				}
				final HashEntry[] tab = this.table;
				final int index = hash & tab.length - 1;
				final HashEntry first = tab[index];
				HashEntry e = first;
				while (e != null && (e.hash != hash || e.key != key)) {
					e = e.next;
				}
				
				if (e != null) {
					return e.value;
				}
				{
					++this.modCount;
					tab[index] = new HashEntry(key, hash, first, new CEIntegerWeak(key));
					this.count = c; // write-volatile
					return tab[index].value;
				}
			} finally {
				this.unlock();
			}
		}
		
		/**
		 * Reads value field of an entry under lock. Called if value field ever
		 * appears to be null. This is possible only if a compiler happens to
		 * reorder a HashEntry initialization with its table assignment, which
		 * is legal under memory model but is not known to ever occur.
		 */
		CEIntegerWeak readValueUnderLock(final HashEntry e) {

			this.lock();
			try {
				return e.value;
			} finally {
				this.unlock();
			}
		}
		
		void rehash() {

			final HashEntry[] oldTable = this.table;
			final int oldCapacity = oldTable.length;
			if (oldCapacity >= CIIntegerHashIntConcurrentExact.MAXIMUM_CAPACITY) {
				return;
			}
			
			/*
			 * Reclassify nodes in each list to new Map. Because we are using
			 * power-of-two expansion, the elements from each bin must either
			 * stay at same index, or move with a power of two offset. We
			 * eliminate unnecessary node creation by catching cases where old
			 * nodes can be reused because their next fields won't change.
			 * Statistically, at the default threshold, only about one-sixth of
			 * them need cloning when a table doubles. The nodes they replace
			 * will be garbage collectable as soon as they are no longer
			 * referenced by any reader thread that may be in the midst of
			 * traversing table right now.
			 */
			
			final HashEntry[] newTable = new HashEntry[oldCapacity << 1];
			this.threshold = (int) (newTable.length * this.loadFactor);
			final int sizeMask = newTable.length - 1;
			for (int i = 0; i < oldCapacity; i++) {
				// We need to guarantee that any existing reads of old Map can
				// proceed. So we cannot yet null out each bin.
				final HashEntry e = oldTable[i];
				
				if (e != null) {
					final HashEntry next = e.next;
					final int idx = e.hash & sizeMask;
					
					// Single node on list
					if (next == null) {
						newTable[idx] = e;
					} else {
						// Reuse trailing consecutive sequence at same slot
						HashEntry lastRun = e;
						int lastIdx = idx;
						for (HashEntry last = next; last != null; last = last.next) {
							final int k = last.hash & sizeMask;
							if (k != lastIdx) {
								lastIdx = k;
								lastRun = last;
							}
						}
						newTable[lastIdx] = lastRun;
						
						// Clone all remaining nodes
						for (HashEntry p = e; p != lastRun; p = p.next) {
							final int k = p.hash & sizeMask;
							final HashEntry n = newTable[k];
							newTable[k] = new HashEntry(p.key, p.hash, n, p.value);
						}
					}
				}
			}
			this.table = newTable;
		}
		
		/**
		 * Remove; match on key only if value null, else match both.
		 */
		CEIntegerWeak remove(final int key, final int hash, final Object value) {

			this.lock();
			try {
				final int c = this.count - 1;
				final HashEntry[] tab = this.table;
				final int index = hash & tab.length - 1;
				final HashEntry first = tab[index];
				HashEntry e = first;
				while (e != null && (e.hash != hash || key != e.key)) {
					e = e.next;
				}
				
				CEIntegerWeak oldValue = null;
				if (e != null) {
					final CEIntegerWeak v = e.value;
					if (value == null || value.equals(v)) {
						oldValue = v;
						// All entries following removed node can stay
						// in list, but all preceding ones need to be
						// cloned.
						++this.modCount;
						HashEntry newFirst = e.next;
						for (HashEntry p = first; p != e; p = p.next) {
							newFirst = new HashEntry(p.key, p.hash, newFirst, p.value);
						}
						tab[index] = newFirst;
						this.count = c; // write-volatile
					}
				}
				return oldValue;
			} finally {
				this.unlock();
			}
		}
		
		CEIntegerWeak replace(final int key, final int hash, final CEIntegerWeak newValue) {

			this.lock();
			try {
				HashEntry e = this.getFirst(hash);
				while (e != null && (e.hash != hash || e.key != key)) {
					e = e.next;
				}
				
				CEIntegerWeak oldValue = null;
				if (e != null) {
					oldValue = e.value;
					e.value = newValue;
				}
				return oldValue;
			} finally {
				this.unlock();
			}
		}
		
		boolean replace(final int key, final int hash, final CEIntegerWeak oldValue, final CEIntegerWeak newValue) {

			this.lock();
			try {
				HashEntry e = this.getFirst(hash);
				while (e != null && (e.hash != hash || e.key != key)) {
					e = e.next;
				}
				
				boolean replaced = false;
				if (e != null && oldValue.equals(e.value)) {
					replaced = true;
					e.value = newValue;
				}
				return replaced;
			} finally {
				this.unlock();
			}
		}
		
		/**
		 * Sets table to new HashEntry array. Call only while holding lock or in
		 * constructor.
		 */
		void setTable(final HashEntry[] newTable) {

			this.threshold = (int) (newTable.length * this.loadFactor);
			this.table = newTable;
		}
	}
	
	/* ---------------- Fields -------------- */
	
	private static final long serialVersionUID = 7249069246763182397L;
	
	/**
	 * The default initial capacity for this table, used when not otherwise
	 * specified in a constructor.
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	/**
	 * The default load factor for this table, used when not otherwise specified
	 * in a constructor.
	 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	/* ---------------- Small Utilities -------------- */
	
	/**
	 * The default concurrency level for this table, used when not otherwise
	 * specified in a constructor.
	 */
	static final int DEFAULT_CONCURRENCY_LEVEL = 16;
	
	/**
	 * The maximum capacity, used if a higher value is implicitly specified by
	 * either of the constructors with arguments. MUST be a power of two <=
	 * 1<<30 to ensure that entries are indexable using ints.
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	/* ---------------- Inner Classes -------------- */
	
	/**
	 * The maximum number of segments to allow; used to bound constructor
	 * arguments.
	 */
	static final int MAX_SEGMENTS = 1 << 16; // slightly
												// conservative

	/**
	 * Number of unsynchronized retries in size and containsValue methods before
	 * resorting to locking. This is used to avoid unbounded retries if tables
	 * undergo continuous modification which would make it impossible to obtain
	 * an accurate result.
	 */
	static final int RETRIES_BEFORE_LOCK = 2;
	
	/* ---------------- Public operations -------------- */
	
	/**
	 * Applies a supplemental hash function to a given hashCode, which defends
	 * against poor quality hash functions. This is critical because
	 * ConcurrentHashMap uses power-of-two length hash tables, that otherwise
	 * encounter collisions for hashCodes that do not differ in lower or upper
	 * bits.
	 */
	private static int hash(int h) {

		// Spread bits to regularize both segment and index locations,
		// using variant of single-word Wang/Jenkins hash.
		h += h << 15 ^ 0xffffcd7d;
		h ^= h >>> 10;
		h += h << 3;
		h ^= h >>> 6;
		h += (h << 2) + (h << 14);
		return h ^ h >>> 16;
	}
	
	/**
	 * Mask value for indexing into segments. The upper bits of a key's hash
	 * code are used to choose the segment.
	 */
	final int segmentMask;
	
	/**
	 * Shift value for indexing within segments.
	 */
	final int segmentShift;
	
	/**
	 * The segments, each of which is a specialized hash table
	 */
	final Segment[] segments;
	
	/**
	 * Creates a new, empty map with a default initial capacity (16), load
	 * factor (0.75) and concurrencyLevel (16).
	 */
	public CIIntegerHashIntConcurrentExact() {
		this(
				CIIntegerHashIntConcurrentExact.DEFAULT_INITIAL_CAPACITY,
				CIIntegerHashIntConcurrentExact.DEFAULT_LOAD_FACTOR,
				CIIntegerHashIntConcurrentExact.DEFAULT_CONCURRENCY_LEVEL);
	}
	
	/**
	 * Creates a new, empty map with the specified initial capacity, and with
	 * default load factor (0.75) and concurrencyLevel (16).
	 *
	 * @param initialCapacity
	 *            the initial capacity. The implementation performs internal
	 *            sizing to accommodate this many elements.
	 * @throws IllegalArgumentException
	 *             if the initial capacity of elements is negative.
	 */
	public CIIntegerHashIntConcurrentExact(final int initialCapacity) {
		this(initialCapacity, CIIntegerHashIntConcurrentExact.DEFAULT_LOAD_FACTOR, CIIntegerHashIntConcurrentExact.DEFAULT_CONCURRENCY_LEVEL);
	}
	
	/**
	 * Creates a new, empty map with the specified initial capacity and load
	 * factor and with the default concurrencyLevel (16).
	 *
	 * @param initialCapacity
	 *            The implementation performs internal sizing to accommodate
	 *            this many elements.
	 * @param loadFactor
	 *            the load factor threshold, used to control resizing. Resizing
	 *            may be performed when the average number of elements per bin
	 *            exceeds this threshold.
	 * @throws IllegalArgumentException
	 *             if the initial capacity of elements is negative or the load
	 *             factor is nonpositive
	 *
	 * @since 1.6
	 */
	public CIIntegerHashIntConcurrentExact(final int initialCapacity, final float loadFactor) {
		this(initialCapacity, loadFactor, CIIntegerHashIntConcurrentExact.DEFAULT_CONCURRENCY_LEVEL);
	}
	
	/**
	 * Creates a new, empty map with the specified initial capacity, load factor
	 * and concurrency level.
	 *
	 * @param initialCapacity
	 *            the initial capacity. The implementation performs internal
	 *            sizing to accommodate this many elements.
	 * @param loadFactor
	 *            the load factor threshold, used to control resizing. Resizing
	 *            may be performed when the average number of elements per bin
	 *            exceeds this threshold.
	 * @param concurrencyLevel
	 *            the estimated number of concurrently updating threads. The
	 *            implementation performs internal sizing to try to accommodate
	 *            this many threads.
	 * @throws IllegalArgumentException
	 *             if the initial capacity is negative or the load factor or
	 *             concurrencyLevel are nonpositive.
	 */
	public CIIntegerHashIntConcurrentExact(int initialCapacity, final float loadFactor, int concurrencyLevel) {
		if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0) {
			throw new IllegalArgumentException();
		}
		
		if (concurrencyLevel > CIIntegerHashIntConcurrentExact.MAX_SEGMENTS) {
			concurrencyLevel = CIIntegerHashIntConcurrentExact.MAX_SEGMENTS;
		}
		
		// Find power-of-two sizes best matching arguments
		int sshift = 0;
		int ssize = 1;
		while (ssize < concurrencyLevel) {
			++sshift;
			ssize <<= 1;
		}
		this.segmentShift = 32 - sshift;
		this.segmentMask = ssize - 1;
		this.segments = Segment.newArray(ssize);
		
		if (initialCapacity > CIIntegerHashIntConcurrentExact.MAXIMUM_CAPACITY) {
			initialCapacity = CIIntegerHashIntConcurrentExact.MAXIMUM_CAPACITY;
		}
		int c = initialCapacity / ssize;
		if (c * ssize < initialCapacity) {
			++c;
		}
		int cap = 1;
		while (cap < c) {
			cap <<= 1;
		}
		
		for (int i = 0; i < this.segments.length; ++i) {
			this.segments[i] = new Segment(cap, loadFactor);
		}
	}
	
	@Override
	public CEIntegerWeak cacheGetCreateInternal(final int key) {

		final int hash = CIIntegerHashIntConcurrentExact.hash(key);
		return this.segmentFor(hash).put(key, hash);
	}
	
	@Override
	public void cacheRemove(final int key, final CEInteger value) {

		final int hash = CIIntegerHashIntConcurrentExact.hash(key);
		if (value != null) {
			this.segmentFor(hash).remove(key, hash, value);
		}
	}
	
	/**
	 * Removes all of the mappings from this map.
	 */
	public void clear() {

		for (final Segment segment : this.segments) {
			segment.clear();
		}
	}
	
	/**
	 * Legacy method testing if some key maps into the specified value in this
	 * table. This method is identical in functionality to
	 * {@link #containsValue}, and exists solely to ensure full compatibility
	 * with class {@link java.util.Hashtable}, which supported this method prior
	 * to introduction of the Java Collections framework.
	 *
	 * @param value
	 *            a value to search for
	 * @return <tt>true</tt> if and only if some key maps to the <tt>value</tt>
	 *         argument in this table as determined by the <tt>equals</tt>
	 *         method; <tt>false</tt> otherwise
	 * @throws NullPointerException
	 *             if the specified value is null
	 */
	public boolean contains(final Object value) {

		return this.containsValue(value);
	}
	
	/**
	 * Tests if the specified object is a key in this table.
	 *
	 * @param key
	 *            possible key
	 * @return <tt>true</tt> if and only if the specified object is a key in
	 *         this table, as determined by the <tt>equals</tt> method;
	 *         <tt>false</tt> otherwise.
	 * @throws NullPointerException
	 *             if the specified key is null
	 */
	public boolean containsKey(final int key) {

		final int hash = CIIntegerHashIntConcurrentExact.hash(key);
		return this.segmentFor(hash).containsKey(key, hash);
	}
	
	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the specified
	 * value. Note: This method requires a full internal traversal of the hash
	 * table, and so is much slower than method <tt>containsKey</tt>.
	 *
	 * @param value
	 *            value whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map maps one or more keys to the specified
	 *         value
	 * @throws NullPointerException
	 *             if the specified value is null
	 */
	public boolean containsValue(final Object value) {

		if (value == null) {
			throw new NullPointerException();
		}
		
		// See explanation of modCount use above
		
		final Segment[] segments = this.segments;
		final int[] mc = new int[segments.length];
		
		// Try a few times without locking
		for (int k = 0; k < CIIntegerHashIntConcurrentExact.RETRIES_BEFORE_LOCK; ++k) {
			int mcsum = 0;
			for (int i = 0; i < segments.length; ++i) {
				mcsum += mc[i] = segments[i].modCount;
				if (segments[i].containsValue(value)) {
					return true;
				}
			}
			boolean cleanSweep = true;
			if (mcsum != 0) {
				for (int i = 0; i < segments.length; ++i) {
					if (mc[i] != segments[i].modCount) {
						cleanSweep = false;
						break;
					}
				}
			}
			if (cleanSweep) {
				return false;
			}
		}
		// Resort to locking all segments
		for (final Segment segment : segments) {
			segment.lock();
		}
		boolean found = false;
		try {
			for (final Segment segment : segments) {
				if (segment.containsValue(value)) {
					found = true;
					break;
				}
			}
		} finally {
			for (final Segment segment : segments) {
				segment.unlock();
			}
		}
		return found;
	}
	
	/* ---------------- Iterator Support -------------- */
	
	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 *
	 * @return <tt>true</tt> if this map contains no key-value mappings
	 */
	public boolean isEmpty() {

		final Segment[] segments = this.segments;
		/*
		 * We keep track of per-segment modCounts to avoid ABA problems in which
		 * an element in one segment was added and in another removed during
		 * traversal, in which case the table was never actually empty at any
		 * point. Note the similar use of modCounts in the size() and
		 * containsValue() methods, which are the only other methods also
		 * susceptible to ABA problems.
		 */
		final int[] mc = new int[segments.length];
		int mcsum = 0;
		for (int i = 0; i < segments.length; ++i) {
			if (segments[i].count != 0) {
				return false;
			}
			mcsum += mc[i] = segments[i].modCount;
		}
		// If mcsum happens to be zero, then we know we got a snapshot
		// before any modifications at all were made. This is
		// probably common enough to bother tracking.
		if (mcsum != 0) {
			for (int i = 0; i < segments.length; ++i) {
				if (segments[i].count != 0 || mc[i] != segments[i].modCount) {
					return false;
				}
			}
		}
		return true;
	}
	
	CEIntegerWeak remove(final int key) {

		final int hash = CIIntegerHashIntConcurrentExact.hash(key);
		return this.segmentFor(hash).remove(key, hash, null);
	}
	
	/**
	 *
	 * @return the previous value associated with the specified key, or
	 *         <tt>null</tt> if there was no mapping for the key
	 * @throws NullPointerException
	 *             if the specified key or value is null
	 */
	CEIntegerWeak replace(final int key, final CEIntegerWeak value) {

		if (value == null) {
			throw new NullPointerException();
		}
		final int hash = CIIntegerHashIntConcurrentExact.hash(key);
		return this.segmentFor(hash).replace(key, hash, value);
	}
	
	/**
	 *
	 * @throws NullPointerException
	 *             if any of the arguments are null
	 */
	boolean replace(final int key, final CEIntegerWeak oldValue, final CEIntegerWeak newValue) {

		if (oldValue == null || newValue == null) {
			throw new NullPointerException();
		}
		final int hash = CIIntegerHashIntConcurrentExact.hash(key);
		return this.segmentFor(hash).replace(key, hash, oldValue, newValue);
	}
	
	/**
	 * Returns the segment that should be used for key with given hash
	 *
	 * @param hash
	 *            the hash code for the key
	 * @return the segment
	 */
	final Segment segmentFor(final int hash) {

		return this.segments[hash >>> this.segmentShift & this.segmentMask];
	}
	
	/**
	 * Returns the number of key-value mappings in this map. If the map contains
	 * more than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of key-value mappings in this map
	 */
	public int size() {

		final Segment[] segments = this.segments;
		long sum = 0;
		long check = 0;
		final int[] mc = new int[segments.length];
		// Try a few times to get accurate count. On failure due to
		// continuous async changes in table, resort to locking.
		for (int k = 0; k < CIIntegerHashIntConcurrentExact.RETRIES_BEFORE_LOCK; ++k) {
			check = 0;
			sum = 0;
			int mcsum = 0;
			for (int i = 0; i < segments.length; ++i) {
				sum += segments[i].count;
				mcsum += mc[i] = segments[i].modCount;
			}
			if (mcsum != 0) {
				for (int i = 0; i < segments.length; ++i) {
					check += segments[i].count;
					if (mc[i] != segments[i].modCount) {
						check = -1; // force retry
						break;
					}
				}
			}
			if (check == sum) {
				break;
			}
		}
		if (check != sum) { // Resort to locking all segments
			sum = 0;
			for (final Segment segment : segments) {
				segment.lock();
			}
			for (final Segment segment : segments) {
				sum += segment.count;
			}
			for (final Segment segment : segments) {
				segment.unlock();
			}
		}
		if (sum > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return (int) sum;
	}
	
}
