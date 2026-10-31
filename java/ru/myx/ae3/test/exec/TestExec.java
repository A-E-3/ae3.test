package ru.myx.ae3.test.exec;

import ru.myx.ae3.test.TestAe3;

/**
 * 
 * @author myx
 * 
 */
public class TestExec {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST EXEC" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestExecFunction.main( new String[0] );
		}
		{
			TestExecArguments.main( new String[0] );
		}
		{
			TestExecCompiler.main( new String[0] );
		}
	}
	
}
