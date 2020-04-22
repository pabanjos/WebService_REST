package port;

import java.sql.SQLException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import beans.Log;
import servico.ServicoResposta;

public class RecursoBase {

	protected RecursoBase() {
		super();
	}

	protected Response sucesso() {
		return Response.status(Status.OK) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

	protected Response criado() {
		return Response.status(Status.CREATED) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

	protected Response pedidoRuim() {
		return Response.status(Status.BAD_REQUEST) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

	protected Response naoAutorizado() {
		return Response.status(Status.UNAUTHORIZED) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

	protected Response proibido() {
		return Response.status(Status.FORBIDDEN) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

	protected Response naoEncontrado() {
		return Response.status(Status.NOT_FOUND) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

	protected Response erroNoServidor(final Exception e) {
		if (e instanceof SQLException) {
			ServicoResposta.adicionarLog(Log.falha("SQLException: " + e.getMessage()));
		} else if (e instanceof NullPointerException) {
			ServicoResposta.adicionarLog(Log.falha("valor vazio."));
		} else {
			ServicoResposta.adicionarLog(Log.falha(e.getMessage()));
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR) //
				.entity(ServicoResposta.build()) //
				.type(MediaType.APPLICATION_JSON) //
				.build();
	}

}
