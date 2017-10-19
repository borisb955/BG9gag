package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Profile;
import model.User;

public class ProfileDao {
	private static ProfileDao instance;
	
	private ProfileDao() {}
	
	public static synchronized ProfileDao getInstance() {
		if(instance == null) {
			instance = new ProfileDao();
		}
		return instance;
	}
	
	public void insertProfile(Profile p) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.profiles(avatar_url, full_name, gender, "
				+ "date_of_birth, info, soc_net_id) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getAvatarUrl());
		ps.setString(2, p.getFullName());
		ps.setString(3, p.getGender());
		ps.setString(4, p.getDateOfBirth());
		ps.setString(5, p.getInfo());
		ps.setLong(6, p.getSocNet().getSocNetId());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.setProfileId(rs.getLong("profile_id"));
	}
	
	
	public Profile getProfile(long profileID) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT avatar_url, full_name, gender, info, birthday_date"
				+ ", social_net_id FROM 9gag.profiles WHERE profile_id = ?");
		ps.setLong(1, profileID);
		ResultSet rs = ps.executeQuery();
	
		
		if(rs.next()) {
			//TODO: is there a better way to check if empty
			return new Profile(rs.getString("avatar_url").isEmpty() ? null : rs.getString("avatar_url"), 
					rs.getString("full_name").isEmpty() ? null : rs.getString("full_name"), 
					rs.getString("gender").isEmpty() ? null : rs.getString("gender"),
					rs.getString("birthday_date").isEmpty() ? null : rs.getString("birthday_date"), 
					rs.getString("info").isEmpty() ? null : rs.getString("info"), 
					SocialNetworkingDao.getInstance().getSocNet(rs.getLong("social_net_id")));
		}
		return null;
	}
}
