package br.com.staroski.tools.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe utilitária para manipulação de coleções.
 * 
 * @author Ricardo Artur Staroski
 */
@SuppressWarnings("unchecked")
public final class CollectionHandler {

    /**
     * Filtra uma {@link Collection coleção} de objetos, retornando uma {@link List lista} com aqueles que atendem ao {@link Predicate predicado} informado.<BR>
     * <B>Observações</B><BR>
     * O método {@link Predicate#apply(Object) apply(Object)} do {@link Predicate predicado} é invocado para cada objeto iterado na coleção.
     * 
     * @param collection A {@link Collection coleção} a ser filtrada.
     * @param predicate O {@link Predicate predicado} utilizado para filtrar a {@link Collection coleção}.
     * @return Uma {@link List lista} contendo os objetos que atendem ao {@link Predicate predicado} informado.
     * @see #filterAndProcess(Collection, Predicate, Processor)
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        return filterAndProcess(collection, predicate, (Processor<T>) Processor.Null.object());
    }

    /**
     * Processa uma {@link Collection coleção} de objetos com o {@link Processor processador} informado, retornando uma {@link List lista} com os elementos processados.<BR>
     * <B>Observações</B><BR>
     * O método {@link Processor#beforeStart() beforeStart()} do {@link Processor processador} é invocado uma única vez antes da coleção ser iterada.<BR>
     * O método {@link Processor#afterFinish() afterFinish()} do {@link Processor processador} é invocado uma única vez após a coleção ser iterada.
     * 
     * @param collection A {@link Collection coleção} a ser filtrada.
     * @param processor O {@link Processor processador} a ser utilizado.
     * @return Uma {@link List lista} contendo os objetos processados.
     * @see #filterAndProcess(Collection, Predicate, Processor)
     */
    public static <T> List<T> process(Collection<T> collection, Processor<T> processor) {
        return filterAndProcess(collection, (Predicate<T>) Predicate.Null.object(), processor);
    }

    /**
     * Filtra e processa uma {@link Collection coleção} de objetos, retornando uma {@link List lista} com aqueles que atendem ao {@link Predicate predicado} informado.<BR>
     * Durante a filtragem, os objetos que atendem ao {@link Predicate predicado}, são processados através do {@link Processor processador} informado.<BR>
     * <B>Observações</B><BR>
     * O método {@link Processor#beforeStart() beforeStart()} do {@link Processor processador} é invocado uma única vez antes da coleção ser iterada.<BR>
     * O método {@link Predicate#apply(Object) apply(Object)} do {@link Predicate predicado} é invocado para cada objeto iterado na coleção.<BR>
     * O método {@link Processor#afterFinish() afterFinish()} do {@link Processor processador} é invocado uma única vez após a coleção ser iterada.
     * 
     * @param collection A {@link Collection coleção} a ser filtrada.
     * @param predicate O {@link Predicate predicado} utilizado para filtrar a {@link Collection coleção}.
     * @param processor O {@link Processor processador} utilizado para processar o objeto que atendeu ao {@link Predicate predicado}.
     * @return Uma {@link List lista} contendo os objetos processados que atenderam ao {@link Predicate predicado}.
     * @see #filter(Collection, Predicate)
     * @see #process(Collection, Processor)
     */
    public static <T> List<T> filterAndProcess(Collection<T> collection, Predicate<T> predicate, Processor<T> processor) {
        if (collection == null) throw new IllegalArgumentException("collection cannot be null");
        if (predicate == null) throw new IllegalArgumentException("predicate cannot be null");
        if (processor == null) throw new IllegalArgumentException("processor cannot be null");
        final List<T> list = new ArrayList<T>();
        synchronized(processor) { // só pra garantir que ninguém de fora manipule o processador enquanto estiver processando
            processor.beforeStart();
            for (T object : collection) {
                if (predicate.apply(object)) {
                    list.add(processor.process(object));
                }
            }
            processor.afterFinish();
        }
        return list;
    }

    // classe utilitária não instanciável
    private CollectionHandler() {}
}
