package br.com.jsa.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsa.api.dto.DadoNomePessoaDTO;
import br.com.jsa.api.dto.PessoaDTO;
import br.com.jsa.api.form.PessoaForm;
import br.com.jsa.infra.exception.ParametroInvalidoException;
import br.com.jsa.infra.model.Pessoa;
import br.com.jsa.infra.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa getPessoa(String id) {
		return pessoaRepository
		.findById(id)
		.orElseThrow(() -> new ParametroInvalidoException("O parâmetro informado não foi localizado na base de dados"));
	}
	
	public Optional<PessoaDTO> getOptionalPessoa(String id) {
		var pessoa = pessoaRepository.findById(id);
		if(pessoa.isEmpty())
			return Optional.empty();
		return Optional.of(new PessoaDTO(pessoa.get()));
	}
	
	public PessoaDTO cadastraDadosPessoa(PessoaForm form) {
		var p = this.pessoaRepository.save(form.toPessoa());
		return new PessoaDTO(p);
	}
	
	
	public List<PessoaDTO> listaPessoa(){
		return this.pessoaRepository
				.findAll()
				.stream()
				.map(PessoaDTO::new)
				.collect(Collectors.toList());
	}
	
	public List<PessoaDTO> consultaListaPessoa(List<String> listaPessoa) {
		var listaDadosPessoa = new ArrayList<PessoaDTO>();
		listaPessoa
			.stream()
			.forEach(p -> {
				listaDadosPessoa.add(new PessoaDTO(getPessoa(p)));
			});
		return listaDadosPessoa;
	}
	
	public Optional<DadoNomePessoaDTO> consultaPessoaPorId(String id) {
		var pessoa = getOptionalPessoa(id);
		if(pessoa.isPresent())
			return Optional.of(new DadoNomePessoaDTO(pessoa.get().getNome()));
		return Optional.empty();
	}
	
	public void removePessoa(String id){
		var f = this.getPessoa(id);
		this.pessoaRepository.delete(f);
	}
	
	public List<PessoaDTO> buscaPessoa(PessoaForm pessoaForm) {
		List<Pessoa> listaCliente = new ArrayList<>();
		if (pessoaForm.getNome().length() >= 3 && pessoaForm.getDataNascimento() != null)
			listaCliente = pessoaRepository.findByDataNascimentoAndNomeLikeIgnoreCase(pessoaForm.getNome(), pessoaForm.getDataNascimento());
		else if(pessoaForm.getNome().length() >= 3)
			listaCliente = pessoaRepository.findByNomeLikeIgnoreCase(pessoaForm.getNome());
		else if(pessoaForm.getDataNascimento() != null) 
			listaCliente = pessoaRepository.findByDataNascimento(pessoaForm.getDataNascimento());
		else throw new ParametroInvalidoException("Parametros informado para busca invalidos");
		
		return listaCliente
				.stream()
				.map(PessoaDTO::new)
				.collect(Collectors.toList());
	}
	

}
