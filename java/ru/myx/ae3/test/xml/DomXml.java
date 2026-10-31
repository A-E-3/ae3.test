package ru.myx.ae3.test.xml;

import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Element;

import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.help.Dom;
import ru.myx.ae3.help.Validate;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.xml.Xml;

class DomXml {
	private static void checkMapping(final BaseObject mapping) {
		System.out.println( ">> " + mapping.getClass() + ":: " + Ecma.toEcmaSourceCompact( mapping ) );
		for (final Boolean readable : new Boolean[] { Boolean.TRUE, Boolean.FALSE }) {
			for (final Element xml : new Element[] { null,//
					Xml.toElement( "data", //
							mapping,
							readable.booleanValue() ), //
					Xml.toElement( "data", //
							mapping,
							readable.booleanValue(),
							null,
							null,
							64 ), //
			}) {
				if (xml == null) {
					continue;
				}
				System.out.println( ">> >> xml: " + Dom.toXmlCompact( xml ) );
				{
					final BaseObject result = Xml.toBase( xml, null, null, null, null );
					assert result != null : "NULL java value!";
					if (!Validate.mapsEqual( result, mapping )) {
						TestXml.compare( result, mapping );
						throw new Error( "Mapping fromXml(toXml()) error - should be equal, mapping="
								+ Ecma.toEcmaSourceCompact( mapping )
								+ ", xml="
								+ Dom.toXmlCompact( xml )
								+ ", result="
								+ Ecma.toEcmaSourceCompact( result ) );
					}
				}
			}
		}
	}
	
	private static void checkPrimitive(final BaseObject primitive) {
		System.out.println( ">> " + primitive.getClass() );
		for (final Boolean readable : Arrays.asList( new Boolean[] { Boolean.TRUE, Boolean.FALSE } )) {
			final Element xml = Xml.toElement( "data", primitive, readable.booleanValue() );
			final BaseObject result = Xml.toBase( xml, null, null, null, null );
			assert result != null : "NULL java value!";
			if (result != primitive) {
				throw new Error( "Primitive fromXml(toXml()) error - should be strictly equal, primitive="
						+ primitive
						+ ", primitiveClass="
						+ primitive.getClass().getName()
						+ ", element="
						+ xml
						+ ", xml="
						+ Dom.toXmlCompact( xml )
						+ ", result="
						+ result
						+ ", resultClass="
						+ result.getClass().getName() );
			}
		}
	}
	
	public static void main(final String[] args) throws Throwable {
		System.out.println( ">>>> >>>> DOM XML" );
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
			Xml.toElement( "data", BaseObject.UNDEFINED, false );
		}
		{ // from boot: after exec instruction list
			final Element element = Dom.toElement( "<xml><passed class=\"boolean\">true</passed></xml>" );
			final BaseObject map = Xml.toBase( element, null, null, null, null );
			if (map.baseGet( "passed", BaseObject.UNDEFINED ) != BaseObject.TRUE) {
				throw new Error( "BOOT: AEXML check: FAIL, value="
						+ map.baseGet( "passed", BaseObject.UNDEFINED )
						+ ", map="
						+ map.toString() );
			}
		}
		{ // from boot: after exec instruction list
			final Element element = Dom.toElement( "<D:x xmlns:D=\"A\"><D:p class=\"boolean\">true</D:p></D:x>" );
			final BaseObject map = Xml.toBase( element, null, "A", null, null );
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
				DomXml.checkPrimitive( primitive );
			}
			System.out.println( "=========================" );
		}
		{
			System.out.println( "Checking mappings:" );
			final List<BaseObject> mappings = TestXml.createTestMappings();
			for (final BaseObject mapping : mappings) {
				DomXml.checkMapping( mapping );
			}
			System.out.println( "=========================" );
		}
	}
}
