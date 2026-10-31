package ru.myx.ae3.test.xml;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.help.Validate;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.xml.Xml;

class TextXml {
	private static void checkMapping(final BaseObject mapping) {
		System.out.println( "\n\n\n>> " + mapping.getClass() + ":: " + Ecma.toEcmaSourceCompact( mapping ) );
		for (final Boolean readable : new Boolean[] { Boolean.TRUE, Boolean.FALSE }) {
			final String xml = Xml.toXmlString( "data", mapping, readable.booleanValue() );
			System.out.println( ">> >> xml: " + xml );
			{
				final BaseObject result = Xml.toBase( "test", xml, null, null, null );
				assert result != null : "NULL java value!";
				if (!Validate.mapsEqual( result, mapping )) {
					TestXml.compare( result, mapping );
					throw new Error( "Mapping fromXml(toXml()) error - should be equal, xml="
							+ xml
							+ ", primitive="
							+ Format.Describe.toEcmaSource( mapping, "" )
							+ ", result="
							+ Format.Describe.toEcmaSource( result, "" ) );
				}
			}
			if (mapping.baseArray() == null) {
				final BaseObject result = Xml.toMap( "TEST-XML", xml, null, new BaseNativeObject(), null, null );
				assert result != null : "NULL java value!";
				if (!Validate.mapsEqual( result, mapping )) {
					TestXml.compare( result, mapping );
					throw new Error( "Mapping fromXml(toXml()) error - should be equal, primitive="
							+ Format.Describe.toEcmaSource( mapping, "" )
							+ ", xml="
							+ xml
							+ ", result="
							+ Format.Describe.toEcmaSource( result, "" ) );
				}
			}
		}
	}
	
	private static void checkPrimitive(final BaseObject primitive) {
		System.out.println( "\n\n\n>> " + primitive.getClass() );
		for (final boolean readable : new boolean[] { true, false }) {
			final String xml = Xml.toXmlString( "data", primitive, readable );
			System.out.println( ">> >> >> xml: " + xml );
			final BaseObject result = Xml.toBase( "test", xml, null, null, null );
			assert result != null : "NULL java value!";
			if (result != primitive) {
				throw new Error( "Primitive fromXml(toXml()) error - should be strictly equal, primitive="
						+ primitive
						+ ", xml="
						+ xml
						+ ", result="
						+ Format.Describe.toEcmaSource( result, "" ) );
			}
		}
	}
	
	private static void checkSimple(final BaseObject simple) {
		System.out.println( "\n\n\n>> " + simple.getClass() );
		for (final Boolean readable : Arrays.asList( new Boolean[] { Boolean.TRUE, Boolean.FALSE } )) {
			final String xml = Xml.toXmlString( "data", simple, readable.booleanValue() );
			final BaseObject result = Xml.toBase( "test", xml, null, null, null );
			assert result != null : "NULL java value!";
			if (!simple.equals( result )) {
				throw new Error( "Primitive fromXml(toXml()) error - should be equal, simple="
						+ simple
						+ ", xml="
						+ xml
						+ ", result="
						+ result );
			}
		}
	}
	
	public static void main(final String[] args) throws Throwable {
		System.out.println( "\n\n\n>>>> >>>> TEXT XML" );
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
			Xml.toXmlString( "data", BaseObject.UNDEFINED, false );
		}
		{ // from boot: after exec instruction list
			final String string = "<xml><passed class=\"boolean\">true</passed></xml>";
			final BaseObject map = Xml.toBase( "tests", string, null, null, null );
			if (map.baseGet( "passed", BaseObject.UNDEFINED ) != BaseObject.TRUE) {
				throw new Error( "BOOT: AEXML check: FAIL, value="
						+ map.baseGet( "passed", BaseObject.UNDEFINED )
						+ ", map="
						+ Format.Describe.toEcmaSource( map, "" )
						+ ", xml="
						+ string );
			}
		}
		{ // from boot: after exec instruction list
			final String string = "<D:x xmlns:D=\"A\"><D:p class=\"boolean\">true</D:p></D:x>";
			final BaseObject map = Xml.toBase( "tests", string, "A", null, null );
			if (map.baseGet( "p", BaseObject.UNDEFINED ) != BaseObject.TRUE) {
				throw new Error( "BOOT: AEXML check: FAIL, value="
						+ map.baseGet( "p", BaseObject.UNDEFINED )
						+ ", map="
						+ Format.Describe.toEcmaSource( map, "" ) );
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
					BasePrimitiveNumber.NAN,
					Base.forString( "string" ) };
			for (final BaseObject primitive : primitives) {
				TextXml.checkPrimitive( primitive );
			}
			System.out.println( "=========================" );
		}
		{
			System.out.println( "Checking simple types:" );
			final BaseObject[] simples = {
					Base.forDate( new Date( 0 ) ),
					Base.forDate( new Date( 10000 ) ),
					Base.forDate( new Date( 10000000 ) ), };
			for (final BaseObject simple : simples) {
				TextXml.checkSimple( simple );
			}
			System.out.println( "=========================" );
		}
		{
			System.out.println( "Checking mappings:" );
			final List<BaseObject> mappings = TestXml.createTestMappings();
			for (final BaseObject mapping : mappings) {
				TextXml.checkMapping( mapping );
			}
			System.out.println( "=========================" );
		}
		{
			// check real XML file
			final String xml = Transfer.createCopier( BinaryXml.class.getResourceAsStream( "test-rss.xml" ) ) //
					.nextCopy() //
					.toString( "windows-1251" );
			for (final BaseObject result : new BaseObject[] {
					//
					Xml.toMap( "test-rss", xml, null, new BaseNativeObject(), null, null ),
					//
					Xml.toBase( "test-rss", xml, null, null, null ),
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
