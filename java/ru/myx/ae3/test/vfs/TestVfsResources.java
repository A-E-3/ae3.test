package ru.myx.ae3.test.vfs;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.EntryBinary;
import ru.myx.ae3.vfs.Storage;
import ru.myx.ae3.vfs.ars.ArsStorage;
import ru.myx.ae3.vfs.resources.StorageImplResources;

final class TestVfsResources {
	
	private static void dump(final Entry entry) {
		
		System.out.println("entry: " + entry + ": " + entry.isBinary());
		assert !entry.canWrite() : "Rousources expected to be read-only!";
		if (entry.isBinary()) {
			final EntryBinary binary = entry.toBinary();
			assert binary.getBinaryContent().baseValue().nextCopy().remaining() == binary.getBinaryContentLength();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST VFS RESOURCES");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final ArsStorage<?, ?, ?> storage = new StorageImplResources(TestVfsResources.class);
			assert TestVfs.testStorageRootReference(storage);
			final Entry root = Storage.createRoot(storage);
			assert TestVfs.testAssertContainer(root);
			assert !root.canWrite() : "Resources expected to be read-only!";
			System.out.println("root location: " + root.getLocation());
			final Entry check1 = root.toContainer().getContentElement("undefined.aa", null).baseValue();
			assert !check1.isBinary();
			TestVfsResources.dump(check1);
			final Entry check2 = root.toContainer().getContentElement("TestVfsResources.class", null).baseValue();
			assert TestVfs.testAssertBinary(check2);
			TestVfsResources.dump(check2);
			final Entry skin = root.relative("vfs-test-resources", null);
			assert TestVfs.testAssertContainerNotEmpty(skin);
			final BaseList<Entry> array = skin.toContainer().getContentCollection(null).baseValue();
			System.out.println(">> folder: " + skin);
			for (final Entry entry : array) {
				System.out.println(">> child: " + entry);
			}
		}
	}
}
