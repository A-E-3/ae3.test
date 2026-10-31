package ru.myx.ae3.test.eval;

import ru.myx.ae3.act.Act;
import java.util.function.Function;
import ru.myx.ae3.eval.BalanceType;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.eval.tokens.TokenInstruction;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.InstructionResult;
import ru.myx.ae3.exec.ProgramAssembly;
import ru.myx.ae3.exec.ProgramPart;
import ru.myx.ae3.exec.ResultHandler;
import ru.myx.ae3.test.TestAe3;

/**
 *
 */

/**
 * @author myx
 *
 */
class TestEvalOptimiser {

	static final class TestEval {

		final int instructionCountArrayOnStack;
		
		final int instructionCountObjectOnStack;
		
		final int instructionCountObjectInRR;
		
		final int instructionCountStatement;
		
		final int unbalancedResultCount;
		
		final String test;
		
		TestEval(
				final int instructionCountObjectOnStack,
				final int instructionCountObjectInRR,
				final int instructionCountStatement,
				final int unbalancedResultCount,
				final int instructionCountArrayOnStack,
				final String test) {
			this.instructionCountArrayOnStack = instructionCountArrayOnStack;
			this.instructionCountObjectOnStack = instructionCountObjectOnStack;
			this.instructionCountObjectInRR = instructionCountObjectInRR;
			this.instructionCountStatement = instructionCountStatement;
			this.unbalancedResultCount = unbalancedResultCount;
			this.test = test;
		}
		
		TestEval(final int instructionCountObjectOnStack, final int instructionCountObjectInRR, final int instructionCountStatement, final String test) {
			this(instructionCountObjectOnStack, instructionCountObjectInRR, instructionCountStatement, 1, instructionCountObjectOnStack + 1, test);
		}
		
		TestEval(final int instructionCount, final int unbalancedResultCount, final String test) {
			this(instructionCount, instructionCount, instructionCount, unbalancedResultCount, instructionCount + 1, test);
		}
		
		TestEval(final int instructionCount, final String test) {
			this(instructionCount, instructionCount, instructionCount, 1, instructionCount + 1, test);
		}
	}
	
	static final class TestJob implements Function<Void, Void> {

		private static void testEval(final ProgramAssembly assembly, final int instructionCount) {

			final int count = assembly.getInstructionCount(0);
			final ProgramPart prepared = assembly.toProgram(0);
			System.out.println(">>>> >>>> code(instructionCount=" + instructionCount + "):" + prepared);
			System.out.flush();
			assert prepared.getInstructions().length == count;
			assert count == instructionCount : "Optimiser is broken, expected: " + instructionCount + ", but: " + count;
		}
		
		private static void testEval(final ProgramAssembly assembly, final TestEval test, final int resultCount, final int instructionCount, final BalanceType balance) {

			final int truncate = assembly.size();
			try {
				System.out.println(">>>> >>>> test() balance=" + balance + ", token=" + test.test);
				final TokenInstruction token = Evaluate.compileToken(assembly, test.test, balance);
				System.out.println(">>>> >>>> >>>> token=" + token);
				if (token == null) {
					assert balance == BalanceType.STATEMENT;
					System.out.println(">>>> >>>> >>>> void balance empty token");
					return;
				}
				System.out.flush();
				assert token.assertZeroStackOperands();
				assert token.isStackValue() || resultCount != 1 || token.getResultType() == InstructionResult.NEVER;
				assert token.getResultCount() == resultCount : "Result count is broken, expected: " + resultCount + ", but: " + token.getResultCount() + "\r\n\t\ttoken code: "
						+ token.toCode();
				token.toAssembly(assembly, null, null, balance == BalanceType.STATEMENT
					? ResultHandler.FA_BNN_NXT
					: ResultHandler.FB_BSN_NXT);
				final ProgramPart program = assembly.toProgram(0);
				System.out.println(">>>> >>>> >>>> code=" + program);
				System.out.flush();
				assert program.getInstructions().length == instructionCount : "InstructionCount, expected " + instructionCount + ", but " + program.getInstructions().length;
			} finally {
				assembly.truncate(truncate);
			}
		}
		
		private final ProgramAssembly assembly;
		
		private final TestEval test;
		
		TestJob(final ProgramAssembly assembly, final TestEval test) {
			this.assembly = assembly;
			this.test = test;
		}
		
		@Override
		public Void apply(final Void arg) {

			System.out.println(">>>> >>>> #################################");
			System.out.println(">>>> >>>> #################################");
			{
				TestJob.testEval(this.assembly, this.test, 1, this.test.instructionCountObjectOnStack, BalanceType.EXPRESSION);
			}
			{
				TestJob.testEval(this.assembly, this.test, 1, this.test.instructionCountStatement, BalanceType.STATEMENT);
			}
			{
				TestJob.testEval(this.assembly, this.test, this.test.unbalancedResultCount, this.test.instructionCountObjectOnStack, BalanceType.ARGUMENT_LIST);
			}
			{
				TestJob.testEval(this.assembly, this.test, 1, this.test.instructionCountArrayOnStack, BalanceType.ARRAY_LITERAL);
			}
			{
				System.out.println(">>>> >>>> test() expression - stack: " + this.test.test);
				Evaluate.compileExpression(this.assembly, this.test.test, ResultHandler.FB_BSN_NXT);
				TestJob.testEval(this.assembly, this.test.instructionCountObjectOnStack);
			}
			{
				System.out.println(">>>> >>>> test() statement: " + this.test.test);
				Evaluate.compileStatement(this.assembly, 0, this.test.test);
				TestJob.testEval(this.assembly, this.test.instructionCountStatement);
			}
			return null;
		}
	}
	
	/**
	 *
	 * @param args
	 * @throws Throwable
	 */
	public final static void main(final String... args) throws Throwable {

		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ae3.ecma optimiser");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ProgramAssembly assembly = new ProgramAssembly();
		assembly.setReportErrors(false);
		
		final ExecProcess ctx = Exec.createProcess(null, "Eval Optimiser test");
		{
			for (final TestEval test : new TestEval[]{
					//
					// constant global
					new TestEval(1, 1, 0, 1, 1, "true"),
					// not cached
					new TestEval(1, 1, 0, 1, 1, "3.9"),
					// simplest constant expression
					new TestEval(1, 1, 0, 1, 1, "3 + 1"),
					// unary
					new TestEval(1, 1, 0, 1, 1, "-6 + 7"),
					// complex 1
					new TestEval(1, 1, 0, 1, 1, "3 + 1 * 2 - 4 / 5 % 1"),
					// variable
					new TestEval(1, 1, 1, 1, 1, "a"),
					// complex 1
					new TestEval(1, "2 * a"),
					// complex 1
					new TestEval(3, 3, 1, "3 + 1 * a - 4 / 5 % 1"),
					// complex 2
					new TestEval(1, 1, 0, 1, 1, "3 + 1 * 2 - 4 / 5 % ( -6 + 7 )"),
					// complex 3 with variable
					new TestEval(3, 3, 1, "3 + 1 * a - 4 / 5 % ( -6 + 7 )"),
					// ERRORS - unterminated string
					new TestEval(1, 1, 1, 1, 1, "a = 'b"), new TestEval(1, 1, 1, 1, 1, "a('b + 2)"), new TestEval(1, 1, 1, 1, 1, "a * ('b + 2)"),
					new TestEval(1, 1, 1, 1, 1, "a = 'b; c = d ? e : f;"),
					// ERRORS - unmatched ()
					new TestEval(1, 1, 1, 1, 1, "a * (b + 2"), new TestEval(1, 1, 1, 1, 1, "a * b + 2) + 3"),
					// ERRORS - unmatched []
					new TestEval(1, 1, 1, 1, 1, "a * b[ + 2"), new TestEval(1, 1, 1, 1, 1, "a * b] + 2"), new TestEval(1, 1, 1, 1, 1, "a * [b + 2"),
					new TestEval(1, 1, 1, 1, 1, "a * b + 2] + 3"),
					// ERRORS - unmatched ?:
					new TestEval(1, 1, 1, 1, 1, "a ? b || c"), new TestEval(1, 1, 1, 1, 1, "a = ? b || c"), new TestEval(1, 1, 1, 1, 1, "a = b : c"),
					// ERRORS - ||
					new TestEval(1, 1, 1, 1, 1, "a = b ||"), new TestEval(1, 1, 1, 1, 1, "a = || b"), new TestEval(1, 1, 1, 1, 1, "a = b || c = d"),
					// ERRORS - &&
					new TestEval(1, 1, 1, 1, 1, "a = b &&"), new TestEval(1, 1, 1, 1, 1, "a = && b"), new TestEval(1, 1, 1, 1, 1, "a = b && c = d"),
					// ERRORS - other
					new TestEval(1, 1, 1, 1, 1, "a ="), new TestEval(1, 1, 1, 1, 1, "="), new TestEval(1, 1, 1, 1, 1, "+"), new TestEval(1, 1, 1, 1, 1, "-"),
					new TestEval(1, 1, 1, 1, 1, "*"), new TestEval(1, 1, 1, 1, 1, "/"), new TestEval(1, 1, 1, 1, 1, "a+"), new TestEval(1, 1, 1, 1, 1, "a = [ b : 1, c : 2, , ]"),
					new TestEval(1, 1, 1, 1, 1, "a = { b : 1, c : 2, , }"),
					// BOR
					new TestEval(1, 1, 0, 1, 1, "true || 5"), new TestEval(1, 1, 0, 1, 1, "false || 5"), new TestEval(1, 1, 0, 1, 1, "1 || 5"),
					new TestEval(1, 1, 0, 1, 1, "2 || a"), new TestEval(1, 1, 1, "a || 3"), new TestEval(1, 1, 1, "a || true"), new TestEval(1, 1, 1, "a || false"),
					/**
					 * FIXME 2, 2, 1 -- BOR should not use CTX['b'] - invalid
					 */
					new TestEval(4, 3, 3, 1, 4, "a || b"), new TestEval(4, 4, 3, 1, 4, "a.b || c.d"), new TestEval(6, 6, 5, 1, 6, "a.b.c || d.e.f"),
					new TestEval(4, 4, 3, 1, 4, "a.b() || c.d()"), new TestEval(4, 4, 3, 1, 4, "a.b(c) || d.e(f)"), new TestEval(8, 8, 7, 1, 8, "a.b(c,d) || e.f(g,h)"),
					// BAND
					new TestEval(1, 1, 0, 1, 1, "true && 5"), new TestEval(1, 1, 0, 1, 1, "false && 5"), new TestEval(1, 1, 1, 1, 1, "true && a"),
					new TestEval(1, 1, 0, 1, 1, "false && a"), new TestEval(1, 1, 0, 1, 1, "1 && 5"), new TestEval(1, 1, 1, 1, 1, "2 && a"), new TestEval(1, 1, 1, "a && true"),
					new TestEval(2, 2, 1, "a.a && true"), new TestEval(4, 4, 1, 1, 4, "a && 3"), new TestEval(4, 4, 3, 1, 4, "a && b"), new TestEval(4, 4, 3, 1, 4, "a.b && c.d"),
					new TestEval(6, 6, 5, 1, 6, "a.b.c && d.e.f"), new TestEval(4, 4, 3, 1, 4, "a.b() && d.e()"), new TestEval(4, 4, 3, 1, 4, "a.b(c) && d.e(f)"),
					new TestEval(8, 8, 7, 1, 8, "a.b(c,d) && e.f(g,h)"),
					// choose const
					new TestEval(1, 1, 0, 1, 1, "true ? 1 : 5"), new TestEval(1, 1, 0, 1, 1, "true ? 2 : b"), new TestEval(1, 1, 1, 1, 1, "true ? a : 3"),
					new TestEval(1, 1, 1, 1, 1, "true ? a : b"), new TestEval(1, 1, 1, "true ? a.b : c.d"), new TestEval(2, 2, 2, "true ? a.b.c : d.e.f"),
					new TestEval(1, 1, 1, "true ? a.b() : d.e()"), new TestEval(1, 1, 1, "true ? a.b(c) : d.e(f)"), new TestEval(3, 3, 3, "true ? a.b(c,d) : e.f(g,h)"),
					// choose const
					new TestEval(1, 1, 0, 1, 1, "false ? 1 : 5"), new TestEval(1, 1, 1, 1, 1, "false ? 2 : b"), new TestEval(1, 1, 0, 1, 1, "false ? a : 3"),
					new TestEval(1, 1, 1, 1, 1, "false ? a : b"), new TestEval(1, 1, 1, "false ? a.b : c.d"), new TestEval(2, 2, 2, "false ? a.b.c : d.e.f"),
					new TestEval(1, 1, 1, "false ? a.b() : d.e()"), new TestEval(1, 1, 1, "false ? a.b(c) : d.e(f)"), new TestEval(3, 3, 3, "false ? a.b(c,d) : e.f(g,h)"),
					// choose
					new TestEval(1, "z ? true : false"), new TestEval(1, "z ? false : true"), new TestEval(2, 2, 1, "z.x ? true : false"),
					new TestEval(2, 2, 1, "z.x ? false : true"), new TestEval(5, 5, 1, "zxx ? 1 : 5"), new TestEval(5, 5, 3, 1, 6, "z ? 2 : b"),
					
					new TestEval(5, 5, 3, "z ? a : 3"), new TestEval(5, 5, 5, "z ? a : b"), new TestEval(5, 5, 5, "z ? a.b : c.d"), new TestEval(7, 7, 7, "z ? a.b.c : d.e.f"),
					new TestEval(5, 5, 5, "z ? a.b() : d.e()"), new TestEval(5, 5, 5, "z ? a.b(c) : d.e(f)"), new TestEval(9, 9, 9, "z ? a.b(c,d) : e.f(g,h)"),
					// long access
					new TestEval(36, "a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z.A.B.C.D.E.F.G.H.I.J.K"),
					// COBJECT
					new TestEval(1, 1, 1, "{}"), new TestEval(2, 2, 3, "{a:1}"), new TestEval(2, 2, 3, "{a:1,}"), new TestEval(3, 3, 4, "{a:1, b:2}"),
					new TestEval(3, 3, 4, "{a:1, b:2,}"), new TestEval(8, 8, 9, "{a:1, m:{aa:11, bb:33}, c:3}"),
					// constant global output
					new TestEval(1, "= true"),
					// var output
					new TestEval(1, "= o"), new TestEval(1, 1, 1, 1, 1, "a"), new TestEval(2, "a+b*c"), new TestEval(1, "a++"), new TestEval(2, "a++ +b"),
					// assign simple frame
					new TestEval(1, "a = 5"), new TestEval(1, "a = b"), new TestEval(2, "a = b * c"), new TestEval(2, "a += 5"), new TestEval(2, "a += b"),
					new TestEval(3, "a += b + c"),
					// assign simple access
					new TestEval(1, "a.f = 5"), new TestEval(1, "a.f = b"), new TestEval(2, "a.f = b * c"), new TestEval(3, "a.f += 5"), new TestEval(3, "a.f += b"),
					new TestEval(4, "a.f += b + c"),
					// assign simple special access
					new TestEval(1, "this.f = 5"), new TestEval(1, "this.f = b"), new TestEval(2, "this.f = b * c"), new TestEval(2, "this.f += 5"),
					new TestEval(3, "this.f.g += 5"), new TestEval(2, "this.f += b"), new TestEval(3, "this.f.g += b"), new TestEval(3, "this.f += b + c"),
					new TestEval(4, "this.f.g += b + c"),
					// assign simple index
					new TestEval(1, "a[f] = 5"), new TestEval(1, "a[f] = b"), new TestEval(2, "a[f] = b * c"), new TestEval(3, "a[f] += 5"), new TestEval(3, "a[f] += b"),
					new TestEval(4, "a[f] += b + c"),
					// assign simple special index
					new TestEval(1, "this[f] = 5"), new TestEval(1, "this.c[f] = 5"), new TestEval(1, "this[f] = b"), new TestEval(1, "this.c[f] = b"),
					new TestEval(2, "this[f] = b * c"), new TestEval(2, "this.c[f] = b * c"), new TestEval(3, "this[f] += 5"), new TestEval(3, "this[f] += b"),
					new TestEval(4, "this[f] += b + c"),
					// assign complex access
					new TestEval(2, "a().f = 5"), new TestEval(2, "a().f = b"), new TestEval(3, "a().f = b * c"), new TestEval(4, "a().f += 5"), new TestEval(4, "a().f += b"),
					new TestEval(5, "a().f += b + c"),
					// assign complex special access
					new TestEval(1, "this.a.f = 5"), new TestEval(2, "this.a.f.g = 5"), new TestEval(1, "this.a.f = b"), new TestEval(2, "this.a.f.g = b"),
					new TestEval(2, "this.a.f = b * c"), new TestEval(3, "this.a.f.g = b * c"), new TestEval(3, "this.a.f += 5"), new TestEval(4, "this.a.f.g += 5"),
					new TestEval(3, "this.a.f += b"), new TestEval(4, "this.a.f.g += b"), new TestEval(4, "this.a.f += b + c"), new TestEval(5, "this.a.f.g += b + c"),
					// assign complex index
					new TestEval(3, "a()[f()] = 5"), new TestEval(3, "a()[f()] = b"), new TestEval(4, "a()[f()] = b * c"), new TestEval(5, "a()[f()] += 5"),
					new TestEval(5, "a()[f()] += b"), new TestEval(6, "a()[f()] += b + c"),
					// assign complex index
					new TestEval(3, "z.a()[f()] = 5"), new TestEval(3, "z.a()[f()] = b"), new TestEval(4, "z.a()[f()] = b * c"), new TestEval(5, "z.a()[f()] += 5"),
					new TestEval(5, "z.a()[f()] += b"), new TestEval(6, "z.a()[f()] += b + c"),
					// assign complex index special
					new TestEval(3, "this.a()[f()] = 5"), new TestEval(3, "this.a()[f()] = b"), new TestEval(4, "this.a()[f()] = b * c"), new TestEval(5, "this.a()[f()] += 5"),
					new TestEval(5, "this.a()[f()] += b"), new TestEval(6, "this.a()[f()] += b + c"),
					// assign complex index
					new TestEval(4, "z[a()][f()] = 5"), new TestEval(4, "z[a()][f()] = b"), new TestEval(5, "z[a()][f()] = b * c"), new TestEval(6, "z[a()][f()] += 5"),
					new TestEval(6, "z[a()][f()] += b"), new TestEval(7, "z[a()][f()] += b + c"),
					// assign complex index special
					new TestEval(4, "this[a()][f()] = 5"), new TestEval(4, "this[a()][f()] = b"), new TestEval(5, "this[a()][f()] = b * c"), new TestEval(6, "this[a()][f()] += 5"),
					new TestEval(6, "this[a()][f()] += b"), new TestEval(7, "this[a()][f()] += b + c"),
					// assign precedence
					new TestEval(7, "a = b = c = d = e = f = g = 'ABCDEF'"), new TestEval(5, "a.z = b.x = c.y = d.w = e.v = 'ABCDEF'"),
					new TestEval(8, "a.z.k = b.x.l = c.y.m = d.w.n = 'ABCDEF'"),
					// some real life examples
					new TestEval(1, "n = ('b', 'c', 'cl', 'co', 'd', 'e', 'f', 'g', 'h', 'ho', 'i', 'in', 'j', 'ju', 'l', 'm', 'o', 'p', 'pu', 'r', 're', 's', 'so', 't', 'u')"),
					
					// choose prio
					new TestEval(8, 8, 7, 1, 8, "key ? mapp[kez] || or : mabb"), new TestEval(8, 8, 7, 1, 8, "key ? mapp[kez] && and : mabb"),
					new TestEval(5, 5, 5, 1, 6, "key ? left || 4 : right || 3"), new TestEval(11, 11, 9, 1, 10, "key ? left || a : right || b"),
					new TestEval(11, 11, 9, 1, 10, "key ? left && a : right && b"), new TestEval(5, 5, 5, 1, 6, "key ? left - a : right / b"),
					// choose & assign prios
					new TestEval(5, 5, 5, "key ? left = a : right = b"), new TestEval(6, 6, 6, "x = key ? left = a : right = b"),
					new TestEval(6, 6, 6, "x.y = key ? left = a : right = b"), new TestEval(7, 7, 7, "x.y.z = key ? left = a : right = b"),
					new TestEval(8, 8, 8, "x.y.z['w'] = key ? left = a : right = b"), new TestEval(9, 9, 9, "key ? key2 ? left = a : middle = c : right = b"),
					new TestEval(10, 10, 10, "x = key ? key2 ? left = a : middle = c : right = b"),
					new TestEval(10, 10, 10, "x.y = key ? key2 ? left = a : middle = c : right = b"),
					new TestEval(11, 11, 11, "x.y.z = key ? key2 ? left = a : middle = c : right = b"),
					new TestEval(12, 12, 12, "x.y.z['w'] = key ? key2 ? left = a : middle = c : right = b"),
					new TestEval(9, 9, 9, "= key ? key2 ? left = a : middle = c : right = b"),
					new TestEval(13, "c.t  ? afterPoint( c.t ) : c.lT ? afterPoint( c.lT ) : afterPoint( c.tT || 'unt' )"),
					// calls
					new TestEval(1, "substring0()"), new TestEval(1, "null()"), new TestEval(1, "substring1(1)"), new TestEval(2, "trim().matches()"),
					new TestEval(2, "uname.trim().matches()"), new TestEval(3, "req.uname.trim().matches()"),
					new TestEval(3, "Request.username.trim().matches('^[a-zA-Z0-9\\-\\.]{3,16}$')"),
					new TestEval(4, "!Request.username.trim().matches('^[a-zA-Z0-9\\-\\.]{3,16}$')"), new TestEval(7, "con.ban.getBannersItems( 'a' , con.keyw,  'b' ).entrySet()"),
					// named calls
					new TestEval(3, "substring1( a = 1 )"), new TestEval(4, "substring1( a = 1, b = 3 )"), new TestEval(4, "substring1( a = 1, b )"),
					new TestEval(4, "substring1( z, a = 1 )"),
					// simple access
					new TestEval(1, "aa.a"), new TestEval(1, "= aa.bb"), new TestEval(2, "= aa.bb.cc"),
					// another access
					new TestEval(1, "null.a"), new TestEval(1, "a.substring()"), new TestEval(2, "(a||'a').substring()"), new TestEval(4, "(a||b).substring()"),
					new TestEval(4, "(a&&b).substring()"),
					// check another const
					new TestEval(1, "null.a()"), new TestEval(1, "null['a']()"), new TestEval(1, "null[a]()"), new TestEval(2, "null[a+b]()"), new TestEval(2, "(c+d)[a]()"),
					new TestEval(3, "(c+d)[a+b]()"), new TestEval(1, "a.substring(1)"), new TestEval(1, "'string'.substring(1)"),
					/**
					 * FIXME 2,2,1
					 */
					new TestEval(2, 2, 2, "number + 'string'.substring(1)"), new TestEval(4, "result = typeof number + 'string'.substring(1)"),
					// arrays const
					/**
					 * TODO: add instructionCountNone <code>
					new TestEval( 1, 2, "1, 2" ),
					new TestEval( 3, 3, "1, 2, 3" ),
					new TestEval( 4, 4, "1, 2, 3, 4" ),
					</code>
					 */
					// arrays var
					new TestEval(2, 2, "a, b"), new TestEval(3, 3, "a, b, c"), new TestEval(4, 4, "a, b, c, d"),
					// delete
					new TestEval(1, "delete a"), new TestEval(1, "delete a.b"), new TestEval(2, "delete a.b.c"), new TestEval(3, "delete a.b.c.d"),
					// array
					new TestEval(7, "test=[ 0, 1, 2, 3, 4 ]"), new TestEval(7, "test=[ 0, 1, 2, 3, 4, ]"),
					new TestEval(23, "test=[ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 ]"),
					//
					
					new TestEval(8, 8, 8, "function(){'ABC' + z && z();}"), new TestEval(8, 8, 8, "function(a){'ABC' + z && z();}"),
					new TestEval(8, 8, 8, "function(a,b){'ABC' + z && z();}"),
					
					new TestEval(8, 8, 8, "function(){ return 'ABC' + z && z();}"), new TestEval(8, 8, 8, "function(a){ return 'ABC' + z && z();}"),
					new TestEval(8, 8, 8, "function(a,b){ return 'ABC' + z && z();}"),
					
					new TestEval(6, "function(){return 'ABCDEF';}"), new TestEval(6, "function(){return '}}}ABCDEF'.substring(3);}"),
					new TestEval(6, "function(){return '{{{ABCDEF'.substring(3);}"), new TestEval(6, "function(a){return a + 'DEF';}"),
					new TestEval(7, "function(a){return a + '}}}DEF'.substring(3);}"), new TestEval(7, "function(a){return a + '{{{DEF'.substring(3);}"),
					new TestEval(7, "function(a, b){return a + 'CD' + b;}"), new TestEval(8, "function(a, b){return a + '}}CD'.substring(2) + b;}"),
					new TestEval(8, "function(a, b){return a + '{{CD'.substring(2) + b;}"),
					
					new TestEval(21, "function(){ return { a:function(){ return 0; }, b : function(){ return 1; } }; }"),
					
					/**
					 * for optimiser
					 */
					new TestEval(9, "function(){for(var i in null){return 5;}}"), new TestEval(9, "function(){for(var i in undefined){return 5;}}"),
					new TestEval(5, "function(){for(var i in z){;}}"), new TestEval(17, "function(){for(var i in z){return i;}}"),
					
					new TestEval(10, 10, 7, 2, 11, "M.f(M.r(7)/17), M.c(99 + M.r(5)/15) && 'A' || 'B'"),
					new TestEval(12, 12, 9, 2, 13, "M.f(M.r(7)/17), (M.c(99 + M.r(5)/15) && 'A') && 'B'"),
					new TestEval(10, 10, 10, "(M.f(M.r(7)/17), M.c(99 + M.r(5)/15) && 'A' || 'B' )"),
					new TestEval(13, 13, 11, 1, 12, "(M.f(M.r(7)/17), M.c(99 + M.r(5)/15) && a && b )"),
					new TestEval(10, 10, 10, "=(M.f(M.r(5)/15), M.c(1 + M.r(5)/15) && 'A' || 'B' )"),
					new TestEval(10, 10, 7, 2, 11, "(= M.f(M.r(5)/15)), M.c(1 + M.r(5)/15) && 'A' || 'B'"), new TestEval(7, 7, 7, 1, 8, "(brr = {})['aaa' + ['[]']] += 55"),
					new TestEval(9, 9, 9, 1, 10, "(brr = {})['aaa' + ['[]']] += 5 + ['[]']"), new TestEval(7, 7, 7, "manMap[Rec.manId].data[cUA].userStates[Rec.userState]"),
					new TestEval(10, "manMap[Rec.manId].data[cUA].userStates[Rec.userState]++"),
					new TestEval(10, "this.manMap[this.Rec.manId].data[this.cUA].userStates[this.Rec.userState]++"),
					new TestEval(10, "manMap[Rec.manId].data[cUA].userStates[Rec.userState] += a+b"),
					new TestEval(10, "manMap[Rec.manId].data[cUA].userStates[Rec.userState] += a+b"),
					new TestEval(
							24,
							"Ima.graDrTx( gra,  chrs.chAt( i ), ( rnd > 0 ? Rnd.int( 1 ) + 3 : 3) + i * 14 + 10, rnd < 0 ? Rnd.int( 1 ) + 7 : 5,  'xx' , true, false, 30, 0, 5)"),
					
					//
			}) {
				Act.run(ctx, new TestJob(assembly, test), null);
			}
		}
		System.out.println(">>>> >>>> optimizer test done.");
	}
}
