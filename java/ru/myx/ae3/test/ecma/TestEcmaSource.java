package ru.myx.ae3.test.ecma;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BasePrimitiveString;
import ru.myx.ae3.base.BaseProperty;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;

class TestEcmaSource {
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST Ecma.toSource" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{ // from boot thread: before memory printout
			final ExecProcess ctx = Exec.createProcess( null, "Evaluate test" );
			if (Evaluate.evaluateObject( "2+2*2 == 6", ctx, null ) == BaseObject.FALSE) {
				throw new Error( "BOOT: EVAL CHECK: FAIL, (2+2*2 != 6), actually evaluates to: "
						+ Evaluate.evaluateObject( "2+2*2", ctx, null ) );
			}
		}
		final ExecProcess ctx = Exec.currentProcess();
		{
			assert "undefined".equals( Ecma.toEcmaSourceCompact( BaseObject.UNDEFINED ) );
			assert Evaluate.evaluateObject( "undefined", ctx, null ) == BaseObject.UNDEFINED : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "null".equals( Ecma.toEcmaSourceCompact( BaseObject.NULL ) );
			assert Evaluate.evaluateObject( "null", ctx, null ) == BaseObject.NULL : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "false".equals( Ecma.toEcmaSourceCompact( BaseObject.FALSE ) );
			assert Evaluate.evaluateObject( "false", ctx, null ) == BaseObject.FALSE : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "true".equals( Ecma.toEcmaSourceCompact( BaseObject.TRUE ) );
			assert Evaluate.evaluateObject( "true", ctx, null ) == BaseObject.TRUE : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "0".equals( Ecma.toEcmaSourceCompact( BasePrimitiveNumber.ZERO ) );
			assert Evaluate.evaluateObject( "0", ctx, null ) == BasePrimitiveNumber.ZERO : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "1".equals( Ecma.toEcmaSourceCompact( BasePrimitiveNumber.ONE ) );
			assert Evaluate.evaluateObject( "1", ctx, null ) == BasePrimitiveNumber.ONE : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "-1".equals( Ecma.toEcmaSourceCompact( BasePrimitiveNumber.MONE ) );
			assert Evaluate.evaluateObject( "-1", ctx, null ) == BasePrimitiveNumber.MONE : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "\"\"".equals( Ecma.toEcmaSourceCompact( BaseString.EMPTY ) );
			assert Evaluate.evaluateObject( "''", ctx, null ) == BaseString.EMPTY : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
			
			assert "\"1\"".equals( Ecma.toEcmaSourceCompact( BaseString.STR_ONE ) );
			assert Evaluate.evaluateObject( "'1'", ctx, null ) == BaseString.STR_ONE : "class="
					+ Evaluate.evaluateObject( "undefined", ctx, null ).getClass().getName();
		}
		{
			/**
			 * Secret escaping feature and it's support
			 */
			final BasePrimitiveString string = Base.forString( "<script></script>" );
			final String source = Ecma.toEcmaSourceCompact( string );
			assert "\"<script></scr\\x69pt>\"".equals( source ) : "Invalid escaping: " + source;
			final BaseObject result = Evaluate.evaluateObject( source, ctx, null );
			assert result == string : "Invalid parsing: " + result;
		}
		{
			final BaseList<Object> object = new BaseNativeArray();
			assert "[]".equals( Ecma.toEcmaSourceCompact( object ) );
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray() != null;
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray().length() == 0;
			
			object.add( BaseObject.UNDEFINED );
			assert "[undefined]".equals( Ecma.toEcmaSourceCompact( object ) );
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray() != null;
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray().length() == 1;
			
			object.add( BaseObject.TRUE );
			assert "[undefined,true]".equals( Ecma.toEcmaSourceCompact( object ) );
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray() != null;
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray().length() == 2;
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray()
					.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseArray().get( 1 ) == Boolean.TRUE;
		}
		{
			final BaseObject object = new BaseNativeObject();
			assert "{}".equals( Ecma.toEcmaSourceCompact( object ) );
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseGet( "a",
					BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			Base.put( object, "a", BaseObject.TRUE );
			assert "{\"a\":true}".equals( Ecma.toEcmaSourceCompact( object ) );
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseGet( "a",
					BaseObject.UNDEFINED ) == BaseObject.TRUE;
			
			Base.put( object, "b", BaseObject.TRUE, BaseProperty.ATTRS_MASK_WND );
			assert "{\"a\":true}".equals( Ecma.toEcmaSourceCompact( object ) );
			
			Base.put( object, "d", "строка" );
			assert "{\"a\":true,\"d\":\"строка\"}".equals( Ecma.toEcmaSourceCompact( object ) );
			assert Evaluate.evaluateObject( Ecma.toEcmaSourceCompact( object ), ctx, null ).baseGet( "a",
					BaseObject.UNDEFINED ) == BaseObject.TRUE;
		}
	}
}
