package ru.myx.ae3.test.help;

import java.nio.charset.StandardCharsets;

import ru.myx.ae3.Engine;
import ru.myx.util.Base64;

class TestHelpBase64 {
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST AE3 HELP BASE64" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		for (final String text : new String[] {
				"",
				"1",
				"12",
				"123",
				"1234",
				"12345",
				"123456",
				" 1 ",
				" 1 2 ",
				"ABCDEF ABCDEF ABCDEF" }) {
			System.out.println( ">>>> >>>> TEST AE3 HELP BASE64 >>>> " + text );
			for (final boolean lines : new boolean[] { true, false }) {
				final String encoded = Base64.encode( text.getBytes( StandardCharsets.US_ASCII ), lines );
				final byte[] decodedBytes = Base64.decode( encoded, lines );
				final String decoded = new String( decodedBytes, StandardCharsets.US_ASCII );
				assert text.equals( decoded ) : text + " != " + decoded;
			}
		}
		System.out.println( ">>>> >>>> TEST AE3 HELP BASE64 done." );
	}
}
