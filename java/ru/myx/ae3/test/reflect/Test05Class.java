/**
 *
 */
package ru.myx.ae3.test.reflect;

import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeObject;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class Test05Class extends Test00Class {
	
	
	private final BaseMap data = new BaseNativeObject();
	
	@SuppressWarnings("javadoc")
	public BaseMap getData() {
		
		
		return this.data;
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
}
