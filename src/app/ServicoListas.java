package app;

import java.util.List;

import beans.Acesso;
import beans.Compra;
import beans.Mensagem;
import beans.Transacao;
import dao.DaoListas;

public class ServicoListas {

	private final DaoListas dao = new DaoListas();

	public List<Transacao> transacoes() throws Exception {
		List<Transacao> lista = dao.transacoes();
		return lista;
	}

	public List<Compra> compras() throws Exception {
		List<Compra> lista = dao.compras();
		return lista;
	}

	public List<Acesso> acessos() throws Exception {
		List<Acesso> lista = dao.acessos();
		return lista;
	}

	public List<Mensagem> mensagens() throws Exception {
		List<Mensagem> lista = dao.mensagens();
		return lista;
	}

}
