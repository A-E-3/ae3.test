package ru.myx.ae3.test.binary;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import ru.myx.ae3.Engine;
import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferBuffer;
import ru.myx.ae3.binary.TransferCollector;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.binary.TransferTarget;
import ru.myx.ae3.help.Format;

/**
 *
 * @author myx
 *
 */
public class TestBinary {

	/**
	 *
	 * @param args
	 * @throws Throwable
	 */
	@SuppressWarnings("resource")
	public static void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> TEST BINARY");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			assert TransferBuffer.NUL_BUFFER != null;
			assert !TransferBuffer.NUL_BUFFER.isSequence();
			assert !TransferBuffer.NUL_BUFFER.hasRemaining();
			assert TransferBuffer.NUL_BUFFER.remaining() == 0;
			assert TransferBuffer.NUL_BUFFER.next() == -1;
			assert TransferBuffer.NUL_BUFFER.next(new byte[10], 0, 10) == 0;
			assert TransferBuffer.NUL_BUFFER.toSubBuffer(0, 0) == TransferBuffer.NUL_BUFFER;
			assert TransferBuffer.NUL_BUFFER.toDirectArray() != null;
			assert TransferBuffer.NUL_BUFFER.toDirectArray().length == 0;
			assert TransferBuffer.NUL_BUFFER.toInputStream() != null;
			assert TransferBuffer.NUL_BUFFER.toInputStream().read() == -1;
			assert TransferBuffer.NUL_BUFFER.toInputStream().read(new byte[10]) <= 0;
			assert TransferBuffer.NUL_BUFFER.toInputStream().read(new byte[10], 0, 10) <= 0;
			assert TransferBuffer.NUL_BUFFER.toInputStream().available() == 0;
			assert TransferBuffer.NUL_BUFFER.toInputStream().skip(10) == 0;
			assert TransferBuffer.NUL_BUFFER.toString() != null;
			assert TransferBuffer.NUL_BUFFER.toString().length() == 0;
			assert TransferBuffer.NUL_BUFFER.toString(StandardCharsets.US_ASCII) != null;
			assert TransferBuffer.NUL_BUFFER.toString(StandardCharsets.US_ASCII).length() == 0;
			assert TransferBuffer.NUL_BUFFER.toString(StandardCharsets.UTF_8) != null;
			assert TransferBuffer.NUL_BUFFER.toString(StandardCharsets.UTF_8).length() == 0;
			assert TransferBuffer.NUL_BUFFER.toBinary() != null;
			assert TransferBuffer.NUL_BUFFER.toBinary().length() == 0;
			assert TransferBuffer.NUL_BUFFER.toBinary().nextCopy() != null;
			assert !TransferBuffer.NUL_BUFFER.toBinary().nextCopy().hasRemaining();
			assert TransferBuffer.NUL_BUFFER.toBinary().nextCopy().remaining() == 0;
			assert TransferBuffer.NUL_BUFFER.getMessageDigest() != null;
			// just to be sure
			assert MessageDigest.isEqual(
					Engine.getMessageDigestInstance().digest(), //
					Engine.getMessageDigestInstance().digest());
			assert MessageDigest.isEqual(
					Engine.getMessageDigestInstance().digest(), //
					TransferBuffer.NUL_BUFFER.getMessageDigest().digest());
			assert MessageDigest.isEqual(
					Engine.getMessageDigestInstance().digest(), //
					TransferBuffer.NUL_BUFFER.updateMessageDigest(Engine.getMessageDigestInstance()).digest());
		}
		{
			assert TransferCopier.NUL_COPIER != null;
			assert TransferCopier.NUL_COPIER.length() == 0;
			assert TransferCopier.NUL_COPIER.copy(0, new byte[10], 0, 10) == 0;
			assert TransferCopier.NUL_COPIER.slice(0, 10) == TransferCopier.NUL_COPIER;
			assert TransferCopier.NUL_COPIER.nextCopy() == TransferBuffer.NUL_BUFFER;
			assert TransferCopier.NUL_COPIER.nextInputStream() != null;
			assert TransferCopier.NUL_COPIER.nextInputStream().read() == -1;
			assert TransferCopier.NUL_COPIER.nextInputStream().available() == 0;
			assert TransferCopier.NUL_COPIER.getMessageDigest() != null;
			// just to be sure
			assert MessageDigest.isEqual(
					Engine.getMessageDigestInstance().digest(), //
					Engine.getMessageDigestInstance().digest());
			assert MessageDigest.isEqual(
					Engine.getMessageDigestInstance().digest(), //
					TransferCopier.NUL_COPIER.getMessageDigest().digest());
			assert MessageDigest.isEqual(
					Engine.getMessageDigestInstance().digest(), //
					TransferCopier.NUL_COPIER.updateMessageDigest(Engine.getMessageDigestInstance()).digest());
		}
		{
			assert TransferTarget.NUL_TARGET != null;
			assert TransferTarget.NUL_TARGET.absorb(0);
			assert TransferTarget.NUL_TARGET.absorb(1);
			assert TransferTarget.NUL_TARGET.absorbArray(new byte[10], 0, 10);
			assert TransferTarget.NUL_TARGET.absorbBuffer(TransferBuffer.NUL_BUFFER);
			assert TransferTarget.NUL_TARGET.absorbNio(ByteBuffer.wrap(new byte[10]));

			final StringBuilder checkBuilder = new StringBuilder();
			assert checkBuilder.length() == 0;

			assert TransferTarget.NUL_TARGET.enqueueAction(null, arg -> {
				
				checkBuilder.append("0123");
				return null;
			}, null);

			for (int attempt = 20; attempt > 0; attempt--) {
				Thread.sleep(100);
				if (checkBuilder.length() > 0) {
					break;
				}
			}
			assert checkBuilder.length() == 4;
		}
		{
			assert TransferCollector.NUL_COLLECTOR != null;
			assert TransferCollector.NUL_COLLECTOR.getOutputStream() != null;
			assert TransferCollector.NUL_COLLECTOR.getTarget() == TransferTarget.NUL_TARGET;
			assert TransferCollector.NUL_COLLECTOR.toBinary() == TransferCopier.NUL_COPIER;
			assert TransferCollector.NUL_COLLECTOR.toBuffer() == TransferBuffer.NUL_BUFFER;
			assert TransferCollector.NUL_COLLECTOR.toCloneFactory() == TransferCopier.NUL_COPIER;

			TransferCollector.NUL_COLLECTOR.getOutputStream().flush();
			TransferCollector.NUL_COLLECTOR.reset();
			TransferCollector.NUL_COLLECTOR.close();

			assert TransferCollector.NUL_COLLECTOR.getOutputStream() != null;
			assert TransferCollector.NUL_COLLECTOR.getTarget() == TransferTarget.NUL_TARGET;
			assert TransferCollector.NUL_COLLECTOR.toBinary() == TransferCopier.NUL_COPIER;
			assert TransferCollector.NUL_COLLECTOR.toBuffer() == TransferBuffer.NUL_BUFFER;
			assert TransferCollector.NUL_COLLECTOR.toCloneFactory() == TransferCopier.NUL_COPIER;

			TransferCollector.NUL_COLLECTOR.getOutputStream().write(1);
			TransferCollector.NUL_COLLECTOR.getOutputStream().write(new byte[10]);
			TransferCollector.NUL_COLLECTOR.getOutputStream().write(new byte[10], 0, 10);

			assert TransferCollector.NUL_COLLECTOR.getTarget() == TransferTarget.NUL_TARGET;
			assert TransferCollector.NUL_COLLECTOR.toBinary() == TransferCopier.NUL_COPIER;
			assert TransferCollector.NUL_COLLECTOR.toBuffer() == TransferBuffer.NUL_BUFFER;
			assert TransferCollector.NUL_COLLECTOR.toCloneFactory() == TransferCopier.NUL_COPIER;
		}
		for (final String test : new String[]{
				"", "1234567890", "wswx cdcd vfvd rrf", TestBinary.randomString(0), TestBinary.randomString(200), TestBinary.randomString(2200), TestBinary.randomString(22200),
				TestBinary.randomString(222200), TestBinary.randomString(2222200),
		}) {
			final byte[] bytes = test.getBytes();
			System.out.println(">>>> >>>> TEST BINARY, length: " + Format.Compact.toBytes(bytes.length) + " (" + Format.Exact.toBytes(bytes.length) + ")");
			{
				final TransferBuffer buffer = Transfer.createBuffer(bytes);
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert buffer.isDirectAbsolutely() || bytes.length == 0 || bytes.length > Transfer.BUFFER_MAX;
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferBuffer buffer = Transfer.createBuffer(bytes, 0, bytes.length);
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert buffer.isDirectAbsolutely() || bytes.length == 0 || bytes.length > Transfer.BUFFER_MAX;
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferBuffer buffer = Transfer.wrapBuffer(bytes);
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert buffer.isDirectAbsolutely() || bytes.length == 0;
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferBuffer buffer = Transfer.wrapBuffer(bytes, 0, bytes.length);
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert buffer.isDirectAbsolutely() || bytes.length == 0;
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferBuffer buffer = Transfer.createBuffer(new ByteArrayInputStream(bytes));
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			/**
			 * COLLECTOR
			 */
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				for (final byte b : bytes) {
					collector.getTarget().absorb(b & 0xFF);
				}
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				for (final byte b : bytes) {
					collector.getOutputStream().write(b & 0xFF);
				}
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getOutputStream().write('x');
				for (final byte b : bytes) {
					collector.getOutputStream().write(b & 0xFF);
				}
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.next() == 'x';
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getOutputStream().write(bytes);
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getTarget().absorbArray(bytes, 0, bytes.length);
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getTarget().absorbBuffer(Transfer.createBuffer(bytes));
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getTarget().absorbNio(ByteBuffer.wrap(bytes));
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getTarget().absorb('x');
				collector.getTarget().absorbNio(ByteBuffer.wrap(bytes));
				collector.close();
				final TransferBuffer buffer = collector.toBuffer();
				assert buffer.next() == 'x';
				assert buffer.remaining() == bytes.length : "Length is fucked";
				assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
				assert buffer.remaining() == 0 : "Should be empty";
				/**
				 * just to cover
				 */
				buffer.destroy();
			}
			{
				final TransferCollector collector = Transfer.createCollector();
				assert collector != null;
				collector.getTarget().absorbArray(bytes, 0, bytes.length);
				final TransferCopier copier = collector.toBinary();
				{
					final TransferBuffer buffer = copier.nextCopy();
					assert buffer.remaining() == bytes.length : "Length is fucked";
					assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
					assert buffer.remaining() == 0 : "Should be empty";
					/**
					 * just to cover
					 */
					buffer.destroy();
				}
				{
					final TransferBuffer buffer = copier.nextCopy();
					assert buffer.remaining() == bytes.length : "Length is fucked";
					assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
					assert buffer.remaining() == 0 : "Should be empty";
					/**
					 * just to cover
					 */
					buffer.destroy();
				}
				{
					final TransferBuffer buffer = Transfer.createBufferFromBinary(copier.nextCopy());
					assert buffer.remaining() == bytes.length : "Length is fucked";
					assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
					assert buffer.remaining() == 0 : "Should be empty";
					/**
					 * just to cover
					 */
					buffer.destroy();
				}
				{
					final TransferBuffer buffer = Transfer.createBufferFromBinary(copier);
					assert buffer.remaining() == bytes.length : "Length is fucked";
					assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
					assert buffer.remaining() == 0 : "Should be empty";
					/**
					 * just to cover
					 */
					buffer.destroy();
				}
				{
					final TransferBuffer buffer = Transfer.createBufferFromBinary(copier.nextInputStream());
					assert buffer.remaining() == bytes.length : "Length is fucked";
					assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
					assert buffer.remaining() == 0 : "Should be empty";
					/**
					 * just to cover
					 */
					buffer.destroy();
				}
				{
					final TransferBuffer buffer = Transfer.createBufferFromBinary(copier.nextDirectArray());
					assert buffer.remaining() == bytes.length : "Length is fucked";
					assert new String(buffer.toDirectArray()).equals(test) : "Contents fucked";
					assert buffer.remaining() == 0 : "Should be empty";
					/**
					 * just to cover
					 */
					buffer.destroy();
				}
			}
			//
			//
			//
			for (final TransferCopier copier : new TransferCopier[]{
					Transfer.createCopier(bytes), Transfer.createCopier(bytes, 0, bytes.length), Transfer.wrapCopier(bytes), Transfer.wrapCopier(bytes, 0, bytes.length),
					//
			}) {
				assert copier.length() == bytes.length //
				: "Length is fucked";

				assert copier.nextCopy().remaining() == bytes.length //
				: "Length is fucked";

				assert new String(copier.nextDirectArray()).equals(test) : "Contents fucked";

				assert copier.toString().equals(test) //
				: "Contents fucked, toString=" + copier.nextCopy().toString();

				assert copier.nextCopy().toString().equals(test) //
				: "Contents fucked, toString=" + copier.nextCopy().toString();

				assert copier.toString("UTF-8").equals(test) //
				: "Contents fucked";

				assert copier.nextCopy().toString("UTF-8").equals(test) //
				: "Contents fucked";

				assert copier.toString(StandardCharsets.UTF_8).equals(test) //
				: "Contents fucked";

				assert copier.nextCopy().toString(StandardCharsets.UTF_8).equals(test) //
				: "Contents fucked";

				assert copier.toStringUtf8().equals(test) //
				: "Contents fucked";

				assert MessageDigest.isEqual(
						copier.getMessageDigest().digest(), //
						copier.updateMessageDigest(Engine.getMessageDigestInstance()).digest());

				assert MessageDigest.isEqual(
						Engine.getMessageDigestInstance().digest(), //
						copier.getMessageDigest().digest()) == (copier.length() == 0);
			}
		}

		/**
		 * TEST collector as source!
		 */
		{
			final CollectorAsSourceTest test = new CollectorAsSourceTest();
			final TransferCollector collector = Transfer.createCollector();

			assert test.rx == 0;

			collector.getTarget().absorb('a');
			collector.getOutputStream().write('b');

			collector.connectTarget(test);

			assert test.rx == 2;

			collector.getTarget().absorb('a');
			collector.getOutputStream().write('B');

			assert test.rx == 4;

			collector.connectTarget(null);

			collector.getTarget().absorb('A');
			collector.getOutputStream().write('b');

			assert test.rx == 4;

			collector.connectTarget(test);

			assert test.rx == 6;

			collector.getTarget().absorbArray("0123456789".getBytes(StandardCharsets.UTF_8), 0, 10);
			collector.getTarget().absorbBuffer(Transfer.wrapBuffer("0123456789".getBytes(StandardCharsets.UTF_8)));
			collector.getOutputStream().write("0123456789".getBytes(StandardCharsets.UTF_8));
			collector.getOutputStream().write("0123456789".getBytes(StandardCharsets.UTF_8), 0, 10);

			assert test.rx == 46;

			final TransferCopier copier = collector.toBinary();

			assert copier.length() == 0;
			assert copier == TransferCopier.NUL_COPIER;

		}
		{
			assert Transfer.createDescription("test", 0) != null;
		}
	}
	
	private static final String randomString(final int length) {
		
		final StringBuilder builder = new StringBuilder(length);
		for (int i = length; i > 0; --i) {
			builder.append(Engine.createRandom(10));
		}
		return builder.toString();
	}
}
