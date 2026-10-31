package ru.myx.ae3.test.exec;

import ru.myx.ae3.eval.BalanceType;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.eval.tokens.TokenInstruction;
import ru.myx.ae3.exec.ProgramAssembly;
import ru.myx.ae3.test.TestAe3;

/**
 * @author myx
 * 
 */
class TestExecCompiler {
	
	static class TestEval {
		
		final int resultCount;
		
		final String test;
		
		TestEval(final int resultCount, final String test) {
			this.resultCount = resultCount;
			this.test = test;
		}
	}
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public final static void main(final String... args) throws Throwable {
		
		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ae3.exec parser");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ProgramAssembly assembly = new ProgramAssembly();
		{
			for (final TestEval test : new TestEval[]{
					//
					new TestEval(1, "true"), new TestEval(1, "3.9"), new TestEval(1, "3 + 1"), new TestEval(1, "-6 + 7"), new TestEval(1, "a.b"), new TestEval(1, "a.b.c"),
					new TestEval(1, "a.b.c.d"), new TestEval(1, "a.b.c.d.e"), new TestEval(1, "a.b.c.d.e.f()"), new TestEval(3, "3, 1 * 2, 4 / 5 % 1"),
					new TestEval(1, "a.substring(1)"), new TestEval(1, "a.b - c.d != e"), new TestEval(1, "list[88]"), new TestEval(1, "list[88]()"),
					new TestEval(1, "list[88]() !=  77"), new TestEval(2, "'hello', 'string'.substring(1)"),
					new TestEval(4, "'hello', 'string'.substring(1), bbbframe(), this.aaathis()"), new TestEval(4, "'hello', 'string'.substring(1), bbbframe(1), this.aaathis(1)"),
					new TestEval(4, "'hello', 'string'.substring(1,2), bbbframe(1,2), this.aaathis(1,2)"),
					new TestEval(4, "'hello', 'string'.s(a = 1, b = 2), bf(a = 1, b = 2), this.at(a = 1, b = 2)"), new TestEval(1, "c += a + b"),
					new TestEval(4, "a++, b--, c+=1, d-=7"), new TestEval(1, "dd.c += a + b"), new TestEval(4, "bb.a++, cc.b--, dd.c+=1, ee.d-=7"),
					
					//
			}) {
				{
					System.out.println(">>>> >>>> test() token=" + test.test);
					{
						final TokenInstruction token = Evaluate.compileToken(assembly, test.test, BalanceType.ARGUMENT_LIST);
						System.out.println(">>>> >>>> >>>> NONE result=" + token.getResultType() + ", token=" + token);
						assert token.assertZeroStackOperands();
						assert token.getResultCount() == test.resultCount : "Expecting " + test.resultCount + ", but: " + token.getResultCount();
					}
					{
						final TokenInstruction token = Evaluate.compileToken(assembly, test.test, BalanceType.ARRAY_LITERAL);
						System.out.println(">>>> >>>> >>>> ARRY result=" + token.getResultType() + ", token=" + token);
						assert token.assertStackValue();
					}
					{
						final TokenInstruction token = Evaluate.compileToken(assembly, test.test, BalanceType.EXPRESSION);
						System.out.println(">>>> >>>> >>>> OBJT result=" + token.getResultType() + ", token=" + token);
						assert token.assertStackValue();
					}
					{
						final TokenInstruction token = Evaluate.compileToken(assembly, test.test, BalanceType.STATEMENT);
						/**
						 * statement could produce NULL token, when result is
						 * constant
						 */
						System.out.println(">>>> >>>> >>>> VOID " + (token == null
							? ""
							: "result=" + token.getResultType() + ", ") + "token=" + token);
						assert token == null || token.assertStackValue();
					}
				}
			}
		}
	}
}
