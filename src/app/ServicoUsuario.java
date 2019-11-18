package app;

import java.util.List;

import beans.Log;
import beans.Usuario;
import dao.DaoUsuario;
import infra.ICRUD;
import servico.ServicoResposta;

public class ServicoUsuario implements ICRUD<Usuario> {

	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoUsuario() {
		super();
	}

	@Override
	public void criar(final Usuario obj) throws Exception {
		Usuario usuarioBase = ((DaoUsuario) daoUsuario).readBylogin(obj.getLogin());
		if (usuarioBase != null) {
			ServicoResposta.adicionarLog(Log.falha("login já cadastrado."));
		} else {
			daoUsuario.criar(obj);
		}
	}

	@Override
	public void criarTodos(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Usuario buscarPorId(final int id) throws Exception {
		return daoUsuario.buscarPorId(id);
	}

	@Override
	public List<Usuario> buscarTodos() throws Exception {
		return daoUsuario.buscarTodos();
	}

	@Override
	public void alterar(final Usuario obj) throws Exception {
		daoUsuario.alterar(obj);
		ServicoResposta.adicionarLog(Log.sucesso("usuário atualizado."));
	}

	@Override
	public void alterarTodos(final List<Usuario> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		daoUsuario.deletarPorId(id);
		ServicoResposta.adicionarLog(Log.sucesso("usuário deletado."));
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
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
