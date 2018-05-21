	package br.com.scpm.controller;

import javax.servlet.http.HttpServletRequest;

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

import br.com.scpm.model.entity.usuario.Role;
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
    
    @GetMapping("/{login}") //carregar usuário
    public String carregarUsuario(Model model, @PathVariable String login, HttpServletRequest request) {

    	Usuario usuario = new Usuario();
    	if (login.equals("login")) {
    		usuario = usuarioService.carregar(SecurityContextHolder.getContext().getAuthentication().getName());
    	} else {
    		usuario = usuarioService.carregar(login);
    	}
    	model.addAttribute("usuario", usuario);
    	model.addAttribute("roles", roleService.listar());
    	
    	if (request.isUserInRole("ROLE_ADMIN")) {
    		return "/isAuthenticated/administrador/formulario";
    	} else if (request.isUserInRole("ROLE_SECRE")){
    		return "/isAuthenticated/secretario/formulario";
    	} else if (request.isUserInRole("ROLE_CONTR")) {
    		return "/isAuthenticated/contribuinte/formulario";
    	} else {
    		return null;
    	}
    }
    
    @GetMapping //carregar informações tela novo usuário
    public String administradorUsuarioFormulario(Model model, HttpServletRequest request) {
    	model.addAttribute("roles", roleService.listar());
    	model.addAttribute("usuario", new Usuario());
    	if (request.isUserInRole("ROLE_ADMIN")) {
    		return "/isAuthenticated/administrador/formulario";
    	} else if (request.isUserInRole("ROLE_SECRE")){
    		return "/isAuthenticated/secretario/formulario";
    	} else if (request.isUserInRole("ROLE_CONTR")) {
    		return "/isAuthenticated/contribuinte/formulario";
    	} else {
    		return null;
    	}
    }
    
    @PostMapping
    public @ResponseBody String usuario(@ModelAttribute Usuario usuario) {	
    	Role role = new Role();
    	role.setNomeRole("ROLE_CONTR");
    	usuario.setSituacao(true);
    	usuario.getRoles().add(role);
    	usuarioService.salvar(usuario);
    	return "É nós!!!";
    }
}
