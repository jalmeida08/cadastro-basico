package br.com.jsa.api.dto;

public class NovoUsuarioDTO extends UsuarioForm {

	private String pessoaId;

	public NovoUsuarioDTO(String email, String nome, String pessoaId) {
		this.pessoaId = pessoaId;
		this.setEmail(email);
		this.setNome(nome);
	}
	
	public NovoUsuarioDTO() {}

	public String getPessoaId() {
		return pessoaId;
	}

	public void setFuncionarioId(String pessoaId) {
		this.pessoaId = pessoaId;
	}

	@Override
	public String toString() {
		return "NovoUsuarioForm ["
				+ "funcionarioId=" + pessoaId + "'\',"
				+ "getNome()=" + getNome() + "',\'"
				+ "getEmail()="+ getEmail() + " '\'"
			+ "]";
	}

	
}
