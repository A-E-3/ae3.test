package ru.myx.ae3.test.reflect;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseArray;
import ru.myx.ae3.base.BaseArrayAdvanced;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.base.ToPrimitiveHint;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecCallable;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.help.Convert;
import ru.myx.ae3.reflect.ImplementReflect;
import ru.myx.ae3.reflect.Reflect;
import ru.myx.ae3.reflect.Reflected;
import ru.myx.ae3.sapi.MathSAPI;
import ru.myx.ae3.test.TestAe3;
import ru.myx.sapi.RandomSAPI;
import ru.myx.util.Counter;
import ru.myx.util.EntrySimple;

/** @author myx */
public class TestReflect {

	private static void dump(final BaseObject object) {

		System.out.println(">>> >>> dump " + object);
		for (final Iterator<? extends CharSequence> iterator = object.baseKeysOwnAll(); iterator.hasNext();) {
			final CharSequence key = iterator.next();
			System.out.println(">>> >>> >>> " + key + ", " + object.baseFindProperty(key) + " = " + object.baseGet(key, BaseObject.UNDEFINED));
		}
		System.out.println();
	}

	/** @param args
	 * @throws Exception */
	public static void main(final String[] args) throws Exception {

		TestAe3.initAE3();
		System.out.println(">>>> TEST REFLECT");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			for (final BaseObject prototype : new BaseObject[]{
					Reflect.classToBasePrototype(System.class), Base.forUnknown(System.class), Base.forUnknown(Base.forUnknown(System.class).baseValue())
			}) {
				System.out.println("\tcheck System.out, class=" + prototype);
				assert prototype != null : "NULL java value";
				assert prototype.baseGet("AlT", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				assert prototype.baseGet(Base.forString("AlT"), BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				assert prototype.baseGet("out", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
				assert prototype.baseGet(Base.forString("out"), BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
				assert Base.getJava(prototype, "AlT", null) == null;
				assert Base.getJava(prototype, "out", null) != null;
				{
					final BaseObject read = prototype.baseGet("out", BaseObject.UNDEFINED);
					System.out.println("\t\tnative ecma read=" + read);
					assert read != null;
					assert read != BaseObject.UNDEFINED;
					assert read != BaseObject.NULL;
				}
				{
					final BaseObject read = prototype.baseGet(Base.forString("out"), BaseObject.UNDEFINED);
					System.out.println("\t\tnative ecma read=" + read);
					assert read != null;
					assert read != BaseObject.UNDEFINED;
					assert read != BaseObject.NULL;
				}
			}
		}
		{
			assert ImplementReflect.basePrototypeForJavaClass(MathSAPI.class, true) != null : "Reflector is not available";
			assert ImplementReflect.basePrototypeForJavaClass(MathSAPI.class, true).baseGet("cos", BaseObject.UNDEFINED) != BaseObject.UNDEFINED : "Reflector failed!";
			assert Reflect.classToBasePrototype(MathSAPI.class) != null : "Reflector is not available";
			assert Reflect.classToBasePrototype(MathSAPI.class).baseGet("cos", BaseObject.UNDEFINED) != BaseObject.UNDEFINED : "Reflector failed!";
		}
		{
			final BaseObject prototype = Reflect.classToBasePrototype(Test00Class.class);
			assert prototype != null : "NULL java value";
			assert prototype.baseGet("staticMethod0", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			assert prototype.baseGet("staticMethod1", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert prototype.baseGet("staticMethod2", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert prototype.baseGet("staticMethod3", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			assert prototype.baseGet("method1", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert prototype.baseGet("method2", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert prototype.baseGet("method3", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			assert prototype.baseGet("staticField0", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			assert prototype.baseGet("staticField1", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			TestReflect.dump(prototype);
			{
				final BaseObject inherited = Reflect.classToBasePrototype(Test01Class.class);
				assert inherited != null : "NULL java value";
				assert inherited != prototype;
				assert inherited.baseGet("staticMethod0", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				/** Questionably??? */
				// assert inherited.baseGet( "staticMethod1" ) ==
				// BaseObject.UNDEFINED;
				{
					final BaseObject v1 = inherited.baseGet("method1", BaseObject.UNDEFINED);
					final BaseObject v2 = prototype.baseGet("method1", BaseObject.UNDEFINED);
					assert v1 == v2 : "Not the same: equal=" + v1.equals(v2) + ", inherited=" + v1 + ", class=" + v1.getClass() + ", prototype=" + v2;
				}
				{
					final BaseObject v1 = inherited.baseGet("method2", BaseObject.UNDEFINED);
					final BaseObject v2 = prototype.baseGet("method2", BaseObject.UNDEFINED);
					assert v1 == v2 : "Not the same: equal=" + v1.equals(v2) + ", inherited=" + v1 + ", class=" + v1.getClass() + ", prototype=" + v2;
				}
				{
					final BaseObject v1 = inherited.baseGet("method2EE", BaseObject.UNDEFINED);
					final BaseObject v2 = prototype.baseGet("method2EE", BaseObject.UNDEFINED);
					assert v1 != v2 : "The same: equal=" + v1.equals(v2) + ", inherited=" + v1 + ", class=" + v1.getClass() + ", prototype=" + v2;
				}
				TestReflect.dump(inherited);
				{
					final BaseObject overloaded = Reflect.classToBasePrototype(Test02Class.class);
					assert overloaded != null : "NULL java value";
					assert overloaded != prototype;
					assert overloaded.baseGet("staticMethod0", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
					assert overloaded.baseGet("method2", BaseObject.UNDEFINED) != prototype.baseGet("method2", BaseObject.UNDEFINED) : "The same: equal="
							+ overloaded.baseGet("method2", BaseObject.UNDEFINED).equals(prototype.baseGet("method2", BaseObject.UNDEFINED)) + ", inherited="
							+ overloaded.baseGet("method2", BaseObject.UNDEFINED) + ", class=" + overloaded.baseGet("method2", BaseObject.UNDEFINED).getClass() + ", prototype="
							+ prototype.baseGet("method2", BaseObject.UNDEFINED);
					TestReflect.dump(overloaded);
				}
			}
		}
		{
			final BaseObject p1 = Reflect.classToBasePrototype(Object.class);
			assert p1 != null;
			assert p1.baseGet("toString", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			final BaseObject p2 = Reflect.classToBasePrototype(Test00Class.class);
			assert p2 != null;
			assert p2.baseGet("toString", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert p1.baseGet("toString", BaseObject.UNDEFINED) == p2.baseGet("toString", BaseObject.UNDEFINED);
			final BaseObject p3 = Reflect.classToBasePrototype(Test01Class.class);
			assert p3 != null;
			assert p3.baseGet("toString", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert p1.baseGet("toString", BaseObject.UNDEFINED) == p3.baseGet("toString", BaseObject.UNDEFINED);
			final BaseObject p4 = Reflect.classToBasePrototype(StringBuilder.class);
			assert p4 != null;
			assert p4.baseGet("toString", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert p1.baseGet("toString", BaseObject.UNDEFINED) != p4.baseGet("toString", BaseObject.UNDEFINED);
		}
		{
			final BaseObject prototype = Reflect.classToBasePrototype(Test10Interface.class);
			assert prototype != null : "NULL java value";
			assert prototype.baseGet("method0", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			assert prototype.baseGet("method1", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
			assert prototype.baseGet("method3", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			TestReflect.dump(prototype);
			{
				final BaseObject inherited = Reflect.classToBasePrototype(Test11Class.class);
				assert inherited != null : "NULL java value";
				assert inherited != prototype;
				assert inherited.baseGet("method0", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				assert inherited.baseGet("method1", BaseObject.UNDEFINED) != BaseObject.UNDEFINED;
				assert inherited.baseGet("method3", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				TestReflect.dump(inherited);
			}
		}
		{
			@SuppressWarnings("unchecked")
			final Map.Entry<String, Object>[] entries = new Map.Entry[]{
					new EntrySimple<String, Object>("false", "true"), new TestMapEntryEntry(), new TestMapEntryEntry() {

						@Override
						public String getKey() {

							return "false";
						}
					}, new TestMapEntryExtendsEntry(), new TestMapEntryExtendsEntry() {
						//
					},
					//
			};
			for (final Map.Entry<String, Object> entry : entries) {
				{
					final BaseObject object = Base.forUnknown(entry);
					assert object != null : "NULL java value";
					assert object.baseGet("getKey", BaseObject.UNDEFINED).baseCall() != null : "Must have getKey() method, class=" + entry.getClass().getName();
					assert object.baseGet("getValue", BaseObject.UNDEFINED).baseCall() != null : "Must have getValue() method, class=" + entry.getClass().getName();
					assert object.baseGet("key", BaseObject.UNDEFINED) == BaseString.STR_FALSE : "Key is not 'false' but: " + object.baseGet("key", BaseObject.UNDEFINED)
							+ ", entryClass=" + entry.getClass().getName() + ", baseClass=" + object.getClass().getName() + ", getKey="
							+ object.baseGet("getKey", BaseObject.UNDEFINED) + ", getKey.class=" + object.baseGet("getKey", BaseObject.UNDEFINED).getClass().getName();
				}
			}
		}
		{
			// choose List vs. Array
			final Reflected random = Reflect.classToReflected(RandomSAPI.class);
			final Object randomObject = new RandomSAPI();
			final BaseObject randomNative = Base.forUnknown(randomObject);
			assert random != null;
			System.out.println("\t\tobject: " + random);
			final BaseObject method = random.reflectReadNative(null, randomNative, "fromList");
			assert method != null;
			assert method instanceof ExecCallable;
			System.out.println("\t\tobject.fromList: " + method);
			/** EMPTY */
			System.out.println("\t\t\tlist empty");
			for (final Object list : new Object[]{
					//
					new Object[0], new AbstractList<String>() {

						@Override
						public String get(final int index) {

							return null;
						}

						@Override
						public int size() {

							return 0;
						}
					}, new ArrayList<String>(), new BaseNativeArray(), new BaseNativeArray(), Base.forUnknown((Object) new ArrayList<String>())
					//
			}) {
				System.out.println("\t\t\tlist : class=" + list.getClass().getName() + ", value=" + list);
				final ExecProcess process = Exec.createProcess(null, "test");
				{
					final Object r = randomNative.baseCall(process, "fromList", false, Base.forUnknown(list));
					assert !Ecma.ecmaToBoolean(r) : "Not BCVT FALSE: " + r;
				}
				{
					final BaseObject listBase = Base.forUnknown(list);
					{
						final Object r = randomNative.baseCall(process, "fromList", false, listBase);
						assert !Ecma.ecmaToBoolean(r) : "Not BCVT FALSE: " + r;
					}
					{
						final Object r = randomNative.baseCall(process, "fromList", false, listBase);
						assert !Ecma.ecmaToBoolean(r) : "Not BCVT FALSE: " + r;
					}
					final BaseObject randomBase = Base.forUnknown(randomObject);
					{
						final Object r = randomBase.baseCall(process, "fromList", false, listBase);
						assert !Ecma.ecmaToBoolean(r) : "Not BCVT FALSE: " + r;
					}
					{
						final Object r = randomBase.baseCall(process, "fromList", false, listBase);
						assert !Ecma.ecmaToBoolean(r) : "Not BCVT FALSE: " + r;
					}
				}
			}
			/** SINGLE */
			System.out.println("\t\t\tlist single");
			for (final Object list : new Object[]{
					//
					new Object[]{
							Boolean.TRUE
					}, new Object[]{
							BaseObject.TRUE
					}, new ArrayList<>(Collections.singleton("TRUE")), new BaseNativeArray(BaseObject.TRUE),
					Base.forUnknown((Object) new ArrayList<>(Collections.singleton("TRUE")))
					//
			}) {
				System.out.println("\t\t\tlist : class=" + list.getClass().getName() + ", value=" + list);
				final ExecProcess process = Exec.createProcess(null, "test");
				{
					final Object r = randomNative.baseCall(process, "fromList", false, Base.forUnknown(list));
					assert Ecma.ecmaToBoolean(r) : "Not BCVT TRUE: " + r;
				}
				{
					final BaseObject listBase = Base.forUnknown(list);
					assert List.class.isInstance(listBase);
					{
						final Object r = randomNative.baseCall(process, "fromList", false, listBase);
						assert Ecma.ecmaToBoolean(r) : "Not BCVT TRUE: " + r;
					}
					{
						final Object r = randomNative.baseCall(process, "fromList", false, listBase);
						assert Ecma.ecmaToBoolean(r) : "Not BCVT TRUE: " + r;
					}
					final BaseObject randomBase = Base.forUnknown(randomObject);
					{
						final Object r = randomBase.baseCall(process, "fromList", false, listBase);
						assert Ecma.ecmaToBoolean(r) : "Not BCVT TRUE: " + r;
					}
					{
						final Object r = randomBase.baseCall(process, "fromList", false, listBase);
						assert Ecma.ecmaToBoolean(r) : "Not BCVT TRUE: " + r;
					}
				}
			}
		}
		{
			final Reflected c1 = Reflect.classToReflected(Test10Interface.class);
			assert c1.reflectReadNative(null, Base.forUnknown(c1), "STATE_POLISH").baseValue() == null;
			assert c1.reflectReadNative(null, Base.forUnknown(c1), "STATE_PUBLISH").baseValue() != null;
			final Reflected c2 = Reflect.classToReflected(Test11Class.class);
			assert c1.reflectReadNative(null, Base.forUnknown(c2), "STATE_POLISH").baseValue() == null;
			assert c1.reflectReadNative(null, Base.forUnknown(c2), "STATE_PUBLISH").baseValue() != null;
			assert c2.reflectReadNative(null, Base.forUnknown(c2), "STATE_POLISH").baseValue() == null;
			assert c2.reflectReadNative(null, Base.forUnknown(c2), "STATE_PUBLISH").baseValue() != null;
		}
		{
			/** Test numbers */
			for (final int value : new int[]{
					0, 1, 2, 3
			}) {
				for (final Number test : new Number[]{
						//
						Byte.valueOf((byte) value), Short.valueOf((short) value), Integer.valueOf(value), Long.valueOf(value), Float.valueOf(value), Double.valueOf(value),
						new BigInteger(String.valueOf(value)), new BigDecimal(value), new AtomicInteger(value), new AtomicLong(value), new Counter(value), value == 0
							? BasePrimitiveNumber.ZERO
							: Base.forInteger(value),
						//
				}) {
					System.out.println(">>>> >>>> number: " + test.getClass().getName() + ", value: " + value);
					final BaseObject base = Base.forUnknown((Object) test);
					assert base != null;
					assert base instanceof Number : "Expected to be number: class=" + base.getClass().getName();

					final Number numb = (Number) base;

					assert numb.byteValue() == value;
					assert numb.shortValue() == value;
					assert numb.intValue() == value;
					assert numb.longValue() == value;
					assert numb.floatValue() == value;
					assert numb.doubleValue() == value;

					assert base.baseToJavaInteger() == value;

					assert base.baseToNumber().byteValue() == value;
					assert base.baseToNumber().shortValue() == value;
					assert base.baseToNumber().intValue() == value;
					assert base.baseToNumber().longValue() == value;
					assert base.baseToNumber().floatValue() == value;
					assert base.baseToNumber().doubleValue() == value;

					assert base.baseToInteger().byteValue() == value;
					assert base.baseToInteger().shortValue() == value;
					assert base.baseToInteger().intValue() == value;
					assert base.baseToInteger().longValue() == value;
					assert base.baseToInteger().floatValue() == value;
					assert base.baseToInteger().doubleValue() == value;

					assert base.baseToJavaInteger() == value;
					assert base.baseToJavaLong() == value;

					assert base.baseToInt32().byteValue() == value;
					assert base.baseToInt32().shortValue() == value;
					assert base.baseToInt32().intValue() == value;
					assert base.baseToInt32().longValue() == value;
					assert base.baseToInt32().floatValue() == value;
					assert base.baseToInt32().doubleValue() == value;

					assert base.baseToJavaBoolean() == (value != 0) : //
					"value: " + value + ", baseClass=" + base.getClass().getName() + ", boolean: " + base.baseToJavaBoolean();

					assert base.baseToPrimitive(ToPrimitiveHint.NUMBER) instanceof Number;
					assert base.baseToPrimitive(ToPrimitiveHint.STRING) instanceof Number;

					assert Convert.Any.toInt(base.baseToJavaString(), -1) == value : "string: " + base.baseToJavaString();
					assert Convert.Any.toInt(base.baseToString(), -1) == value;

					assert base.hashCode() == base.baseValue().hashCode();
				}
			}
		}
		{
			/** Test collections */
			for (final Collection<?> test : new Collection<?>[]{
					//
					new IdentityHashMap<String, Object>().entrySet(), new HashSet<String>(), new TreeSet<String>(), Collections.emptySet(),
					//
			}) {
				System.out.println(">>>> >>>> collection: " + test.getClass().getName());
				final BaseObject base = Base.forUnknown(test);
				assert base != null;
				assert base instanceof Collection<?>;

				assert ((Collection<?>) base).isEmpty();
				assert ((Collection<?>) base).iterator() != null;
				assert !((Collection<?>) base).iterator().hasNext();

				assert base.baseGet("add", BaseObject.NULL).baseCall() != null;
				assert base.baseGet("remove", BaseObject.NULL).baseCall() != null;
				assert base.baseGet("clear", BaseObject.NULL).baseCall() != null;

				assert base.baseGet("clear222", BaseObject.NULL).baseCall() == null;
			}
		}
		{
			/** Test maps */
			for (final Map<?, ?> test : new Map<?, ?>[]{
					//
					new IdentityHashMap<String, Object>(), new Hashtable<String, Object>(), new HashMap<String, Object>(), new TreeMap<String, Object>(), new Properties(),
					new BaseNativeObject(),
					//
			}) {
				System.out.println(">>>> >>>> map: " + test.getClass().getName());
				final BaseObject base = Base.forUnknown(test);
				assert base != null;
				assert base instanceof Map<?, ?>;

				final String field = "field1";

				Base.put(base, field, "string1");
				assert Base.getString(base, field, "").equals("string1");
				assert ((Map<?, ?>) base).get(field) != null;
				assert ((Map<?, ?>) base).get(field).equals("string1");
			}
		}
		{
			/** Test arrays */
			for (final Object arrayTest : new Object[]{
					new Object[]{
							"1", "2", "3"
					}, Arrays.asList(new Object[]{
							"1", "2", "3"
					}), new String[]{
							"1", "2", "3"
					}, new int[]{
							1, 2, 3
					}, new long[]{
							1, 2, 3
					}, new double[]{
							1, 2, 3
					}, new float[]{
							1, 2, 3
					}, new short[]{
							1, 2, 3
					}, new byte[]{
							1, 2, 3
					}, new char[]{
							'1', '2', '3'
					}, new BaseObject[]{
							BasePrimitiveNumber.ONE, Base.forInteger(2), Base.forString("3")
					},
					//
			}) {
				final BaseObject arrayObject = Base.forUnknown(arrayTest);
				final BaseArray array = arrayObject.baseArray();
				assert array != null : "Not an array: testClass=" + arrayTest.getClass().getName() + ", baseClass=" + arrayObject.getClass().getName() + ", base=" + arrayObject;
				System.out.println(">>>> >>>> array: " + array.getClass().getName());
				assert array.length() == 3 : "Length ain't 3 but: " + array.length() + ", testClass=" + arrayTest.getClass().getName() + ", baseClass="
						+ arrayObject.getClass().getName() + ", base=" + arrayObject;
				assert Base.getLong(array, "length", 0) == 3 : "Length ain't 3 but: " + array.length() + ", testClass=" + arrayTest.getClass().getName() + ", baseClass="
						+ arrayObject.getClass().getName() + ", base=" + arrayObject;
				assert "1".equals(array.baseGet(0, BaseObject.UNDEFINED).baseToJavaString()) : "First element ain't '1' but: " + array.baseGet(0, BaseObject.UNDEFINED)
						+ ", testClass=" + arrayTest.getClass().getName() + ", baseClass=" + arrayObject.getClass().getName() + ", base=" + arrayObject;
				assert "2".equals(array.baseGet(1, BaseObject.UNDEFINED).baseToJavaString()) : "Second element ain't '2' but: " + array.baseGet(1, BaseObject.UNDEFINED)
						+ ", testClass=" + arrayTest.getClass().getName() + ", baseClass=" + arrayObject.getClass().getName() + ", base=" + arrayObject;
				/** just for baseIterator method */
				assert array.baseArrayAdvanced() != null;
				final Iterator<?>[] iterators;
				if (array instanceof List<?>) {
					final Object[] arr = ((List<?>) array).toArray();
					assert arr != null;
					assert arr.length == 3;
					assert Convert.Any.toInt(arr[0], -1) == 1;
					assert Convert.Any.toInt(arr[1], -1) == 2;
					assert Convert.Any.toInt(arr[2], -1) == 3;

					final List<?> list = ((List<?>) array).subList(1, 3);
					assert !list.isEmpty();
					assert list.size() == 2;
					assert !list.contains(Boolean.TRUE);
					assert !list.contains(Boolean.TYPE);
					assert !list.remove(Boolean.TYPE);
					assert list.indexOf(Boolean.TYPE) == -1;
					assert list.lastIndexOf(Boolean.TYPE) == -1;
					assert Convert.ListEntry.toInt(list, 0, 0) == 2;
					assert Convert.ListEntry.toInt(list, 1, 0) == 3;

					iterators = new Iterator<?>[]{//
							((List<?>) array).iterator(), ((List<?>) array).listIterator(), ((List<?>) array).listIterator(0), ((BaseArrayAdvanced<?>) array).baseIterator(),
							//
					};
				} else {
					iterators = new Iterator<?>[]{
							array.baseIterator(),
					};
				}
				for (final Iterator<?> iterator : iterators) {
					assert iterator != null;
					System.out.println(">>>> >>>> >>>> array: " + array.getClass().getName() + ", iterator: " + iterator.getClass().getName());
					assert iterator.hasNext();
					assert Convert.Any.toInt(iterator.next(), 0) == 1;
					assert iterator.hasNext();
					assert Convert.Any.toInt(iterator.next(), 0) == 2;
					assert iterator.hasNext();
					assert Convert.Any.toInt(iterator.next(), 0) == 3;
					assert !iterator.hasNext();
				}
			}
		}

		{
			/** Test fields */
			{
				/** own */
				final Test00Class test = new Test00Class();
				final BaseObject base = Base.forUnknown(test);
				test.field1 = "123";
				assert base.baseGet("field1", BaseObject.UNDEFINED).baseToString().equals("123");
				assert Base.put(base, "field1", "321");
				assert test.field1.equals("321");
				assert !Base.put(base, "fld", "123");
			}
			{
				/** inherited */
				final Test01Class test = new Test01Class();
				final BaseObject base = Base.forUnknown(test);
				test.field1 = "123";
				assert base.baseGet("field1", BaseObject.UNDEFINED).baseToString().equals("123");
				assert Base.put(base, "field1", "321");
				assert test.field1.equals("321");
				assert !Base.put(base, "fld", "123");
			}
			{
				/** getData Map */
				final BaseObject base = Base.forUnknown(new Test03Class());
				assert Base.put(base, "fld", "123") : "class=" + base.getClass();
				assert base.baseGet("fld", BaseObject.UNDEFINED).baseToString().equals("123")//
				: "strings do not match: " + base.baseGet("fld", BaseObject.UNDEFINED).getClass().getName();

				assert Base.put(base, "fld", "321");
				assert base.baseGet("fld", BaseObject.UNDEFINED).baseToString().equals("321")//
				: "strings do not match: " + base.baseGet("fld", BaseObject.UNDEFINED).getClass().getName();
			}
			{
				/** getData Base */
				final BaseObject base = Base.forUnknown(new Test04Class());
				assert Base.put(base, "fld", "123");
				assert base.baseGet("fld", BaseObject.UNDEFINED).baseToString().equals("123")//
				: "strings do not match: " + base.baseGet("fld", BaseObject.UNDEFINED).getClass().getName();

				assert Base.put(base, "fld", "321");
				assert base.baseGet("fld", BaseObject.UNDEFINED).baseToString().equals("321");
			}
			{
				/** getData BaseNativeObject (both map and base) */
				final BaseObject base = Base.forUnknown(new Test05Class());
				assert Base.put(base, "fld", "123");
				assert base.baseGet("fld", BaseObject.UNDEFINED).baseToString().equals("123");
				assert Base.put(base, "fld", "321");
				assert base.baseGet("fld", BaseObject.UNDEFINED).baseToString().equals("321");
			}
			{
				/** getter/setter simple string */
				final BaseObject base = Base.forUnknown(new Test00Class());
				assert base.baseGet("gsField1", BaseObject.UNDEFINED).baseToString().equals("BB1");
				assert Base.put(base, "gsField1", "123");
				assert base.baseGet("gsField1", BaseObject.UNDEFINED).baseToString().equals("123");
				assert Base.put(base, "gsField1", "321");
				assert base.baseGet("gsField1", BaseObject.UNDEFINED).baseToString().equals("321");
			}
			{
				/** getter/setter scripting friendly setters string/integer */
				final BaseObject base = Base.forUnknown(new Test01Class());
				assert base.baseGet("gsField2", BaseObject.UNDEFINED).baseToString().equals("BB2");
				assert Base.put(base, "gsField2", "123");
				assert base.baseGet("gsField2", BaseObject.UNDEFINED).baseToString().equals("123");
				assert Base.put(base, "gsField2", 321);
				assert base.baseGet("gsField2", BaseObject.UNDEFINED).baseToString().equals("321");
			}
			{
				/** setter only, dual scripting friendly setters string/integer, value is
				 * concatenated with ',' */
				final BaseObject base = Base.forUnknown(new Test02Class());

				assert base.baseGet("gsField3", BaseObject.UNDEFINED).baseToString().equals("BB3") //
				: "gsField3: " + base.baseGet("gsField3", BaseObject.UNDEFINED);

				assert Base.put(base, "gsField3", "123");
				assert base.baseGet("gsField3", BaseObject.UNDEFINED).baseToString().equals("BB3,str=123");
				assert Base.put(base, "gsField3", 321);
				assert base.baseGet("gsField3", BaseObject.UNDEFINED).baseToString().equals("BB3,str=123,int=321");
			}
			{
				/** getter/setter dual setters string/integer */
				final BaseObject base = Base.forUnknown(new Test03Class());
				assert base.baseGet("gsField4", BaseObject.UNDEFINED).baseToString().equals("BB4");
				assert Base.put(base, "gsField4", "123");
				assert base.baseGet("gsField4", BaseObject.UNDEFINED).baseToString().equals("123");
				assert Base.put(base, "gsField4", 321);
				assert base.baseGet("gsField4", BaseObject.UNDEFINED).baseToString().equals("321");
			}
			{
				/** getter/setter dual java setters and dual scripting friendly setters.
				 * string/integer. Making sure scripting friendly is preferred. */
				final BaseObject base = Base.forUnknown(new Test04Class());

				assert base.baseGet("gsField5", BaseObject.UNDEFINED).baseToString().equals("BB5") //
				: "unexpected: " + base.baseGet("gsField5", BaseObject.UNDEFINED);

				assert Base.put(base, "gsField5", "123");
				assert base.baseGet("gsField5", BaseObject.UNDEFINED).baseToString().equals("BB5,str-123");
				assert Base.put(base, "gsField5", 321);
				assert base.baseGet("gsField5", BaseObject.UNDEFINED).baseToString().equals("BB5,str-123,int-321");
			}
		}
		{
			/** methods */
			{
				final BaseObject DEFAULT = new BaseNativeObject(Reflect.classToBasePrototype(Math.class));
				final BaseObject mathApi = new BaseNativeObject(DEFAULT);
				final ExecProcess ctx = Exec.createProcess(null, "test");
				for (final Number test : new Number[]{
						BasePrimitiveNumber.ZERO, BasePrimitiveNumber.PI, Integer.valueOf(0), Long.valueOf(0), Double.valueOf(0), Double.valueOf(Math.PI), //
						Double.valueOf(2 * Math.PI), //
				}) {
					final Number result = mathApi.baseCall(ctx, "sin", false, Base.forNumber(test)).baseToNumber();

					assert result != null && (long) (result.doubleValue() * 1000000) == 0 //
					: "result=" + result + ", argClass=" + test.getClass().getName() + ", argValue=" + test;
				}
			}
		}
		/** Test annotations */
		{
			{
				final BaseObject reflected = Base.forUnknown(new AnnoTestBase());
				assert null != reflected.baseFindProperty("methodInstanceBase");
				assert null == reflected.baseFindProperty("methodInstanceBase222");
			}
			{
				final BaseObject reflected = Base.forUnknown(new AnnoTestDefault());
				assert null != reflected.baseFindProperty("methodInstanceBase");
				assert null == reflected.baseFindProperty("methodInstanceBase222");
				assert null != reflected.baseFindProperty("methodInstanceDefault");
				assert null == reflected.baseFindProperty("methodInstanceHidden");
			}
			{
				final BaseObject reflected = Base.forUnknown(new AnnoTestDisabled());
				assert null == reflected.baseFindProperty("methodInstanceBase");
				assert null == reflected.baseFindProperty("methodInstanceBase222");
				assert null == reflected.baseFindProperty("methodInstanceDisabled");
			}
			{
				final BaseObject reflected = Base.forUnknown(new AnnoTestIgnored());
				assert null != reflected.baseFindProperty("methodInstanceBase");
				assert null == reflected.baseFindProperty("methodInstanceBase222");
				assert null == reflected.baseFindProperty("methodInstanceIgnored");
			}
			{
				final BaseObject reflected = Base.forUnknown(new AnnoTestManual());
				assert null != reflected.baseFindProperty("methodInstanceBase");
				assert null == reflected.baseFindProperty("methodInstanceBase222");
				assert null == reflected.baseFindProperty("methodInstanceDefault");
				assert null != reflected.baseFindProperty("methodInstanceExplicit");
			}
		}
	}
}
