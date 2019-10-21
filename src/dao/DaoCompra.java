package dao;

import java.util.ArrayList;
import java.util.List;

import app.ICRUD;
import beans.Compra;
import infra.Constantes;
import utils.Dao;

public class DaoCompra extends Dao implements ICRUD<Compra> {

	public DaoCompra() {
		super();
	}

	@Override
	public void create(final Compra obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createAll(final List<Compra> compras) throws Exception {
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
	public Compra readById(final int id) throws Exception {
		return null;
	}

	@Override
	public List<Compra> readAll() throws Exception {
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

	@Override
	public void update(final Compra obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateAll(final List<Compra> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM compra WHERE idCompra=" + id);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM compra WHERE idCompra=" + id);
			ps.execute();
		}
		fecharConexao();
	}
}
