package ru.myx.ae3.test.know;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;

import ru.myx.ae3.Engine;
import ru.myx.ae3.know.Guid;
import ru.myx.io.DataInputByteArrayFast;

/**
 * @author myx
 * 
 */
class TestGuidSerialization {
	
	private static void checkGuids(final Guid nguid) throws Throwable {
		{
			final Guid check = Guid.fromBase64( nguid.toBase64() );
			if (!check.equals( nguid )) {
				System.err.println( "TESTS: (" + nguid.getType() + ")\t" + nguid );
				System.err.println( "TESTS: (" + check.getType() + ")\t" + check );
				throw new UnsupportedOperationException( "TESTS: STRING: Guids are not equal after serialization!" );
			}
		}
		{
			final Guid check = Guid.fromName( nguid.toName() );
			if (!check.equals( nguid )) {
				System.err.println( "TESTS: " + nguid.toName() );
				System.err.println( "TESTS: " + check.toName() );
				throw new UnsupportedOperationException( "TESTS: KEY: Guids are not equal after serialization!" );
			}
		}
		{
			final Guid check = Guid.fromHex( nguid.toHex() );
			if (!check.equals( nguid )) {
				System.err.println( "TESTS: " + nguid.toHex() );
				System.err.println( "TESTS: " + check.toHex() );
				throw new UnsupportedOperationException( "TESTS: HEX: Guids are not equal after serialization!" );
			}
		}
		{
			final Guid check = Guid.readGuid( nguid.toInputStream() );
			if (!check.equals( nguid )) {
				throw new UnsupportedOperationException( "TESTS: Guids are not equal after serialization!" );
			}
		}
		{
			final Guid check = Guid.readGuid( new ByteArrayInputStream( nguid.toBytes() ) );
			if (!check.equals( nguid )) {
				throw new UnsupportedOperationException( "TESTS: Guids are not equal after serialization!" );
			}
		}
		{
			final Guid check = Guid.readGuid( (DataInput) new DataInputByteArrayFast( nguid.toBytes() ) );
			if (!check.equals( nguid )) {
				throw new UnsupportedOperationException( "TESTS: Guids are not equal after serialization!" );
			}
		}
		{
			final byte[] buffer = new byte[Guid.MAX_LENGTH];
			Guid.writeGuid( nguid, buffer, 0 );
			final Guid check = Guid.readGuid( buffer );
			if (!check.equals( nguid )) {
				throw new UnsupportedOperationException( "TESTS: Guids are not equal after serialization!" );
			}
		}
		{
			try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				Guid.writeGuid( nguid, bos );
				final Guid check = Guid.readGuid( new ByteArrayInputStream( bos.toByteArray() ) );
				if (!check.equals( nguid )) {
					throw new UnsupportedOperationException( "TESTS: Guids are not equal after serialization!" );
				}
			}
		}
		{
			try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				final DataOutput dos = new DataOutputStream( bos );
				Guid.writeGuid( nguid, dos );
				final Guid check = Guid.readGuid( new ByteArrayInputStream( bos.toByteArray() ) );
				if (!check.equals( nguid )) {
					throw new UnsupportedOperationException( "TESTS: Guids are not equal after serialization!" );
				}
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String... args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST KNOW GUID SERIALISATION/MATERIALISATION" );
		{
			TestGuidSerialization.checkGuids( Guid.createGuid384() );
			TestGuidSerialization.checkGuids( Guid.createGuid384() );
			TestGuidSerialization.checkGuids( Guid.createGuid384() );
			
			TestGuidSerialization.checkGuids( Guid.createGuid184() );
			TestGuidSerialization.checkGuids( Guid.createGuid184() );
			TestGuidSerialization.checkGuids( Guid.createGuid184() );
		}
		for (int i = 10000; i > 0; --i) {
			TestGuidSerialization.checkGuids( Guid.forJavaInteger( i ) );
			TestGuidSerialization.checkGuids( Guid.forJavaLong( i + 2206241L ) );
			TestGuidSerialization.checkGuids( Guid.forJavaDateMillis( i ) );
			TestGuidSerialization.checkGuids( Guid.forJavaDateMillis( i + 2206241L ) );
			TestGuidSerialization.checkGuids( Guid.forString( String.valueOf( i ) ) );
			TestGuidSerialization.checkGuids( Guid.forString( i + "2206241L" ) );
			TestGuidSerialization.checkGuids( Guid.forString( i + "abcdef" ) );
			TestGuidSerialization.checkGuids( Guid.forString( i + "яшщz" ) );
		}
		{
			final Guid nguid = Guid.readGuid( (byte[]) null );
			TestGuidSerialization.checkGuids( nguid );
		}
	}
}
