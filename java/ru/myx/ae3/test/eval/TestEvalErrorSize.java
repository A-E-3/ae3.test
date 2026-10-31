package ru.myx.ae3.test.eval;

import java.lang.ref.SoftReference;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.common.HolderSimple;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecArgumentsEmpty;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.Instruction;
import ru.myx.ae3.exec.ModifierArguments;
import ru.myx.ae3.exec.OperationsA10;
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
class TestEvalErrorSize {
	
	
	private static void checkError(final ExecProcess ctx) throws Exception {
		
		
		final Instruction error = OperationsA10.ZTCALLS.instruction(ModifierArguments.AC12NULL, 0, ResultHandler.FA_BNN_NXT);

		final ProgramPart program = new ProgramPart(new Instruction[]{
				error,
		});

		System.out.println(">>>> eval result=" + program.execCallPrepare(ctx, ctx.rb4CT, ResultHandler.FA_BNN_NXT, true, ExecArgumentsEmpty.INSTANCE));

		final String check = String.valueOf(ctx.vmGetResultDetachable());
		assert check.length() < 65536 : check;

		assert check.contains("var1");
		assert check.contains("var2");
		assert check.contains("var3");
		assert check.contains("var4");
		assert check.contains("var5");
		assert check.contains("var6");

		assert check.contains("0123456789");
		assert check.contains("test:10000");
		assert check.contains("key10000");
	}

	/**
	 *
	 * @param args
	 * @throws Throwable
	 */
	public final static void main(final String... args) throws Throwable {
		
		
		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ae3.eval.error size");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ExecProcess ctx = Exec.createProcess(null, "test");
		final String longText;
		final BaseList<Object> longArray = new BaseNativeArray();
		final BaseMap longObject = new BaseNativeObject();
		{
			final StringBuilder longTextBuilder = new StringBuilder();
			for (int i = 10000; i > 0; --i) {
				longTextBuilder.append("0123456789\r\n");
				longArray.baseDefaultPush(Base.forString("test:" + i));
				longObject.baseDefine("key" + i, BaseObject.TRUE);
			}
			longText = longTextBuilder.toString();
			assert longText.length() == 120000;
		}
		// TestEvalErrorSize.checkError( ctx );
		ctx.baseDefine("var1", longText);
		assert ctx.baseGet("var1", BaseObject.UNDEFINED).baseToJavaString() == longText;
		// TestEvalErrorSize.checkError( ctx );
		ctx.baseDefine("var2", new HolderSimple<>(longText));
		ctx.baseDefine("var3", Base.forUnknown(new SoftReference<>(longText)));
		ctx.baseDefine("var4", new BaseNativeObject("var41", longText));
		ctx.baseDefine("var5", longArray);
		ctx.baseDefine("var6", longObject);
		TestEvalErrorSize.checkError(ctx);
		System.out.println(">>>> >>>> TEST ae3.eval.error size finished.");
	}
}
