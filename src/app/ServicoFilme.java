package app;

import java.util.List;

import beans.Filme;
import dao.DaoFilme;
import dao.ICRUD;

public class ServicoFilme implements ICRUD<Filme> {

	private final ICRUD<Filme> dao = new DaoFilme();

	public ServicoFilme() {
		super();
	}

	@Override
	public void create(final Filme obj) throws Exception {
		dao.create(obj);
	}

	@Override
	public void createAll(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Filme readById(final int id) throws Exception {
		return dao.readById(id);
	}

	@Override
	public List<Filme> readAll() throws Exception {
		return dao.readAll();
	}

	@Override
	public void update(final Filme obj) throws Exception {
		dao.update(obj);
	}

	@Override
	public void updateAll(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		dao.deleteById(id);
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
