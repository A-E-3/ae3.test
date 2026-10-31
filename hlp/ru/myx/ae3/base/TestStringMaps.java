package ru.myx.ae3.base;

import java.util.Random;
import java.util.function.Function;

/** @author myx */
public enum TestStringMaps {
	/**
	 *
	 */
	HASH_BUCKET {

		@Override
		CacheStringImplEx createObject() {

			return new CIStringBuckets();
		}
	},
	/**
	 *
	 */
	HASH_MAP_4F {

		@Override
		CacheStringImplEx createObject() {

			return new CIStringHashMap(65536, 4.0f);
		}
	},
	/**
	 *
	 */
	HASH_MAP_1F {

		@Override
		CacheStringImplEx createObject() {

			return new CIStringHashMap(65536, 1.0f);
		}
	},
	/**
	 *
	 */
	HASH_MAP_05F {

		@Override
		CacheStringImplEx createObject() {

			return new CIStringHashMap(65536, 0.5f);
		}
	},
	/**
	 *
	 */
	DUMMY {

		@Override
		CacheStringImplEx createObject() {

			return new CacheStringImplEx() {

				@Override
				public BasePrimitiveString cacheGetCreate(final String key) {

					return null;
				}

				@Override
				public CEString cacheGetCreateInternal(final String key) {

					return null;
				}

				@Override
				public void cachePutInternal(final BasePrimitiveString value) {

					//
				}

				@Override
				public void cacheRemove(final String key, final CEString value) {

					//
				}
			};
		}
	},

	/**
	 *
	 */
	;

	static {
		TestStringMaps.testBasic();
	}

	private static final void testBasic() {

		/** check some */
		for (final TestStringMaps test : TestStringMaps.values()) {
			if (test != DUMMY) {
				final CacheStringImplEx cacheImpl = test.createObject();
				final CEString result1 = cacheImpl.cacheGetCreateInternal("33");
				if (result1 == null) {
					throw new IllegalStateException("Have to create!");
				}
				if (cacheImpl.cacheGetCreateInternal("33") != result1) {
					throw new IllegalStateException("Have to return the same!");
				}
				if (cacheImpl.cacheGetCreateInternal("33") != result1) {
					throw new IllegalStateException("Have to return created initially!");
				}
				cacheImpl.cacheRemove("33", result1);
				if (cacheImpl.cacheGetCreateInternal("33") == result1) {
					throw new IllegalStateException("Have to return new entry!");
				}
				if (cacheImpl.cacheGetCreateInternal("33") == null) {
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

	abstract CacheStringImplEx createObject();

	/** @param size
	 * @return */
	public Function<Void, Void> createTestOnce(final int size) {

		final Random random = new Random(2206241);
		final String[] ints = new String[size];
		final int[] orderCreate = new int[size];
		final int[] orderRemove = new int[size];

		for (int i = 0; i < size; i++) {
			orderCreate[i] = orderRemove[i] = i;
			final String value;
			switch (i & 1) {
				case 0 :
					value = String.valueOf((float) random.nextGaussian());
					break;
				case 1 :
					value = String.valueOf(random.nextFloat());
					break;
				default :
					throw new IllegalStateException("i=" + i + ", b=" + i % 1);
			}
			ints[i] = value;
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

		final CEString[] values = new CEString[size];
		final CacheStringImplEx[] leafs = new CacheStringImplEx[]{
				TestStringMaps.this.createObject()
		};
		return new Function<>() {

			@Override
			public Void apply(final Void arg) {
				
				for (int i1 = 0; i1 < size; i1++) {
					final int index1 = orderCreate[i1];
					/** read and create */
					values[index1] = leafs[0].cacheGetCreateInternal(ints[index1]);
				}
				for (int i2 = 0; i2 < size; i2++) {
					/** read again */
					leafs[0].cacheGetCreate(ints[i2]);
				}
				for (int i3 = 0; i3 < size; i3++) {
					final int index2 = orderRemove[i3];
					/** remove %) */
					leafs[0].cacheRemove(ints[index2], values[index2]);
				}
				return null;
			}
		};
	}

	/** @return */
	public boolean testSpeed() {

		return true;
	}
}
