package ru.myx.ae3.test.act;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BasePrimitiveString;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.ExecProcessVariable;

class TestContextVariable {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST CONTEXT VARIABLES" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			/**
			 * inheritable
			 */
			final ExecProcess root = Exec.getRootProcess();
			final ExecProcessVariable<BasePrimitiveString> s1 = Exec.createProcessVariable( "s1", true );
			final ExecProcessVariable<BasePrimitiveString> s2 = Exec.createProcessVariable( "s2", true );
			final ExecProcessVariable<BasePrimitiveString> s3 = Exec.createProcessVariable( "s3", true );
			final ExecProcessVariable<BasePrimitiveString> s4 = Exec.createProcessVariable( "s4", false );
			
			assert s3.baseValue( root ) == null : "Variable should not be set: " + s3.baseValue( root );
			assert s4.baseValue( root ) == null : "Variable should not be set: " + s4.baseValue( root );
			
			s3.execSet( root, BaseString.STR_NULL );
			s4.execSet( root, BaseString.STR_NULL );
			
			assert s3.baseValue( root ) == BaseString.STR_NULL : "Variable should be set to 'null': "
					+ s3.baseValue( root );
			assert s4.baseValue( root ) == BaseString.STR_NULL : "Variable should be set to 'null': "
					+ s4.baseValue( root );
			
			final ExecProcess p1 = Exec.createProcess( root, "p1" );
			final ExecProcess p2 = Exec.createProcess( root, "p2" );
			
			assert s3.baseValue( p1 ) == BaseString.STR_NULL : "Variable should be set to 'null': "
					+ s3.baseValue( p1 );
			assert s3.baseValue( p2 ) == BaseString.STR_NULL : "Variable should be set to 'null': "
					+ s3.baseValue( p2 );
			
			assert s1.baseValue( p1 ) == null : "Variable should not be set: " + s1.baseValue( p1 );
			assert s2.baseValue( p1 ) == null : "Variable should not be set: " + s2.baseValue( p1 );
			assert s4.baseValue( p1 ) == null : "Variable should not be set: " + s4.baseValue( p1 );
			assert s1.baseValue( p2 ) == null : "Variable should not be set: " + s1.baseValue( p2 );
			assert s2.baseValue( p2 ) == null : "Variable should not be set: " + s2.baseValue( p2 );
			assert s4.baseValue( p2 ) == null : "Variable should not be set: " + s4.baseValue( p2 );
			
			s1.execSet( p1, BaseString.STR_FALSE );
			
			assert s1.baseValue( p1 ) == BaseString.STR_FALSE : "Variable should be set to 'false': "
					+ s1.baseValue( p1 );
			assert s2.baseValue( p1 ) == null : "Variable should not be set: " + s2.baseValue( p1 );
			assert s4.baseValue( p1 ) == null : "Variable should not be set: " + s4.baseValue( p1 );
			assert s1.baseValue( p2 ) == null : "Variable should not be set: " + s1.baseValue( p2 );
			assert s2.baseValue( p2 ) == null : "Variable should not be set: " + s2.baseValue( p2 );
			assert s4.baseValue( p2 ) == null : "Variable should not be set: " + s4.baseValue( p2 );
		}
	}
}
