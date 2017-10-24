package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import model.db.PostDao;
import model.db.UserDao;

/**
 * Servlet implementation class ShowPostWithCommentsServlet
 */
@WebServlet("/showPostWithComment")
public class ShowPostWithCommentsServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				String postId=request.getParameter("postId");
				String userId=request.getParameter("userId");
		
				try {
					HttpSession s = request.getSession();
					Object o = s.getAttribute("logged");
					boolean logged = (o != null && ((boolean) o));
					Post post = PostDao.getInstance().getPost(Long.parseLong(postId),		
							UserDao.getInstance().getUserById(Long.parseLong(userId)));
					request.setAttribute("postPage", post);
					if(s.isNew() || !logged) {
					request.getRequestDispatcher("WEB-INF/notLoggedPostPage.jsp").forward(request, response);
					return;
					} else {
						request.getRequestDispatcher("WEB-INF/loggedPostPage.jsp").forward(request, response);
						return;
					}
				} catch (NumberFormatException e) {					
					e.printStackTrace();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
	}

}
