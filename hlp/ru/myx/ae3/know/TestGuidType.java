package ru.myx.ae3.know;

import ru.myx.ae3.test.TestAe3;

/**
 * 
 * @author myx
 * 
 */
public class TestGuidType {
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		
		/**
		 * Sorry - nothing to do here 8-)
		 */
		for (final GuidType guidType : GuidType.ALL) {
			if (guidType.isInline() && !guidType.isPrimitive() && !guidType.isBinary()) {
				throw new Exception( "Only binary guids are supposed to be inline but not primitive. Type: " + guidType );
			}
		}
		
	}
}
