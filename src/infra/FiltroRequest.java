package infra;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import app.Controle;
import beans.Usuario;

@Provider
public class FiltroRequest implements ContainerRequestFilter {

	@Override
	public void filter(final ContainerRequestContext ctx) throws IOException {
		String path = ctx.getUriInfo().getPath();
		// paths acessiveis a qualquer um
		if (!path.equals("usuarios/entrar") && !path.equals("usuarios/sair")) {
			String login = ctx.getHeaderString("login");
			if (login == null) {
				ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			} else {
				Usuario logado = Controle.getLOGADO();
				if (logado == null) {
					ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				} else if (!login.equals(logado.getLogin())) {
					ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				} else {
					String perfil = logado.getPerfil();
					if (!perfil.equals("Administrador") && path.equals("usuarios/logados")) {
						ctx.abortWith(Response.status(Response.Status.FORBIDDEN).build());
					}
				}
			}
		}
	}
}