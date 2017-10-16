package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		PreparedStatement ps = conn.prepareStatement("INSERT INTO 9gag.tags(tag_anme) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, tag.getTag_name());
		ResultSet rs = ps.executeQuery();
		rs.next();
		tag.setTag_id(rs.getLong("tag_id"));
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
	
	// getTags
}
