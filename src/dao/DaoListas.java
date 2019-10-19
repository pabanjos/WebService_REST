package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Acesso;
import beans.Compra;
import beans.Filme;
import beans.Mensagem;
import beans.Transacao;
import beans.Usuario;

public class DaoListas extends Dao {

	public DaoListas() {
		super();
	}

	public List<Transacao> transacoes() throws Exception {
		List<Transacao> lista = new ArrayList<Transacao>();
		abrirConexao();
		String syntax1 = "select * from transacao t ";
		String syntax2 = "inner join usuario u on t.usuarioLocal = u.idUsuario";
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			Transacao transacao = new Transacao();
			Usuario local = transacao.getUsuarioLocal();
			transacao.setIdTransacao(rs.getInt(1));
			transacao.setOperacao(rs.getString(2));
			transacao.setValor(rs.getInt(3));
			transacao.setData(rs.getTimestamp(4).toLocalDateTime());
			local.setIdUsuario(rs.getInt(7));
			local.setLogin(rs.getString(8));
			local.setSenha(rs.getString(9));
			local.setPerfil(rs.getString(10));
			local.setSaldo(rs.getInt(11));
			lista.add(transacao);
		}
		fecharConexao();
		return lista;
	}

	public List<Compra> compras() throws Exception {
		List<Compra> lista = new ArrayList<Compra>();
		abrirConexao();
		String syntax1 = "select * from compra c ";
		String syntax2 = "inner join usuario u on c.usuario = u.idUsuario ";
		String syntax3 = "inner join filme f on c.filme = f.idFilme";
		ps = con.prepareStatement(syntax1 + syntax2 + syntax3);
		rs = ps.executeQuery();
		while (rs.next()) {
			Compra compra = new Compra();
			compra.setIdCompra(rs.getInt(1));
			compra.setQuantidade(rs.getInt(2));
			compra.setStatus(rs.getString(3));
			compra.setData(rs.getTimestamp(4).toLocalDateTime());
			Usuario usuario = compra.getUsuario();
			usuario.setIdUsuario(rs.getInt(7));
			usuario.setLogin(rs.getString(8));
			usuario.setSenha(rs.getString(9));
			usuario.setPerfil(rs.getString(10));
			usuario.setSaldo(rs.getInt(11));
			Filme filme = compra.getFilme();
			filme.setIdFilme(rs.getInt(12));
			filme.setPoster(rs.getString(13));
			filme.setTitulo(rs.getString(14));
			filme.setGenero(rs.getString(15));
			filme.setProtagonista(rs.getString(16));
			filme.setDiretor(rs.getString(17));
			filme.setLancamento(rs.getInt(18));
			filme.setPreco(rs.getInt(19));
			filme.setEstoque(rs.getInt(20));
			lista.add(compra);
		}
		fecharConexao();
		return lista;
	}

	public List<Acesso> acessos() throws Exception {
		List<Acesso> lista = new ArrayList<Acesso>();
		abrirConexao();
		String syntax1 = "select * from acesso a ";
		String syntax2 = "inner join usuario u on a.usuario = u.idUsuario";
		ps = con.prepareStatement(syntax1 + syntax2);
		rs = ps.executeQuery();
		while (rs.next()) {
			Acesso acesso = new Acesso();
			Usuario usuario = acesso.getUsuario();
			acesso.setIdAcesso(rs.getInt(1));
			acesso.setNome(rs.getString(2));
			acesso.setIp(rs.getString(3));
			acesso.setData(rs.getTimestamp(4).toLocalDateTime());
			usuario.setIdUsuario(rs.getInt(6));
			usuario.setLogin(rs.getString(7));
			usuario.setSenha(rs.getString(8));
			usuario.setPerfil(rs.getString(9));
			usuario.setSaldo(rs.getInt(10));
		}
		fecharConexao();
		return lista;
	}

	public List<Mensagem> mensagens() throws Exception {
		List<Mensagem> lista = new ArrayList<Mensagem>();
		abrirConexao();
		String syntax1 = "select * from mensagem m ";
		String syntax2 = "inner join usuario u1 on m.remetente = u1.idUsuario ";
		String syntax3 = "inner join usuario u2 on m.destinatario = u2.idUsuario";
		ps = con.prepareStatement(syntax1 + syntax2 + syntax3);
		rs = ps.executeQuery();
		while (rs.next()) {
			Mensagem mensagem = new Mensagem();
			mensagem.setIdMensagem(rs.getInt(1));
			mensagem.setTexto(rs.getString(2));
			mensagem.setData(rs.getTimestamp(3).toLocalDateTime());
			mensagem.setVisualizada(rs.getString(4));
			Usuario remetente = mensagem.getRemetente();
			remetente.setIdUsuario(rs.getInt(7));
			remetente.setLogin(rs.getString(8));
			remetente.setSenha(rs.getString(9));
			remetente.setPerfil(rs.getString(10));
			remetente.setSaldo(rs.getInt(11));
			Usuario destinatario = mensagem.getDestinatario();
			destinatario.setIdUsuario(rs.getInt(12));
			destinatario.setLogin(rs.getString(13));
			destinatario.setSenha(rs.getString(14));
			destinatario.setPerfil(rs.getString(15));
			destinatario.setSaldo(rs.getInt(16));
		}
		fecharConexao();
		return lista;
	}

}
