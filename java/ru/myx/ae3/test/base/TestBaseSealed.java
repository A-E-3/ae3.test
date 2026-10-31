package ru.myx.ae3.test.base;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseMap;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseNativeObject;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.base.BaseProperty;
import ru.myx.ae3.base.BaseSealed;

/**
 * 
 * @author myx
 * 
 */
class TestBaseSealed {
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> TEST BASE SEALED" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			/**
			 * BaseSealed BaseNativeObject
			 */
			final BaseMap object = new BaseNativeObject( null );
			final BaseSealed sealed = new BaseSealed( object );
			
			assert object.baseArray() == null;
			assert sealed.baseArray() == null;
			
			assert object.baseCall() == null;
			assert sealed.baseCall() == null;
			
			assert object.baseGetOwnProperty( "A" ) == null;
			assert sealed.baseGetOwnProperty( "A" ) == null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseKeysOwnAll() == BaseObject.ITERATOR_EMPTY;
			assert sealed.baseKeysOwnAll() == BaseObject.ITERATOR_EMPTY;
			assert object.baseKeysOwn() == BaseObject.ITERATOR_EMPTY;
			assert sealed.baseKeysOwn() == BaseObject.ITERATOR_EMPTY;
			
			Base.put( object, "A", BaseObject.TRUE );
			
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseKeysOwnAll().hasNext();
			assert sealed.baseKeysOwnAll().hasNext();
			assert object.baseKeysOwn().hasNext();
			assert sealed.baseKeysOwn().hasNext();
			
			assert object.baseGet( "A", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert sealed.baseGet( "A", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			
			Base.put( sealed, "B", BaseObject.TRUE );
			
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			sealed.baseDefine( "B", BaseObject.TRUE, BaseProperty.ATTRS_MASK_WED );
			
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			sealed.baseDelete( "A" );
			
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			assert object.baseKeysOwnAll().hasNext();
			assert sealed.baseKeysOwnAll().hasNext();
			assert object.baseKeysOwn().hasNext();
			assert sealed.baseKeysOwn().hasNext();
			
			object.baseDelete( "A" );
			
			assert object.baseGetOwnProperty( "A" ) == null;
			assert sealed.baseGetOwnProperty( "A" ) == null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseKeysOwnAll() == BaseObject.ITERATOR_EMPTY;
			assert sealed.baseKeysOwnAll() == BaseObject.ITERATOR_EMPTY;
			assert object.baseKeysOwn() == BaseObject.ITERATOR_EMPTY;
			assert sealed.baseKeysOwn() == BaseObject.ITERATOR_EMPTY;
		}
		{
			/**
			 * BaseSealed BaseNativeArray properties
			 */
			final BaseNativeArray object = new BaseNativeArray();
			final BaseSealed sealed = new BaseSealed( object );
			
			assert object.baseArray() != null;
			assert sealed.baseArray() != null;
			
			assert object.baseCall() == null;
			assert sealed.baseCall() == null;
			
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getInt( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert Base.getInt( sealed, "length", -1 ) == 0;
			assert object.baseGetOwnProperty( "A" ) == null;
			assert sealed.baseGetOwnProperty( "A" ) == null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseKeysOwnAll().hasNext() : "'length' property";
			assert sealed.baseKeysOwnAll().hasNext() : "'length' property";
			assert object.baseKeysOwn() == BaseObject.ITERATOR_EMPTY : "Not empty: next: "
					+ object.baseKeysOwn().next();
			assert sealed.baseKeysOwn() == BaseObject.ITERATOR_EMPTY : "Not empty: next: "
					+ sealed.baseKeysOwn().next();
			
			Base.put( object, "A", BaseObject.TRUE );
			
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseKeysOwnAll().hasNext();
			assert sealed.baseKeysOwnAll().hasNext();
			assert object.baseKeysOwn().hasNext();
			assert sealed.baseKeysOwn().hasNext();
			
			assert object.baseGet( "A", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert sealed.baseGet( "A", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			
			Base.put( sealed, "B", BaseObject.TRUE );
			
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			sealed.baseDefine( "B", BaseObject.TRUE, BaseProperty.ATTRS_MASK_WED );
			
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			sealed.baseDelete( "A" );
			
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGetOwnProperty( "A" ) != null;
			assert sealed.baseGetOwnProperty( "A" ) != null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( "B", BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			assert object.baseKeysOwnAll().hasNext();
			assert sealed.baseKeysOwnAll().hasNext();
			assert object.baseKeysOwn().hasNext();
			assert sealed.baseKeysOwn().hasNext();
			
			object.baseDelete( "A" );
			
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGetOwnProperty( "A" ) == null;
			assert sealed.baseGetOwnProperty( "A" ) == null;
			assert object.baseGetOwnProperty( "B" ) == null;
			assert sealed.baseGetOwnProperty( "B" ) == null;
			
			assert object.baseKeysOwnAll().hasNext() : "'length' property";
			assert sealed.baseKeysOwnAll().hasNext() : "'length' property";
			assert object.baseKeysOwn() == BaseObject.ITERATOR_EMPTY : "Not empty: next: "
					+ object.baseKeysOwn().next();
			assert sealed.baseKeysOwn() == BaseObject.ITERATOR_EMPTY : "Not empty: next: "
					+ sealed.baseKeysOwn().next();
		}
		{
			/**
			 * BaseSealed BaseNativeArray indices
			 */
			final BaseNativeArray object = new BaseNativeArray();
			final BaseSealed sealed = new BaseSealed( object );
			
			assert object.baseArray() != null;
			assert sealed.baseArray() != null;
			
			assert object.baseCall() == null;
			assert sealed.baseCall() == null;
			
			assert object.baseArray().length() == 0;
			assert sealed.baseArray().length() == 0;
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert object.baseGet( 0, null ) == null;
			assert sealed.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert object.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert object.baseGet( 1, null ) == null;
			assert sealed.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			object.baseDefaultPush( BaseObject.TRUE );
			
			assert object.baseArray().length() == 1;
			assert sealed.baseArray().length() == 1;
			assert Base.getLong( object, "length", -1 ) == 1;
			assert Base.getLong( sealed, "length", -1 ) == 1;
			assert object.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert object.baseGet( 0, null ) == BaseObject.TRUE;
			assert sealed.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert object.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert object.baseGet( 1, null ) == null;
			assert sealed.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			assert sealed.baseArray().baseArrayWritable() == null;
			assert sealed.baseArray().baseArrayDynamic() == null;
			
			assert object.baseArray().length() == 1;
			assert sealed.baseArray().length() == 1;
			assert Base.getLong( object, "length", -1 ) == 1;
			assert Base.getLong( sealed, "length", -1 ) == 1;
			assert object.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert sealed.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert object.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			
			object.baseRemove( 0 );
			
			assert object.baseArray().length() == 0;
			assert sealed.baseArray().length() == 0;
			assert Base.getLong( object, "length", -1 ) == 0;
			assert Base.getLong( sealed, "length", -1 ) == 0;
			assert object.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( 0, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert object.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
			assert sealed.baseGet( 1, BaseObject.UNDEFINED ) == BaseObject.UNDEFINED;
		}
	}
}
