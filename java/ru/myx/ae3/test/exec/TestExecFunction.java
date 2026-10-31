package ru.myx.ae3.test.exec;

import ru.myx.ae3.act.Act;
import ru.myx.ae3.base.BaseArray;
import ru.myx.ae3.base.BaseFunctionAbstract;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.eval.LanguageImpl;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecCallableFull;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.ExecStateCode;
import ru.myx.ae3.exec.ProgramPart;
import ru.myx.ae3.help.Convert;
import ru.myx.ae3.test.TestAe3;
import ru.myx.renderer.ecma.AcmEcmaLanguageImpl;

/**
 * @author myx
 *
 */
class TestExecFunction extends BaseFunctionAbstract implements ExecCallableFull {
	
	
	/**
	 *
	 * @param args
	 * @throws Throwable
	 */
	public final static void main(final String... args) throws Throwable {
		
		
		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ae3.exec function");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ExecProcess ctx = Exec.createProcess(Exec.currentProcess(), "test");
		ctx.baseDefine("testf", new TestExecFunction());
		Act.run(ctx, argument -> {
			final LanguageImpl language = AcmEcmaLanguageImpl.INSTANCE;
			final ProgramPart prepared = Evaluate.compileProgram(language, "test", "testf( 22, key = 55, 33, f = testf, xxx = 5 )");
			System.out.println("Result=" + prepared.callNE0(ctx, BaseObject.UNDEFINED));
			ctx.dump(System.out);
			return null;
		}, ctx);
		
	}
	
	@Override
	public final int execArgumentsAcceptable() {
		
		
		return Integer.MAX_VALUE;
	}
	
	@Override
	public ExecStateCode execCallImpl(final ExecProcess context) throws Exception {
		
		
		final BaseArray list = context.contextGetArguments();
		System.out.println(">>>> >>>> >>>> arguments: " + list);
		System.out.println(">>>> >>>> >>>> arguments.key: " + Convert.MapEntry.toObject(list, "key", null));
		System.out.println("!!!!l " + list.length());
		assert Evaluate.evaluateBoolean("arguments.key == 55", context, null);
		return null;
	}
	
	@Override
	public BaseObject execScope() {
		
		
		/**
		 * executes in real current scope
		 */
		return ExecProcess.GLOBAL;
	}
}
