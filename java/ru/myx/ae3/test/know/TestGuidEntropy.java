package ru.myx.ae3.test.know;

import java.io.ByteArrayOutputStream;

import ru.myx.ae3.Engine;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.help.Validate;
import ru.myx.ae3.know.Guid;
import ru.myx.io.DataOutputByteArrayReusable;

/**
 * @author myx
 * 
 */
class TestGuidEntropy {
	
	private static final int	TEST_SIZE	= 2000;
	
	private static int checkDataOutputEntropy(final DataOutputByteArrayReusable dba, final int elementSize) {
		dba.close();
		final byte[] buffer = dba.getBuffer();
		final int position = dba.getPosition();
		assert position == TestGuidEntropy.TEST_SIZE * elementSize : "Position: " + position;
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
		System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final int doubleZeroToOneEntropy;
		{
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Math.random() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy > 93 : "Bad double zero to one entropy: " + entropy;
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY, normative double zero to one entropy is "
					+ entropy );
			/**
			 * TODO: -1 is not OK - why my entropy is so bad?
			 */
			doubleZeroToOneEntropy = entropy - 1;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY GUID184" );
			try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
					Guid.writeGuid( Guid.createGuid184(), bos );
				}
				final int entropy = Validate.Analyse.entropy( bos.toByteArray() );
				assert entropy > 99 : "Bad guid entropy: " + entropy;
			}
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE GUID184" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.createGuid184().hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY GUID384" );
			try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
					Guid.writeGuid( Guid.createGuid384(), bos );
				}
				final int entropy = Validate.Analyse.entropy( bos.toByteArray() );
				assert entropy > 99 : "Bad guid entropy: " + entropy;
			}
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE GUID384" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.createGuid384().hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE INT" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forJavaInteger( i * 4255397 ).hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING INT" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (int i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forJavaInteger( i * 4255397 ).getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE LONG" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forJavaLong( i * 4255397 ).hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING LONG" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forJavaLong( i * 4255397 ).getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE DOUBLE" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forJavaDouble( i * 4255397.5913621 ).hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING DOUBLE" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forJavaDouble( i * 4255397.5913621 ).getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE STRING" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forString( String.valueOf( i * 4255397 ) ).hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING STRING" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forString( String.valueOf( i * 4255397 ) ).getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE STRING LONGER" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forString( Guid.createGuid184().toString() ).hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING STRING LONGER" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forString( Guid.createGuid184().toString() ).getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE BINARY (STRING LONGEST)" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forBinaryChecksum( Transfer.wrapCopier( Guid.createGuid384().toString().getBytes() ) )
						.hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING BINARY (STRING LONGEST)" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forBinaryChecksum( Transfer.wrapCopier( Guid.createGuid384().toString().getBytes() ) )
						.getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY HASHCODE DATE" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeInt( Guid.forJavaDateMillis( i * 4255397 ).hashCode() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 4 );
			assert entropy > 99 : "Bad guid entropy: " + entropy;
		}
		{
			System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY PARTITIONING DATE" );
			final DataOutputByteArrayReusable dba = new DataOutputByteArrayReusable();
			for (long i = TestGuidEntropy.TEST_SIZE; i > 0; --i) {
				dba.writeDouble( Guid.forJavaDateMillis( i * 4255397 ).getPartitioningParameter() );
			}
			final int entropy = TestGuidEntropy.checkDataOutputEntropy( dba, 8 );
			assert entropy >= doubleZeroToOneEntropy : "Bad guid entropy: " + entropy;
		}
		System.out.println( ">>>> >>>> TEST KNOW AE3 GUID ENTROPY done." );
	}
}
