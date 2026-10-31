package ru.myx.renderer.ecma;

import java.util.Arrays;
import java.util.Collection;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.help.Format;

/** @author myx */
public class TestVarParser {

	private static final void checkMultiple(final String statement, final Collection<String> check) {

		final BaseMap parsed = TestVarParser.parseVars(statement);
		assert parsed.size() == check.size() //
		: "check=" + check + ", parsed=" + Format.Describe.toEcmaSource(parsed, "");
		for (final String key : check) {
			assert parsed.baseGetOwnProperty(key) != null : "key '" + key + "' is missing, keys: " + Base.objectKeysArray(parsed);
		}
	}

	private static final BaseMap parseVars(final String statement) {

		final BaseMap result = new BaseNativeObject();
		TokenStatementAbstract.toLocals(statement, result);
		return result;
	}

	/** @throws Throwable */
	public static final void test() throws Throwable {

		assert !TokenStatementAbstract.toLocals("a", new BaseNativeObject()) : "No code";
		assert !TokenStatementAbstract.toLocals("a,b", new BaseNativeObject()) : "No code";
		assert !TokenStatementAbstract.toLocals("a, b", new BaseNativeObject()) : "No code";

		assert TokenStatementAbstract.toLocals("a=5", new BaseNativeObject()) : "Has code";
		assert TokenStatementAbstract.toLocals("a=5, b", new BaseNativeObject()) : "Has code";
		assert TokenStatementAbstract.toLocals("a = 5, b", new BaseNativeObject()) : "Has code";
		assert TokenStatementAbstract.toLocals("a, b=5", new BaseNativeObject()) : "Has code";
		assert TokenStatementAbstract.toLocals("a, b = 5", new BaseNativeObject()) : "Has code";

		assert TestVarParser.parseVars("a").size() == 1;
		assert TestVarParser.parseVars("a=5").size() == 1;
		assert TestVarParser.parseVars("a = 5").size() == 1;

		assert TestVarParser.parseVars("a").containsKey("a");
		assert TestVarParser.parseVars("a=5").containsKey("a");
		assert TestVarParser.parseVars("a = 5").containsKey("a");

		assert TestVarParser.parseVars("aa").containsKey("aa");
		assert TestVarParser.parseVars("aa=5").containsKey("aa");
		assert TestVarParser.parseVars("aa = 5").containsKey("aa");

		{
			final Collection<String> check = Arrays.asList(new String[]{
					"a", "b"
			});
			assert check.size() == 2;
			TestVarParser.checkMultiple("a, b", check);
			TestVarParser.checkMultiple("a=5, b", check);
			TestVarParser.checkMultiple("a = 5, b", check);
			TestVarParser.checkMultiple("a, b = 2", check);
			TestVarParser.checkMultiple("a=5, b = 2", check);
			TestVarParser.checkMultiple("a = 5, b = 2 ", check);
		}

		{
			final Collection<String> check = Arrays.asList(new String[]{
					"a", "b", "c"
			});
			assert check.size() == 3;
			TestVarParser.checkMultiple("a, b, c", check);
			TestVarParser.checkMultiple("a=5, b, c", check);
			TestVarParser.checkMultiple("a = 5, b, c", check);
			TestVarParser.checkMultiple("a, b = 2, c", check);
			TestVarParser.checkMultiple("a=5, b = 2, c", check);
			TestVarParser.checkMultiple("a = 5, b = 2, c ", check);
			TestVarParser.checkMultiple("a, b, c = 1", check);
			TestVarParser.checkMultiple("a=5, b, c = 1", check);
			TestVarParser.checkMultiple("a = 5, b, c = 1", check);
			TestVarParser.checkMultiple("a, b = 2, c = 1", check);
			TestVarParser.checkMultiple("a=5, b = 2, c = 1", check);
			TestVarParser.checkMultiple("a = 5, b = 2, c = 1 ", check);
		}

		{
			final Collection<String> check = Arrays.asList(new String[]{
					"aa", "bb", "cc"
			});
			assert check.size() == 3;
			TestVarParser.checkMultiple("aa, bb, cc", check);
			TestVarParser.checkMultiple("aa=5, bb, cc", check);
			TestVarParser.checkMultiple("aa = 5, bb, cc", check);
			TestVarParser.checkMultiple("aa, bb = 2, cc", check);
			TestVarParser.checkMultiple("aa=5, bb = 2, cc", check);
			TestVarParser.checkMultiple("aa = 5, bb = 2, cc ", check);
			TestVarParser.checkMultiple("aa, bb, cc = 1", check);
			TestVarParser.checkMultiple("aa=5, bb, cc = 1", check);
			TestVarParser.checkMultiple("aa = 5, bb, cc = 1", check);
			TestVarParser.checkMultiple("aa, bb = 2, cc = 1", check);
			TestVarParser.checkMultiple("aa=5, bb = 2, cc = 1", check);
			TestVarParser.checkMultiple("aa = 5, bb = 2, cc = 1 ", check);
		}

		{
			final BaseMap parsed = TestVarParser.parseVars("username, login, password");
			assert parsed.baseGet("aaaaa", null) == null;
			assert parsed.baseGet("login", null) == BaseObject.TRUE;

			final BaseObject array = TokenStatementAbstract.toLocalsObjectOrArray(parsed);
			assert !array.baseIsPrimitiveString();
			assert array.baseArray() != null;
			assert array.baseArray().length() == 3;
		}

		{
			final BaseMap parsed = TestVarParser.parseVars("username, login = 'myx', password");
			assert parsed.baseGet("aaaaa", null) == null;
			assert parsed.baseGet("login", null) == BaseObject.FALSE;

			final BaseObject array = TokenStatementAbstract.toLocalsObjectOrArray(parsed);
			assert !array.baseIsPrimitiveString();
			assert array.baseArray() != null;
			assert array.baseArray().length() == 2;
		}

		{
			final BaseMap parsed = TestVarParser.parseVars("username, login = 'myx'");
			assert parsed.baseGet("aaaaa", null) == null;
			assert parsed.baseGet("login", null) == BaseObject.FALSE;

			final BaseObject array = TokenStatementAbstract.toLocalsObjectOrArray(parsed);
			assert array.baseIsPrimitiveString();
			assert "username".equals(array.baseToJavaString());
		}
	}
}
