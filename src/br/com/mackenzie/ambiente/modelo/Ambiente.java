package br.com.mackenzie.ambiente.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta classe implementa ambientes.
 */
public class Ambiente implements Serializable {

	// Versão da classe.
	private static final long serialVersionUID = 1L;
	
	// Atributos.
	private Integer id;
	private String nome;
	private Integer porta;
	private Integer janela;
	private Integer metragem;
	
	// Construtores.
	public Ambiente() {}
	public Ambiente(Integer id) { this.id = id; }
	public Ambiente(Integer id, String nome, Integer porta, Integer janela,
			Integer metragem) {
		super();
		setId(id);
		setNome(nome);
		setPorta(porta);
		setJanela(janela);
		setMetragem(metragem);
	}	

	// Métodos de acesso.
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public Integer getJanela() {
		return janela;
	}
	public void setJanela(Integer janela) {
		this.janela = janela;
	}
	public Integer getMetragem() {
		return metragem;
	}
	public void setMetragem(Integer metragem) {
		this.metragem = metragem;
	}
	
}
