/**
 *
 */
package ru.myx.ae3.test.reflect;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class Test00Class {
	
	
	@SuppressWarnings("javadoc")
	public static String staticField1 = "AAA";
	
	@SuppressWarnings("javadoc")
	public static void staticMethod1() {
		
		
		// do nothing
	}
	
	@SuppressWarnings("javadoc")
	public static void staticMethod2() {
		
		
		// do nothing
	}
	
	@SuppressWarnings("javadoc")
	public String field1 = "BBB";
	
	@SuppressWarnings("javadoc")
	public String field2 = "CCC";
	
	private String gsField1 = "BB1";
	
	@SuppressWarnings("javadoc")
	public String getGsField1() {
		
		
		return this.gsField1;
	}
	
	@SuppressWarnings("javadoc")
	public void method1() {
		
		
		// do nothing
	}
	
	@SuppressWarnings("javadoc")
	public void method2() {
		
		
		// do nothing
	}
	
	@SuppressWarnings("javadoc")
	public void method2EE() {
		
		
		// do nothing
	}
	
	@SuppressWarnings("javadoc")
	public void setGsField1(final String s) {
		
		
		this.gsField1 = s;
	}
}
