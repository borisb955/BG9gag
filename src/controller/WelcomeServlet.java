package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("")
public class WelcomeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		Object o = s.getAttribute("logged");
		boolean logged = (o != null && ((boolean) o));
		if(s.isNew() || !logged) {
			resp.sendRedirect("index.html");
		}else {
			resp.sendRedirect("logged.html");
		}
	}
}
