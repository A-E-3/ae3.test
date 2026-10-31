package ru.myx.ae3.test.help;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import ru.myx.ae3.Engine;
import ru.myx.ae3.help.Text;

class TestHelpText {
	
	/** @param args
	 * @throws Exception */
	public static void main(final String[] args) throws Exception {

		Engine.createGuid();
		System.out.println(">>>> >>>> TEST AE3 HELP TEXT");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			assert Text.padString("123", ' ', 10).length() == 10;
			assert "       123".equals(Text.padString("123", ' ', 10));
			assert "      123".equals(Text.padString("123", ' ', 9));
			assert "     123".equals(Text.padString("123", ' ', 8));
			assert "    123".equals(Text.padString("123", ' ', 7));
			assert "   123".equals(Text.padString("123", ' ', 6));
			assert "  123".equals(Text.padString("123", ' ', 5));
			assert " 123".equals(Text.padString("123", ' ', 4));
			assert "123".equals(Text.padString("123", ' ', 3));
			assert "123".equals(Text.padString("123", ' ', 2));
			assert "123".equals(Text.padString("123", ' ', 1));
			assert "123".equals(Text.padString("123", ' ', 0));
		}
		{
			assert "1%202".equals(Text.encodeUriComponent("1 2", StandardCharsets.US_ASCII));
			assert "1%202".equals(Text.encodeUriComponent("1 2", StandardCharsets.US_ASCII.name()));
		}
		{
			assert "1%202".equals(Text.encodeUri("1 2", StandardCharsets.US_ASCII));
			assert "1%202".equals(Text.encodeUri("1 2", StandardCharsets.US_ASCII.name()));
		}
		{
			assert "1 2".equals(Text.decodeUri("1%202", StandardCharsets.US_ASCII));
			assert "1 2".equals(Text.decodeUri("1%202", StandardCharsets.US_ASCII.name()));
		}
		{
			assert "1 2".equals(new String(Text.decodeUriAsBytes("1%202"), StandardCharsets.US_ASCII));
		}
		{
			assert "1,2".equals(Text.join(new Object[]{
					"1", "2"
			}, ","));
			assert "1,2".equals(Text.join(Arrays.asList(new Object[]{
					"1", "2"
			}), ","));
		}
		{
			// assert "12345".equals( Text.limitString( "1234567890", 5 ) );
			assert "12345...".equals(Text.limitString("1234567890", 5, "..."));
			assert "12345".equals(Text.limitString("12345", 5, "..."));
		}
		{
			assert "a&lt;b".equals(Text.encodeXmlNodeValue("a<b"));
			assert "a&amp;b".equals(Text.encodeXmlNodeValue("a&b"));
			assert "a&quot;b".equals(Text.encodeXmlNodeValue("a\"b"));
		}
		{
			assert "a<b".equals(Text.decodeXmlNodeValue("a&lt;b"));
			assert "a&b".equals(Text.decodeXmlNodeValue("a&amp;b"));
			assert "a\"b".equals(Text.decodeXmlNodeValue("a&quot;b"));
		}
		{
			assert "1,2,3".equals(Text.join(Text.toStringArray("1\n2\n3", "\n", Integer.MAX_VALUE), ","));
			assert "1,2,3".equals(Text.join(Text.toStringArray("1\n2\n3\n", "\n", Integer.MAX_VALUE), ","));
			assert "1,2,3".equals(Text.join(Text.toStringArray("4\n1\n2\n3", "\n", 3), ","));
			assert "1,2,3".equals(Text.join(Text.toStringArray("4\n1\n2\n3\n", "\n", 3), ","));
		}
		System.out.println(">>>> >>>> TEST AE3 HELP TEXT done.");
	}
}
