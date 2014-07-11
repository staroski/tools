package br.com.staroski.tools.javadocs;

import static br.com.staroski.tools.io.IO.*;

import java.io.*;
import java.util.*;

public final class HtmlEscaper {

	public static void main(String[] args) {
		try {
			checkArgs(args);
			HtmlEscaper escaper = new HtmlEscaper();
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
					+ "    <java> <jvm-args> " + HtmlEscaper.class.getName() + " <diretorios>\n" //
					+ "\n" //
					+ "onde:\n" //
					+ "    <diretorios>   os diretórios, separados por espaço, a terem seus arquivos .java escapados\n");
		}
	}

	public String escapeHtml(String s) {
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

	private void escapeJavaFile(File file) throws IOException {
		System.out.println("escapando arquivo \"" + file.getAbsolutePath() + "\"");
		List<String> lines = readLines(file);
		writeEscaping(lines, file);
	}

	private void writeEscaping(List<String> lines, File file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		for (String line : lines) {
			line = escapeHtml(line);
			writer.println(line);
		}
		writer.flush();
		writer.close();
	}
}
