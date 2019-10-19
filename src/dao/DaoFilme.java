package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Filme;

public class DaoFilme extends Dao implements IDao<Filme> {

	public DaoFilme() {
		super();
	}

	@Override
	public void create(final Filme filme) throws Exception {
		abrirConexao();
		ps = con.prepareStatement("insert into filme values(null,?,?,?,?,?,?,?,?)");
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

	public void inserirPoster(final String poster, final Integer idFilme) throws Exception {
		abrirConexao();
		ps = con.prepareStatement("update filme set poster=? where idFilme=" + idFilme);
		ps.setString(1, poster);
		ps.execute();
		fecharConexao();
	}

	@Override
	public Filme readById(final int idFilme) throws Exception {
		Filme filme = null;
		abrirConexao();
		ps = con.prepareStatement("select * from filme where idFilme=" + idFilme);
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
	public List<Filme> read() throws Exception {
		List<Filme> lista = new ArrayList<Filme>();
		abrirConexao();
		ps = con.prepareStatement("select * from filme order by lancamento desc");
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
		abrirConexao();
		ps = con.prepareStatement("update filme set estoque=? where idFilme=" + filme.getIdFilme());
		ps.setInt(1, filme.getEstoque());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deleteById(final int idFilme) throws Exception {
		abrirConexao();
		ps = con.prepareStatement("delete from filme where idFilme=" + idFilme);
		ps.execute();
		fecharConexao();
	}

}