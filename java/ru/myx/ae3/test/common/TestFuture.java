package ru.myx.ae3.test.common;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.common.FutureSimpleUnknown;
import ru.myx.ae3.concurrent.CommonHelper;

public class TestFuture {
	
	public static void main(final String... args) {
		
		Engine.createGuid();
		Engine.class.getSimpleName();
		// Exec.class.getSimpleName();
		
		Base.class.getSimpleName();
		
		final FutureSimpleUnknown<BaseObject> future = new FutureSimpleUnknown<>();
		
		System.out.println(">>>> 0, pending: " + CommonHelper.isTaskPending(future));
		System.out.println(">>>> 0, failure: " + CommonHelper.isTaskFailed(future));
		
		System.out.println(">>>> 1 ");
		
		future.setResult(BaseObject.TRUE);
		
		System.out.println(">>>> 1, pending: " + CommonHelper.isTaskPending(future));
		System.out.println(">>>> 1, failure: " + CommonHelper.isTaskFailed(future));
		
		System.out.println(">>>> 2 ");
		
		System.out.println(">>>> 2, .setErr: " + Base.get(future, "setError", null));
		System.out.println(">>>> 2, .isDone: " + Base.get(future, "isDone", null));
		System.out.println(">>>> 2, .isDone: " + Base.get(future.basePrototype(), "isDone", null));
		
		System.out.println(">>>> 3 ");
	}
}
