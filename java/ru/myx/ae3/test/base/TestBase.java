package ru.myx.ae3.test.base;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseArray;
import ru.myx.ae3.base.BaseFunction;
import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseNumber;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.ecma.compare.ComparatorEcma;
import ru.myx.ae3.help.Format;

/** @author myx */
public class TestBase {
	
	private static final void checkConstant(final BaseObject object, final BaseObject constant) {
		
		if (object != constant) {
			throw new UnsupportedOperationException("Should be constant, value=" + object);
		}
	}

	private static final void checkStrictEquals(final Object object1, final Object object2) {
		
		if (object1 != object2) {
			throw new UnsupportedOperationException("Should strict equal, value1=" + object1 + ", value2" + object2);
		}
	}

	/** @param args
	 * @throws Throwable */
	public static final void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> TEST BASE");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			/** check number object */
			assert BaseNumber.PROTOTYPE != null : "Number.prototype is NULL!";
			assert !BaseNumber.PROTOTYPE.baseIsPrimitive() : "Number.prototype is primitive!";
			for (final BaseObject check : new BaseObject[]{
					BasePrimitiveNumber.ZERO, BasePrimitiveNumber.ONE, BasePrimitiveNumber.MONE, Base.forInteger(101), Base.forLong(101L), Base.forDouble(101.0),
					Base.forInteger(0x2206241),
			}) {

				assert check.baseIsPrimitive() : "Must be a primitive!";
				assert check.baseIsPrimitiveNumber() : "Must be a number!";
				assert check.baseIsPrimitiveInteger() : "Must be an integer!";
			}
		}
		{
			/** check Function prototype */
			assert BaseFunction.PROTOTYPE.baseGet("apply", BaseObject.UNDEFINED) != BaseObject.UNDEFINED : "Must be DEFINED!";
			assert BaseFunction.PROTOTYPE.baseGet("bind", BaseObject.UNDEFINED) != BaseObject.UNDEFINED : "Must be DEFINED!";
			assert BaseFunction.PROTOTYPE.baseGet("call", BaseObject.UNDEFINED) != BaseObject.UNDEFINED : "Must be DEFINED!";
		}
		{
			final Object testValue = new StringBuilder();
			final BaseNativeObject object1 = new BaseNativeObject();
			assert object1.hashCodeForValue() == 0 : "Empty object's hashCode expected to be 0 regardless of BaseObject.PROTOTYPE contents";
			assert !object1.baseHasKeysOwn() : "Should not have own properties!";
			assert !Base.hasKeys(object1) : "Should not have properties unless Object.prototype is fucked!";
			Base.put(object1, "122", Base.forUnknown(testValue));
			Base.put(object1, "123", object1);
			assert object1.baseGet("120", BaseObject.UNDEFINED).baseValue() == null : "Should be null";
			assert Base.hasProperty(object1, "120") == false : "Should be false";
			assert object1.baseGetOwnProperty("120") == null : "Should be false";
			assert object1.baseGet("122", BaseObject.UNDEFINED).baseValue() == testValue : "Cannot hold and object";
			assert Base.hasProperty(object1, "122") == true : "Should be true";
			assert object1.baseGetOwnProperty("122") != null : "Should be true";
			assert object1.baseGet("123", BaseObject.UNDEFINED) == object1 : "Cannot hold baseObject";
			assert Base.hasProperty(object1, "123") == true : "Should be true";
			assert object1.baseGetOwnProperty("123") != null : "Should be true";
			final BaseObject object2 = new BaseNativeObject(object1);
			assert object2.baseGet("120", BaseObject.UNDEFINED).baseValue() == null : "Should be null";
			assert object2.baseGet("122", BaseObject.UNDEFINED).baseValue() == testValue : "Cannot hold and object";
			assert object2.baseGet("123", BaseObject.UNDEFINED) == object1 : "Prototype's field is not accessible";
			assert Base.hasProperty(object2, "123") == true : "Should be true";
			assert object2.baseGetOwnProperty("123") == null : "Should be true";

			{
				final BaseObject baseTestValue = Base.forUnknown(testValue);
				assert baseTestValue != null : "Should not be NULL";
				assert testValue == baseTestValue.baseValue() : "Reflection doesn't hold an object";
				assert baseTestValue.baseFindProperty("substring") != null : "Should reflect methods!";
				assert baseTestValue.baseFindProperty("substring222") == null : "Should not reflect non-existent methods!";
				Base.put(object2, "124", baseTestValue);
				assert object2.baseGet("124", BaseObject.UNDEFINED).baseValue() == testValue : "Cannot hold and object";
				assert object1.baseGet("124", BaseObject.UNDEFINED).baseValue() == null : "Should be null";
			}
			{
				final Map<String, Object> map = new TreeMap<>();
				map.put("java1", Boolean.FALSE);
				map.put("java2", "value2");
				final BaseObject baseObject = Base.forUnknown(map);
				assert baseObject != null : "Should not be NULL";
				assert baseObject.baseValue() == map : "Should provide original map on baseValue";

				assert baseObject.baseGet(Base.forString("java1"), BaseObject.UNDEFINED) != BaseObject.UNDEFINED //
						: "Should reflect map entries (native key): class: " + baseObject.getClass().getName();

				assert baseObject.baseGet("java1", BaseObject.UNDEFINED) != BaseObject.UNDEFINED //
						: "Should reflect map entries (java key): class: " + baseObject.getClass().getName();

				assert baseObject.baseGetOwnProperty(Base.forString("java1")) != null //
						: "Should reflect map entries (own property, native): class: " + baseObject.getClass().getName();

				assert baseObject.baseGetOwnProperty("java1") != null //
						: "Should reflect map entries (own property, java): class: " + baseObject.getClass().getName();

				assert baseObject.baseFindProperty(Base.forString("java1")) != null //
						: "Should reflect map entries (properties, native): class: " + baseObject.getClass().getName();

				assert baseObject.baseFindProperty("java1") != null //
						: "Should reflect map entries (properties, java): class: " + baseObject.getClass().getName();

				assert baseObject.baseGet(Base.forString("java333"), BaseObject.UNDEFINED) == BaseObject.UNDEFINED //
						: "Should not reflect non-existent methods (native key): class: " + baseObject.getClass().getName();

				assert baseObject.baseGet("java333", BaseObject.UNDEFINED) == BaseObject.UNDEFINED //
						: "Should not reflect non-existent methods (java key): class: " + baseObject.getClass().getName();

				assert baseObject.baseGetOwnProperty("java333") == null //
						: "Should not reflect non-existent methods (own property): class: " + baseObject.getClass().getName();

				assert baseObject.baseFindProperty("java333") == null //
						: "Should not reflect non-existent methods (properties): class: " + baseObject.getClass().getName();

				assert baseObject.baseGet("size", BaseObject.UNDEFINED) != BaseObject.UNDEFINED //
						: "Should reflect methods!";

				assert baseObject.baseFindProperty("size") != null //
						: "Should reflect methods!";

				assert baseObject.baseGet("size222", BaseObject.UNDEFINED) == BaseObject.UNDEFINED //
						: "Should not reflect non-existent methods!";

				assert baseObject.baseFindProperty("size222") == null : "Should not reflect non-existent methods!";
				assert baseObject.baseFindProperty("java1") != null : "Should reflect native map properties!";
				Base.put(baseObject, "key1", BasePrimitiveNumber.E);
				assert baseObject.baseFindProperty("key1") != null : "Should suport base properties!";
				Base.put(baseObject, "key2", BasePrimitiveNumber.PI);
				{
					int count = 0;
					for (final Iterator<String> iterator = Base.keys(baseObject); iterator.hasNext();) {
						final String key = iterator.next();
						assert key != null : "NULL key!";
						count++;
					}
					assert count == 4 : "Should iterate through 4 properties, but: " + count;
				}
				assert baseObject.baseDelete("java333") : "Should return true, even though there's no such variable!";
				assert baseObject.baseDelete("java1") : "Should return true!";
				assert baseObject.baseDelete("key333") : "Should return true, even though there's no such variable!";
				assert baseObject.baseDelete("key1") : "Should return true!";
				{
					int count = 0;
					for (final Iterator<String> iterator = Base.keys(baseObject); iterator.hasNext();) {
						final String key = iterator.next();
						assert key != null : "NULL key!";
						count++;
					}
					assert count == 2 : "Should iterate through 2 properties!";
				}
			}
		}
		{
			{
				TestBase.checkConstant(Base.forInteger(1), BasePrimitiveNumber.ONE);
				TestBase.checkConstant(Base.forLong(1L), BasePrimitiveNumber.ONE);
				TestBase.checkConstant(Base.forDouble(1.0), BasePrimitiveNumber.ONE);
				TestBase.checkConstant(Base.forInteger(0), BasePrimitiveNumber.ZERO);
				TestBase.checkConstant(Base.forLong(0L), BasePrimitiveNumber.ZERO);
				TestBase.checkConstant(Base.forDouble(0.0), BasePrimitiveNumber.ZERO);
				TestBase.checkConstant(Base.forInteger(-1), BasePrimitiveNumber.MONE);
				TestBase.checkConstant(Base.forLong(-1L), BasePrimitiveNumber.MONE);
				TestBase.checkConstant(Base.forDouble(-1.0), BasePrimitiveNumber.MONE);
				TestBase.checkConstant(Base.forDouble(Double.NaN), BasePrimitiveNumber.NAN);
				TestBase.checkConstant(Base.forDouble(Double.NEGATIVE_INFINITY), BasePrimitiveNumber.NINF);
				TestBase.checkConstant(Base.forDouble(Double.POSITIVE_INFINITY), BasePrimitiveNumber.PINF);
				TestBase.checkConstant(Base.forDouble(Math.PI), BasePrimitiveNumber.PI);
				TestBase.checkConstant(Base.forDouble(Math.E), BasePrimitiveNumber.E);
				TestBase.checkConstant(Base.forString(""), BaseString.EMPTY);
				TestBase.checkConstant(Base.forString("true"), BaseString.STR_TRUE);
				TestBase.checkConstant(Base.forString("false"), BaseString.STR_FALSE);
			}
			{
				final BasePrimitiveNumber check = Base.forDouble(0.0);
				TestBase.checkStrictEquals(check, BaseObject.FALSE.baseToNumber());
				TestBase.checkStrictEquals(check, Base.forDouble(Double.NaN).baseToInt32());
				TestBase.checkStrictEquals(check, Base.forDouble(0.0));
				TestBase.checkStrictEquals(check, Base.forInteger(0));
				TestBase.checkStrictEquals(check, Base.forUnknown((Object) Boolean.FALSE).baseToNumber());
				TestBase.checkStrictEquals(check, Base.forString("").baseToBoolean().baseToNumber());
			}
			{
				final BasePrimitiveNumber check = Base.forDouble(1.0);
				TestBase.checkStrictEquals(check, BaseObject.TRUE.baseToNumber());
				TestBase.checkStrictEquals(check, Base.forDouble(1.0));
				TestBase.checkStrictEquals(check, Base.forInteger(1));
				TestBase.checkStrictEquals(check, Base.forUnknown((Object) Boolean.TRUE).baseToNumber());
				TestBase.checkStrictEquals(check, Base.forString("5.0").baseToBoolean().baseToNumber());
				TestBase.checkStrictEquals(check, Base.forString("true").baseToBoolean().baseToNumber());
			}
			{
				final BasePrimitiveNumber check = Base.forDouble(5.0);
				TestBase.checkStrictEquals(check, Base.forInteger(5));
				TestBase.checkStrictEquals(check, Base.forDouble(5.0));
				TestBase.checkStrictEquals(check, Base.forUnknown((Object) Double.valueOf(5.0)));
				TestBase.checkStrictEquals(check, Base.forString("5.0").baseToNumber());
				TestBase.checkStrictEquals(check, Base.forString("+5.0").baseToNumber());
				TestBase.checkStrictEquals(check, Base.forString("5").baseToNumber());
				TestBase.checkStrictEquals(check, Base.forString("+5").baseToNumber());
			}
		}
		{
			TestBaseNativeObject.main(null);
			TestBaseDate.main(null);
			TestBaseNativeArray.main(null);
			TestBaseSealed.main(null);
		}
		{
			final BaseObject baseObject = Base.forUnknown(Runtime.getRuntime());
			assert baseObject != null : "Should not be NULL";
		}
		for (final BaseObject object : new BaseObject[]{
				Base.forUnknown(new TreeMap<String, Object>()), new BaseNativeObject(), new BaseNativeArray(), new BaseNativeArray(),
		}) {
			for (int k = 2; k > 0; k--) {
				for (int i = 9; i >= 0; --i) {
					Base.put(object, "z00" + i, BaseObject.TRUE);
				}
				object.baseDelete("z003");
				object.baseDelete("z009");
				object.baseDelete("z000");
				object.baseDelete("z005");
				object.baseDelete("z002");
				object.baseDelete("z001");
				object.baseDelete("z007");
				object.baseDelete("z008");
				object.baseDelete("z006");
				object.baseDelete("z004");
				assert !object.baseKeysOwn().hasNext() : "No properties expected!, first: " + object.baseKeysOwn().next();
			}
		}
		for (final BaseObject mapping : new BaseObject[]{
				Base.forUnknown(new TreeMap<String, Object>()), new BaseNativeObject(), new BaseNativeArray(), new BaseNativeArray(),
		}) {
			Base.put(mapping, "number", BasePrimitiveNumber.PI);
			Base.put(mapping, "string", BaseString.STR_PINFINITY);
			assert mapping.baseHasKeysOwn() : "Expected to have own properties, class=" + mapping.getClass().getName();
			assert mapping.baseGetOwnProperty("number") != null : "Cannot hold a value!";
			assert mapping.baseGet("number", BaseObject.UNDEFINED) == BasePrimitiveNumber.PI : "Cannot hold a value!";
		}
		{
			final BaseObject o1 = Base.forUnknown(Runtime.getRuntime());
			assert o1 != null : "Should not be NULL";
			assert o1.baseValue() == Runtime.getRuntime() : "Should be equal";
			final BaseObject o2 = Base.forUnknown(Runtime.getRuntime());
			assert o2 != null : "Should not be NULL";
			assert o1.equals(o2) : "Should be equal";
			final BaseObject o3 = Base.forUnknown(StandardCharsets.US_ASCII);
			assert o3 != null : "Should not be NULL";
			assert !o1.equals(o3) : "Should be NOT equal";
		}
		{
			final BaseObject date = Base.forDateMillis(Engine.fastTime() + 24L * 60L * 60_000L);
			/** Will check as Map as well! */
			final BaseMap mapping = new BaseNativeObject();
			Base.put(mapping, "date", date);
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			assert mapping.baseGet("date", BaseObject.UNDEFINED) == date : "Expected to hold the same object!";
			assert mapping.get("date") == date : "Expected to hold the same object even for map!";
		}
		{
			for (final Object source : new Object[]{
					new BaseNativeArray(), new BaseNativeArray(), new ArrayList<>(), new Object[0], new String[0]
			}) {
				final BaseObject array = Base.forUnknown(source);
				System.out.println("\tarray check: originalClass=" + source.getClass().getName());
				assert array != null;
				System.out.println("\t\t baseClass=" + array.getClass().getName());
				assert array instanceof List<?>;
				assert array.baseValue() == source;
				{
					boolean found = false;
					for (BaseObject object = array; object != null; object = object.basePrototype()) {
						System.out.println("\t\t checkClass=" + Format.Describe.toEcmaSource(object, "\t\t\t\t "));
						if (object == BaseArray.PROTOTYPE) {
							found = true;
							break;
						}
					}
					assert found : "No Array.prototype in prototype chain!";
				}
				assert Base.hasProperty(array, "length");
				assert array.baseFindProperty("length") != null;
				System.out.println("\t\t .length propertyClass=" + array.baseFindProperty("length").getClass().getName());
				assert array.baseArray() != null;
				assert array.baseArray().length() == 0;
				assert array.baseGet("length", BaseObject.UNDEFINED) == BasePrimitiveNumber.ZERO;
				{
					final Object read = array.baseGet("length", BaseObject.UNDEFINED);
					assert read != null : "Not ZERO but NULL";
					/** ecmaFieldRead is not required to return BaseObjects */
					assert ComparatorEcma.INSTANCE.compare(read, BasePrimitiveNumber.ZERO) == 0 : "Not ZERO: " + read + ", class=" + read.getClass().getName();
				}
			}
		}
		{
			assert TransferCopier.NUL_COPIER != null;
			assert Base.forUnknown((Object) TransferCopier.NUL_COPIER) == TransferCopier.NUL_COPIER;
		}
	}
}
