package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Post;
import model.Tag;
import model.User;
import model.db.PostDao;
import model.db.PostTagDao;
import model.db.TagDao;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		Object o = s.getAttribute("logged");
		boolean logged = (o != null && ((boolean) o));
		
		req.getRequestDispatcher("WEB-INF/upload.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(true);
		User u = (User) s.getAttribute("user");
		String description = req.getParameter("description"); 
	    Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
	
		
	    //using File.seperator for the different OSs
	    File folders = new File("C:"
	    		+File.separator+"Users"
	    		+File.separator+"Grozdan"
	    		+File.separator+"Desktop"
	    		+File.separator+"9gagPic"
	    		+File.separator+"users"
	    		+File.separator + u.getUsername() 
	    		+File.separator+"uploads"
	    		+File.separator+"pics");
	    folders.mkdirs();
	    
	    //TODO: make posts names unique and remove random
	    File file = new File("C:"
	    	+File.separator+"Users"
	    	+File.separator+"Grozdan"
	    	+File.separator+"Desktop"
	    	+File.separator+"9gagPic"
	    	+File.separator+"users"
	    	+File.separator + u.getUsername() 
	    	+File.separator+"uploads"
	    	+File.separator+"pics"
	    	+File.separator+ u.getUsername() + new Random().nextInt(2_000_000_000)
	    	+".png");
	    
	    if(!file.exists()) {
	    	file.createNewFile();
	    }
	    
	    Post post = new Post(description , file.getAbsolutePath() , LocalDateTime.now(), u);
//	    try {
//			PostDao.getInstance().insertPost(post);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	    
	    //TODO: why not FileInputStream -> from video
	    InputStream fis = filePart.getInputStream();
	    FileOutputStream fos = new FileOutputStream(file);
	    int b = fis.read();
	    while(b != -1) {
	    	fos.write(b);
	    	b = fis.read();
	    }
	    fis.close();
	    fos.close();
	    
	    //Insert post and tags in DB 
	    String tag1 = req.getParameter("tag1");
		String tag2 = req.getParameter("tag2");
		String tag3 = req.getParameter("tag3");
		
		ArrayList<String> tags = new ArrayList<>();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		
		try {
			PostDao.getInstance().insertInTransaction(post, tags);
		} catch (SQLException e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("WEB-INF/errorPage.jsp").forward(req, resp);
		}
	}
}


