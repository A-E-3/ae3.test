package ru.myx.ae3.test.reflect;

import ru.myx.ae3.reflect.ReflectionDisable;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
@ReflectionDisable
public class AnnoTestDisabled extends AnnoTestBase {
	
	
	@SuppressWarnings({
			"static-method", "javadoc"
	})
	public boolean methodInstanceDisabled() {
		
		
		return true;
	}
}
