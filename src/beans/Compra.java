package beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idCompra;
	private Integer quantidade;
	private String status;
	private LocalDateTime data;
	private Usuario usuario = new Usuario();
	private Filme filme = new Filme();
	private Integer valor;

	private static Integer desconto = 0;
	private static Integer frete = 0;
	private static Integer quantidadeTotal = 0;
	private static Integer valorTotal = 0;
	private static Integer valorTotalFinal = 0;

	public Compra() {
		super();
	}

	public Compra(final Integer idCompra, final Integer quantidade, final String status, final LocalDateTime data,
			final Usuario usuario, final Filme filme) {
		super();
		this.idCompra = idCompra;
		this.quantidade = quantidade;
		this.status = status;
		this.data = data;
		this.usuario = usuario;
		this.filme = filme;
	}

	@Override
	public String toString() {
		return "{\"idCompra\":" + idCompra + ",\"quantidade\":" + quantidade + ",\"status\":\"" + status
				+ "\",\"data\":\"" + data + "\",\"usuario\":" + usuario + ",\"filme\":" + filme + ",\"valor\":" + valor
				+ "}";
	}

	public String toCSV() {
		return getData2() + "\t" + filme.getTitulo() + "\t" + quantidade + "\t" + filme.getPreco() + "\n";
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(final Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(final Filme filme) {
		this.filme = filme;
	}

	public Integer getValor() {
		valor = quantidade * filme.getPreco();
		return valor;
	}

	public void setValor(final Integer valor) {
		this.valor = valor;
	}

	public static Integer getDesconto() {
		return desconto;
	}

	public static void setDesconto(final Integer desconto) {
		Compra.desconto = desconto;
	}

	public static Integer getFrete() {
		return frete;
	}

	public static void setFrete(final Integer frete) {
		Compra.frete = frete;
	}

	public static Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public static void setQuantidadeTotal(final Integer quantidadeTotal) {
		Compra.quantidadeTotal = quantidadeTotal;
	}

	public static Integer getValorTotal() {
		return valorTotal;
	}

	public static void setValorTotal(final Integer valorTotal) {
		Compra.valorTotal = valorTotal;
	}

	public static Integer getValorTotalFinal() {
		return valorTotalFinal;
	}

	public static void setValorTotalFinal(final Integer valorTotalFinal) {
		Compra.valorTotalFinal = valorTotalFinal;
	}

}