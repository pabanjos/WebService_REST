package app;

import java.util.List;

import beans.Log;
import beans.Usuario;
import dao.DaoUsuario;
import dao.ICRUD;
import servico.ServicoResposta;

public class ServicoUsuario implements ICRUD<Usuario> {

	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoUsuario() {
		super();
	}

	@Override
	public void create(final Usuario obj) throws Exception {
		Usuario usuarioBase = ((DaoUsuario) daoUsuario).readBylogin(obj.getLogin());
		if (usuarioBase != null) {
			ServicoResposta.adicionarLog(Log.falha("login já cadastrado."));
		} else {
			daoUsuario.create(obj);
		}
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
		ServicoResposta.adicionarLog(Log.sucesso("usuário atualizado."));
	}

	@Override
	public void updateAll(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		daoUsuario.deleteById(id);
		ServicoResposta.adicionarLog(Log.sucesso("usuário deletado."));
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

	public void entrar(final Usuario u) throws Exception {
		Usuario usuario = ((DaoUsuario) daoUsuario).entrar(u);
		if (usuario == null) {
			ServicoResposta.adicionarLog(Log.falha("login e senha não encontrados."));
		} else {
			CacheUsuario.setLOGADO(usuario);
			ServicoResposta.adicionarObjeto("logado", usuario);
			ServicoResposta.adicionarLog(Log.sucesso("usuário logado."));
		}
	}

	public void sair(final Usuario u) throws Exception {
		CacheUsuario.deslogar(u);
		ServicoResposta.adicionarLog(Log.falha("logout efetuado."));
	}

}
