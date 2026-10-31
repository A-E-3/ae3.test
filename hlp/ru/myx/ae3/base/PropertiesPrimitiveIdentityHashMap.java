/**
 *
 */
package ru.myx.ae3.base;

import java.util.IdentityHashMap;
import java.util.Iterator;

import ru.myx.ae3.help.Format;

final class PropertiesPrimitiveIdentityHashMap extends IdentityHashMap<BasePrimitiveString, BasePropertyData<BasePrimitiveString>> implements BaseProperties<BasePrimitiveString> {
	
	private static final long serialVersionUID = 2888363574028818360L;
	
	private BasePropertyData<BasePrimitiveString> first;
	
	private BasePropertyData<BasePrimitiveString> last;
	
	PropertiesPrimitiveIdentityHashMap(final BasePropertyData<BasePrimitiveString> first) {
		
		super(16);
		for (BasePropertyData<BasePrimitiveString> current = first;;) {
			final BasePropertyData<BasePrimitiveString> next = current.next;
			current.next = null;
			this.add(current.name, current);
			current = next;
			if (current == null) {
				break;
			}
		}
	}
	
	private final BaseProperties<BasePrimitiveString> delete(final BasePropertyData<BasePrimitiveString> removed) {
		
		if (removed == null || (removed.attributes & BaseProperty.ATTR_ENUMERABLE) == 0) {
			return this;
		}
		if (removed == this.first) {
			if (removed == this.last) {
				this.first = this.last = null;
			} else {
				(this.first = removed.next).prev = null;
			}
		} else if (removed == this.last) {
			(this.last = removed.prev).next = null;
		} else {
			removed.prev.next = removed.next;
			removed.next.prev = removed.prev;
			removed.prev = null;
			/** don't clear removed.next here used for enumeration:
			 * <p>
			 * Properties of the object being enumerated may be deleted during enumeration. If a
			 * property that has not yet been visited during enumeration is deleted, then it will
			 * not be visited. If new properties are added to the object being enumerated during
			 * enumeration, the newly added properties are not guaranteed to be visited in the
			 * active enumeration. Deleted: The mechanics of enumerating the properties (step 5 in
			 * the first algorithm, step 6 in the second) is implementation dependent. The order of
			 * enumeration is defined by the object. */
		}
		if (this.size() == 1) {
			final BasePropertyData<BasePrimitiveString> property = this.values().iterator().next();
			property.next = null;
			return property;
		}
		return this;
	}
	
	@Override
	public BaseProperties<BasePrimitiveString> add(final BaseObject instance, final BasePrimitiveString name, final BaseProperty property, final short attributes) {
		
		return this.add(
				name,
				Base.createPropertyPrimitive(
						instance, //
						name,
						property,
						attributes));
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> add(final BaseObject instance, final String name, final BaseProperty property, final short attributes) {
		
		return this.add(instance, Base.forString(name), property, attributes);
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> add(final BasePrimitiveString name, final BaseObject value, final short attributes) {
		
		return this.add(name, new BasePropertyHolderPrimitive(null, value, attributes));
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> add(final BasePrimitiveString name, final BasePropertyData<BasePrimitiveString> property) {
		
		assert property != null : "NULL property";
		assert name != null : "NULL property name";
		assert property.name == null || property.name.equals(name) : "Property is already assigned!";
		assert property.next == null : "Property is already assigned!";
		property.name = name;
		final BasePropertyData<BasePrimitiveString> removed = this.put(name, property);
		if ((property.attributes & BaseProperty.ATTR_ENUMERABLE) != 0) {
			if (removed != null && (removed.attributes & BaseProperty.ATTR_ENUMERABLE) != 0) {
				if (removed == this.first) {
					if (removed == this.last) {
						this.first = this.last = property;
					} else {
						removed.next.prev = property;
						property.next = removed.next;
						this.first = property;
					}
				} else if (removed == this.last) {
					removed.prev.next = property;
					property.prev = removed.prev;
					this.last = property;
				} else {
					removed.next.prev = property;
					property.next = removed.next;
					removed.prev.next = property;
					property.prev = removed.prev;
				}
			} else {
				if (this.last == null) {
					this.first = this.last = property;
				} else {
					this.last.next = property;
					property.prev = this.last;
					this.last = property;
				}
			}
		} else {
			if (removed != null && (removed.attributes & BaseProperty.ATTR_ENUMERABLE) != 0) {
				if (removed == this.first) {
					if (removed == this.last) {
						this.first = this.last = null;
					} else {
						(this.first = removed.next).prev = null;
					}
				} else //
				if (removed == this.last) {
					(this.last = removed.prev).next = null;
				} else {
					removed.prev.next = removed.next;
					removed.next.prev = removed.prev;
					removed.prev = null;
					/** don't clear removed.next here used for enumeration:
					 * <p>
					 * Properties of the object being enumerated may be deleted during enumeration.
					 * If a property that has not yet been visited during enumeration is deleted,
					 * then it will not be visited. If new properties are added to the object being
					 * enumerated during enumeration, the newly added properties are not guaranteed
					 * to be visited in the active enumeration. Deleted: The mechanics of
					 * enumerating the properties (step 5 in the first algorithm, step 6 in the
					 * second) is implementation dependent. The order of enumeration is defined by
					 * the object. */
				}
			}
		}
		return this;
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> add(final String name, final BaseObject value, final short attributes) {
		
		return this.add(Base.forString(name), value, attributes);
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> add(final String name, final BasePropertyData<BasePrimitiveString> property) {
		
		return this.add(Base.forString(name), property);
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> delete(final BasePrimitiveString name) {
		
		return this.delete(this.remove(name));
	}
	
	@Override
	public final BaseProperties<BasePrimitiveString> delete(final String name) {
		
		return this.delete(this.remove(name));
	}
	
	@Override
	public final BasePropertyData<BasePrimitiveString> find(final BasePrimitiveString name) {
		
		return this.get(name);
	}
	
	/** TODO: make it faster by removing Base.forString() */
	@Override
	public final BasePropertyData<BasePrimitiveString> find(final CharSequence name) {
		
		return this.get(Base.forString(name));
	}
	
	/** TODO: make it faster by removing Base.forString() */
	@Override
	public final BasePropertyData<BasePrimitiveString> find(final String name) {
		
		return this.get(Base.forString(name));
	}
	
	@Override
	public final boolean hasEnumerableProperties() {
		
		return this.first != null;
	}
	
	/** COMMENTS:
	 *
	 * 1) put only enumerable properties to a linked chain, use this.keySet() for 'all'
	 *
	 * 2) use sequence - to put enumerable elements in the front. */
	@Override
	public final Iterator<BasePrimitiveString> iteratorAll() {
		
		return this.first == null
			? this.keySet().iterator()
			: new IteratorSequencePrimitive(this.iteratorEnumerableAsPrimitive(), this.keySet().iterator());
	}
	
	@Override
	public final Iterator<BasePrimitiveString> iteratorAllAsPrimitive() {
		
		return this.iteratorAll();
	}
	
	@Override
	public final Iterator<String> iteratorAllAsString() {
		
		return this.first == null
			? new IteratorStringForAnything(this.keySet().iterator())
			: new IteratorSequenceString(this.iteratorEnumerableAsString(), new IteratorStringForAnything(this.keySet().iterator()));
	}
	
	@Override
	public final Iterator<BasePrimitiveString> iteratorEnumerable() {
		
		final BasePropertyData<BasePrimitiveString> first = this.first;
		if (first == null) {
			return BaseProperties.ITERATOR_EMPTY_PRIMITIVE_STRING;
		}
		return new IteratorPropertiesAll<>(first);
	}
	
	@Override
	public final Iterator<BasePrimitiveString> iteratorEnumerableAsPrimitive() {
		
		return this.iteratorEnumerable();
	}
	
	@Override
	public final Iterator<String> iteratorEnumerableAsString() {
		
		final BasePropertyData<BasePrimitiveString> first = this.first;
		if (first == null) {
			return BaseObject.ITERATOR_EMPTY;
		}
		return new IteratorPropertiesAllToString<>(first);
	}
	
	/** important for debugging */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		for (final Iterator<BasePrimitiveString> iterator = this.iteratorEnumerableAsPrimitive(); iterator.hasNext();) {
			final BasePrimitiveString key = iterator.next();
			builder.append(
					builder.length() == 0
						? ": "
						: ", ");
			final BasePropertyData<BasePrimitiveString> data = this.find(key);
			builder.append(key);
			builder.append(" : ");
			builder.append(
					data != null
						? Format.Describe.toEcmaSource(data.propertyGet(null, key), "")
						: "?");
		}
		return builder.append("#;").toString();
	}
}
