package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.print.attribute.standard.PagesPerMinuteColor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.db.PostDao;

@WebServlet("/postpic")
public class PostpicServlet extends HttpServlet{
	
	
	public static final String PICTURE_URL = "D:/postPics/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Post post = (Post) req.getSession().getAttribute("post");
		String pictureUrl = post.getPostUrl();
	
		System.out.println(post.getPostUrl());
	
		
		if (pictureUrl == null || pictureUrl.isEmpty()) {
			pictureUrl = "defaultPic.png";
		}
		
		File myFile = new File(PICTURE_URL + pictureUrl);
		OutputStream out = resp.getOutputStream();
		Path path = myFile.toPath();
		Files.copy(path, out);
		out.flush();
		
		
//		File file = new File(absPath);
//		
//		try (OutputStream out = resp.getOutputStream()) {
//		    Path path = file.toPath();
//		    Files.copy(path, out);
//		    out.flush();
//		} catch (IOException e) {
//		    // handle exception
//		}

	}
}
