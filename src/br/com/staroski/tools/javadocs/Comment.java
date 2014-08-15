package br.com.staroski.tools.javadocs;

final class Comment {

	private int start;
	private int end;
	private String text;

	public Comment(int start, String text) {
		this.start = start;
		this.text = text;
		this.end = start + text.length();
	}

	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}