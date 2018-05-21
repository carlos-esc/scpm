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
import br.com.scpm.model.entity.morador.Morador;
import br.com.scpm.service.AutenticacaoService;
import br.com.scpm.service.FaturaService;
import br.com.scpm.service.RoleService;
import br.com.scpm.service.UsuarioService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private FaturaService faturaService;

	@GetMapping("/usuarios") // Lista dos usuários
	public String adminContribuinte(Model model) {
		model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
		model.addAttribute("usuarios", usuarioService.listar());
		return "isAuthenticated/administrador/usuarios";
	}

	@GetMapping("/{login}") // carregar usuário
	public String carregarUsuario(Model model, @PathVariable String login) {
		model.addAttribute("roles", roleService.listar());
		model.addAttribute("usuario",login.equals("login")
						? usuarioService.carregar(SecurityContextHolder.getContext().getAuthentication().getName())
						: usuarioService.carregar(login));
		return "/isAuthenticated/administrador/formulario";
	}

	// FATURA===========================================================================================================
	@GetMapping("/{login}/fatura") // carregar informações nova fatura paga
	public String carregarUsuariofatura(Model model, @PathVariable String login) {
		model.addAttribute("usuario", usuarioService.carregar(login));
		model.addAttribute("fatura", new Fatura());
		return "isAuthenticated/administrador/fatura";
	}

	@PostMapping("/{usuarioId}/fatura") // grava informações fatura
	public @ResponseBody String fatura(@ModelAttribute Fatura fatura, @PathVariable String usuarioId) {
		fatura.setMorador(usuarioService.carregar(usuarioId).getMorador());
		faturaService.salvar(fatura);
		return "É nós!!! fatura...";
	}

	// ASSINATURA=========================================================================================================
	@GetMapping("/assinatura")
	public String adminAssinatura(Model model, Principal principal) {
		model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
		return "isAuthenticated/administrador/assinatura";
	}
}