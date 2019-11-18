package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Registro;
import infra.Constantes;
import infra.ICRUD;
import utils.Dao;

public class DaoRegistro extends Dao implements ICRUD<Registro> {

	public DaoRegistro() {
		super();
	}

	@Override
	public void criar(final Registro acesso) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("INSERT INTO acesso VALUES (NULL,?,?,now(),?)");
		ps.setString(1, acesso.getNome());
		ps.setString(2, acesso.getIp());
		ps.setInt(3, acesso.getUsuario().getIdUsuario());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void criarTodos(final List<Registro> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Registro buscarPorId(final int idRegistro) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Registro> buscarTodos() throws Exception {
		List<Registro> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM acesso");
		rs = ps.executeQuery();
		while (rs.next()) {
			Registro acesso = new Registro();
			acesso.setIdRegistro(rs.getInt(1));
			acesso.setNome(rs.getString(2));
			acesso.setIp(rs.getString(3));
			acesso.setData(rs.getTimestamp(4).toLocalDateTime());
			acesso.getUsuario().setIdUsuario(rs.getInt(5));
			lista.add(acesso);
		}
		fecharConexao();
		return lista;
	}

	@Override
	public void alterar(final Registro obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void alterarTodos(final List<Registro> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM acesso WHERE idRegistro=" + id);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM acesso WHERE idRegistro=" + id);
			ps.execute();
		}
		fecharConexao();
	}
}
