package model;

public class PostTag {
	private Post post;
	private Tag tag;
	
	public PostTag(Post post, Tag tag) {
		this.post = post;
		this.tag = tag;
	}

	
	public Post getPost() {
		return post;
	}

	public Tag getTag() {
		return tag;
	}
}
