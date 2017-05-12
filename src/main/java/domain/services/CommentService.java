package domain.services;

import java.util.ArrayList;

import domain.Comment;

public class CommentService {
	private static ArrayList<Comment> db = new ArrayList<Comment>();
	private static int currentId = 0;
	
	public int getCurrentId() {
		return currentId;
	}
	
	public Comment get(int id) {
		for (Comment c : db) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public void add(Comment comment) {
		comment.setId(++currentId);
		db.add(comment);
	}
	
	public void delete(Comment comment) {
		db.remove(comment);
	}
}
