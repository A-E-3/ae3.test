package ru.myx.ae3.test.act;

import ru.myx.ae3.Engine;

/**
 * 
 * @author myx
 * 
 */
public class TestAct {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> TEST ACT" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestContextVariable.main( new String[0] );
			TestContextIsolation.main( new String[0] );
			TestContextRuntimeIsolation.main( new String[0] );
			TestContextOutput.main( new String[0] );
			TestContextInheritance.main( new String[0] );
		}
	}
	
}
