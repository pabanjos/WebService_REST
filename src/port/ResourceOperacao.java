package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoOperacao;
import beans.Operacao;

@Path("/operacoes")
public class ResourceOperacao extends BaseResource {

	private final ServicoOperacao servico = new ServicoOperacao();

	public ResourceOperacao() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response operacoes() {
		try {
			List<Operacao> lista = servico.readAll();
			return sucesso(lista);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}
}