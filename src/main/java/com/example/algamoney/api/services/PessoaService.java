package com.example.algamoney.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) { 
		Pessoa salva = encontrarPessoa(codigo);
		BeanUtils.copyProperties(pessoa, salva, "codigo");
		return pessoaRepository.save(salva);	 
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa salva = encontrarPessoa(codigo);
		salva.setAtivo(ativo);
		pessoaRepository.save(salva);		
	}
		
	public Pessoa encontrarPessoa(Long codigo) {
		Pessoa salva = pessoaRepository.findById(codigo).orElse(null);
		
		if (salva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return salva;
	}

}
