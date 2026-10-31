package ru.myx.ae3.base;

import ru.myx.util.HashMapPrimitiveInt;

@SuppressWarnings("serial")
final class CIIntegerHashIntHashed extends HashMapPrimitiveInt<CEIntegerWeak> implements CacheIntegerImplEx {

	public CIIntegerHashIntHashed(final int capacity, final float loadFactor) {
		super(capacity, loadFactor);
	}
	
	public CEIntegerWeak cacheGet(final int key) {

		return this.get(key);
	}
	
	@Override
	public CEIntegerWeak cacheGetCreateInternal(final int key) {

		CEIntegerWeak result;
		{
			result = this.get(key);
			if (result != null) {
				return result;
			}
		}
		synchronized (this) {
			result = this.get(key);
			if (result != null) {
				return result;
			}
			this.put(key, result = new CEIntegerWeak(key));
		}
		return result;
	}
	
	@Override
	public void cacheRemove(final int key, final CEInteger value) {

		synchronized (this) {
			this.remove(key);
		}
	}
	
}
