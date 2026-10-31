package ru.myx.ae3.test.vfs;

import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.ars.ArsStorage;

/**
 * 
 * @author myx
 * 
 */
public final class TestVfs {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		TestAe3.initAE3();
		System.out.println( ">>>> TEST VFS" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			TestVfsRam.main( new String[0] );
			TestVfsUnion.main( new String[0] );
			TestVfsSignals.main( new String[0] );
			TestVfsResources.main( new String[0] );
			TestVfsMount.main( new String[0] );
		}
		{
			TestVfsReadWrite.main( new String[0] );
		}
	}
	
	static boolean testAssertBinary(final Entry entry) {
		assert entry != null : "Must not be null";
		assert entry.isExist() : "Should exist, entry: " + entry;
		assert entry.isBinary() : "Should be binary, entry: " + entry;
		assert !entry.isContainer() : "Should not be a container, entry: " + entry;
		assert entry.toBinary().getBinaryContent() != null : "NULL binary content reference, entry: " + entry;
		assert entry.toBinary().getBinaryContent().baseValue() != null : "NULL binary content, entry: " + entry;
		return true;
	}
	
	static boolean testAssertContainer(final Entry entry) {
		assert entry != null : "Must not be null";
		assert entry.isExist() : "Should exist!, entry: " + entry;
		assert !entry.isBinary() : "Should not be binary, entry: " + entry;
		assert entry.isContainer() : "Should be container, entry: " + entry;
		assert entry.toContainer() != null : "toContainer is broken, entry: " + entry;
		assert entry.toContainer().getContentCollection( null ) != null : "NULL reference is invalid, entry: " + entry;
		return true;
	}
	
	static boolean testAssertContainerNotEmpty(final Entry entry) {
		assert TestVfs.testAssertContainer( entry );
		assert entry.toContainer().getContentCollection( null ).baseValue() != null : "NULL listing is invalid, entry: "
				+ entry;
		assert entry.toContainer().getContentCollection( null ).baseValue().length() != 0 : "Empty listing is invalid, entry: "
				+ entry;
		return true;
	}
	
	static boolean testStorageRootReference(final ArsStorage<?, ?, ?> storage) {
		assert storage.getRootReference() != null : "NULL";
		assert storage.getRootReference().getKeyString() != null : "NULL key";
		assert storage.getRootReference().getTarget() != null : "NULL";
		assert storage.getRootReference().isExist() == true : "Should exist";
		assert storage.getRootReference().getTarget().isBinary() == false : "Should not be binary";
		assert storage.getRootReference().getTarget().isContainer() == true : "Should be container";
		return true;
	}
}
