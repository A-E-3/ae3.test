package ru.myx.ae3.test.layout;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.i3.TargetInterface;
import ru.myx.ae3.l2.LayoutEngine;
import ru.myx.ae3.l2.NativeTargetContext;
import ru.myx.ae3.l2.json.JsonTargetContext;
import ru.myx.ae3.test.TestAe3;

/** @author myx */
public class TestLayout {

	/** @param args
	 */
	public static void main(final String[] args) {
		
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		TestAe3.initAE3();
		Engine.createGuid();
		System.out.println(">>>> TEST LAYOUT");

		{
			System.out.println(">>>> TEST LAYOUT: test classname conversion and back: SequenceAttachmentWest");
			assert "SequenceAttachmentWest".equals(LayoutEngine.convertLayoutNameToClassName("sequence-attachment-west")) : LayoutEngine
					.convertLayoutNameToClassName("sequence-attachment-west");
			assert "sequence-attachment-west".equals(LayoutEngine.convertClassNameToLayoutName("SequenceAttachmentWest")) : LayoutEngine
					.convertClassNameToLayoutName("SequenceAttachmentWest");
			System.out.println(">>>> TEST LAYOUT: test classname conversion and back: Page200");
			assert "Page200".equals(LayoutEngine.convertLayoutNameToClassName("page-200")) : LayoutEngine.convertLayoutNameToClassName("page-200");
			assert "page-200".equals(LayoutEngine.convertClassNameToLayoutName("Page200")) : LayoutEngine.convertClassNameToLayoutName("Page200");
		}

		{
			System.out.println(">>>> TEST LAYOUT: primitive equvalence");
			final JsonTargetContext context = new JsonTargetContext((TargetInterface) null, NativeTargetContext.TargetMode.DUMB);
			for (final String[] source : new String[][]{
					{
							"3.14", "{ layout: 'number', value : 3.14 }"
					}, {
							"3.14", "{ layout: 'number', value : '3.14' }"
					}, {
							"true", "{ layout: 'boolean', value : true }"
					}, {
							"true", "{ layout: 'boolean', value : 'true' }"
					}, {
							"'abc'", "{ layout: 'string', value : 'abc' }"
					},
			}) {
				final BaseObject[] test = new BaseObject[]{
						LayoutEngine.parseJSLD(source[0]), LayoutEngine.parseJSLD(source[1])
				};
				context.transform(test[0]);
				final BaseObject result1 = context.getResultLayout();
				context.transform(test[1]);
				final BaseObject result2 = context.getResultLayout();
				assert result1.equals(result2) : "Have to be equal: " + Ecma.toEcmaSourceCompact(result1) + " != " + Ecma.toEcmaSourceCompact(result2);
			}
		}
	}
}
