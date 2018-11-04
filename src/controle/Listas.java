package controle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import persistence.Dao;

@Path("/listas")
public class Listas extends Dao {
	
	@GET
	@Path("/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	public String clientes() {
		try {
			conectar();
			String syntax1 = "select * from Conta ";
			String syntax2 = "inner join Pessoa on Conta.idConta = Pessoa.conta where Conta.perfil='Cliente'";
			ps = con.prepareStatement(syntax1 + syntax2);
			rs = ps.executeQuery();
			String clientes = "[";
			while (rs.next()) {
				clientes += "{\"idConta\":" + rs.getInt(1) 
							+ ",\"usuario\":\"" + rs.getString(2) 
							+ "\",\"senha\":\"" + rs.getString(3) 
							+ "\",\"perfil\":\"" + rs.getString(4) 
							+ "\",\"saldo\":" + rs.getInt(5)  
							+ ",\"pessoa\":{" 
								+ "\"idPessoa\":" + rs.getInt(6) 
								+ ",\"nome\":\"" + rs.getString(7) 
								+ "\",\"email\":\"" + rs.getString(8) 
								+ "\",\"sexo\":\"" + rs.getString(9) 
								+ "\",\"telefone\":\"" + rs.getString(10) 
								+ "\",\"cep\":\"" + rs.getString(11) 
								+ "\",\"nascimento\":\"" + rs.getDate(12).toLocalDate() 
							+ "\"}}";
				if (!rs.isLast()) {
					clientes += ",";
				}
			}
			clientes += "]";
			con.close();
			return clientes;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

	@GET
	@Path("/filmes")
	@Produces(MediaType.APPLICATION_JSON)
	public String filmes() {
		try {
			conectar();
			ps = con.prepareStatement("select * from Filme order by lancamento desc");
			rs = ps.executeQuery();
			String filmes = "[";
			while (rs.next()) {
				filmes += "{\"idFilme\":" + rs.getInt(1) 
						+ ",\"poster\":\"" + rs.getString(2)
						+ "\",\"titulo\":\"" + rs.getString(3) 
						+ "\",\"genero\":\"" + rs.getString(4) 
						+ "\",\"protagonista\":\"" + rs.getString(5) 
						+ "\",\"diretor\":\"" + rs.getString(6) 
						+ "\",\"lancamento\":" + rs.getInt(7) 
						+ ",\"preco\":" + rs.getInt(8)
						+ ",\"estoque\":" + rs.getInt(9) 
						+ "}";
				if (!rs.isLast()) {
					filmes += ",";
				}
			}
			filmes += "]";
			con.close();
			return filmes;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

	@GET
	@Path("/filmes/{idFilme}")
	@Produces(MediaType.APPLICATION_JSON)
	public String filme(@PathParam("idFilme") Integer idFilme) {
		try {
			conectar();
			ps = con.prepareStatement("select * from Filme where idFilme=" + idFilme);
			rs = ps.executeQuery();
			String filme = "{";
			while (rs.next()) {
				filme += "\"idFilme\":" + rs.getInt(1) 
						+ ",\"poster\":\"" + rs.getString(2)
						+ "\",\"titulo\":\"" + rs.getString(3) 
						+ "\",\"genero\":\"" + rs.getString(4) 
						+ "\",\"protagonista\":\"" + rs.getString(5) 
						+ "\",\"diretor\":\"" + rs.getString(6) 
						+ "\",\"lancamento\":" + rs.getInt(7) 
						+ ",\"preco\":" + rs.getInt(8)
						+ ",\"estoque\":" + rs.getInt(9);
			}
			filme += "}";
			con.close();
			return filme;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

	@GET
	@Path("/transacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public String transacoes() {
		try {
			conectar();
			String syntax1 = "select * from Transacao ";
			String syntax2 = "inner join Conta on Transacao.contaLocal = Conta.idConta";
			ps = con.prepareStatement(syntax1 + syntax2);
			rs = ps.executeQuery();
			String transacoes = "[";
			while (rs.next()) {
				transacoes += "{\"idTransacao\":" + rs.getInt(1) 
							+ ",\"operacao\":\"" + rs.getString(2) 
							+ "\",\"valor\":" + rs.getInt(3) 
							+ ",\"data\":\"" + rs.getTimestamp(4).toLocalDateTime()
							+ "\",\"contaLocal\":{"
								+ "\"idConta\":" + rs.getInt(7) 
								+ ",\"usuario\":\"" + rs.getString(8) 
								+ "\",\"senha\":\"" + rs.getString(9) 
								+ "\",\"perfil\":\"" + rs.getString(10) 
								+ "\",\"saldo\":" + rs.getInt(11) 
							+ "}}";
				if (!rs.isLast()) {
					transacoes += ",";
				}
			}
			transacoes += "]";
			con.close();
			return transacoes;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

	@GET
	@Path("/compras")
	@Produces(MediaType.APPLICATION_JSON)
	public String compras() {
		try {
			conectar();
			String syntax1 = "select * from Compra ";
			String syntax2 = "inner join Conta on Compra.conta = Conta.idConta  ";
			String syntax3 = "inner join Filme on Compra.filme = Filme.idFilme";
			ps = con.prepareStatement(syntax1 + syntax2 + syntax3);
			rs = ps.executeQuery();
			String compras = "[";
			while (rs.next()) {
				compras += "{\"idCompra\":" + rs.getInt(1) 
						+ ",\"quantidade\":" + rs.getInt(2) 
						+ ",\"status\":\"" + rs.getString(3) 
						+ "\",\"data\":\"" + rs.getTimestamp(4).toLocalDateTime() 
						+ "\",\"conta\":{"
							+ "\"idConta\":" +  rs.getInt(7)
							+ ",\"usuario\":\"" + rs.getString(8) 
							+ "\",\"senha\":\"" + rs.getString(9) 
							+ "\",\"perfil\":\"" + rs.getString(10) 
							+ "\",\"saldo\":" + rs.getInt(11) + "},"
						+ "\"filme\":{"
							+ "\"idFilme\":" + rs.getInt(12) 
							+ ",\"poster\":\"" + rs.getString(13) 
							+ "\",\"titulo\":\"" + rs.getString(14) 
							+ "\",\"genero\":\"" + rs.getString(15) 
							+ "\",\"protagonista\":\"" + rs.getString(16) 
							+ "\",\"diretor\":\"" + rs.getString(17) 
							+ "\",\"lancamento\":" + rs.getInt(18) 
							+ ",\"preco\":" + rs.getInt(19)
							+ ",\"estoque\":" + rs.getInt(20) 
						+ "}}";
				if (!rs.isLast()) {
					compras += ",";
				}
			}
			compras += "]";
			con.close();
			return compras;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

	@GET
	@Path("/acessos")
	@Produces(MediaType.APPLICATION_JSON)
	public String acessos() {
		try {
			conectar();
			String syntax1 = "select * from Acesso ";
			String syntax2 = "inner join Conta on Acesso.conta = Conta.idConta";
			ps = con.prepareStatement(syntax1 + syntax2);
			rs = ps.executeQuery();
			String acessos = "[";
			while (rs.next()) {
				acessos += "{\"idAcesso\":" + rs.getInt(1) 
						+ ",\"nome\":\"" + rs.getString(2) 
						+ "\",\"ip\":\"" + rs.getString(3) 
						+ "\",\"data\":\"" + rs.getTimestamp(4).toLocalDateTime()
						+ "\",\"conta\":{"
							+ "\"idConta\":" +  rs.getInt(6)
							+ ",\"usuario\":\"" + rs.getString(7) 
							+ "\",\"senha\":\"" + rs.getString(8) 
							+ "\",\"perfil\":\"" + rs.getString(9) 
							+ "\",\"saldo\":" + rs.getInt(10) 
						+ "}}";
				if (!rs.isLast()) {
					acessos += ",";
				}
			}
			acessos += "]";
			con.close();
			return acessos;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

	@GET
	@Path("/mensagens")
	@Produces(MediaType.APPLICATION_JSON)
	public String mensagens() {
		try {
			conectar();
			String syntax1 = "select * from Mensagem ";
			String syntax2 = "inner join Conta c1 on Mensagem.remetente = c1.idConta ";
			String syntax3 = "inner join Conta c2 on Mensagem.destinatario = c2.idConta";
			ps = con.prepareStatement(syntax1 + syntax2 + syntax3);
			rs = ps.executeQuery();
			String mensagens = "[";
			while (rs.next()) {
				mensagens += "{\"idMensagem\":" + rs.getInt(1) 
							+ ",\"texto\":\"" + rs.getString(2) 
							+ "\",\"data\":\"" + rs.getTimestamp(3).toLocalDateTime() 
							+ "\",\"visualizada\":\"" + rs.getString(4) 
						+ "\",\"remetente\":{"
							+ "\"idConta\":" +  rs.getInt(7)
							+ ",\"usuario\":\"" + rs.getString(8) 
							+ "\",\"senha\":\"" + rs.getString(9) 
							+ "\",\"perfil\":\"" + rs.getString(10) 
							+ "\",\"saldo\":" + rs.getInt(11) + "},"
						+ "\"destinatario\":{"
							+ "\"idConta\":" +  rs.getInt(12)
							+ ",\"usuario\":\"" + rs.getString(13) 
							+ "\",\"senha\":\"" + rs.getString(14) 
							+ "\",\"perfil\":\"" + rs.getString(15) 
							+ "\",\"saldo\":" + rs.getInt(16) 
						+ "}}";
				if (!rs.isLast()) {
					mensagens += ",";
				}
			}
			mensagens += "]";
			con.close();
			return mensagens;
		} catch (Exception ex) {
			return "Falha: " + ex.getMessage();
		}
	}

}
