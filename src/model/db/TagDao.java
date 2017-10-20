package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Post;
import model.Tag;

public class TagDao {
	private static TagDao instance;
	
	private TagDao() {}
	
	public static synchronized TagDao getInstance() {
		if(instance == null) {
			instance = new TagDao();
		}
		return instance;
	}
	
	public void insertTag(Tag tag) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.tags(tag_name) VALUES (?)", 
													Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, tag.getTagName());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		tag.setTagId(rs.getLong(1));
	}
	
	public boolean tagExists(String tagName) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM 9gag.tags WHERE tag_name = ?");
		ps.setString(1, tagName);
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		rs.next();
		int count = rs.getInt(1);
		
		return count > 0;
	}
	
	//TODO: We should take 10-15 most popular tags sorted by sum(posts.points)
	public ArrayList<Tag> getAllTags() throws SQLException{
		Connection conn = DBManager.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT tag_id, tag_name FROM 9gag.tags");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Tag> allTags = new ArrayList<>();
		while(rs.next()) {
			allTags.add(new Tag(rs.getLong("tag_id"), rs.getString("tag_name")));
		}
		return allTags;
	}
	
	public void isertTagIfNew(String tag, Post p) {
		try {
			Tag t = new Tag(tag);
			if(!tagExists(tag) && !tag.isEmpty()) {
				insertTag(t);
			}
			if(!tag.isEmpty()) {
				PostTagDao.getInstance().insertPostTag(p, t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
