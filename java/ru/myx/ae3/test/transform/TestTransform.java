package ru.myx.ae3.test.transform;

import java.nio.charset.StandardCharsets;

import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferBuffer;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.transform.Transform;

/**
 * 
 * @author myx
 * 
 */
public class TestTransform {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST TRANSFORM" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final String check = Transform.materialize( String.class,
					"text/plain",
					null,
					Transfer.createBuffer( "test-5913621".getBytes( StandardCharsets.UTF_8 ) ) );
			assert check != null : "NULL";
			assert "test-5913621".equals( check ) : "Not equals: " + check;
		}
		for (final Class<?> cls : new Class[] { String.class, CharSequence.class }) {
			for (final String type : new String[] { "text/plain", "text/javascript" }) {
				final Object check = Transform.materialize( cls,
						type,
						null,
						Transfer.createBuffer( "test-5913621".getBytes( StandardCharsets.UTF_8 ) ) );
				assert check != null : "NULL: cls=" + cls.getName() + ", type=" + type;
				assert "test-5913621".equals( check ) : "Not equals: cls="
						+ cls.getName()
						+ ", type="
						+ type
						+ ", check="
						+ check;
			}
		}
		for (final Class<?> cls : new Class[] { String.class, TransferBuffer.class, TransferCopier.class }) {
			final Object check = Transform.materialize( cls,
					"text/fuck",
					null,
					Transfer.createBuffer( "test-5913621".getBytes( StandardCharsets.UTF_8 ) ) );
			assert check != null : "NULL - default materializer is not working?, class=" + cls.getName();
			assert "test-5913621".equals( check.toString() ) : "Not equals: " + check;
		}
	}
}
