package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Mensagem;
import beans.Usuario;
import infra.Constantes;
import infra.ICRUD;
import utils.Dao;

public class DaoMensagem extends Dao implements ICRUD<Mensagem> {

	public DaoMensagem() {
		super();
	}

	@Override
	public void criar(final Mensagem mensagem) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("INSERT INTO mensagem VALUES (NULL,?,now(),'N',?,?)");
		ps.setString(1, mensagem.getTexto());
		ps.setInt(3, mensagem.getRemetente().getIdUsuario());
		ps.setInt(4, mensagem.getDestinatario().getIdUsuario());
		ps.execute();
		con.close();
	}

	@Override
	public void criarTodos(final List<Mensagem> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mensagem buscarPorId(final int id) throws Exception {
		return null;
	}

	@Override
	public List<Mensagem> buscarTodos() throws Exception {
		List<Mensagem> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM mensagem");
		rs = ps.executeQuery();
		while (rs.next()) {
			Mensagem mensagem = new Mensagem();
			mensagem.setIdMensagem(rs.getInt(1));
			mensagem.setTexto(rs.getString(2));
			mensagem.setData(rs.getTimestamp(3).toLocalDateTime());
			mensagem.setVisualizada(rs.getString(4));
			mensagem.getRemetente().setIdUsuario(rs.getInt(5));
			mensagem.getDestinatario().setIdUsuario(rs.getInt(6));
			lista.add(mensagem);
		}
		fecharConexao();
		return lista;
	}

	public List<Mensagem> selecionar(final Integer remetente, final Integer destinatario) throws Exception {
		abrirConexao(Constantes.BANCO);
		String sql1 = "select * from mensagem m ";
		String sql2 = "inner join usuario u1 on m.remetente = u1.idUsuario ";
		String sql3 = "inner join usuario u2 on m.destinatario = u2.idUsuario ";
		String sql4 = "where remetente=? and destinatario=? ";
		String sql5 = "or m.remetente=? and m.destinatario=?";
		String sql6 = "order by idMensagem desc";
		ps = con.prepareStatement(sql1 + sql2 + sql3 + sql4 + sql5 + sql6);
		ps.setInt(1, remetente);
		ps.setInt(2, destinatario);
		ps.setInt(3, destinatario);
		ps.setInt(4, remetente);
		rs = ps.executeQuery();
		List<Mensagem> lista = new ArrayList<>();
		while (rs.next()) {
			lista.add(new Mensagem(rs.getInt(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getString(4),
					new Usuario(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11)),
					new Usuario(rs.getInt(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getInt(16))));
		}
		con.close();
		return lista;
	}

	@Override
	public void alterar(final Mensagem mensagem) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("UPDATE mensagem SET visualizada='S' WHERE remetente=? AND destinatario=?");
		ps.setInt(1, mensagem.getRemetente().getIdUsuario());
		ps.setInt(2, mensagem.getDestinatario().getIdUsuario());
		ps.execute();
		con.close();
	}

	@Override
	public void alterarTodos(final List<Mensagem> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM mensagem WHERE idMensagem=" + id);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM mensagem WHERE idMensagem=" + id);
			ps.execute();
		}
		fecharConexao();
	}
}
