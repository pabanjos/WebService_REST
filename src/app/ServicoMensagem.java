package app;

import java.util.List;
import java.util.Objects;

import beans.Mensagem;
import beans.Usuario;
import dao.DaoMensagem;
import dao.DaoUsuario;
import infra.ICRUD;

public class ServicoMensagem implements ICRUD<Mensagem> {

	private final ICRUD<Mensagem> daoMensagem = new DaoMensagem();
	private final ICRUD<Usuario> daoUsuario = new DaoUsuario();

	public ServicoMensagem() {
		super();
	}

	public String censurar(final String texto) {
		return texto.replace("\n", " ")//
				.replace("caralho", "c******")//
				.replace("porra", "p****")//
				.replace("fuder", "f****")//
				.replace("puta", "p***")//
				.replace("vsf", "v**")//
				.replace("fdp", "f**");
	}

	@Override
	public void criar(final Mensagem obj) throws Exception {
		daoMensagem.criar(obj);
	}

	@Override
	public void criarTodos(final List<Mensagem> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mensagem buscarPorId(final int id) throws Exception {
		Mensagem mensagem = daoMensagem.buscarPorId(id);
		if (Objects.nonNull(mensagem)) {
			Usuario remetente = daoUsuario.buscarPorId(mensagem.getRemetente().getIdUsuario());
			Usuario destinatario = daoUsuario.buscarPorId(mensagem.getDestinatario().getIdUsuario());
			mensagem.setRemetente(remetente);
			mensagem.setDestinatario(destinatario);
		}
		return mensagem;
	}

	@Override
	public List<Mensagem> buscarTodos() throws Exception {
		List<Mensagem> mensagens = daoMensagem.buscarTodos();
		for (Mensagem mensagem : mensagens) {
			Usuario remetente = daoUsuario.buscarPorId(mensagem.getRemetente().getIdUsuario());
			Usuario destinatario = daoUsuario.buscarPorId(mensagem.getDestinatario().getIdUsuario());
			mensagem.setRemetente(remetente);
			mensagem.setDestinatario(destinatario);
		}
		return mensagens;
	}

	@Override
	public void alterar(final Mensagem obj) throws Exception {
		daoMensagem.alterar(obj);
	}

	@Override
	public void alterarTodos(final List<Mensagem> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		daoMensagem.deletarPorId(id);
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

}
