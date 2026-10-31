package ru.myx.ae3.base;

import java.util.Random;

import java.util.function.Function;

/** @author myx */
public enum TestIntegerMaps {
	/**
	 *
	 */
	MAP_HUGE_HASHU16 {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHugeHashU16();
		}
	},
	/**
	 *
	 */
	MAP_HUGE_HASH {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHugeHash();
		}
	},
	/**
	 *
	 */
	MAP_HUGE_TREE {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHugeTree();
		}
	},
	/**
	 *
	 */
	MAP_HASH_MAP_EXACT {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHashIntExact(65536, 3.0f);
		}
	},
	/**
	 *
	 */
	MAP_HASH_MAP_HASHED {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHashIntHashed(65536, 3.0f);
		}
	},
	/**
	 *
	 */
	MAP_HUGE_EXACT {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHugeExact();
		}
	},
	/**
	 *
	 */
	MAP_HASH {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHash(65536, 3.0f);
		}
	},
	/**
	 *
	 */
	MAP_HUGE_HASHED {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHugeHashed();
		}
	},
	/**
	 *
	 */
	DUMMY {

		@Override
		CacheIntegerImplEx createObject() {

			return new CacheIntegerImplEx() {

				@Override
				public CEIntegerWeak cacheGetCreateInternal(final int key) {

					return null;
				}
				
				@Override
				public void cacheRemove(final int key, final CEInteger value) {

					//
				}
			};
		}
	},
	/**
	 *
	 */
	MAP_CONCURRENT {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerHashIntConcurrentExact(65536, 2.0f, 128);
		}
	},
	/**
	 *
	 */
	MAP_TREE {

		@Override
		CacheIntegerImplEx createObject() {

			return new CIIntegerTree();
		}
		
		@Override
		public boolean testSpeed() {

			return false;
		}
		
	},
	
	/**
	 *
	 */
	;
	
	static {
		TestIntegerMaps.testBasic();
	}
	
	private static final void testBasic() {

		/** check some */
		for (final TestIntegerMaps test : TestIntegerMaps.values()) {
			if (test != DUMMY) {
				final CacheIntegerImplEx object = test.createObject();
				final CEInteger result1 = object.cacheGetCreateInternal(3);
				if (result1 == null) {
					throw new IllegalStateException("Have to create!");
				}
				if (object.cacheGetCreateInternal(3) != result1) {
					throw new IllegalStateException("Have to return the same!");
				}
				if (object.cacheGetCreateInternal(3) != result1) {
					throw new IllegalStateException("Have to return created initially!");
				}
				object.cacheRemove(3, result1);
				if (object.cacheGetCreateInternal(3) == result1) {
					throw new IllegalStateException("Have to return new entry!");
				}
				if (object.cacheGetCreateInternal(3) == null) {
					throw new IllegalStateException("Have to create!");
				}
			}
			for (int i = 0; i < 8; i++) {
				try {
					test.createTestOnce(i).apply(null);
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	abstract CacheIntegerImplEx createObject();
	
	/** @param size
	 * @return */
	public Function<Void, Void> createTestOnce(final int size) {

		final Random random = new Random(2206241);
		final int[] ints = new int[size];
		final int[] orderCreate = new int[size];
		final int[] orderRemove = new int[size];
		
		for (int i = 0; i < size; i++) {
			final int value;
			switch (i & 1) {
				case 0 :
					value = Float.floatToRawIntBits((float) random.nextGaussian());
					break;
				case 1 :
					value = random.nextInt();
					break;
				default :
					throw new IllegalStateException("i=" + i + ", b=" + i % 1);
			}
			ints[i] = value;
			orderCreate[i] = orderRemove[i] = i;
		}
		
		for (int i = size * 2 - 1; i >= 0; --i) {
			for (final int[] shuffle : new int[][]{
					orderCreate, orderRemove
			}) {
				final int a = random.nextInt(size);
				final int b = random.nextInt(size);
				final int c = shuffle[a];
				shuffle[a] = shuffle[b];
				shuffle[b] = c;
			}
		}
		
		final CEInteger[] values = new CEInteger[size];
		final CacheIntegerImplEx[] leaves = new CacheIntegerImplEx[]{
				TestIntegerMaps.this.createObject()
		};
		return new Function<>() {

			@Override
			public Void apply(Void arg) {
				
				for (int i1 = 0; i1 < size; i1++) {
					final int index1 = orderCreate[i1];
					/** read and create */
					values[index1] = leaves[0].cacheGetCreateInternal(ints[index1]);
				}
				for (int i2 = 0; i2 < size; i2++) {
					/** read again */
					leaves[0].cacheGetCreate(ints[i2]);
				}
				for (int i3 = 0; i3 < size; i3++) {
					final int index2 = orderRemove[i3];
					/** remove %) */
					leaves[0].cacheRemove(ints[index2], values[index2]);
				}
				return null;
			}
		};
	}
	
	/** @return */
	@SuppressWarnings("static-method")
	public boolean testSpeed() {

		return true;
	}
}
