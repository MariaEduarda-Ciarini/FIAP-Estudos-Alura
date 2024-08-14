package br.com.fiap.exemplomvc.bean;

import java.io.Serializable;
import java.util.Calendar;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codigo;
	private String nome;
	private double valor;
	private Calendar dataFabricacao;
	private int quantidade;

	public Produto() {
		super();
	}

	public Produto(int codigo, String nome, double valor, Calendar dataFabricacao, int quantidade) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.dataFabricacao = dataFabricacao;
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;

	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;

	}

	public String getNome() {
		return nome;

	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Calendar dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
