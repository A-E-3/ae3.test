package ru.myx.ae3.test.eval;

import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.e4.parse.TokenType;
import ru.myx.ae3.eval.parse.ParseConstants;
import ru.myx.ae3.eval.parse.TK_WRAP_TOKEN_ARGS1;
import ru.myx.ae3.eval.parse.TK_WRAP_TOKEN_ARGS2;
import ru.myx.ae3.eval.tokens.TokenInstruction;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.InstructionResult;
import ru.myx.ae3.exec.Instructions;
import ru.myx.ae3.exec.ModifierArgument;
import ru.myx.ae3.exec.ModifierArgumentA30IMM;
import ru.myx.ae3.exec.ModifierArguments;
import ru.myx.ae3.exec.ProgramAssembly;
import ru.myx.ae3.exec.ProgramPart;
import ru.myx.ae3.exec.ResultHandler;
import ru.myx.ae3.exec.ResultHandlerBasic;
import ru.myx.ae3.test.TestAe3;

/**
 *
 */

/** @author myx */
class TestEvalParseTokens {

	static class TestEval implements TokenInstruction {

		private final int operands;

		TestEval(final int operands) {

			this.operands = operands;
		}

		@Override
		public int getOperandCount() {

			return this.operands;
		}

		@Override
		public int getPriorityLeft() {

			return 0;
		}

		@Override
		public int getPriorityRight() {

			return 0;
		}

		@Override
		public int getResultCount() {

			return 1;
		}

		@Override
		public InstructionResult getResultType() {

			return InstructionResult.OBJECT;
		}

		@Override
		public TokenType getTokenType() {

			return TokenType.OPERATOR;
		}

		@Override
		public void toAssembly(final ProgramAssembly assembly, final ModifierArgument argumentA, final ModifierArgument argumentB, final ResultHandlerBasic store) {

			assembly.addInstruction(Instructions.INSTR_LOAD_1_T_SN_NEXT);
		}

		@Override
		public String toCode() {

			return null;
		}
	}

	/** @param args
	 * @throws Throwable */
	@SuppressWarnings("unused")
	public final static void main(final String... args) throws Throwable {

		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ae3.eval.parse tokens");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			/** general stuff */
			{
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.assertAccessReference();
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.assertZeroStackOperands();
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.assertStackValue();
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toReferenceDelete();
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toReferenceObject();
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toReferenceProperty();
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toReferenceReadBeforeWrite(assembly, ModifierArgumentA30IMM.EMPTY_STRING, ModifierArgumentA30IMM.ZERO, true, true, false);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toReferenceWriteAfterRead(
							assembly,
							ModifierArgumentA30IMM.EMPTY_STRING,
							ModifierArgumentA30IMM.ZERO,
							ModifierArgumentA30IMM.NULL,
							false,
							ResultHandler.FA_BNN_NXT);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
		}
		final ExecProcess ctx = Exec.createProcess(null, "test");
		{
			/** less general stuff */
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toStackValue(assembly, true);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toStackValue(assembly, false);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toStackValue(assembly, ParseConstants.TKV_THIS, true);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_MADD_BA_SS.toStackValue(assembly, ParseConstants.TKV_THIS, false);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_BCVT_A_S_S.toStackValue(assembly, ParseConstants.TKV_THIS, ParseConstants.TKV_THIS, true);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				final ProgramAssembly assembly = new ProgramAssembly();
				boolean found = false;
				try {
					ParseConstants.TKO_BCVT_A_S_S.toStackValue(assembly, ParseConstants.TKV_THIS, ParseConstants.TKV_THIS, false);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
		}
		{
			/** TK_WRAP_TOKEN_ARGS */
			{
				boolean found = false;
				try {
					new TK_WRAP_TOKEN_ARGS2(null, null, null);
				} catch (final Throwable t) {
					found = true;
				}
				assert found;
			}
			{
				/** should not accept zero-argument instructions and 'no arguments' */
				for (int i = 0; i < 4; i++) {
					final TokenInstruction instruction = new TestEval(i);
					{
						boolean found = false;
						try {
							new TK_WRAP_TOKEN_ARGS2(instruction, null, null);
						} catch (final Throwable t) {
							found = true;
						}
						assert found;
					}
				}
			}
			{
				final TokenInstruction instruction = new TestEval(1);
				{
					boolean found = false;
					try {
						new TK_WRAP_TOKEN_ARGS1(
								instruction, //
								null);
					} catch (final Throwable t) {
						found = true;
					}
					assert found;
				}
				{
					boolean found = false;
					try {
						new TK_WRAP_TOKEN_ARGS1(
								instruction, //
								ModifierArguments.AA0RB);
					} catch (final Throwable t) {
						found = true;
					}
					assert found;
				}
				{
					boolean found = false;
					try {
						new TK_WRAP_TOKEN_ARGS1(
								instruction, //
								ModifierArguments.AE21POP);
					} catch (final Throwable t) {
						found = true;
					}
					assert found;
				}
				{
					final BaseObject instance = new BaseNativeObject();
					ctx.rb4CT = instance;
					assert instruction.getOperandCount() == 1;
					{
						final ProgramAssembly assembly = new ProgramAssembly();
						instruction.toAssembly(assembly, ModifierArguments.AB4CT, null, ResultHandler.FB_BSN_NXT);
						assert assembly.size() > 0;
						final ProgramPart program = assembly.toProgram(0);
						System.out.println("program: " + program);
						assert program.callNE0(ctx, BaseObject.UNDEFINED) == instance;
						program.execCallPrepare(ctx, null, ResultHandler.FU_BNN_NXT, true);
					}
					{
						final TokenInstruction wrapped = new TK_WRAP_TOKEN_ARGS1(instruction, ModifierArguments.AB4CT);
						{
							final ProgramAssembly assembly = new ProgramAssembly();
							wrapped.toAssembly(assembly, null, null, ResultHandler.FB_BSN_NXT);
							assert assembly.size() > 0;
							final ProgramPart program = assembly.toProgram(0);
							System.out.println("program: " + program);
							assert program.callNE0(ctx, BaseObject.UNDEFINED) == instance;
							program.execCallPrepare(ctx, null, ResultHandler.FU_BNN_NXT, true);
						}
					}
				}
			}

			{
				final TokenInstruction instruction = new TestEval(2);
				{
					for (final int i : new int[]{
							0x0, 0x1, 0x2, 0x3
					}) {
						boolean found = false;
						try {
							new TK_WRAP_TOKEN_ARGS2(
									instruction, //
									(i >> 0 & 0x1) != 0
										? ModifierArguments.AA0RB
										: null,
									(i >> 1 & 0x1) != 0
										? ModifierArguments.AA0RB
										: null);
						} catch (final Throwable t) {
							found = true;
						}
						assert found : "pattern: 0x" + Integer.toHexString(i) + " " + Integer.toBinaryString(i) + " binary";
					}
				}
				{
					final BaseObject instance = new BaseNativeObject();
					ctx.rb4CT = instance;
					assert instruction.getOperandCount() == 2;
					{
						final ProgramAssembly assembly = new ProgramAssembly();
						instruction.toAssembly(assembly, ModifierArguments.AB4CT, null, ResultHandler.FB_BSN_NXT);
						assert assembly.size() > 0;
						final ProgramPart program = assembly.toProgram(0);
						System.out.println("program: " + program);
						assert program.callNE0(ctx, BaseObject.UNDEFINED) == instance;
						program.execCallPrepare(ctx, null, ResultHandler.FU_BNN_NXT, true);
					}
					/** both overridden */
					{
						final TokenInstruction wrapped = new TK_WRAP_TOKEN_ARGS2(instruction, ModifierArguments.AB4CT, ModifierArguments.AB4CT);
						assert wrapped.getOperandCount() == 0;
						{
							final ProgramAssembly assembly = new ProgramAssembly();
							wrapped.toAssembly(assembly, null, null, ResultHandler.FB_BSN_NXT);
							assert assembly.size() > 0;
							final ProgramPart program = assembly.toProgram(0);
							System.out.println("program: " + program);
							assert program.callNE0(ctx, BaseObject.UNDEFINED) == instance;
							program.execCallPrepare(ctx, null, ResultHandler.FU_BNN_NXT, true);
						}
					}
					/** two combinations */
					for (final int i : new int[]{
							0x1, 0x2
					}) {
						final TokenInstruction wrapped = new TK_WRAP_TOKEN_ARGS2(
								instruction, //
								(i >> 0 & 1) != 0
									? ModifierArguments.AB4CT
									: null,
								(i >> 1 & 1) != 0
									? ModifierArguments.AB4CT
									: null);
						assert wrapped.getOperandCount() == 1;
						{
							final ProgramAssembly assembly = new ProgramAssembly();
							wrapped.toAssembly(assembly, ModifierArguments.AA0RB, null, ResultHandler.FB_BSN_NXT);
							assert assembly.size() > 0;
							final ProgramPart program = assembly.toProgram(0);
							System.out.println("program: " + program);
							assert program.callNE0(ctx, BaseObject.UNDEFINED) == instance;
							program.execCallPrepare(ctx, null, ResultHandler.FU_BNN_NXT, true);
						}
					}
				}
			}
		}
	}
}
