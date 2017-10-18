package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

import model.Post;
import model.Upvote;
import model.User;

public class UpvoteDao {
	private static UpvoteDao instance;
	
	private UpvoteDao() {}
	
	public static synchronized UpvoteDao getInstance() {
		if(instance == null) {
			instance = new UpvoteDao();
		}
		return instance;
	}
	
	public void insertUpvote(Upvote upv) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.upvotes(user_id, post_id, upvote_date)"
												+ " VALUES(?, ?, ?)");
		ps.setLong(1, upv.getUser().getId());
		ps.setLong(2, upv.getPost().getPost_id());
		ps.setTimestamp(3, Timestamp.valueOf(upv.getDateTime()));
		ps.executeUpdate();
	}
	
	public ArrayList<Upvote> getUpvotes(User u) throws SQLException{
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT post_id, upvote_date FROM 9gag.upvotes "
													+ "WHERE user_id = ?"
													+ "ORDER BY upvote_date");
		ps.setLong(1, u.getId());
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		
		ArrayList<Upvote> upv = new ArrayList<>();
		while(rs.next()) {
			String ldt = rs.getDate("upvote_date").toString();
			//
			//Is there a point to add the user in the constructor?
			upv.add(new Upvote(PostDao.getInstance().getPost(rs.getLong("post_id"), u), 
							   rs.getTimestamp("upvote_date").toLocalDateTime()));
		}
		return upv;
	}
}
