package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.TreeSet;

import model.Post;
import model.Upvote;
import model.User;

public class PostDao {
	private static PostDao instance;
	
	private PostDao() {}
	
	public static synchronized PostDao getInstance() {
		if(instance == null) {
			instance = new PostDao();
		}
		return instance;
	}
	
	public void insertPost(Post p) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.posts(description, post_url, upload_date"
				+ "user_id) VALUES(?, ?, ?, ?)");
		ps.setString(1, p.getDescription());
		ps.setString(2, p.getPost_url());
		ps.setTimestamp(3, Timestamp.valueOf(p.getDateTime()));
		ps.setLong(4, p.getUser().getId());
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		p.setPost_id(rs.getLong("post_id"));
	}
	
	public Post getPost(long postId, User u) throws SQLException{
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT description, post_url"
				+ ", upload_date FROM 9gag.posts WHERE post_id = ?");
		ps.setLong(1, postId);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		return new Post(postId,
						rs.getString("description"), 
						rs.getString("post_url"), 
						rs.getTimestamp("upload_date").toLocalDateTime(), 
						u);
	}

	public TreeSet<Post> getAllPostsForUser(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT post_id, description, post_url, points,"
				+ ", upload_date FROM 9gag.posts WHERE user_id = ?");
		ps.setLong(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		TreeSet<Post> posts = new TreeSet<>();
		while(rs.next()) {
			posts.add(new Post(rs.getLong("post_id"),
							   rs.getString("description"), 
							   rs.getString("post_url"), 
							   rs.getTimestamp("upload_date").toLocalDateTime(),
							   u));
		}
		return posts;
	}
}
