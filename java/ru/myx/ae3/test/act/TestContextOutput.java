package ru.myx.ae3.test.act;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.base.BaseWrapEmptyAbstract;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecOutputBuilder;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.extra.ExtraTextBase;

class TestContextOutput {
	
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST OUTPUT");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		for (final BaseObject test : new BaseObject[]{
				Base.forString("2206241"), Base.forDouble(2206241.0), Base.forInteger(2206241), new BaseWrapEmptyAbstract<>() {
					
					@Override
					public BaseObject basePrototype() {
						
						return BaseString.PROTOTYPE;
					}
					
					@Override
					public String toString() {
						
						return "2206241";
					}
					
					@Override
					public Object baseValue() {
						
						return "2206241";
					}
				}, new ExtraTextBase("TEST", "GUID", 0L, Transfer.createCopier("2206241".getBytes())), Base.forUnknown(new StringBuilder("2206241")), Base.forUnknown(new Object() {
					
					@Override
					public String toString() {
						
						return "2206241";
					}
				}),
				//
		}) {
			final ExecProcess ctx = Exec.createProcess(null, "output test");
			final StringBuilder builder = new StringBuilder();
			ctx.execOutputReplace(new ExecOutputBuilder(builder));
			ctx.execOutput(test);
			ctx.execOutputReplace(null);
			assert "2206241".equals(builder.toString()) : "'2206241' != '" + builder.toString() + "'";
		}
	}
}
