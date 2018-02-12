package br.com.staroski.tools.cache;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a key composed by more than one object.
 * 
 * @author Ricardo Artur Staroski
 */
@SuppressWarnings("unchecked")
final class MultiKey implements Comparable<MultiKey> {

    private final int hashCode;
    private final List<Object> objects;

    /**
     * Creates a new {@link MultiKey compound key}.
     * 
     * @param key1
     *            The firs object of this key.
     * @param key2
     *            The second object of this key.
     * @param moreKeys
     *            The others objects from this. (<B><I>Optional</I></B>)
     */
    public MultiKey(Object key1, Object key2, Object... moreKeys) {
        List<Object> objects = new LinkedList<>();
        objects.add(key1);
        objects.add(key2);
        objects.addAll(Arrays.asList(moreKeys));
        this.objects = objects;
        this.hashCode = objects.hashCode();
    }

    @Override
    public int compareTo(MultiKey other) {
        return this.hashCode - (other == null ? 0 : other.hashCode);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof MultiKey) {
            final MultiKey that = (MultiKey) object;
            // if the hashes are different then the objects are different
            if (this.hashCode != that.hashCode) {
                return false;
            }
            // different object can have equal hashes, so compare it to ensure
            return this.objects.equals(that.objects);
        }
        return false;
    }

    /**
     * Gets the object at the specified index of this {@link MultiKey compound key}.
     * 
     * @param index
     *            The index of the object to get.
     * @return The object at specified index.
     */
    public <T> T get(int index) {
        return (T) objects.get(index);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /**
     * @return A quantidade de objetos que compõe esta {@link MultiKey chave composta}.
     */
    public int size() {
        return objects.size();
    }
}
