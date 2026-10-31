package ru.myx.ae3.test.know;

import java.util.HashMap;
import java.util.Map;

import ru.myx.ae3.Engine;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.know.Guid;

/**
 * @author myx
 * 
 */
class TestGuidUniqueness {
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST KNOW GUID UNIQUENESS" );
		{
			System.out.println( ">>>> >>>> TEST KNOW GUID UNIQUENESS GUID184" );
			Runtime.getRuntime().gc();
			final Map<Guid, Object> GUIDS = new HashMap<>( 1000000, 0.99f );
			final Object dummy = new Object();
			final long started = System.currentTimeMillis();
			for (int i = 1000000 / 2; i > 0; --i) {
				if (i % 100000 == 99999) {
					Runtime.getRuntime().gc();
					System.out.println( i
							+ " left (ram="
							+ Format.Compact.toBytes( Runtime.getRuntime().freeMemory() )
							+ ")..." );
				}
				if (GUIDS.put( Guid.createGuid184(), dummy ) != null) {
					System.out.println( "FAILED" );
					assert false;
				}
			}
			System.out.println( Format.Compact.toPeriod( System.currentTimeMillis() - started )
					+ " took, "
					+ GUIDS.size()
					+ " guids checked." );
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW GUID UNIQUENESS GUID384" );
			Runtime.getRuntime().gc();
			final Map<Guid, Object> GUIDS = new HashMap<>( 1000000, 0.99f );
			final Object dummy = new Object();
			final long started = System.currentTimeMillis();
			for (int i = 1000000 / 2; i > 0; --i) {
				if (i % 100000 == 99999) {
					Runtime.getRuntime().gc();
					System.out.println( i
							+ " left (ram="
							+ Format.Compact.toBytes( Runtime.getRuntime().freeMemory() )
							+ ")..." );
				}
				if (GUIDS.put( Guid.createGuid384(), dummy ) != null) {
					System.out.println( "FAILED" );
					assert false;
				}
			}
			System.out.println( Format.Compact.toPeriod( System.currentTimeMillis() - started )
					+ " took, "
					+ GUIDS.size()
					+ " guids checked." );
		}
		for (final long offset : new long[] { 0, 4255397, 42553975913621L }) {
			System.out.println( ">>>> >>>> TEST KNOW GUID UNIQUENESS INT+" + offset );
			Runtime.getRuntime().gc();
			final Map<Guid, Object> GUIDS = new HashMap<>( 1000000, 0.99f );
			final Object dummy = new Object();
			final long started = System.currentTimeMillis();
			for (int i = 1000000 / 2; i > 0; --i) {
				if (i % 100000 == 99999) {
					Runtime.getRuntime().gc();
					System.out.println( i
							+ " left (ram="
							+ Format.Compact.toBytes( Runtime.getRuntime().freeMemory() )
							+ ")..." );
				}
				if (GUIDS.put( Guid.forJavaLong( i + offset ), dummy ) != null) {
					System.out.println( "FAILED" );
					assert false;
				}
			}
			System.out.println( Format.Compact.toPeriod( System.currentTimeMillis() - started )
					+ " took, "
					+ GUIDS.size()
					+ " guids checked." );
		}
		for (final long offset : new long[] {
				0,
				System.currentTimeMillis(),
				4255397,
				42553975913621L,
				4255397591362142637L }) {
			System.out.println( ">>>> >>>> TEST KNOW GUID UNIQUENESS DATE+" + offset );
			Runtime.getRuntime().gc();
			final Map<Guid, Object> GUIDS = new HashMap<>( 1000000, 0.99f );
			final Object dummy = new Object();
			final long started = System.currentTimeMillis();
			for (int i = 1000000 / 2; i > 0; --i) {
				if (i % 100000 == 99999) {
					Runtime.getRuntime().gc();
					System.out.println( i
							+ " left (ram="
							+ Format.Compact.toBytes( Runtime.getRuntime().freeMemory() )
							+ ")..." );
				}
				if (GUIDS.put( Guid.forJavaDateMillis( i + offset ), dummy ) != null) {
					System.out.println( "FAILED" );
					assert false;
				}
			}
			System.out.println( Format.Compact.toPeriod( System.currentTimeMillis() - started )
					+ " took, "
					+ GUIDS.size()
					+ " guids checked." );
		}
	}
}
