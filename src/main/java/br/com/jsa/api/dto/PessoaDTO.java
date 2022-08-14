package br.com.jsa.api.dto;

import java.time.LocalDate;

import br.com.jsa.infra.model.Pessoa;

public class PessoaDTO {

	private String id;
	private String nome;
	private LocalDate dataNascimento;
	
	public PessoaDTO(Pessoa p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.dataNascimento = p.getDataNascimento();
	}
	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
}
