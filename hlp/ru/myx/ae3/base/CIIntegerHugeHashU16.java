package ru.myx.ae3.base;

final class CIIntegerHugeHashU16 implements CacheIntegerImplEx {
	
	private static final int BUCKET_SIZE = 65536;
	
	private static final int BUCKET_MASK = CIIntegerHugeHashU16.BUCKET_SIZE - 1;
	static {
		assert 0 < CIIntegerHugeHashU16.BUCKET_SIZE;
		assert !(0 > (CIIntegerHugeHashU16.BUCKET_SIZE & CIIntegerHugeHashU16.BUCKET_MASK));
		assert !(0 < (CIIntegerHugeHashU16.BUCKET_SIZE & CIIntegerHugeHashU16.BUCKET_MASK));
	}
	
	private final CIIntegerHashU16[] bucket = new CIIntegerHashU16[CIIntegerHugeHashU16.BUCKET_SIZE];
	
	CIIntegerHugeHashU16() {
		for (int i = CIIntegerHugeHashU16.BUCKET_MASK; i >= 0; --i) {
			this.bucket[i] = new CIIntegerHashU16(32, 2.0f);
		}
	}
	
	@Override
	public final CEIntegerWeakLinked cacheGetCreateInternal(final int key) {

		return this.bucket[key & CIIntegerHugeHashU16.BUCKET_MASK].cacheGetCreateInternal(key);
	}
	
	@Override
	public final void cacheRemove(final int key, final CEInteger value) {

		this.bucket[key & CIIntegerHugeHashU16.BUCKET_MASK].cacheRemove(key, value);
	}
	//
}
