package ru.myx.ae3.test.vfs;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.Storage;
import ru.myx.ae3.vfs.ars.ArsStorage;
import ru.myx.ae3.vfs.ram.StorageImplMemory;
import ru.myx.ae3.vfs.union.StorageImplUnion;

final class TestVfsUnion {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST VFS UNION");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final ArsStorage<?, ?, ?> ramfs1 = StorageImplMemory.create("test1");
			assert TestVfs.testStorageRootReference(ramfs1);
			final Entry ramfsRoot1 = Storage.createRoot(ramfs1);
			assert TestVfs.testAssertContainer(ramfsRoot1);
			
			final ArsStorage<?, ?, ?> ramfs2 = StorageImplMemory.create("test2");
			assert TestVfs.testStorageRootReference(ramfs2);
			final Entry ramfsRoot2 = Storage.createRoot(ramfs2);
			assert TestVfs.testAssertContainer(ramfsRoot2);
			
			final ArsStorage<?, ?, ?> ramfs3 = StorageImplMemory.create("test3");
			assert TestVfs.testStorageRootReference(ramfs3);
			final Entry ramfsRoot3 = Storage.createRoot(ramfs3);
			assert TestVfs.testAssertContainer(ramfsRoot3);
			
			final ArsStorage<?, ?, ?> storage = new StorageImplUnion(new Entry[]{
					ramfsRoot1, ramfsRoot2, ramfsRoot3, //
			});
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
				ramfsRoot1.relativeFolderEnsure("folder1");
				ramfsRoot2.relativeFolderEnsure("folder2");
				ramfsRoot3.relativeFolderEnsure("folder3");
				{
					final Entry entry = ramfsRoot1.relativeFolder("folder1");
					assert entry != null;
					assert entry.isExist();
					assert entry.isContainer();
				}
			}
			{
				final BaseList<Entry> entries = root.toContainer().getContentCollection(null).baseValue();
				assert entries != null : "";
				assert entries.length() == 3;
			}
			if (!storage.isReadOnly()) {
				root.relativeFolderEnsure("folder");
				final Entry entry = root.relativeFolder("folder");
				assert entry != null;
				assert entry.isExist();
			}
		}
	}
}
