package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoCompra;
import beans.Compra;
import servico.ServicoResposta;

@Path("/compras")
public class RecursoCompra extends RecursoBase {

	private final ServicoCompra servico = new ServicoCompra();

	public RecursoCompra() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		try {
			List<Compra> lista = servico.buscarTodos();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
