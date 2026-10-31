package ru.myx.ae3.base;

class CILongHashMapCacheEntryFixed implements CacheLongImplEx {
	
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

		return CILongHashMapCacheEntryFixed.hashNumber((int) (key ^ key >>> 32));
	}
	
	private transient int size;
	
	private final transient CELongWeakLinked2[] table;
	
	CILongHashMapCacheEntryFixed(final int capacity) {
		this.table = new CELongWeakLinked2[capacity];
	}
	
	@Override
	public final CELongWeakLinked2 cacheGetCreateInternal(final long key) {

		final int hash = CILongHashMapCacheEntryFixed.hashNumber(key);
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
				}
				return value;
			}
		}
	}
	
	@Override
	public void cacheRemove(final long key, final CELongWeak val) {
		
		final CELongWeakLinked2 value = (CELongWeakLinked2) val;

		if (value.prev == null) {
			final int i = CILongHashMapCacheEntryFixed.hashNumber(key) & this.table.length - 1;
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
	
	public int size() {

		return this.size;
	}
}
