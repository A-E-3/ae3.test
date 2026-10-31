package ru.myx.ae3.test.engine;

import ru.myx.ae3.Engine;
import ru.myx.ae3.help.Validate;
import ru.myx.io.DataOutputByteArrayReusable;

/**
 * 
 * @author myx
 * 
 */
public class TestEngineRandomEntropy {
	
	private static int getEntropy(final DataOutputByteArrayReusable dba) {
		dba.close();
		final byte[] buffer = dba.getBuffer();
		final int position = dba.getPosition();
		assert position == 8000;
		final byte[] check = new byte[position];
		System.arraycopy( buffer, 0, check, 0, position );
		final int entropy = Validate.Analyse.entropy( check );
		return entropy;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST KNOW ACM RANDOM ENTROPY" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 2000; i > 0; i--) {
				dba.writeInt( Engine.createRandom() );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 8000; i > 0; i--) {
				dba.writeByte( (byte) Engine.createRandom( 256 ) );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 8000; i > 0; i--) {
				dba.writeByte( (byte) (Engine.createRandom( 65536 ) >>> 8) );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 4000; i > 0; i--) {
				dba.writeShort( (short) Engine.createRandom( 65536 ) );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 4000; i > 0; i--) {
				dba.writeShort( (short) Engine.createRandom( Integer.MAX_VALUE ) );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 1000; i > 0; i--) {
				dba.writeLong( Engine.createRandomLong() );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = 2000; i > 0; i--) {
				final double rnd = Engine.createRandomDouble();
				assert rnd >= 0.0 : "must be >= 0.0, value: " + rnd;
				assert rnd < 1.0 : "must be < 1.0, value: " + rnd;
				final int intValue = (int) ((long) (rnd * ((double) Integer.MAX_VALUE - (double) Integer.MIN_VALUE)) + Integer.MIN_VALUE);
				dba.writeInt( intValue );
			}
			final int entropy = TestEngineRandomEntropy.getEntropy( dba );
			assert entropy > 99 : "Bad random entropy: " + entropy;
		}
	}
}
