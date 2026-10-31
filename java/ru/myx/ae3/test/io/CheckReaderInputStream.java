package ru.myx.ae3.test.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import ru.myx.ae3.Engine;
import ru.myx.ae3.binary.Transfer;
import ru.myx.io.ReaderInputStream;
import ru.myx.io.WriterOutputStream;

/**
 * 
 * @author myx
 * 
 */
class CheckReaderInputStream {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		{
			Engine.createGuid();
		}
		{
			final String test = "myx ьнч";
			final StringReader stringReader = new StringReader( test );
			final InputStream inputStream = new ReaderInputStream( stringReader, StandardCharsets.UTF_8 );
			final StringWriter stringWriter = new StringWriter();
			final OutputStream outputStream = new WriterOutputStream( stringWriter, StandardCharsets.UTF_8 );
			
			Transfer.toStream( inputStream, outputStream, true );
			
			if (!test.equals( stringWriter.getBuffer().toString() )) {
				throw new IllegalStateException( "Failed: " + test + ", " + stringWriter.getBuffer().toString() );
			}
		}
	}
}
