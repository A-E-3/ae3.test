package ru.myx.ae3.test.mime;

import ru.myx.ae3.mime.MimeType;
import ru.myx.ae3.test.TestAe3;

/**
 * 
 * @author myx
 * 
 */
public class TestMime {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		TestAe3.initAE3();
		System.out.println( ">>>> TEST MIME" );
		{
			final MimeType textJavascript = MimeType.getMimeType( "text/javascript", null, false );
			assert textJavascript != null : "NULL type??!";
			assert "text/javascript".equals( textJavascript.getContentType() ) : "Wrong type: "
					+ textJavascript.getContentType();
			assert "text".equals( textJavascript.getMediaTypeName() );
			
			assert textJavascript.getCompressibility() != null : "Compressibility is NULL!";
			
			assert MimeType.MT_APPLICATION_OCTET_STREAM != null : "default type is NULL";
			
			final MimeType textPlain = textJavascript.getSuperType();
			assert textPlain != MimeType.MT_APPLICATION_OCTET_STREAM : "Kind of no type definitions?";
			assert textPlain != null : "NULL plain";
			
			assert "text/plain".equals( textPlain.getContentType() ) : "Not a text plain";
			
			final MimeType unknown = textPlain.getSuperType();
			assert unknown != null : "NULL any";
			assert unknown == MimeType.MT_APPLICATION_OCTET_STREAM : "Not unknown: " + unknown;
		}
	}
}
