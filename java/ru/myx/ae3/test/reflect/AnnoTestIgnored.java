package ru.myx.ae3.test.reflect;

import ru.myx.ae3.reflect.ReflectionIgnore;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
@ReflectionIgnore
public class AnnoTestIgnored extends AnnoTestBase {
	
	
	@SuppressWarnings({
			"static-method", "javadoc"
	})
	public boolean methodInstanceIgnored() {
		
		
		return true;
	}
}
