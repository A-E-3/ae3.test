package ru.myx.ae3.base;

final class CIIntegerHashIntExact extends HashMapPrimitiveIntExact<CEIntegerWeak> implements CacheIntegerImplEx {

	public CIIntegerHashIntExact(final int capacity, final float loadFactor) {
		super(capacity, loadFactor);
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
