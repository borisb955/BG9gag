package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.db.DBManager;
import model.db.UserDao;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");
		
		//check if logged
		HttpSession s = req.getSession();
		Object o = s.getAttribute("logged");
		boolean logged = (o != null && ((boolean) o));
		if(logged) {
			resp.sendRedirect("logged.html");
			return;
		}

		//validations
		try {
			if(!UserDao.isValidEmailAddress(email)) {
				resp.getWriter().append("The email is not valid");
			} 
			else if(username.length() <= 4) {
				resp.getWriter().append("The username must be more than 4 symbols");
			}
			else if(pass.length() <= 6 || !pass.matches(".*[A-Za-z].*") || !pass.matches(".*[1-9].*")) {
				resp.getWriter().append("The password must be more than 6 symbols and must contains at least 1 number");
			}
			else if(UserDao.emailExists(email)){
				resp.getWriter().append("This email already exists");
			}
			else if(UserDao.userExists(username)) {
				resp.getWriter().append("This user already exists");
			}
			else {
				User u = new User(username, pass, email);
				try {
					UserDao.getInstance().insertUser(u);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.getRequestDispatcher("").forward(req, resp);
				return;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		DBManager.getInstance().closeConnection();
		super.destroy();
	}
}
