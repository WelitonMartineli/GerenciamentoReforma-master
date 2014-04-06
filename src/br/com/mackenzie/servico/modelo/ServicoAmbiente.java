package br.com.mackenzie.servico.modelo;

import java.io.Serializable;

public class ServicoAmbiente implements Serializable {

	// Versão da classe.
	private static final long serialVersionUID = 1L;
		
	// Atributos.
	private Integer id;
	private Integer idAmbiente;
	private String codServico;
	private String desServico;	
	
	// Construtores.
	public ServicoAmbiente() {}
		public ServicoAmbiente(Integer id) { this.id = id; }
		public ServicoAmbiente(Integer id, Integer idAmbiente, String codServico, String desServico) {
			super();
			setId(id);
			setIdAmbiente(idAmbiente);
			setCodServico(codServico);
			setDesServico(desServico);

	}		
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdAmbiente() {
		return idAmbiente;
	}
	public void setIdAmbiente(Integer idAmbiente) {
		this.idAmbiente = idAmbiente;
	}
	public String getCodServico() {
		return codServico;
	}
	public void setCodServico(String codServico) {
		this.codServico = codServico;
	}
	public String getDesServico() {
		return desServico;
	}
	public void setDesServico(String desServico) {
		this.desServico = desServico;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}
