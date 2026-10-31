package ru.myx.ae3.l2.pdf.test;

import java.io.File;
import java.io.FileInputStream;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.l2.LayoutEngine;
import ru.myx.ae3.l2.pdf.PdfTargetFileContext;
import ru.myx.ae3.l2.pdf.PdfTargetReplyContext;
import ru.myx.ae3.report.Report;
import ru.myx.ae3.serve.file.ServeRequestExecuteFileOnReply;
import ru.myx.ae3.test.TestAe3;

/**
 * @author myx
 * 
 */
public class TestPdf {
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		if (false) {
			final File userDir = new File( System.getProperty( "user.dir" ) );
			final File pathPublic = new File( userDir.getParentFile(), "acm-cvs/sys-current" );
			assert pathPublic.exists() : "public doesn't exist: " + pathPublic.getAbsolutePath();
			assert new File( pathPublic, "axiom" ).exists() : "public/axiom doesn't exist: public="
					+ pathPublic.getAbsolutePath();
			final File pathProtected = new File( userDir.getParentFile(), "unit-test/!protected" );
			final File pathPrivate = new File( userDir.getParentFile(), "unit-test/!private" );
			System.setProperty( "ru.myx.ae3.properties.path.public", pathPublic.getAbsolutePath() );
			System.setProperty( "ru.myx.ae3.properties.path.protected", pathProtected.getAbsolutePath() );
			System.setProperty( "ru.myx.ae3.properties.path.private", pathPrivate.getAbsolutePath() );
			System.setProperty( "ru.myx.ae3.properties.log.level", "DEBUG" );
			Engine.createGuid();
			System.out.println( "public: " + Engine.PATH_PUBLIC );
			System.out.println( "protected: " + Engine.PATH_PROTECTED );
			System.out.println( "private: " + Engine.PATH_PRIVATE );
			System.out.println( "level: " + Report.LEVEL_NAME );
		}
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		
		if (Engine.createRandom( 2 ) == 1) {
			final ServeRequestExecuteFileOnReply query = new ServeRequestExecuteFileOnReply( "pdftest-", ".pdf" );
			System.out.println( "LayoutEngine2 PDF renderer test, output to: " + query );
			
			final BaseObject text = args == null || args.length == 0
					? LayoutEngine.getDocumentation() // context.getLayoutAbout()
					: args.length > 1
							? LayoutEngine.getDocumentation()
							: LayoutEngine.parseJSLD( new FileInputStream( args[0] ) );
			
			new PdfTargetReplyContext( query, "Hello", "World" ).transform( text ).baseValue();
		} else {
			final File output = File.createTempFile( "test", ".pdf" );
			System.out.println( "LayoutEngine2 PDF renderer test, output to: " + output.getAbsolutePath() );
			
			final BaseObject text = args == null || args.length == 0
					? LayoutEngine.getDocumentation() // context.getLayoutAbout()
					: args.length > 1
							? LayoutEngine.getDocumentation()
							: LayoutEngine.parseJSLD( new FileInputStream( args[0] ) );
			new PdfTargetFileContext( output, "Hello", "World" ).transform( text ).baseValue();
			
			Engine.createProcess( output.getName(), null, output.getParentFile() );
		}
	}
}
