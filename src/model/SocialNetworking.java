package model;

public class SocialNetworking {
	private long socNetId;
	private boolean facebook;
	private boolean google;
	
	public SocialNetworking(boolean facebook, boolean google) {
		this.facebook = facebook;
		this.google = google;
	}

	
	
	public long getSocNetId() {
		return socNetId;
	}

	public boolean isFacebook() {
		return facebook;
	}

	public boolean isGoogle() {
		return google;
	}

	public void setSocNetId(long socNetId) {
		this.socNetId = socNetId;
	}
	
}
