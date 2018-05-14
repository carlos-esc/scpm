package br.com.scpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.fatura.Fatura;
import br.com.scpm.model.entity.fatura.FaturaRepository;

@Service
public class FaturaService {
	
	@Autowired
	private FaturaRepository faturaRepository;
	
	public void salvar(Fatura fatura) {
		faturaRepository.save(fatura);
	}
}
