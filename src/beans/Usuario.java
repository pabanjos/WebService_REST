package beans;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private String login;
	private String senha;
	private String perfil;
	private Integer saldo;
	private Pessoa pessoa = new Pessoa();

	public Usuario() {
		super();
	}

	public Usuario(final Integer idUsuario, final Integer saldo) {
		super();
		this.idUsuario = idUsuario;
		this.saldo = saldo;
	}

	public Usuario(final Integer idUsuario, final String login, final String senha, final String perfil,
			final Integer saldo) {
		super();
		this.idUsuario = idUsuario;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.saldo = saldo;
	}

	public Usuario(final Integer idUsuario, final String login, final String senha, final String perfil,
			final Integer saldo, final Pessoa pessoa) {
		super();
		this.idUsuario = idUsuario;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.saldo = saldo;
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "{\"idUsuario\":" + idUsuario + ",\"login\":\"" + login + "\",\"senha\":\"" + senha + "\",\"perfil\":\""
				+ perfil + "\",\"saldo\":" + saldo + ",\"pessoa\":" + pessoa + "}";
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(final Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(final String perfil) {
		this.perfil = perfil;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(final Integer saldo) {
		this.saldo = saldo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(final Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}