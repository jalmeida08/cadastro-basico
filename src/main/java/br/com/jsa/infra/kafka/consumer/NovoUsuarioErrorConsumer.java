package br.com.jsa.infra.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jsa.api.dto.NovoUsuarioDTO;
import br.com.jsa.api.service.FuncionarioService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NovoUsuarioErrorConsumer {

	public static final Logger logger = LoggerFactory.getLogger(NovoUsuarioErrorConsumer.class);
	
	@Value("${app.kafka.consumer.usuario.erro-novo-usuario.topic}")
	private String erroNovoUsuario;
	

	@Autowired
	private FuncionarioService funcionarioService;
	
	@KafkaListener(
			topics ="${app.kafka.consumer.usuario.erro-novo-usuario.topic}",
			groupId = "app.kafka.consumer.usuario.erro-novo-usuario.group-id")
	@Transactional
	private void erroNovoUsuarioFuncionario(NovoUsuarioDTO dto) {
		
		logger.info("INICIO :: REMOVENDO FUNCIONARIO");
		this.funcionarioService.removeFuncionario(dto.getPessoaId());
		logger.info("FIM :: REMOVENDO FUNCIONARIO");
	}

}
