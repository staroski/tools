package br.com.staroski.tools.javadocs;

import static br.com.staroski.tools.io.IO.*;

import java.io.*;
import java.util.*;

/**
 * Classe utilit&aacute;ria para escapar o c&oacute;digo HTML dos Javadocs em fontes Java.<BR><BR>
 * Utilize a em seus fontes da seguinte forma:
	<PRE>
	JavadocEscaper escaper = new JavadocEscaper();
	escaper.scan(new File(diretorio));
	</PRE>
 * 	Ou ent&atilde;o execute a como uma classe execut&aacute;vel Java da seguinte forma:
	<PRE>
	&lt;java&gt; &lt;jvm-args&gt; br.com.staroski.tools.javadocs.JavadocEscaper &lt;diretorios&gt;
	</PRE>
	onde:
	<PRE>
	&lt;java&gt;         a m&aacute;quina virtual java a ser utilizada, por exemplo: java, javaw
	&lt;jvm-args&gt;     os par&acirc;metros da m&aacute;quina virtual
	&lt;diretorios&gt;   os diret&oacute;rios, separados por espa&ccedil;o, a terem seus arquivos .java escapados
	</PRE>
 * 
 * @author Ricardo Artur Staroski
 */
public final class JavadocEscaper {

	/**
	 * Este &eacute; o ponto de entrada desta classe execut&aacute;vel.<BR><BR>
	execute da seguinte forma:
	<PRE>
	&lt;java&gt; &lt;jvm-args&gt; br.com.staroski.tools.javadocs.JavadocEscaper &lt;diretorios&gt;
	</PRE>
	onde:
	<PRE>
	&lt;java&gt;         a m&aacute;quina virtual java a ser utilizada, por exemplo: java, javaw
	&lt;jvm-args&gt;     os par&acirc;metros da m&aacute;quina virtual
	&lt;diretorios&gt;   os diret&oacute;rios, separados por espa&ccedil;o, a terem seus arquivos .java escapados
	</PRE>
	 * @param args Os diret&oacute;rios a serem varridos
	 */
	public static void main(String[] args) {
		try {
			checkArgs(args);
			JavadocEscaper escaper = new JavadocEscaper();
			for (String dir : args) {
				escaper.scan(new File(dir));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void checkArgs(String[] args) {
		int count = args == null ? 0 : args.length;
		if (count < 1) {
			throw new IllegalArgumentException("parâmetros inválidos!\nexecute da seguinte forma:\n" //
					+ "\n" //
					+ "    <java> <jvm-args> " + JavadocEscaper.class.getName() + " <diretorios>\n" //
					+ "\n" //
					+ "onde:\n" //
					+ "    <java>         a máquina virtual java a ser utilizada, por exemplo: java, javaw\n" //
					+ "    <jvm-args>     os parâmetros da máquina virtual\n" //
					+ "    <diretorios>   os diretórios, separados por espaço, a terem seus arquivos .java escapados\n" //
			);
		}
	}

	/**
	 * Varre o diret&oacute;rio informado, escapando os Javadocs dos fontes Java encontrados
	 * @param dir O diret&oacute;rio a ser varrido
	 * @throws IOException
	 */
	public void scan(File dir) throws IOException {
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("\"" + dir.getAbsolutePath() + "\" is not a valid directory");
		}
		File[] children = dir.listFiles();
		for (File child : children) {
			if (child.isDirectory()) {
				scan(child);
			} else if (child.getName().endsWith(".java")) {
				escapeJavaFile(child);
			}
		}
	}

	private StringBuilder adjustText(StringBuilder text, List<Comment> comments) {
		int offset = 0;
		for (Comment comment : comments) {
			int start = offset + comment.getStart();
			int end = offset + comment.getEnd();
			String oldText = text.substring(start, end);
			String newText = HtmlEscaper.escape(comment.getText());
			offset += newText.length() - oldText.length();
			text.delete(start, end);
			text.insert(start, newText);
		}
		return text;
	}

	private void escapeJavaFile(File file) throws IOException {
		System.out.println("escapando comentarios do arquivo \"" + file.getAbsolutePath() + "\"...");
		StringBuilder text = readText(file);
		List<Comment> comments = Comment.extractComments(text);
		text = adjustText(text, comments);
		writeText(text, file);
		System.out.println("arquivo escapado!");
	}

	private StringBuilder readText(File file) throws IOException {
		StringBuilder text = new StringBuilder();
		List<String> lines = readLines(file);
		for (int i = 0, n = lines.size(); i < n; i++) {
			if (i > 0) {
				text.append('\n');
			}
			text.append(lines.get(i));
		}
		return text;
	}

	private void writeText(StringBuilder text, File file) throws IOException {
		List<String> lines = Arrays.asList(text.toString().split("\\n"));
		writeLines(file, lines);
	}

}