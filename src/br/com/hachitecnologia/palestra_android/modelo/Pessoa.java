package br.com.hachitecnologia.palestra_android.modelo;

import java.io.Serializable;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1321310020086594932L;

	private Long id;
	private String nome;
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
