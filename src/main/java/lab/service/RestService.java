package lab.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lab.util.ChaveValor;

public class RestService {
	
	public static final String URL_BASE = "http://localhost:8080/restjwt/rest/";
	
	public static Response getRestResponse(String url) {
		return getRestGetResponse(url, null);
	}

	public static Response getRestPostResponse(String url, List<ChaveValor<String, String>> params) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);

		Form form = null;
		if (params != null && params.size() > 0) {
			form = new Form();
			for (ChaveValor<String, String> par : params) {
				form.param(par.getChave(), par.getValor());
			}
		}

		Invocation.Builder invocationBuilder = target.request();
		// invocationBuilder.header("some-header", "true");

		Response response = null;

		if (form != null)
			response = invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		else
			response = invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
					.get();

		return response;
	}

	public static Response getRestPostResponse(String url, List<ChaveValor<String, String>> params, List<ChaveValor<String, String>> headers) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		
		Form form = null;
		if (params != null && params.size() > 0) {
			form = new Form();
			for (ChaveValor<String, String> par : params) {
				form.param(par.getChave(), par.getValor());
			}
		}
		
		Invocation.Builder invocationBuilder = target.request();
		if (headers != null && headers.size() > 0) {
			for (ChaveValor<String, String> par : headers) {
				invocationBuilder.header(par.getChave(), par.getValor());
			}
		}
		
		Response response = null;
		
		if (form != null)
			response = invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		else
			response = invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
			.get();
		
		return response;
	}

	public static Response getRestGetResponse(String url, List<ChaveValor<String, String>> params) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);

		if (params != null && params.size() > 0) {
			for (ChaveValor<String, String> par : params) {
				target = target.queryParam(par.getChave(), par.getValor());
			}
		}

		Invocation.Builder invocationBuilder = target.request();
		// invocationBuilder.header("some-header", "true");

		Response response = null;

		response = invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").get();

		return response;
	}

	public static Response getRestGetResponse(String url, List<ChaveValor<String, String>> params,
			List<ChaveValor<String, String>> headers) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);

		if (params != null && params.size() > 0) {
			for (ChaveValor<String, String> par : params) {
				target = target.queryParam(par.getChave(), par.getValor());
			}
		}

		Invocation.Builder invocationBuilder = target.request();
		if (headers != null && headers.size() > 0) {
			for (ChaveValor<String, String> par : headers) {
				invocationBuilder.header(par.getChave(), par.getValor());
			}
		}

		Response response = null;

		response = invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").get();

		return response;
	}
}
