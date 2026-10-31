package ru.myx.ae3.base;

import java.util.Random;

import java.util.function.Function;

/** @author myx */
public enum TestDoubleMaps {
	/**
	 *
	 */
	MAP_PRIMITIVE_DOUBLE_4F {

		@Override
		CacheDoubleImplEx createObject() {

			return new CIDoubleHashMap(16384, 4.0f);
		}
	},
	/**
	 *
	 */
	MAP_PRIMITIVE_DOUBLE_1F {

		@Override
		CacheDoubleImplEx createObject() {

			return new CIDoubleHashMap(16384, 4.0f);
		}
	},
	/**
	 *
	 */
	DUMMY {

		@Override
		CacheDoubleImplEx createObject() {

			return new CacheDoubleImplEx() {
				
				@Override
				public CacheNumber cacheGetCreateInternal(final double key) {

					return null;
				}
				
				@Override
				public void cachePutInternal(final double key, final CacheNumber value) {

					//
				}
				
				@Override
				public void cacheRemove(final double key, final CacheNumber value) {

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
		TestDoubleMaps.testBasic();
	}
	
	private static final void testBasic() {

		/** check some */
		for (final TestDoubleMaps test : TestDoubleMaps.values()) {
			if (test != DUMMY) {
				final CacheDoubleImplEx object = test.createObject();
				final CacheNumber result1 = object.cacheGetCreateInternal(3);
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
	
	abstract CacheDoubleImplEx createObject();
	
	/** @param size
	 * @return */
	public Function<Void, Void> createTestOnce(final int size) {

		final Random random = new Random(2206241);
		final double[] ints = new double[size];
		final int[] orderCreate = new int[size];
		final int[] orderRemove = new int[size];
		
		for (int i = 0; i < size; i++) {
			orderCreate[i] = orderRemove[i] = i;
			final double value;
			switch (i & 1) {
				case 0 :
					value = random.nextGaussian();
					break;
				case 1 :
					value = random.nextDouble();
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
		
		final CacheNumber[] values = new CacheNumber[size];
		final CacheDoubleImplEx[] leafs = new CacheDoubleImplEx[]{
				TestDoubleMaps.this.createObject()
		};
		return new Function<>() {

			@Override
			public Void apply(Void arg) {
				
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
	@SuppressWarnings("static-method")
	public boolean testSpeed() {

		return true;
	}
}
