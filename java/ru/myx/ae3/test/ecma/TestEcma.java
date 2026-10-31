package ru.myx.ae3.test.ecma;

import ru.myx.ae3.test.TestAe3;
import ru.myx.renderer.ecma.TestVarParser;

/**
 * @author myx
 * 
 */
public class TestEcma {
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST ECMA" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestVarParser.test();
		}
		{
			TestEcmaSource.main( new String[0] );
			
			TestEcmaCompare.main( new String[0] );
			
			TestEcmaImplBasics.main( new String[0] );
			
			TestEcmaImplScripts.main( new String[0] );
		}
	}
	
}
