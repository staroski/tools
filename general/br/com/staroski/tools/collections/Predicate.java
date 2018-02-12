package br.com.staroski.tools.collections;

/**
 * Interface para predicados.
 * 
 * @author Ricardo Artur Staroski
 *
 * @param <T>
 *            Tipo de dado do objeto que será submetido ao predicado.
 */
public interface Predicate<T> {

	/**
	 * @see #object() Predicate.Null.object()
	 */
	public static final class Null {

		/**
		 * @return Uma instância padrão <I>Null Object</I> da interface
		 *         {@link Predicate}
		 */
		public static <T> Predicate<T> object() {
			return new Predicate<T>() {

				@Override
				public boolean apply(T object) {
					return true;
				}
			};
		}

		// não instanciável
		private Null() {
		}
	}

	/**
	 * Verifica se o objeto informado atende à este {@link Predicate predicado}
	 * .
	 * 
	 * @param object
	 *            O objeto a ser verificado.
	 * @return <code>true</code> se o objeto atende à este {@link Predicate
	 *         predicado} e <code>false</code> caso contrário.
	 */
	public boolean apply(T object);
}
