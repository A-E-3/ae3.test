/**
 *
 */
package ru.myx.ae3.test.reflect;

import ru.myx.ae3.exec.ExecProcess;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class Test02Class extends Test00Class {
	
	
	@SuppressWarnings("javadoc")
	public final StringBuilder gsField3 = new StringBuilder("BB3");

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
	public void setGsField3(final ExecProcess ctx, final int s) {
		
		
		System.out.println(">>>>>> " + this.getClass().getName() + " int: " + s + ", result=" + this.gsField3);
		this.gsField3.append(",int=").append(s);
	}

	@SuppressWarnings({
			"unused", "javadoc"
	})
	public void setGsField3(final ExecProcess ctx, final String s) {
		
		
		this.gsField3.append(",str=").append(s);
		System.out.println(">>>>>> " + this.getClass().getName() + " str: " + s + ", result=" + this.gsField3);
	}

}
