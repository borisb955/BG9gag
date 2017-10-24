package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");
		String avatarUrl = null;
		if(u.getProfile() != null) {
			avatarUrl = u.getProfile().getAvatarUrl();
		}
		if(avatarUrl == null) {
			avatarUrl = File.separator+"Users"
						+File.separator+"PC"
						+File.separator+"Desktop"
						+File.separator+"9gagPic"
						+File.separator+"avatar.png";
		}
		
		File file = new File(avatarUrl);
		
		try (OutputStream out = resp.getOutputStream()) {
		    Path path = file.toPath();
		    Files.copy(path, out);
		    out.flush();
		} catch (IOException e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("WEB-INF/errorPage.jsp").forward(req, resp);
		}
	}
}
