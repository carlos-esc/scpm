package br.com.scpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.morador.MoradorRepository;

@Service
public class MoradorService {
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	public Boolean cpfExist(String cpf) {
		//return moradorRepository.findByCpf(cpf.replaceAll("[^0-9]", "")) == null ? false : true;
		return moradorRepository.findByCpf(cpf) == null ? false : true;
	}
	
	public Boolean emailExist(String email) {
		return moradorRepository.findByEmail(email) == null ? false : true;
	}
}
