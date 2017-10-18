package model;

import java.time.LocalDateTime;

public class Post{
	private long post_id;
	private String description;
	private String post_url;
	private int points;
	private LocalDateTime dateTime;
	private User user;
	
	public Post(String text, String post_url, LocalDateTime dateTime, User user) {
		this.description = text;
		this.post_url = post_url;
		this.dateTime = dateTime;
		this.user = user;
		this.points = 0;
	}

	public Post(long post_id, String text, String post_url, int points, LocalDateTime dateTime, User user) {
		this(text, post_url, dateTime, user);
		this.post_id = post_id;
		this.points = points;
	}
	
	
	
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public long getPost_id() {
		return post_id;
	}

	public String getDescription() {
		return description;
	}

	public String getPost_url() {
		return post_url;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public User getUser() {
		return user;
	}
	
	
	
	
}
