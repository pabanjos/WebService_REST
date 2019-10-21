package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoCompra;
import beans.Compra;

@Path("/compras")
public class ResourceCompra extends BaseResource {

	private final ServicoCompra servico = new ServicoCompra();

	public ResourceCompra() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response compras() {
		try {
			List<Compra> lista = servico.readAll();
			return sucesso(lista);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
