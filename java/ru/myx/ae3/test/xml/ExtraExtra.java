package ru.myx.ae3.test.xml;

import ru.myx.ae3.extra.AbstractExtra;
import ru.myx.ae3.extra.External;
import ru.myx.ae3.extra.ExtraBinary;

final class ExtraExtra extends AbstractExtra {
	private final ExtraBinary	extra;
	
	ExtraExtra(final String recId, final ExtraBinary extra) {
		super( recId );
		this.extra = extra;
	}
	
	@Override
	public Object baseValue() {
		final External extra = this.getExtra();
		return extra != null
				? extra.baseValue()
				: null;
	}
	
	public External getExtra() {
		return this.extra;
	}
	
	@Override
	public long getRecordDate() {
		return this.extra.getRecordDate();
	}
	
	@Override
	public Object getRecordIssuer() {
		return this.extra.getRecordIssuer();
	}
	
	@Override
	public Object toBinary() {
		return this.extra.toBinary();
	}
	
	@Override
	public final String toString() {
		return String.valueOf( this.getExtra() );
	}
}
