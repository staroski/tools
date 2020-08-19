package br.com.staroski.tools.brdocs;

import java.util.Random;

/**
 * Classe utilit�ria com m�todos est�ticos para:<br>
 * <ul>
 * <li>Verificar se o CPF informado � v�lido: {@link Documentos#cpfValido(String)};</li>
 * <li>Gerar um CPF aleat�rio v�lido: {@link Documentos#cpfAleatorio()};</li>
 * <li>Verificar se o CNPJ informado � v�lido: {@link Documentos#cnpjValido(String)};</li>
 * <li>Gerar um CNPJ aleat�rio v�lido: {@link Documentos#cnpjAleatorio()}.</li>
 * </ul>
 * 
 * @author <a href="https://github.com/staroski">Ricardo Artur Staroski</a>
 *
 */
public final class Documentos {

    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_CNPJ = 14;

    private static final int[] PESOS_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] PESOS_CNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    /**
     * Gera um CNPJ aleat�rio v�lido.
     * 
     * @return O CNPJ gerado.
     * 
     * @see Documentos#cpfValido(String)
     * @see Documentos#cpfAleatorio()
     * @see Documentos#cnpjValido(String)
     */
    public static String cnpjAleatorio() {
        String digitos = digitos(TAMANHO_CNPJ - 2);
        String verificador1 = verificador(digitos, PESOS_CNPJ);
        String verificador2 = verificador(digitos + verificador1, PESOS_CNPJ);
        return digitos + verificador1 + verificador2;
    }

    /**
     * Verifica se o CNPJ informado � v�lido.
     * 
     * @param cnpj
     *            O CNPJ a ser verificado.
     * 
     * @return <code>true</code> se o CNPJ informado for v�lido e <code>false</code> caso contr�rio.
     * 
     * @see Documentos#cpfValido(String)
     * @see Documentos#cpfAleatorio()
     * @see Documentos#cnpjAleatorio()
     */
    public static boolean cnpjValido(final String cnpj) {
        if (cnpj == null) {
            return false;
        }
        if (cnpj.length() != TAMANHO_CNPJ) {
            return false;
        }
        if (cnpj.matches(cnpj.charAt(0) + "{" + TAMANHO_CNPJ + "}")) {
            return false;
        }
        String digitos = cnpj.substring(0, TAMANHO_CNPJ - 2);
        String verificador1 = verificador(digitos, PESOS_CNPJ);
        String verificador2 = verificador(digitos + verificador1, PESOS_CNPJ);
        return (digitos + verificador1 + verificador2).equals(cnpj);
    }

    /**
     * Gera um CPF aleat�rio v�lido.
     * 
     * @return O CPF gerado.
     * 
     * @see Documentos#cpfValido(String)
     * @see Documentos#cnpjValido(String)
     * @see Documentos#cnpjAleatorio()
     */
    public static String cpfAleatorio() {
        String digitos = digitos(TAMANHO_CPF - 2);
        String verificador1 = verificador(digitos, PESOS_CPF);
        String verificador2 = verificador(digitos + verificador1, PESOS_CPF);
        return digitos + verificador1 + verificador2;
    }

    /**
     * Verifica se o CPF informado � v�lido.
     * 
     * @param cpf
     *            O CPF a ser verificado.
     * 
     * @return <code>true</code> se o CPF informado for v�lido e <code>false</code> caso contr�rio.
     * 
     * @see Documentos#cpfAleatorio()
     * @see Documentos#cnpjValido(String)
     * @see Documentos#cnpjAleatorio()
     */
    public static boolean cpfValido(final String cpf) {
        if (cpf == null) {
            return false;
        }
        if (cpf.length() != TAMANHO_CPF) {
            return false;
        }
        if (cpf.matches(cpf.charAt(0) + "{" + TAMANHO_CPF + "}")) {
            return false;
        }
        String digitos = cpf.substring(0, TAMANHO_CPF - 2);
        String verificador1 = verificador(digitos, PESOS_CPF);
        String verificador2 = verificador(digitos + verificador1, PESOS_CPF);
        return (digitos + verificador1 + verificador2).equals(cpf);
    }

    /**
     * Utilizado internamente para gerar determinada quantidade de d�gitos.
     */
    private static String digitos(int quantidade) {
        StringBuilder digitos = new StringBuilder();
        Random random = new Random();
        for (int contador = 0; contador < quantidade; contador++) {
            digitos.append(random.nextInt(10));
        }
        return digitos.toString();
    }

    /**
     * Utilizado internamente para gerar um d�gito verificador.
     */
    private static String verificador(String digitos, int[] pesos) {
        int soma = 0;
        int qtdPesos = pesos.length;
        int qtdDigitos = digitos.length();
        for (int posicao = qtdDigitos - 1; posicao >= 0; posicao--) {
            int digito = Character.getNumericValue(digitos.charAt(posicao));
            soma += digito * pesos[qtdPesos - qtdDigitos + posicao];
        }
        soma = 11 - soma % 11;
        return String.valueOf(soma > 9 ? 0 : soma);
    }

    // Construtor privado, n�o faz sentido instanciar esta classe
    private Documentos() {}
}