package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.PostTag;
import model.Upvote;

public class PostTagDao {
	private static PostTagDao instance;
	
	private PostTagDao() {}
	
	public static synchronized PostTagDao getInstance() {
		if(instance == null) {
			instance = new PostTagDao();
		}
		return instance;
	}
	
	public void insertPostTag(PostTag pt) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.posts_tags(post_id, tag_id) VALUES(?, ?)");
		ps.setLong(1, pt.getPost().getPost_id());
		ps.setLong(2, pt.getTag().getTag_id());
		ps.executeUpdate();
	}
}
