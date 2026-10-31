package ru.myx.ae3.test.reflect;

import ru.myx.ae3.reflect.ReflectionHidden;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
public class AnnoTestDefault extends AnnoTestBase {
	
	
	@SuppressWarnings({
			"static-method", "javadoc"
	})
	public boolean methodInstanceDefault() {
		
		
		return true;
	}

	@SuppressWarnings({
			"static-method", "javadoc"
	})
	@ReflectionHidden
	public boolean methodInstanceDefaultHidden() {
		
		
		return true;
	}

}
