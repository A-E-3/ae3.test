package ru.myx.ae3.test.flow;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseMessage;
import ru.myx.ae3.flow.Flow;
import ru.myx.ae3.flow.ObjectSource;
import ru.myx.ae3.flow.ObjectTarget;

/**
 * 
 * @author myx
 * 
 */
public class TestFlow {
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		Engine.createGuid();
		System.out.println( ">>>> TEST FLOW" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			assert Flow.NUL_MESSAGE.isEmpty();
			
			assert !Flow.NUL_MESSAGE.isBinary();
			assert !Flow.NUL_MESSAGE.isFile();
			assert !Flow.NUL_MESSAGE.isObject();
			assert !Flow.NUL_MESSAGE.isSequence();
			assert !Flow.NUL_MESSAGE.isCharacter();
			
			assert ((BaseMessage) Flow.NUL_MESSAGE).toBinary() == Flow.NUL_MESSAGE;
			assert ((BaseMessage) Flow.NUL_MESSAGE).toCharacter() == Flow.NUL_MESSAGE;
		}
		{
			assert ObjectTarget.NUL_TARGET.accepts() == Object.class;
			assert ObjectTarget.NUL_TARGET.absorb( new Object() );
		}
		{
			assert ObjectSource.NUL_SOURCE.isExhausted();
			assert !ObjectSource.NUL_SOURCE.isReady();
			assert ObjectSource.NUL_SOURCE.next() == null;
		}
		
		for (final BaseMessage attribute : new BaseMessage[] {
				Flow.mimeAttribute( "A", "A", "text/plain" ),
				Flow.mimeAttribute( "B", "B", "text/plain; charset=koi8-r" ),
				Flow.mimeAttribute( "C", "C", "text/plain; charset=koi8-r, aaa=bbb" ), }) {
			System.out.println( ">>> >>> FLOW >>> testing: " + attribute );
			assert attribute != null : "NULL attribute";
			assert attribute.getAttributes() != null || "A".equals( attribute.getTitle() ) : "NULL java value, class="
					+ attribute.getClass().getName()
					+ ", attribute: "
					+ attribute;
		}
	}
}
