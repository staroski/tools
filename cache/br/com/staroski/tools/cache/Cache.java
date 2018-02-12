package br.com.staroski.tools.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of an object cache.<BR>
 * Instances of this class depend uppon a {@link CacheLoader} to load the objects on demand.<BR>
 * 
 * @author Ricardo Artur Staroski
 *
 * @param <K>
 *            Data type of the search key.
 * @see CacheLoader
 */
@SuppressWarnings("unchecked")
public final class Cache {

    // map that actually holds the loaded objects
    private final Map<MultiKey, Object> map = new HashMap<>();

    /**
     * Clears this {@link Cache cache}.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Gets the value from the entered search key.<br>
     * If the value has not yet been loaded, then the informed {@link CacheLoader} will be used to load and store it in the cache.
     * 
     * @param loader
     *            The {@link CacheLoader} responsible for loading the value.
     * @param key
     *            The search key used to get the value.
     * 
     * @return The value found for the entered key, if the key is <code>null</code>, the return will be <code>null</code>.
     * 
     * @throws IllegalArgumentException
     *             If the {@link CacheLoader} is <code>null</code>.
     */
    public <K, V> V get(final CacheLoader<K, V> loader, final K key) {
        if (loader == null)
            throw new IllegalArgumentException("loader cannot be null");
        if (key == null) {
            return null;
        }
        final MultiKey pair = new MultiKey(loader, key);
        V value = (V) map.get(pair);
        if (value == null) {
            synchronized (this) { // os 2 if's são realmente iguais, não é idiotice não, é um "double-checked locking"
                value = (V) map.get(pair);
                if (value == null) {
                    value = loader.load((K) key);
                    map.put(pair, value);
                }
            }
        }
        return value;
    }
}
