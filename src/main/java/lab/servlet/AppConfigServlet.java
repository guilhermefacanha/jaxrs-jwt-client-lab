package lab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.service.RestService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@WebServlet("/config.do")
public class AppConfigServlet extends HttpServlet { 
	private static final long serialVersionUID = -3568306974877937124L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String baseUrl = req.getParameter("url");
		RestService.setUrl(baseUrl);
		log.info("BASE URL changed to: "+baseUrl);
		
		req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
