package ru.myx.ae3.base;

final class CIIntegerHashExact implements CacheIntegerImplEx {
	
	/**
	 * The maximum capacity, used if a higher value is implicitly specified by
	 * either of the constructors with arguments. MUST be a power of two <=
	 * 1<<30.
	 */
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	private final float loadFactor;

	private transient int size;

	private transient CEIntegerWeakLinked[] table;

	private int threshold;

	CIIntegerHashExact(final int capacity, final float loadFactor) {
		this.table = new CEIntegerWeakLinked[capacity];
		this.loadFactor = loadFactor;
		this.threshold = (int) (this.table.length * this.loadFactor);
	}

	public final CEIntegerWeakLinked cacheGet(final int key) {
		
		for (CEIntegerWeakLinked e = this.table[key & this.table.length - 1]; e != null; e = e.next) {
			if (e.value == key) {
				return e;
			}
		}
		return null;
	}

	@Override
	public final CEIntegerWeakLinked cacheGetCreateInternal(final int key) {
		
		repeat : for (CEIntegerWeakLinked e = this.table[key & this.table.length - 1];;) {
			if (e != null) {
				for (;; e = e.next) {
					if (e.value == key) {
						return e;
					}
					if (e.next == null) {
						final CEIntegerWeakLinked value;
						synchronized (e) {
							if (e.next != null) {
								/**
								 * created while waiting for lock
								 */
								continue;
							}
							value = new CEIntegerWeakLinked(key);
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
			/**
			 * falls only for the empty bucket
			 */
			synchronized (this.table) {
				final int i = key & this.table.length - 1;
				e = this.table[i];
				if (e == null) {
					return this.table[i] = new CEIntegerWeakLinked(key);
				}
			}
			continue repeat;
		}
	}

	@Override
	public final void cacheRemove(final int key, final CEInteger val) {
		
		final CEIntegerWeakLinked value = (CEIntegerWeakLinked) val;
		
		if (value.prev == null) {
			final int i = key & this.table.length - 1;
			synchronized (this.table) {
				assert this.table[i] == value;
				if ((this.table[i] = value.next) != null) {
					value.next.prev = null;
				}
				this.size--;
			}
		} else {
			synchronized (value) {
				assert this.cacheGet(key) == value;
				if ((value.prev.next = value.next) != null) {
					value.next.prev = value.prev;
				}
				value.prev = null;
				this.size--;
			}
		}
	}

	public final void clear() {
		
		final CEIntegerWeakLinked[] tab = this.table;
		for (int i = 0; i < tab.length; i++) {
			tab[i] = null;
		}
		this.size = 0;
	}

	public final boolean containsKey(final int key) {
		
		final int i = key & this.table.length - 1;
		CEIntegerWeakLinked e = this.table[i];
		while (e != null) {
			if (key == e.value) {
				return true;
			}
			e = e.next;
		}
		return false;
	}

	public final boolean containsValue(final Object value) {
		
		if (value == null) {
			return false;
		}
		final CEIntegerWeakLinked[] tab = this.table;
		for (final CEIntegerWeakLinked element : tab) {
			for (CEIntegerWeakLinked e = element; e != null; e = e.next) {
				if (value.equals(e)) {
					return true;
				}
			}
		}
		return false;
	}

	public final CEIntegerWeakLinked get(final int key) {
		
		for (CEIntegerWeakLinked e = this.table[key & this.table.length - 1]; e != null; e = e.next) {
			if (e.value == key) {
				return e;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		
		return this.size == 0;
	}

	public final CEIntegerWeakLinked put(final int key, final CEIntegerWeakLinked value) {
		
		final int i = key & this.table.length - 1;
		CEIntegerWeakLinked e = this.table[i];
		if (e == null) {
			synchronized (this.table) {
				e = this.table[i];
				if (e == null) {
					return this.table[i] = new CEIntegerWeakLinked(key);
				}
			}
		}
		for (;; e = e.next) {
			if (e.value == key) {
				return e;
			}
			if (e.next == null) {
				synchronized (e) {
					if (e.next != null) {
						/**
						 * created while waiting for lock
						 */
						continue;
					}
					this.size++;
					e.next = value;
					value.prev = e;
					if (this.size >= this.threshold) {
						this.resize(2 * this.table.length);
					}
				}
				return value;
			}
		}
	}

	public final CEIntegerWeakLinked remove(final int key) {
		
		final int i = key & this.table.length - 1;
		CEIntegerWeakLinked prev = this.table[i];
		CEIntegerWeakLinked e = prev;

		while (e != null) {
			final CEIntegerWeakLinked next = e.next;
			if (key == e.value) {
				this.size--;
				if (prev == e) {
					this.table[i] = next;
					next.prev = null;
				} else {
					prev.next = next;
					next.prev = prev;
				}
				return e;
			}
			prev = e;
			e = next;
		}

		return e;
	}

	private final void resize(final int newCapacity) {
		
		final CEIntegerWeakLinked[] oldTable = this.table;
		final int oldCapacity = oldTable.length;
		if (oldCapacity == CIIntegerHashExact.MAXIMUM_CAPACITY) {
			this.threshold = Integer.MAX_VALUE;
			return;
		}

		final CEIntegerWeakLinked[] newTable = new CEIntegerWeakLinked[newCapacity];
		this.transfer(newTable);
		this.table = newTable;
		this.threshold = (int) (newCapacity * this.loadFactor);
	}

	public int size() {
		
		return this.size;
	}

	private void transfer(final CEIntegerWeakLinked[] newTable) {
		
		final CEIntegerWeakLinked[] src = this.table;
		final int newCapacity = newTable.length;
		for (int j = src.length - 1; j >= 0; j--) {
			CEIntegerWeakLinked e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					final int i = e.value & newCapacity - 1;
					final CEIntegerWeakLinked prev = newTable[i];
					final CEIntegerWeakLinked next = e.next;
					if ((e.next = prev) != null) {
						prev.prev = e;
					}
					newTable[i] = e;
					e.prev = null;
					e = next;
				} while (e != null);
			}
		}
	}
}
