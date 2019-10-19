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

import app.ServicoUsuario;
import beans.Usuario;

@Path("/usuarios")
public class ResourceUsuario extends BaseResource {

	private final ServicoUsuario servico = new ServicoUsuario();

	public ResourceUsuario() {
		super();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(final String u) throws Exception {
		try {
			if (u == null) {
				return pedidoRuim();
			}
			Usuario usuario = gson.fromJson(u, Usuario.class);
			servico.create(usuario);
			return criado();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Path("/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readById(@PathParam("idUsuario") final Integer idUsuario) {
		try {
			if (idUsuario == null) {
				return pedidoRuim();
			}
			Usuario usuario = servico.readById(idUsuario);
			if ((usuario == null) || (usuario.getIdUsuario() == null)) {
				return pedidoRuim();
			}
			String json = gson.toJson(usuario);
			return sucesso(json);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		try {
			List<Usuario> lista = servico.read();
			String json = gson.toJson(lista);
			return sucesso(json);
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(final String u) throws Exception {
		try {
			if (u == null) {
				return pedidoRuim();
			}
			Usuario usuario = gson.fromJson(u, Usuario.class);
			if ((usuario == null) || (usuario.getIdUsuario() == null)) {
				return pedidoRuim();
			}
			servico.update(usuario);
			return sucesso();
		} catch (Exception e) {
			return erroNoServidor(e);
		}
	}

	@DELETE
	@Path("/{idUsuario}")
	public Response deleteById(@PathParam("idUsuario") final Integer idUsuario) throws Exception {
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

}
