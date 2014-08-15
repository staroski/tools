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

	public static String escapeHtml(String s) {
		StringBuilder sb = new StringBuilder();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
				case 'á':
					sb.append("&aacute;");
					break;
				case 'Á':
					sb.append("&Aacute;");
					break;
				case 'ã':
					sb.append("&atilde;");
					break;
				case 'Ã':
					sb.append("&Atilde;");
					break;
				case 'à':
					sb.append("&agrave;");
					break;
				case 'À':
					sb.append("&Agrave;");
					break;
				case 'â':
					sb.append("&acirc;");
					break;
				case 'Â':
					sb.append("&Acirc;");
					break;
				case 'ä':
					sb.append("&auml;");
					break;
				case 'Ä':
					sb.append("&Auml;");
					break;
				case 'å':
					sb.append("&aring;");
					break;
				case 'Å':
					sb.append("&Aring;");
					break;
				case 'æ':
					sb.append("&aelig;");
					break;
				case 'Æ':
					sb.append("&AElig;");
					break;
				case 'ç':
					sb.append("&ccedil;");
					break;
				case 'Ç':
					sb.append("&Ccedil;");
					break;
				case 'é':
					sb.append("&eacute;");
					break;
				case 'É':
					sb.append("&Eacute;");
					break;
				case 'è':
					sb.append("&egrave;");
					break;
				case 'È':
					sb.append("&Egrave;");
					break;
				case 'ê':
					sb.append("&ecirc;");
					break;
				case 'Ê':
					sb.append("&Ecirc;");
					break;
				case 'ë':
					sb.append("&euml;");
					break;
				case 'Ë':
					sb.append("&Euml;");
					break;
				case 'í':
					sb.append("&iacute;");
					break;
				case 'Í':
					sb.append("&Iacute;");
					break;
				case 'ï':
					sb.append("&iuml;");
					break;
				case 'Ï':
					sb.append("&Iuml;");
					break;
				case 'ó':
					sb.append("&oacute;");
					break;
				case 'Ó':
					sb.append("&Oacute;");
					break;
				case 'õ':
					sb.append("&otilde;");
					break;
				case 'Õ':
					sb.append("&Otilde;");
					break;
				case 'ô':
					sb.append("&ocirc;");
					break;
				case 'Ô':
					sb.append("&Ocirc;");
					break;
				case 'ö':
					sb.append("&ouml;");
					break;
				case 'Ö':
					sb.append("&Ouml;");
					break;
				case 'ø':
					sb.append("&oslash;");
					break;
				case 'Ø':
					sb.append("&Oslash;");
					break;
				case 'ß':
					sb.append("&szlig;");
					break;

				case 'ú':
					sb.append("&uacute;");
					break;
				case 'Ú':
					sb.append("&Uacute;");
					break;
				case 'ù':
					sb.append("&ugrave;");
					break;
				case 'Ù':
					sb.append("&Ugrave;");
					break;
				case 'û':
					sb.append("&ucirc;");
					break;
				case 'Û':
					sb.append("&Ucirc;");
					break;
				case 'ü':
					sb.append("&uuml;");
					break;
				case 'Ü':
					sb.append("&Uuml;");
					break;
				case '®':
					sb.append("&reg;");
					break;
				case '©':
					sb.append("&copy;");
					break;
				case '€':
					sb.append("&euro;");
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

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
			String newText = escapeHtml(comment.getText());
			offset += newText.length() - oldText.length();
			text.delete(start, end);
			text.insert(start, newText);
		}
		return text;
	}

	private void escapeJavaFile(File file) throws IOException {
		System.out.println("escapando comentarios do arquivo \"" + file.getAbsolutePath() + "\"...");
		StringBuilder text = readText(file);
		List<Comment> comments = getComments(text);
		text = adjustText(text, comments);
		writeText(text, file);
		System.out.println("arquivo escapado!");
	}

	private List<Comment> getComments(StringBuilder text) {
		List<Comment> comments = new ArrayList<Comment>();

		boolean previous_was_slash = false;
		boolean previous_was_star = false;
		int i = 0;
		int n = text.length();
		while (i < n) {
			switch (text.charAt(i)) {
				case '/':
					if (previous_was_slash) {
						previous_was_slash = false;
						continue;
					}
					previous_was_slash = true;
					break;
				case '*':
					if (previous_was_star) {
						previous_was_slash = false;
						previous_was_star = false;

						Comment comment = readComment(text, i, n);
						comments.add(comment);
						i = comment.getEnd();
						continue;
					}
					if (previous_was_slash) {
						previous_was_star = true;
					}
					break;
				default:
					previous_was_slash = false;
					previous_was_star = false;
					break;
			}
			i++;
		}
		return comments;
	}

	private Comment readComment(StringBuilder text, int start, int length) {
		StringBuilder commentText = new StringBuilder();
		int i = start - 2;
		commentText.append(text.charAt(i++));
		commentText.append(text.charAt(i++));
		boolean previous_was_star = false;
		LOOP: while (i < length) {
			char letter = text.charAt(i);
			commentText.append(letter);
			switch (letter) {
				case '*':
					previous_was_star = true;
					break;
				case '/':
					if (previous_was_star) {
						break LOOP;
					}
					break;
				default:
					previous_was_star = false;
					break;
			}
			i++;
		}
		Comment comment = new Comment(start - 2, commentText.toString());
		return comment;
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