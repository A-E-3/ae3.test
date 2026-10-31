package ru.myx.ae3.test.xml;

import java.util.Arrays;
import java.util.List;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.help.Validate;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.xml.Xml;

class BinaryXml {
	private static void checkMapping(final BaseObject mapping) {
		System.out.println( ">> " + mapping.getClass() + ":: " + Ecma.toEcmaSourceCompact( mapping ) );
		for (final Boolean readable : new Boolean[] { Boolean.TRUE, Boolean.FALSE }) {
			final TransferCopier xml = Xml.toXmlBinary( "data", mapping, readable.booleanValue(), null, null, 0 );
			System.out.println( ">> >> xml: " + xml.toString() );
			{
				final BaseObject result = Xml.toBase( "test", xml, null, null, null, null );
				assert result != null : "NULL java value!";
				if (!Validate.mapsEqual( result, mapping )) {
					TestXml.compare( result, mapping );
					throw new Error( "Mapping fromXml(toXml()) error - should be equal, primitive="
							+ mapping
							+ ", xml="
							+ xml
							+ ", result="
							+ result );
				}
			}
			if (mapping.baseArray() == null) {
				final BaseObject result = Xml
						.toMap( "TEST-XML", xml, null, null, new BaseNativeObject(), null, null );
				assert result != null : "NULL java value!";
				if (!Validate.mapsEqual( result, mapping )) {
					TestXml.compare( result, mapping );
					throw new Error( "Mapping fromXml(toXml()) error - should be equal, primitive="
							+ mapping
							+ ", xml="
							+ xml
							+ ", result="
							+ result );
				}
			}
		}
	}
	
	private static void checkPrimitive(final BaseObject primitive) {
		System.out.println( ">> " + primitive.getClass() );
		for (final Boolean readable : Arrays.asList( new Boolean[] { Boolean.TRUE, Boolean.FALSE } )) {
			final TransferCopier xml = Xml.toXmlBinary( "data", primitive, readable.booleanValue(), null, null, 0 );
			final BaseObject result = Xml.toBase( "test", xml, null, null, null, null );
			assert result != null : "NULL java value!";
			if (result != primitive) {
				throw new Error( "Primitive fromXml(toXml()) error - should be strictly equal, primitive="
						+ primitive
						+ ", xml="
						+ xml
						+ ", result="
						+ result );
			}
		}
	}
	
	public static void main(final String[] args) throws Throwable {
		System.out.println( ">>>> >>>> BINARY XML" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		
		TestAe3.initAE3();
		
		{
			// just to initialize Engine
			Xml.toXmlBinary( "data", BaseObject.UNDEFINED, false, null, null, 0 );
		}
		{ // from boot: after exec instruction list
			final String test = "<xml><passed class=\"boolean\">true</passed></xml>";
			final TransferCopier binary = Transfer.createCopier( test.getBytes() );
			final BaseObject map = Xml.toBase( "tests", binary, null, null, null, null );
			if (map.baseGet( "passed", BaseObject.UNDEFINED ) != BaseObject.TRUE) {
				throw new Error( "BOOT: AEXML check: FAIL, value="
						+ map.baseGet( "passed", BaseObject.UNDEFINED )
						+ ", map="
						+ map.toString() );
			}
		}
		{ // from boot: after exec instruction list
			final String test = "<D:x xmlns:D=\"A\"><D:p class=\"boolean\">true</D:p></D:x>";
			final TransferCopier binary = Transfer.createCopier( test.getBytes() );
			final BaseObject map = Xml.toBase( "tests", binary, null, "A", null, null );
			if (map.baseGet( "p", BaseObject.UNDEFINED ) != BaseObject.TRUE) {
				throw new Error( "BOOT: AEXML check: FAIL, value="
						+ map.baseGet( "p", BaseObject.UNDEFINED )
						+ ", map="
						+ map.toString() );
			}
		}
		{
			System.out.println( "Checking primitive types:" );
			final BaseObject[] primitives = {
					BaseObject.UNDEFINED,
					BaseObject.NULL,
					BaseObject.FALSE,
					BaseObject.TRUE,
					BaseString.EMPTY,
					BaseString.STR_UNDEFINED,
					BasePrimitiveNumber.ZERO,
					BasePrimitiveNumber.PI,
					BasePrimitiveNumber.NAN };
			for (final BaseObject primitive : primitives) {
				BinaryXml.checkPrimitive( primitive );
			}
			System.out.println( "=========================" );
		}
		{
			System.out.println( "Checking mappings:" );
			final List<BaseObject> mappings = TestXml.createTestMappings();
			for (final BaseObject mapping : mappings) {
				BinaryXml.checkMapping( mapping );
			}
			System.out.println( "=========================" );
		}
		{
			// check real XML file
			final TransferCopier copier = Transfer.createCopier( BinaryXml.class.getResourceAsStream( "test-rss.xml" ) );
			for (final BaseObject result : new BaseObject[] {
					//
					Xml.toMap( "test-rss", copier, null, null, new BaseNativeObject(), null, null ),
					//
					Xml.toBase( "test-rss", copier, null, null, null, null ),
			//
			}) {
				final BaseObject channel = result.baseGet( "channel", BaseObject.UNDEFINED );
				assert channel != null;
				assert channel != BaseObject.UNDEFINED;
				
				assert Base.getString( channel, "title", "" ).trim().equals( "Lenta.ru: Новости" );
				
				assert !channel.baseGet( "image", BaseObject.UNDEFINED ).baseIsPrimitive();
				assert channel.baseGet( "image", BaseObject.UNDEFINED ).baseIsExtensible();
				
				assert channel.baseGet( "item", BaseObject.UNDEFINED ).baseArray() != null;
				assert channel.baseGet( "item222", BaseObject.UNDEFINED ).baseArray() == null;
			}
		}
	}
}
