package app;

import java.util.List;
import java.util.Objects;

import beans.Compra;
import beans.Filme;
import beans.Usuario;
import dao.DaoCompra;
import dao.DaoFilme;
import dao.DaoUsuario;
import infra.ICRUD;

public class ServicoCompra implements ICRUD<Compra> {

	private final ICRUD<Compra> daoCompra = new DaoCompra();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();
	private final ICRUD<Filme> daoFilme = new DaoFilme();

	public ServicoCompra() {
		super();
	}

	@Override
	public void criar(final Compra obj) throws Exception {
		daoCompra.criar(obj);
	}

	@Override
	public void criarTodos(final List<Compra> list) throws Exception {
		daoCompra.criarTodos(list);
	}

	@Override
	public Compra buscarPorId(final int id) throws Exception {
		Compra compra = daoCompra.buscarPorId(id);
		if (Objects.nonNull(compra)) {
			Usuario usuario = daoUsuario.buscarPorId(compra.getUsuario().getIdUsuario());
			Filme filme = daoFilme.buscarPorId(compra.getFilme().getIdFilme());
			compra.setUsuario(usuario);
			compra.setFilme(filme);
		}
		return compra;
	}

	@Override
	public List<Compra> buscarTodos() throws Exception {
		List<Compra> compras = daoCompra.buscarTodos();
		for (Compra compra : compras) {
			Usuario usuario = daoUsuario.buscarPorId(compra.getUsuario().getIdUsuario());
			Filme filme = daoFilme.buscarPorId(compra.getFilme().getIdFilme());
			compra.setUsuario(usuario);
			compra.setFilme(filme);
		}
		return compras;
	}

	public List<Compra> obterComprasPendentesDoUsuario(final int idUsuario) throws Exception {
		return ((DaoCompra) daoCompra).obterComprasPendentesDoUsuario(idUsuario);
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
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
