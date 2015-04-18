package br.com.staroski.tools.cache;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta classe representa uma chave composta por mais de um objeto.
 * 
 * @author Ricardo Artur Staroski
 */
@SuppressWarnings("unchecked")
public final class MultiKey implements Comparable<MultiKey> {

    private final int hashCode;
    private final List<Object> objects;

    /**
     * Instancia uma nova {@link MultiKey chave composta}.
     * 
     * @param key1 O primeiro objeto da chave
     * @param key2 O segundo objeto da chave.
     * @param moreKeys Os outros objetos da chave. (<B><I>Opcional</I></B>)
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
            // se os hashes são diferentes, então de cara já sei que os objetos também são diferentes
            if (this.hashCode != that.hashCode) {
                return false;
            }
            // objetos diferentes podem ter hashes iguais, então comparo eles
            return this.objects.equals(that.objects);
        }
        return false;
    }

    /**
     * Obtém o objeto do índice informado desta {@link MultiKey chave composta}.
     * 
     * @param index O índece do objeto que se deseja obter.
     * @return O objeto no índice informado.
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
