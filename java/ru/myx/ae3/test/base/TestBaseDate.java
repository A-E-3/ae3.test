package ru.myx.ae3.test.base;

import java.util.Date;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseDate;
import ru.myx.ae3.base.BaseObject;

/**
 * @author myx
 * 
 */
class TestBaseDate {
	
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		
		
		Engine.createGuid();
		System.out.println(">>>> TEST BASE DATE");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final BaseDate date = Base.forDateMillis(1L);
			assert date != null : "Should not be NULL";
			assert date.basePrototype() == BaseDate.PROTOTYPE : "BaseDate.PROTOTYPE expected";
			assert date.baseGet("getTime", BaseObject.UNDEFINED).baseCall() != null : "getTime method expected!";
			assert date.baseGet("toString", BaseObject.UNDEFINED).baseCall() != null : "toString method expected!";
			assert date.baseGet("toUTCString", BaseObject.UNDEFINED).baseCall() != null : "toUTCString method expected!";
			assert date.baseGet("UTC", BaseObject.UNDEFINED).baseCall() != null : "UTC method expected!";
			
			// assert date.baseGet( "toDateString" ).baseCall() != null :
			// "toDateString method expected!";
			
			// assert date.baseGet( "toTimeString" ).baseCall() != null :
			// "toTimeString method expected!";
			
			assert date.baseGet("setTime", BaseObject.UNDEFINED).baseCall() != null : "setTime method expected!";
		}
		{
			final BaseDate date = Base.forDateMillis(1L);
			assert date != null : "Should not be NULL";
			assert date.getTime() == 1L : "Time is wrong";
			assert date.baseToNumber().longValue() == 1L : "Time (toNumber) is wrong: " + date.baseToNumber();
			
			// sorry, timezone can really change
			assert "1970.01.01".equals(date.toString()) //
			: date.toString();
			
			assert "1970.01.01".equals(date.baseToJavaString()) //
			: date.baseToJavaString();
			
			// cannot change, web format
			assert "Thu, 01 Jan 1970 00:00:00 GMT".equals(date.toUTCString()) //
			: date.toUTCString();
			
			// YYYY-MM-DDTHH:mm:ss.sssZ
			assert "1970-01-01T00:00:00.001Z".equals(date.toISOString()) //
			: date.toISOString();
		}
		{
			final BaseDate date = Base.forDate(new Date(1L));
			assert date != null : "Should not be NULL";
			assert date.getTime() == 1L : "Time is wrong";
			assert date.baseToNumber().longValue() == 1L : "Time (toNumber) is wrong: " + date.baseToNumber();
			
			// sorry, timezone can really change
			assert "1970.01.01".equals(date.toString()) //
			: date.toString();
			
			assert "1970.01.01".equals(date.baseToJavaString()) //
			: date.baseToJavaString();
			
			// cannot change, web format
			assert "Thu, 01 Jan 1970 00:00:00 GMT".equals(date.toUTCString()) //
			: date.toUTCString();
			
			// sorry, timezone can really change
			// YYYY-MM-DDTHH:mm:ss.sssZ
			assert "1970-01-01T00:00:00.001Z".equals(date.toISOString()) //
			: date.toISOString();
		}
		{
			final BaseObject date = Base.forUnknown((Object) new Date(1L));
			assert date != null : "Should not be NULL";
			assert date instanceof Date : "Should be instance of Date";
			assert date.baseValue() instanceof Date : "Should be instance of Date";
		}
	}
}
