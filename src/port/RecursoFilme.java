package port;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.ServicoFilme;
import beans.Filme;
import beans.Log;
import servico.ServicoResposta;

@Path("/filmes")
public class RecursoFilme extends RecursoBase {

	private final ServicoFilme servico = new ServicoFilme();

	public RecursoFilme() {
		super();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(final Filme f) {
		try {
			if (f == null) {
				return pedidoRuim();
			}
			servico.criar(f);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/{idFilme}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readById(@PathParam("idFilme") final Integer idFilme) {
		try {
			if (idFilme == null) {
				return pedidoRuim();
			}
			Filme filme = servico.buscarPorId(idFilme);
			if (filme == null) {
				ServicoResposta.adicionarLog(Log.falha("não encontrado."));
			} else {
				ServicoResposta.adicionarObjeto("filme", filme);
			}
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		try {
			List<Filme> lista = servico.buscarTodos();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(final Filme f) {
		try {
			if ((f == null) || (f.getIdFilme() == null)) {
				return pedidoRuim();
			}
			servico.alterar(f);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@DELETE
	@Path("/{idFilme}")
	public Response deleteById(@PathParam("idFilme") final Integer idFilme) {
		try {
			if (idFilme == null) {
				return pedidoRuim();
			}
			servico.deletarPorId(idFilme);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/gerarScript")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gerarScript() {
		try {
			servico.gerarScript();
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
