package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Post;
import model.Tag;
import model.User;

public class PostTagDao {
	private static PostTagDao instance;
	
	private PostTagDao() {}
	
	public static synchronized PostTagDao getInstance() {
		if(instance == null) {
			instance = new PostTagDao();
		}
		return instance;
	}
	
	public void insertPostTag(Post p, Tag t) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.posts_tags(post_id, tag_id) "
												   + "VALUES(?, ?)");
		ps.setLong(1, p.getPostId());
		ps.setLong(2, t.getTagId());
		ps.executeUpdate();
	}
	
	public ArrayList<Post> getAllPostsForTag(Tag tag) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		//tags <-> posts_tags <-> posts
		PreparedStatement ps = conn.prepareStatement("SELECT p.post_id, p.description, p.post_url, p.points,"
												+ " p.upload_date, p.user_id "
												+ "FROM 9gag.tags as t "
												+ "JOIN 9gag.posts_tags as pt "
												+ "ON t.tag_id = pt.tag_id "
												+ "WHERE t.tag_name = ? "
												+ "JOIN 9gag.posts as p "
												+ "ON pt.post_id = p.post_id "
												+ "ORDER BY p.upload_date");
		ps.setString(1, tag.getTagName());
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Post> posts = new ArrayList<>();
		while(rs.next()) {
			long postId = rs.getLong("p.post_id");
			posts.add(new Post(postId, 
							   rs.getString("p.description"), 
							   rs.getString("post_url"), 
							   rs.getInt("p.points"), 
							   rs.getTimestamp("p.upload_date").toLocalDateTime(), 
							   UserDao.getInstance().getUserById(rs.getLong("p.user_id")),
							   PostTagDao.getInstance().getTagsForPost(postId)));
		}
		return posts;
	}

	public ArrayList<Tag> getTagsForPost(long postId) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT t.tag_id, t.tag_name FROM 9gag.tags as t "
												   + "JOIN 9gag.posts_tags as pt "
												   + "ON t.tag_id = pt.tag_id "
												   + "WHERE pt.post_id = ?");
		ps.setLong(1, postId);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Tag> tags = new ArrayList<>();
		while(rs.next()) {
			tags.add(new Tag(rs.getLong("t.tag_id"), rs.getString("t.tag_name")));
		}
		return tags;
	}
}
