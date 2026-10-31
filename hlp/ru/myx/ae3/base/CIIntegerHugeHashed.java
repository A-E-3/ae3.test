package ru.myx.ae3.base;

final class CIIntegerHugeHashed implements CacheIntegerImplEx {
	
	private final CIIntegerHashIntHashed[] BUCKET = new CIIntegerHashIntHashed[65536];
	
	private final int BUCKET_MASK = this.BUCKET.length - 1;
	
	CIIntegerHugeHashed() {
		for (int i = this.BUCKET_MASK; i >= 0; --i) {
			this.BUCKET[i] = new CIIntegerHashIntHashed(32, 4.0f);
		}
	}
	
	@Override
	public CEIntegerWeak cacheGetCreateInternal(final int key) {

		return this.BUCKET[key & this.BUCKET_MASK].cacheGetCreateInternal(key);
	}
	
	@Override
	public void cacheRemove(final int key, final CEInteger value) {

		this.BUCKET[key & this.BUCKET_MASK].cacheRemove(key, value);
	}
	//
}
