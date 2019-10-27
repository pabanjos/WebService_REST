package app;

import java.util.List;
import java.util.Objects;

import beans.Registro;
import beans.Usuario;
import dao.DaoRegistro;
import dao.DaoUsuario;
import dao.ICRUD;

public class ServicoRegistro implements ICRUD<Registro> {

	private final ICRUD<Registro> daoRegistro = new DaoRegistro();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoRegistro() {
		super();
	}

	@Override
	public void create(final Registro obj) throws Exception {
		daoRegistro.create(obj);
	}

	@Override
	public void createAll(final List<Registro> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Registro readById(final int id) throws Exception {
		Registro acesso = daoRegistro.readById(id);
		if (Objects.nonNull(acesso)) {
			Usuario usuario = daoUsuario.readById(acesso.getUsuario().getIdUsuario());
			acesso.setUsuario(usuario);
		}
		return acesso;
	}

	@Override
	public List<Registro> readAll() throws Exception {
		List<Registro> acessos = daoRegistro.readAll();
		for (Registro a : acessos) {
			Usuario u = daoUsuario.readById(a.getUsuario().getIdUsuario());
			a.setUsuario(u);
		}
		return acessos;
	}

	@Override
	public void update(final Registro obj) throws Exception {
		daoRegistro.update(obj);
	}

	@Override
	public void updateAll(final List<Registro> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		daoRegistro.deleteById(id);
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
