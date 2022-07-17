package br.com.jsa.api.form;

import java.time.LocalDate;

import br.com.jsa.infra.model.Funcionario;

public class FuncionarioForm {

	private String nome;
	private LocalDate dataNascimento;
	private String email;

	public FuncionarioForm() {}
	
	public Funcionario toFuncionario() {
		Funcionario f = new Funcionario();
		f.setNome(this.nome);
		f.setDataNascimento(this.dataNascimento);
		return f;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
