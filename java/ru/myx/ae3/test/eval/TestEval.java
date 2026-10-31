package ru.myx.ae3.test.eval;

import ru.myx.ae3.test.TestAe3;

/**
 * 
 */
public class TestEval {
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public final static void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST EVAL" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestEvalParseTokens.main( new String[0] );
			
			TestEvalExpression.main( new String[0] );
			
			TestEvalErrorSize.main( new String[0] );
			
			TestEvalOptimiser.main( new String[0] );
		}
	}
}
