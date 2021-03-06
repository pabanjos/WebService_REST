package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Operacao;
import infra.Constantes;
import infra.ICRUD;
import utils.Dao;

public class DaoOperacao extends Dao implements ICRUD<Operacao> {

	public DaoOperacao() {
		super();
	}

	@Override
	public void criar(final Operacao operacao) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("INSERT INTO operacao VALUES (NULL, ?, ?, now(), ?, ?)");
		ps.setString(1, operacao.getTipo());
		ps.setDouble(2, operacao.getValor());
		ps.setInt(3, operacao.getUsuarioLocal().getIdUsuario());
		ps.setInt(4, operacao.getUsuarioDestino().getIdUsuario());
		ps.execute();
		fecharConexao();
	}

	@Override
	public void criarTodos(final List<Operacao> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Operacao buscarPorId(final int id) throws Exception {
		throw new UnsupportedOperationException();
	}

	public List<Operacao> selecionarPorUsuario(final Integer idUsuario) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM operacao WHERE usuarioLocal=?");
		ps.setInt(1, idUsuario);
		rs = ps.executeQuery();
		List<Operacao> lista = new ArrayList<>();
		while (rs.next()) {
			lista.add(new Operacao(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4).toLocalDateTime()));
		}
		fecharConexao();
		return lista;
	}

	@Override
	public List<Operacao> buscarTodos() throws Exception {
		List<Operacao> lista = new ArrayList<>();
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("SELECT * FROM operacao");
		rs = ps.executeQuery();
		while (rs.next()) {
			Operacao operacao = new Operacao();
			operacao.setIdOperacao(rs.getInt(1));
			operacao.setTipo(rs.getString(2));
			operacao.setValor(rs.getInt(3));
			operacao.setData(rs.getTimestamp(4).toLocalDateTime());
			operacao.getUsuarioLocal().setIdUsuario(rs.getInt(5));
			operacao.getUsuarioDestino().setIdUsuario(rs.getInt(6));
			lista.add(operacao);
		}
		fecharConexao();
		return lista;
	}

	@Override
	public void alterar(final Operacao obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void alterarTodos(final List<Operacao> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		abrirConexao(Constantes.BANCO);
		ps = con.prepareStatement("DELETE FROM operacao WHERE idOperacao=" + id);
		ps.execute();
		fecharConexao();
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		abrirConexao(Constantes.BANCO);
		for (int id : ids) {
			ps = con.prepareStatement("DELETE FROM operacao WHERE idOperacao=" + id);
			ps.execute();
		}
		fecharConexao();
	}
}
