package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import model.Post;
import model.Tag;
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
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.posts(description, post_url,"
												+ " upload_date, user_id) "
												+ "VALUES(?, ?, ?, ?)");
		ps.setString(1, p.getDescription());
		ps.setString(2, p.getPostUrl());
		ps.setTimestamp(3, Timestamp.valueOf(p.getDateTime()));
		ps.setLong(4, p.getUser().getId());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.setPostId(rs.getLong(1));
	}
	
	public Post getPost(long postId, User u) throws SQLException{
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT description, post_url, points , upload_date "
													+ "FROM 9gag.posts "
													+ "WHERE post_id = ?");
		ps.setLong(1, postId);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		ArrayList<Tag> tags = PostTagDao.getInstance().getTagsForPost(postId);
		String description = rs.getString("description");
		String url =rs.getString("post_url");
		int points = rs.getInt("points");
		LocalDateTime time= rs.getTimestamp("upload_date").toLocalDateTime();
		return new Post(postId,
						description, 
						url, 
						points,
						time, 
						u,
						tags,
						CommentDao.getInstance().getMainCommentsForPost(new Post(postId,description,url,points,time,u,tags,null)));
	}

	public ArrayList<Post> getAllPostsForUser(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT post_id, description, post_url, points"
												+ ", upload_date "
												+ "FROM 9gag.posts "
												+ "WHERE user_id = ? ");
//TODO: Why doesn't work						+ "ORDER BY upload_date");
		ps.setLong(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Post> posts = new ArrayList<>();
		while(rs.next()) {
			long postId = rs.getLong("post_id");
			posts.add(new Post(postId,
							   rs.getString("description"), 
							   rs.getString("post_url"),
							   rs.getInt("points"),
							   rs.getTimestamp("upload_date").toLocalDateTime(),
							   u,
							   PostTagDao.getInstance().getTagsForPost(postId), null)
							
					);
		}

		return posts;
	}
	
	public HashSet<Post> getAllPosts() throws SQLException{
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT p.*, u.username FROM 9gag.posts as p "
													+ "JOIN 9gag.users as u "
													+ "ON p.user_id=u.user_id");
		ResultSet rs = ps.executeQuery();
		
		HashSet<Post> allPosts = new HashSet<>();
		while(rs.next()) {
			long postId = rs.getLong("p.post_id");
			allPosts.add(new Post(postId,
								  rs.getString("p.description"),
								  rs.getString("p.post_url"), 
								  rs.getInt("p.points"), 
								  rs.getTimestamp("p.upload_date").toLocalDateTime(), 
								  new User(rs.getLong("p.user_id"), rs.getString("u.username")),
								  PostTagDao.getInstance().getTagsForPost(postId),null));
		}
		return allPosts;
	}
	
	
	public void insertInTransaction(Post post, ArrayList<String> tags) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		conn.setAutoCommit(false);
		
		try {
			//inserting the post
			insertPost(post);
			
			//inserting tags and posts-tags
			for (String tag : tags) {
				TagDao.getInstance().isertTagIfNew(tag, post);
			}
			
			conn.commit();
		} catch (SQLException e) {
			//reverse
			conn.rollback();
			throw new SQLException();
	
		} finally {
			conn.setAutoCommit(true);
		}
		
	}
}
