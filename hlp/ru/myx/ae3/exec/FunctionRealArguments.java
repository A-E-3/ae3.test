package ru.myx.ae3.exec;

import java.util.function.Function;
import ru.myx.ae3.base.BaseArray;
import ru.myx.ae3.base.BaseFunctionAbstract;
import ru.myx.ae3.base.BaseObject;

/**
 * @author myx
 *
 */
public final class FunctionRealArguments extends BaseFunctionAbstract implements ExecCallableFull, Function<ExecProcess, BaseArray> {
	
	/**
	 * @param ctx
	 * @return
	 */
	public static final ExecArguments[] makeArguments(final ExecProcess ctx) {
		
		final class Descriptor implements NamedToIndexMapper {
			
			@Override
			public int length() {
				
				return 2;
			}

			@Override
			public int nameCount() {
				
				return 2;
			}

			@Override
			public int nameIndex(final int nameIndex) {
				
				return nameIndex;
			}

			@Override
			public int nameIndex(final String key) {
				
				return "field1".equals(key)
					? 0
					: "field2".equals(key)
						? 1
						: -1;
			}

			@Override
			public String[] names() {
				
				return new String[]{
						"field1", "field2"
				};
			}
		}
		final class Factory {
			
			final ExecProcess create(int i) {
				
				final ExecProcess result = Exec.createProcess(ctx, "tst");
				if (--i >= 0) {
					result.stackPush(BaseObject.TRUE);
				}
				if (--i >= 0) {
					result.stackPush(BaseObject.FALSE);
				}
				if (--i >= 0) {
					result.stackPushInteger(2206241);
				}
				if (--i >= 0) {
					result.stackPushInteger(4218410);
				}
				if (--i >= 0) {
					result.stackPushInteger(4263718);
				}
				if (--i >= 0) {
					result.stackPushInteger(5913621);
				}
				return result;
			}
		}
		return new ExecArguments[]{//
				//
				new Factory().create(0)//
						.argumentsConstant(BaseObject.TRUE), //

				new Factory().create(1)//
						.argumentsCopy(1), //

				new Factory().create(2)//
						.argumentsCopy(2), //

				new Factory().create(3)//
						.argumentsCopy(3), //

				new Factory().create(2)//
						.argumentsCopyMap(2, new Descriptor()), //

				new Factory().create(3)//
						.argumentsCopyMap(3, new Descriptor()), //

				new Factory().create(1)//
						.argumentsCtx(1, null), //

				new Factory().create(2)//
						.argumentsCtx(2, null), //

				new Factory().create(3)//
						.argumentsCtx(3, null), //

				new Factory().create(2)//
						.argumentsCtx(2, new Descriptor()), //

				new Factory().create(3)//
						.argumentsCtx(3, new Descriptor()),//

		};
	}

	/**
	 *
	 */
	public FunctionRealArguments() {
		//
	}

	@Override
	public int execArgumentsAcceptable() {
		
		return Integer.MAX_VALUE;
	}

	@Override
	public int execArgumentsDeclared() {
		
		return 0;
	}

	@Override
	public ExecStateCode execCallImpl(final ExecProcess context) throws Exception {
		
		return context.vmSetCallResult(context.rb6CA);
	}

	@Override
	public boolean execHasNamedArguments() {
		
		return true;
	}

	@Override
	public boolean execIsConstant() {
		
		return false;
	}

	@Override
	public Class<? extends Object> execResultClassJava() {
		
		return Object.class;
	}

	@Override
	public BaseArray apply(final ExecProcess ctx) {
		
		return ctx.rb6CA;
	}
}
