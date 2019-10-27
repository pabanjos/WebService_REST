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
import servico.ServicoResposta;

@Path("/filmes")
public class ResourceFilme extends BaseResource {

	private final ServicoFilme servico = new ServicoFilme();

	public ResourceFilme() {
		super();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(final String f) throws Exception {
		try {
			if (f == null) {
				return pedidoRuim();
			}
			Filme filme = gson.fromJson(f, Filme.class);
			servico.create(filme);
			return criado();
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
			Filme filme = servico.readById(idFilme);
			if ((filme == null) || (filme.getIdFilme() == null)) {
				return pedidoRuim();
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
			List<Filme> lista = servico.readAll();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(final String f) throws Exception {
		try {
			if (f == null) {
				return pedidoRuim();
			}
			Filme filme = gson.fromJson(f, Filme.class);
			if ((filme == null) || (filme.getIdFilme() == null)) {
				return pedidoRuim();
			}
			servico.update(filme);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@DELETE
	@Path("/{idFilme}")
	public Response deleteById(@PathParam("idFilme") final Integer idFilme) throws Exception {
		try {
			if (idFilme == null) {
				return pedidoRuim();
			}
			servico.deleteById(idFilme);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
