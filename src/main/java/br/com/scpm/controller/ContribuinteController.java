package br.com.scpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.scpm.service.AutenticacaoService;
import br.com.scpm.service.UsuarioService;

@Controller
@RequestMapping("/contribuinte")
public class ContribuinteController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{login}/extrato") //retorna o extrato das faturas pagas
    public String contrExtrato(Model model, @PathVariable String login) {  
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	model.addAttribute("faturas", login.equals("login")
    			? usuarioService.carregar(SecurityContextHolder.getContext().getAuthentication().getName()).getMorador().getFaturas() 
    			: usuarioService.carregar(login).getMorador().getFaturas());
    	return "isAuthenticated/contribuinte/extrato";
    }
}
