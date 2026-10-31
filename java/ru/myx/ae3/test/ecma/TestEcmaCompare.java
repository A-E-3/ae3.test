package ru.myx.ae3.test.ecma;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseDate;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.ecma.compare.ComparatorEcma;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.java.compare.ComparatorJava;

class TestEcmaCompare {

	/** @param args
	 * @throws Throwable */
	public static void main(final String[] args) throws Throwable {

		Engine.createGuid();
		System.out.println(">>>> >>>> TEST Ecma.EcmaCompare");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{ // from boot thread: before memory printout
			final ExecProcess ctx = Exec.createProcess(null, "Evaluate test");
			if (Evaluate.evaluateObject("100-21/7 == 97", ctx, null) == BaseObject.FALSE) {
				throw new Error("BOOT: EVAL CHECK: FAIL, (100-21/7 != 97), actually evaluates to: " + Evaluate.evaluateObject("100-21/7", ctx, null));
			}
		}
		{
			for (final Object o : new Object[]{
					null, Boolean.FALSE, new Boolean(false), BaseObject.FALSE, BaseObject.UNDEFINED, BaseObject.NULL, BasePrimitiveNumber.ZERO, BaseString.EMPTY,
					//
			}) {
				System.out.println(
						"\t\tcheck FALSE, class=" + (o == null
							? "null"
							: o.getClass().getName()));
				assert !Ecma.ecmaToBoolean(o);
				assert !Base.forUnknown(o).baseToJavaBoolean();
				assert Base.forUnknown(o).baseToBoolean().booleanValueXorTrue();
			}
		}
		{
			assert !Base.compareEQU(null, BasePrimitiveNumber.NAN);
			assert !BaseObject.equalsNative(BaseObject.UNDEFINED, BasePrimitiveNumber.NAN);
			assert !BaseObject.equalsNative(BaseObject.NULL, BasePrimitiveNumber.NAN);
			assert !Base.compareEQU(BasePrimitiveNumber.NAN, null);
			assert !BaseObject.equalsNative(BasePrimitiveNumber.NAN, BaseObject.UNDEFINED);
			assert !BaseObject.equalsNative(BasePrimitiveNumber.NAN, BaseObject.NULL);
		}
		final ComparatorEcma comparator = ComparatorEcma.INSTANCE;
		{
			assert comparator.compare((Object) null, BaseObject.NULL) == 0;
			assert comparator.compare((Object) null, BaseObject.UNDEFINED) == 0;
			assert comparator.compare(BaseObject.NULL, (Object) null) == 0;
			assert comparator.compare(BaseObject.UNDEFINED, (Object) null) == 0;
			assert comparator.compare(Boolean.TRUE, BaseObject.TRUE) == 0;
			assert comparator.compare(Boolean.FALSE, BaseObject.FALSE) == 0;
			assert comparator.compare((Object) BaseObject.FALSE, BaseObject.FALSE) == 0;
			assert comparator.compare(BaseObject.TRUE, Boolean.TRUE) == 0;
			assert comparator.compare(BaseObject.FALSE, Boolean.FALSE) == 0;
			assert comparator.compare(Boolean.TRUE, Boolean.FALSE) != 0;
			assert comparator.compare(BaseObject.TRUE, Boolean.FALSE) != 0;
			assert comparator.compare(Boolean.TRUE, BaseObject.FALSE) != 0;
			assert comparator.compare((Object) BaseObject.TRUE, BaseObject.FALSE) != 0;
		}
		{
			for (final Object o : new Object[]{
					Boolean.TRUE, new Boolean(true), BaseObject.TRUE, BasePrimitiveNumber.ONE,
					//
			}) {
				System.out.println(
						"\t\tcheck TRUE, class=" + (o == null
							? "null"
							: o.getClass().getName()));
				assert Ecma.ecmaToBoolean(o);
				assert Base.forUnknown(o).baseToJavaBoolean();
				assert !Base.forUnknown(o).baseToBoolean().booleanValueXorTrue();

				assert ComparatorEcma.compareEQU(o, Boolean.TRUE);
				assert ComparatorEcma.compareEQU(o, BaseObject.TRUE);
				assert BaseObject.equalsNative(Base.forUnknown(o), BaseObject.TRUE);
				assert ComparatorEcma.compareEQU(o, BasePrimitiveNumber.ONE);
				assert BaseObject.equalsNative(Base.forUnknown(o), BasePrimitiveNumber.ONE);

				assert !ComparatorEcma.compareEQU(o, null);
				assert comparator.compare(o, null) != 0;
				assert !ComparatorEcma.compareEQU(o, Boolean.FALSE);
				assert comparator.compare(o, BaseObject.FALSE) != 0;
				assert !ComparatorEcma.compareEQU(o, BaseObject.FALSE);
				assert ComparatorEcma.compare(Base.forUnknown(o), BaseObject.FALSE) != 0;
				assert !BaseObject.equalsNative(Base.forUnknown(o), BaseObject.FALSE);
				assert comparator.compare(o, BaseObject.UNDEFINED) != 0;
				assert !ComparatorEcma.compareEQU(o, BaseObject.UNDEFINED);
				assert ComparatorEcma.compare(Base.forUnknown(o), BaseObject.UNDEFINED) != 0;
				assert !BaseObject.equalsNative(Base.forUnknown(o), BaseObject.UNDEFINED);
				assert comparator.compare(o, BaseObject.NULL) != 0;
				assert !ComparatorEcma.compareEQU(o, BaseObject.NULL);
				assert ComparatorEcma.compare(Base.forUnknown(o), BaseObject.NULL) != 0;
				assert !BaseObject.equalsNative(Base.forUnknown(o), BaseObject.NULL);
				assert comparator.compare(o, BasePrimitiveNumber.ZERO) != 0;
				assert !ComparatorEcma.compareEQU(o, BasePrimitiveNumber.ZERO);
				assert ComparatorEcma.compare(Base.forUnknown(o), BasePrimitiveNumber.ZERO) != 0;
				assert !BaseObject.equalsNative(Base.forUnknown(o), BasePrimitiveNumber.ZERO) : "o=" + o;
				assert comparator.compare(o, BaseString.EMPTY) != 0;
				assert !ComparatorEcma.compareEQU(o, BaseString.EMPTY);
				assert ComparatorEcma.compare(Base.forUnknown(o), BaseString.EMPTY) != 0;
				assert !Base.compareEQU(Base.forUnknown(o), BaseString.EMPTY);
				assert !BaseObject.equalsNative(Base.forUnknown(o), BaseString.EMPTY);
			}
		}
		{
			for (final Object o : new Object[]{
					Boolean.FALSE, new Boolean(false), BaseObject.FALSE, BasePrimitiveNumber.ZERO, BaseString.EMPTY,
					//
			}) {
				System.out.println(
						"\t\tcheck FALSE, class=" + (o == null
							? "null"
							: o.getClass().getName()));
				assert !Ecma.ecmaToBoolean(o);
				assert !Base.forUnknown(o).baseToJavaBoolean();

				assert !ComparatorEcma.compareEQU(o, Boolean.TRUE);
				assert !ComparatorEcma.compareEQU(o, BaseObject.TRUE);
				assert !BaseObject.equalsNative(Base.forUnknown(o), BaseObject.TRUE);
				assert !ComparatorEcma.compareEQU(o, BasePrimitiveNumber.ONE);
				assert !BaseObject.equalsNative(Base.forUnknown(o), BasePrimitiveNumber.ONE);
				assert !ComparatorEcma.compareEQU(o, BaseString.STR_FALSE);
				assert !Base.compareEQU(Base.forUnknown(o), BaseString.STR_FALSE);

				assert ComparatorEcma.compareEQU(o, Boolean.FALSE);
				assert ComparatorEcma.compareEQU(o, BaseObject.FALSE);
				assert BaseObject.equalsNative(Base.forUnknown(o), BaseObject.FALSE);
				assert ComparatorEcma.compareEQU(o, BasePrimitiveNumber.ZERO);
				assert BaseObject.equalsNative(Base.forUnknown(o), BasePrimitiveNumber.ZERO);
				assert ComparatorEcma.compareEQU(o, BaseString.EMPTY);
				assert Base.compareEQU(Base.forUnknown(o), BaseString.EMPTY);
			}
		}
		{
			for (final Object o : new Object[]{
					//
					null,
					//
					BaseObject.UNDEFINED,
					//
					BaseObject.NULL,
					//
			}) {
				System.out.println(
						"\t\tcheck FALSE, class=" + (o == null
							? "null"
							: o.getClass().getName()));
				assert !Ecma.ecmaToBoolean(o);

				assert ComparatorEcma.compareEQU(o, null);
				assert ComparatorEcma.compareEQU(o, BaseObject.UNDEFINED);
				assert BaseObject.equalsNative(Base.forUnknown(o), BaseObject.UNDEFINED);
				assert ComparatorEcma.compareEQU(o, BaseObject.NULL);
				assert BaseObject.equalsNative(Base.forUnknown(o), BaseObject.NULL);
			}
		}
		{
			for (final Object[] o : new Object[][]{
					//
					new Object[]{
							BasePrimitiveNumber.ZERO, Base.forInteger(-1)
					}, new Object[]{
							Integer.valueOf(0), Base.forInteger(-1)
					}, new Object[]{
							Long.valueOf(0), Base.forInteger(-1)
					}, new Object[]{
							Double.valueOf(0), Base.forInteger(-1)
					}, new Object[]{
							BasePrimitiveNumber.ZERO, Integer.valueOf(-1)
					}, new Object[]{
							Integer.valueOf(0), Integer.valueOf(-1)
					}, new Object[]{
							Long.valueOf(0), Integer.valueOf(-1)
					}, new Object[]{
							Double.valueOf(0), Integer.valueOf(-1)
					},
					//
					new Object[]{
							BasePrimitiveNumber.ONE, Base.forInteger(-1)
					}, new Object[]{
							Integer.valueOf(1), Base.forInteger(-1)
					}, new Object[]{
							Long.valueOf(1), Base.forInteger(-1)
					}, new Object[]{
							Double.valueOf(1), Base.forInteger(-1)
					}, new Object[]{
							BasePrimitiveNumber.ONE, Integer.valueOf(-1)
					}, new Object[]{
							Integer.valueOf(1), Integer.valueOf(-1)
					}, new Object[]{
							Long.valueOf(1), Integer.valueOf(-1)
					}, new Object[]{
							Double.valueOf(1), Integer.valueOf(-1)
					},
					//
					new Object[]{
							BasePrimitiveNumber.ONE, BasePrimitiveNumber.ZERO
					}, new Object[]{
							Integer.valueOf(1), Base.forInteger(0)
					}, new Object[]{
							Long.valueOf(1), Base.forInteger(0)
					}, new Object[]{
							Double.valueOf(1), Base.forInteger(0)
					}, new Object[]{
							BasePrimitiveNumber.ONE, Integer.valueOf(0)
					}, new Object[]{
							Integer.valueOf(1), Integer.valueOf(0)
					}, new Object[]{
							Long.valueOf(1), Integer.valueOf(0)
					}, new Object[]{
							Double.valueOf(1), Integer.valueOf(0)
					},

					new Object[]{
							Long.valueOf(2), Long.valueOf(1)
					}, new Object[]{
							Long.valueOf(3), Long.valueOf(1)
					},
					//
			}) {
				final Object x = o[0], y = o[1];
				System.out.println(
						"\t\tcheck MORE, class1=" + (x == null
							? "null"
							: x.getClass().getName()) + ", class1="
								+ (y == null
									? "null"
									: y.getClass().getName()));
				{
					/** forward */
					assert ComparatorEcma.compareMORE(x, y) : "Wow! Forward. ";
					assert ComparatorEcma.compareNLESS(x, y) : "Wow! Forward. ";
					assert !ComparatorEcma.compareLESS(x, y) : "Wow! Forward. ";
					assert !ComparatorEcma.compareNMORE(x, y) : "Wow! Forward. ";
					assert !ComparatorEcma.compareEQU(x, y) : "Wow! Forward. ";
					assert comparator.compare(x, y) == 1 : "Wow! Forward: " + comparator.compare(x, y);

					/** reverse */
					assert ComparatorEcma.compareLESS(y, x) : "Wow! Reverse. ";
					assert ComparatorEcma.compareNMORE(y, x) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareMORE(y, x) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareNLESS(y, x) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareEQU(y, x) : "Wow! Reverse. ";
					assert comparator.compare(y, x) == -1 : "Wow! Reverse: " + comparator.compare(y, x);
				}

				/** both base */
				{
					final BaseObject bx = Base.forUnknown(x);
					final BaseObject by = Base.forUnknown(y);
					/** forward base */
					assert ComparatorEcma.compareMORE(bx, by) : "Wow! Forward. bxc=" + bx.getClass().getName() + ", byc=" + by.getClass().getName();
					assert ComparatorEcma.compareNLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareNMORE(bx, by) : "Wow! Forward. ";
					assert !BaseObject.equalsNative(bx, by) : "Wow! Forward. ";
					assert ComparatorEcma.compare(bx, by) == 1 : "Wow! Forward: " + ComparatorEcma.compare(bx, by);

					/** reverse base */
					assert ComparatorEcma.compareLESS(by, bx) : "Wow! Reverse. ";
					assert ComparatorEcma.compareNMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareNLESS(by, bx) : "Wow! Reverse. ";
					assert !BaseObject.equalsNative(by, bx) : "Wow! Reverse. ";
					assert ComparatorEcma.compare(by, bx) == -1 : "Wow! Reverse: " + ComparatorEcma.compare(by, bx);
				}

				/** left base */
				{
					final BaseObject bx = Base.forUnknown(x);
					final Object by = y;
					/** forward base */
					assert ComparatorEcma.compareMORE(bx, by) : "Wow! Forward. ";
					assert ComparatorEcma.compareNLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareNMORE(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareEQU(bx, by) : "Wow! Forward. ";
					assert comparator.compare(bx, by) == 1 : "Wow! Forward: " + comparator.compare(bx, by);

					/** reverse base */
					assert ComparatorEcma.compareLESS(by, bx) : "Wow! Reverse. ";
					assert ComparatorEcma.compareNMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareNLESS(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareEQU(by, bx) : "Wow! Reverse. ";
					assert comparator.compare(by, bx) == -1 : "Wow! Reverse: " + comparator.compare(by, bx);
				}

				/** right base */
				{
					final Object bx = x;
					final BaseObject by = Base.forUnknown(y);
					/** forward base */
					assert ComparatorEcma.compareMORE(bx, by) : "Wow! Forward. ";
					assert ComparatorEcma.compareNLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareNMORE(bx, by) : "Wow! Forward. ";
					assert !ComparatorEcma.compareEQU(bx, by) : "Wow! Forward. ";
					assert comparator.compare(bx, by) == 1 : "Wow! Forward: " + comparator.compare(bx, by);

					/** reverse base */
					assert ComparatorEcma.compareLESS(by, bx) : "Wow! Reverse. ";
					assert ComparatorEcma.compareNMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareNLESS(by, bx) : "Wow! Reverse. ";
					assert !ComparatorEcma.compareEQU(by, bx) : "Wow! Reverse. ";
					assert comparator.compare(by, bx) == -1 : "Wow! Reverse: " + comparator.compare(by, bx);
				}
			}
		}
		{
			/** DATES */
			System.out.println("\t\tcheck DATES");
			{
				/** equal dates */
				final BaseObject o1 = new BaseDate(1000);
				final BaseObject o2 = new BaseDate(1000);

				/** TODO: kind of needed in actual scripts, so the cheat is exactly to use java
				 * 'equals' method for objects of the same class. */
				// assert !Base.compareEQU( o1, o2 ) //
				// : "Same objects are still not equal in ecma";
				assert ComparatorJava.compareEQU(o1, o2) : "Same objects are equal in java though";

				assert ComparatorEcma.compare(o1, o2) == 0;
				assert ComparatorEcma.compare(o2, o1) == 0;
			}
			{
				/** equal date & number */
				final BaseObject o1 = new BaseDate(1000);
				final BaseObject o2 = Base.forInteger(1000);

				assert BaseObject.equalsNative(o1, o2) : "Same objects are equal in ecma when do non-EQU comparison (at least dates)";
				assert !ComparatorJava.compareEQU(o1, o2) : "Date and Integer objects are not equal in java though";

				assert ComparatorEcma.compare(o1, o2) == 0 : "o1=" + o1 + ", o2=" + o2;
				assert ComparatorEcma.compare(o2, o1) == 0 : "o1=" + o1 + ", o2=" + o2;
			}
			{
				/** different dates */
				final BaseObject o1 = new BaseDate(1000);
				final BaseObject o2 = new BaseDate(2000);

				assert !BaseObject.equalsNative(o1, o2) : "Evan same objects are still not equal in ecma";
				assert !ComparatorJava.compareEQU(o1, o2) : "Different objects are not equal in java though";

				assert ComparatorEcma.compare(o1, o2) < 0;
				assert ComparatorEcma.compare(o2, o1) > 0;
			}
			{
				/** different date & number */
				final BaseObject o1 = new BaseDate(1000);
				final BaseObject o2 = Base.forInteger(2000);

				assert !BaseObject.equalsNative(o1, o2) : "Evan same objects are still not equal in ecma";
				assert !ComparatorJava.compareEQU(o1, o2) : "Different objects are not equal in java though";

				assert ComparatorEcma.compare(o1, o2) < 0;
				assert ComparatorEcma.compare(o2, o1) > 0;
			}
		}
		{
			/** ???? */

		}
	}
}
