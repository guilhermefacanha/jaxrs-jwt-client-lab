package lab.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lab.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

	public List<User> getUsuarios() {
		List<User> lista = new ArrayList<>();

		try {
			Response restResponse = RestService.getRestResponse(RestService.URL_BASE + "usuarios");
			if (restResponse.getStatusInfo().equals(Status.OK)) {
				String usuarios = restResponse.readEntity(String.class);
				usuarios = usuarios.replace("[", "").replace("]", "");
				for (String s : usuarios.split(","))
					lista.add(new User(s, null));
			}
		} catch (Exception e) {
			log.error("Erro: ", e); 
		}
		return lista;
	}

}
