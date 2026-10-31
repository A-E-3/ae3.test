package ru.myx.ae3.test.vfs;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.common.Value;
import ru.myx.ae3.help.Convert;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.know.Guid;
import ru.myx.ae3.report.Report;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.vfs.Entry;
import ru.myx.ae3.vfs.EntryContainer;
import ru.myx.ae3.vfs.Storage;
import ru.myx.ae3.vfs.TreeLinkType;
import ru.myx.ae3.vfs.TreeReadType;
import ru.myx.ae3.vfs.ars.ArsRefArray;
import ru.myx.ae3.vfs.ars.ArsRecord;
import ru.myx.ae3.vfs.ars.ArsReference;
import ru.myx.ae3.vfs.ars.ArsStorage;
import ru.myx.ae3.vfs.s4.StorageImplS4;

/**
 * 
 * @author myx
 * 		
 */
public final class TestVfsReadWrite {
	
	static {
		TestAe3.initAE3();
	}
	
	private static boolean COMPARE_VS = true;
	
	private static VfsVariants VS_1 = VfsVariants.TEMP_S4_BDB;
	
	private static VfsVariants VS_2 = VfsVariants.TEMP_S4_BDB_COMPARE;
	
	private static boolean SPEED = !Report.MODE_ASSERT;
	
	private static final byte[] TEST_BUFFER;
	static {
		TEST_BUFFER = new byte[1024];
		final Random rnd = new Random();
		rnd.setSeed(2206241);
		rnd.nextBytes(TestVfsReadWrite.TEST_BUFFER);
	}
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		
		System.out.println(">>>> >>>> TEST VFS READ/WRITE");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		final StringBuilder results = new StringBuilder();
		results.append("\r\n");
		final List<VfsVariants> pending = new ArrayList<>();
		if (TestVfsReadWrite.COMPARE_VS) {
			pending.add(TestVfsReadWrite.VS_1);
			pending.add(TestVfsReadWrite.VS_2);
		} else {
			for (final VfsVariants variant : VfsVariants.values()) {
				if (!TestVfsReadWrite.SPEED || variant.isWorking() && !variant.isSlow()) {
					pending.add(variant);
				}
			}
		}
		
		System.out.println(">>>> >>>> TESTS PENDING: " + pending);
		System.out.println(">>>> >>>> SPEED TESTS: " + TestVfsReadWrite.SPEED);
		
		for (int j = TestVfsReadWrite.SPEED || TestVfsReadWrite.COMPARE_VS
			? 2
			: 1; j > 0; j--) {
			for (final VfsVariants variant : pending) {
				if (variant.isWorking()) {
					final ArsStorage<ArsRecord, ArsReference<ArsRecord>, ArsRefArray<ArsReference<ArsRecord>>> local = Convert.Any.toAny(variant.createInstance());
					TestVfsReadWrite.test(local, true, results);
				}
			}
		}
		
		if (TestVfsReadWrite.SPEED) {
			for (int k = TestVfsReadWrite.COMPARE_VS
				? 3
				: 2; k > 0; k--) {
				for (final VfsVariants variant : pending) {
					System.out.println();
					System.out.println();
					System.out.println(results);
					for (int j = 2; j > 0; j--) {
						final ArsStorage<ArsRecord, ArsReference<ArsRecord>, ArsRefArray<ArsReference<ArsRecord>>> local = Convert.Any.toAny(variant.createInstance());
						TestVfsReadWrite.test(local, false, results);
					}
				}
			}
		}
		System.out.println();
		System.out.println();
		System.out.println(results);
	}
	
	private static void test(final ArsStorage<ArsRecord, ArsReference<ArsRecord>, ArsRefArray<ArsReference<ArsRecord>>> local, final boolean once, final StringBuilder results)
			throws Exception {
			
		@SuppressWarnings("unused")
		long totalTime = 0;
		results.append("TYPE: ").append(local);
		if (local.isReadOnly()) {
			throw new IllegalArgumentException("Cannot test read-only storages!");
		}
		System.out.println("=============================================================");
		System.out.println("LOCAL TYPE: " + local);
		{
			System.out.println("2ND START");
			System.out.println("START");
			// local.start();
		}
		/**
		 * Storage Driver Basics
		 */
		{
			final ArsReference<ArsRecord> root = local.getRootReference();
			for (final String key : new String[]{
					"a", "bb", "ccc", "dddd"
			}) {
				final ArsRecord keyObject = local.createKeyForString(key);
				{
					final Value<ArsReference<ArsRecord>> link = local.getLink(root.getTarget(), keyObject, null);
					assert link == null || link.baseValue() == null : "Non existing link should return null when no LinkType specified";
				}
				{
					final Value<ArsReference<ArsRecord>> link = local.getLink(root.getTarget(), keyObject, TreeLinkType.PUBLIC_TREE_REFERENCE);
					assert link != null && link.baseValue() != null : "Non existing link should return non-null with LinkType specified";
				}
				{
					/**
					 * TODO: create link and check for existance?
					 */
				}
			}
		}
		final Entry root = Storage.createRoot(local);
		{
			final String guid = Engine.createGuid();
			System.out.println("CHECK-WRITE-OVERWRITE-READ-DELETE TEST, will use guid: " + guid);
			final Entry file = root.relative(guid, TreeLinkType.PUBLIC_TREE_REFERENCE);
			assert file != null : "NULL value!";
			assert !file.isExist() : "Should not exist!";
			assert !file.isBinary() : "Should not be binary yet!";
			file.doSetBinary(Transfer.createCopier("12345".getBytes(StandardCharsets.US_ASCII))).baseValue();
			assert file.isExist() : "Should already exist!";
			assert file.isBinary() : "Should become binary!";
			assert file.toBinary() != null : "Unexpected NULL";
			assert file.toBinary().getBinaryContent() != null : "Unexpected NULL";
			assert file.toBinary().getBinaryContent().baseValue() != null : "Unexpected NULL";
			assert file.toBinary().getBinaryContent().baseValue().toString() != null : "Unexpected NULL";
			assert "12345".equals(file.toBinary().getBinaryContent().baseValue().toString()) : "Wrong contents!";
			file.doSetBinary(Transfer.createCopier("987".getBytes(StandardCharsets.US_ASCII))).baseValue();
			assert file.isExist() : "Should already exist!";
			assert file.isBinary() : "Should become binary!";
			assert "987".equals(file.toBinary().getBinaryContent().baseValue().toString()) : "Wrong contents!";
			file.doUnlink().baseValue();
			assert !file.isExist() : "Should not exist already!";
			assert !file.isBinary() : "Should not be binary already!";
		}
		{
			for (final int length : Report.MODE_ASSERT
				/**
				 * FIXME: bigger sizes are not working yet!
				 */
				? new int[]{
						0, 10, 20, 30, 40, 48, 100, 300, 600, 900, 1024
			}
				: new int[]{
						0, 10, 20, 30, 40, 48, 100, 300, 600, 900, 1024, 1500, 9000, 32000, 128000
			}) {
				System.out.println("CHECK-BINARY TEST, size: " + length);
				final byte[] bytes = new byte[length];
				for (int left = length; left > 0;) {
					final int random = Engine.createRandom();
					for (int leftLong = 4; leftLong > 0 && left > 0;) {
						bytes[--left] = (byte) (random >> --leftLong * 8);
					}
				}
				final TransferCopier copier = Transfer.createCopier(bytes);
				final ArsRecord template = local.createBinaryTemplate(copier);
				assert template != null : "Template is null: localClass=" + local.getClass().getName() + ", copierClass=" + copier.getClass().getName() + ", copier=" + copier;
				assert template.isBinary() : "Template is not binary: localClass=" + local.getClass().getName() + ", copierClass=" + copier.getClass().getName() + ", copier="
						+ copier + ", templateClass=" + template.getClass().getName() + ", template=" + template;
				assert template.getBinaryContentLength() == length : "Wrong length!";
				assert local.getBinary(template) != null : "binary content support is broken! Value is NULL (while length is correct), templateClass="
						+ template.getClass().getName();
				assert local.getBinary(template).baseValue() != null;
				assert local.getBinary(template).baseValue().length() == length;
				assert local.getBinary(template).baseValue().equals(copier) : "Not equals: classCopier=" + copier.getClass().getName() + ", classResult="
						+ local.getBinary(template).baseValue().getClass().getName();
			}
		}
		{
			final int size = once
				? 1024
				: 83333;
			System.out.println("CREATE-DELETE (" + size + ") TEST");
			final long started = System.currentTimeMillis();
			final EntryContainer test = root.toContainer().getContentElement("ADAA", TreeLinkType.HIDDEN_TREE_REFERENCE).baseValue().toContainer();
			test.doUnlink().baseValue();
			assert !test.isExist();
			assert !test.isContainer();
			test.doSetContainer().baseValue();
			assert test.isExist();
			assert test.isContainer();
			for (int i = 0; i < size; i++) {
				final Entry created = test.getContentElement("ADAA" + i, i % 1 == 0
					? TreeLinkType.HIDDEN_TREE_REFERENCE
					: TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
				assert created != null : "Shouldn't create NULL when linkage mode is not NULL";
				// create
				assert !created.isExist() : "Shouldn't exist yet";
				created.doSetPrimitive(i % 10 == 0
					? Guid.GUID_NULL
					: Guid.forJavaInteger(i)).baseValue();
				// delete
				assert created.isExist() : "Should exist already";
				created.doUnlink().baseValue();
				assert !created.isExist() : "Shouldn't exist already";
				if (i % 10000 == 9999) {
					System.out.println("...left: " + (size - i));
				}
			}
			/**
			 * delete test folder
			 */
			assert test.isExist();
			assert test.isContainer();
			test.doUnlink().baseValue();
			assert !test.isExist();
			assert !test.isContainer();
			/**
			 * 
			 */
			final long timeTook = System.currentTimeMillis() - started;
			totalTime += timeTook;
			final String took = Format.Compact.toPeriod(timeTook);
			System.out.println("CREATE-DELETE TEST TOOK: " + took);
			results.append(", CDT: ").append(took);
		}
		{
			final int size = once
				? 1024
				: 83333;
			System.out.println("CREATE-LIST-GET-DELETE (" + size + ") TEST");
			final long started = System.currentTimeMillis();
			final EntryContainer test = root.toContainer().getContentElement("ADAA", TreeLinkType.HIDDEN_TREE_REFERENCE).baseValue().toContainer();
			test.doUnlink().baseValue();
			assert !test.isExist();
			assert !test.isContainer();
			test.doSetContainer().baseValue();
			assert test.isExist();
			assert test.isContainer();
			assert test.toContainer().isContainer();
			/**
			 * create
			 */
			System.out.println(">>>>> CREATE");
			for (int i = 0; i < size; i++) {
				final Entry created = test.getContentElement("ADAA" + i, i % 1 == 0
					? TreeLinkType.HIDDEN_TREE_REFERENCE
					: TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
				assert created != null : "Shouldn't create NULL when linkage mode is not NULL";
				// create
				assert !created.isExist() : "Shouldn't exist yet";
				created.doSetPrimitive(i % 10 == 0
					? Guid.GUID_NULL
					: Guid.forJavaInteger(i)).baseValue();
				if (i % 10000 == 9999) {
					System.out.println("...left: " + (size - i));
				}
			}
			/**
			 * list
			 */
			System.out.println(">>>>> LIST");
			for (final TreeReadType type : new TreeReadType[]{
					null, TreeReadType.ANY, TreeReadType.PERSISTENT
			}) {
				final BaseList<Entry> contents = test.toContainer().getContentCollection(type).baseValue();
				System.out.println(">>>>> >>> list type: " + type + ", expect: " + size);
				assert contents == null
					? size == 0
					: contents.length() == size //
					: "expected to be " + size + ", but" + (contents == null
						? " 'contents' is null"
						: ": " + contents.length());
			}
			for (final TreeReadType type : new TreeReadType[]{
					TreeReadType.ITERABLE, TreeReadType.SEARCHABLE
			}) {
				final BaseList<Entry> contents = test.toContainer().getContentCollection(type).baseValue();
				assert contents != null;
				assert contents.length() == size || contents.length() == size / 2 : "expected to be " + size + " or " + size / 2 + ", but: " + contents.length();
			}
			/**
			 * delete
			 */
			System.out.println(">>>>> GET+DELETE");
			for (int i = 0; i < size; i++) {
				final Entry existing = test.getContentElement("ADAA" + i, i % 1 == 0
					? TreeLinkType.HIDDEN_TREE_REFERENCE
					: TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
				assert existing != null : "Shouldn't create NULL when linkage mode is not NULL";
				// create
				assert existing.isExist() : "Shouldn't exist already";
				// delete
				existing.doUnlink().baseValue();
				assert !existing.isExist() : "Shouldn't exist already";
				if (i % 10000 == 9999) {
					System.out.println("...left: " + (size - i));
				}
			}
			/**
			 * re-check for empty list
			 */
			System.out.println(">>>>> LIST");
			for (final TreeReadType type : TreeReadType.values()) {
				final BaseList<Entry> contents = test.toContainer().getContentCollection(type).baseValue();
				assert contents != null;
				assert contents.length() == 0 : "expected to be 0, but: " + contents.length();
			}
			/**
			 * delete folder
			 */
			assert test.isExist();
			assert test.isContainer();
			test.doUnlink().baseValue();
			assert !test.isExist();
			assert !test.isContainer();
			/**
			 * 
			 */
			final long timeTook = System.currentTimeMillis() - started;
			totalTime += timeTook;
			final String took = Format.Compact.toPeriod(timeTook);
			System.out.println("CREATE-LIST-GET-DELETE TEST TOOK: " + took);
			results.append(", CLGDT: ").append(took);
		}
		{
			final int size = once
				? 1024
				: 83333;
			System.out.println("CREATE-LIST-GET-RENAME-DELETE (" + size + ") TEST");
			final long started = System.currentTimeMillis();
			final EntryContainer test = root.toContainer().getContentElement("ADAA", TreeLinkType.HIDDEN_TREE_REFERENCE).baseValue().toContainer();
			test.doUnlink().baseValue();
			assert !test.isExist();
			assert !test.isContainer();
			test.doSetContainer().baseValue();
			assert test.isExist();
			assert test.isContainer();
			assert test.toContainer().isContainer();
			/**
			 * create
			 */
			System.out.println(">>>>> CREATE");
			for (int i = 0; i < size; i++) {
				final Entry created = test.getContentElement("ADAA" + i, i % 1 == 0
					? TreeLinkType.HIDDEN_TREE_REFERENCE
					: TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
				assert created != null : "Shouldn't create NULL when linkage mode is not NULL";
				// create
				assert !created.isExist() : "Shouldn't exist yet";
				created.doSetPrimitive(i % 10 == 0
					? Guid.GUID_NULL
					: Guid.forJavaInteger(i)).baseValue();
				if (i % 10000 == 9999) {
					System.out.println("...left: " + (size - i));
				}
			}
			/**
			 * list
			 */
			System.out.println(">>>>> LIST");
			for (final TreeReadType type : new TreeReadType[]{
					null, TreeReadType.ANY, TreeReadType.PERSISTENT
			}) {
				final BaseList<Entry> contents = test.toContainer().getContentCollection(type).baseValue();
				System.out.println(">>>>> >>> list type: " + type + ", expect: " + size);
				assert contents == null
					? size == 0
					: contents.length() == size //
					: "expected to be " + size + ", but" + (contents == null
						? " 'contents' is null"
						: ": " + contents.length());
			}
			for (final TreeReadType type : new TreeReadType[]{
					TreeReadType.ITERABLE, TreeReadType.SEARCHABLE
			}) {
				final BaseList<Entry> contents = test.toContainer().getContentCollection(type).baseValue();
				assert contents != null;
				assert contents.length() == size || contents.length() == size / 2 : "expected to be " + size + " or " + size / 2 + ", but: " + contents.length();
			}
			/**
			 * delete
			 */
			System.out.println(">>>>> GET+RENAME+DELETE");
			for (int i = 0; i < size; i++) {
				final Entry existing = test.getContentElement("ADAA" + i, i % 1 == 0
					? TreeLinkType.HIDDEN_TREE_REFERENCE
					: TreeLinkType.PUBLIC_TREE_REFERENCE).baseValue();
				assert existing != null : "Shouldn't create NULL when linkage mode is not NULL";
				assert existing.isExist() : "Shouldn't exist already";
				
				final String newName = "BDBB" + i;
				final boolean rename = existing.doRename(newName).baseValue() == Boolean.TRUE;
				
				assert rename : "Rename failed!";
				assert existing.getKey().equals(newName) : "Name is wrong: " + existing.getKey();
				
				// delete
				existing.doUnlink().baseValue();
				
				assert !existing.isExist() : "Shouldn't exist already";
				
				assert test.getContentElement("ADAA" + i, null).baseValue() == null : "Must be deleted!";
				
				if (i % 10000 == 9999) {
					System.out.println("...left: " + (size - i));
				}
			}
			/**
			 * re-check for empty list
			 */
			System.out.println(">>>>> LIST");
			for (final TreeReadType type : TreeReadType.values()) {
				final BaseList<Entry> contents = test.toContainer().getContentCollection(type).baseValue();
				assert contents != null;
				assert contents.length() == 0 : "expected to be 0, but: " + contents.length();
			}
			/**
			 * delete folder
			 */
			assert test.isExist();
			assert test.isContainer();
			test.doUnlink().baseValue();
			assert !test.isExist();
			assert !test.isContainer();
			/**
			 * 
			 */
			final long timeTook = System.currentTimeMillis() - started;
			totalTime += timeTook;
			final String took = Format.Compact.toPeriod(timeTook);
			System.out.println("CREATE-LIST-GET-RENAME-DELETE TEST TOOK: " + took);
			results.append(", CLGRDT: ").append(took);
		}
		{
			final ArsStorage<?, ?, ?> storage = local;
			if (storage instanceof StorageImplS4) {
				((StorageImplS4) storage).shutdown();
			}
		}
		results.append("\r\n");
		System.out.println("=============================================================");
	}
}
