package br.com.staroski.tools.cache;

/**
 * Interface para carregar objetos para dentro de um {@link Cache cache}.
 * 
 * @author Ricardo Artur Staroski
 *
 * @param <K> Tipo de dado da chave de busca do {@link Cache cache}.
 * @param <K> Tipo de dado do objeto carregado a partir da chave de busca.
 * @see Cache
 */
public interface CacheLoader<K, V> {

    /**
     * Utiliza a chave de busca informada para carregar um valor.
     * 
     * @param key A chave de busca.
     * @return O valor carregado a partir da chave de busca.
     */
    public V load(K key);
}
