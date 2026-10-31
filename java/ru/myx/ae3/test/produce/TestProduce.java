package ru.myx.ae3.test.produce;

import java.nio.charset.StandardCharsets;

import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.produce.Produce;
import ru.myx.ae3.test.TestAe3;

/**
 * 
 * @author myx
 * 
 */
public class TestProduce {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST PRODUCE" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final String check = Produce.object( String.class,
					"text/plain",
					null,
					Transfer.createBuffer( "test-5913621".getBytes( StandardCharsets.UTF_8 ) ) );
			assert check != null : "NULL";
			assert "test-5913621".equals( check ) : "Not equals: " + check;
		}
		for (final Class<?> cls : new Class[] { String.class, Object.class, CharSequence.class }) {
			final Object check = Produce.object( cls,
					"text/plain",
					null,
					Transfer.createBuffer( "test-5913621".getBytes( StandardCharsets.UTF_8 ) ) );
			assert check != null : "NULL";
			assert "test-5913621".equals( check ) : "Not equals: " + check;
		}
	}
}
