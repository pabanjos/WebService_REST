package beans;

import java.io.Serializable;

public class Filme implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idFilme;
	private String poster;
	private String titulo;
	private String genero;
	private String protagonista;
	private String diretor;
	private Integer lancamento;
	private Integer preco;
	private Integer estoque;

	public Filme() {
		super();
	}

	@Override
	public String toString() {
		return "Filme [idFilme=" + idFilme + ", poster=" + poster + ", titulo=" + titulo + ", genero=" + genero
				+ ", protagonista=" + protagonista + ", diretor=" + diretor + ", lancamento=" + lancamento + ", preco="
				+ preco + ", estoque=" + estoque + "]";
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(final Integer idFilme) {
		this.idFilme = idFilme;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(final String poster) {
		this.poster = poster;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(final String genero) {
		this.genero = genero;
	}

	public String getProtagonista() {
		return protagonista;
	}

	public void setProtagonista(final String protagonista) {
		this.protagonista = protagonista;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(final String diretor) {
		this.diretor = diretor;
	}

	public Integer getLancamento() {
		return lancamento;
	}

	public void setLancamento(final Integer lancamento) {
		this.lancamento = lancamento;
	}

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(final Integer preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(final Integer estoque) {
		this.estoque = estoque;
	}

}
