package br.com.staroski.tools.delphi;

/**
 * Classe para convers&otilde;es de datas entre o <B>Java</B> e o <B>Delphi</B>
 * 
 * @author Ricardo Artur Staroski
 */
public final class JavaAndDelphiTimes {

    /**
     * Diferen&ccedil;a em dias entre a "data inicial" do Java (01/01/1970) e a "data inicial" do Delphi (30/12/1899), o que coresponde a 25569 dias
     * 
     * <pre>
     * Date java = new SimpleDateFormat(&quot;dd/MM/yyyy&quot;).parse(&quot;01/01/1970&quot;); // Data inicial do Java
     * Date delphi = new SimpleDateFormat(&quot;dd/MM/yyyy&quot;).parse(&quot;30/12/1899&quot;); // Data inicial do Delphi
     * long millis = java.getTime() - delphi.getTime(); // Diferen&ccedil;a em milissegundos
     * DAYS_DIFERENCE = millis / 1000 / 60 / 60 / 24; // 25569 dias
     * </pre>
     */
    private static final int DAYS_DIFERENCE = 25569;

    /**
     * Quantidade de milissegundos que comp&otilde;e um dia <code><pre>  
     * MILLIS_PER_DAY = 24 * 60 * 60 * 1000; // 86400000 ms  
     * </pre></code>
     */
    private static final int MILLIS_PER_DAY = 86400000;

    /**
     * Converte um instante de tempo do formato do <B>Delphi</B> para o formato do <B>Java</B><BR>
     * <BR>
     * - No <B>Delphi</B> o instante &eacute; um <B>double</B> cuja <B>parte inteira</B> corresponde a <B>quantidade de dias</B> que se passaram <B>desde 30/12/1899</B> e a <B>parte
     * fracion&aacute;ria</B> &eacute; uma <B>fra&ccedil;&atilde;o das 24 horas de um dia</B><BR>
     * <BR>
     * - No <B>Java</B> o instante &eacute; um <B>long</B> que corresponde a <B>quantidade de milisegundos</B> que passaram <B>desde 01/01/1970</B>
     */
    public static long delphiToJava(final double delphiDays) {
        return Math.round((delphiDays - DAYS_DIFERENCE) * MILLIS_PER_DAY);
    }

    /**
     * Converte um instante de tempo do formato do <B>Java</B> para o formato do <B>Delphi</B><BR>
     * <BR>
     * - No <B>Java</B> o instante &eacute; um <B>long</B> que corresponde a <B>quantidade de milisegundos</B> que passaram <B>desde 01/01/1970</B><BR>
     * <BR>
     * - No <B>Delphi</B> o instante &eacute; um <B>double</B> cuja <B>parte inteira</B> corresponde a <B>quantidade de dias</B> que se passaram <B>desde 30/12/1899</B> e a <B>parte
     * fracion&aacute;ria</B> &eacute; uma <B>fra&ccedil;&atilde;o das 24 horas de um dia</B>
     */
    public static double javaToDelphi(final long javaMillis) {
        return (javaMillis / (double) MILLIS_PER_DAY) + DAYS_DIFERENCE;
    }

    // classe utilitária não-instanciável
    private JavaAndDelphiTimes() {}
}