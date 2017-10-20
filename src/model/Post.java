package model;

import java.time.LocalDateTime;

public class Post{
	private long postId;
	private String description;
	private String postUrl;
	private int points;
	private LocalDateTime dateTime;
	private User user;
	
	public Post(String text, String post_url, LocalDateTime dateTime, User user) {
		this.description = text;
		this.postUrl = post_url;
		this.dateTime = dateTime;
		this.user = user;
		this.points = 0;
	}

	public Post(long post_id, String text, String post_url, int points, LocalDateTime dateTime, User user) {
		this(text, post_url, dateTime, user);
		this.postId = post_id;
		this.points = points;
	}
	
	
	
	public void setPostId(long post_id) {
		this.postId = post_id;
	}

	public long getPostId() {
		return postId;
	}

	public String getDescription() {
		return description;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public User getUser() {
		return user;
	}
	
	public int getPoints() {
		return points;
	}
	
	
	
	
}
