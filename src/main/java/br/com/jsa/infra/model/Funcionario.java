package br.com.jsa.infra.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionario")
public class Funcionario extends Pessoa{

}
