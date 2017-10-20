package model;

public class Tag {
	private long tagId;
	private String tagName;
	
	public Tag(String name) {
		this.tagName = name;
	}
	
	public Tag(long tagId,String name) {
		this(name);
		this.tagId = tagId;
	}
	
	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}
}
