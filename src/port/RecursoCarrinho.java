package port;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoCarrinho;
import beans.Carrinho;
import beans.Filme;
import infra.Constantes;
import servico.ServicoResposta;

@Path("/carrinho")
public class RecursoCarrinho extends RecursoBase {

	private final ServicoCarrinho controle = new ServicoCarrinho();

	public RecursoCarrinho() {
		super();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obter() {
		try {
			Carrinho carrinho = controle.obter();
			ServicoResposta.adicionarObjeto(Constantes.CARRINHO, carrinho);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@POST
	@Path("/adicionar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionar(final Filme f) {
		try {
			Carrinho carrinho = controle.adicionar(f);
			ServicoResposta.adicionarObjeto(Constantes.CARRINHO, carrinho);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@POST
	@Path("/remover")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(final Filme f) {
		try {
			Carrinho carrinho = controle.remover(f);
			ServicoResposta.adicionarObjeto(Constantes.CARRINHO, carrinho);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}
}
