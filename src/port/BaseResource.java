package port;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import servico.ServicoLogs;

public class BaseResource {

	protected final Gson gson = new Gson();
	protected final Map<String, Object> dados = new HashMap<>();

	protected BaseResource() {
		super();
	}

	protected Response sucesso() {
		if (!ServicoLogs.getLogs().isEmpty()) {
			String logs = gson.toJson(ServicoLogs.getLogs().get(0));
			ServicoLogs.limpar();
			return Response.ok(logs, MediaType.APPLICATION_JSON).build();
		}
		return Response.ok().build();
	}

	private Response sucesso(final String str) {
		dados.put("dados", str);
		if (!ServicoLogs.getLogs().isEmpty()) {
			String logs = gson.toJson(ServicoLogs.getLogs().get(0));
			ServicoLogs.limpar();
			return Response.ok(logs, MediaType.APPLICATION_JSON).build();
		}
		String json = gson.toJson(dados);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	protected Response sucesso(final Object obj) {
		dados.put("dados", obj);
		if (!ServicoLogs.getLogs().isEmpty()) {
			dados.put("logs", ServicoLogs.getLogs().get(0));
			ServicoLogs.limpar();
		}
		String json = gson.toJson(dados);
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
