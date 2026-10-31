package ru.myx.ae3.test.vfs;

import java.util.Map;

import ru.myx.ae3.Engine;
import java.util.function.Function;
import ru.myx.ae3.help.Create;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.Storage;
import ru.myx.ae3.vfs.TreeLinkType;
import ru.myx.ae3.vfs.ars.ArsStorage;
import ru.myx.ae3.vfs.ram.StorageImplMemory;
import ru.myx.ae3.vfs.signals.StorageImplSignals;

final class TestVfsSignals {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		Engine.createGuid();
		System.out.println(">>>> >>>> TEST VFS SIGNALS");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final Map<String, Function<Void, Object>> signals = Create.tempMap();
			final StringBuilder builder = new StringBuilder();
			signals.put("TEST", new Function<Void, Object>() {

				@Override
				public Object apply(final Void arg) {

					builder.append("YO!");
					return Boolean.TRUE;
				}

				@Override
				public String toString() {

					return "TesT";
				}
			});
			final ArsStorage<?, ?, ?> storage = new StorageImplSignals(signals);
			assert TestVfs.testStorageRootReference(storage);
			final Entry root = Storage.createRoot(storage);
			assert TestVfs.testAssertContainer(root);
			{
				final ArsStorage<?, ?, ?> ramfs = StorageImplMemory.create("test");
				assert TestVfs.testStorageRootReference(ramfs);
				final Entry ramfsRoot = Storage.createRoot(ramfs);
				assert TestVfs.testAssertContainer(ramfsRoot);
				{
					final Entry mount = Storage.mount(ramfsRoot, "ctrl", TreeLinkType.PUBLIC_TREE_REFERENCE, root);
					assert TestVfs.testAssertContainer(mount);
					assert "ctrl".equals(mount.getKey());
				}
				{
					final Entry test = ramfsRoot.relative("ctrl", null);
					assert test != null : "Doesn't exist!";
					{
						final Entry signal = ramfsRoot.relative("ctrl/TEST", null);
						assert TestVfs.testAssertBinary(signal);
						assert signal.toBinary().getBinaryContent().baseValue().toString().startsWith("TesT") : "Wrong binary content!";
						assert builder.length() == 0 : "Builder should be empty";
						signal.doUnlink().baseValue();
						assert builder.length() == 3 : "Builder's length != 3";
						builder.setLength(0);
					}
				}
			}
		}
	}
}
