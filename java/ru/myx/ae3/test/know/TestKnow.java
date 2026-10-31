package ru.myx.ae3.test.know;

import ru.myx.ae3.test.TestAe3;

/**
 * 
 * @author myx
 * 
 */
public class TestKnow {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST KNOW" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		
		/**
		 * quick
		 */
		TestGuidBasics.main( args );
		TestGuidSerialization.main( args );
		TestGuidStream.main( args );
		TestWhirlpool.main( args );
		TestWhirlpoolCRC.main( args );
		
		/**
		 * slow
		 */
		TestGuidPartitioning.main( args );
		TestGuidEntropy.main( args );
		TestGuidUniqueness.main( args );
	}
	
}
