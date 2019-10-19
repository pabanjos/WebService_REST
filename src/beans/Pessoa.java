package beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idPessoa;
	private String nome;
	private String email;
	private String sexo;
	private String telefone;
	private String cep;
	private LocalDate nascimento;

	public Pessoa() {
		super();
	}

	public Pessoa(final Integer idPessoa, final String nome, final String email, final String sexo,
			final String telefone, final String cep, final LocalDate nascimento) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.telefone = telefone;
		this.cep = cep;
		this.nascimento = nascimento;
	}

	@Override
	public String toString() {
		return "{\"idPessoa\":" + idPessoa + ",\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"sexo\":\"" + sexo
				+ "\",\"telefone\":\"" + telefone + "\",\"cep\":\"" + cep + "\",\"nascimento\":\"" + nascimento + "\"}";
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(final Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(final String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(final LocalDate nascimento) {
		this.nascimento = nascimento;
	}

}
