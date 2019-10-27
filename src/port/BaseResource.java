package port;

import java.time.LocalDateTime;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Log;
import infra.LocalDateTimeAdapter;
import servico.ServicoResposta;

public class BaseResource {

	protected Gson gson = new GsonBuilder() //
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe()) //
			.create();

	protected BaseResource() {
		super();
		ServicoResposta.limpar();
	}

	protected Response sucesso() {
		String dadosJSON = gson.toJson(ServicoResposta.build());
		return Response.ok(dadosJSON, MediaType.APPLICATION_JSON).build();
	}

	protected Response criado() {
		return Response.status(Status.CREATED).build();
	}

	protected Response pedidoRuim() {
		return Response.status(Status.BAD_REQUEST).build();
	}

	protected Response erroNoServidor(final Exception e) {
		e.printStackTrace();
		ServicoResposta.adicionarLog(Log.falha(e.getMessage()));
		String dadosJSON = gson.toJson(ServicoResposta.build());
		return Response.serverError().entity(dadosJSON).build();
	}

}
