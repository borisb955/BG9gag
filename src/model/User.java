package model;

import java.util.TreeSet;

public class User {
	private long id;
	private String username;
	private String password;
	private String email;
	private Profile profile;
	private TreeSet<Upvote> upvotes;
	private TreeSet<Post> posts;
	private TreeSet<Comment> comments;
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public User(Long id, String username, String password, String email, Profile profile) {
		this(username, password, email);
		this.profile = profile;
	}
	
	
	
	public void setComments(TreeSet<Comment> comments) {
		this.comments = comments;
	}
	
	public void setPosts(TreeSet<Post> posts) {
		this.posts = posts;
	}
	
	public void setUpvotes(TreeSet<Upvote> upvotes) {
		this.upvotes = upvotes;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public long getId() {
		return id;
	}
	
}
