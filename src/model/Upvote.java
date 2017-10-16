package model;

import java.time.LocalDateTime;

public class Upvote implements Comparable<Upvote>{
	private User user;
	private Post post;
	private LocalDateTime dateTime;
	
	public Upvote(Post post, LocalDateTime  dateTime) {
		this.post = post;
		this.dateTime = dateTime;
	}
	
	public Upvote(User user, Post post, LocalDateTime dateTime) {
		this(post, dateTime);
		this.user = user;
	}
	
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}

	@Override
	public int compareTo(Upvote o) {
		return this.dateTime.compareTo(o.getDateTime());
	}
		
	
}
