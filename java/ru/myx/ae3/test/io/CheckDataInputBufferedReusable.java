/*
 * Created on 08.02.2006
 */
package ru.myx.ae3.test.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import ru.myx.io.DataInputBufferedReusable;

/** @author myx */
class CheckDataInputBufferedReusable {
	
	/** @param args
	 * @throws Throwable */
	public static void main(final String[] args) throws Throwable {
		
		final String textShort = "AbCd fdskhj s;jcxvkjiurpf ;sh kjla la;`d'oei выжа жлссчмгзш лаыфашмшщхсм лоаивы жорв аЫЖР ВАШГЫАЛДОРЖРОЛлрдл dkfljh fds ;ldf s98908798 fds 9879070 0000000000000....";

		CheckDataInputBufferedReusable.testUTF(textShort, 1);
		CheckDataInputBufferedReusable.testUTF(textShort, 8);
		CheckDataInputBufferedReusable.testUTF(textShort, 16);

		final String textLong;
		{
			final StringBuilder builder = new StringBuilder(textShort);
			for (int i = 8; i > 0; --i) {
				builder.append(builder.toString());
			}
			textLong = builder.toString();
		}

		CheckDataInputBufferedReusable.testUTF(textLong, 1);
		CheckDataInputBufferedReusable.testUTF(textLong, 8);
		CheckDataInputBufferedReusable.testUTF(textLong, 16);
	}

	private static void testUTF(final String text, final int count) throws Throwable {
		
		System.out.println("TEST UTF: length=" + text.length() + ", count=" + count);
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (final DataOutputStream dos = new DataOutputStream(bos)) {
			for (int i = count; i > 0; --i) {
				dos.writeUTF(text);
			}
		}
		System.out.println("TEST UTF: output length=" + bos.size());
		final ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		try (final DataInputBufferedReusable dis = new DataInputBufferedReusable(4 * 1024, 64 * 1024, 32 * 1024)) {
			dis.setStream(bis);
			for (int i = count; i > 0; --i) {
				final String read = dis.readUTF();
				if (!text.equals(read)) {
					throw new Error("Doesn't match:\r\na=" + text + "\r\nb=" + read);
				}
			}
		}
	}
}
