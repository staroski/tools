package br.com.staroski.tools.javadocs;

import java.util.*;

final class Comment {

	private static Comment extractComment(StringBuilder text, int start) {
		StringBuilder commentText = new StringBuilder();
		int i = start - 2;
		commentText.append(text.charAt(i++));
		commentText.append(text.charAt(i++));
		boolean previous_was_star = false;
		int length = text.length();
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

	public static List<Comment> extractComments(StringBuilder text) {
		List<Comment> comments = new ArrayList<Comment>();

		boolean previous_was_slash = false;
		boolean previous_was_star = false;
		int index = 0;
		int length = text.length();
		while (index < length) {
			switch (text.charAt(index)) {
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

						Comment comment = extractComment(text, index);
						comments.add(comment);
						index = comment.getEnd();
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
			index++;
		}
		return comments;
	}

	private int start;
	private int end;
	private String text;

	private Comment(int start, String text) {
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