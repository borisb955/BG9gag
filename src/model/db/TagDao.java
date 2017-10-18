package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.tags(tag_anme) VALUES (?)", 
													Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, tag.getTag_name());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		tag.setTag_id(rs.getLong(1));
	}
	
	public boolean tagExists(String tag_name) throws SQLException {
		Connection conn = DBManager.getInstance().getConn();
		
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM 9gag.tags WHERE tag_name = ?");
		ps.setString(1, tag_name);
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		rs.next();
		int count = rs.getInt(1);
		
		return count > -1;
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
}
