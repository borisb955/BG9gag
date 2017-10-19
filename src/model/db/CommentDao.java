package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeSet;

import model.Comment;
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
													+ "parrent_comment, user_id, post_id) "
													+ "VALUES(?, ?, ?, ?, ?, ?)", 
													Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, com.getComment());
		ps.setInt(2, com.getPoints());
		ps.setTimestamp(3, Timestamp.valueOf(com.getDateTime()));
		ps.setLong(4, com.getParrent_comment().getComment_id());
		ps.setLong(5, com.getUser().getId());
		ps.setLong(6, com.getPost().getPost_id());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		com.setComment_id(rs.getLong(1));
	}
	
//	public boolean parrentCommentExists() { check if parent comment is null}

	public ArrayList<Comment> getAllCommentsForUser(User u) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT c1.comment_id, c1.comment, c1.points, "
											+ "c1.issue_date, c1.parrent_comment, c1.post_id, "
											+ "c2.comment_id, c2.comment, c2.points, c2.issue_date, "
											+ "c2.parrent_comment, c2.post_id, c2.user_id "
											+ "FROM 9gag.comments as c1 "
											+ "JOIN 9gag.comments as c2 "
											+ "ON c1.parrent_comment = c2.comment_id "
											+ "WHERE c1.user_id = ? "
											+ "ORDER BY c1.issue_date");
		ps.setLong(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Comment> comments = new ArrayList<>();
		while(rs.next()) {
			//the parrentComment doesn't have parrent_comment so it's null
			Comment parrentComment = new Comment(rs.getLong("c2.comment_id"), 
												 rs.getString("c2.comment"),
												 rs.getInt("c2.points"),
												 rs.getTimestamp("c2.issue_date").toLocalDateTime(), 
												 null, 
												 UserDao.getInstance().getUserById(rs.getLong("c2.user_id")), 
												 PostDao.getInstance().getPost(rs.getLong("c2.post_id"), u));
			
			comments.add(new Comment(rs.getLong("c1.comment_id"), 
									 rs.getString("c1.comment"),
									 rs.getInt("c1.points"),
									 rs.getTimestamp("c1.issue_date").toLocalDateTime(), 
									 parrentComment, 
									 u, 
									 PostDao.getInstance().getPost(rs.getLong("c1.post_id"), u)));
		}

		return comments;
	}
}
