package ru.myx.ae3.base;

import java.util.function.Function;

/**
 * 
 * @author myx
 * 
 */
public enum TestLargePropertyMaps {
	/**
	 * 
	 */
	OBJECT_NO_PROTO_PROPERTIES_PRIMITIVE_INT_MAP {
		@Override
		public BaseObject createObject() {
			return new BaseNativeObject( null,
					new PropertiesPrimitiveOwnIdentityMap( TestLargePropertyMaps.FIRST_PRIMITIVE ) );
		}
	},
	/**
	 * 
	 */
	OBJECT_NO_PROTO_PROPERTIES_PRIMITIVE_HASH_MAP {
		@Override
		public BaseObject createObject() {
			return new BaseNativeObject( null,
					new PropertiesPrimitiveIdentityHashMap( TestLargePropertyMaps.FIRST_PRIMITIVE ) );
		}
	},
	/**
	 * 
	 */
	OBJECT_NO_PROTO_PROPERTIES_STRING_HASH_MAP {
		@Override
		public BaseObject createObject() {
			return new BaseNativeObject( null,
					new PropertiesStringHashMap().add( TestLargePropertyMaps.FIRST_STRING_KEY,
							TestLargePropertyMaps.FIRST_STRING ) );
		}
	},
	/**
	 * 
	 */
	OBJECT_NO_PROTO_PROPERTIES_STRING_TREE_MAP {
		@Override
		public BaseObject createObject() {
			return new BaseNativeObject( null,
					new PropertiesStringTreeMap().add( TestLargePropertyMaps.FIRST_STRING_KEY,
							TestLargePropertyMaps.FIRST_STRING ) );
		}
	},
	/**
	 * 
	 */
	ARRAY_NO_PROTO_PROPERTIES_STRING_HASH_MAP {
		@Override
		public BaseObject createObject() {
			return new BaseNativeArray( null,
					new PropertiesStringHashMap().add( TestLargePropertyMaps.FIRST_STRING_KEY,
							TestLargePropertyMaps.FIRST_STRING ) );
		}
	},
	/**
	 * 
	 */
	OBJECT_WITH_PROTO_PROPERTIES_STRING_HASH_MAP {
		@Override
		public BaseObject createObject() {
			return new BaseNativeObject( BaseObject.PROTOTYPE,
					new PropertiesStringHashMap().add( TestLargePropertyMaps.FIRST_STRING_KEY,
							TestLargePropertyMaps.FIRST_STRING ) );
		}
	},
	/**
	 * 
	 */
	DUMMY {
		/**
		 * TODO: implement to check test overhead when needed
		 */
		@Override
		public BaseObject createObject() {
			return BaseObject.UNDEFINED;
		}
	},
	
	/**
	 * 
	 */
	;
	
	static final BasePrimitiveString		FIRST_PRIMITIVE_KEY	= Base.forString( "First" );
	
	static final BasePropertyDataPrimitive	FIRST_PRIMITIVE		= new BasePropertyHolderPrimitive( TestLargePropertyMaps.FIRST_PRIMITIVE_KEY,
																		BaseObject.NULL,
																		BaseProperty.ATTRS_MASK_NNN );
	
	static final String						FIRST_STRING_KEY	= "First";
	
	static final BasePropertyDataString		FIRST_STRING		= new BasePropertyHolderString( TestLargePropertyMaps.FIRST_STRING_KEY,
																		BaseObject.NULL,
																		BaseProperty.ATTRS_MASK_NNN );
	
	static {
		TestLargePropertyMaps.testBasic();
	}
	
	private static final void testBasic() {
		/**
		 * check some
		 */
		for (final TestLargePropertyMaps test : TestLargePropertyMaps.values()) {
			if (test != DUMMY) {
				/**
				 * 
				 */
			}
			for (int i = 0; i < 8; i++) {
				try {
					/**
					 * Fuck knows why would I need it here!
					 */
					// test.createTestOnce( i ).execute( null );
				} catch (final Exception e) {
					throw new RuntimeException( e );
				}
			}
		}
	}
	
	/**
	 * @return
	 */
	public abstract BaseObject createObject();
	
	/**
	 * @param size
	 * @return
	 */
	@SuppressWarnings("static-method")
	public Function<Void, Void> createTestOnce(final int size) {
		return null;
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("static-method")
	public boolean testSpeed() {
		return true;
	}
}
