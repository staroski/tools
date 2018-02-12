package br.com.staroski.tools.javadocs;

final class HtmlEscaper {

	public static String escape(String s) {
		StringBuilder sb = new StringBuilder();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '�':
					sb.append("&aacute;");
					break;
				case '�':
					sb.append("&Aacute;");
					break;
				case '�':
					sb.append("&atilde;");
					break;
				case '�':
					sb.append("&Atilde;");
					break;
				case '�':
					sb.append("&agrave;");
					break;
				case '�':
					sb.append("&Agrave;");
					break;
				case '�':
					sb.append("&acirc;");
					break;
				case '�':
					sb.append("&Acirc;");
					break;
				case '�':
					sb.append("&auml;");
					break;
				case '�':
					sb.append("&Auml;");
					break;
				case '�':
					sb.append("&aring;");
					break;
				case '�':
					sb.append("&Aring;");
					break;
				case '�':
					sb.append("&aelig;");
					break;
				case '�':
					sb.append("&AElig;");
					break;
				case '�':
					sb.append("&ccedil;");
					break;
				case '�':
					sb.append("&Ccedil;");
					break;
				case '�':
					sb.append("&eacute;");
					break;
				case '�':
					sb.append("&Eacute;");
					break;
				case '�':
					sb.append("&egrave;");
					break;
				case '�':
					sb.append("&Egrave;");
					break;
				case '�':
					sb.append("&ecirc;");
					break;
				case '�':
					sb.append("&Ecirc;");
					break;
				case '�':
					sb.append("&euml;");
					break;
				case '�':
					sb.append("&Euml;");
					break;
				case '�':
					sb.append("&iacute;");
					break;
				case '�':
					sb.append("&Iacute;");
					break;
				case '�':
					sb.append("&iuml;");
					break;
				case '�':
					sb.append("&Iuml;");
					break;
				case '�':
					sb.append("&oacute;");
					break;
				case '�':
					sb.append("&Oacute;");
					break;
				case '�':
					sb.append("&otilde;");
					break;
				case '�':
					sb.append("&Otilde;");
					break;
				case '�':
					sb.append("&ocirc;");
					break;
				case '�':
					sb.append("&Ocirc;");
					break;
				case '�':
					sb.append("&ouml;");
					break;
				case '�':
					sb.append("&Ouml;");
					break;
				case '�':
					sb.append("&oslash;");
					break;
				case '�':
					sb.append("&Oslash;");
					break;
				case '�':
					sb.append("&szlig;");
					break;

				case '�':
					sb.append("&uacute;");
					break;
				case '�':
					sb.append("&Uacute;");
					break;
				case '�':
					sb.append("&ugrave;");
					break;
				case '�':
					sb.append("&Ugrave;");
					break;
				case '�':
					sb.append("&ucirc;");
					break;
				case '�':
					sb.append("&Ucirc;");
					break;
				case '�':
					sb.append("&uuml;");
					break;
				case '�':
					sb.append("&Uuml;");
					break;
				case '�':
					sb.append("&reg;");
					break;
				case '�':
					sb.append("&copy;");
					break;
				case '�':
					sb.append("&euro;");
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	private HtmlEscaper() {}
}