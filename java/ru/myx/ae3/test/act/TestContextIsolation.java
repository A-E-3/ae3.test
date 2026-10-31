package ru.myx.ae3.test.act;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.exec.ExecProcessVariable;

class TestContextIsolation {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		Engine.createGuid();
		System.out.println( ">>>> >>>> TEST CONTEXT ISOLATION" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ExecProcess process = Exec.getRootProcess();
		final ExecProcessVariable<BaseObject> variable = Exec.createProcessVariable( "inheritable", true );
		final ExecProcessVariable<BaseObject> local = Exec.createProcessVariable( "local", false );
		final ExecProcess process01 = Exec.createProcess( process, "root 01" );
		final ExecProcessVariable<BaseObject> variable01 = Exec.createProcessVariable( "TEST01", true );
		
		assert variable.baseValue( process ) == null : "Not a null, value="
				+ variable.baseValue( process )
				+ ", class="
				+ variable.getClass().getName()
				+ "!";
		
		assert local.baseValue( process ) == null : "Not a null, value="
				+ local.baseValue( process )
				+ ", class="
				+ local.getClass().getName()
				+ "!";
		
		assert variable.baseValue( process01 ) == null : "Not a null, value="
				+ variable.baseValue( process01 )
				+ ", class="
				+ variable.getClass().getName()
				+ "!";
		
		assert local.baseValue( process01 ) == null : "Not a null, value="
				+ local.baseValue( process01 )
				+ ", class="
				+ local.getClass().getName()
				+ "!";
		
		variable.execSet( process01, BaseObject.TRUE );
		
		assert variable.baseValue( process ) == null : "Not a null, value="
				+ variable.baseValue( process )
				+ ", class="
				+ variable.getClass().getName()
				+ "!";
		
		assert variable.baseValue( process01 ) == BaseObject.TRUE : "Not a true, value="
				+ variable.baseValue( process01 )
				+ ", class="
				+ variable.getClass().getName()
				+ "!";
		
		variable01.execSet( process, BasePrimitiveNumber.LN2 );
		
		assert variable01.baseValue( process ) == BasePrimitiveNumber.LN2 : "Not a null, value="
				+ variable01.baseValue( process )
				+ ", class="
				+ variable01.getClass().getName()
				+ "!";
		
		assert variable01.baseValue( process01 ) == BasePrimitiveNumber.LN2 : "Not a true, value="
				+ variable01.baseValue( process01 )
				+ ", class="
				+ variable01.getClass().getName()
				+ "!";
		
		local.execSet( process, BasePrimitiveNumber.PI );
		
		assert local.baseValue( process ) == BasePrimitiveNumber.PI : "Not a null, value="
				+ local.baseValue( process )
				+ ", class="
				+ local.getClass().getName()
				+ "!";
		
		assert local.baseValue( process01 ) == null : "Not a null, value="
				+ local.baseValue( process01 )
				+ ", class="
				+ local.getClass().getName()
				+ "!";
		
		variable.execSet( process, BaseString.STR_ONE );
		
		assert variable.baseValue( process ) == BaseString.STR_ONE : "Not a null, value="
				+ variable.baseValue( process )
				+ ", class="
				+ variable.getClass().getName()
				+ "!";
		
		assert variable.baseValue( process01 ) == BaseObject.TRUE : "Not a true, value="
				+ variable.baseValue( process01 )
				+ ", class="
				+ variable.getClass().getName()
				+ "!";
	}
}
