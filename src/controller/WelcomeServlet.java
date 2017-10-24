package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import model.db.PostDao;

@WebServlet("")
public class WelcomeServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		Object o = s.getAttribute("logged");
		boolean logged = (o != null && ((boolean) o));
		
		// Making it to get allPosts every time when the client click home page
		//TODO: use application scope if possible to get all the posts every several 
		//minutes in order to get the new posts
		try {
			HashSet<Post> allPosts = PostDao.getInstance().getAllPosts();
			req.setAttribute("allPosts", allPosts);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(s.isNew() || !logged) {
			req.getRequestDispatcher("WEB-INF/notLogged.jsp").forward(req, resp);
			return;
		}else {
			req.getRequestDispatcher("WEB-INF/logged.jsp").forward(req, resp);
			return;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		Object o = s.getAttribute("logged");
		boolean logged = (o != null && ((boolean) o));
		
		// Making it to get allPosts every time when the client click home page
		//TODO: use application scope if possible to get all the posts every several 
		//minutes in order to get the new posts
		
		try {

			HashSet<Post> allPosts = PostDao.getInstance().getAllPosts();

			req.setAttribute("allPosts", allPosts);
			
		} catch (SQLException e) {		
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("WEB-INF/errorPage.jsp").forward(req, resp);
		}
		
		
		if(s.isNew() || !logged) {
			req.getRequestDispatcher("WEB-INF/notLogged.jsp").forward(req, resp);
			return;
		}else {
			req.getRequestDispatcher("WEB-INF/logged.jsp").forward(req, resp);
			return;
		}
	}
}
