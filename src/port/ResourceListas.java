package port;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoListas;
import beans.Acesso;
import beans.Compra;
import beans.Mensagem;
import beans.Transacao;

@Path("/listas")
public class ResourceListas extends BaseResource {

	private final ServicoListas servico = new ServicoListas();

	@GET
	@Path("/transacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacoes() {
		try {
			List<Transacao> lista = servico.transacoes();
			String json = gson.toJson(lista);
			return sucesso(json);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/compras")
	@Produces(MediaType.APPLICATION_JSON)
	public Response compras() {
		try {
			List<Compra> lista = servico.compras();
			String json = gson.toJson(lista);
			return sucesso(json);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/acessos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response acessos() {
		try {
			List<Acesso> lista = servico.acessos();
			String json = gson.toJson(lista);
			return sucesso(json);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/mensagens")
	@Produces(MediaType.APPLICATION_JSON)
	public Response mensagens() {
		try {
			List<Mensagem> lista = servico.mensagens();
			String json = gson.toJson(lista);
			return sucesso(json);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
