package ru.myx.ae3.test.reflect;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class AnnoTestBase {
	
	
	@SuppressWarnings({
			"static-method", "javadoc"
	})
	public boolean methodInstanceBase() {
		
		
		return true;
	}
}
