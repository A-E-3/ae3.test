package ru.myx.ae3.test.exec;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseFunction;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecArguments;
import ru.myx.ae3.exec.ExecArgumentsList1;
import ru.myx.ae3.exec.ExecArgumentsList2;
import ru.myx.ae3.exec.ExecArgumentsListXWrapped;
import ru.myx.ae3.exec.ExecArgumentsMapX;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.FunctionRealArguments;
import ru.myx.ae3.help.Create;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.test.TestAe3;

/**
 *
 */

/** @author myx */
class TestExecArguments {
	
	/** @param args
	 * @throws Throwable */
	public final static void main(final String... args) throws Throwable {
		
		TestAe3.initAE3();
		System.out.println(">>>> >>>> TEST ae3.exec arguments");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final BaseFunction function = new FunctionRealArguments();
			for (final ExecArguments[] list : new ExecArguments[][]{ //
					new ExecArguments[]{ //
							new ExecArgumentsList2(BaseObject.TRUE, BaseObject.FALSE), //
							new ExecArgumentsList1(BaseObject.TRUE), //
							
							ExecArgumentsListXWrapped.createArguments(new BaseNativeArray(BaseObject.TRUE)), //
							ExecArgumentsListXWrapped.createArguments(new BaseNativeArray(BaseObject.TRUE, BaseObject.FALSE)), //
							ExecArgumentsListXWrapped.createArguments(new BaseNativeArray(BaseObject.TRUE, BaseObject.FALSE, BaseObject.NULL)), //
							ExecArgumentsListXWrapped.createArguments(new BaseNativeArray(BaseObject.TRUE, BaseObject.FALSE, BaseObject.NULL, BaseObject.PROTOTYPE)), //
							
							ExecArgumentsMapX.createArguments(new BaseNativeObject("G", BaseObject.TRUE)), //
							ExecArgumentsMapX.createArguments(new BaseNativeObject("zzzz", BaseObject.TRUE)), //
							ExecArgumentsMapX.createArguments(new BaseNativeObject("H", BaseObject.TRUE).putAppend("field", BaseObject.FALSE)), //
							ExecArgumentsMapX.createArguments(new BaseNativeObject("zzzz", BaseObject.TRUE).putAppend("field", BaseObject.FALSE)), //
							
							/** numeric */
							ExecArgumentsMapX.createArguments(new BaseNativeObject("0", BaseObject.TRUE)), //
							
							ExecArgumentsMapX.createArguments(//
									new BaseNativeObject(BaseString.STR_LENGTH, BaseObject.TRUE)//
											.putAppend(BaseString.STR_ZERO, BaseObject.FALSE)),
							
							ExecArgumentsMapX.createArguments(new BaseNativeArray(
									/** as a property, not a part of array */
									"length", BasePrimitiveNumber.E)//
											.putAppend(BaseObject.TRUE)),
							
							ExecArgumentsMapX.createArguments(new BaseNativeArray(
									/** as a property, not a part of array */
									"field", BasePrimitiveNumber.E)//
											.putAppend(BaseObject.TRUE)//
											.putAppend(BaseObject.FALSE)),
							
							ExecArgumentsMapX.createArguments(//
									new BaseNativeArray(
											/** as a property, not a part of array */
											Base.forString("length"), BasePrimitiveNumber.E)//
													.putAppend(BaseObject.TRUE)),
							
							ExecArgumentsMapX.createArguments(//
									new BaseNativeArray(
											/** as a property, not a part of array */
											Base.forString("field"), BasePrimitiveNumber.E)//
													.putAppend(BaseObject.TRUE)//
													.putAppend(BaseObject.FALSE)),
					//
					},
					//
					
					// FunctionRealArguments.makeArguments(Exec.getRootProcess())
					// //
			}) {
				for (final ExecArguments initial : list) { //
					System.out.println(">>>> >>>> >>>> arguments intital: " + initial + ", class: " + initial.getClass().getSimpleName());
					final ExecProcess ctx = Exec.createProcess(null, "arguments test");
					ctx.rb6CA = initial;
					
					assert ctx.baseGet(0, BaseObject.FALSE) == BaseObject.TRUE;
					assert ctx.baseGet(1, BaseObject.FALSE) == BaseObject.FALSE;
					assert ctx.contextGetArguments().baseGet(0, BaseObject.FALSE) == BaseObject.TRUE;
					assert ctx.contextGetArguments().baseGet(1, BaseObject.FALSE) == BaseObject.FALSE;
					{
						final ExecArguments arguments = (ExecArguments) function.callNEX(ctx, function, initial);
						assert arguments.baseGet(0, BaseObject.FALSE) == BaseObject.TRUE;
						assert arguments.baseGet(1, BaseObject.FALSE) == BaseObject.FALSE;
					}
					assert ctx.baseGet(0, BaseObject.FALSE) == BaseObject.TRUE;
					assert ctx.baseGet(1, BaseObject.FALSE) == BaseObject.FALSE;
					assert ctx.contextGetArguments().baseGet(0, BaseObject.FALSE) == BaseObject.TRUE;
					assert ctx.contextGetArguments().baseGet(1, BaseObject.FALSE) == BaseObject.FALSE;
					
					if (initial.baseHasKeysOwn()) {
						/** Map with 0 properties as argument - is nonsence! */
						assert initial.baseKeysOwnAll().hasNext() //
						: "ownAll - empty? " + Format.Describe.toEcmaSource(initial, "    ");
						
						assert initial.baseKeysOwn().hasNext() //
						: "ownKeys - empty? " + Format.Describe.toEcmaSource(initial, "    ");
						
						assert initial.baseKeysOwnPrimitive().hasNext() //
						: "ownPrimitive - empty? " + Format.Describe.toEcmaSource(initial, "    ");
						
						/**
						*
						*/
						System.out.println(">>>> >>>> >>>> >>>> keys: " //
								+ Create.tempSet(initial.baseKeysOwn()));
						System.out.println(">>>> >>>> >>>> >>>> keys: " //
								+ Create.tempSet(initial.baseKeysOwnPrimitive()));
						System.out.println(">>>> >>>> >>>> >>>> keysAll: " //
								+ Create.tempSet(initial.baseKeysOwnAll()));
						
					}
				}
			}
		}
	}
}
