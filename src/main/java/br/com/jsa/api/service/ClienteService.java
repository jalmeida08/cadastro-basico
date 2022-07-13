package br.com.jsa.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsa.api.dto.ClienteDTO;
import br.com.jsa.api.form.ClienteForm;
import br.com.jsa.infra.exception.ParametroInvalidoException;
import br.com.jsa.infra.model.Cliente;
import br.com.jsa.infra.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<Cliente> getOptionalCliente(String id) {
		return clienteRepository
				.findById(id);
	}
	
	public Optional<ClienteDTO> buscaClientePorId(String id) {
		var c = getOptionalCliente(id);
		if(c.isEmpty())
			return Optional.empty();
		return Optional.of(new ClienteDTO(c.get()));
	}
	
	public ClienteDTO salvaCliente(ClienteForm clienteForm) {
		Cliente c = this.clienteRepository.save(clienteForm.toCliente());
		return new ClienteDTO(c);
	}

	public List<ClienteDTO> listaCliente() {
		return clienteRepository
				.findAll()
				.stream()
				.map(ClienteDTO::new).collect(Collectors.toList());
	}

	public List<ClienteDTO> buscaCliente(ClienteForm clienteForm) {
		List<Cliente> listaCliente = new ArrayList<>();
		if (clienteForm.getNome().length() >= 3 && clienteForm.getDataNascimento() != null)
			listaCliente = clienteRepository.findByDataNascimentoAndNomeLikeIgnoreCase(clienteForm.getNome(), clienteForm.getDataNascimento());
		else if(clienteForm.getNome().length() >= 3)
			listaCliente = clienteRepository.findByNomeLikeIgnoreCase(clienteForm.getNome());
		else if(clienteForm.getDataNascimento() != null) 
			listaCliente = clienteRepository.findByDataNascimento(clienteForm.getDataNascimento());
		else throw new ParametroInvalidoException("Parametros informado para busca invalidos");
		
		return listaCliente
				.stream()
				.map(ClienteDTO::new)
				.collect(Collectors.toList());
	}
	
	
}
