package app;

import java.util.List;

import beans.Usuario;
import dao.DaoUsuario;
import dao.IDao;

public class ServicoUsuario {

	private final IDao<Usuario> dao = new DaoUsuario();

	public ServicoUsuario() {
		super();
	}

	public void create(final Usuario usuario) throws Exception {
		dao.create(usuario);
	}

	public Usuario readById(final Integer idUsuario) throws Exception {
		return dao.readById(idUsuario);
	}

	public List<Usuario> read() throws Exception {
		return dao.read();
	}

	public void update(final Usuario usuario) throws Exception {
		dao.update(usuario);
	}

	public void deleteById(final Integer idUsuario) throws Exception {
		dao.deleteById(idUsuario);
	}

}
