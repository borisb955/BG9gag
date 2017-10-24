package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.db.UserDao;

@WebServlet("/changeAccount")
public class AccountsettingsServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		boolean uIsChanged = false;
		boolean eIsChanged = false;
		User u = (User) req.getSession().getAttribute("user");
		
		if(!username.isEmpty() && username != null) {
			try {
				if(!UserDao.getInstance().userExists(username)) {
					UserDao.getInstance().changeUsername(u.getId(), username);
					uIsChanged = true;
				}else {
					// User exists, please select another username
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(!email.isEmpty() && email != null) {
			try {
				if(!UserDao.getInstance().emailExists(email)) {
					UserDao.getInstance().changeEmail(u.getId(), email);
					eIsChanged = true;
				}else {
					// User exists, please select another username
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(!uIsChanged) {
			username = u.getUsername();
		}
		if(!uIsChanged) {
			email = u.getEmail();
		}
		u = new User(u.getId(), username, u.getPassword(), email, u.getProfile(), u.getLikedPosts());
		
		req.getSession().removeAttribute("user");
		req.getSession().setAttribute("user", u);
		req.getRequestDispatcher("").forward(req, resp);
	}
}
