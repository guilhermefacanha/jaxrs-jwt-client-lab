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

@WebServlet("/newuser.do")
public class NewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 8124293811413833102L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");

		List<ChaveValor<String, String>> headers = new ArrayList<>();

		User usuarioLogado = (User) req.getSession().getAttribute("usuario_logado");
		if (usuarioLogado != null) {
			headers.add(new ChaveValor<String, String>(HttpHeaders.AUTHORIZATION, usuarioLogado.getToken()));
		}

		String url = RestService.URL_BASE + "usuarios/new";

		List<ChaveValor<String, String>> params = new ArrayList<>();
		params.add(new ChaveValor<String, String>("user", user));

		try {
			Response restPostResponse = RestService.getRestPostResponse(url, params, headers);
			if (restPostResponse.getStatusInfo().equals(Status.OK)) {
				req.setAttribute("msg_type", MsgType.OK);
				req.setAttribute("msg", "User cadastrado com sucesso.");

			} else if (restPostResponse.getStatusInfo().equals(Status.INTERNAL_SERVER_ERROR)) {
				String readEntity = restPostResponse.readEntity(String.class);
				throw new Exception("Erro: " + readEntity);
			} else
				throw new Exception("Erro em rest service " + url + ": " + restPostResponse.getStatusInfo().getStatusCode() + " - " + restPostResponse.getStatusInfo().toString());
		} catch (Exception e) {
			req.setAttribute("msg_type", MsgType.ERRO);
			req.setAttribute("msg", e.getMessage());
		}

		req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}

}
