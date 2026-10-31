package ru.myx.ae3.test.ecma;

import ru.myx.ae3.act.Act;
import java.util.function.Function;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BaseProperty;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.eval.LanguageImpl;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.ProgramAssembly;
import ru.myx.ae3.exec.ProgramPart;
import ru.myx.ae3.exec.ResultHandler;
import ru.myx.ae3.exec.ResultHandlerBasic;
import ru.myx.ae3.test.TestAe3;
import ru.myx.renderer.ecma.AcmEcmaLanguageImpl;

/**
 * @author myx
 *
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TestEcmaImplScripts {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public final static void main(final String[] args) throws Throwable {

		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ECMA COMPILER SCRIPTS");

		if (false) {

			{
				final ProgramAssembly assembly = new ProgramAssembly();
				Evaluate.compileExpression(assembly, "var r = ooo.oo.o[++ooo.oo.o.index](ooo.oo.o)", ResultHandler.FA_BNN_NXT);
				System.err.println(assembly.toProgram(0));
			}

			{
				final ProgramAssembly assembly = new ProgramAssembly();
				Evaluate.compileExpression(assembly, "(function(){var r = ooo.oo.o[++ooo.oo.o.index](ooo.oo.o);})", ResultHandler.FA_BNN_NXT);
				System.err.println(assembly.toProgram(0));
			}

			for (final ResultHandlerBasic store : new ResultHandlerBasic[]{
					ResultHandler.FC_PNN_RET, ResultHandler.FA_DNN_NXT, ResultHandler.FB_BSO_NXT
			}) {
				System.err.println(">>> >>> >>> >>> " + store);
				final ProgramAssembly assembly = new ProgramAssembly();
				{
					Evaluate.compileExpression(assembly, "a(a) || b(b)", store);
					System.err.println(assembly.toProgram(0));
				}
				{
					Evaluate.compileExpression(assembly, "a[a] && b[b]", store);
					System.err.println(assembly.toProgram(0));
				}
				{
					Evaluate.compileExpression(assembly, "a[a] ? b[b] : c[c]", store);
					System.err.println(assembly.toProgram(0));
				}
			}

			return;
		}

		for (final String test : new String[]{
				null, //
				"!ecma-current-std.js", "!ecma-current-libs.js", //
		}) {
			if (test == null) {
				continue;
			}
			final ExecProcess ctx = Exec.createProcess(null, "ECMA test: " + test);
			final String template = Transfer.createBuffer(TestEcmaImplScripts.class.getResourceAsStream(test)).toString();
			System.out.println("Preparing (test '" + test + "' size=" + template.length() + ")...");
			final LanguageImpl language = AcmEcmaLanguageImpl.INSTANCE;

			ctx.baseDefine(ProgramAssembly.FLAG_REPORT_ERRORS, BaseObject.FALSE, BaseProperty.ATTRS_MASK_NNN);

			Act.run(ctx, new Function<>() {

				@Override
				public Object apply(Object arg) {
					
					final ProgramPart prepared = Evaluate.compileProgram(language, "test", template);
					System.out.println("Result=" + prepared.callNE0(ctx, BaseObject.UNDEFINED));
					return null;
				}
			}, null);
		}
	}
}
