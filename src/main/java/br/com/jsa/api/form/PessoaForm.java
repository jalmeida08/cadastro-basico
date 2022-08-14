package br.com.jsa.api.form;

import java.time.LocalDate;

import br.com.jsa.infra.model.Pessoa;

public class PessoaForm {

	private String nome;
	private LocalDate dataNascimento;

	public PessoaForm() {}
	
	public Pessoa toPessoa() {
		var p = new Pessoa();
		p.setNome(this.nome);
		p.setDataNascimento(this.dataNascimento);
		return p;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
