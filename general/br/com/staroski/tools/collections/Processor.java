package br.com.staroski.tools.collections;

/**
 * Interface para processadores de objetos.
 * 
 * @author Ricardo Artur Staroski
 * 
 * @param <T>
 *            Tipo de dado do objeto que será processado.
 */
public interface Processor<T> {

    /**
     * @see #object() Processor.Null.object()
     */
    public static final class Null {

        /**
         * @return Uma instância padrão <I>Null Object</I> da interface {@link Processor}
         */
        public static <T> Processor<T> object() {
            return new ProcessorAdapter<T>() {

                @Override
                public T process(T object) {
                    return object;
                }
            };
        }

        // não instanciável
        private Null() {}
    }

    /**
     * Invocado após finalizar o processamento.
     */
    public void afterFinish();

    /**
     * Invocado antes de iniciar o processamento.
     */
    public void beforeStart();

    /**
     * @return O resultado do processamento ou <code>null</code>.
     */
    public <V> V getResult();

    /**
     * Processa o objeto informado.
     * 
     * @param object
     *            O Objecto a ser processado
     * @return O objeto processado
     */
    public T process(T object);
}
