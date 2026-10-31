package ru.myx.ae3.test.act;

import ru.myx.ae3.Engine;
import ru.myx.ae3.act.Act;
import java.util.function.Function;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseProperty;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;

class TestContextInheritance {
	
	static class Job implements Function<ExecProcess, Boolean> {
		
		@Override
		public Boolean apply(final ExecProcess arg) {
			
			assert arg == Exec.currentProcess();
			assert "test2".equals(Base.getString(arg, "___test", "")) : "Should be equal";
			assert "test2".equals(Base.getString(Exec.currentProcess(), "___test", "")) : "Should be equal";
			assert Exec.getRootProcess() == Exec.currentProcess().getParentProcess() : "Parent should be root";
			return Boolean.TRUE;
		}
	}

	/**
	 *
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST CONTEXT INHERITANCE");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ExecProcess root = Exec.getRootProcess();
		final ExecProcess process = Exec.createProcess(root, "inherited");
		assert process != null : "NULL process created";
		assert process != root : "root process created";
		assert !Base.hasProperty(root, "___test") : "Should not have";
		assert !Base.hasProperty(process, "___test") : "Should not have";
		root.baseDefine("___test", "test", BaseProperty.ATTRS_MASK_NND);
		assert Base.hasProperty(root, "___test") : "Should have";
		assert Base.hasProperty(process, "___test") : "Should have";
		assert "test".equals(Base.getString(process, "___test", "")) : "Should be equal";
		process.baseDefine("___test", "test2", BaseProperty.ATTRS_MASK_NND);
		assert "test2".equals(Base.getString(process, "___test", "")) : "Should be equal";
		final Boolean result = Act.run(process, new Job(), process);
		assert result == Boolean.TRUE : "TRUE expected";
		root.baseDelete("___test");
		assert !Base.hasProperty(root, "___test") : "Should not have";
	}
}
