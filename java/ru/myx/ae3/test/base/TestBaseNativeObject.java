package ru.myx.ae3.test.base;

import java.util.Iterator;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BasePrimitiveNumber;
import ru.myx.ae3.base.BaseProperty;
import ru.myx.ae3.base.BaseString;

/**
 * 
 * @author myx
 * 		
 */
class TestBaseNativeObject {
	
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		
		Engine.createGuid();
		System.out.println(">>>> TEST BASE NATIVE OBJECT");
		{
			try {
				assert false;
				throw new RuntimeException("assertions required for this test!");
			} catch (final AssertionError t) {
				// ignore
			}
		}
		for (final BaseObject object : new BaseObject[]{
				new BaseNativeObject(), new BaseNativeObject(null)
		}) {
			assert !object.baseHasKeysOwn();
			assert !object.baseKeysOwn().hasNext();
			assert !object.baseKeysOwnPrimitive().hasNext();
			
			assert object.baseGet("java333", BaseObject.UNDEFINED) == BaseObject.UNDEFINED : "Should not provide non-existent methods!";
			assert !Base.hasProperty(object, "java333") : "Should not provide non-existent methods!";
			final BaseObject o2 = new BaseNativeObject(object.basePrototype());
			assert object.equals(o2) : "Should be equal";
			
			final String key = "key1";
			object.baseDefine(key, BaseObject.TRUE);
			assert !object.equals(o2) : "Should be NOT equal";
			o2.baseDefine(key, BaseObject.TRUE);
			assert object.equals(o2) : "Should be equal";
		}
		{
			final String[] keys = {
					"a", "b", "c", "d", "e"
			};
			final BaseObject object = new BaseNativeObject();
			assert !object.baseHasKeysOwn();
			assert !object.baseKeysOwn().hasNext();
			assert !object.baseKeysOwnPrimitive().hasNext();
			
			/**
			 * pre-check
			 */
			for (final String key : keys) {
				assert object.baseGet(key, BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				assert object.baseGet(key, null) == null;
				assert object.baseGet(Base.forString(key), BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
				assert object.baseGet(Base.forString(key), null) == null;
			}
			
			/**
			 * put some
			 */
			for (final String key : keys) {
				object.baseDefine(key, BaseString.EMPTY);
				assert object.baseGet(key, BaseObject.UNDEFINED) == BaseString.EMPTY;
				assert object.baseGet(key, null) == BaseString.EMPTY;
				assert object.baseGet(Base.forString(key), BaseObject.UNDEFINED) == BaseString.EMPTY;
				assert object.baseGet(Base.forString(key), null) == BaseString.EMPTY;
			}
			
			/**
			 * delete check
			 */
			for (final String key : keys) {
				object.baseDelete(key);
				assert object.baseGet(key, BaseObject.UNDEFINED) == BaseObject.UNDEFINED : "key: " + key;
				assert object.baseGet(key, BaseObject.UNDEFINED) == BaseObject.UNDEFINED : "key: " + key;
				assert object.baseGet(key, null) == null;
			}
			
			/**
			 * reverse order, just for sure
			 */
			for (final String key : keys) {
				object.baseDefine(key, BaseString.STR_FALSE);
				assert object.baseGet(key, BaseObject.UNDEFINED) == BaseString.STR_FALSE;
			}
			for (int i = keys.length - 1; i >= 0; --i) {
				final String key = keys[i];
				object.baseDelete(key);
				assert object.baseGet(key, BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			}
			
			/**
			 * done
			 */
		}
		{
			final BaseObject object = new BaseNativeObject();
			assert !object.baseHasKeysOwn();
			assert !object.baseKeysOwn().hasNext();
			assert !object.baseKeysOwnPrimitive().hasNext();
			
			object.baseDefine("wEd", BasePrimitiveNumber.E, BaseProperty.ATTRS_MASK_NEN);
			object.baseDefine("wED", BasePrimitiveNumber.E, BaseProperty.ATTRS_MASK_NED);
			object.baseDefine("WEd", BasePrimitiveNumber.E, BaseProperty.ATTRS_MASK_WEN);
			
			assert object.baseGetOwnProperty("wEd") != null;
			assert object.baseGetOwnProperty("wED") != null;
			assert object.baseGetOwnProperty("WEd") != null;
			assert BaseProperty.propertyIsWritable(object.baseGetOwnProperty("wEd").propertyAttributes("wEd")) == false;
			assert BaseProperty.propertyIsWritable(object.baseGetOwnProperty("wED").propertyAttributes("wED")) == false;
			assert BaseProperty.propertyIsWritable(object.baseGetOwnProperty("WEd").propertyAttributes("WEd")) == true;
			assert BaseProperty.propertyIsEnumerable(object.baseGetOwnProperty("wEd").propertyAttributes("wEd"));
			assert BaseProperty.propertyIsEnumerable(object.baseGetOwnProperty("wED").propertyAttributes("wED"));
			assert BaseProperty.propertyIsEnumerable(object.baseGetOwnProperty("WEd").propertyAttributes("WEd"));
			assert BaseProperty.propertyIsDynamic(object.baseGetOwnProperty("wEd").propertyAttributes("wEd")) == false;
			assert BaseProperty.propertyIsDynamic(object.baseGetOwnProperty("wED").propertyAttributes("wED")) == true;
			assert BaseProperty.propertyIsDynamic(object.baseGetOwnProperty("WEd").propertyAttributes("WEd")) == false;
			
			assert object.baseGet("wEd", BaseObject.UNDEFINED) == BasePrimitiveNumber.E;
			assert object.baseGet("wED", BaseObject.UNDEFINED) == BasePrimitiveNumber.E;
			assert object.baseGet("WEd", BaseObject.UNDEFINED) == BasePrimitiveNumber.E;
			
			assert !Base.put(object, "wEd", BasePrimitiveNumber.PI) : "Not writable!";
			assert !Base.put(object, "wED", BasePrimitiveNumber.PI) : "Not writable!";
			assert Base.put(object, "WEd", BasePrimitiveNumber.PI) : "Writable!";
			
			assert object.baseGetOwnProperty("wEd") != null : "changed";
			assert object.baseGetOwnProperty("wED") != null : "changed";
			assert object.baseGetOwnProperty("WEd") != null : "changed";
			assert BaseProperty.propertyIsWritable(object.baseGetOwnProperty("wEd").propertyAttributes("wEd")) == false : "changed";
			assert BaseProperty.propertyIsWritable(object.baseGetOwnProperty("wED").propertyAttributes("wED")) == false : "changed";
			assert BaseProperty.propertyIsWritable(object.baseGetOwnProperty("WEd").propertyAttributes("WEd")) == true : "changed";
			assert BaseProperty.propertyIsEnumerable(object.baseGetOwnProperty("wEd").propertyAttributes("wEd")) : "changed";
			assert BaseProperty.propertyIsEnumerable(object.baseGetOwnProperty("wED").propertyAttributes("wED")) : "changed";
			assert BaseProperty.propertyIsEnumerable(object.baseGetOwnProperty("WEd").propertyAttributes("WEd")) : "changed";
			assert BaseProperty.propertyIsDynamic(object.baseGetOwnProperty("wEd").propertyAttributes("wEd")) == false : "changed";
			assert BaseProperty.propertyIsDynamic(object.baseGetOwnProperty("wED").propertyAttributes("wED")) == true : "changed";
			assert BaseProperty.propertyIsDynamic(object.baseGetOwnProperty("WEd").propertyAttributes("WEd")) == false : "changed";
			
			assert object.baseGet("wEd", BaseObject.UNDEFINED) == BasePrimitiveNumber.E;
			assert object.baseGet("wED", BaseObject.UNDEFINED) == BasePrimitiveNumber.E;
			assert object.baseGet("WEd", BaseObject.UNDEFINED) == BasePrimitiveNumber.PI;
			
			assert !object.baseDelete("wEd") : "Not dynamic!";
			assert object.baseDelete("wED") : "Dynamic!";
			assert !object.baseDelete("WEd") : "Not dynamic!";
			
			assert object.baseGet("wEd", BaseObject.UNDEFINED) == BasePrimitiveNumber.E;
			assert object.baseGet("wED", BaseObject.UNDEFINED) == BaseObject.UNDEFINED;
			assert object.baseGet("WEd", BaseObject.UNDEFINED) == BasePrimitiveNumber.PI;
		}
		/**
		 * reverse order
		 */
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			Base.put(object, "c", BaseString.EMPTY);
			Base.put(object, "d", BaseString.EMPTY);
			Base.put(object, "e", BaseString.EMPTY);
			Base.put(object, "f", BaseString.EMPTY);
			Base.put(object, "g", BaseString.EMPTY);
			Base.put(object, "h", BaseString.EMPTY);
			Base.put(object, "i", BaseString.EMPTY);
			Base.put(object, "j", BaseString.EMPTY);
			object.baseDelete("j");
			object.baseDelete("i");
			object.baseDelete("h");
			object.baseDelete("g");
			object.baseDelete("f");
			object.baseDelete("e");
			object.baseDelete("d");
			object.baseDelete("c");
			object.baseDelete("b");
			object.baseDelete("a");
		}
		/**
		 * same order
		 */
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			Base.put(object, "c", BaseString.EMPTY);
			Base.put(object, "d", BaseString.EMPTY);
			Base.put(object, "e", BaseString.EMPTY);
			Base.put(object, "f", BaseString.EMPTY);
			Base.put(object, "g", BaseString.EMPTY);
			Base.put(object, "h", BaseString.EMPTY);
			Base.put(object, "i", BaseString.EMPTY);
			Base.put(object, "j", BaseString.EMPTY);
			object.baseDelete("a");
			object.baseDelete("b");
			object.baseDelete("c");
			object.baseDelete("d");
			object.baseDelete("e");
			object.baseDelete("f");
			object.baseDelete("g");
			object.baseDelete("h");
			object.baseDelete("i");
			object.baseDelete("j");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			Base.put(object, "c", BaseString.EMPTY);
			Base.put(object, "d", BaseString.EMPTY);
			object.baseDelete("d");
			object.baseDelete("c");
			object.baseDelete("b");
			object.baseDelete("a");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			Base.put(object, "c", BaseString.EMPTY);
			object.baseDelete("c");
			object.baseDelete("b");
			object.baseDelete("a");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			Base.put(object, "c", BaseString.EMPTY);
			object.baseDelete("b");
			object.baseDelete("c");
			object.baseDelete("a");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			object.baseDelete("b");
			object.baseDelete("a");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			Base.put(object, "b", BaseString.EMPTY);
			object.baseDelete("a");
			object.baseDelete("b");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY);
			object.baseDelete("a");
		}
		{
			final BaseObject object = new BaseNativeObject();
			Base.put(object, "a", BaseString.EMPTY, BaseProperty.ATTRS_MASK_NNN);
			object.baseDelete("a");
		}
		{
			final BaseObject prototype = new BaseNativeObject(null);
			final BaseObject object = new BaseNativeObject(prototype);
			
			Base.put(object, "A", "A");
			Base.put(object, "D", "D");
			
			Base.put(prototype, "A", "A");
			Base.put(prototype, "B", "B");
			Base.put(prototype, "C", "C");
			
			int i = 0;
			for (final Iterator<String> iterator = Base.keys(object); iterator.hasNext();) {
				iterator.next();
				i++;
			}
			
			assert i == 4 : "Should have 4 properties!";
		}
	}
}
