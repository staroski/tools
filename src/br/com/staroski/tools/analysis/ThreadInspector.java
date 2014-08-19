package br.com.staroski.tools.analysis;

import java.lang.reflect.Method;

import javax.swing.UIManager;

/**
 * Classe utilit&aacute;ria que exibe a pilha de chamada das {@link Thread threads} em execu&ccedil;&atilde;o.<BR>
 * <BR>
 * Execute seus aplicativos Java a partir desta classe da seguinte forma:
 * 
 * <PRE>
 * &lt;java&gt; &lt;inspector&gt; &lt;main-class&gt; &lt;args&gt;
 * 
 * Onde:
 *     &lt;java&gt;       &eacute; a JVM a ser executada
 *     &lt;inspector&gt;  &eacute; br.com.staroski.analysis.ThreadInspector
 *     &lt;main-class&gt; &eacute; a classe a ser executada
 *     &lt;args&gt;       s&atilde;o os argumentos para o m&eacute;todo 'main' da &lt;main-class&gt; 
 * </PRE>
 * 
 * @author Ricardo Artur Staroski
 */
public final class ThreadInspector {

    /**
     * Ponto de entrada do aplicativo.
     * 
     * @param args Array de {@link String} onde o primeiro argumento &eacute; o nome da classe a ser executada ( <code>&lt;main-class&gt</code> ) e os outros elementos s&atilde;o os argumentos do m&eacute;todo
     *            <code>main</code> ( <code>&lt;args&gt;</code> ) da mesma classe.
     */
    public static void main(String... args) {
        if (args.length < 1) {
            System.out.println("usage: ");
            System.out.println("    <java> <inspector> <main-class> <args>");
            System.out.println("where");
            System.out.println("    <java>       is the JVM to be executed");
            System.out.println("    <inspector>  is " + ThreadInspector.class.getName());
            System.out.println("    <main-class> is the class to be executed");
            System.out.println("    <args>       are the arguments for method 'main' of <main-class>");
            System.exit(0);
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            System.err.println("warning: could not use system's native look & feel");
        }
        try {
            System.out.println("opening inspector...");
            StackViewer.getInstance().show();
            System.out.println("ispector opened!");

            String mainClass = args[0];
            int length = args.length - 1;
            String[] classArgs = new String[length];
            if (length > 0) {
                System.arraycopy(args, 1, classArgs, 0, length);
            }
            System.out.println("loading class '" + mainClass + "'...");
            Class<?> aClass = Class.forName(mainClass);
            System.out.println("class loaded!");

            System.out.println("locating 'main' method...");
            Method main = aClass.getMethod("main", String[].class);
            System.out.println("method located!");

            System.out.println("invoking 'main' method...");
            main.invoke(null, (Object) classArgs);
            System.out.println("method invoked!");
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(0);
        }
    }

    private ThreadInspector() {
        throw new UnsupportedOperationException(getClass().getName() + " can not be instantiated");
    }
}