package br.com.scpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.morador.Morador;
import br.com.scpm.model.entity.morador.MoradorRepository;

@Service
public class MoradorService {
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	public Morador findByCpf(String cpf) {
		return moradorRepository.findByCpf(cpf);
	}
	
	public Morador findByEmail(String email) {
		return moradorRepository.findByEmail(email) ;
	}
}
