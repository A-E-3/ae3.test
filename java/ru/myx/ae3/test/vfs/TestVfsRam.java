package ru.myx.ae3.test.vfs;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.Storage;
import ru.myx.ae3.vfs.ars.ArsStorage;
import ru.myx.ae3.vfs.ram.size.StorageImplMemorySize;
import ru.myx.ae3.vfs.ram.speed.StorageImplMemorySpeed;

final class TestVfsRam {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST VFS RAM");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final ArsStorage<?, ?, ?>[] tests = new ArsStorage<?, ?, ?>[]{
				new StorageImplMemorySpeed("test"), new StorageImplMemorySize("test"), //
		};
		for (final ArsStorage<?, ?, ?> storage : tests) {
			
			assert TestVfs.testStorageRootReference(storage);
			final Entry root = Storage.createRoot(storage);
			assert TestVfs.testAssertContainer(root);
			{
				final Entry entry = root.relative("folder", null);
				assert entry == null;
			}
			{
				final Entry entry = root.relativeFolder("folder");
				assert entry != null;
				assert !entry.isExist();
			}
			{
				final BaseList<Entry> entries = root.toContainer().getContentCollection(null).baseValue();
				assert entries == null || entries.length() == 0;
			}
			{
				root.relativeFolderEnsure("folder1");
				root.relativeFolderEnsure("folder2");
				root.relativeFolderEnsure("folder3");
				{
					final Entry entry = root.relativeFolder("folder1");
					assert entry != null;
					assert entry.isExist();
					assert entry.isContainer();
				}
			}
			{
				final BaseList<Entry> entries = root.toContainer().getContentCollection(null).baseValue();
				assert entries != null;
				assert entries.length() == 3;
			}
			{
				root.relativeFolderEnsure("folder");
				final Entry entry = root.relativeFolder("folder");
				assert entry != null;
				assert entry.isExist();
				assert entry.isContainer();
			}
		}
	}
}
