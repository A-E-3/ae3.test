package ru.myx.ae3.base;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;

/**
 * A Red-Black tree based {@link NavigableMap} implementation. The map is sorted
 * according to the {@linkplain Comparable natural ordering} of its keys, or by
 * a {@link Comparator} provided at map creation time, depending on which
 * constructor is used.
 *
 * <p>
 * This implementation provides guaranteed log(n) time cost for the
 * <tt>containsKey</tt>, <tt>get</tt>, <tt>put</tt> and <tt>remove</tt>
 * operations. Algorithms are adaptations of those in Cormen, Leiserson, and
 * Rivest's <I>Introduction to Algorithms</I>.
 *
 * <p>
 * Note that the ordering maintained by a sorted map (whether or not an explicit
 * comparator is provided) must be <i>consistent with equals</i> if this sorted
 * map is to correctly implement the <tt>Map</tt> interface. (See
 * <tt>Comparable</tt> or <tt>Comparator</tt> for a precise definition of
 * <i>consistent with equals</i>.) This is so because the <tt>Map</tt> interface
 * is defined in terms of the equals operation, but a map performs all key
 * comparisons using its <tt>compareTo</tt> (or <tt>compare</tt>) method, so two
 * keys that are deemed equal by this method are, from the standpoint of the
 * sorted map, equal. The behavior of a sorted map <i>is</i> well-defined even
 * if its ordering is inconsistent with equals; it just fails to obey the
 * general contract of the <tt>Map</tt> interface.
 *
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a map concurrently, and at least one of the threads
 * modifies the map structurally, it <i>must</i> be synchronized externally. (A
 * structural modification is any operation that adds or deletes one or more
 * mappings; merely changing the value associated with an existing key is not a
 * structural modification.) This is typically accomplished by synchronizing on
 * some object that naturally encapsulates the map. If no such object exists,
 * the map should be "wrapped" using the
 * {@link Collections#synchronizedSortedMap Collections.synchronizedSortedMap}
 * method. This is best done at creation time, to prevent accidental
 * unsynchronized access to the map:
 *
 * <pre>
 *   SortedMap m = Collections.synchronizedSortedMap(new TreeMapFast(...));
 * </pre>
 *
 * <p>
 * The iterators returned by the <tt>iterator</tt> method of the collections
 * returned by all of this class's "collection view methods" are
 * <i>fail-fast</i>: if the map is structurally modified at any time after the
 * iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a
 * {@link ConcurrentModificationException}. Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 *
 * <p>
 * Note that the fail-fast behavior of an iterator cannot be guaranteed as it
 * is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification. Fail-fast iterators throw
 * <tt>ConcurrentModificationException</tt> on a best-effort basis. Therefore,
 * it would be wrong to write a program that depended on this exception for its
 * correctness: <i>the fail-fast behavior of iterators should be used only to
 * detect bugs.</i>
 *
 * <p>
 * All <tt>Map.Entry</tt> pairs returned by methods in this class and its views
 * represent snapshots of mappings at the time they were produced. They do
 * <em>not</em> support the <tt>Entry.setValue</tt> method. (Note however that
 * it is possible to change mappings in the associated map using <tt>put</tt>.)
 *
 * <p>
 * This class is a member of the
 * <a href="{@docRoot} /../technotes/guides/collections/index.html"> Java
 * Collections Framework</a>.
 *
 * @author Josh Bloch and Doug Lea
 * @version 1.73, 05/10/06
 * @see Map
 * @see HashMap
 * @see Hashtable
 * @see Comparable
 * @see Comparator
 * @see Collection
 * @since 1.2
 */

final class CIIntegerTree implements CacheIntegerImplEx {
	
	/**
	 * Node in the Tree. Doubles as a means to pass key-value pairs back to user
	 * (see Map.Entry).
	 */
	
	static final class Entry {

		int key;
		
		CEIntegerWeak value;
		
		Entry left = null;
		
		Entry right = null;
		
		Entry parent;
		
		boolean color = CIIntegerTree.BLACK;
		
		Entry(final int key, final CEIntegerWeak value, final Entry parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		
		@Override
		public boolean equals(final Object o) {

			if (!(o instanceof Entry)) {
				return false;
			}
			final Entry e = (Entry) o;
			return this.key == e.key;
		}
		
		public int getKey() {

			return this.key;
		}
		
		public CEIntegerWeak getValue() {

			return this.value;
		}
		
		@Override
		public int hashCode() {

			final int keyHash = this.key;
			final int valueHash = this.value == null
				? 0
				: this.value.hashCode();
			return keyHash ^ valueHash;
		}
		
		@Override
		public String toString() {

			return this.key + "=" + this.value;
		}
	}
	
	private static final int compare(final int i1, final int i2) {

		return i2 - i1;
	}
	
	private transient Entry root = null;
	
	/**
	 * The number of entries in the tree
	 */
	private transient int size = 0;
	
	private static final boolean RED = false;
	
	private static final boolean BLACK = true;
	
	/**
	 * Balancing operations.
	 *
	 * Implementations of rebalancings during insertion and deletion are
	 * slightly different than the CLR version. Rather than using dummy
	 * nilnodes, we use a set of accessors that deal properly with null. They
	 * are used to avoid messiness surrounding nullness checks in the main
	 * algorithms.
	 */
	
	private static boolean colorOf(final Entry p) {

		return p == null
			? CIIntegerTree.BLACK
			: p.color;
	}
	
	/**
	 * Returns the key corresponding to the specified Entry.
	 *
	 * @throws NoSuchElementException
	 *             if the Entry is null
	 */
	private static Entry leftOf(final Entry p) {

		return p == null
			? null
			: p.left;
	}
	
	private static Entry parentOf(final Entry p) {

		return p == null
			? null
			: p.parent;
	}
	
	private static Entry rightOf(final Entry p) {

		return p == null
			? null
			: p.right;
	}
	
	private static void setColor(final Entry p, final boolean c) {

		if (p != null) {
			p.color = c;
		}
	}
	
	/**
	 * Returns the successor of the specified Entry, or null if no such.
	 */
	static CIIntegerTree.Entry successor(final Entry t) {

		if (t == null) {
			return null;
		} else if (t.right != null) {
			Entry p = t.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			Entry p = t.parent;
			Entry ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}
	
	/**
	 * Constructs a new, empty tree map, using the natural ordering of its keys.
	 * All keys inserted into the map must implement the {@link Comparable}
	 * interface. Furthermore, all such keys must be <i>mutually comparable</i>:
	 * <tt>k1.compareTo(k2)</tt> must not throw a <tt>ClassCastException</tt>
	 * for any keys <tt>k1</tt> and <tt>k2</tt> in the map. If the user attempts
	 * to put a key into the map that violates this constraint (for example, the
	 * user attempts to put a string key into a map whose keys are integers),
	 * the <tt>put(Object key, Object value)</tt> call will throw a
	 * <tt>ClassCastException</tt>.
	 */
	public CIIntegerTree() {
		//
	}
	
	public final CEIntegerWeak cacheGet(final int key) {

		final Entry p = this.getEntry(key);
		return p == null
			? null
			: p.value;
	}
	
	@Override
	public final CEIntegerWeak cacheGetCreateInternal(final int key) {

		Entry t = this.root;
		if (t == null) {
			// TBD:
			// 5045147: (coll) Adding null to an empty TreeSet should
			// throw NullPointerException
			//
			// compare(key, key); // type check
			final CEIntegerWeak value = new CEIntegerWeak(key);
			this.root = new Entry(key, value, null);
			this.size = 1;
			return value;
		}
		int cmp;
		Entry parent;
		// split comparator and comparable paths
		do {
			parent = t;
			cmp = CIIntegerTree.compare(key, t.key);
			if (cmp < 0) {
				t = t.left;
			} else if (cmp > 0) {
				t = t.right;
			} else {
				return t.value;
			}
		} while (t != null);
		{
			final CEIntegerWeak value = new CEIntegerWeak(key);
			final Entry e = new Entry(key, value, parent);
			if (cmp < 0) {
				parent.left = e;
			} else {
				parent.right = e;
			}
			this.fixAfterInsertion(e);
			this.size++;
			return value;
		}
	}
	
	public final CEIntegerWeak cacheGetCreate2(final int key) {

		{
			final Entry p = this.getEntry(key);
			if (p != null) {
				return p.value;
			}
		}
		synchronized (this) {
			final Entry p = this.getEntry(key);
			return p != null
				? p.value
				: this.put(key, new CEIntegerWeak(key));
		}
	}
	
	@Override
	public void cacheRemove(final int key, final CEInteger value) {

		final Entry p = this.getEntry(key);
		synchronized (this) {
			if (p == null) {
				return;
			}
			this.deleteEntry(p);
		}
	}
	
	/**
	 * Removes all of the mappings from this map. The map will be empty after
	 * this call returns.
	 */
	public void clear() {

		this.size = 0;
		this.root = null;
	}
	
	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the specified
	 * key.
	 *
	 * @param key
	 *            key whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map contains a mapping for the specified
	 *         key
	 * @throws ClassCastException
	 *             if the specified key cannot be compared with the keys
	 *             currently in the map
	 * @throws NullPointerException
	 *             if the specified key is null and this map uses natural
	 *             ordering, or its comparator does not permit null keys
	 */
	public boolean containsKey(final int key) {

		return this.getEntry(key) != null;
	}
	
	/*
	 * Unlike Values and EntrySet, the KeySet class is static, delegating to a
	 * NavigableMap to allow use by SubMaps, which outweighs the ugliness of
	 * needing type-tests for the following Iterator methods that are defined
	 * appropriately in main versus submap classes.
	 */
	
	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the specified
	 * value. More formally, returns <tt>true</tt> if and only if this map
	 * contains at least one mapping to a value <tt>v</tt> such that
	 * <tt>(value==null ? v==null : value.equals(v))</tt>. This operation will
	 * probably require time linear in the map size for most implementations.
	 *
	 * @param value
	 *            value whose presence in this map is to be tested
	 * @return <tt>true</tt> if a mapping to <tt>value</tt> exists;
	 *         <tt>false</tt> otherwise
	 * @since 1.2
	 */
	public boolean containsValue(final CEIntegerWeak value) {

		return this.containsKey(value.value);
	}
	
	/**
	 * Delete node p, and then rebalance the tree.
	 */
	private void deleteEntry(Entry p) {

		this.size--;
		
		// If strictly internal, copy successor's element to p and then make p
		// point to successor.
		if (p.left != null && p.right != null) {
			final Entry s = CIIntegerTree.successor(p);
			p.key = s.key;
			p.value = s.value;
			p = s;
		} // p has 2 children
		
		// Start fixup at replacement node, if it exists.
		final Entry replacement = p.left != null
			? p.left
			: p.right;
		
		if (replacement != null) {
			// Link replacement to parent
			replacement.parent = p.parent;
			if (p.parent == null) {
				this.root = replacement;
			} else if (p == p.parent.left) {
				p.parent.left = replacement;
			} else {
				p.parent.right = replacement;
			}
			
			// Null out links so they are OK to use by fixAfterDeletion.
			p.left = p.right = p.parent = null;
			
			// Fix replacement
			if (p.color == CIIntegerTree.BLACK) {
				this.fixAfterDeletion(replacement);
			}
		} else if (p.parent == null) { // return if we are the only node.
			this.root = null;
		} else { // No children. Use self as phantom replacement and unlink.
			if (p.color == CIIntegerTree.BLACK) {
				this.fixAfterDeletion(p);
			}
			
			if (p.parent != null) {
				if (p == p.parent.left) {
					p.parent.left = null;
				} else if (p == p.parent.right) {
					p.parent.right = null;
				}
				p.parent = null;
			}
		}
	}
	
	/** From CLR */
	private void fixAfterDeletion(Entry x) {

		while (x != this.root && CIIntegerTree.colorOf(x) == CIIntegerTree.BLACK) {
			if (x == CIIntegerTree.leftOf(CIIntegerTree.parentOf(x))) {
				Entry sib = CIIntegerTree.rightOf(CIIntegerTree.parentOf(x));
				
				if (CIIntegerTree.colorOf(sib) == CIIntegerTree.RED) {
					CIIntegerTree.setColor(sib, CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.RED);
					this.rotateLeft(CIIntegerTree.parentOf(x));
					sib = CIIntegerTree.rightOf(CIIntegerTree.parentOf(x));
				}
				
				if (CIIntegerTree.colorOf(CIIntegerTree.leftOf(sib)) == CIIntegerTree.BLACK
						&& CIIntegerTree.colorOf(CIIntegerTree.rightOf(sib)) == CIIntegerTree.BLACK) {
					CIIntegerTree.setColor(sib, CIIntegerTree.RED);
					x = CIIntegerTree.parentOf(x);
				} else {
					if (CIIntegerTree.colorOf(CIIntegerTree.rightOf(sib)) == CIIntegerTree.BLACK) {
						CIIntegerTree.setColor(CIIntegerTree.leftOf(sib), CIIntegerTree.BLACK);
						CIIntegerTree.setColor(sib, CIIntegerTree.RED);
						this.rotateRight(sib);
						sib = CIIntegerTree.rightOf(CIIntegerTree.parentOf(x));
					}
					CIIntegerTree.setColor(sib, CIIntegerTree.colorOf(CIIntegerTree.parentOf(x)));
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.rightOf(sib), CIIntegerTree.BLACK);
					this.rotateLeft(CIIntegerTree.parentOf(x));
					x = this.root;
				}
			} else { // symmetric
				Entry sib = CIIntegerTree.leftOf(CIIntegerTree.parentOf(x));
				
				if (CIIntegerTree.colorOf(sib) == CIIntegerTree.RED) {
					CIIntegerTree.setColor(sib, CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.RED);
					this.rotateRight(CIIntegerTree.parentOf(x));
					sib = CIIntegerTree.leftOf(CIIntegerTree.parentOf(x));
				}
				
				if (CIIntegerTree.colorOf(CIIntegerTree.rightOf(sib)) == CIIntegerTree.BLACK
						&& CIIntegerTree.colorOf(CIIntegerTree.leftOf(sib)) == CIIntegerTree.BLACK) {
					CIIntegerTree.setColor(sib, CIIntegerTree.RED);
					x = CIIntegerTree.parentOf(x);
				} else {
					if (CIIntegerTree.colorOf(CIIntegerTree.leftOf(sib)) == CIIntegerTree.BLACK) {
						CIIntegerTree.setColor(CIIntegerTree.rightOf(sib), CIIntegerTree.BLACK);
						CIIntegerTree.setColor(sib, CIIntegerTree.RED);
						this.rotateLeft(sib);
						sib = CIIntegerTree.leftOf(CIIntegerTree.parentOf(x));
					}
					CIIntegerTree.setColor(sib, CIIntegerTree.colorOf(CIIntegerTree.parentOf(x)));
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.leftOf(sib), CIIntegerTree.BLACK);
					this.rotateRight(CIIntegerTree.parentOf(x));
					x = this.root;
				}
			}
		}
		
		CIIntegerTree.setColor(x, CIIntegerTree.BLACK);
	}
	
	/** From CLR */
	private void fixAfterInsertion(Entry x) {

		x.color = CIIntegerTree.RED;
		
		while (x != null && x != this.root && x.parent.color == CIIntegerTree.RED) {
			if (CIIntegerTree.parentOf(x) == CIIntegerTree.leftOf(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)))) {
				final Entry y = CIIntegerTree.rightOf(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)));
				if (CIIntegerTree.colorOf(y) == CIIntegerTree.RED) {
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.BLACK);
					CIIntegerTree.setColor(y, CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)), CIIntegerTree.RED);
					x = CIIntegerTree.parentOf(CIIntegerTree.parentOf(x));
				} else {
					if (x == CIIntegerTree.rightOf(CIIntegerTree.parentOf(x))) {
						x = CIIntegerTree.parentOf(x);
						this.rotateLeft(x);
					}
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)), CIIntegerTree.RED);
					this.rotateRight(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)));
				}
			} else {
				final Entry y = CIIntegerTree.leftOf(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)));
				if (CIIntegerTree.colorOf(y) == CIIntegerTree.RED) {
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.BLACK);
					CIIntegerTree.setColor(y, CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)), CIIntegerTree.RED);
					x = CIIntegerTree.parentOf(CIIntegerTree.parentOf(x));
				} else {
					if (x == CIIntegerTree.leftOf(CIIntegerTree.parentOf(x))) {
						x = CIIntegerTree.parentOf(x);
						this.rotateRight(x);
					}
					CIIntegerTree.setColor(CIIntegerTree.parentOf(x), CIIntegerTree.BLACK);
					CIIntegerTree.setColor(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)), CIIntegerTree.RED);
					this.rotateLeft(CIIntegerTree.parentOf(CIIntegerTree.parentOf(x)));
				}
			}
		}
		this.root.color = CIIntegerTree.BLACK;
	}
	
	// Little utilities
	
	public CEIntegerWeak get(final int key) {

		final Entry p = this.getEntry(key);
		return p == null
			? null
			: p.value;
	}
	
	/**
	 * Gets the entry corresponding to the specified key; if no such entry
	 * exists, returns the entry for the least key greater than the specified
	 * key; if no such entry exists (i.e., the greatest key in the Tree is less
	 * than the specified key), returns <tt>null</tt>.
	 */
	final Entry getCeilingEntry(final int key) {

		Entry p = this.root;
		while (p != null) {
			final int cmp = CIIntegerTree.compare(key, p.key);
			if (cmp < 0) {
				if (p.left != null) {
					p = p.left;
				} else {
					return p;
				}
			} else if (cmp > 0) {
				if (p.right != null) {
					p = p.right;
				} else {
					Entry parent = p.parent;
					Entry ch = p;
					while (parent != null && ch == parent.right) {
						ch = parent;
						parent = parent.parent;
					}
					return parent;
				}
			} else {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Returns this map's entry for the given key, or <tt>null</tt> if the map
	 * does not contain an entry for the key.
	 *
	 * @return this map's entry for the given key, or <tt>null</tt> if the map
	 *         does not contain an entry for the key
	 * @throws ClassCastException
	 *             if the specified key cannot be compared with the keys
	 *             currently in the map
	 * @throws NullPointerException
	 *             if the specified key is null and this map uses natural
	 *             ordering, or its comparator does not permit null keys
	 */
	final Entry getEntry(final int key) {

		for (Entry p = this.root; p != null;) {
			final int cmp = CIIntegerTree.compare(key, p.key);
			if (cmp < 0) {
				p = p.left;
			} else //
			if (cmp > 0) {
				p = p.right;
			} else {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Returns the first Entry in the TreeMap (according to the TreeMap's
	 * key-sort function). Returns null if the TreeMap is empty.
	 */
	final Entry getFirstEntry() {

		Entry p = this.root;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
		}
		return p;
	}
	
	// SubMaps
	
	// Red-black mechanics
	
	public CEIntegerWeak put(final int key, final CEIntegerWeak value) {

		Entry t = this.root;
		if (t == null) {
			// TBD:
			// 5045147: (coll) Adding null to an empty TreeSet should
			// throw NullPointerException
			//
			// compare(key, key); // type check
			this.root = new Entry(key, value, null);
			this.size = 1;
			return value;
		}
		int cmp;
		Entry parent;
		// split comparator and comparable paths
		do {
			parent = t;
			cmp = CIIntegerTree.compare(key, t.key);
			if (cmp < 0) {
				t = t.left;
			} else if (cmp > 0) {
				t = t.right;
			} else {
				return t.value;
			}
		} while (t != null);
		final Entry e = new Entry(key, value, parent);
		if (cmp < 0) {
			parent.left = e;
		} else {
			parent.right = e;
		}
		this.fixAfterInsertion(e);
		this.size++;
		return value;
	}
	
	public void remove(final int key) {

		final Entry p = this.getEntry(key);
		if (p == null) {
			return;
		}
		this.deleteEntry(p);
	}
	
	/** From CLR */
	private void rotateLeft(final Entry p) {

		if (p != null) {
			final Entry r = p.right;
			p.right = r.left;
			if (r.left != null) {
				r.left.parent = p;
			}
			r.parent = p.parent;
			if (p.parent == null) {
				this.root = r;
			} else if (p.parent.left == p) {
				p.parent.left = r;
			} else {
				p.parent.right = r;
			}
			r.left = p;
			p.parent = r;
		}
	}
	
	/** From CLR */
	private void rotateRight(final Entry p) {

		if (p != null) {
			final Entry l = p.left;
			p.left = l.right;
			if (l.right != null) {
				l.right.parent = p;
			}
			l.parent = p.parent;
			if (p.parent == null) {
				this.root = l;
			} else if (p.parent.right == p) {
				p.parent.right = l;
			} else {
				p.parent.left = l;
			}
			l.right = p;
			p.parent = l;
		}
	}
	
	/**
	 * Returns the number of key-value mappings in this map.
	 *
	 * @return the number of key-value mappings in this map
	 */
	public int size() {

		return this.size;
	}
}
