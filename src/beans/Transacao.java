package beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idTransacao;
	private String operacao;
	private Integer valor;
	private LocalDateTime data;
	private Usuario usuarioLocal = new Usuario();
	private Usuario usuarioDestino = new Usuario();

	public Transacao() {
		super();
	}

	public Transacao(final Integer idTransacao, final String operacao, final Integer valor, final LocalDateTime data) {
		super();
		this.idTransacao = idTransacao;
		this.operacao = operacao;
		this.valor = valor;
		this.data = data;
	}

	public Transacao(final Integer idTransacao, final String operacao, final Integer valor, final LocalDateTime data,
			final Usuario usuarioLocal) {
		super();
		this.idTransacao = idTransacao;
		this.operacao = operacao;
		this.valor = valor;
		this.data = data;
		this.usuarioLocal = usuarioLocal;
	}

	public Transacao(final Integer idTransacao, final String operacao, final Integer valor, final LocalDateTime data,
			final Usuario usuarioLocal, final Usuario usuarioDestino) {
		super();
		this.idTransacao = idTransacao;
		this.operacao = operacao;
		this.valor = valor;
		this.data = data;
		this.usuarioLocal = usuarioLocal;
		this.usuarioDestino = usuarioDestino;
	}

	@Override
	public String toString() {
		return "{\"idTransacao\":" + idTransacao + ", \"operacao\":\"" + operacao + "\", \"valor\":" + valor
				+ ", \"data\":\"" + data + "\", \"usuarioLocal\":" + usuarioLocal + ", \"usuarioDestino\":"
				+ usuarioDestino + "}";
	}

	public String toCSV() {
		return getData2() + "\t" + operacao + "\t" + valor + "\n";
	}

	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(final Integer idTransacao) {
		this.idTransacao = idTransacao;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(final String operacao) {
		this.operacao = operacao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(final Integer valor) {
		this.valor = valor;
	}

	public String getData2() {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).concat(" às ")
				.concat(data.format(DateTimeFormatter.ofPattern("hh:mm")));
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(final LocalDateTime data) {
		this.data = data;
	}

	public Usuario getUsuarioLocal() {
		return usuarioLocal;
	}

	public void setUsuarioLocal(final Usuario usuarioLocal) {
		this.usuarioLocal = usuarioLocal;
	}

	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(final Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

}