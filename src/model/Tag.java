package model;

public class Tag {
	private long tag_id;
	private String tag_name;
	
	public Tag(String name) {
		this.tag_name = name;
	}
	
	public Tag(long tag_id,String name) {
		this(name);
		this.tag_id = tag_id;
	}
	
	public long getTag_id() {
		return tag_id;
	}

	public void setTag_id(long tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_name() {
		return tag_name;
	}
}
