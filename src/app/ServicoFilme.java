package app;

import java.util.List;

import beans.Filme;
import dao.DaoFilme;
import infra.ICRUD;

public class ServicoFilme implements ICRUD<Filme> {

	private final ICRUD<Filme> dao = new DaoFilme();

	public ServicoFilme() {
		super();
	}

	@Override
	public void criar(final Filme obj) throws Exception {
		dao.criar(obj);
	}

	@Override
	public void criarTodos(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Filme buscarPorId(final int id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Filme> buscarTodos() throws Exception {
		return dao.buscarTodos();
	}

	@Override
	public void alterar(final Filme obj) throws Exception {
		dao.alterar(obj);
	}

	@Override
	public void alterarTodos(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		dao.deletarPorId(id);
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
