package ru.myx.ae3.test.java;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseString;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.eval.Evaluate;
import ru.myx.ae3.exec.Exec;
import ru.myx.ae3.exec.ExecProcess;
import ru.myx.ae3.java.compare.ComparatorJava;

/**
 * 
 * @author myx
 * 
 */
class TestJavaCompare {
	
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> >>>> TEST Java.JavaCompare");
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
				System.out.println("\t\tcheck FALSE, class=" + (o == null
					? "null"
					: o.getClass().getName()));
				assert !Ecma.ecmaToBoolean(o);
				assert !Base.forUnknown(o).baseToJavaBoolean();
			}
		}
		{
			assert !ComparatorJava.compareEQU(null, BasePrimitiveNumber.NAN);
			assert !ComparatorJava.compareEQU(BaseObject.UNDEFINED, BasePrimitiveNumber.NAN);
			assert !ComparatorJava.compareEQU(BaseObject.NULL, BasePrimitiveNumber.NAN);
			assert !ComparatorJava.compareEQU(BasePrimitiveNumber.NAN, null);
			assert !ComparatorJava.compareEQU(BasePrimitiveNumber.NAN, BaseObject.UNDEFINED);
			assert !ComparatorJava.compareEQU(BasePrimitiveNumber.NAN, BaseObject.NULL);
		}
		final ComparatorJava comparator = ComparatorJava.INSTANCE;
		{
			assert comparator.compare(null, BaseObject.NULL) == 0;
			assert comparator.compare(null, BaseObject.UNDEFINED) == 0;
			assert comparator.compare(BaseObject.NULL, null) == 0;
			assert comparator.compare(BaseObject.UNDEFINED, null) == 0;
			assert comparator.compare(Boolean.TRUE, BaseObject.TRUE) == 0;
			assert comparator.compare(Boolean.FALSE, BaseObject.FALSE) == 0;
			assert comparator.compare(BaseObject.FALSE, BaseObject.FALSE) == 0;
			assert comparator.compare(BaseObject.TRUE, Boolean.TRUE) == 0;
			assert comparator.compare(BaseObject.FALSE, Boolean.FALSE) == 0;
			assert comparator.compare(Boolean.TRUE, Boolean.FALSE) != 0;
			assert comparator.compare(BaseObject.TRUE, Boolean.FALSE) != 0;
			assert comparator.compare(Boolean.TRUE, BaseObject.FALSE) != 0;
			assert comparator.compare(BaseObject.TRUE, BaseObject.FALSE) != 0;
		}
		{
			for (final Object o : new Object[]{
					Boolean.TRUE, new Boolean(true), BaseObject.TRUE, BasePrimitiveNumber.ONE,
					//
			}) {
				System.out.println("\t\tcheck TRUE, class=" + (o == null
					? "null"
					: o.getClass().getName()));
				assert Ecma.ecmaToBoolean(o);
				assert Base.forUnknown(o).baseToJavaBoolean();
				
				assert ComparatorJava.compareEQU(o, Boolean.TRUE);
				assert ComparatorJava.compareEQU(o, BaseObject.TRUE);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.TRUE);
				assert ComparatorJava.compareEQU(o, BasePrimitiveNumber.ONE);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BasePrimitiveNumber.ONE);
				
				assert !ComparatorJava.compareEQU(o, null);
				assert comparator.compare(o, null) != 0;
				assert !ComparatorJava.compareEQU(o, Boolean.FALSE);
				assert comparator.compare(o, BaseObject.FALSE) != 0;
				assert !ComparatorJava.compareEQU(o, BaseObject.FALSE);
				assert comparator.compare(Base.forUnknown(o), BaseObject.FALSE) != 0;
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.FALSE);
				assert comparator.compare(o, BaseObject.UNDEFINED) != 0;
				assert !ComparatorJava.compareEQU(o, BaseObject.UNDEFINED);
				assert comparator.compare(Base.forUnknown(o), BaseObject.UNDEFINED) != 0;
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.UNDEFINED);
				assert comparator.compare(o, BaseObject.NULL) != 0;
				assert !ComparatorJava.compareEQU(o, BaseObject.NULL);
				assert comparator.compare(Base.forUnknown(o), BaseObject.NULL) != 0;
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.NULL);
				assert comparator.compare(o, BasePrimitiveNumber.ZERO) != 0;
				assert !ComparatorJava.compareEQU(o, BasePrimitiveNumber.ZERO);
				assert comparator.compare(Base.forUnknown(o), BasePrimitiveNumber.ZERO) != 0;
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BasePrimitiveNumber.ZERO);
				assert comparator.compare(o, BaseString.EMPTY) != 0;
				assert !ComparatorJava.compareEQU(o, BaseString.EMPTY);
				assert comparator.compare(Base.forUnknown(o), BaseString.EMPTY) != 0;
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BaseString.EMPTY);
			}
		}
		{
			for (final Object o : new Object[]{
					Boolean.FALSE, new Boolean(false), BaseObject.FALSE, BasePrimitiveNumber.ZERO, BaseString.EMPTY,
					//
			}) {
				System.out.println("\t\tcheck FALSE, class=" + (o == null
					? "null"
					: o.getClass().getName()));
				assert !Ecma.ecmaToBoolean(o);
				assert !Base.forUnknown(o).baseToJavaBoolean();
				
				assert !ComparatorJava.compareEQU(o, Boolean.TRUE);
				assert !ComparatorJava.compareEQU(o, BaseObject.TRUE);
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.TRUE);
				assert !ComparatorJava.compareEQU(o, BasePrimitiveNumber.ONE);
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BasePrimitiveNumber.ONE);
				assert !ComparatorJava.compareEQU(o, BaseString.STR_FALSE);
				assert !ComparatorJava.compareEQU(Base.forUnknown(o), BaseString.STR_FALSE);
				
				assert ComparatorJava.compareEQU(o, Boolean.FALSE);
				assert ComparatorJava.compareEQU(o, BaseObject.FALSE);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.FALSE);
				assert ComparatorJava.compareEQU(o, BasePrimitiveNumber.ZERO);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BasePrimitiveNumber.ZERO);
				assert ComparatorJava.compareEQU(o, BaseString.EMPTY);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BaseString.EMPTY);
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
				System.out.println("\t\tcheck FALSE, class=" + (o == null
					? "null"
					: o.getClass().getName()));
				assert !Ecma.ecmaToBoolean(o);
				
				assert ComparatorJava.compareEQU(o, null);
				assert ComparatorJava.compareEQU(o, BaseObject.UNDEFINED);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.UNDEFINED);
				assert ComparatorJava.compareEQU(o, BaseObject.NULL);
				assert ComparatorJava.compareEQU(Base.forUnknown(o), BaseObject.NULL);
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
					//
			}) {
				final Object x = o[0], y = o[1];
				System.out.println("\t\tcheck MORE, class1=" + (x == null
					? "null"
					: x.getClass().getName()) + ", class1="
						+ (y == null
							? "null"
							: y.getClass().getName()));
				{
					/**
					 * forward
					 */
					assert ComparatorJava.compareMORE(x, y) : "Wow! Forward. ";
					assert ComparatorJava.compareNLESS(x, y) : "Wow! Forward. ";
					assert !ComparatorJava.compareLESS(x, y) : "Wow! Forward. ";
					assert !ComparatorJava.compareNMORE(x, y) : "Wow! Forward. ";
					assert !ComparatorJava.compareEQU(x, y) : "Wow! Forward. ";
					assert comparator.compare(x, y) == 1 : "Wow! Forward: " + comparator.compare(x, y);
					
					/**
					 * reverse
					 */
					assert ComparatorJava.compareLESS(y, x) : "Wow! Reverse. ";
					assert ComparatorJava.compareNMORE(y, x) : "Wow! Reverse. ";
					assert !ComparatorJava.compareMORE(y, x) : "Wow! Reverse. ";
					assert !ComparatorJava.compareNLESS(y, x) : "Wow! Reverse. ";
					assert !ComparatorJava.compareEQU(y, x) : "Wow! Reverse. ";
					assert comparator.compare(y, x) == -1 : "Wow! Reverse: " + comparator.compare(y, x);
				}
				
				/**
				 * both base
				 */
				{
					final BaseObject bx = Base.forUnknown(x);
					final BaseObject by = Base.forUnknown(y);
					/**
					 * forward base
					 */
					assert ComparatorJava.compareMORE(bx, by) : "Wow! Forward. ";
					assert ComparatorJava.compareNLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareNMORE(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareEQU(bx, by) : "Wow! Forward. ";
					assert comparator.compare(bx, by) == 1 : "Wow! Forward: " + comparator.compare(bx, by);
					
					/**
					 * reverse base
					 */
					assert ComparatorJava.compareLESS(by, bx) : "Wow! Reverse. ";
					assert ComparatorJava.compareNMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareNLESS(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareEQU(by, bx) : "Wow! Reverse. ";
					assert comparator.compare(by, bx) == -1 : "Wow! Reverse: " + comparator.compare(by, bx);
				}
				
				/**
				 * left base
				 */
				{
					final BaseObject bx = Base.forUnknown(x);
					final Object by = y;
					/**
					 * forward base
					 */
					assert ComparatorJava.compareMORE(bx, by) : "Wow! Forward. ";
					assert ComparatorJava.compareNLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareNMORE(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareEQU(bx, by) : "Wow! Forward. ";
					assert comparator.compare(bx, by) == 1 : "Wow! Forward: " + comparator.compare(bx, by);
					
					/**
					 * reverse base
					 */
					assert ComparatorJava.compareLESS(by, bx) : "Wow! Reverse. ";
					assert ComparatorJava.compareNMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareNLESS(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareEQU(by, bx) : "Wow! Reverse. ";
					assert comparator.compare(by, bx) == -1 : "Wow! Reverse: " + comparator.compare(by, bx);
				}
				
				/**
				 * right base
				 */
				{
					final Object bx = x;
					final BaseObject by = Base.forUnknown(y);
					/**
					 * forward base
					 */
					assert ComparatorJava.compareMORE(bx, by) : "Wow! Forward. ";
					assert ComparatorJava.compareNLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareLESS(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareNMORE(bx, by) : "Wow! Forward. ";
					assert !ComparatorJava.compareEQU(bx, by) : "Wow! Forward. ";
					assert comparator.compare(bx, by) == 1 : "Wow! Forward: " + comparator.compare(bx, by);
					
					/**
					 * reverse base
					 */
					assert ComparatorJava.compareLESS(by, bx) : "Wow! Reverse. ";
					assert ComparatorJava.compareNMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareMORE(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareNLESS(by, bx) : "Wow! Reverse. ";
					assert !ComparatorJava.compareEQU(by, bx) : "Wow! Reverse. ";
					assert comparator.compare(by, bx) == -1 : "Wow! Reverse: " + comparator.compare(by, bx);
				}
			}
		}
	}
}
