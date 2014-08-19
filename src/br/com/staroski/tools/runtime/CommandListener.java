package br.com.staroski.tools.runtime;

/**
 * Listener da classe {@link Command} utilizado para receber as mensagens de sa&iacute;da e de erro do processo executado.
 * 
 * @see Command
 * 
 * @author Ricardo Artur Staroski
 */
public interface CommandListener {

	/**
	 * <a href="http://en.wikipedia.org/wiki/Null_Object_pattern">Null Object</a> desta {@link CommandListener interface}.
	 */
	public static final CommandListener NULL = new CommandListener() {

		@Override
		public void receive(String text) {
			// não faz nada
		}
	};

	/**
	 * Recebe uma mensagem do processo em execu&ccedil;&atilde;o.
	 * 
	 * @param message
	 *            A mensagem recebida.
	 */
	void receive(String message);
}