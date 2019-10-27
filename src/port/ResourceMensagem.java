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
public class ResourceMensagem extends BaseResource {

	private final ServicoMensagem servico = new ServicoMensagem();

	public ResourceMensagem() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response mensagens() {
		try {
			List<Mensagem> lista = servico.readAll();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
