package controle;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javabeans.Filme;
import persistence.DaoFilme;

@Path("/filme")
public class ControleFilme {

	@POST
	@Path("/{filme}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String cadastrarFilme(@PathParam("filme") String filme) throws Exception {
		JSONObject object = (JSONObject) new JSONParser().parse(filme);
		Filme f = new Filme(object);
		new DaoFilme().inserirFilme(f);
		return new Listas().filmes();
	}

	@PUT
	@Path("/{estoque}/{idFilme}")
	@Produces(MediaType.APPLICATION_JSON)
	public String atualizarFilme(@PathParam("estoque") Integer estoque, @PathParam("idFilme") Integer idFilme) throws Exception {
		new DaoFilme().atualizarEstoque(estoque, idFilme);
		return new Listas().filmes();
	}

	@DELETE
	@Path("/{idFilme}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletarFilme(@PathParam("idFilme") Integer idFilme) throws Exception {
		new DaoFilme().deletar(idFilme);
		return new Listas().filmes();
	}

}
