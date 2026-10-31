/**
 *
 */
package ru.myx.ae3.base;

import java.util.Iterator;
import java.util.TreeMap;

import ru.myx.ae3.help.Format;

final class PropertiesStringTreeMap extends TreeMap<String, BasePropertyData<String>> implements BaseProperties<String> {

	private static final long serialVersionUID = 2888363574028818360L;

	private BasePropertyData<String> first;

	private BasePropertyData<String> last;

	PropertiesStringTreeMap() {

		//
	}

	PropertiesStringTreeMap(final BasePropertyData<String> property1, final String name2, final BasePropertyData<String> property2) {

		this.add(property1.name, property1);
		this.add(name2, property2);
	}

	@Override
	public BaseProperties<String> add(final BaseObject instance, final BasePrimitiveString name, final BaseProperty property, final short attributes) {

		return this.add(instance, name.stringValue(), property, attributes);
	}

	@Override
	public final BaseProperties<String> add(final BaseObject instance, final String name, final BaseProperty property, final short attributes) {

		return this.add(
				name,
				Base.createPropertyString(
						instance, //
						name,
						property,
						attributes));
	}

	@Override
	public final BaseProperties<String> add(final BasePrimitiveString name, final BaseObject value, final short attributes) {

		return this.add(name, new BasePropertyHolderString(null, value, attributes));
	}

	@Override
	public BaseProperties<String> add(final BasePrimitiveString name, final BasePropertyData<String> property) {

		return this.add(name.stringValue(), property);
	}

	@Override
	public final BaseProperties<String> add(final String name, final BaseObject value, final short attributes) {

		return this.add(name, new BasePropertyHolderString(null, value, attributes));
	}

	@Override
	public BaseProperties<String> add(final String name, final BasePropertyData<String> property) {

		assert property != null : "NULL property";
		assert name != null : "NULL property name";
		assert property.name == null || property.name.equals(name) : "Property is already assigned!";
		assert property.next == null : "Property is already assigned!";
		property.name = name;
		final BasePropertyData<String> removed = this.put(name, property);

		/** removed was not enumerable **/
		if (removed == null || (removed.attributes & BaseProperty.ATTR_ENUMERABLE) == 0) {
			/** property is enumerable **/
			if ((property.attributes & BaseProperty.ATTR_ENUMERABLE) != 0) {
				if (this.last == null) {
					this.first = this.last = property;
				} else {
					this.last.next = property;
					property.prev = this.last;
					this.last = property;
				}
			}
			return this;
		}

		/** both (property and remove) are enumerable **/
		if ((property.attributes & BaseProperty.ATTR_ENUMERABLE) != 0) {
			if (removed == this.first) {
				if (removed == this.last) {
					this.first = this.last = property;
					return this;
				}
				removed.next.prev = property;
				property.next = removed.next;
				this.first = property;
				return this;
			}
			if (removed == this.last) {
				removed.prev.next = property;
				property.prev = removed.prev;
				this.last = property;
				return this;
			}
			removed.next.prev = property;
			property.next = removed.next;
			removed.prev.next = property;
			property.prev = removed.prev;
			return this;
		}

		/** removed was enumerable **/
		{
			if (removed == this.first) {
				if (removed == this.last) {
					this.first = this.last = null;
					return this;
				}
				(this.first = removed.next).prev = null;
				return this;
			}
			if (removed == this.last) {
				(this.last = removed.prev).next = null;
				return this;
			}

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
			return this;
		}
	}

	@Override
	public BaseProperties<String> delete(final BasePrimitiveString name) {

		return this.delete(name.stringValue());
	}

	@Override
	public BaseProperties<String> delete(final String name) {

		final BasePropertyData<String> removed = this.remove(name);
		if (removed == null || (removed.attributes & BaseProperty.ATTR_ENUMERABLE) == 0) {
			return this;
		}
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
			 * Properties of the object being enumerated may be deleted during enumeration. If a
			 * property that has not yet been visited during enumeration is deleted, then it will
			 * not be visited. If new properties are added to the object being enumerated during
			 * enumeration, the newly added properties are not guaranteed to be visited in the
			 * active enumeration. Deleted: The mechanics of enumerating the properties (step 5 in
			 * the first algorithm, step 6 in the second) is implementation dependent. The order of
			 * enumeration is defined by the object. */
		}
		if (this.size() == 1) {
			final BasePropertyData<String> property = this.values().iterator().next();
			property.next = null;
			return property;
		}
		return this;
	}

	@Override
	public BasePropertyData<String> find(final BasePrimitiveString name) {

		return this.find(name.stringValue());
	}

	@Override
	public BasePropertyData<String> find(final CharSequence name) {

		return this.find(name.toString());
	}

	@Override
	public BasePropertyData<String> find(final String name) {

		return this.get(name);
	}

	@Override
	public boolean hasEnumerableProperties() {

		return this.first != null;
	}

	/** COMMENTS:
	 *
	 * 1) put only enumerable properties to a linked chain, use this.keySet() for 'all'
	 *
	 * 2) use sequence - to put enumerable elements in the front. */
	@Override
	public Iterator<String> iteratorAll() {

		return this.first == null
			? this.keySet().iterator()
			: new IteratorSequenceString(this.iteratorEnumerableAsString(), this.keySet().iterator());
	}

	@Override
	public Iterator<BasePrimitiveString> iteratorAllAsPrimitive() {

		return this.first == null
			? new IteratorPrimitiveForString(this.keySet().iterator())
			: new IteratorSequencePrimitive(this.iteratorEnumerableAsPrimitive(), new IteratorPrimitiveForString(this.keySet().iterator()));
	}

	@Override
	public Iterator<String> iteratorAllAsString() {

		return this.iteratorAll();
	}

	@Override
	public Iterator<String> iteratorEnumerable() {

		final BasePropertyData<String> first = this.first;
		return first == null
			? BaseObject.ITERATOR_EMPTY
			: new IteratorPropertiesAll<>(first);
	}

	@Override
	public Iterator<BasePrimitiveString> iteratorEnumerableAsPrimitive() {

		final BasePropertyData<String> first = this.first;
		return first == null
			? BaseProperties.ITERATOR_EMPTY_PRIMITIVE_STRING
			: new IteratorPropertiesAllToPrimitive(first);
	}

	@Override
	public Iterator<String> iteratorEnumerableAsString() {

		return this.iteratorEnumerable();
	}

	/** important for debugging */
	@Override
	public String toString() {

		final StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		for (final Iterator<String> iterator = this.iteratorEnumerableAsString(); iterator.hasNext();) {
			final String key = iterator.next();
			builder.append(
					builder.length() == 0
						? ": "
						: ", ");
			final BasePropertyData<String> data = this.find(key);
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
