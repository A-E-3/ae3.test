/**
 * 
 */
package ru.myx.ae3.test.s4;

import ru.myx.ae3.report.Report;
import ru.myx.ae3.vfs.s4.common.S4StoreType;
import ru.myx.ae3.vfs.s4.lcl.S4DriverLocalAbstract;
import ru.myx.ae3.vfs.s4.lcl.S4LocalDriver;

enum S4Variants {
	/**
	 * 
	 */
	S4IMPL_BDBJ_LOCAL_TEST {
		@Override
		S4DriverLocalAbstract makeRoot() throws Exception {
			return new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.bdbj.BdbjLocalS4", S4Variants.TYPE );
		}
	},
	/**
	 * 
	 */
	S4IMPL_BDBJ_CLASSIC_TEST {
		@Override
		S4DriverLocalAbstract makeRoot() throws Exception {
			return new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.bdbj_classic.BdbjClassic", S4Variants.TYPE );
		}
	},
	/**
	 * 
	 */
	S4IMPL_H2DB_LOCAL_TEST {
		@Override
		S4DriverLocalAbstract makeRoot() throws Exception {
			return new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.h2db.H2dbLocalS4", S4Variants.TYPE );
		}
	},
	/**
	 * 
	 */
	S4IMPL_DRBY_LOCAL_TEST {
		@Override
		S4DriverLocalAbstract makeRoot() throws Exception {
			return new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.drby.DrbyLocalS4", S4Variants.TYPE );
		}
	},
	/**
	 * 
	 */
	S4IMPL_DB4O_LOCAL_TEST {
		@Override
		S4DriverLocalAbstract makeRoot() throws Exception {
			return new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.db4o.Db4oLocalS4", S4Variants.TYPE );
		}
	},
	/**
	 * 
	 */
	S4IMPL_HSQL_LOCAL_TEST {
		@Override
		S4DriverLocalAbstract makeRoot() throws Exception {
			return new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.hsql.HsqlLocalS4", S4Variants.TYPE );
		}
	},
	/**
	 * 
	 */
	;
	static final S4StoreType	TYPE	= Report.MODE_ASSERT
												? S4StoreType.TEST_OPEN
												: S4StoreType.TEST;
	
	abstract S4DriverLocalAbstract makeRoot() throws Exception;
}
