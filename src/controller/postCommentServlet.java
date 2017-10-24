package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			String text = request.getParameter("commentText");
			long postId = Long.parseLong(request.getParameter("postId"));
			long userId = Long.parseLong(request.getParameter("userId"));
			//TEST
			System.out.println(text);
			System.out.println(postId);
			System.out.println(userId);
		}
			
		
	}

}
