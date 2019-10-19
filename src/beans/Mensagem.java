package beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idMensagem;
	private String texto;
	private LocalDateTime data;
	private String visualizada;
	private Usuario remetente = new Usuario();
	private Usuario destinatario = new Usuario();

	public Mensagem() {
		super();
	}

	public Mensagem(final Integer idMensagem, final String texto, final LocalDateTime data, final String visualizada,
			final Usuario remetente, final Usuario destinatario) {
		super();
		this.idMensagem = idMensagem;
		this.texto = texto;
		this.data = data;
		this.visualizada = visualizada;
		this.remetente = remetente;
		this.destinatario = destinatario;
	}

	@Override
	public String toString() {
		return "{\"idMensagem\":" + idMensagem + ",\"texto\":\"" + texto + "\",\"data\":\"" + data
				+ "\",\"visualizada\":\"" + visualizada + "\",\"remetente\":" + remetente + ",\"destinatario\":"
				+ destinatario + "}";
	}

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(final Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getDataFormatada() {
		return data.format(DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy"));
	}

	public void setData(final LocalDateTime data) {
		this.data = data;
	}

	public String getVisualizada() {
		return visualizada;
	}

	public void setVisualizada(final String visualizada) {
		this.visualizada = visualizada;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(final Usuario remetente) {
		this.remetente = remetente;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(final Usuario destinatario) {
		this.destinatario = destinatario;
	}

}