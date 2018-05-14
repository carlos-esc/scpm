	package br.com.scpm.controller;

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

import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.RoleService;
import br.com.scpm.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
    
    @GetMapping("/checks") //verifica se o: cpf, email e login já existem
    public @ResponseBody String carregarChecks(@ModelAttribute Usuario usuario) {
    	return usuarioService.checksCpfEmailLoginIfExistin(usuario.getMorador().getCpf(), usuario.getMorador().getEmail(), usuario.getLogin()).toString(); 
    }
    
    @GetMapping("/{login}") //consultar usuário
    public String carregarUsuario(Model model, @PathVariable String login) {
    	model.addAttribute("roles", roleService.listar());
    	model.addAttribute("usuario", login.equals("login") ? usuarioService.carregar(SecurityContextHolder.getContext().getAuthentication().getName()) : usuarioService.carregar(login));
    	return "/usuario/formulario";
    }
    
    @GetMapping //carregar informações tela novo usuário
    public String administradorUsuarioFormulario(Model model) {
    	model.addAttribute("roles", roleService.listar());
    	model.addAttribute("usuario", new Usuario());
    	return "/usuario/formulario";
    }
    
    @PostMapping
    public @ResponseBody String usuario(@ModelAttribute Usuario usuario) {	
    	usuarioService.salvar(usuario);
    	return "É nós!!!";
    }
}
