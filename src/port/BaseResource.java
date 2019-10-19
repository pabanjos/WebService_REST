package port;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

public class BaseResource {

	protected final Gson gson = new Gson();

	protected BaseResource() {
		super();
	}

	protected Response sucesso() {
		return Response.ok().build();
	}

	protected Response sucesso(final String json) {
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	protected Response criado() {
		return Response.status(Status.CREATED).build();
	}

	protected Response pedidoRuim() {
		return Response.status(Status.BAD_REQUEST).build();
	}

	protected Response erroNoServidor(final Exception e) {
		e.printStackTrace();
		return Response.serverError().build();
	}

}
