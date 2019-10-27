package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Filme;
import infra.Constantes;
import utils.Dao;

public class DaoFilme extends Dao implements ICRUD<Filme> {

	public DaoFilme() {
		super();
	}

	@Override
	public void create(final Filme filme) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("INSERT INTO filme VALUES (NULL,?,?,?,?,?,?,?,?)");
		ps.setString(1, "poster404.jpg");
		ps.setString(2, filme.getTitulo());
		ps.setString(3, filme.getGenero());
		ps.setString(4, filme.getProtagonista());
		ps.setString(5, filme.getDiretor());
		ps.setInt(6, filme.getLancamento());
		ps.setInt(7, filme.getPreco());
		ps.setInt(8, filme.getEstoque());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void createAll(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Filme readById(final int idFilme) throws Exception {
		Filme filme = null;
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM filme WHERE idFilme=" + idFilme);
		rs = ps.executeQuery();
		while (rs.next()) {
			filme = new Filme();
			filme.setIdFilme(rs.getInt(1));
			filme.setPoster(rs.getString(2));
			filme.setTitulo(rs.getString(3));
			filme.setGenero(rs.getString(4));
			filme.setProtagonista(rs.getString(5));
			filme.setDiretor(rs.getString(6));
			filme.setLancamento(rs.getInt(7));
			filme.setPreco(rs.getInt(8));
			filme.setEstoque(rs.getInt(9));
		}
		fecharConexao();
		return filme;
	}

	@Override
	public List<Filme> readAll() throws Exception {
		List<Filme> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM filme ORDER BY lancamento DESC");
		rs = ps.executeQuery();
		while (rs.next()) {
			Filme filme = new Filme();
			filme.setIdFilme(rs.getInt(1));
			filme.setPoster(rs.getString(2));
			filme.setTitulo(rs.getString(3));
			filme.setGenero(rs.getString(4));
			filme.setProtagonista(rs.getString(5));
			filme.setDiretor(rs.getString(6));
			filme.setLancamento(rs.getInt(7));
			filme.setPreco(rs.getInt(8));
			filme.setEstoque(rs.getInt(9));
			lista.add(filme);
		}
		fecharConexao();
		return lista;
	}

	@Override
	public void update(final Filme filme) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("UPDATE filme SET poster=?, titulo=?, genero=?, protagonista=?, diretor=?, "
				+ "lancamento=?, preco=?, estoque=? WHERE idFilme=" + filme.getIdFilme());
		ps.setString(1, filme.getPoster());
		ps.setString(2, filme.getTitulo());
		ps.setString(3, filme.getGenero());
		ps.setString(4, filme.getProtagonista());
		ps.setString(5, filme.getDiretor());
		ps.setInt(6, filme.getLancamento());
		ps.setInt(7, filme.getPreco());
		ps.setInt(8, filme.getEstoque());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void updateAll(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int idFilme) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM filme WHERE idFilme=" + idFilme);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM filme WHERE idFilme=" + id);
			ps.execute();
		}
		fecharConexao();
	}
}