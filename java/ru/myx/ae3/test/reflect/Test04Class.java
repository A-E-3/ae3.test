/**
 *
 */
package ru.myx.ae3.test.reflect;

import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.exec.ExecProcess;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class Test04Class extends Test00Class {
	
	
	private final BaseObject data = new BaseNativeObject();

	private final StringBuilder gsField5Scripting = new StringBuilder("BB5");

	private Object gsField5Java = new StringBuilder("BB5-java");

	@SuppressWarnings("javadoc")
	public BaseObject getData() {
		
		
		return this.data;
	}

	@SuppressWarnings("javadoc")
	public Object getGsField5() {
		
		
		return this.gsField5Java;
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public StringBuilder getGsField5(final ExecProcess ctx) {
		
		
		return this.gsField5Scripting;
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public void method2(final Integer argument) {
		
		
		// do nothing
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public void method2(final String argument) {
		
		
		// do nothing
	}

	@SuppressWarnings("javadoc")
	public void method3() {
		
		
		// do nothing
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public void setGsField5(final ExecProcess ctx, final int s) {
		
		
		System.out.println(">>>>>> " + this.getClass().getName() + " int: " + s);
		this.gsField5Scripting.append(",int-").append(s);
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public void setGsField5(final ExecProcess ctx, final String s) {
		
		
		System.out.println(">>>>>> " + this.getClass().getName() + " str: " + s);
		this.gsField5Scripting.append(",str-").append(s);
	}

	@SuppressWarnings("javadoc")
	public void setGsField5(final int s) {
		
		
		this.gsField5Java = Integer.valueOf(s);
	}

	@SuppressWarnings("javadoc")
	public void setGsField5(final String s) {
		
		
		this.gsField5Java = s;
	}

}
