package ru.myx.ae3.test.engine;

import ru.myx.ae3.Engine;
import ru.myx.ae3.help.Validate;
import ru.myx.io.DataOutputByteArrayReusable;

/**
 * 
 * @author myx
 * 
 */
public class TestEngineGuidEntropy {
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST ENGINE GUID ENTROPY" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			@SuppressWarnings("resource")
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			try {
				for (int i = 2000; i > 0; i--) {
					final String guid = Engine.createGuid();
					dba.writeInt( guid.hashCode() );
				}
			} finally {
				dba.close();
			}
			final byte[] buffer = dba.getBuffer();
			final int position = dba.getPosition();
			assert position == 8000;
			final byte[] check = new byte[position];
			System.arraycopy( buffer, 0, check, 0, position );
			final int entropy = Validate.Analyse.entropy( check );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
	}
}
