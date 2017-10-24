package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.User;
import model.db.PostDao;

@WebServlet("/myposts")
public class MypostsServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");
		try {
			ArrayList<Post> posts = PostDao.getInstance().getAllPostsForUser(u);
			req.setAttribute("posts", posts);
		} catch (SQLException e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("WEB-INF/errorPage.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("WEB-INF/myPosts.jsp").forward(req, resp);
		
		
	}
}
