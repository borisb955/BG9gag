package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.jmx.snmp.Timestamp;

import model.Comment;
import model.Post;
import model.User;
import model.db.CommentDao;

/**
 * Servlet implementation class postCommentServlet
 */
@WebServlet("/postComment")
public class postCommentServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession s = request.getSession();
		Object o = s.getAttribute("logged");
		boolean logged = (o != null && ((boolean) o));
		
		if(s.isNew() || !logged) {
			request.getRequestDispatcher("WEB-INF/notLogged.jsp").forward(request, response);
			return;
		}else {
			Post post = (Post) s.getAttribute("postPage");
			User user = (User) s.getAttribute("userPage");
			String text = request.getParameter("commentText");

			
			try {
				CommentDao.getInstance().insertComment(new Comment(text,LocalDateTime.now(),null,user,post));
			} catch (SQLException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("WEB-INF/errorPage.jsp").forward(request, response);
			}
				
		
		}
			
		
	}

}
