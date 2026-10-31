package ru.myx.ae3.test.reflect;

import ru.myx.ae3.reflect.ReflectionExplicit;
import ru.myx.ae3.reflect.ReflectionManual;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 *
 * @author myx
 *
 */
@ReflectionManual
public class AnnoTestManual extends AnnoTestBase {
	
	
	@SuppressWarnings({
			"static-method", "javadoc"
	})
	public boolean methodInstanceDefault() {
		
		
		return true;
	}

	@SuppressWarnings({
			"static-method", "javadoc"
	})
	@ReflectionExplicit
	public boolean methodInstanceExplicit() {
		
		
		return true;
	}

}
