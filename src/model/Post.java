package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post{
	private long postId;
	private String description;
	private String postUrl;
	private int points;
	private LocalDateTime dateTime;
	private User user;
	private ArrayList<Tag> tags;
	
	public Post(String text, String postUrl, LocalDateTime dateTime, User user) {
		this.description = text;
		this.postUrl = postUrl;
		this.dateTime = dateTime;
		this.user = user;
		this.points = 0;
	}

	public Post(long postId, String text, String postUrl, int points, LocalDateTime dateTime, User user,
			ArrayList<Tag> tags) {
		this(text, postUrl, dateTime, user);
		this.postId = postId;
		this.points = points;
		this.tags = tags;
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
	
	public ArrayList<Tag> getTags() {
		return tags;
	}
	
	
}
