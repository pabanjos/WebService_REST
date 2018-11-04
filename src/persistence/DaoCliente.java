package persistence;

public class DaoCliente extends Dao {

	public void atualizar(String usuario, String senha, Integer idConta, String nome, String email, String telefone, String cep) throws Exception {
		conectar();
		ps = con.prepareStatement("update Conta set usuario=?, senha=? where idConta=?");
		ps.setString(1, usuario);
		ps.setString(2, senha);
		ps.setInt(3, idConta);
		ps.execute();
		ps.close();
		ps = con.prepareStatement("update Pessoa set nome=?, email=?, telefone=?, cep=? where conta=?");
		ps.setString(1, nome);
		ps.setString(2, email);
		ps.setString(3, telefone);
		ps.setString(4, cep);
		ps.setInt(5, idConta);
		ps.execute();
		con.close();
	}

	public void atualizarPerfil(Integer idConta) throws Exception {
		conectar();
		ps = con.prepareStatement("update Conta set perfil='Administrador' where idConta=" + idConta);
		ps.execute();
		con.close();
	}

	public void deletar(Integer idConta) throws Exception {
		conectar();
		ps = con.prepareStatement("delete from Conta where idConta=" + idConta);
		ps.execute();
		con.close();
	}

}