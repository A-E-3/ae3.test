package ru.myx.ae3.test.know;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.know.Guid;
import ru.myx.ae3.know.GuidStream;
import ru.myx.ae3.test.TestAe3;

/**
 * @author myx
 * 		
 */
class TestGuidStream {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		System.out.println(">>>> >>>> TEST KNOW AE3 GUID STREAM");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		
		TestAe3.initAE3();
		
		for (final boolean detach : new boolean[]{
				false, true
		}) {
			final Map<Guid, BaseObject> check = new TreeMap<>();
			for (final BaseObject object : new BaseObject[]{
					null, //
					BaseObject.NULL, //
					BaseObject.UNDEFINED, //
					BaseObject.TRUE, //
					BaseObject.FALSE, //
					Base.forDateMillis(0L), //
					Base.forDateMillis(System.currentTimeMillis()), //
					Base.forInteger(0), //
					Base.forInteger(5), //
					Base.forInteger(-5), //
					Base.forDouble(5.1), //
					Base.forDouble(-5.1), //
					Base.forDouble(5.2), //
					Base.forDouble(-5.2), //
					BasePrimitiveNumber.ONE, BasePrimitiveNumber.PINF, BasePrimitiveNumber.NINF, Base.forString(""), //
					Transfer.createCopier("".getBytes(StandardCharsets.US_ASCII)), //
					Base.forString("a"), //
					Transfer.createCopier("a".getBytes(StandardCharsets.US_ASCII)), //
					Base.forString("ab"), //
					Transfer.createCopier("ab".getBytes(StandardCharsets.US_ASCII)), //
					Base.forString("abc"), //
					Transfer.createCopier("abc".getBytes(StandardCharsets.US_ASCII)), //
					Base.forString("abcdef"), //
					Transfer.createCopier("abcdef".getBytes(StandardCharsets.US_ASCII)), //
					Base.forString("abcdeЯ"), //
					Transfer.createCopier("abcdeЯ".getBytes(StandardCharsets.UTF_8)), //
					Base.forString("abc0123456789-ABCDEFG-def"), //
					Transfer.createCopier("abc0123456789-ABCDEFG-def".getBytes(StandardCharsets.US_ASCII)), //
					Base.forString("abc0123456789-ABCDEFG-deЯ"), //
					Transfer.createCopier("abc0123456789-ABCDEFG-deЯ".getBytes(StandardCharsets.UTF_8)), //
					//
					//
					new BaseNativeObject(), //
					/**
					 * <code>
					new BaseNativeObject() //
							.putAppend( "test", 0 ), //
					new BaseNativeObject() //
							.putAppend( "test", 0 ) //
							.putAppend( "test2", 2 ), //
					</code>
					 */
					//
					//
					new BaseNativeArray(), //
					/**
					 * <code>
					new BaseNativeArray() //
							.putAppend( BasePrimitiveNumber.LN10 ), //
					new BaseNativeArray() //
							.putAppend( BasePrimitiveNumber.LN10 ) //
							.putAppend( BasePrimitiveNumber.LN2 ), //
					new BaseNativeArray() //
							.putAppend( BasePrimitiveNumber.LN10 ) //
							.putAppend( BasePrimitiveNumber.LN2 ) //
							.putAppend( BasePrimitiveNumber.PI ), //
					</code>
					 */
					//
			}) {
				if (object == null) {
					continue;
				}
				final Guid guid1 = GuidStream.toUniqueIdentifier(object, detach);
				System.out.println(
						">> >> >> base=" + Ecma.toEcmaSourceCompact(object) + ", guid=" + guid1 + ", isBinary=" + guid1.isBinary() + ", isCollection=" + guid1.isCollection()
								+ ", isExternal=" + guid1.isExternal() + ", isPrimitive=" + guid1.isPrimitive() + ", isInline=" + (guid1.isInline()
									? "true, inlineClass=" + guid1.getInlineBaseValue().getClass().getName() + ", inline=" + guid1.getInlineBaseValue()
									: "false"));
				assert guid1.isInline() : "Expected to be inline: element = " + Format.Describe.toEcmaSource(object, "");
				final Guid guid2 = GuidStream.toUniqueIdentifier(object, detach);
				assert guid1.equals(guid2);
				assert guid2.equals(guid1);
				assert guid1 == Guid.GUID_JS_OBJECT_EMPTY || !check.containsKey(guid1) : "oops, " + "\n\tguid=" + guid1 + "\n\tclass=" + object.getClass().getName() + "\n\tvalue="
						+ Ecma.toEcmaSourceCompact(object) + "\n\tsameClass=" + check.get(guid1).getClass().getName() + "\n\tsameValue="
						+ Ecma.toEcmaSourceCompact(check.get(guid1));
				check.put(guid1, object);
			}
			for (final BaseObject object : new BaseObject[]{
					null, //
					Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop"), //
					//
					//
					new BaseNativeObject() //
							.putAppend("tEst", Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop")), //
					new BaseNativeObject() //
							.putAppend(
									"tEst-electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop",
									Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop")), //
					new BaseNativeObject()
							//
							.putAppend("tEst", Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop")) //
							.putAppend("tEst111", Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop111111")), //
					//
					//
					//
					new BaseNativeArray() //
							.putAppend(Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop")), //
					new BaseNativeArray()
							//
							.putAppend(Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop"))
							.putAppend(Base.forString("electrolux_dlya_electrolux_zvq_2100,_2101__viva_quick_stop111111")), //
					//
			}) {
				if (object == null) {
					continue;
				}
				final Guid guid1 = GuidStream.toUniqueIdentifier(object, detach);
				System.out.println(
						">> >> >> base=" + Ecma.toEcmaSourceCompact(object) + ", guid=" + guid1 + ", isBinary=" + guid1.isBinary() + ", isCollection=" + guid1.isCollection()
								+ ", isExternal=" + guid1.isExternal() + ", isPrimitive=" + guid1.isPrimitive() + ", isInline=" + (guid1.isInline()
									? "true, inlineClass=" + guid1.getInlineBaseValue().getClass().getName() + ", inline=" + guid1.getInlineBaseValue()
									: "false"));
				assert !guid1.isInline() : "Expected to be NOT inline!";
				final Guid guid2 = GuidStream.toUniqueIdentifier(object, detach);
				assert guid1.equals(guid2);
				assert guid2.equals(guid1);
				assert !check.containsKey(guid1) : "oops, " + "\n\tguid=" + guid1 + "\n\tclass=" + object.getClass().getName() + "\n\tvalue=" + Ecma.toEcmaSourceCompact(object)
						+ "\n\tsameClass=" + check.get(guid1).getClass().getName() + "\n\tsameValue=" + Ecma.toEcmaSourceCompact(check.get(guid1));
				check.put(guid1, object);
			}
		}
		
		/**
		 * prototypes
		 */
		{
			final BaseObject data1 = new BaseNativeObject()//
					.putAppend("a", "11")//
					.putAppend("b", "12")//
					.putAppend("c", "21")//
					.putAppend("d", "22")//
					;
					
			final Guid guid1 = GuidStream.toUniqueIdentifier(data1, false);
			System.out.println(">>> >>> >>> guid1: " + guid1.toString());
			
			final BaseObject data2 = new BaseNativeArray();
			data2.baseDefineImportAllEnumerable(data1);
			
			final Guid guid2 = GuidStream.toUniqueIdentifier(data2, false);
			System.out.println(">>> >>> >>> guid2: " + guid2.toString());
			
			assert !guid1.equals(guid2) : "Must not be equal!";
			
			final BaseObject data3 = new BaseNativeObject(data1);
			data3.baseDefineImportAllEnumerable(data1);
			
			final Guid guid3 = GuidStream.toUniqueIdentifier(data3, false);
			System.out.println(">>> >>> >>> guid3: " + guid3.toString());
			
			System.out.println(">>> FAIL FAIL FAIL: guidstream, " + !guid1.equals(guid3));
			
			// assert !guid1.equals( guid3 ) : "Must not be equal!";
			// assert !guid2.equals( guid3 ) : "Must not be equal!";
		}
	}
	
}
