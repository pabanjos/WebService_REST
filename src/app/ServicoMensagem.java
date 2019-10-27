package app;

import java.util.List;
import java.util.Objects;

import beans.Mensagem;
import beans.Usuario;
import dao.DaoMensagem;
import dao.DaoUsuario;
import dao.ICRUD;

public class ServicoMensagem implements ICRUD<Mensagem> {

	private final ICRUD<Mensagem> daoMensagem = new DaoMensagem();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoMensagem() {
		super();
	}

	@Override
	public void create(final Mensagem obj) throws Exception {
		daoMensagem.create(obj);
	}

	@Override
	public void createAll(final List<Mensagem> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mensagem readById(final int id) throws Exception {
		Mensagem mensagem = daoMensagem.readById(id);
		if (Objects.nonNull(mensagem)) {
			Usuario remetente = daoUsuario.readById(mensagem.getRemetente().getIdUsuario());
			Usuario destinatario = daoUsuario.readById(mensagem.getDestinatario().getIdUsuario());
			mensagem.setRemetente(remetente);
			mensagem.setDestinatario(destinatario);
		}
		return mensagem;
	}

	@Override
	public List<Mensagem> readAll() throws Exception {
		List<Mensagem> mensagens = daoMensagem.readAll();
		for (Mensagem mensagem : mensagens) {
			Usuario remetente = daoUsuario.readById(mensagem.getRemetente().getIdUsuario());
			Usuario destinatario = daoUsuario.readById(mensagem.getDestinatario().getIdUsuario());
			mensagem.setRemetente(remetente);
			mensagem.setDestinatario(destinatario);
		}
		return mensagens;
	}

	@Override
	public void update(final Mensagem obj) throws Exception {
		daoMensagem.update(obj);
	}

	@Override
	public void updateAll(final List<Mensagem> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(final int id) throws Exception {
		daoMensagem.deleteById(id);
	}

	@Override
	public void deleteAllById(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
