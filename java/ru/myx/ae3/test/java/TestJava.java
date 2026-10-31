package ru.myx.ae3.test.java;

import ru.myx.ae3.Engine;

/**
 * 
 * @author myx
 * 
 */
public class TestJava {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> TEST JAVA" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestJavaCompare.main( new String[0] );
		}
	}
	
}
