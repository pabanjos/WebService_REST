package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Pessoa;
import beans.Usuario;

public class DaoUsuario extends Dao implements IDao<Usuario> {

	public DaoUsuario() {
		super();
	}

	@Override
	public void create(final Usuario u) throws Exception {
	}

	@Override
	public Usuario readById(final int idUsuario) throws Exception {
		Usuario usuario = null;
		abrirConexao();
		String syntax1 = "select * from usuario u ";
		String syntax2 = "inner join pessoa p on u.idUsuario = p.usuario where u.idUsuario=" + idUsuario;
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt(1));
			usuario.setLogin(rs.getString(2));
			usuario.setSenha(rs.getString(3));
			usuario.setPerfil(rs.getString(4));
			usuario.setSaldo(rs.getInt(5));
			Pessoa pessoa = usuario.getPessoa();
			pessoa.setIdPessoa(rs.getInt(6));
			pessoa.setNome(rs.getString(7));
			pessoa.setEmail(rs.getString(8));
			pessoa.setSexo(rs.getString(9));
			pessoa.setTelefone(rs.getString(10));
			pessoa.setCep(rs.getString(11));
			pessoa.setNascimento(rs.getDate(12).toLocalDate());
		}
		fecharConexao();
		return usuario;
	}

	@Override
	public List<Usuario> read() throws Exception {
		List<Usuario> lista = new ArrayList<Usuario>();
		abrirConexao();
		String syntax1 = "select * from usuario u ";
		String syntax2 = "inner join pessoa p on u.idUsuario = p.usuario where u.perfil='Cliente'";
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(rs.getInt(1));
			usuario.setLogin(rs.getString(2));
			usuario.setSenha(rs.getString(3));
			usuario.setPerfil(rs.getString(4));
			usuario.setSaldo(rs.getInt(5));
			Pessoa pessoa = usuario.getPessoa();
			pessoa.setIdPessoa(rs.getInt(6));
			pessoa.setNome(rs.getString(7));
			pessoa.setEmail(rs.getString(8));
			pessoa.setSexo(rs.getString(9));
			pessoa.setTelefone(rs.getString(10));
			pessoa.setCep(rs.getString(11));
			pessoa.setNascimento(rs.getDate(12).toLocalDate());
			lista.add(usuario);
		}
		fecharConexao();
		return lista;
	}

	@Override
	public void update(final Usuario u) throws Exception {
		abrirConexao();
		ps = con.prepareStatement(
				"update usuario set login=?, senha=?, perfil=?, saldo=? where idUsuario=" + u.getIdUsuario());
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		ps.setString(3, u.getPerfil());
		ps.setInt(4, u.getSaldo());
		ps.execute();
		ps.close();
		ps = con.prepareStatement(
				"update pessoa set nome=?, email=?, telefone=?, cep=? where usuario=" + u.getIdUsuario());
		ps.setString(1, u.getPessoa().getNome());
		ps.setString(2, u.getPessoa().getEmail());
		ps.setString(3, u.getPessoa().getTelefone());
		ps.setString(4, u.getPessoa().getCep());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deleteById(final int idUsuario) throws Exception {
		abrirConexao();
		ps = con.prepareStatement("delete from usuario where idUsuario=" + idUsuario);
		ps.execute();
		fecharConexao();
	}

}