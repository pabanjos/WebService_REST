package app;

import java.net.InetAddress;
import java.util.List;
import java.util.Objects;

import beans.Registro;
import beans.Usuario;
import dao.DaoRegistro;
import dao.DaoUsuario;
import infra.ICRUD;

public class ServicoRegistro implements ICRUD<Registro> {

	private final ICRUD<Registro> daoRegistro = new DaoRegistro();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoRegistro() {
		super();
	}

	@Override
	public void criar(final Registro obj) throws Exception {
		obj.setNome(InetAddress.getLocalHost().getHostName());
		obj.setIp(InetAddress.getLocalHost().getHostAddress());
		daoRegistro.criar(obj);
	}

	@Override
	public void criarTodos(final List<Registro> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Registro buscarPorId(final int id) throws Exception {
		Registro acesso = daoRegistro.buscarPorId(id);
		if (Objects.nonNull(acesso)) {
			Usuario usuario = daoUsuario.buscarPorId(acesso.getUsuario().getIdUsuario());
			acesso.setUsuario(usuario);
		}
		return acesso;
	}

	@Override
	public List<Registro> buscarTodos() throws Exception {
		List<Registro> acessos = daoRegistro.buscarTodos();
		for (Registro a : acessos) {
			Usuario u = daoUsuario.buscarPorId(a.getUsuario().getIdUsuario());
			a.setUsuario(u);
		}
		return acessos;
	}

	@Override
	public void alterar(final Registro obj) throws Exception {
		daoRegistro.alterar(obj);
	}

	@Override
	public void alterarTodos(final List<Registro> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		daoRegistro.deletarPorId(id);
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
