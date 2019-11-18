package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoRegistro;
import beans.Registro;
import servico.ServicoResposta;

@Path("/registros")
public class ResourceRegistro extends BaseResource {

	private final ServicoRegistro servico = new ServicoRegistro();

	public ResourceRegistro() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		try {
			List<Registro> lista = servico.buscarTodos();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}
}
