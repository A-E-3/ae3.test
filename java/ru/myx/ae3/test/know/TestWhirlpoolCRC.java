package ru.myx.ae3.test.know;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ru.myx.ae3.Engine;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.binary.WrapCopier;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.know.Guid;

/**
 * @author myx
 * 
 */
final class TestWhirlpoolCRC {
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST KNOW WHIRLPOOL CRC" );
		final byte[] TEST_BUFFER = new byte[1024];
		final Random rnd = new Random();
		rnd.setSeed( 2206241 );
		rnd.nextBytes( TEST_BUFFER );
		final Map<Guid, Guid> check = new HashMap<>();
		int count = 0;
		long size = 0;
		for (int s = 1024; s > 60; s -= 1) {
			for (int i = TEST_BUFFER.length - s; i >= 0; i -= 1) {
				final TransferCopier tail = new WrapCopier( TEST_BUFFER, i, s );
				final Guid guid = Guid.forBinaryChecksum( tail );
				if (check.put( guid, guid ) != null) {
					System.out.println( "BAD CRC GUID(" + s + "," + i + "): " + guid );
					assert false;
				}
				count++;
				size += tail.length();
			}
		}
		System.out.println( "Finished: count="
				+ Format.Compact.toDecimal( count )
				+ ", size="
				+ Format.Compact.toBytes( size ) );
	}
}
