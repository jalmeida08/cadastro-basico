package br.com.jsa.infra.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.infra.model.Pessoa;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String>{

	public List<Pessoa> findByNomeLikeIgnoreCase(String nome);
	public List<Pessoa> findByDataNascimento(LocalDate DataNascimento);
	public List<Pessoa> findByDataNascimentoAndNomeLikeIgnoreCase(String nome, LocalDate DataNascimento);
}
