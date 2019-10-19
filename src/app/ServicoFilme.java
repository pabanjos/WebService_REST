package app;

import java.util.List;

import beans.Filme;
import dao.DaoFilme;
import dao.IDao;

public class ServicoFilme {

	private final IDao<Filme> dao = new DaoFilme();

	public ServicoFilme() {
		super();
	}

	public void create(final Filme filme) throws Exception {
		dao.create(filme);
	}

	public Filme readById(final Integer idFilme) throws Exception {
		return dao.readById(idFilme);
	}

	public List<Filme> read() throws Exception {
		return dao.read();
	}

	public void update(final Filme filme) throws Exception {
		dao.update(filme);
	}

	public void deleteById(final Integer idFilme) throws Exception {
		dao.deleteById(idFilme);
	}

}
