package beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Acesso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idAcesso;
	private String nome;
	private String ip;
	private LocalDateTime data;
	private Usuario usuario = new Usuario();

	public Acesso() {
		super();
	}

	public Acesso(final Integer idAcesso, final String nome, final String ip, final LocalDateTime data) {
		super();
		this.idAcesso = idAcesso;
		this.nome = nome;
		this.ip = ip;
		this.data = data;
	}

	public Acesso(final Integer idAcesso, final String nome, final String ip, final LocalDateTime data,
			final Usuario usuario) {
		super();
		this.idAcesso = idAcesso;
		this.nome = nome;
		this.ip = ip;
		this.data = data;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "{\"idAcesso\":" + idAcesso + ",\"nome\":\"" + nome + "\",\"ip\":\"" + ip + "\",\"data\":\"" + data
				+ "\",\"usuario\":" + usuario + "}";
	}

	public Integer getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(final Integer idAcesso) {
		this.idAcesso = idAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(final LocalDateTime data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

}
