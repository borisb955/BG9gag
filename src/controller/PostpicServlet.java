package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.print.attribute.standard.PagesPerMinuteColor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/postpic")
public class PostpicServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String absPath = (String) req.getAttribute("postUrl");
		File file = new File(absPath);
		
		try (OutputStream out = resp.getOutputStream()) {
		    Path path = file.toPath();
		    Files.copy(path, out);
		    out.flush();
		} catch (IOException e) {
		    // handle exception
		}
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println(absPath);
	}
}
