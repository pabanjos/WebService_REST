package port;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import app.CacheUsuario;
import app.ServicoUsuario;
import beans.Log;
import beans.Usuario;
import servico.ServicoResposta;

@Path("/usuarios")
public class RecursoUsuario extends RecursoBase {

	private final ServicoUsuario servico = new ServicoUsuario();

	public RecursoUsuario() {
		super();
	}

	@POST
	public Response create(final Usuario u) {
		try {
			if ((u == null) || (u.getLogin() == null)) {
				return pedidoRuim();
			}
			servico.criar(u);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/{idUsuario}")
	public Response readById(@PathParam("idUsuario") final Integer idUsuario) {
		try {
			if (idUsuario == null) {
				return pedidoRuim();
			}
			Usuario usuario = servico.buscarPorId(idUsuario);
			if (usuario == null) {
				ServicoResposta.adicionarLog(Log.falha("n�o encontrado."));
			} else {
				ServicoResposta.adicionarObjeto("usuario", usuario);
			}
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	public Response read() {
		try {
			List<Usuario> lista = servico.buscarTodos();
			ServicoResposta.adicionarObjeto("lista", lista);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@PUT
	public Response update(final Usuario u) {
		try {
			if ((u == null) || (u.getIdUsuario() == null)) {
				return pedidoRuim();
			}
			servico.alterar(u);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@DELETE
	@Path("/{idUsuario}")
	public Response deleteById(@PathParam("idUsuario") final Integer idUsuario) {
		try {
			if (idUsuario == null) {
				return pedidoRuim();
			}
			servico.deletarPorId(idUsuario);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@POST
	@Path("/entrar")
	public Response entrar(final Usuario u) {
		try {
			if ((u == null) || (u.getLogin() == null) || (u.getSenha() == null)) {
				ServicoResposta.adicionarLog(Log.falha("dados inv�lidos."));
				return pedidoRuim();
			}
			servico.entrar(u);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@POST
	@Path("/sair")
	public Response sair(final Usuario u) {
		try {
			if ((u == null) || (u.getLogin() == null) || (u.getSenha() == null)) {
				ServicoResposta.adicionarLog(Log.falha("dados inv�lidos."));
				return pedidoRuim();
			}
			servico.sair(u);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/logados")
	public Response logados() {
		try {
			ServicoResposta.adicionarObjeto("logados", CacheUsuario.getLISTA());
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
