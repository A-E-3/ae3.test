/*
 * Created on 08.02.2006
 */
package ru.myx.ae3.test.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;

import ru.myx.io.DataOutputBufferedReusable;

/** @author myx */
class CheckDataOutputBufferedReusable {
	
	/** @param args
	 * @throws Throwable */
	public static void main(final String[] args) throws Throwable {
		
		final String textShort = "AbCd fdskhj s;jcxvkjiurpf ;sh kjla la;`d'oei выжа жлссчмгзш лаыфашмшщхсм лоаивы жорв аЫЖР ВАШГЫАЛДОРЖРОЛлрдл dkfljh fds ;ldf s98908798 fds 9879070 0000000000000....";

		CheckDataOutputBufferedReusable.testUTF(textShort, 1);
		CheckDataOutputBufferedReusable.testUTF(textShort, 8);
		CheckDataOutputBufferedReusable.testUTF(textShort, 16);

		final String textLong;
		{
			final StringBuilder builder = new StringBuilder(textShort);
			for (int i = 8; i > 0; --i) {
				builder.append(builder.toString());
			}
			textLong = builder.toString();
		}

		CheckDataOutputBufferedReusable.testUTF(textLong, 1);
		CheckDataOutputBufferedReusable.testUTF(textLong, 8);
		CheckDataOutputBufferedReusable.testUTF(textLong, 16);
	}

	private static void testUTF(final String text, final int count) throws Throwable {
		
		System.out.println("TEST UTF: length=" + text.length() + ", count=" + count);
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (final DataOutputBufferedReusable dobr = new DataOutputBufferedReusable(32 * 1024, 8 * 1024)) {
			dobr.setStream(bos);
			for (int i = count; i > 0; --i) {
				dobr.writeUTF(text);
			}
		}
		System.out.println("TEST UTF: output length=" + bos.size());
		final ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		final DataInputStream dis = new DataInputStream(bis);
		for (int i = count; i > 0; --i) {
			final String read = dis.readUTF();
			if (!text.equals(read)) {
				throw new Error("Doesn't match:\r\na=" + text + "\r\nb=" + read);
			}
		}
	}
}
