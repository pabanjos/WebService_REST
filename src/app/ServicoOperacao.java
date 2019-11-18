package app;

import java.util.List;
import java.util.Objects;

import beans.Operacao;
import beans.Usuario;
import dao.DaoOperacao;
import dao.DaoUsuario;
import infra.ICRUD;

public class ServicoOperacao implements ICRUD<Operacao> {

	private final ICRUD<Operacao> daoOperacao = new DaoOperacao();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoOperacao() {
		super();
	}

	@Override
	public void criar(final Operacao obj) throws Exception {
		daoOperacao.criar(obj);
	}

	@Override
	public void criarTodos(final List<Operacao> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Operacao buscarPorId(final int id) throws Exception {
		Operacao operacao = daoOperacao.buscarPorId(id);
		if (Objects.nonNull(operacao)) {
			Usuario local = daoUsuario.buscarPorId(operacao.getUsuarioLocal().getIdUsuario());
			Usuario destino = daoUsuario.buscarPorId(operacao.getUsuarioDestino().getIdUsuario());
			operacao.setUsuarioLocal(local);
			operacao.setUsuarioDestino(destino);
		}
		return operacao;
	}

	@Override
	public List<Operacao> buscarTodos() throws Exception {
		List<Operacao> operacoes = daoOperacao.buscarTodos();
		for (Operacao operacao : operacoes) {
			Usuario local = daoUsuario.buscarPorId(operacao.getUsuarioLocal().getIdUsuario());
			Usuario destino = daoUsuario.buscarPorId(operacao.getUsuarioDestino().getIdUsuario());
			operacao.setUsuarioLocal(local);
			operacao.setUsuarioDestino(destino);
		}
		return operacoes;
	}

	@Override
	public void alterar(final Operacao obj) throws Exception {
		daoOperacao.alterar(obj);
	}

	@Override
	public void alterarTodos(final List<Operacao> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		daoOperacao.deletarPorId(id);
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
