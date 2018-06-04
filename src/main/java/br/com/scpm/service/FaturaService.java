package br.com.scpm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.fatura.Fatura;
import br.com.scpm.model.entity.fatura.FaturaRepository;

@Service
public class FaturaService {
	
	@Autowired
	private FaturaRepository faturaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	MoradorService moradorService;
	
	public void salvar(Fatura fatura) {
		faturaRepository.save(fatura);
	}
	
	public List<Fatura> listarTodas(String cpf, HttpServletRequest request){
		if (cpf.equals("todas")) {
			return usuarioService.carregar(SecurityContextHolder.getContext().getAuthentication().getName()).getMorador().getFaturas();
		} else {
			if(request.isUserInRole("ROLE_ADMIN")) {
				return moradorService.findByCpf(cpf).getFaturas();
			} else {
				return null;
			}
		}
	}
	
}
