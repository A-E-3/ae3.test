/**
 *
 */
package ru.myx.ae3.test.reflect;

import java.util.Map;

/**
 *
 * Must be public to check reflection (reflects public classes only)
 * 
 * @author myx
 *
 */
public class TestMapEntryEntry implements Map.Entry<String, Object> {
	
	@Override
	public String getKey() {
		
		return "false";
	}

	@Override
	public Object getValue() {
		
		return "true";
	}

	@Override
	public Object setValue(final Object value) {
		
		return "true";
	}
	//
}
