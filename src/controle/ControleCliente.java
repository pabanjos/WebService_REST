package controle;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import persistence.DaoCliente;

@Path("/cliente")
public class ControleCliente {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void atualizarCliente(@PathParam("usuario") String usuario,
									@PathParam("senha") String senha,
									@PathParam("idConta") Integer idConta,
									@PathParam("nome") String nome,
									@PathParam("email") String email,
									@PathParam("telefone") String telefone,
									@PathParam("cep") String cep) throws Exception {
		new DaoCliente().atualizar(usuario, senha, idConta, nome, email, telefone, cep);
	}

	@PUT
	@Path("/atualizar/{idConta}")
	public void atualizarPerfil(@PathParam("idConta") Integer idConta) throws Exception {
		new DaoCliente().atualizarPerfil(idConta);
	}
	
	@DELETE
	@Path("/deletar/{idConta}")
	public void deletarCliente(@PathParam("idConta") Integer idConta) throws Exception {
		new DaoCliente().deletar(idConta);
	}

}
