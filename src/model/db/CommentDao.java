package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.TreeSet;

import model.Comment;
import model.Upvote;
import model.User;
import sun.security.action.GetLongAction;

public class CommentDao {
	private static CommentDao instance;
	
	private CommentDao() {}
	
	public static synchronized CommentDao getInstance() {
		if(instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}
	
	public void insertComment(Comment com) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.comments(comment, points, issue_date"
				+ "parrent_comment, user_id, post_id) VALUES(?, ?, ?, ?, ?, ?)");
		ps.setString(1, com.getComment());
		ps.setInt(2, com.getPoints());
		ps.setTimestamp(3, Timestamp.valueOf(com.getDateTime()));
		ps.setLong(4, com.getParrent_comment().getComment_id());
		ps.setLong(5, com.getUser().getId());
		ps.setLong(6, com.getPost().getPost_id());
		ResultSet rs = ps.executeQuery();
		rs.next();
		com.setComment_id(rs.getLong("comment_id"));
	}

	public TreeSet<Comment> getAllCommentsForUser(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT comment_id, comment, points, issue_date, "
				+ " parrent_comment, post_id, user_id FROM 9gag.comments WHERE user_id = ?");
		ps.setLong(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		TreeSet<Comment> comments = new TreeSet<>();
		while(rs.next()) {
			comments.add(new Comment(rs.getLong("comment_id"), 
									 rs.getString("comment"),
									 rs.getInt("points"),
									 rs.getTimestamp("issue_date").toLocalDateTime(), 
									 getParrentComment(rs.getLong("comment_id")), 
									 u, 
									 PostDao.getInstance().getPost(rs.getLong("post_id"), u)));
		}
		return comments;
	}

	private Comment getParrentComment(long commentId) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT comment, points, issue_date, user_id, post_id "
				+ "FROM 9gag.comments WHERE comment_id = ?");
		ps.setLong(1, commentId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		//TODO: Тук трябва да видим как ще извикваме User-a. Дали да направим нов конструктор без колекциите
		//или по друг начин.
		return new Comment(commentId, 
						   rs.getString("comment"),
						   rs.getInt("points"),
						   rs.getTimestamp("issue_date").toLocalDateTime(), 
						   null, 
						   user, 
						   PostDao.getInstance().getPost(rs.getLong("post_id"), u));
	}
}
