package infra;

import java.io.Serializable;

public class Log implements Serializable {
	private static final long serialVersionUID = 1L;
	private String texto;
	private String tipo;

	public Log() {
		super();
	}

	public Log(final String texto, final String tipo) {
		super();
		this.texto = texto;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Log [texto=" + texto + ", tipo=" + tipo + "]";
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

}
