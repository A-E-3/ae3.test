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
public class Test11Class implements Test10Interface {
	
	
	@SuppressWarnings({
			"unused", "javadoc"
	})
	public Test11Class(final String argument) {
		// do nothing
	}

	@Override
	public String method1() {
		
		
		return null;
	}
}
