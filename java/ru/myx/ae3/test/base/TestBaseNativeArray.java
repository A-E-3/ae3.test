package ru.myx.ae3.test.base;

import java.util.List;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.base.BaseArray;
import ru.myx.ae3.base.BaseList;
import ru.myx.ae3.base.BaseNativeArray;
import ru.myx.ae3.base.BaseObject;

/**
 * 
 * @author myx
 * 
 */
class TestBaseNativeArray {
	/**
	 * 
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		Engine.createGuid();
		System.out.println( ">>>> TEST BASE NATIVE ARRAY" );
		{
			try {
				assert false;
				throw new RuntimeException( "assertions required for this test!" );
			} catch (final AssertionError t) {
				// ignore
			}
		}
		{
			final BaseObject object = new BaseNativeArray();
			assert object.baseArray() != null;
			{
				final BaseArray array = object.baseArray();
				assert array.isEmpty();
				assert array.length() == 0;
				assert array.baseKeysOwn() != null;
				assert !array.baseKeysOwn().hasNext();
				assert array.baseKeysOwnAll() != null;
				assert array.baseKeysOwnAll().hasNext();
				assert "length".equals( array.baseKeysOwnAll().next() );
			}
			assert object instanceof List<?>;
			{
				final List<?> list = (List<?>) object;
				assert list.isEmpty();
				assert list.size() == 0;
				assert list.iterator() != null;
				assert !list.iterator().hasNext();
				assert list.listIterator() != null;
				assert !list.listIterator().hasNext();
				assert list.listIterator( 0 ) != null;
				assert !list.listIterator( 0 ).hasNext();
			}
		}
		{
			final BaseList<Object> array = new BaseNativeArray( "aaa", BaseObject.TRUE );
			Base.put( array, "bbb", BaseObject.TRUE );
			Base.put( array, "ccc", true );
			Base.put( array, "ddd", 5 );
			Base.put( array, "eee", "str" );
			array.add( Boolean.TRUE );
			assert array.length() == 1;
			assert array.size() == 1;
			assert Base.getLong( array, "length", 0 ) == 1;
			assert Base.getInt( array, "length", 0 ) == 1;
			
			assert array.baseGet( 0, null ) == BaseObject.TRUE;
			assert array.get( 0 ) == Boolean.TRUE;
			
			assert array.baseGet( "aaa", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert array.baseGet( "bbb", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			assert array.baseGet( "ccc", BaseObject.UNDEFINED ) == BaseObject.TRUE;
			
			assert Base.getLong( array, "ddd", 0 ) == 5;
			assert Base.getString( array, "eee", "" ).equals( "str" );
		}
		{
			final BaseList<Object> array = new BaseNativeArray();
			array.baseDefaultPush( BaseObject.TRUE );
			array.baseDefaultPush( BaseObject.FALSE );
			array.baseDefaultPush( BaseObject.NULL );
			assert array.length() == 3;
			assert array.size() == 3;
			assert Base.getLong( array, "length", 0 ) == 3;
			assert array.baseGet( 0, null ) == BaseObject.TRUE;
			assert array.baseGet( 1, null ) == BaseObject.FALSE;
			assert array.baseGet( 2, null ) == BaseObject.NULL;
			assert array.get( 0 ) == Boolean.TRUE;
			assert array.get( 1 ) == Boolean.FALSE;
			assert array.get( 2 ) == null;
		}
		{
			final BaseList<Object> array = new BaseNativeArray( BaseObject.TRUE );
			array.baseDefaultPush( BaseObject.FALSE );
			array.baseDefaultPush( BaseObject.NULL );
			assert array.length() == 3;
			assert array.size() == 3;
			assert Base.getLong( array, "length", 0 ) == 3;
			assert array.baseGet( 0, null ) == BaseObject.TRUE;
			assert array.baseGet( 1, null ) == BaseObject.FALSE;
			assert array.baseGet( 2, null ) == BaseObject.NULL;
			assert array.get( 0 ) == Boolean.TRUE;
			assert array.get( 1 ) == Boolean.FALSE;
			assert array.get( 2 ) == null;
		}
		{
			final BaseList<Object> array = new BaseNativeArray( 5, BaseObject.TRUE );
			assert array.length() == 5;
			assert array.size() == 5;
			assert Base.getLong( array, "length", 0 ) == 5;
			assert array.baseGet( 0, null ) == BaseObject.TRUE;
			assert array.baseGet( 1, null ) == BaseObject.TRUE;
			assert array.baseGet( 2, null ) == BaseObject.TRUE;
			assert array.baseGet( 3, null ) == BaseObject.TRUE;
			assert array.baseGet( 4, null ) == BaseObject.TRUE;
			assert array.get( 0 ) == Boolean.TRUE;
			assert array.get( 1 ) == Boolean.TRUE;
			assert array.get( 2 ) == Boolean.TRUE;
			assert array.get( 3 ) == Boolean.TRUE;
			assert array.get( 4 ) == Boolean.TRUE;
		}
		{
			/**
			 * property
			 */
			final BaseList<Object> array = new BaseNativeArray( 5, BaseObject.TRUE );
			assert Base.put( array, "aaa", BaseObject.TRUE );
			assert Base.put( array, "aaa", BaseObject.TRUE );
			assert BaseObject.TRUE == array.baseGet( "aaa", BaseObject.UNDEFINED );
		}
		{
			/**
			 * hash code & equals
			 */
			final BaseNativeArray array1 = new BaseNativeArray( 5, BaseObject.TRUE );
			final BaseNativeArray array2 = new BaseNativeArray( 5, BaseObject.TRUE );
			
			assert array1.hashCodeForValue() == array2.hashCodeForValue();
			assert array1.equals( array2 );
			assert array2.equals( array1 );
			
			array1.baseDefaultPush( BaseObject.NULL );
			
			assert array1.hashCode() != array2.hashCode();
			assert array1.hashCodeForValue() != array2.hashCodeForValue();
			assert !array1.equals( array2 );
			assert !array2.equals( array1 );
			
			array2.baseDefaultPush( BaseObject.NULL );
			
			assert array1.hashCodeForValue() == array2.hashCodeForValue();
			assert array1.equals( array2 );
			assert array2.equals( array1 );
			
			array1.baseDefaultPush( BaseObject.PROTOTYPE );
			
			assert array1.hashCode() != array2.hashCode();
			assert array1.hashCodeForValue() != array2.hashCodeForValue();
			assert !array1.equals( array2 );
			assert !array2.equals( array1 );
			
			array2.baseDefaultPush( BaseObject.PROTOTYPE );
			
			assert array1.hashCode() != array2.hashCode();
			assert array1.hashCodeForValue() == array2.hashCodeForValue();
			assert array1.equals( array2 );
			assert array2.equals( array1 );
		}
	}
}
