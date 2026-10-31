package ru.myx.ae3.base;

class CILongHashMapCacheEntry2 implements CacheLongImplEx {
	
	/**
	 * The maximum capacity, used if a higher value is implicitly specified by
	 * either of the constructors with arguments. MUST be a power of two <=
	 * 1<<30.
	 */
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	/**
	 * Applies a supplemental hash function to a given hashCode, which defends
	 * against poor quality hash functions. This is critical because HashMap
	 * uses power-of-two length hash tables, that otherwise encounter collisions
	 * for hashCodes that do not differ in lower bits.
	 */
	private static final int hashNumber(int h) {
		
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		h ^= h >>> 20 ^ h >>> 12;
		return h ^ h >>> 7 ^ h >>> 4;
	}

	private static final int hashNumber(final long key) {
		
		return CILongHashMapCacheEntry2.hashNumber((int) (key ^ key >>> 32));
	}

	private final float loadFactor;

	private transient int size;

	private transient CELongWeakLinked2[] table;

	private int threshold;

	CILongHashMapCacheEntry2(final int capacity, final float loadFactor) {
		this.table = new CELongWeakLinked2[capacity];
		this.loadFactor = loadFactor;
		this.threshold = (int) (this.table.length * this.loadFactor);
	}

	@Override
	public final CELongWeakLinked2 cacheGetCreateInternal(final long key) {
		
		final int hash = CILongHashMapCacheEntry2.hashNumber(key);
		CELongWeakLinked2 e = this.table[hash & this.table.length - 1];
		if (e == null) {
			/**
			 * falls only for the empty bucket
			 */
			synchronized (this.table) {
				final int i = hash & this.table.length - 1;
				e = this.table[i];
				if (e == null) {
					return this.table[i] = new CELongWeakLinked2(key);
				}
			}
		}
		for (;; e = e.next) {
			if (e.value == key) {
				return e;
			}
			if (e.next == null) {
				final CELongWeakLinked2 value;
				synchronized (e) {
					if (e.next != null) {
						/**
						 * created while waiting for lock
						 */
						e = e.next;
						continue;
					}
					value = new CELongWeakLinked2(key);
					e.next = value;
					value.prev = e;
					this.size++;
					if (this.size >= this.threshold) {
						this.resize(2 * this.table.length);
					}
				}
				return value;
			}
		}
	}

	@Override
	public void cacheRemove(final long key, final CELongWeak val) {
		
		final CELongWeakLinked2 value = (CELongWeakLinked2) val;
		
		if (value.prev == null) {
			final int i = CILongHashMapCacheEntry2.hashNumber(key) & this.table.length - 1;
			synchronized (this.table) {
				assert this.table[i] == value;
				if ((this.table[i] = value.next) != null) {
					value.next.prev = null;
				}
				this.size--;
			}
		} else {
			synchronized (value) {
				// assert this.cacheGet( key ) == value;
				if ((value.prev.next = value.next) != null) {
					value.next.prev = value.prev;
				}
				value.prev = null;
				this.size--;
			}
		}
	}

	private final void resize(final int newCapacity) {
		
		final CELongWeakLinked2[] oldTable = this.table;
		final int oldCapacity = oldTable.length;
		if (oldCapacity == CILongHashMapCacheEntry2.MAXIMUM_CAPACITY) {
			System.err.println(">>>>>> " + this + ": maximum capacity reached!");
			this.threshold = Integer.MAX_VALUE;
			return;
		}

		final CELongWeakLinked2[] newTable = new CELongWeakLinked2[newCapacity];
		this.transfer(newTable);
		this.table = newTable;
		this.threshold = (int) (newCapacity * this.loadFactor);
	}

	public int size() {
		
		return this.size;
	}

	private void transfer(final CELongWeakLinked2[] newTable) {
		
		final CELongWeakLinked2[] src = this.table;
		final int newCapacity = newTable.length;
		for (int j = src.length - 1; j >= 0; j--) {
			CELongWeakLinked2 e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					final int i = CILongHashMapCacheEntry2.hashNumber(e.value) & newCapacity - 1;
					final CELongWeakLinked2 prev = newTable[i];
					final CELongWeakLinked2 next = e.next;
					if ((e.next = prev) != null) {
						prev.prev = e;
					}
					e.prev = null;
					newTable[i] = e;
					e = next;
				} while (e != null);
			}
		}
	}
}
