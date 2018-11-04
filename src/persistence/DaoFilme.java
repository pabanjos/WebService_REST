package persistence;

import javabeans.Filme;

public class DaoFilme extends Dao {

	public void inserirFilme(Filme filme) throws Exception {
		conectar();
		ps = con.prepareStatement("insert into Filme values(null,?,?,?,?,?,?,?,?)");
		ps.setString(1, "poster404.jpg");
		ps.setString(2, filme.getTitulo());
		ps.setString(3, filme.getGenero());
		ps.setString(4, filme.getProtagonista());
		ps.setString(5, filme.getDiretor());
		ps.setInt(6, filme.getLancamento());
		ps.setInt(7, filme.getPreco());
		ps.setInt(8, filme.getEstoque());
		ps.execute();
		con.close();
	}

	public void inserirPoster(String poster, Integer idFilme) throws Exception {
		conectar();
		ps = con.prepareStatement("update Filme set poster=? where idFilme=" + idFilme);
		ps.setString(1, poster);
		ps.execute();
		con.close();
	}

	public void atualizarEstoque(Integer estoque, Integer idFilme) throws Exception {
		conectar();
		ps = con.prepareStatement("update Filme set estoque=? where idFilme=?");
		ps.setInt(1, estoque);
		ps.setInt(2, idFilme);
		ps.execute();
		con.close();
	}

	public void deletar(Integer idFilme) throws Exception {
		conectar();
		ps = con.prepareStatement("delete from Filme where idFilme=" + idFilme);
		ps.execute();
		con.close();
	}

}