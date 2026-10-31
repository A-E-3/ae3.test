package ru.myx.ae3.test.vfs;

import ru.myx.ae3.Engine;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.Storage;
import ru.myx.ae3.vfs.TreeLinkType;
import ru.myx.ae3.vfs.ram.StorageImplMemory;

final class TestVfsMount {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST VFS MOUNT");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final Entry root = Storage.createRoot(StorageImplMemory.create("VfsTestRoot"));
			assert TestVfs.testAssertContainer(root);
			assert root.canWrite() : "Memory storage expected to be writable!";
			
			final Entry bind = Storage.createRoot(StorageImplMemory.create("VfsTestBind"));
			assert TestVfs.testAssertContainer(bind);
			assert bind.canWrite() : "Memory storage expected to be writable!";
			
			final Entry another = Storage.createRoot(StorageImplMemory.create("VfsTestAnother"));
			assert TestVfs.testAssertContainer(another);
			assert another.canWrite() : "Memory storage expected to be writable!";
			
			assert another.toContainer().getContentElement("check", null).baseValue() == null;
			
			another.toContainer().setContentBinary("check", TreeLinkType.PUBLIC_TREE_REFERENCE, TransferCopier.NUL_COPIER);
			
			assert another.toContainer().getContentElement("check", null).baseValue() != null;
			
			final Entry container = bind.toContainer().getContentElement("container", TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
			
			assert container.relative("mount1/check", null) == null;
			assert bind.relative("container/mount1/check", null) == null;
			Storage.mount(container, "mount1", TreeLinkType.PUBLIC_TREE_REFERENCE, another);
			assert container.relative("mount1/check", null) != null;
			assert bind.relative("container/mount1/check", null) != null;
			
			final Entry target = root.toContainer().getContentElement("target", TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
			target.doSetContainer().baseValue();
			
			assert root.relative("target/bind/container/mount1/check", null) == null;
			Storage.mount(target, "bind", TreeLinkType.PUBLIC_TREE_REFERENCE, bind);
			assert root.relative("target/bind/container/mount1/check", null) != null;
		}
	}
}
