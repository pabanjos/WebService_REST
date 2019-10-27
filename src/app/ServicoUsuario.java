package app;

import java.util.List;
import java.util.Objects;

import beans.Compra;
import beans.Filme;
import beans.Log;
import beans.Usuario;
import dao.DaoCompra;
import dao.DaoFilme;
import dao.DaoUsuario;
import dao.ICRUD;
import servico.ServicoResposta;

public class ServicoUsuario implements ICRUD<Usuario> {

	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();
	private final ICRUD<Compra> daoCompra = new DaoCompra();
	private final ICRUD<Filme> daoFilme = new DaoFilme();

	public ServicoUsuario() {
		super();
	}

	@Override
	public void create(final Usuario obj) throws Exception {
		daoUsuario.create(obj);
	}

	@Override
	public void createAll(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Usuario readById(final int id) throws Exception {
		return daoUsuario.readById(id);
	}

	@Override
	public List<Usuario> readAll() throws Exception {
		return daoUsuario.readAll();
	}

	@Override
	public void update(final Usuario obj) throws Exception {
		daoUsuario.update(obj);
		ServicoResposta.adicionarLog(Log.sucesso("usuário atualizado"));
	}

	@Override
	public void updateAll(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		daoUsuario.deleteById(id);
		ServicoResposta.adicionarLog(Log.sucesso("usuário deletado"));
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

	public void entrar(final Usuario usuario) throws Exception {
		Usuario resultado = null;
		if (Objects.isNull(usuario) || Objects.isNull(usuario.getLogin()) || Objects.isNull(usuario.getSenha())) {
			ServicoResposta.adicionarLog(Log.falha("dados inválidos"));
		} else {
			resultado = ((DaoUsuario) daoUsuario).entrar(usuario);
			if (resultado == null) {
				ServicoResposta.adicionarLog(Log.falha("login e senha não encontrados"));
			} else {
				ServicoResposta.adicionarObjeto("logado", resultado);
				ServicoResposta.adicionarLog(Log.sucesso("usuário logado"));
			}
		}
	}

}
