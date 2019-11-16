package port;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import app.Controle;
import app.ServicoUsuario;
import beans.Log;
import beans.Usuario;
import servico.ServicoResposta;

@Path("/usuarios")
public class ResourceUsuario extends BaseResource {

	private final ServicoUsuario servico = new ServicoUsuario();

	public ResourceUsuario() {
		super();
	}

	@POST
	public Response create(final Usuario u) {
		try {
			if ((u == null) || (u.getLogin() == null)) {
				return pedidoRuim();
			}
			servico.create(u);
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
			Usuario usuario = servico.readById(idUsuario);
			if (usuario == null) {
				ServicoResposta.adicionarLog(Log.falha("não encontrado."));
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
			List<Usuario> lista = servico.readAll();
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
			servico.update(u);
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
			servico.deleteById(idUsuario);
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
				ServicoResposta.adicionarLog(Log.falha("dados inválidos."));
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
				ServicoResposta.adicionarLog(Log.falha("dados inválidos."));
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
			ServicoResposta.adicionarObjeto("logados", Controle.getLISTA());
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

}
