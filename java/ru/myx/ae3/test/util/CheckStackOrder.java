package ru.myx.ae3.test.util;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.util.QueueStackRecord;

/**
 * 
 * @author myx
 * 
 */
class CheckStackOrder {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		Engine.createGuid();
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final BaseObject[] test = new BaseObject[] {
				BaseObject.TRUE,
				BaseObject.FALSE,
				BaseObject.NULL,
				BasePrimitiveNumber.LN2,
				BasePrimitiveNumber.E,
				BasePrimitiveNumber.PI,
				BaseString.STR_NAN,
				BaseString.STR_NINFINITY,
				BaseString.STR_ONE,
				BaseString.STR_ZERO,
				BaseString.STR_UNDEFINED,
		//
		};
		final QueueStackRecord<BaseObject> queue = new QueueStackRecord<>();
		for (final BaseObject current : test) {
			queue.push( current );
		}
		final BaseList<Object> result = new BaseNativeArray();
		for (BaseObject next; (next = queue.pop()) != null;) {
			result.baseDefaultPush( next );
		}
		assert test.length == result.size() : "Must have same size";
		for (int i = 0, j = test.length; i < test.length; i++) {
			assert test[i] == result.baseGet( --j, null ) : "Differs at position: " + i;
		}
	}
	
}
