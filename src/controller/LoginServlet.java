package controller;

import java.io.IOException;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		try {
			if (!UserDao.emailExists(email)) {
				resp.getWriter().append("This email does not exist.");
			} else if (!UserDao.passwordMatch(email, pass)) {
				resp.getWriter().append("Incorrect password.");
			} else {
				User u = UserDao.getInstance().getFullUserByEmail(email);
				HttpSession s = req.getSession();
				
				System.out.println();
				System.out.println();
				System.out.println("novata sesiika : "+s);
				
				req.getSession().setAttribute("user", u);
				req.getSession().setAttribute("logged", true);
				req.getRequestDispatcher("").forward(req, resp);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		DBManager.getInstance().closeConnection();
		super.destroy();
	}
}
