package ru.myx.ae3.test.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.base.BaseMessage;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.extra.ExtraBinary;
import ru.myx.ae3.flow.Flow;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.xml.Xml;

/** @author myx */
public class TestXml {

	/** @param args
	 * @throws Throwable */
	public static void main(final String[] args) throws Throwable {

		System.out.println(">>>> TEST XML");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}

		TestAe3.initAE3();

		{
			assert !Format.Xml.isValidName(" ") : "Must be invalid!";
			assert !Format.Xml.isValidName("\u0011") : "Must be invalid!";
			assert !Format.Xml.isValidName("\u0012") : "Must be invalid!";
		}
		{
			assert Format.Xml.isValidName("a") : "Must be valid!";
			assert Format.Xml.isValidName("hello-world") : "Must be valid!";
			assert Format.Xml.isValidName("minus_five") : "Must be valid!";
		}
		{
			final String xml = "<l class='list'><l>item</l><l>item</l><l>item</l></l>";
			final BaseObject base = Xml.toBase("test", xml, "", null, null);
			assert base.baseArray() != null;
			assert base.baseArray().length() == 3;
		}
		{
			final String xml = "<map><l class='list'></l><l>item</l><l>item</l><l>item</l></map>";
			final BaseObject base = Xml.toBase("test", xml, "", null, null);
			assert base != null;
			final BaseObject list = base.baseGet("l", null);
			assert list != null;
			assert list.baseArray() != null;
			assert list.baseArray().length() == 3;
		}
		{
			final String xml = "<map><l class='list' items='li'><li>item</li><li>item</li><li>item</li></l></map>";
			final BaseObject base = Xml.toBase("test", xml, "", null, null);
			assert base != null;
			final BaseObject list = base.baseGet("l", null);
			assert list != null;
			assert list.baseArray() != null;
			assert list.baseArray().length() == 3;
		}
		{
			final String xml = "<map><l class='list'><l>item</l><l>item</l><l>item</l></l></map>";
			final BaseObject base = Xml.toBase("test", xml, "", null, null);
			assert base != null;
			final BaseObject list = base.baseGet("l", null);
			assert list != null;
			assert list.baseArray() != null;
			assert list.baseArray().length() == 3;
		}
		{
			final String xml = "<map><l>item</l><l>item</l><l>item</l></map>";
			final BaseObject base = Xml.toBase("test", xml, "", null, null);
			assert base != null;
			final BaseObject list = base.baseGet("l", null);
			assert list != null;
			assert list.baseArray() != null;
			assert list.baseArray().length() == 3;
		}
		{
			DomXml.main(new String[0]);
			TextXml.main(new String[0]);
			BinaryXml.main(new String[0]);
		}
	}

	static final void compare(final BaseObject map1, final BaseObject map2) {

		if (map1 != map2) {
			for (final Iterator<String> iterator = Base.keys(map1); iterator.hasNext();) {
				final String key = iterator.next();
				final BaseObject original = map1.baseGet(key, BaseObject.UNDEFINED);
				assert original != null : "NULL java value";
				final BaseObject effective = map2.baseGet(key, BaseObject.UNDEFINED);
				assert effective != null : "NULL java value";
				if (original != effective && !original.equals(effective)) {
					System.err.println("K=" + key);
					System.err.println("OS=" + Format.Describe.toEcmaSource(original, "") + ", ES=" + Format.Describe.toEcmaSource(effective, ""));
					System.err.println("OC=" + original.getClass() + ", EC=" + effective.getClass() + " not equals");
				}
				if (original.getClass() != effective.getClass()) {
					System.err.println("K=" + key);
					System.err.println("OS=" + Format.Describe.toEcmaSource(original, "") + ", ES=" + Format.Describe.toEcmaSource(effective, ""));
					System.err.println("O=" + original.getClass() + ", E=" + effective.getClass() + " class differs");
				}
			}
			for (final Iterator<String> iterator = Base.keys(map2); iterator.hasNext();) {
				final String key = iterator.next();
				final BaseObject original = map1.baseGet(key, BaseObject.UNDEFINED);
				assert original != null : "NULL java value";
				final BaseObject effective = map2.baseGet(key, BaseObject.UNDEFINED);
				assert effective != null : "NULL java value";
				if (original != effective && !original.equals(effective)) {
					System.err.println("K=" + key);
					System.err.println("OS=" + Format.Describe.toEcmaSource(original, "") + ", ES=" + Format.Describe.toEcmaSource(effective, ""));
					System.err.println("OC=" + original.getClass() + ", EC=" + effective.getClass() + " not equals");
				}
				if (original.getClass() != effective.getClass()) {
					System.err.println("K=" + key);
					System.err.println("OS=" + Format.Describe.toEcmaSource(original, "") + ", ES=" + Format.Describe.toEcmaSource(effective, ""));
					System.err.println("O=" + original.getClass() + ", E=" + effective.getClass() + " class differs");
				}
			}
		}
	}

	static final List<BaseObject> createTestMappings() {

		final List<BaseObject> mappings = new ArrayList<>();
		{
			final BaseObject mapping = new BaseNativeObject();
			assert !mapping.baseHasKeysOwn() : "Expected NOT to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("number", BasePrimitiveNumber.PI)//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("date", Base.forDateMillis(Engine.fastTime() + 24L * 60L * 60_000L))//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("string", BaseString.STR_PINFINITY)//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("mappingEmpty", new BaseNativeObject())//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
					.putAppend("mappingEmpty", new BaseNativeObject())//
					.putAppend("mappingOne", mappings.get(0))//
					.putAppend("mappingTwo", mappings.get(1))//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}

		/**
		 *
		 */
		{
			final BaseObject mapping = new BaseNativeArray();
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(0, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BaseString.STR_FALSE);
			assert mapping.baseGet(0, null) == BaseString.STR_FALSE : "Should contain 'false'!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(1, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(3, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeArray()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
			;
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(0, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
					.putAppend(BasePrimitiveNumber.PI)//
					.putAppend(BasePrimitiveNumber.PI)//
					.putAppend(BasePrimitiveNumber.PI)//
					.putAppend(BasePrimitiveNumber.PI)//
			;
			assert mapping.baseGet(3, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}

		/**
		 *
		 */
		{
			final BaseObject mapping = new BaseNativeArray();
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(0, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BaseString.STR_FALSE);
			assert mapping.baseGet(0, null) == BaseString.STR_FALSE : "Should contain 'false'!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(1, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseList<Object> mapping = new BaseNativeArray();
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(3, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert !mapping.baseHasKeysOwn() : "Expected not to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseObject mapping = new BaseNativeArray()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
			;
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseNativeArray mapping = new BaseNativeArray()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
			;
			mapping.baseDefaultPush(BasePrimitiveNumber.PI);
			assert mapping.baseGet(0, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}
		{
			final BaseNativeArray mapping = new BaseNativeArray()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
					.putAppend(BasePrimitiveNumber.PI)//
					.putAppend(BasePrimitiveNumber.PI)//
					.putAppend(BasePrimitiveNumber.PI)//
					.putAppend(BasePrimitiveNumber.PI)//
			;
			assert mapping.baseGet(3, null) == BasePrimitiveNumber.PI : "Should contain PI!";
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}

		/**
		 *
		 */
		{
			final BaseObject mapping = new BaseNativeObject()//
					.putAppend("number", BasePrimitiveNumber.PI)//
					.putAppend("string", BaseString.STR_PINFINITY)//
					.putAppend("mappingEmpty", new BaseNativeObject())//
					.putAppend("mappingOne", mappings.get(0))//
					.putAppend("mappingTwo", mappings.get(1))//
					.putAppend("array", new BaseNativeArray())//
			;
			{
				final BaseList<Object> array = new BaseNativeArray()//
						.putAppend(BasePrimitiveNumber.PI)//
				;
				mapping.baseDefine("array1", array);
			}
			{
				final BaseList<Object> array = new BaseNativeArray()//
						.putAppend(BaseString.STR_FALSE)//
				;
				mapping.baseDefine("array1str", array);
			}
			{
				final BaseList<Object> array = new BaseNativeArray()//
						.putAppend(BasePrimitiveNumber.PI)//
						.putAppend(BasePrimitiveNumber.PI)//
				;
				mapping.baseDefine("array2", array);
			}
			{
				final BaseList<Object> array = new BaseNativeArray()//
						.putAppend(BasePrimitiveNumber.PI)//
						.putAppend(BasePrimitiveNumber.PI)//
						.putAppend(BasePrimitiveNumber.PI)//
				;
				mapping.baseDefine("array3", array);
			}
			assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
			mappings.add(mapping);
		}

		/**
		 *
		 */
		{
			final String invalidString = "\u0010 \u0011 Hello!";
			final byte[] invalidBytes = invalidString.getBytes();
			final TransferCopier invalidCopier = Transfer.wrapCopier(invalidBytes);
			{
				final ExtraBinary extra = new ExtraBinary(
						"dfdfdfdfd", //
						"zazazazaza",
						0L,
						invalidCopier);
				final BaseObject mapping = new BaseNativeObject()//
						.putAppend("extra", extra)//
				;
				assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
				mappings.add(mapping);
			}
			{
				final ExtraBinary extra = new ExtraBinary(
						"ijijijijij", //
						"ghghghghg",
						0L,
						invalidCopier);
				
				final BaseObject mapping = new BaseNativeObject()//
						.putAppend("extra", new ExtraExtra(extra.getIdentity(), extra))//
				;
				
				assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
				mappings.add(mapping);
			}
			{
				final BaseMessage message = Flow.binary("FLD-BINARY", "check", "text/plain", invalidCopier);

				final BaseObject mapping = new BaseNativeObject()//
						.putAppend("messageBinary", message)//
				;
				
				assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
				mappings.add(mapping);
			}
			{
				final BaseMessage message = Flow.character("FLD-BINARY", "check", invalidString);

				final BaseObject mapping = new BaseNativeObject()//
						.putAppend("messageTextual", message)//
				;
				
				assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
				mappings.add(mapping);
			}

			if (false) {
				final BaseObject mapping = new BaseNativeObject()//
						.putAppend("stringShort", "\u0010 \u0011 Hello!")//
				;
				assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
				mappings.add(mapping);
			}
			if (false) {
				final BaseObject mapping = new BaseNativeObject()//
						.putAppend("stringShort", "\u0010 \u0011 Hello!")//
						.putAppend("stringLonger", "\u0010 \u0011 Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello! Hello!")//
				;
				assert mapping.baseHasKeysOwn() : "Expected to have own properties!";
				mappings.add(mapping);
			}
		}

		return mappings;
	}
}
