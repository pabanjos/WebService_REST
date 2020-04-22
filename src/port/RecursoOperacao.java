package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoOperacao;
import beans.Operacao;
import servico.ServicoResposta;

@Path("/operacoes")
public class RecursoOperacao extends RecursoBase {

	private final ServicoOperacao servico = new ServicoOperacao();

	public RecursoOperacao() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		try {
			List<Operacao> lista = servico.buscarTodos();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}
}
