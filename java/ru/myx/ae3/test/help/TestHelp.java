package ru.myx.ae3.test.help;

import ru.myx.ae3.Engine;

/**
 * 
 * @author myx
 * 
 */
public class TestHelp {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> TEST AE3 HELP" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		TestHelpConvert.main( args );
		TestHelpBase64.main( args );
		TestHelpText.main( args );
		TestHelpHtml.main( args );
	}
	
}
