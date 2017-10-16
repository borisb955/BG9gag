package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import model.Upvote;
import model.User;

public class UserDao {
	private static UserDao instance;
	
	private UserDao() {}
	
	public static synchronized UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	public void insertUser(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.users(username, password, email) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getEmail());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		u.setId(rs.getLong(1));
	}
	
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
		    emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		   return result;
	}
	
	public static boolean emailExists(String email) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(1) FROM 9gag.users WHERE email = ?");
		ps.setString(1, email);
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		rs.next();
		int count = rs.getInt(1);
		
		
		return count > -1;
	}
	
	public static boolean userExists(String username) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(1) FROM 9gag.users WHERE username = ?");
		ps.setString(1, username);
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		rs.next();
		int count = rs.getInt(1);
		
		
		return count > -1;
	}

	public static boolean passwordMatch(String email, String writtenPass) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT password FROM 9gag.users WHERE email = ?");
		ps.setString(1, email);
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		rs.next();
		String realPass = rs.getString(1);
		
		if(realPass.equals(writtenPass)) {
			return true;
		}
		return false;
	}
	
	public User getUser(String username) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT user_id ,user_name, password, email, "
				+ "upvotes_hidden, profile_id FROM 9gag.users WHERE username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		//да се види дали ще се получи безкрайна рекурсия при коментарите
		User u = new User(rs.getLong("user_id"), 
						rs.getString("user_name"), 
						rs.getString("password"), 
						rs.getString("email"),
						ProfileDao.getInstance().getProfile(rs.getLong("profile_id")));
		
		u.setUpvotes(UpvoteDao.getInstance().getUpvotes(u));
		u.setPosts(PostDao.getInstance().getAllPostsForUser(u));

		return u;
	}
}
