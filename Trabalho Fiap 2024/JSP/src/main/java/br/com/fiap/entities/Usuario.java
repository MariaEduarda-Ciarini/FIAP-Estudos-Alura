package br.com.fiap.entities;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private byte idade;

	public Usuario() {

	super();

}

	public Usuario(String nome, byte idade) {
		super();
		this.nome=nome;
		this.idade=idade;
	}
	
	public String getNome() {
		return nome;
	}

	public byte getIdade() {
		return idade;
	}

	public void setIdade(byte idade) {
		this.idade = idade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}}
	
		
	
