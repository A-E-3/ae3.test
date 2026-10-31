/**
 *
 */
package ru.myx.ae3.test.reflect;

import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.exec.ExecProcess;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class Test01Class extends Test00Class {
	
	
	public static void staticMethod2() {
		
		
		// do nothing
	}

	@SuppressWarnings("javadoc")
	public static void staticMethod3() {
		
		
		// do nothing
	}

	@SuppressWarnings("javadoc")
	public String field2 = "DDD";

	private Object gsField2 = "BB2";

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public BaseObject getGsField2(final ExecProcess ctx) {
		
		
		return Base.forUnknown(this.gsField2);
	}

	@Override
	public void method2() {
		
		
		// do nothing
	}

	@SuppressWarnings({
			"javadoc", "unused"
	})
	public void method2EE(final ExecProcess ctx) {
		
		
		// do nothing
	}

	@SuppressWarnings("javadoc")
	public void method3() {
		
		
		// do nothing
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public void setGsField2(final ExecProcess ctx, final int s) {
		
		
		this.gsField2 = Base.forInteger(s);
	}

}
