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
public class TestMapEntryExtendsEntry extends TestMapEntryEntry {
	
	
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
