package ru.myx.ae3.test;

import java.io.File;

import ru.myx.ae3.Engine;
import ru.myx.ae3.report.Report;
import ru.myx.ae3.system.ImplAE3;
import ru.myx.ae3.test.act.TestAct;
import ru.myx.ae3.test.answer.TestAnswer;
import ru.myx.ae3.test.auth.TestAuth;
import ru.myx.ae3.test.base.TestBase;
import ru.myx.ae3.test.binary.TestBinary;
import ru.myx.ae3.test.ecma.TestEcma;
import ru.myx.ae3.test.engine.TestEngineGuidEntropy;
import ru.myx.ae3.test.engine.TestEngineRandomEntropy;
import ru.myx.ae3.test.eval.TestEval;
import ru.myx.ae3.test.exec.TestExec;
import ru.myx.ae3.test.flow.TestFlow;
import ru.myx.ae3.test.help.TestHelp;
import ru.myx.ae3.test.io.TestIo;
import ru.myx.ae3.test.java.TestJava;
import ru.myx.ae3.test.know.TestKnow;
import ru.myx.ae3.test.layout.TestLayout;
import ru.myx.ae3.test.mime.TestMime;
import ru.myx.ae3.test.produce.TestProduce;
import ru.myx.ae3.test.reflect.TestReflect;
import ru.myx.ae3.test.s4.TestS4;
import ru.myx.ae3.test.transform.TestTransform;
import ru.myx.ae3.test.util.TestUtil;
import ru.myx.ae3.test.vfs.TestVfs;
import ru.myx.ae3.test.xml.TestXml;

/**
 * @author myx
 *
 */
public class TestAe3 {

	/**
	 *
	 */
	public static void initAE3() {

		if (System.getProperty("ae3", "").length() == 0) {
			System.setProperty("ae3", "initialized");

			System.setProperty("ru.myx.ae3.properties.vfs.storage", System.getProperty("ru.myx.ae3.properties.vfs.storage", "ramfs:storage"));

			final File userDir = new File(System.getProperty("user.dir"));
			final File pathPublic = new File(userDir.getParentFile(), "acm-cvs/sys-current");
			assert pathPublic.exists() : "public doesn't exist: " + pathPublic.getAbsolutePath();
			assert new File(pathPublic, "axiom").exists() : "public/axiom doesn't exist: public=" + pathPublic.getAbsolutePath();
			System.setProperty("ru.myx.ae3.properties.path.public", pathPublic.getAbsolutePath());
			
			final File pathProtected = new File(userDir.getParentFile(), "ae3-test-protected");
			System.setProperty("ru.myx.ae3.properties.path.protected", pathProtected.getAbsolutePath());
			
			final File pathPrivate = new File(userDir.getParentFile(), "ae3-test-private");
			System.setProperty("ru.myx.ae3.properties.path.private", pathPrivate.getAbsolutePath());
			
			final File pathShared = new File(userDir.getParentFile(), "unit-test/!shared");
			System.setProperty("ru.myx.ae3.properties.path.shared", pathShared.getAbsolutePath());
			
			System.setProperty("ru.myx.ae3.properties.log.level", "DEBUG");
			
			Engine.createGuid();
			System.out.println("public:    " + Engine.PATH_PUBLIC);
			System.out.println("protected: " + Engine.PATH_PROTECTED);
			System.out.println("private:   " + Engine.PATH_PRIVATE);
			System.out.println("shared:    " + Engine.PATH_SHARED);
			System.out.println("level: " + Report.LEVEL_NAME);
			
			ImplAE3.init();
		}
	}
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String... args) throws Throwable {

		TestAe3.message("AE3 TEST STARTED!!!");
		
		TestAe3.initAE3();
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		/**
		 * basic
		 */
		TestBase.main(new String[0]);
		TestEngineRandomEntropy.main(new String[0]);
		TestEngineGuidEntropy.main(new String[0]);
		TestAct.main(new String[0]);
		TestBinary.main(new String[0]);
		TestIo.main(new String[0]);
		TestUtil.main(new String[0]);
		TestHelp.main(new String[0]);
		TestKnow.main(new String[0]);
		TestAnswer.main(new String[0]);
		TestAuth.main(new String[0]);
		TestReflect.main(new String[0]);
		TestExec.main(new String[0]);
		TestJava.main(new String[0]);
		
		TestS4.main(new String[0]);
		
		TestVfs.main(new String[0]);
		
		TestFlow.main(new String[0]);
		TestProduce.main(new String[0]);
		TestMime.main(new String[0]);
		TestTransform.main(new String[0]);
		TestXml.main(new String[0]);
		TestEval.main(new String[0]);
		TestEcma.main(new String[0]);
		TestLayout.main(new String[0]);
		
		TestAe3.message("AE3 TEST PASSED!!!");
	}
	
	/**
	 * @param message
	 */
	public static final void message(final String message) {

		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("## ");
			System.out.println("## ");
			System.out.println("## ");
			System.out.println("## ");
			System.out.println("## " + message);
			System.out.println("## ");
			System.out.println("## ");
			System.out.println("## ");
			System.out.println("## ");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println("#######################################################################");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}
