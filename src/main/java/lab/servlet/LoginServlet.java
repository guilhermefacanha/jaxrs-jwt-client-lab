package lab.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lab.entity.User;
import lab.entity.enumeration.MsgType;
import lab.service.RestService;
import lab.util.ChaveValor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1874089075131968235L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");

		List<ChaveValor<String, String>> params = new ArrayList<>();
		params.add(new ChaveValor<String, String>("user", user));
		params.add(new ChaveValor<String, String>("pass", pass));
		String url = RestService.URL_BASE + "usuarios/auth";
		try {
			log.info("Processing login with user {} and pass ******", user);
			Response restResp = RestService.getRestPostResponse(url, params);
			if (restResp.getStatusInfo().equals(Status.OK)) {
				String autorization = restResp.getHeaderString(HttpHeaders.AUTHORIZATION);
				User usuario = new User();
				usuario.setName("Admin");
				usuario.setToken(autorization);
				req.getSession().setAttribute("usuario_logado", usuario);
				log.info("rest response: {}", restResp);
				
				req.setAttribute("msg_type", MsgType.OK);
				req.setAttribute("msg", "Login realizado com sucesso, agora você pode realizar operações que necessitam de autenticação.");
				
			} else if (restResp.getStatusInfo().equals(Status.INTERNAL_SERVER_ERROR)) {
				String readEntity = restResp.readEntity(String.class);
				throw new Exception("Erro: " + readEntity);
			} else
				throw new Exception("Erro em rest service " + url + ": " + restResp.getStatusInfo().getStatusCode() + " - " + restResp.getStatusInfo().toString());

		} catch (Exception e) {
			req.setAttribute("msg_type", MsgType.ERRO);
			req.setAttribute("msg", e.getMessage());
		}

		req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}

}
