package br.com.jsa.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.api.form.PessoaForm;
import br.com.jsa.api.service.PessoaService;

@RestController
@RequestMapping("pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastraDadosPessoa(@RequestBody PessoaForm form) {
		var dto = this.pessoaService.cadastraDadosPessoa(form);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> listarPessoa() {
		var listaPessoa = this.pessoaService.listaPessoa();
		return ResponseEntity.ok(listaPessoa);
	}
	
	@GetMapping("/publico/busca-nome/{id}")
	public ResponseEntity<?> buscaNomePessoaPorId(@PathVariable("id") String id) {
		var dto = pessoaService.consultaPessoaPorId(id);
		return ResponseEntity.ok(dto);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPessoaPorId(@PathVariable("id") String id) {
		var dto = pessoaService.consultaPessoaPorId(id);
		return ResponseEntity.ok(dto);
		
	}
	
	@PostMapping("/consulta-lista")
	public ResponseEntity<?> consultaDadosListaPessoa(@RequestBody List<String> listaPessoa){
		var lista = pessoaService.consultaListaPessoa(listaPessoa);
		return ResponseEntity.ok(lista);
	}
}
