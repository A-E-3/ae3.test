package ru.myx.ae3.test.help;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.exec.ExecArguments;
import ru.myx.ae3.exec.ExecArgumentsEmpty;
import ru.myx.ae3.exec.ExecArgumentsList1;
import ru.myx.ae3.help.Convert;

class TestHelpConvert {
	
	/** @param args
	 * @throws Exception */
	public static void main(final String[] args) throws Exception {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST AE3 HELP CONVERT");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			/** misses */
			for (final byte b : new byte[]{
					0, 1, 2, 3, 4, 5, 6, 7, 8, 9
			}) {
				final Object o = null;

				assert b == Convert.Any.toByte(o, b);
				assert b == Convert.Any.toShort(o, b);
				assert b == Convert.Any.toInt(o, b);
				assert b == Convert.Any.toLong(o, b);
				assert b == Convert.Any.toFloat(o, b);
				assert b == Convert.Any.toDouble(o, b);

				assert b != 0 == Convert.Any.toBoolean(o, b != 0);

				for (final List<?> list : new List<?>[]{
						Collections.singletonList(null), Collections.emptyList(), null
				}) {
					assert b == Convert.ListEntry.toByte(list, 0, b);
					assert b == Convert.ListEntry.toShort(list, 0, b);
					assert b == Convert.ListEntry.toInt(list, 0, b);
					assert b == Convert.ListEntry.toLong(list, 0, b);
					assert b == Convert.ListEntry.toFloat(list, 0, b);
					assert b == Convert.ListEntry.toDouble(list, 0, b);

					assert b != 0 == Convert.ListEntry.toBoolean(list, 0, b != 0);

					assert null == Convert.ListEntry.toObject(list, 0, null);
					assert null == Convert.ListEntry.toString(list, 0, null);

					final String string = "aaa";
					assert string == Convert.ListEntry.toObject(list, 0, string);
					assert string == Convert.ListEntry.toString(list, 0, string);
				}

				for (final ExecArguments list : new ExecArguments[]{
						ExecArgumentsEmpty.INSTANCE, null
				}) {
					// assert b == Convert.ListEntry.toByte( list, 0, b );
					// assert b == Convert.ListEntry.toShort( list, 0, b );
					assert b == Convert.ListEntry.toInt(list, 0, b);
					assert b == Convert.ListEntry.toLong(list, 0, b);
					assert b == Convert.ListEntry.toFloat(list, 0, b);
					assert b == Convert.ListEntry.toDouble(list, 0, b);

					assert b != 0 == Convert.ListEntry.toBoolean(list, 0, b != 0);

					assert null == Convert.ListEntry.toObject(list, 0, null);
					assert null == Convert.ListEntry.toString(list, 0, null);

					final String string = "aaa";
					assert string == Convert.ListEntry.toObject(list, 0, string);
					assert string == Convert.ListEntry.toString(list, 0, string);
				}

				for (final Map<?, ?> map : new Map<?, ?>[]{
						null, new TreeMap<String, Object>(), new BaseNativeObject()
				}) {
					assert b == Convert.MapEntry.toByte(map, "a", b);
					assert b == Convert.MapEntry.toShort(map, "a", b);
					assert b == Convert.MapEntry.toInt(map, "a", b);
					assert b == Convert.MapEntry.toLong(map, "a", b);
					assert b == Convert.MapEntry.toFloat(map, "a", b);
					assert b == Convert.MapEntry.toDouble(map, "a", b);

					assert b != 0 == Convert.MapEntry.toBoolean(map, "a", b != 0);

					assert null == Convert.MapEntry.toObject(map, "a", null);
					assert null == Convert.MapEntry.toString(map, "a", null);

					final String string = "aaa";
					assert string == Convert.MapEntry.toObject(map, "a", string);
					assert string == Convert.MapEntry.toString(map, "a", string);
				}

				for (final BaseObject map : new BaseObject[]{
						null, new BaseNativeObject()
				}) {
					assert b == Convert.MapEntry.toByte(map, "a", b);
					assert b == Convert.MapEntry.toShort(map, "a", b);
					assert b == Convert.MapEntry.toInt(map, "a", b);
					assert b == Convert.MapEntry.toLong(map, "a", b);
					assert b == Convert.MapEntry.toFloat(map, "a", b);
					assert b == Convert.MapEntry.toDouble(map, "a", b);

					assert b != 0 == Convert.MapEntry.toBoolean(map, "a", b != 0);

					assert null == Convert.MapEntry.toObject(map, "a", null);
					assert null == Convert.MapEntry.toString(map, "a", null);

					final String string = "aaa";
					assert string == Convert.MapEntry.toObject(map, "a", string);
					assert string == Convert.MapEntry.toString(map, "a", string);
				}

				for (final BaseMap map : new BaseMap[]{
						null, new BaseNativeObject()
				}) {
					assert b == Convert.MapEntry.toByte(map, "a", b);
					assert b == Convert.MapEntry.toShort(map, "a", b);
					assert b == Convert.MapEntry.toInt(map, "a", b);
					assert b == Convert.MapEntry.toLong(map, "a", b);
					assert b == Convert.MapEntry.toFloat(map, "a", b);
					assert b == Convert.MapEntry.toDouble(map, "a", b);

					assert b != 0 == Convert.MapEntry.toBoolean(map, "a", b != 0);

					assert null == Convert.MapEntry.toObject(map, "a", null);
					assert null == Convert.MapEntry.toString(map, "a", null);

					final String string = "aaa";
					assert string == Convert.MapEntry.toObject(map, "a", string);
					assert string == Convert.MapEntry.toString(map, "a", string);
				}
			}
			/** hits */
			for (final byte b : new byte[]{
					0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 30, 40, 50
			}) {
				for (final Object o : new Object[]{
						Byte.valueOf(b), Short.valueOf(b), Integer.valueOf(b), Long.valueOf(b), Float.valueOf(b), Double.valueOf(b), new AtomicInteger(b), new AtomicLong(b), String.valueOf(b),
						// decimal spacer
						"0 " + String.valueOf(b), "0x" + Integer.toHexString(b),
						//
				}) {
					assert b == Convert.Any.toByte(o, (byte) -1);
					assert b == Convert.Any.toShort(o, (byte) -1);
					assert b == Convert.Any.toInt(o, (byte) -1);
					assert b == Convert.Any.toLong(o, (byte) -1);
					assert b == Convert.Any.toFloat(o, (byte) -1);
					assert b == Convert.Any.toDouble(o, (byte) -1);

					assert b != 0 == Convert.Any.toBoolean(o, false);
					assert b != 0 == Convert.Any.toBoolean(o, true);

					assert b != 0 == Convert.Any.toBoolean(Base.forUnknown(o), false);
					assert b != 0 == Convert.Any.toBoolean(Base.forUnknown(o), true);

					{
						final String string = String.valueOf(o);
						/** hits */
						assert 0 == Convert.Any.toInt(string, new String[]{
								string
						}, -1);
						assert 0 == Convert.Any.toInt(string, new String[]{
								string, ""
						}, -1);
						assert 1 == Convert.Any.toInt(string, new String[]{
								"", string
						}, -1);
						assert 0 == Convert.Any.toInt(string, new String[]{
								String.valueOf(o)
						}, -1);
						assert 0 == Convert.Any.toInt(string, new String[]{
								String.valueOf(o), ""
						}, -1);
						assert 1 == Convert.Any.toInt(string, new String[]{
								"", String.valueOf(o)
						}, -1);

						/** misses */
						assert b == Convert.Any.toInt(string, new String[0], b);
						assert b == Convert.Any.toInt(string, new String[]{
								"1234"
						}, b);
						assert b == Convert.Any.toInt(string, new String[]{
								"2345", ""
						}, b);
						assert b == Convert.Any.toInt(null, new String[0], b);
						assert b == Convert.Any.toInt(null, new String[]{
								string
						}, b);
						assert b == Convert.Any.toInt(null, new String[]{
								"", string
						}, b);
						assert b == Convert.Any.toInt(null, new String[]{
								String.valueOf(o)
						}, b);
						assert b == Convert.Any.toInt(null, new String[]{
								"", String.valueOf(o)
						}, b);

					}

					{
						final List<?> list = Collections.singletonList(o);
						assert list.size() == 1;

						assert b == Convert.ListEntry.toByte(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toShort(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toInt(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toLong(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toFloat(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toDouble(list, 0, (byte) -1);

						assert b != 0 == Convert.ListEntry.toBoolean(list, 0, false);
						assert b != 0 == Convert.ListEntry.toBoolean(list, 0, true);
					}

					{
						final ExecArguments list = new ExecArgumentsList1(Base.forUnknown(o));
						assert list.length() == 1;

						// assert b == Convert.ListEntry.toByte( list, 0, (byte)
						// -1 );
						// assert b == Convert.ListEntry.toShort( list, 0,
						// (byte) -1 );
						assert b == Convert.ListEntry.toInt(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toLong(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toFloat(list, 0, (byte) -1);
						assert b == Convert.ListEntry.toDouble(list, 0, (byte) -1);

						assert b != 0 == Convert.ListEntry.toBoolean(list, 0, false);
						assert b != 0 == Convert.ListEntry.toBoolean(list, 0, true);
					}

					{
						final Map<String, Object> map = new TreeMap<>();
						map.put("a", o);
						assert map.size() == 1;

						assert b == Convert.MapEntry.toByte(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toShort(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toInt(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toLong(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toFloat(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toDouble(map, "a", (byte) -1);

						assert b != 0 == Convert.MapEntry.toBoolean(map, "a", false);
						assert b != 0 == Convert.MapEntry.toBoolean(map, "a", true);
					}

					{
						final BaseObject map = new BaseNativeObject()//
								.putAppend("a", Base.forUnknown(o))//
						;
						assert map.baseGetOwnProperty("a") != null;

						assert b == Convert.MapEntry.toByte(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toShort(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toInt(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toLong(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toFloat(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toDouble(map, "a", (byte) -1);

						assert b != 0 == Convert.MapEntry.toBoolean(map, "a", false);
						assert b != 0 == Convert.MapEntry.toBoolean(map, "a", true);
					}

					{
						final BaseMap map = new BaseNativeObject();
						map.put("a", o);
						assert map.size() == 1;

						assert b == Convert.MapEntry.toByte(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toShort(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toInt(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toLong(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toFloat(map, "a", (byte) -1);
						assert b == Convert.MapEntry.toDouble(map, "a", (byte) -1);

						assert b != 0 == Convert.MapEntry.toBoolean(map, "a", false);
						assert b != 0 == Convert.MapEntry.toBoolean(map, "a", true);
					}
				}
			}
		}
		{
			/** true */
			for (final Object o : new Object[]{
					BaseObject.TRUE, Boolean.TRUE, "true", "yes", "1", "1.0", Integer.valueOf(1),
			}) {
				final int ci;
				ci = 1;

				assert ci == Convert.Any.toByte(o, (byte) -1);
				assert ci == Convert.Any.toShort(o, (byte) -1);
				assert ci == Convert.Any.toInt(o, (byte) -1);
				assert ci == Convert.Any.toLong(o, (byte) -1);
				assert ci == Convert.Any.toFloat(o, (byte) -1);
				assert ci == Convert.Any.toDouble(o, (byte) -1);

				assert ci != 0 == Convert.Any.toBoolean(o, false);
				assert ci != 0 == Convert.Any.toBoolean(o, true);

				assert ci != 0 == Convert.Any.toBoolean(Base.forUnknown(o), false);
				assert ci != 0 == Convert.Any.toBoolean(Base.forUnknown(o), true);

				{
					final List<?> list = Collections.singletonList(o);

					assert ci == Convert.ListEntry.toByte(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toShort(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toInt(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toLong(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toFloat(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toDouble(list, 0, (byte) -1);

					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, false);
					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, true);
				}

				{
					final ExecArguments list = new ExecArgumentsList1(Base.forUnknown(o));
					assert list.length() == 1;

					// assert ci == Convert.ListEntry.toByte( list, 0, (byte) -1
					// );
					// assert ci == Convert.ListEntry.toShort( list, 0, (byte)
					// -1 );
					assert ci == Convert.ListEntry.toInt(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toLong(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toFloat(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toDouble(list, 0, (byte) -1);

					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, false);
					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, true);
				}

				{
					final Map<String, Object> map = new TreeMap<>();
					map.put("a", o);
					assert map.size() == 1;

					assert ci == Convert.MapEntry.toByte(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toShort(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toInt(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toLong(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toFloat(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toDouble(map, "a", (byte) -1);

					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", false);
					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", true);
				}

				{
					final BaseObject map = new BaseNativeObject()//
							.putAppend("a", Base.forUnknown(o))//
					;
					assert map.baseGetOwnProperty("a") != null;

					assert ci == Convert.MapEntry.toByte(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toShort(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toInt(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toLong(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toFloat(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toDouble(map, "a", (byte) -1);

					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", false);
					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", true);
				}

				{
					final BaseMap map = new BaseNativeObject();
					map.put("a", o);
					assert map.size() == 1;

					assert ci == Convert.MapEntry.toByte(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toShort(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toInt(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toLong(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toFloat(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toDouble(map, "a", (byte) -1);

					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", false);
					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", true);
				}
			}
			/** false */
			for (final Object o : new Object[]{
					BaseObject.FALSE, Boolean.FALSE, "false", "no", "0", "0.0", Integer.valueOf(0),
					//
			}) {
				final int ci;
				ci = 0;

				assert ci == Convert.Any.toByte(o, (byte) -1);
				assert ci == Convert.Any.toShort(o, (byte) -1);
				assert ci == Convert.Any.toInt(o, (byte) -1);
				assert ci == Convert.Any.toLong(o, (byte) -1);
				assert ci == Convert.Any.toFloat(o, (byte) -1);
				assert ci == Convert.Any.toDouble(o, (byte) -1);

				assert ci != 0 == Convert.Any.toBoolean(o, false);
				assert ci != 0 == Convert.Any.toBoolean(o, true);

				assert ci != 0 == Convert.Any.toBoolean(Base.forUnknown(o), false);
				assert ci != 0 == Convert.Any.toBoolean(Base.forUnknown(o), true);

				{
					final List<?> list = Collections.singletonList(o);

					assert ci == Convert.ListEntry.toByte(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toShort(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toInt(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toLong(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toFloat(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toDouble(list, 0, (byte) -1);

					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, false);
					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, true);
				}

				{
					final ExecArguments list = new ExecArgumentsList1(Base.forUnknown(o));
					assert list.length() == 1;

					// assert ci == Convert.ListEntry.toByte( list, 0, (byte) -1
					// );
					// assert ci == Convert.ListEntry.toShort( list, 0, (byte)
					// -1 );
					assert ci == Convert.ListEntry.toInt(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toLong(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toFloat(list, 0, (byte) -1);
					assert ci == Convert.ListEntry.toDouble(list, 0, (byte) -1);

					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, false);
					assert ci != 0 == Convert.ListEntry.toBoolean(list, 0, true);
				}

				{
					final Map<String, Object> map = new TreeMap<>();
					map.put("a", o);
					assert map.size() == 1;

					assert ci == Convert.MapEntry.toByte(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toShort(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toInt(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toLong(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toFloat(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toDouble(map, "a", (byte) -1);

					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", false);
					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", true);
				}

				{
					final BaseObject map = new BaseNativeObject()//
							.putAppend("a", Base.forUnknown(o))//
					;
					assert map.baseGetOwnProperty("a") != null;

					assert ci == Convert.MapEntry.toByte(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toShort(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toInt(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toLong(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toFloat(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toDouble(map, "a", (byte) -1);

					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", false);
					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", true);
				}

				{
					final BaseMap map = new BaseNativeObject();
					map.put("a", o);
					assert map.size() == 1;

					assert ci == Convert.MapEntry.toByte(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toShort(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toInt(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toLong(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toFloat(map, "a", (byte) -1);
					assert ci == Convert.MapEntry.toDouble(map, "a", (byte) -1);

					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", false);
					assert ci != 0 == Convert.MapEntry.toBoolean(map, "a", true);
				}
			}
		}
		System.out.println(">>>> >>>> TEST AE3 HELP CONVERT done.");
	}
}
