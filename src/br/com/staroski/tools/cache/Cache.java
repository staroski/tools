package br.com.staroski.tools.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementação de um cache de objetos.<BR>
 * Instâncias desta classe dependem de um {@link CacheLoader} para carregar os objetos sob demanda.<BR>
 * Como já dizia o véio deitado: <I>Se cache, cache! Se não cache, diz!</I>
 * 
 * @author Ricardo Artur Staroski
 *
 * @param <K> Tipo de dado da chave de busca.
 * @see CacheLoader
 */
@SuppressWarnings("unchecked")
public final class Cache {

    // mapa que armazena os objetos carregados
    private final Map<MultiKey, Object> map = new HashMap<>();

    /**
     * Limpa este {@link Cache cache}.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Obtém o valor a partir da chave de busca informada.<BR>
     * Se o valor ainda não tiver sido carregado, então o {@link CacheLoader} informado será utilizado para carregá-lo e armazenar no {@link Cache cache}.
     * 
     * @param loader O {@link CacheLoader} responsável por carregar o valor.
     * @param key A chave de busca utilizada para obter o valor.
     * @return O valor encontrado para a chave informada, se a chave for <code>null</code>, o retorno será <code>null</code>.
     * @throws IllegalArgumentException se o {@link CacheLoader} informado for <code>null</code>
     */
    public <K, V> V get(final CacheLoader<K, V> loader, final K key) {
        if (loader == null) throw new IllegalArgumentException("loader cannot be null");
        if (key == null) {
            return null;
        }
        final MultiKey pair = new MultiKey(loader, key);
        V value = (V) map.get(pair);
        if (value == null) {
            synchronized(this) { // os 2 if's são realmente iguais, não é idiotice não, é um "double-checked locking"
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
