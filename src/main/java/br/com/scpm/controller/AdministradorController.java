package br.com.scpm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.scpm.model.entity.fatura.Fatura;
import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.AutenticacaoService;
import br.com.scpm.service.FaturaService;
import br.com.scpm.service.RoleService;
import br.com.scpm.service.UsuarioService;

@Controller
@RequestMapping("/usuario/administrador")
public class AdministradorController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private FaturaService faturaService;
	
	@GetMapping("/usuarios") //Lista dos usuários
    public String adminContribuinte(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	model.addAttribute("usuarios", usuarioService.listar());
    	return "/usuario/administrador/usuarios";
    }
//FATURA===========================================================================================================
    @GetMapping("/usuario/{login}/fatura") //carregar informações nova fatura paga
    public String carregarUsuariofatura(Model model, @PathVariable String login) {
    	model.addAttribute("usuario", usuarioService.carregar(login));
    	model.addAttribute("fatura", new Fatura());
    	return "/usuario/administrador/fatura";
    }
    
    @PostMapping("/usuario/{login}/fatura") //grava informações fatura
    public @ResponseBody String fatura(@ModelAttribute Fatura fatura, @PathVariable String login) {
    	Usuario usuario = usuarioService.carregar(login);
    	fatura.setUsuario(usuario);
    	faturaService.salvar(fatura);
    	return "É nós!!! fatura...";
    }
//ASSINATURA=========================================================================================================
    @GetMapping("/assinatura")
    public String adminAssinatura(Model model, Principal principal) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	return "/usuario/administrador/assinatura";
    }
}