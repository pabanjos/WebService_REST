package javabeans;

import org.json.simple.JSONObject;

public class Filme {
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
	}

	public Filme(Integer idFilme, String poster, String titulo, String genero, String protagonista, String diretor, Integer lancamento, Integer preco, Integer estoque) {
		this.idFilme = idFilme;
		this.poster = poster;
		this.titulo = titulo;
		this.genero = genero;
		this.protagonista = protagonista;
		this.diretor = diretor;
		this.lancamento = lancamento;
		this.preco = preco;
		this.estoque = estoque;
	}

	public Filme(JSONObject f) {
		this.titulo = (String) f.get("titulo");
		this.genero = (String) f.get("genero");
		this.protagonista = (String) f.get("protagonista");
		this.diretor = (String) f.get("diretor");
		this.lancamento = Integer.parseInt(f.get("lancamento").toString());
		this.preco = Integer.parseInt(f.get("preco").toString());
		this.estoque = Integer.parseInt(f.get("estoque").toString());
	}

	@Override
	public String toString() {
		return "{\"idFilme\":" + idFilme + ", \"poster\":\"" + poster + "\", \"titulo\":\"" + titulo + "\", \"genero\":\"" + genero + "\", \"protagonista\":\"" + protagonista + "\", \"diretor\":\"" + diretor + "\", \"lancamento\":" + lancamento + ", \"preco\":" + preco + ", \"estoque\":" + estoque
				+ "}";
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getProtagonista() {
		return protagonista;
	}

	public void setProtagonista(String protagonista) {
		this.protagonista = protagonista;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public Integer getLancamento() {
		return lancamento;
	}

	public void setLancamento(Integer lancamento) {
		this.lancamento = lancamento;
	}

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(Integer preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
