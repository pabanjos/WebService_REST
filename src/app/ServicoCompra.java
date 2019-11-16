package app;

import java.util.List;
import java.util.Objects;

import beans.Compra;
import beans.Filme;
import beans.Usuario;
import dao.DaoCompra;
import dao.DaoFilme;
import dao.DaoUsuario;
import dao.ICRUD;

public class ServicoCompra implements ICRUD<Compra> {

	private final ICRUD<Compra> daoCompra = new DaoCompra();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();
	private final ICRUD<Filme> daoFilme = new DaoFilme();

	public ServicoCompra() {
		super();
	}

	@Override
	public void create(final Compra obj) throws Exception {
		daoCompra.create(obj);
	}

	@Override
	public void createAll(final List<Compra> list) throws Exception {
		daoCompra.createAll(list);
	}

	@Override
	public Compra readById(final int id) throws Exception {
		Compra compra = daoCompra.readById(id);
		if (Objects.nonNull(compra)) {
			Usuario usuario = daoUsuario.readById(compra.getUsuario().getIdUsuario());
			Filme filme = daoFilme.readById(compra.getFilme().getIdFilme());
			compra.setUsuario(usuario);
			compra.setFilme(filme);
		}
		return compra;
	}

	@Override
	public List<Compra> readAll() throws Exception {
		List<Compra> compras = daoCompra.readAll();
		for (Compra compra : compras) {
			Usuario usuario = daoUsuario.readById(compra.getUsuario().getIdUsuario());
			Filme filme = daoFilme.readById(compra.getFilme().getIdFilme());
			compra.setUsuario(usuario);
			compra.setFilme(filme);
		}
		return compras;
	}

	public List<Compra> obterComprasPendentesDoUsuario(final int idUsuario) throws Exception {
		return ((DaoCompra) daoCompra).obterComprasPendentesDoUsuario(idUsuario);
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
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
