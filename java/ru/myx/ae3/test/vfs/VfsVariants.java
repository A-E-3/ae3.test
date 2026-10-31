package ru.myx.ae3.test.vfs;

import java.io.File;

import ru.myx.ae3.Engine;
import ru.myx.ae3.vfs.ars.ArsStorage;
import ru.myx.ae3.vfs.filesystem.StorageImplFilesystem;
import ru.myx.ae3.vfs.ram.size.StorageImplMemorySize;
import ru.myx.ae3.vfs.ram.speed.StorageImplMemorySpeed;
import ru.myx.ae3.vfs.s4.StorageImplS4;
import ru.myx.ae3.vfs.s4.common.S4StoreType;
import ru.myx.ae3.vfs.s4.lcl.S4LocalDriver;

enum VfsVariants {
	RAMFS_SIZE {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() {
			return new StorageImplMemorySize( "vfs-test" );
		}
		
	},
	RAMFS_SPEED {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() {
			return new StorageImplMemorySpeed( "vfs-test" );
		}
		
	},
	TEMP_OSFS {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() {
			final File file = new File( Engine.PATH_TEMP, Engine.createGuid() + ".vfs_rw_test" );
			file.mkdir();
			return new StorageImplFilesystem( file, false );
		}
		
	},
	TEMP_S4_BDB {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.bdbj.BdbjLocalS4", S4StoreType.TEST ) );
		}
		
	},
	TEMP_S4_BDB_COMPARE {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.bdbj_compare.BdbjCompare",
					S4StoreType.TEST ) );
		}
		
	},
	TEMP_S4_DB4O {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.db4o.Db4oLocalS4", S4StoreType.TEST ) );
		}
		
	},
	TEMP_S4_DRBY {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.drby.DrbyLocalS4", S4StoreType.TEST ) );
		}
		
		@Override
		boolean isSlow() {
			return true;
		}
	},
	TEMP_S4_H2DB {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.h2db.H2dbLocalS4", S4StoreType.TEST ) );
		}
		
	},
	TEMP_S4_HSQL {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.hsql.HsqlLocalS4", S4StoreType.TEST ) );
		}
		
	},
	TEMP_S4_BDB_CLASSIC {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.bdbj_classic.BdbjClassic",
					S4StoreType.TEST ) );
		}
		
	},
	TEMP_S4_SQLT {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.sqlt.SqltLocalS4", S4StoreType.TEST ) );
		}
		
		@Override
		boolean isSlow() {
			return true;
		}
		
	},
	TEMP_S4_SSQL {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			// doesn't support ON CASCADE DELETE
			// cannot do indexes - so can't read data
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.ssql.SsqlLocalS4", S4StoreType.TEST ) );
		}
		
		@Override
		boolean isWorking() {
			return false;
		}
		
	},
	TEMP_S4_QEDB {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			// error in setBytes (stores extra bytes), error in setBinaryStream
			// (null pointer exception), cannot open existing (conversion error
			// on SELECT MAX(luid) FROM xxITEMS)
			// database %)
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.qedb.QedbLocalS4", S4StoreType.TEST ) );
		}
		
		@Override
		boolean isWorking() {
			return false;
		}
		
	},
	TEMP_S4_JODB {
		
		@Override
		ArsStorage<?, ?, ?> createInstance() throws Exception {
			// hangs
			return new StorageImplS4( new S4LocalDriver( "ru.myx.ae3.vfs.s4.lcl.jodb.JodbLocalS4", S4StoreType.TEST ) );
		}
		
		@Override
		boolean isWorking() {
			return false;
		}
		
	},
	;
	abstract ArsStorage<?, ?, ?> createInstance() throws Exception;
	
	@SuppressWarnings("static-method")
	boolean isSlow() {
		return false;
	}
	
	@SuppressWarnings("static-method")
	boolean isWorking() {
		return true;
	}
}
