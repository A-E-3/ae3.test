package ru.myx.ae3.test.s4;

import ru.myx.ae3.binary.Transfer;
import ru.myx.ae3.binary.TransferCopier;
import ru.myx.ae3.know.Guid;
import ru.myx.ae3.test.TestAe3;
import ru.myx.ae3.vfs.s4.lcl.S4DriverLocalAbstract;

/**
 *
 * @author myx
 *
 */
public final class TestS4 {
	
	
	/**
	 *
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		
		
		TestAe3.initAE3();
		System.out.println(">>>> TEST S4");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		for (final S4Variants variant : S4Variants.values()) {
			System.out.println(">>>> >>>> S4 LOCAL: " + variant);
			final S4DriverLocalAbstract driver = variant.makeRoot();
			System.out.println(">>>> >>>> S4LocalDriver: " + driver);
			System.out.println(">>>> >>>> >>>> starting");
			driver.start();

			System.out.println(">>>> >>>> >>>> root reference");
			{
				assert driver.getRootReference() != null : "NULL root reference";
			}

			System.out.println(">>>> >>>> >>>> binary template");
			for (final TransferCopier copier : new TransferCopier[]{
					TransferCopier.NUL_COPIER, //
					Transfer.createCopier("check".getBytes()), //
					Transfer.createCopier("check-check-check-check-check-check-check-check-".getBytes()), //
			}) {
				assert driver.createBinaryTemplate(copier) != null : "NULL container template!";
			}

			System.out.println(">>>> >>>> >>>> container template");
			{
				assert driver.createContainerTemplate() != null : "NULL container template!";
			}

			System.out.println(">>>> >>>> >>>> primitive template");
			for (final Guid guid : new Guid[]{
					Guid.GUID_NULL, //
					Guid.GUID_BYTES_EMPTY, //
					Guid.GUID_NUMBER_ZERO, //
			}) {
				assert driver.createPrimitiveTemplate(guid) != null : "NULL container template!";
			}

			System.out.println(">>>> >>>> >>>> stopping: " + driver);
			
			driver.stop();
			Thread.yield();
			System.runFinalization();
			Thread.yield();
			System.gc();
			Thread.yield();
			System.runFinalization();
			Thread.yield();
		}
	}
}
