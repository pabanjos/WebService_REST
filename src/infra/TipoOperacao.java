package infra;

public enum TipoOperacao {
	DEPOSITO(1, "depósito"), //
	SAQUE(2, "saque"), //
	TRANSFERENCIA(3, "transferência");

	private int id;
	private String descricao;

	TipoOperacao(final int id, final String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public TipoOperacao obterPorId(final int id) {
		switch (id) {
		case 1:
			return TipoOperacao.DEPOSITO;
		case 2:
			return TipoOperacao.SAQUE;
		case 3:
			return TipoOperacao.TRANSFERENCIA;
		default:
			return null;
		}
	}

}
