/**
 *
 */
package ru.myx.ae3.test.reflect;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class Test03Class extends Test00Class {
	
	
	private final Map<String, Object> data = new TreeMap<>();

	private Object gsField4 = "BB4";

	@SuppressWarnings("javadoc")
	public Map<String, Object> getData() {
		
		
		return this.data;
	}

	@SuppressWarnings("javadoc")
	public Object getGsField4() {
		
		
		return this.gsField4;
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

	@SuppressWarnings("javadoc")
	public void setGsField4(final int s) {
		
		
		this.gsField4 = Integer.valueOf(s);
	}

	@SuppressWarnings("javadoc")
	public void setGsField4(final String s) {
		
		
		this.gsField4 = s;
	}

}
