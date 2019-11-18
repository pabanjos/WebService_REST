package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Compra;
import infra.Constantes;
import infra.ICRUD;
import utils.Dao;

public class DaoCompra extends Dao implements ICRUD<Compra> {

	public DaoCompra() {
		super();
	}

	@Override
	public void criar(final Compra obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void criarTodos(final List<Compra> compras) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("INSERT INTO compra VALUES (NULL,?,?,now(),?,?)");
		for (Compra compra : compras) {
			ps.setInt(1, compra.getQuantidade());
			ps.setString(2, compra.getStatus());
			ps.setInt(3, compra.getUsuario().getIdUsuario());
			ps.setInt(4, compra.getFilme().getIdFilme());
			ps.execute();
		}
		fecharConexao();
	}

	@Override
	public Compra buscarPorId(final int id) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Compra> buscarTodos() throws Exception {
		List<Compra> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM compra");
		rs = ps.executeQuery();
		while (rs.next()) {
			Compra compra = new Compra();
			compra.setIdCompra(rs.getInt(1));
			compra.setQuantidade(rs.getInt(2));
			compra.setStatus(rs.getString(3));
			compra.setData(rs.getTimestamp(4).toLocalDateTime());
			compra.getUsuario().setIdUsuario(rs.getInt(5));
			compra.getFilme().setIdFilme(rs.getInt(6));
			lista.add(compra);
		}
		fecharConexao();
		return lista;
	}

	public List<Compra> obterComprasPendentesDoUsuario(final int idUsuario) throws Exception {
		List<Compra> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM compra WHERE comprado='N' and usuario=" + idUsuario);
		rs = ps.executeQuery();
		while (rs.next()) {
			Compra compra = new Compra();
			compra.setIdCompra(rs.getInt(1));
			compra.setQuantidade(rs.getInt(2));
			compra.setStatus(rs.getString(3));
			compra.setData(rs.getTimestamp(4).toLocalDateTime());
			compra.getUsuario().setIdUsuario(rs.getInt(5));
			compra.getFilme().setIdFilme(rs.getInt(6));
			lista.add(compra);
		}
		fecharConexao();
		return lista;
	}

	@Override
	public void alterar(final Compra obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void alterarTodos(final List<Compra> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM compra WHERE idCompra=" + id);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM compra WHERE idCompra=" + id);
			ps.execute();
		}
		fecharConexao();
	}
}
