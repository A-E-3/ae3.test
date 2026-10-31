package ru.myx.ae3.test.help;

import ru.myx.ae3.Engine;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.help.Html;

class TestHelpHtml {
	private static void a(final String s1, final String s2) {
		assert s1.equals( s2 ) : "Not EQ: " + Format.Ecma.string( s1 ) + " != " + Format.Ecma.string( s2 );
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST AE3 HELP HTML" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestHelpHtml.a( "Hello!", Html.cleanHtml( "Hello!" ) );
			TestHelpHtml.a( "Hello !", Html.cleanHtml( "Hello !" ) );
			TestHelpHtml.a( "Hello !", Html.cleanHtml( "Hello&nbsp;!" ) );
			TestHelpHtml.a( "Hello!", Html.cleanHtml( "<b>Hello!</b>" ) );
			TestHelpHtml.a( "Hello!\r\n\r\n", Html.cleanHtml( "<h1>Hello!</h1>" ) );
			TestHelpHtml.a( "Hello!\r\n\r\n", Html.cleanHtml( "<title>Hello!</title>" ) );
			TestHelpHtml.a( "\r\nHello!\r\n\r\n", Html.cleanHtml( "<p>Hello!</p>" ) );
			TestHelpHtml.a( "Hello (c)", Html.cleanHtml( "Hello &copy;" ) );
			TestHelpHtml.a( ">> hello <<", Html.cleanHtml( "&gt;&gt; hello &lt;&lt;" ) );
		}
		{
			TestHelpHtml.a( "Hello!", Html.enhanceHtml( "Hello!", null ) );
			TestHelpHtml.a( "Hello!", Html.enhanceHtml( "Hello!", "target=_blank" ) );
			
			TestHelpHtml.a( //
			"<a href=\"mailto:myx@myx.ru\">myx@myx.ru</a>",
					Html.enhanceHtml( "myx@myx.ru", null ) );
			
			TestHelpHtml.a( //
			"mailto:<a href=\"mailto:myx@myx.ru\">myx@myx.ru</a>",
					Html.enhanceHtml( "mailto:myx@myx.ru", null ) );
			
			TestHelpHtml.a( //
			"<a href=\"mailto:myx@myx.ru\" target=_blank>myx@myx.ru</a>",
					Html.enhanceHtml( "myx@myx.ru", "target=_blank" ) );
			
			TestHelpHtml.a( //
			"<a href=\"mailto:myx@myx.ru\" target=_blank>myx@myx.ru</a>.",
					Html.enhanceHtml( "myx@myx.ru.", "target=_blank" ) );
			
			TestHelpHtml.a( //
			"<a href=\"http://myx.ru\">http://myx.ru</a>",
					Html.enhanceHtml( "http://myx.ru", null ) );
			
			TestHelpHtml.a( //
			"<a href=\"http://myx.ru\">http://myx.ru</a>.",
					Html.enhanceHtml( "http://myx.ru.", null ) );
			
			TestHelpHtml.a( //
			"Visit <a href=\"http://myx.ru\">http://myx.ru</a>",
					Html.enhanceHtml( "Visit http://myx.ru", null ) );
			
			TestHelpHtml.a( //
			"<a href=\"http://myx.ru\" target=_blank>http://myx.ru</a>",
					Html.enhanceHtml( "http://myx.ru", "target=_blank" ) );
			
			TestHelpHtml.a( //
			"<a href=\"https://myx.ru\">https://myx.ru</a>",
					Html.enhanceHtml( "https://myx.ru", null ) );
			
			TestHelpHtml.a( //
			"Visit <a href=\"https://myx.ru\">https://myx.ru</a>",
					Html.enhanceHtml( "Visit https://myx.ru", null ) );
			
			TestHelpHtml.a( "<a href=\"https://myx.ru\" target=_blank>https://myx.ru</a>", //
					Html.enhanceHtml( "https://myx.ru", "target=_blank" ) );
			
			TestHelpHtml.a( "<a href=\"https://myx.ru\" target=_blank>https://myx.ru</a>.", //
					Html.enhanceHtml( "https://myx.ru.", "target=_blank" ) );
			
			TestHelpHtml.a( "www.myx.ru.", //
					Html.enhanceHtml( "www.myx.ru.", null ) );
			TestHelpHtml.a( "www.myx.ru.", //
					Html.enhanceHtml( "www.myx.ru.", "target=_blank" ) );
			
			TestHelpHtml.a( "www.myx.ru", //
					Html.enhanceHtml( "www.myx.ru", null ) );
			TestHelpHtml.a( "www.myx.ru", //
					Html.enhanceHtml( "www.myx.ru", "target=_blank" ) );
		}
		System.out.println( ">>>> >>>> TEST AE3 HELP HTML done." );
	}
}
