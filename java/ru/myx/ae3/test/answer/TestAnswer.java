package ru.myx.ae3.test.answer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ru.myx.ae3.Engine;
import ru.myx.ae3.answer.Reply;

/** @author myx */
public class TestAnswer {

	private static final void checkReplySAPI() throws Throwable {
		
		System.out.println(">>>> >>>> TEST ANSWER ReplySAPI start");
		final Field[] fields = Reply.class.getDeclaredFields();
		for (final Field field : fields) {
			if (!Modifier.isPublic(field.getModifiers())) {
				continue;
			}
			System.out.println(">>>> >>>> >>>> field: " + field);
			if (field.getName().startsWith("CD_")) {
				if (!Modifier.isStatic(field.getModifiers())) {
					assert false : "Something new";
				}
				if (!Modifier.isPublic(field.getModifiers())) {
					assert false : "Something new";
				}
				if (!Modifier.isFinal(field.getModifiers())) {
					assert false : "Something new";
				}
				if (field.getType() != Integer.TYPE) {
					assert false : "Something new";
				}
				final int code = field.getInt(null);
				System.out.println(">>>> >>>> >>>> code: " + code);

				final String title = Reply.getDefaultTitleForCode(code);
				assert title != null && !title.isBlank() && title.charAt(0) != '[' : "NULL title for code: " + code + ", name: " + field.getName();
			} else //
			if (!"ASYNC".equals(field.getName())) {
				assert false : "Something new";
			}

		}
		System.out.println(">>>> >>>> TEST ANSWER ReplySAPI done");
	}

	/** @param args
	 * @throws Throwable */
	public static void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> TEST ANSWER");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		TestAnswer.checkReplySAPI();
	}

}
