package ru.myx.ae3.test.know;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import ru.myx.ae3.base.BaseDate;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.help.Format;
import ru.myx.ae3.know.Guid;
import ru.myx.ae3.test.TestAe3;
import ru.myx.benchmarking.BenchmarkRequest;

/** @author myx */
class TestGuidBasics {
	
	private final static Set<Guid> GUIDS = new TreeSet<>();

	/** @param args
	 * @throws Throwable */
	public static final void main(final String[] args) throws Throwable {
		
		System.out.println(">>>> >>>> TEST KNOW AE3 GUID BASICS");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}

		TestAe3.initAE3();

		assert Guid.GUID_NULL == Guid.forUnknown(null);
		assert Guid.GUID_NULL == Guid.forUnknown(Guid.forUnknown(null));
		assert Guid.GUID_NUMBER_ZERO == Guid.forJavaInteger(0);
		assert Guid.GUID_NULL != Guid.GUID_NUMBER_ZERO;

		final String[] strings = { //
				"", //
				"a", //
				"abcdefghij", //
				"aф", //
				"aфbcdefghij", //
				"вф", //
				"вфячсмфыывп", //
				"123456789012345678901234", //
				"вфячсмфыыввыаррпыываавып", //
				"123456789012345678901234567", //
				"1234567890123456789012345678", //
				"12345678901234567890123456789", //
				"123456789012345678901234567890", //
				"1234567890123456789012345678901", //
				"12345678901234567890123456789012", //
				"123456789012345678901234567890123", //
				"1234567890123456789012345678901234", //
				"12345678901234567890123456789012345", //
				"123456789012345678901234567890123456", //
				"1234567890123456789012345678901234567", //
				"12345678901234567890123456789012345678", //
				"123456789012345678901234567890123456789", //
				"1234567890123456789012345678901234567890", //
				"12345678901234567890123456789012345678901", //
				"123456789012345678901234567890123456789012", //
				"1234567890123456789012345678901234567890123", //
				"12345678901234567890123456789012345678901234", //
				"123456789012345678901234567890123456789012345", //
				"1234567890123456789012345678901234567890123456", //
				"12345678901234567890123456789012345678901234567", //
				"123456789012345678901234567890123456789012345678", //
				"1234567890123456789012345678901234567890123456789", //
				"12345678901234567890123456789012345678901234567890", //
				"123456789012345678901234567890123456789012345678901", //
				"1234567890123456789012345678901234567890123456789012", //
				"12345678901234567890123456789012345678901234567890123", //
				"123456789012345678901234567890123456789012345678901234", //
		};
		final long[] numbersLong = { //
				1, //
				2, //
				255, //
				256, //
				312, //
				1024, //
				2048, //
				4100, //
				32767, //
				98767, //
				13000256, //
				1600650256, //
				33426000256L, //
				33426000342256L, //
				33426000342556256L, //
				3342607800342556256L //
		};
		TestGuidBasics.test(Guid.GUID_NULL);
		TestGuidBasics.test(Guid.GUID_NEGATIVE_INFINITY);
		TestGuidBasics.test(Guid.GUID_POSITIVE_INFINITY);
		TestGuidBasics.test(Guid.GUID_BYTES_EMPTY);
		TestGuidBasics.test(Guid.GUID_STRING_EMPTY);
		TestGuidBasics.test(Guid.forJavaInteger(0));
		for (final long number : numbersLong) {
			TestGuidBasics.test(Guid.forJavaLong(number));
			TestGuidBasics.test(Guid.forJavaLong(-number));
		}
		for (final String string : strings) {
			TestGuidBasics.test(Guid.forString(string));
		}
		TestGuidBasics.test(Guid.createGuid184());
		TestGuidBasics.test(Guid.createGuid184());
		TestGuidBasics.test(Guid.createGuid384());
		TestGuidBasics.test(Guid.createGuid384());

		TestGuidBasics.test(Guid.forBinaryChecksum(Transfer.wrapCopier("12345".getBytes())));
		TestGuidBasics.test(Guid.forBinaryChecksum(Transfer.wrapCopier("1234567890".getBytes())));
		TestGuidBasics.test(Guid.forBinaryChecksum(Transfer.wrapCopier("1234567890123456789012345678901234567890123456789012345678901234567890".getBytes())));

		TestGuidBasics.test(Guid.forBinaryInline(Transfer.wrapCopier("12345".getBytes())));
		TestGuidBasics.test(Guid.forBinaryInline(Transfer.wrapCopier("1234567890".getBytes())));

		TestGuidBasics.test(Guid.forBinaryText(Transfer.wrapCopier("12345".getBytes())));
		TestGuidBasics.test(Guid.forBinaryText(Transfer.wrapCopier("1234567890".getBytes())));
		TestGuidBasics.test(Guid.forBinaryText(Transfer.wrapCopier("1234567890123456789012345678901234567890123456789012345678901234567890".getBytes())));

		TestGuidBasics.test(Guid.forBinaryChecksum("12345".getBytes()));
		TestGuidBasics.test(Guid.forBinaryChecksum("1234567890".getBytes()));
		TestGuidBasics.test( //
				Guid.forBinaryChecksum("1234567890123456789012345678901234567890123456789012345678901234567890".getBytes()));

		TestGuidBasics.test(Guid.forBinaryInline("12345".getBytes()));
		TestGuidBasics.test(Guid.forBinaryInline("1234567890".getBytes()));

		TestGuidBasics.test(Guid.forBinaryText("12345".getBytes()));
		TestGuidBasics.test(Guid.forBinaryText("1234567890".getBytes()));
		TestGuidBasics.test( //
				Guid.forBinaryText("1234567890123456789012345678901234567890123456789012345678901234567890".getBytes()));

		TestGuidBasics.test(Guid.forUnknown(Transfer.wrapCopier("12345".getBytes())));
		TestGuidBasics.test(Guid.forUnknown(Transfer.wrapCopier("1234567890".getBytes())));
		TestGuidBasics.test(Guid.forUnknown(Transfer.wrapBuffer("12345".getBytes())));
		TestGuidBasics.test(Guid.forUnknown(Transfer.wrapBuffer("1234567890".getBytes())));

		TestGuidBasics.test(Guid.forUnknown("12345".getBytes()));
		TestGuidBasics.test(Guid.forUnknown("1234567890".getBytes()));

		TestGuidBasics.test(Guid.GUID_JS_OBJECT_PROTOTYPE);
		TestGuidBasics.test(Guid.GUID_JS_ARRAY_PROTOTYPE);
		TestGuidBasics.test(Guid.GUID_JS_DATE_PROTOTYPE);
		TestGuidBasics.test(Guid.GUID_JS_OBJECT_EMPTY);
		TestGuidBasics.test(Guid.GUID_JS_ARRAY_EMPTY);

		for (final long date : new long[]{ //
				1336965577, //
				1336965577000L //
		}) {
			System.out.println(">>> >>> date: " + new Date(date));
			TestGuidBasics.test(Guid.forUnknown(new BaseDate(date)));
		}

		if (args != null && args.length > 0) {
			// speed
			final byte[] targetBuffer = new byte[Guid.MAX_LENGTH];
			System.out.println(">> starting speed tests:");
			for (final Guid guid : TestGuidBasics.GUIDS) {
				new BenchmarkRequest(new Runnable() {
					
					@Override
					public final void run() {
						
						Guid.writeGuid(guid, targetBuffer, 0);
					}

					@Override
					public final String toString() {
						
						return "writeGuidBytes: " + guid.toString();
					}
				}).run();
			}
		}
	}

	private static void test(final Guid guid) throws Throwable {
		
		System.out.println("-------------------------------------------------------");
		System.out.println(">>> partition parameter: " + guid.getPartitioningParameter());
		System.out.println(">>> valid: " + guid.isValid());
		System.out.println(">>> guid str: " + guid.toString());
		System.out.println(">>> guid key: " + guid.toName());
		System.out.println(">>> guid hex: " + guid.toHex());
		System.out.println(">>> guid str length: " + guid.toString().length());
		System.out.println(">>> guid key length: " + guid.toName().length());
		System.out.println(">>> guid hex length: " + guid.toHex().length());
		System.out.println(">>> guidType: " + guid.getType());
		System.out.println(">>> guidInline: " + guid.isInline());
		assert guid.isValid() : "Guid is invalid, type=" + guid.getType() + ", ivalue=" + guid.getInlineBaseValue();
		if (guid.isInline()) {
			final Object value = guid.getInlineValue();
			System.out.println(">>> inline >>> value: " + Format.Describe.toEcmaSource(value, ""));
			if (guid.isPrimitive() || guid.isInline() && !guid.isBinaryText()) {
				final Guid other = Guid.forUnknown(value);
				if (!(guid == other || guid.equals(other))) {
					final Object otherValue = other.getInlineValue();
					throw new RuntimeException("Invalid inline guid: guid1=" + guid + ", guid2=" + other + ", valueMatch: " + (value == otherValue || value.equals(otherValue)));
				}
			}
			if (guid.isInlineNumber()) {
				System.out.println(">>> number >>> valid: " + (value instanceof Number));
				if (value instanceof Number && guid.isInlineNumberInteger()) {
					final Number number = (Number) value;
					final long longValue = number.longValue();
					System.out.println(">>> integer >>> value: " + longValue);
					System.out.println(">>> integer >>> valid: " + (longValue == number.doubleValue()));
					final Guid another = Guid.forJavaLong(longValue);
					System.out.println(">>> equal1? >>> valid: " + another.equals(guid));
					if (!another.equals(guid)) {
						System.out.println(">>>>>> v1=" + number + ", v2=" + another.getInlineValue() + ", equals=" + number.equals(another.getInlineValue()));
						throw new RuntimeException("!equals");
					}
					final Guid negative = Guid.forJavaLong(-longValue);
					final Number compare = (Number) negative.getInlineValue();
					System.out.println(">>> equal2? >>> valid: " + (-longValue == compare.doubleValue()));
				}
			}
		}
		assert guid.equals(Guid.fromBase64(guid.toBase64())) : ">>> check str >>> valid: ";
		assert guid.equals(Guid.fromName(guid.toName())) : ">>> check key >>> valid: ";
		assert guid.equals(Guid.fromHex(guid.toHex())) : ">>> check hex >>> valid: ";
		final byte[] buffer = new byte[Guid.MAX_LENGTH];
		Guid.writeGuid(guid, buffer, 0);
		assert guid.equals(Guid.readGuid(buffer)) : ">>> check bin >>> valid: ";
		TestGuidBasics.GUIDS.add(guid);
	}
}
