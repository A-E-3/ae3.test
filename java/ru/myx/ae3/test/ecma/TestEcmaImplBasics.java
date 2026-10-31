package ru.myx.ae3.test.ecma;

/*
 * Created on 13.11.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.eval.LanguageImpl;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.ProgramPart;
import ru.myx.ae3.test.TestAe3;
import ru.myx.renderer.ecma.AcmEcmaLanguageImpl;

/** @author myx
 *
 *         To change the template for this generated type comment go to Window>Preferences>Java>Code
 *         Generation>Code and Comments */
public class TestEcmaImplBasics {

	/** @param args
	 * @throws Throwable
	 */
	public final static void main(final String[] args) throws Throwable {

		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ECMA COMPILER BASICS");
		{ // from boot thread: before memory printout
			final ExecProcess ctx = Exec.createProcess(null, "Evaluate test");
			if (Evaluate.evaluateObject("100-21/7 == 97", ctx, null) == BaseObject.FALSE) {
				throw new Error("BOOT: EVAL CHECK: FAIL, (100-21/7 != 97), actually evaluates to: " + Evaluate.evaluateObject("100-21/7", ctx, null));
			}
		}
		{
			for (final String test : new String[]{
					// pre-check just to be first for debugging, commented
					// normally

					"var f = function z() { return 'ABCDEF' }; return f();",
					// "return /'/g;",

					// "return (/'/g);",

					// "var matches = new RegExp('(ABC\\\\S+)').exec('This is a
					// ABCDEF');return matches[1];",

					// "var matches = /(ABC\\S+)/.exec('This is a
					// ABCDEF');return matches[1];",

					// simple
					"return 'ABCDEF'", "= 'ABCDEF'", "%>ABCDEF<%",
					// template literals (backtick)
					"return `ABCDEF`", 
					"= `ABCDEF`", 
					"return `ABC${'DEF'}`", 
					"return `${'AB'}${'CD'}${'EF'}`", 
					"= `AB${'CD'}EF`", 
					"return `AB` + `CD` + `EF`",
					// assignment
					"var x; return x = 'ABCDEF';", //
					"var x; = x = 'ABCDEF';", //
					"var x = {}; return x.y = 'ABCDEF';", //
					"var x = {}; = x.y = 'ABCDEF';", //
					"var x = { y : {} }; return x.y.z = 'ABCDEF';", //
					"var x = { y : {} }; = x.y.z = 'ABCDEF';", //
					"var x = { y : {} }; x.y.z = 'ABCDEF'; return x.y.z;", //
					"var x = { y : {} }; x.y.z = 'ABCDEF'; = x.y.z;", //
					// logical flow
					"return false || 'ABCDEF'", //
					"return true && 'ABCDEF'", //
					"return 1 && 'ABCDEF'", //
					"var aaa; return aaa || 'ABCDEF'", //
					"var aaa = 1; return aaa && 'ABCDEF'", //
					"return false || 'ABCDEF' || 'FEDCBA'", "var aaa; return aaa || 'ABCDEF' || 'FEDCBA'", "return Math.floor(Math.random(5)/15) || 'ABCDEF'",
					"return Math.floor(Math.random(5)/15) || zzzz || 'ABCDEF'", "return Math.floor(Math.random(5)/15) || 'ABCDEF' || 'FEDCBA'",
					"= Math.floor(Math.random(5)/15) || 'ABCDEF'", "= Math.floor(Math.random(5)/15) || 'ABCDEF' || 'FEDCBA'",
					"return Math.floor(Math.random(5)/15) ? 'FEDCBA' : 'ABCDEF'", "= Math.floor(Math.random(5)/15) ? 'FEDCBA' : 'ABCDEF'",
					"return Math.ceil(1 + Math.random(5)/15) && 'ABCDEF'", "return Math.ceil(1 + Math.random(5)/15) && 'ABCDEF' || 'FEDCBA'",
					"return Math.ceil(1 + Math.random(5)/15) ? 'ABCDEF' : 'FEDCBA'", "= Math.ceil(1 + Math.random(5)/15) && 'ABCDEF'",
					"= Math.ceil(1 + Math.random(5)/15) && 'ABCDEF' || 'FEDCBA'", "= Math.ceil(1 + Math.random(5)/15) ? 'ABCDEF' : 'FEDCBA'",
					// brace flow
					"return ('FEDCBA','ABCDEF')", "= ('FEDCBA','ABCDEF')", "return (Math.floor(Math.random(5)/15),'ABCDEF')", "= (Math.floor(Math.random(5)/15),'ABCDEF')",
					"return (Math.floor(Math.random(5)/15), Math.floor(Math.random(5)/15) || 'ABCDEF')",
					"return (Math.floor(Math.random(5)/15), Math.floor(Math.random(5)/15) || 'ABCDEF' || 'FEDCBA')",
					"= (Math.floor(Math.random(5)/15), Math.floor(Math.random(5)/15) || 'ABCDEF')",
					"= (Math.floor(Math.random(5)/15), Math.floor(Math.random(5)/15) || 'ABCDEF' || 'FEDCBA')",
					"return (Math.floor(Math.random(5)/15), Math.floor(Math.random(5)/15) ? 'FEDCBA' : 'ABCDEF')",
					"= (Math.floor(Math.random(5)/15), Math.floor(Math.random(5)/15) ? 'FEDCBA' : 'ABCDEF')",
					"return (Math.floor(Math.random(5)/15), Math.ceil(1 + Math.random(5)/15) && 'ABCDEF')",
					"return (Math.floor(Math.random(5)/15), Math.ceil(1 + Math.random(5)/15) && 'ABCDEF' || 'FEDCBA')",
					"return (Math.floor(Math.random(5)/15), Math.ceil(1 + Math.random(5)/15) ? 'ABCDEF' : 'FEDCBA')",
					"= (Math.floor(Math.random(5)/15), Math.ceil(1 + Math.random(5)/15) && 'ABCDEF')",
					"= (Math.floor(Math.random(5)/15), Math.ceil(1 + Math.random(5)/15) && 'ABCDEF' || 'FEDCBA')",
					"= (Math.floor(Math.random(5)/15), Math.ceil(1 + Math.random(5)/15) ? 'ABCDEF' : 'FEDCBA')",

					"var xx = ''; $output(xx){ %>AB<%   %>CDEF<%", "%>AB<%= 'CD' %>EF<%", "%>AB<% if(false) { = 'EAS';}  %>CDEF<%",
					"%>AB<% if(false) = 'EAS';  %>CDEF<% } return xx;", "%>AB<% if(false) { = 'EAS';} else { = 'CDEF'; }", "%>AB<% if(false) = 'EAS'; else { = 'CDEF'; }",
					"%>AB<% if(false) { = 'EAS';} else = 'CDEF';", "%>AB<% if(false) = 'EAS'; else = 'CDEF';", "%>AB<% if(false) \n\t = 'EAS'; \n else \n\t = 'CDEF';",
					"%>AB<% if(true) { = 'CDEF';} else { = 'EAS'; }", "%>AB<% if(true) = 'CDEF'; else { = 'EAS'; }", "%>AB<% if(true) { = 'CDEF';} else = 'EAS';",
					"%>AB<% if(true) = 'CDEF'; else = 'EAS';", "%>AB<% if(true) \n\t = 'CDEF'; \n else \n\t = 'EAS';",
					"%>AB<% if(Math.floor(Math.random(5)/15)) { = 'EAS';} %>CDEF<%", "%>AB<% if(Math.floor(Math.random(5)/15)) { = 'EAS';} else { ='CDEF'; }",
					"%>AB<% if(!Math.floor(Math.random(5)/15)) { = 'CDEF';} else { ='EAS'; }", "%>AB<% if(false) { = 'EAS';} if(true) { = 'CD'; } %>EF<%",
					"%>AB<% if(Math.floor(Math.random(5)/15)) { = 'EAS';} if(10 + Math.random(5)) { = 'CD'; }  %>EF<%", "%>AB<% $output(cd){ %>CD<% } = cd; %>EF<%",
					"%>AB<% $output(cd){ ='CD'; } = cd; %>EF<%", "%>AB<% $output(cd){ = { a : 5 }.zz || 'CD'; } = cd; %>EF<%",
					"%>AB<% var x = { a : 5 }; $output(cd){ = x.zz || x.zzz || x.zzzz || 'CD'; } = cd; %>EF<%", "%>AB<% $output(cd){ = 3; ='CD'; } = cd.substring(1); %>EF<%",
					"%>AB<% $output(cd){ = 3.0; ='CD'; } = cd.substring(1); %>EF<%", "%>AB<% $output(cd){ = 3.1; ='CD'; } = cd.substring(3); %>EF<%",
					"%>AB<% var o; o = {}; o.cd = 'CD'; = o.cd; %>EF<%", "%>AB<% var o = {}; o.cd = 'CD'; = o.cd; %>EF<%", "='A'; = ['BCDEF'][0];", "='A'; = ['A','BCDEF'][1];",
					"='A';try{='B';throw 'CD';}catch(e){=e;}='EF';", "='A';try{='B';null.a();}catch(e){='CD';}='EF';",

					"return isNaN(Number.NaN) && 'ABCDEF';",

					"if(false) \n\t return 'EASAAA'; \n else \n\t return 'ABCDEF';",
					"\n\t var r; \n\t if(false) \n\t\t r = 'EASAAA'; \n\t else \n\t\t r = 'ABCDEF'; \n\t return r; \n\t",
					"{\n\t var r; \n\t if(false) \n\t\t r = 'EASAAA'; \n\t else \n\t\t r = 'ABCDEF'; \n\t return r; \n}",
					"{\n\t var a; \n\t if(false) \n\t\t var r = 'EASAAA'; \n\t else \n\t\t var r = 'ABCDEF'; \n\t return r; \n}",
					"try{\n\t var a; \n\t if(false) \n\t\t var r = 'EASAAA'; \n\t else \n\t\t throw 'ABCDEF'; \n\t return r; \n}catch(x){ return x; }",
					"try{\n\t var a; \n\t if(false) \n\t\t throw 'EASAAA'; \n\t else \n\t\t var r = 'ABCDEF'; \n\t return r; \n}catch(x){ return x; }",
					"try{\n\tvar a;\n\tif(false)\n\t\tvar r = 'EASAAA';\n\telse\n\t\tthrow 'ABCDEF';\n\t return r; \n}catch(x){return x;}",
					"try{\n\tvar a;\n\tif(false)\n\t\tthrow 'EASAAA';\n\telse\n\t\tvar r = 'ABCDEF';\n\t return r; \n}catch(x){return x;}",
					"try{\n\t var a = 0; \n\t if(a) \n\t\t var r = 'EASAAA'; \n\t else \n\t\t throw 'ABCDEF'; \n\t return r; \n}catch(x){ return x; }",
					"try{\n\t var a = 0; \n\t if(a) \n\t\t throw 'EASAAA'; \n\t else \n\t\t var r = 'ABCDEF'; \n\t return r; \n}catch(x){ return x; }",

					"if(true) \n\t return 'ABCDEF'; \n else \n\t return 'AAAEAS';",
					"\n\t var r; \n\t if(true) \n\t\t r = 'ABCDEF'; \n\t else \n\t\t r = 'AAAEAS'; \n\t return r; \n\t",
					"{\n\t var r; \n\t if(true) \n\t\t r = 'ABCDEF'; \n\t else \n\t\t r = 'AAAEAS'; \n\t return r; \n}",
					"{\n\t var a; \n\t if(true) \n\t\t var r = 'ABCDEF'; \n\t else \n\t\t var r = 'AAAEAS'; \n\t return r; \n}",
					"try{\n\t var a; \n\t if(true) \n\t\t throw 'ABCDEF'; \n\t else \n\t\t var r = 'AAAEAS'; \n\t return r; \n}catch(x){ return x; }",
					"try{\n\t var a; \n\t if(true) \n\t\t var r = 'ABCDEF'; \n\t else \n\t\t throw 'AAAEAS'; \n\t return r; \n}catch(x){ return x; }",
					"try{\n\tvar a;\n\tif(true)\n\t\tthrow 'ABCDEF';\n\telse\n\t\tvar r = 'AAAEAS';\n\treturn r;\n}catch(x){return x;}",
					"try{\n\tvar a;\n\tif(true)\n\t\tvar r = 'ABCDEF';\n\telse\n\t\tthrow 'AAAEAS';\n\treturn r;\n}catch(x){return x;}",
					"try{\n\t var a = 1; \n\t if(a) \n\t\t throw 'ABCDEF'; \n\t else \n\t\t var r = 'AAAEAS'; \n\t return r; \n}catch(x){ return x; }",
					"try{\n\t var a = 1; \n\t if(a) \n\t\t var r = 'ABCDEF'; \n\t else \n\t\t throw 'AAAEAS'; \n\t return r; \n}catch(x){ return x; }",

					"function f(){return 'ABCDEF';} return f();", "function f(a){return a + 'DEF';} return f('ABC');",
					"function f(a,b){return a + b + 'CDEF';} return f('A', 'B');",
					"function f(a,b){return a + b + this.cdef;} var a = { f : f, cdef : 'CDEF' }; return a.f('A', 'B');", "function f(a,b){return a + b + 'CDEF';} = f('A', 'B');",
					"var a = {string:'ABCDEF'}; return a.string;", "var a = ['ABC','DEF']; return a[0] + a[1];", "var a = ['ABC','DEF']; return (a[1],a[0]) + (a[0],a[1]);",
					// assign
					"var a,b,c,d,e,f,g; return a = b = c = d = e = f = g = 'ABCDEF'", "var a={}, b={}, c={}, d={}; return a.z = b.x = c.y = d.w = 'ABCDEF'",
					"var a={z:{}}, b={x:{}}, c={y:{}}; return a.z.g = b.x.h = c.y.k = 'ABCDEF'",
					// constant
					"return typeof false == 'boolean' ? 'ABCDEF' : 'FEDCBA';", "return typeof 77777 == 'number' ? 'ABCDEF' : 'FEDCBA';",
					"return typeof 77.77 == 'number' ? 'ABCDEF' : 'FEDCBA';", "return typeof 'zzz' == 'string' ? 'ABCDEF' : 'FEDCBA';",
					"return typeof 'zzz' == 'string' ? true && 'ABCDEF' : 'FEDCBA';", "return typeof 'zzz' == 'string' ? !vvv && 'ABCDEF' : 'FEDCBA';",
					"return typeof 'zzz' == 'string' ? xxxx || 'ABCDEF' : 'FEDCBA';", "return typeof 'zzz' == 'string' ? xxxx = 'ABCDEF' : 'FEDCBA';",
					"return typeof 'zzz' != 'string' ? xxxx = 'FEDCBA' : 'ABCDEF';", "return typeof 'zzz' == 'string' ? 'ABCDEF' : xxxx = 'FEDCBA';",
					"return typeof 'zzz' != 'string' ? 'FEDCBA' : xxxx = 'ABCDEF';",
					// variable
					"return typeof zzz == 'undefined' ? 'ABCDEF' : 'FEDCBA';", "return typeof zzz == 'undefined' ? true && 'ABCDEF' : 'FEDCBA';",
					"return typeof zzz == 'undefined' ? !vvv && 'ABCDEF' : 'FEDCBA';", "return typeof zzz == 'undefined' ? xxxx || 'ABCDEF' : 'FEDCBA';",
					"return typeof zzz == 'undefined' ? xxxx = 'ABCDEF' : 'FEDCBA';", "return typeof zzz != 'undefined' ? xxxx = 'FEDCBA' : 'ABCDEF';",
					"return typeof zzz == 'undefined' ? 'ABCDEF' : xxxx = 'FEDCBA';", "return typeof zzz != 'undefined' ? 'FEDCBA' : xxxx = 'ABCDEF';",
					// nested ternary
					"return aa ? bb ? 'abc' : 'def' : cc ? 'xyz' : 'ABCDEF'", "return aa ? bb ? 'abc' : 'def' : !cc ? 'ABCDEF' : 'xyz'",
					"return !aa ? bb ? 'abc' : 'ABCDEF' : cc ? 'xyz' : 'def'", "return !aa ? !bb ? 'ABCDEF' : 'abc' : cc ? 'xyz' : 'def'",
					// nested ternary with assignment - ternary check
					"var g; return aa ? g = bb ? 'abc' : 'def' : g = cc ? 'xyz' : 'ABCDEF'", "var g; return aa ? g = bb ? 'abc' : 'def' : g = !cc ? 'ABCDEF' : 'xyz'",
					"var g; return !aa ? g = bb ? 'abc' : 'ABCDEF' : g = cc ? 'xyz' : 'def'", "var g; return !aa ? g = !bb ? 'ABCDEF' : 'abc' : g = cc ? 'xyz' : 'def'",
					// nested ternary with assignment - assignment check
					"var g; aa ? g = bb ? 'abc' : 'def' : g = cc ? 'xyz' : 'ABCDEF'; return g;", "var g; aa ? g = bb ? 'abc' : 'def' : g = !cc ? 'ABCDEF' : 'xyz'; return g;",
					"var g; !aa ? g = bb ? 'abc' : 'ABCDEF' : g = cc ? 'xyz' : 'def'; return g;", "var g; !aa ? g = !bb ? 'ABCDEF' : 'abc' : g = cc ? 'xyz' : 'def'; return g;",
					// nested ternary with assignments
					"var g; return aa ? bb ? g = 'abc' : g = 'def' : cc ? g = 'xyz' : g = 'ABCDEF'",
					"var g; return aa ? bb ? g = 'abc' : g = 'def' : !cc ? g = 'ABCDEF' : g = 'xyz'",
					"var g; return !aa ? bb ? g = 'abc' : g = 'ABCDEF' : cc ? g = 'xyz' : g = 'def'",
					"var g; return !aa ? !bb ? g = 'ABCDEF' : g = 'abc' : cc ? g = 'xyz' : g = 'def'",
					// nested ternary with hard assignments
					"var g = { d : {} }; return aa ? g.d.a = bb ? g.d.z = 'abc' : g.d.z = 'def' : g.d.b = cc ? g.d.z = 'xyz' : g.d.z = 'ABCDEF'",
					"var g = { d : {} }; return aa ? g.d.a = bb ? g.d.z = 'abc' : g.d.z = 'def' : g.d.b = !cc ? g.d.z = 'ABCDEF' : g.d.z = 'xyz'",
					"var g = { d : {} }; return !aa ? g.d.a = bb ? g.d.z = 'abc' : g.d.z = 'ABCDEF' : g.d.b = cc ? g.d.z = 'xyz' : g.d.z = 'def'",
					"var g = { d : {} }; return !aa ? g.d.a = !bb ? g.d.z = 'ABCDEF' : g.d.z = 'abc' : g.d.b = cc ? g.d.z = 'xyz' : g.d.z = 'def'",
					// temporary/detachable
					"var y = 5, x = 4; return x + 2 > y ? 'ABCDEF' : 'FEDCBA';", "var y = 5, x = 4; return x + 1 >= y ? 'ABCDEF' : 'FEDCBA';",
					"var y = 5, x = 4; return x + 1 == y ? 'ABCDEF' : 'FEDCBA';", "var y = 5, x = 4; return x + 1 === y ? 'ABCDEF' : 'FEDCBA';",
					"var y = 5, x = 4; return x - 2 < y ? 'ABCDEF' : 'FEDCBA';", "var y = 5, x = 4; return x + 1 <= y ? 'ABCDEF' : 'FEDCBA';",
					"var y = 5, x = 4; return x + 2 < y * x ? 'ABCDEF' : 'FEDCBA';", "var y = 5, x = 4; return x - y < y * x ? 'ABCDEF' : 'FEDCBA';",

					// map
					"var a = { b : { d : { check : 'ABCDEF' } }, c : [] }; return a.b.d.check;",
					"var a = { b : { d : { check : 'ABCDEF', e : [] } }, c : [] }; return a.b.d.check;",
					"var a = { b : { d : { check : 'ABCDEF', e : [f] } }, c : [] }; return a.b.d.check;", "var a = { b : { d : 'ABCDEF', e : [f+g] } }; return a.b.d;",
					"var a = { b : { d : { check : 'ABCDEF', e : [f,g] } }, c : [] }; return a.b.d.check;",
					"var a = { b : { d : { c1 : 'ABC' }, e : { c2 : 'DEF', f : [] } }, c : [] }; return a.b.d.c1 + a.b.e.c2;",
					"var a = { b : { d : { c1 : 'ABC' }, e : { c2 : 'DEF', f : [g] } }, c : [] }; return a.b.d.c1 + a.b.e.c2;",
					"var a = { b : { d : { c1 : 'ABC' }, e : { c2 : 'DEF', f : [g+h] } }, c : [] }; return a.b.d.c1 + a.b.e.c2;",

					//
					"var a = { b : 1, c : 2 }; return typeof a == 'object' ? 'ABCDEF' : 'FEDCBA';",
					"var a = { b : 1, c : 2 }; return typeof a.b == 'number' ? 'ABCDEF' : 'FEDCBA';",
					"var a = { b : 1, c : 2 }; return typeof a != 'object' ? 'FEDCBA' : 'ABCDEF';",
					"var a = { b : 1, c : 2 }; return typeof a.b != 'number' ? 'FEDCBA' : 'ABCDEF';",

					"var content = {}, form = content.form ? { $layout : 'layout-form', data : content.form } : 'ABCD'; return form + 'EF';",
					"var content = { form : true }, a = 'ABCD', form = content.form ? { $layout : 'layout-form', data : content.form, s : a } : ''; return form.s + 'EF';",

					// function to scope visibility
					"var test = 'AB'; function f(test){ return test + 'CDEF'; }; return f(test);",
					"var test = 'AB'; function f(test){ test += 'CD'; return 'CDEF'; }; return test += f(test);",

					// function to scope visibility
					"var test = 'AB'; function f(){ return test += 'CDEF'; }; return f();", "var test = 'AB'; function f(a){ return test += 'CDEF'; }; return f();",
					"var test = 'AB'; function f(a){ return test += 'CDEF'; }; return f(0);", "var test = 'AB'; function f(a,b){ return test += 'CDEF'; }; return f();",
					"var test = 'AB'; function f(a,b){ return test += 'CDEF'; }; return f(0);", "var test = 'AB'; function f(a,b){ return test += 'CDEF'; }; return f(0,1);",
					"var test = 'AB'; function f(a,b,c){ return test += 'CDEF'; }; return f();", "var test = 'AB'; function f(a,b,c){ return test += 'CDEF'; }; return f(0,1);",
					"var test = 'AB'; function f(a,b,c){ return test += 'CDEF'; }; return f(0,1,2);",
					"var test = 'AB'; function f(left){ return left ? f(left-1) : test += 'CDEF'; }; return f(4);",
					"var test = 'AB'; function f(){ test += 'CDEF'; }; f(); return test;",

					"var test = 'AB'; function f(){ return test + 'CDEF'; }; return f();", "var test = 'AB'; function f(){ test += 'CD'; return 'EF'; }; return test += f();",

					"var test = function(){ return 'ABCDEF';}; return test();", "function test(){ return 'ABCDEF';} return test();",

					"var test = function(a){ return a + 'DEF';}; return test('ABC');", "function test(a){ return a + 'DEF';} return test('ABC');",

					"var test = function(a, b){ return a + 'CD' + b;}; return test('AB', 'EF');", "function test(a, b){ return a + 'CD' + b;} return test('AB', 'EF');",

					"var test, i, arr = ['A','B','C','D','E','F']; $output(test) for keys(i in arr){ =arr[i]; }; return test;",
					"var test, arr = ['A','B','C','D','E','F']; $output(test) for keys(var i in arr){ =arr[i]; }; return test;",
					"var test = 'AB', arr = ['C','D','E','F']; for keys(var i in arr){ test += arr[i]; } return test;",
					"var test = 'ABCDEF', arr = ['C','D','E','F']; for keys(var i in arr){ break; test += arr[i]; } return test;",
					"var test = 'ABCDEF', arr = ['C','D','E','F']; for keys(var i in arr){ continue; test += arr[i]; } return test;",
					"var test = 'ABCDEF', arr = ['C','D','E','F']; for keys(var i in arr){ break; continue; test += arr[i]; } return test;",

					// no error for NULL or UNDEFINED
					"var test = 'ABCDEF', arr = null; for keys(var i in arr){ test = ''; } return test;",
					"var test = 'ABCDEF', arr = undefined; for keys(var i in arr){ test = ''; } return test;",
					"var test = 'ABCDEF', arr = null; for keys(var i in arr){ test = ''; break; continue; } return test;",
					"var test = 'ABCDEF', arr = undefined; for keys(var i in arr){ test = ''; break; continue; } return test;",

					"var x = 1; for keys(var i in [0,1,2,3]){ if(x == 1) { x = 2; continue; } if(x == 2) { x = 'ABCDEF'; break; } } return x;",

					"var test, i, arr = ['A','B','C','D','E','F']; $output(test) for each(i in arr){ = i; }; return test;",
					"var test, arr = ['A','B','C','D','E','F']; $output(test) for each(var i in arr){ = i; }; return test;",
					"var test = 'AB', arr = ['C','D','E','F']; for each(var i in arr){ test += i; } return test;",
					"var test = 'ABCDEF', arr = ['C','D','E','F']; for each(var i in arr){ break; test += i; } return test;",
					"var test = 'ABCDEF', arr = ['C','D','E','F']; for each(var i in arr){ continue; test += i; } return test;",
					"var test = 'ABCDEF', arr = ['C','D','E','F']; for each(var i in arr){ break; continue; test += i; } return test;",

					// out of array should be === undefined
					"return ([])[1] === undefined && 'ABCDEF'", "return ([])[-11] === undefined && 'ABCDEF'", "return ([3])[5] === undefined && 'ABCDEF'",
					"return ([3])[-11] === undefined && 'ABCDEF'", "return ([3,7])[9] === undefined && 'ABCDEF'", "return ([3,7])[-11] === undefined && 'ABCDEF'",

					// no error for NULL or UNDEFINED
					"var test = 'ABCDEF', arr = null; for each(var i in arr){ test = ''; } return test;",
					"var test = 'ABCDEF', arr = undefined; for each(var i in arr){ test = ''; } return test;",
					"var test = 'ABCDEF', arr = null; for each(var i in arr){ test = ''; break; continue; } return test;",
					"var test = 'ABCDEF', arr = undefined; for each(var i in arr){ test = ''; break; continue; } return test;",

					"var x = 1; for each(var i in [0,1,2,3]){ if(x == 1) { x = 2; continue; } if(x == 2) { x = 'ABCDEF'; break; } } return x;",

					// while/const
					"while(true){ return 'ABCDEF'; } return 'ASD';", "while(false){ return 'ASD'; } return 'ABCDEF';", "while(false){ return 'ASD'; break; } return 'ABCDEF';",
					"while(false){ return 'ASD'; continue; } return 'ABCDEF';", "while(false){ return 'ASD'; break; continue; } return 'ABCDEF';",
					"while(true){ return 'ABCDEF'; } return 'ASD';", "while(true){ return 'ABCDEF'; break; } return 'ASD';",
					"while(true){ return 'ABCDEF'; continue; } return 'ASD';", "while(true){ return 'ABCDEF'; break; continue; } return 'ASD';",
					"while(true){ break; return 'ASD'; } return 'ABCDEF';",

					// while
					"var x; while(!x){ return 'ABCDEF'; } return 'ASD';", "var x; while(!x){ return 'ABCDEF'; break; } return 'ASD';",
					"var x; while(!x){ return 'ABCDEF'; continue; } return 'ASD';", "var x; while(!x){ return 'ABCDEF'; break; continue; } return 'ASD';",
					"var x; while(!!x){ return 'ASD'; } return 'ABCDEF';", "var x; while(!!x){ return 'ASD'; break; } return 'ABCDEF';",
					"var x; while(!!x){ return 'ASD'; continue; } return 'ABCDEF';", "var x; while(!!x){ return 'ASD'; break; continue; } return 'ABCDEF';",
					"var x; while(!x){ return 'ABCDEF'; } return 'ASD';", "var x; while(!x){ break; return 'ASD'; } return 'ABCDEF';",

					"var x = 1; while(!!x){ return 'ABCDEF'; } return 'ASD';", "var x = 1; while(!x){ return 'ASD'; } return 'ABCDEF';",
					"var x = 1; while(!!x){ return 'ABCDEF'; } return 'ASD';", "var x = 1; while(!!x){ break; return 'ASD'; } return 'ABCDEF';",

					"var x = 1; while(!!x){ if(x == 1) { x = 2; continue; } if(x == 2) { x = 'ABCDEF'; break; } } return x;",

					"var x = 2, test = ''; do{ return 'ABCDEF'; }while(false);", "var x = 2, test = 'AB'; do{ test += 'CD'; }while(false); return test + 'EF';",
					"var x = 2, test = 'AB'; do{ test += 'CD'; break; }while(false); return test + 'EF';", "var x = 2, test = ''; do{ return 'ABCDEF'; }while(!x);",
					"var x = 2, test = ''; do{ return 'ABCDEF'; }while(!!x);", "var x = 2, test = 'AB'; do{ test += 'CD'; }while(!x); return test + 'EF';",
					"var x = 2, test = 'AB'; do{ test += 'CD'; break; }while(!x); return test + 'EF';",
					"var x = 2, test = 'AB'; do{ test += 'CD'; break; }while(!!x); return test + 'EF';",

					"var x = 1; do{ if(x == 1) { x = 2; continue; } if(x == 2) { x = 'ABCDEF'; break; } }while(!!x); return x;",

					// switch/return
					"var x = 1; switch(x){ case 1: return 'ABCDEF'; }", "var x = 1; switch(x){ case 0: return 'ASD'; case 1: return 'ABCDEF'; }",
					"var x = 1; switch(x){ case 1: return 'ABCDEF'; case 2: return 'ASD'; }",
					"var x = 1; switch(x){ case 0: return 'ZSA'; case 1: return 'ABCDEF'; case 2: return 'ASD'; }",

					// switch/break
					"var x = 1; switch(x){ case 1: x = 'ABCDEF'; break; } return x;", "var x = 1; switch(x){ case 0: x = 'ASD'; break; case 1: x = 'ABCDEF'; break; } return x;",
					"var x = 1; switch(x){ case 1: x = 'ABCDEF'; break; case 2: x = 'ASD'; break; } return x;",
					"var x = 1; switch(x){ case 0: x = 'ZSA'; break; case 1: x = 'ABCDEF'; break; case 2: x = 'ASD'; break; } return x;",

					// switch/return + default
					"var x = 1; switch(x){ case 0: return 'DDD'; default: return 'ABCDEF';}", "var x = 1; switch(x){ case 1: return 'ABCDEF'; default: return 'DDD';}",
					"var x = 1; switch(x){ case 0: return 'ASD'; case 1: return 'ABCDEF'; default: return 'DDD';}",
					"var x = 1; switch(x){ case 1: return 'ABCDEF'; case 2: return 'ASD'; default: return 'DDD';}",
					"var x = 1; switch(x){ case 0: return 'ZSA'; case 1: return 'ABCDEF'; case 2: return 'ASD'; default: return 'DDD';}",

					// delete
					"var a = { b : 1, c : 2 }; return delete a.b ? 'ABCDEF' : 'FEDCBA';", "var a = { b : 1, c : 2 }; return delete a.b && 'ABCDEF';",
					"var a = { b : 1, c : 2 }; return delete a || 'ABCDEF';",

					// function arguments
					"var a = 4;{var a = 6; function f(a,b){return a + b + 'CDEF';} return f('A', 'B');}",
					"var a = 4;{var a = 5;}{var a = 6; function f(a,b){return a + b + 'CDEF';} return f('A', 'B');}",

					// general try/catch
					"var test = 'ABC'; try{ throw test + 'CD'; }catch(e){ return test + 'DEF'; }", "var test = 'AB'; try{ throw test + 'CD'; }catch(e){ return e + 'EF'; }",
					"var test = 'AB'; try{ throw test += 'C'; }catch(e){ test = e + 'D'; }finally{ test += 'EF'; } return test;",
					"var test = 'AB'; try{ test += 'CD'; }finally{ test += 'EF'; } return test;",

					"var arr = ([new String('ABCDEF')]); return arr[0];", "var arr = [new String('ABCDEF')]; return arr[0];", "var arr = [ new String('ABCDEF') ]; return arr[0];",

					"var arr = ['ABCDEF', 'BCDEFG'], n = 0; return arr[n++]", "var arr = ['ABCDEF', 'BCDEFG'], n = 0; return arr[n++].toString()",

					"var arr = ['012345', 'ABCDEF', 'BCDEFG'], n = 0; return arr[++n]", "var arr = ['012345', 'ABCDEF', 'BCDEFG'], n = 0; return arr[++n].toString()",

					"var s = '0ABCDEF9'; return s[1]+s[2]+s[3]+s[4]+s[5]+s[6];",

					"var Myx = { Class : function(){ this.test = 'ABC'; } }; return new Myx.Class().test + 'DEF';",
					"var Myx = { Class : function(a){ this.test = a + 'CD'; } }; return new Myx.Class('AB').test + 'EF';",

					"var re = new RegExp('(\\\\w+),\\\\s(\\\\w+)'); return 'DEF, ABC'.replace(re, '$2$1');", "var re = /(\\w+),\\s(\\w+)/; return 'DEF, ABC'.replace(re, '$2$1');",

					"var re = new RegExp('(\\\\w+),\\\\s(\\\\w+)', 'i'); return 'DEF, ABC'.replace(re, '$2$1');",
					"var re = /(\\w+),\\s(\\w+)/i; return 'DEF, ABC'.replace(re, '$2$1');",

					"return new RegExp('ABC\\\\S+').test('ABCDDD') && 'ABCDEF';", "return /ABC\\S+/.test('ABCDDD') && 'ABCDEF';",

					"return (new RegExp('ABCDEF')).toString().substring(1,7);", "return (/ABCDEF/).toString().substring(1,7);",

					"var Myx = { Class : function(){ this.test = 'ABC'; } }; return (new Myx.Class()).test + 'DEF';",

					// "{toString:function(){return 'ABCDEF';}}.toString()"
			}) {
				System.out.println(">>>> >>>> check: " + test);
				final ExecProcess ctx = Exec.createProcess(null, "ECMA test");
				final LanguageImpl language = AcmEcmaLanguageImpl.INSTANCE;
				final ProgramPart prepared = Evaluate.compileProgram(language, "test", test);
				System.out.println(">>>> >>>> code:" + prepared);
				final BaseObject result = prepared.callNE0(ctx, BaseObject.UNDEFINED);
				/**
				 *
				 */
				assert result != null : "NULL java value";
				/** all tests return ABCDEF */
				if (!"ABCDEF".equals(result.baseToJavaString())) {
					throw new UnsupportedOperationException("'ABCDEF' != ECMA('" + test + "'), result=" + result);
				}
			}
		}
	}
}
