package br.com.jsa.infra.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.jsa.api.dto.NovoUsuarioDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PessoaProduce {
	
	public static final Logger logger = LoggerFactory.getLogger(PessoaProduce.class); 

	@Value("${app.kafka.producer.pessoa.nova-pessoa.topic}")
	private String novaPessoaTopic;
	
	private final KafkaTemplate<String, NovoUsuarioDTO> kafkaTemplate;
	
	public PessoaProduce(KafkaTemplate<String, NovoUsuarioDTO> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void salvaUsuario(final @RequestBody NovoUsuarioDTO dto) {
		logger.info("INÍCIO :: ENVIANDO NOVO USUARIO DTO");
		var key = dto.getEmail();
		kafkaTemplate.send(novaPessoaTopic, key, dto);
		logger.info("FIM :: ENVIADO NOVO USUARIO DTO");
		
	}

}
