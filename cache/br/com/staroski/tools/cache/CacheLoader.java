package br.com.staroski.tools.cache;

/**
 * Interface to load objects inside a {@link Cache cache}.
 * 
 * @author Ricardo Artur Staroski
 *
 * @param <K>
 *            Data type of the {@link Cache cache}'s search key.
 * @param <K>
 *            Data type of the object loaded by the search key.
 * @see Cache
 */
public interface CacheLoader<K, V> {

    /**
     * Uses the specified search key to load a value.
     * 
     * @param key
     *            The search key.
     * @return The value loader for the specified search key.
     */
    public V load(K key);
}
