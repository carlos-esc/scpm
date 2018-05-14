package br.com.scpm.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.cpfAutorizado.CpfAutorizadoRepository;

@Service
public class CpfAutorizadoService {

	@Autowired
	private CpfAutorizadoRepository cpfAutorizadoRepository;
	
	@Autowired
	private MoradorService moradorService;
	
	public JSONObject carregar(String cpf) {
		
		JSONObject checkCpf = new JSONObject();
		
		checkCpf.put("Autorizado", cpfAutorizadoRepository.findByCpf(cpf) != null ? true : false);
		checkCpf.put("Cadastrado", moradorService.cpfExist(cpf));
		
		return checkCpf;
	}
}
