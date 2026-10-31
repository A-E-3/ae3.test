package ru.myx.ae3.test.eval;

import java.util.Map;
import java.util.TreeMap;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseFunction;
import ru.myx.ae3.base.BaseFunctionAbstract;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitive;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BasePrimitiveString;
import ru.myx.ae3.base.BaseProperty;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.common.HolderSimple;
import ru.myx.ae3.common.ValueSimple;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecArgumentsEmpty;
import ru.myx.ae3.exec.ExecCallable;
import ru.myx.ae3.exec.ExecCallableFull;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.ExecStateCode;
import ru.myx.ae3.exec.ProgramAssembly;
import ru.myx.ae3.exec.ProgramPart;
import ru.myx.ae3.exec.ResultHandler;
import ru.myx.ae3.extra.ExtraTextBase;
import ru.myx.ae3.test.TestAe3;

/** @author myx */
class TestEvalExpression {

	/** @param args
	 * @throws Throwable */
	public final static void main(final String[] args) throws Throwable {

		TestAe3.initAE3();
		System.out.println(">>>> TEST EVAL EXPRESSION");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{ // from boot thread: before memory printout
			final ExecProcess ctx = Exec.createProcess(null, "Evaluate test");
			if (Evaluate.evaluateObject("2+2*2 == 6", ctx, null) == BaseObject.FALSE) {
				throw new Error("BOOT: EVAL CHECK: FAIL, (2+2*2 != 6), actually evaluates to: " + Evaluate.evaluateObject("2+2*2", ctx, null));
			}
		}
		{
			final ExecProcess ctx = Exec.createProcess(null, "Evaluate test");
			ctx.vmFrameEntryExFull();
			ctx.vmFrameLeave();
		}
		for (final String test : new String[]{
				/** pre-check just to be first for debugging, commented normally */
				"var f = function(){return function(){ return 'ABCDEF'; };}.call(null), f()",
				/**
					 *
					 */
				"'ABCDEF'", "\"ABCDEF\"", "'AB' + 'CDEF'", "'AB' + (true ? 'CDEF' : 'GHIJ')", "'AB' + (1 ? 'CDEF' : 'GHIJ')", "'AB' + (-1 ? 'CDEF' : 'GHIJ')",
				"'AB' + (false ? 'GHIJ' : 'CDEF')", "'AB' + (0 ? 'GHIJ' : 'CDEF')", "'AB' + (0xF == 15 ? 'CDEF' : 'GHIJ')", "'AB' + (0xf == 15 ? 'CDEF' : 'GHIJ')",
				"'AB' + (15L == 15 ? 'CDEF' : 'GHIJ')", "'AB' + (15.0 == 15 ? 'CDEF' : 'GHIJ')", "'AB' + (15F == 15 ? 'CDEF' : 'GHIJ')", "'AB' + (-0xF == -15 ? 'CDEF' : 'GHIJ')",
				"'AB' + (-0xf == -15 ? 'CDEF' : 'GHIJ')", "'AB' + (-15L == -15 ? 'CDEF' : 'GHIJ')", "'AB' + (-15.0 == -15 ? 'CDEF' : 'GHIJ')",
				"'AB' + (-15.0f == -15 ? 'CDEF' : 'GHIJ')", "'AB' + (-15.0d == -15 ? 'CDEF' : 'GHIJ')", "'AB' + (-15F == -15 ? 'CDEF' : 'GHIJ')",
				"'AB' + (Math.round(0) ? 'GHIJ' : 'CDEF')", "'AB' + (Math.round(4.3) ? 'CDEF' : 'GHIJ')", "'AB' + (Math.round(4.3) && 'CDEF')",
				"'AB' + (Math.round(-4.3) ? 'CDEF' : 'GHIJ')", "'AB' + (Math.round(-4.3) && 'CDEF')", "false || 'AB' + (Math.round(4.3) && 'CDEF')",
				"true && 'AB' + (Math.round(4.3) && 'CDEF')", "!!true && 'AB' + (Math.round(4.3) && 'CDEF')", "Math.ceil(2) && 'AB' + (Math.round(4.3) && 'CDEF')",
				"!!Math.ceil(2) && 'AB' + (Math.round(4.3) && 'CDEF')",
				// "null, 'AB' + (Math.round(4.3) && 'CDEF')",
				"'abcdef'.toUpperCase()", "'0ABCDEF'.substring(1)", "(2+'ABCDEF').substring(1)", "(2L+'ABCDEF').substring(1)", "(2.0+'ABCDEF').substring(1)",
				"(2.1+'ABCDEF').substring(3)", "(0xA+'ABCDEF').substring(2)", "{string:'ABCDEF'}.string", "{string:('0ABC'+'DEF').substring(1)}.string",
				"{string:'ABCDEF',other:2206241}.string", "{'Content-Type':'ABCDEF',other:2206241}['Content-'+'Type']",
				"{\"Content-Type\":\"ABCDEF\",other:2206241}['Content-'+'Type']", "{title:'test',string:'ABCDEF',other:2206241}.string",
				
				/** inline functions */
				"var f = function(){return 'ABCDEF';}, f()", "var f = function(){return { f : 'ABCDEF' }.f;}, f()", "var f = function(){return '}}}ABCDEF'.substring(3);}, f()",
				"var f = function(){return '{{{ABCDEF'.substring(3);}, f()", "var f = function(a){return a + 'DEF';}, f('ABC')",
				"var f = function(a){return a + '}}}DEF'.substring(3);}, f('ABC')", "var f = function(a){return a + '{{{DEF'.substring(3);}, f('ABC')",
				"var f = function(a, b){return a + 'CD' + b;}, f('AB', 'EF')", "var f = function(a, b){return a + '}}CD'.substring(2) + b;}, f('AB', 'EF')",
				"var f = function(a, b){return a + '{{CD'.substring(2) + b;}, f('AB', 'EF')",
				
				/** inline functions with no 'return' should return 'undefined' */
				"{f : function(){var z = 'ABC' + z;}}.f() === undefined && 'ABCDEF'", "{f : (function(){var z = 'ABC' + z;})}.f() === undefined && 'ABCDEF'",
				"var f = function(){var z = 'ABC' + z;}, f() === undefined && 'ABCDEF'", "var f = function(a){var z = 'ABC' + z;}, f('A') === undefined && 'ABCDEF'",
				"var f = function(a,b){var z = 'ABC' + z;}, f('A','B') === undefined && 'ABCDEF'",
				
				/** return inline function */
				"var f = { f : function(){return (function(){ return 'ABCDEF'; });} }, var z = f.f(), z()",
				"var f = function(){return (function(){ return 'ABCDEF'; });}.call(null), f()", "var f = function(){return function(){ return 'ABCDEF'; };}.call(null), f()",
				/** function without 'this' */
				"var f = function(){return (function(){ return 'ABCDEF'; });}(), f()", "var f = function(){return function(){ return 'ABCDEF'; };}(), f()",
				"var f = (function(){return { f : function(){ return 'ABCDEF'; } }.f;})(), f()", "(function(){return { f : function(){ return 'ABCDEF'; } }.f;})()()",
				"function(){return function(){ return 'ABCDEF'; };}()()", "var f = function(){return function(){ return 'ABCDEF'; };}, f()()",
				"var f = function(){return { f : function(){ return 'ABCDEF'; } }.f;}, f()()", "var f = function(){return (function(){ return 'ABCDEF'; });}, f()()",
				/** arrays */
				"(['AB'])[0]+'CDEF'", "'AB' + (['AB','CD'])[1] + 'EF'", "'AB' + (['AB','CD','EF'])[1] + 'EF'", "'AB' + ([ ['AB','CD','EF'],['aaa'] ])[0][1] + 'EF'",
				"'AB' + ([ ['AB','CD','EF'] ])[0][1] + 'EF'",
				
				/** other */
				"var f = 0, c = {}, z = c.a = ++ f, z == 1 && 'ABCDEF'", "var f = 0, c = {}, z = c.a= ++ f, z == 1 && 'ABCDEF'",
				"var f = 0, c = {}, z = c.a= ++f, z == 1 && 'ABCDEF'", "var f = 0, c = {}, z = c.a=++f, z == 1 && 'ABCDEF'",
				"var f = function(){var z = 0, c = {}; c.a = ++ z; return c.a === 1 ? 'ABCDEF' : 'oops'; }, f()",
				"var f = function(){var z = 0, c = {}; c.a = ++z; return c.a === 1 ? 'ABCDEF' : 'oops'; }, f()",
				"var f = function(){var z = 0, c = {}; c.a=++z; return c.a === 1 ? 'ABCDEF' : 'oops'; }, f()",
				/** the end. */
				"'ABCDEF'",
				// "{toString:function(){return 'ABCDEF';}}.toString()",
		}) {
			System.out.println(">>>> >>>> check: " + test);
			final ExecProcess ctx = Exec.createProcess(null, "EVAL test");
			final BaseObject result = Evaluate.evaluateObject(test, ctx, null);
			assert result != null : "NULL java value";
			/** all tests return ABCDEF */
			if (!"ABCDEF".equals(result.baseToJavaString())) {
				final ProgramAssembly assembly = new ProgramAssembly();
				Evaluate.compileExpression(assembly, test, ResultHandler.FC_PNN_RET);
				throw new UnsupportedOperationException("'ABCDEF' != EVAL('" + test + "'), result=" + result + ", code=" + assembly.toProgram(0));
			}
		}
		{
			/** Need some obsolete API's here */
			// ImplACM.init();
		}
		for (final String test : new String[]{
				"[]", "[1]", "[1,2]", "[1,2,3]", "Array([])", "Array([1])", "Array([1,2])", "Array([1,2,3])", "new Array()", "new Array(1,2,3)",
				/** <code>
						"Create.list()",
						"Create.list(1)",
						"Create.list(2)",
						"Create.list(3)",
						"Create.list(1, 'aaa')",
						"Create.list(2, 'bbb')",
						"Create.list(3, 'ccc')",
						"Create.array()",
						"Create.array(1)",
						"Create.array(2)",
						"Create.array(3)",
						"Create.array(1, 'aaa')",
						"Create.array(2, 'bbb')",
						"Create.array(3, 'ccc')",
						</code> */
				// "{toString:function(){return 'ABCDEF';}}.toString()",
		}) {
			System.out.println(">>>> >>>> arrays: " + test);
			final ExecProcess ctx = Exec.createProcess(null, "EVAL test");
			final BaseObject result = Evaluate.evaluateObject(test, ctx, null);
			assert result != null : "NULL java value";
			/** all tests return native arrays */
			if (!(result instanceof BaseList)) {
				throw new UnsupportedOperationException("Wrong array type('" + test + "'), class=" + result.getClass());
			}
		}
		for (final String test : new String[]{
				"{}", "{a:1}", "{'a':1}", "{a:1,b:2}", "{a:1,b:2,c:3}", "new Object()", "Object.create({a:0})", "Layouts.extend({a:0})", "Layouts.extend({a:0},{a:1})",
				"Object.create(Object.prototype)", "Layouts.extend(Object.prototype)", "Layouts.extend(Object.prototype,{a:1})", "Layouts.extend(Object.prototype,{a:1, b:2})",
				"Layouts.extend(Object.prototype,{a:1, b:2, c:3})",
				/** <code>
						"Create.map()",
						"Create.map(a = 1)",
						"Create.map(a = 1, b = 2)",
						"Create.map(a = 1, b = 2, c = 3)",
						"Create.mapFor()",
						"Create.mapFor(a = 1)",
						"Create.mapFor(a = 1, b = 2)",
						"Create.mapFor(a = 1, b = 2, c = 3)",
				</code> */
				// "{toString:function(){return 'ABCDEF';}}.toString()",
		}) {
			System.out.println(">>>> >>>> objects: " + test);
			final ExecProcess ctx = Exec.createProcess(null, "EVAL test");
			final BaseObject result = Evaluate.evaluateObject(test, ctx, null);
			assert result != null : "NULL java value";
			/** all tests return native objects */
			if (!(result instanceof BaseMap)) {
				throw new UnsupportedOperationException("Wrong object type('" + test + "'), class=" + result.getClass());
			}
		}
		for (final String test : new String[]{
				"extra.toString()", "'' + extra", "{\"Content-Type\":extra,other:2206241}['Content-'+'Type']", "{title:'test',string:extra+'',other:2206241}.string",
				"{title:'test',string:extra+'',other:2206241,ct:Math.max( 0, 60000 ) / 60000}.string"
				// "{toString:function(){return 'ABCDEF';}}.toString()",
		}) {
			System.out.println(">>>> >>>> references: " + test);
			final TransferCopier copier = Transfer.createCopier("ABCDEF".getBytes());
			final ExtraTextBase extra = new ExtraTextBase("TEST", "test-id", 0L, copier);
			final ExecProcess ctx = Exec.createProcess(null, "EVAL test");
			ctx.baseDefine("extra", extra, BaseProperty.ATTRS_MASK_NNN);
			final BaseObject result = Evaluate.evaluateObject(test, ctx, null);
			assert result != null : "NULL java value";
			if (!"ABCDEF".equals(result.baseToJavaString())) {
				throw new UnsupportedOperationException("'ABCDEF' != EVAL('" + test + "'), result=" + result);
			}
		}
		/** Value<?> access */
		{
			final BaseObject object = new BaseNativeObject()//
					.putAppend("field1", BasePrimitiveNumber.ONE)//
			;
			assert object.baseGet("field1", BaseObject.UNDEFINED) == BasePrimitiveNumber.ONE;
			
			for (final Object o : new Object[]{
					//
					object,
					//
					new ValueSimple<>(object),
					//
					new HolderSimple<>(object),
					//
			}) {
				final BaseObject unknown = Base.forUnknown(o);
				System.out.println(">>>> >>>> value test, class=" + o.getClass().getName() + ", baseClass=" + unknown.getClass().getName());
				final ExecProcess ctx = Exec.createProcess(null, "EVAL test");
				{
					final BasePrimitiveString field1 = Base.forString("field1");
					final BaseObject result = unknown.baseGet(field1, BaseObject.UNDEFINED);
					assert result == BasePrimitiveNumber.ONE;
					assert null != unknown.baseFindProperty(field1);
					assert null != unknown.baseFindProperty(field1, BaseObject.PROTOTYPE);
					assert null != unknown.baseFindProperty((BasePrimitive<?>) field1);
				}
				{
					final BaseObject result = unknown.baseGet("field1", BaseObject.UNDEFINED);
					assert result == BasePrimitiveNumber.ONE;
					assert null != unknown.baseFindProperty("field1");
					assert null != unknown.baseFindProperty("field1", BaseObject.PROTOTYPE);
				}
				{
					final BaseObject result = unknown.baseGet("field0", BaseObject.UNDEFINED);
					assert result == BaseObject.UNDEFINED;
					assert null == unknown.baseFindProperty("field0");
					assert null == unknown.baseFindProperty("field0", BaseObject.PROTOTYPE);
				}
				ctx.baseDefine("o", unknown, BaseProperty.ATTRS_MASK_NNN);
				{
					assert unknown == ctx.contextGetBindingValue("o", false);
					assert unknown == ctx.baseGet("o", BaseObject.UNDEFINED);
				}
				{
					final BaseObject result = Evaluate.evaluateObject("o.field0", ctx, null);
					assert result == BaseObject.UNDEFINED;
				}
				{
					final BaseObject result = Evaluate.evaluateObject("o.field1", ctx, null);
					assert result == BasePrimitiveNumber.ONE : "unknownClass: " + unknown.getClass().getName() + ", result=" + result;
				}
				{
					final BasePrimitiveString string1 = Base.forString("[object Object]");
					final BasePrimitiveString string2 = Base.forString("[object Reference]");
					for (final String expression : new String[]{
							"o.toString()", "o.toString(x)", "o.toString(x, y)", "o.toString(x = x, y)", "o.toString(x, y = y)", "o.toString(x = x, y = y)",
					}) {
						final BaseObject result = Evaluate.evaluateObject(expression, ctx, null);
						assert result == string1 || result == string2 : "expected: " + string1 + " OR " + string2 + ", got: " + result + ", class=" + result.getClass().getName();
					}
				}
			}
		}
		/** EVAL renderer one more time */
		for (final String test : new String[]{
				//
				"2+2*2",
				//
				"{a:2+2*2,b:'aaa'}.a"
				//
		}) {
			System.out.println(">>>> EVAL language: test=" + test);
			final ExecProcess ctx = Exec.createProcess(null, "EVAL test");
			final ProgramPart program = Evaluate.compileProgram(Evaluate.EVALUATE_LANGUAGE_IMPL, "TEST", test);
			{
				/** need object */
				final Object result = program.callNE0(ctx, ctx).baseValue();
				assert Base.forUnknown(result).baseToNumber().doubleValue() == 6 : "got: " + result;
			}
			{
				/** need object */
				final Object result = program.callNE0(ctx, ctx).baseValue();
				assert Base.forUnknown(result).baseToNumber().doubleValue() == 6 : "got: " + result;
			}
			{
				class TestFunction extends BaseFunctionAbstract implements ExecCallableFull {

					@Override
					public ExecStateCode execCallImpl(final ExecProcess context) throws Exception {

						return program.execCallPrepare(context, context.rb4CT, ResultHandler.FA_BNN_NXT, true, ExecArgumentsEmpty.INSTANCE);
					}
					
					@Override
					public BaseObject execScope() {

						return null;
					}
					
					@Override
					public String toString() {

						return "test";
					}
				}

				final BaseFunction function = new TestFunction();
				final ExecCallable functionExec = new TestFunction();
				{
					final ExecStateCode code = function.execCallPrepare(ctx, BaseObject.UNDEFINED, ResultHandler.FA_BNN_NXT, true);
					assert code == null : "code is: " + code + ", rr=" + ctx.vmGetResultImmediate();
					assert ctx.vmGetResultImmediate().baseToPrimitive(null).doubleValue() == 6;
					assert ctx.vmGetResultImmediate().baseToPrimitive(null).intValue() == 6;
					assert ctx.vmGetResultImmediate().baseToNumber().doubleValue() == 6;
				}
				{
					final ExecStateCode code = function.execCallPrepare(ctx, BaseString.EMPTY, ResultHandler.FA_BNN_NXT, true);
					assert code == null : "code is: " + code + ", rr=" + ctx.vmGetResultImmediate();
					assert ctx.vmGetResultImmediate().baseToPrimitive(null).doubleValue() == 6;
					assert ctx.vmGetResultImmediate().baseToPrimitive(null).intValue() == 6;
					assert ctx.vmGetResultImmediate().baseToNumber().doubleValue() == 6;
				}
				{
					final BaseMap instance = new BaseNativeObject("fn", Base.forFunction((ExecCallable) function));
					/** need object */
					final BaseObject result = instance.baseCall("fn", false);
					assert result.baseToNumber().doubleValue() == 6;
				}
				{
					final BaseMap instance = new BaseNativeObject("fn", Base.forFunction(functionExec));
					/** need object */
					final BaseObject result = instance.baseCall("fn", false);
					assert result.baseToNumber().doubleValue() == 6;
				}
				{
					final Map<String, Object> instance = new TreeMap<>();
					instance.put("fn", function);
					/** need object */
					final BaseObject result = Base.forUnknown(instance).baseCall("fn", false);
					assert result.baseToNumber().doubleValue() == 6;
				}
			}
		}
	}
}
