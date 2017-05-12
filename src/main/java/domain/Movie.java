package domain;

import java.util.ArrayList;

public class Movie {
	private int id;
	private String name;
	private String description;
	private ArrayList<Integer> scoreList = new ArrayList<Integer>();
	private ArrayList<Comment> commentList = new ArrayList<Comment>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Integer> getScoreList() {
		return scoreList;
	}
	public void setScoreList(ArrayList<Integer> scoreList) {
		this.scoreList = scoreList;
	}
	
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}
	
	public void addScore(int score) {
		this.scoreList.add(score);
	}
	
	public double getAverageScore() {
		double score = 0;
		
		for (int i = 0; i < this.scoreList.size(); i++) {
			score += this.scoreList.get(i);
		}
		
		score = score / this.scoreList.size();
		
		score = score * 100;
		score = Math.round(score);
		score = score / 100;
		
		return score;
	}
	
	public void addComment(Comment comment) {
		commentList.add(comment);
	}
}
