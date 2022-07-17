package br.com.jsa.api.dto;

public class NovoUsuarioDTO extends UsuarioForm {

	private String funcionarioId;

	public NovoUsuarioDTO(String email, String nome, String funcionarioId) {
		this.funcionarioId = funcionarioId;
		this.setEmail(email);
		this.setNome(nome);
	}

	public String getFuncionarioId() {
		return funcionarioId;
	}

	@Override
	public String toString() {
		return "NovoUsuarioForm ["
				+ "funcionarioId=" + funcionarioId + "'/',"
				+ "getNome()=" + getNome() + ","
				+ "getEmail()="+ getEmail() + " '/'"
			+ "]";
	}

	
}
