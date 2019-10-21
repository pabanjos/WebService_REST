package app;

import java.util.List;
import java.util.Objects;

import beans.Operacao;
import beans.Usuario;
import dao.DaoOperacao;
import dao.DaoUsuario;

public class ServicoOperacao implements ICRUD<Operacao> {

	private final ICRUD<Operacao> daoOperacao = new DaoOperacao();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoOperacao() {
		super();
	}

	@Override
	public void create(final Operacao obj) throws Exception {
		daoOperacao.create(obj);
	}

	@Override
	public void createAll(final List<Operacao> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Operacao readById(final int id) throws Exception {
		Operacao operacao = daoOperacao.readById(id);
		if (Objects.nonNull(operacao)) {
			Usuario local = daoUsuario.readById(operacao.getUsuarioLocal().getIdUsuario());
			Usuario destino = daoUsuario.readById(operacao.getUsuarioDestino().getIdUsuario());
			operacao.setUsuarioLocal(local);
			operacao.setUsuarioDestino(destino);
		}
		return operacao;
	}

	@Override
	public List<Operacao> readAll() throws Exception {
		List<Operacao> operacoes = daoOperacao.readAll();
		for (Operacao operacao : operacoes) {
			Usuario local = daoUsuario.readById(operacao.getUsuarioLocal().getIdUsuario());
			Usuario destino = daoUsuario.readById(operacao.getUsuarioDestino().getIdUsuario());
			operacao.setUsuarioLocal(local);
			operacao.setUsuarioDestino(destino);
		}
		return operacoes;
	}

	@Override
	public void update(final Operacao obj) throws Exception {
		daoOperacao.update(obj);
	}

	@Override
	public void updateAll(final List<Operacao> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		daoOperacao.deleteById(id);
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
