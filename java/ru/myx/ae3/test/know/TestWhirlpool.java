package ru.myx.ae3.test.know;

import java.nio.charset.StandardCharsets;

import ru.myx.ae3.Engine;

/**
 * 
 * @author myx
 * 
 */
final class TestWhirlpool {
	
	private static final StringBuilder dumpHex(final byte[] bytes) {
		final StringBuilder result = new StringBuilder();
		for (final byte b : bytes) {
			final int h = (b & 0xF0) >> 4;
			result.append( (char) (h > 9
					? 'A' + h - 10
					: '0' + h) );
			final int l = b & 0x0F;
			result.append( (char) (l > 9
					? 'A' + l - 10
					: '0' + l) );
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static final void main(final String[] args) {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST KNOW WHIRLPOOL DIGEST" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		TestWhirlpool
				.test( "",
						"19FA61D75522A4669B44E39C1D2E1726C530232130D407F89AFEE0964997F7A73E83BE698B288FEBCF88E3E03C4F0757EA8964E59B63D93708B138CC42A66EB3" );
		TestWhirlpool
				.test( "The quick brown fox jumps over the lazy dog",
						"B97DE512E91E3828B40D2B0FDCE9CEB3C4A71F9BEA8D88E75C4FA854DF36725FD2B52EB6544EDCACD6F8BEDDFEA403CB55AE31F03AD62A5EF54E42EE82C3FB35" );
	}
	
	private static final void test(final String text, final String check) {
		System.out.println( "T: " + text );
		final byte[] bytes = text.getBytes( StandardCharsets.US_ASCII );
		final String hexM;
		{
			final ru.myx.ae3.know.WhirlpoolDigest digest = new ru.myx.ae3.know.WhirlpoolDigest();
			digest.update( bytes );
			final byte[] result = digest.digest();
			hexM = TestWhirlpool.dumpHex( result ).toString();
			System.out.println( "M: " + hexM );
		}
		final String hexB;
		{
			final org.bouncycastle.crypto.digests.WhirlpoolDigest digest = new org.bouncycastle.crypto.digests.WhirlpoolDigest();
			digest.update( bytes, 0, bytes.length );
			final byte[] result = new byte[digest.getDigestSize()];
			digest.doFinal( result, 0 );
			hexB = TestWhirlpool.dumpHex( result ).toString();
			System.out.println( "B: " + hexB );
		}
		assert hexM.equals( hexB );
		assert hexM.equals( check );
	}
}
