package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Pessoa;
import beans.Usuario;
import infra.Constantes;
import utils.Dao;

public class DaoUsuario extends Dao implements ICRUD<Usuario> {

	public DaoUsuario() {
		super();
	}

	private Usuario novoUsuario(final ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(rs.getInt(1));
		usuario.setLogin(rs.getString(2));
		usuario.setSenha(rs.getString(3));
		usuario.setPerfil(rs.getString(4));
		Pessoa pessoa = usuario.getPessoa();
		pessoa.setIdPessoa(rs.getInt(5));
		pessoa.setNome(rs.getString(6));
		pessoa.setEmail(rs.getString(7));
		pessoa.setSexo(rs.getString(8));
		pessoa.setTelefone(rs.getString(9));
		pessoa.setCep(rs.getString(10));
		pessoa.setNascimento(rs.getTimestamp(11).toLocalDateTime());
		return usuario;
	}

	@Override
	public void create(final Usuario usuario) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("INSERT INTO usuario VALUES (null, ?, ?, ?)");
		ps.setString(1, usuario.getLogin());
		ps.setString(2, usuario.getSenha());
		ps.setString(3, usuario.getPerfil());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void createAll(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	public Usuario readBylogin(final String login) throws Exception {
		Usuario usuario = null;
		abrirConexao(Constantes.BANCO);
		String syntax1 = "SELECT * FROM usuario u ";
		String syntax2 = "INNER JOIN pessoa p ON u.idUsuario = p.usuario WHERE u.login=" + login;
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			usuario = novoUsuario(rs);
		}
		fecharConexao();
		return usuario;
	}

	@Override
	public Usuario readById(final int idUsuario) throws Exception {
		Usuario usuario = null;
		abrirConexao(Constantes.BANCO);
		String syntax1 = "SELECT * FROM usuario u ";
		String syntax2 = "INNER JOIN pessoa p ON u.idUsuario = p.usuario WHERE u.idUsuario=" + idUsuario;
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			usuario = novoUsuario(rs);
		}
		fecharConexao();
		return usuario;
	}

	@Override
	public List<Usuario> readAll() throws Exception {
		List<Usuario> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		String syntax1 = "SELECT * FROM usuario u ";
		String syntax2 = "INNER JOIN pessoa p ON u.idUsuario = p.usuario";
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			Usuario usuario = novoUsuario(rs);
			lista.add(usuario);
		}
		fecharConexao();
		return lista;
	}

	@Override
	public void update(final Usuario u) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("UPDATE usuario SET login=?, senha=?, perfil=? WHERE idUsuario=" + u.getIdUsuario());
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		ps.setString(3, u.getPerfil());
		ps.execute();
		ps.close();
		ps = con.prepareStatement(
				"UPDATE pessoa SET nome=?, email=?, telefone=?, cep=? WHERE usuario=" + u.getIdUsuario());
		ps.setString(1, u.getPessoa().getNome());
		ps.setString(2, u.getPessoa().getEmail());
		ps.setString(3, u.getPessoa().getTelefone());
		ps.setString(4, u.getPessoa().getCep());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void updateAll(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM usuario WHERE idUsuario=" + id);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM usuario WHERE idUsuario=" + id);
			ps.execute();
		}
		fecharConexao();
	}

	public Usuario entrar(final Usuario u) throws Exception {
		Usuario usuario = null;
		abrirConexao(Constantes.BANCO);
		String syntax1 = "SELECT * FROM usuario u ";
		String syntax2 = "INNER JOIN pessoa p ON u.idUsuario = p.usuario ";
		String syntax3 = "WHERE u.login=? AND senha=?";
		ps = con.prepareStatement(syntax1 + syntax2 + syntax3);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		rs = ps.executeQuery();
		while (rs.next()) {
			usuario = novoUsuario(rs);
		}
		fecharConexao();
		return usuario;
	}
}