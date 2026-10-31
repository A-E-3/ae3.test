package ru.myx.ae3.test.act;

import ru.myx.ae3.Engine;
import ru.myx.ae3.act.Act;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;

class TestContextRuntimeIsolation {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {

		Engine.createGuid();
		System.out.println(">>>> >>>> TEST CONTEXT RUNTIME ISOLATION");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ExecProcess process = Exec.currentProcess();
		assert process == Exec.getRootProcess() : "Questionable, but not running in root process... Am I still a test?";
		final ExecProcess process01 = Exec.createProcess(process, "root 01");

		process01.contextExecFDEBUG(BaseObject.TRUE);
		Act.run(process01, arg -> {
			assert Exec.currentProcess() == process01;
			assert process01.contextGetDebug() == BaseObject.TRUE;
			return null;
		}, null);
		assert Exec.currentProcess() == process;
		assert process01.contextGetDebug() == BaseObject.TRUE;
	}
}
