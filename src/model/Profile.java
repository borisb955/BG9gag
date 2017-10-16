package model;

public class Profile {
	private long profileId;
	private String avatarUrl;
	private String fullName;
	private String gender;
	private String dateOfBirth;
	private String info;
	private SocialNetworking socNet;
	
	public Profile(String avatarUrl, String fullName, String gender, String dateOfBirth, String info,
			SocialNetworking socNet) {
		this.avatarUrl = avatarUrl;
		this.fullName = fullName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.info = info;
		this.socNet = socNet;
	}
	
	public Profile(long profileId,String avatarUrl, String fullName, String gender, String dateOfBirth, String info,
			SocialNetworking socNet) {
		this(avatarUrl, fullName, gender, dateOfBirth, info, socNet);
		this.profileId = profileId;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGender() {
		return gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getInfo() {
		return info;
	}

	public SocialNetworking getSocNet() {
		return socNet;
	}
}
