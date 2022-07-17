package br.com.jsa.infra.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.jsa.api.dto.NovoUsuarioDTO;

@Component
public class FuncionarioProduce {

	@Value("${app.kafka.novo-funcionario.topic}")
	private String novoFuncionarioTopic;
	
	private final KafkaTemplate<String, NovoUsuarioDTO> kafkaTemplate;
	
	public FuncionarioProduce(KafkaTemplate<String, NovoUsuarioDTO> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void salvaUsuario(final @RequestBody NovoUsuarioDTO dto) {
		System.out.println("INÍCIO :: ENVIANDO NOVO USUARIO DTO");
		var key = dto.getEmail();
		kafkaTemplate.send(novoFuncionarioTopic, key, dto);
		System.out.println("FIM :: ENVIADO NOVO USUARIO DTO");
		
	}

}
