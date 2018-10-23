package lab.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lab.service.RestService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/restget.do")
public class RestGetServlet extends HttpServlet {
	private static final long serialVersionUID = -3568306974877937124L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// start
		long lStartTime = System.nanoTime();
		String result = "{ \"foo\": \"sample\", \"bar\": \"sample\" }";
		String path = req.getParameter("path");

		int repeat = tryParse(req.getParameter("rep"));
		int p1 = tryParse(req.getParameter("p1"));
		int p2 = tryParse(req.getParameter("p2"));

		if (repeat > 0) {
			result = executeGetRest(path+getRandomParam(p1, p2));
			executeWithRepeat(path, repeat, p1, p2);
		} else
			result = executeGetRest(path);

		// end
		long lEndTime = System.nanoTime();

		// time elapsed
		long output = lEndTime - lStartTime;

		output = output / 1000000;

		req.setAttribute("time", output + " milliseconds");

		req.setAttribute("res", result);
		req.getServletContext().getRequestDispatcher("/rest_get.jsp").forward(req, resp);
	}

	private void executeWithRepeat(String path, int repeat, int p1, int p2) {

		for (int i = 0; i < repeat; i++) {
			log.info("Created thread {} with path {}", i + 1, path);
			Thread t = new Thread() {
				@Override
				public void run() {
					executeGetRest(path + getRandomParam(p1,p2));
				}
			};

			t.run();
		}
	}
	
	private String getRandomParam(int p1, int p2) {
		int param = 0;
		if (p1 > 0 && p2 > 0 && p2 > p1) {
			param = new Random().nextInt(p2 + 1);
			while (param <= 0)
				param = new Random().nextInt(p2 + 1);
		}

		return param > 0 ? param + "" : "";
	}

	private int tryParse(String repeat) {
		try {
			return Integer.parseInt(repeat);
		} catch (Exception e) {
			return 0;
		}
	}

	private String executeGetRest(String path) {
		String result;
		Response restResponse = RestService.getRestResponse(RestService.URL_BASE + path);
		if (restResponse.getStatusInfo().equals(Status.OK)) {
			result = restResponse.readEntity(String.class);
		} else {
			result = "{\"error\": \"Error " + restResponse.getStatusInfo() + "\"}";
			log.warn("Warn in rest response for path {} : {}", path, restResponse.getStatusInfo());
		}
		return result;
	}

}
