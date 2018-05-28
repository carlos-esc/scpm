package br.com.scpm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.scpm.service.AutenticacaoService;

@Controller
@RequestMapping("/assinaturas")
public class AssinaturaController {

	//@Autowired
	//private AssinaturaService assinaturaService;
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@GetMapping("/")
	public String adminAssinatura(Model model, Principal principal) {
		model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
		return "isAuthenticated/administrador/assinatura";
	}
}
