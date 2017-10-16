package model;

import java.time.LocalDateTime;

public class Comment {
	private long comment_id;
	private String comment;
	private int points;
	private LocalDateTime dateTime;
	private Comment parrent_comment;
	private User user;
	private Post post;
	
	public Comment(String comment, LocalDateTime dateTime, Comment parrent_comment, 
					User user, Post post) {
		this.comment = comment;
		this.points = points;
		this.dateTime = dateTime;
		this.parrent_comment = parrent_comment;
		this.user = user;
		this.post = post;
	}
	
	public Comment(long comment_id, String comment, int points, LocalDateTime dateTime, Comment parrent_comment, 
			User user, Post post) {
		this(comment, dateTime, parrent_comment, user, post);
		this.comment_id = comment_id;
		this.points = points;
	}

	
	
	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment() {
		return comment;
	}

	public int getPoints() {
		return points;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Comment getParrent_comment() {
		return parrent_comment;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}
	
	
	
	

}
