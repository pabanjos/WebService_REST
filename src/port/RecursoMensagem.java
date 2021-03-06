package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoMensagem;
import beans.Mensagem;
import servico.ServicoResposta;

@Path("/mensagens")
public class RecursoMensagem extends RecursoBase {

	private final ServicoMensagem servico = new ServicoMensagem();

	public RecursoMensagem() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		try {
			List<Mensagem> lista = servico.buscarTodos();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
