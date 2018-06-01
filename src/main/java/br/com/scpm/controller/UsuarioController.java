	package br.com.scpm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.AutenticacaoService;
import br.com.scpm.service.RoleService;
import br.com.scpm.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AutenticacaoService autenticacaoService;
    
	@GetMapping("/") // Listar todos
	public String listarTodos(Model model) {
		model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
		model.addAttribute("usuarios", usuarioService.listarTodos());
		return "/usuario/admin/listarTodos";
	}
	
	@GetMapping("/{login}") //carregar usuário
	public String carregarUsuario(Model model, @PathVariable String login, HttpServletRequest request) {

	  	model.addAttribute("usuario", usuarioService.carregar(login));
	   	model.addAttribute("roles", roleService.listar());
	    	
	   	return usuarioService.usuarioNivelAcesso(request);
	}
	
	@PostMapping("/") //salvar usuario
    public @ResponseBody String usuario(@ModelAttribute Usuario usuario, HttpServletRequest request) {	
		usuarioService.salvar(usuario,request);
    	return "Salvo com sucesso!!!!";
    }
	
	@GetMapping("/novo") //carregar informações tela novo usuário
    public String administradorUsuarioFormulario(Model model, HttpServletRequest request) {
    	model.addAttribute("roles", roleService.listar());
    	model.addAttribute("usuario", new Usuario());
    	
    	return usuarioService.usuarioNivelAcesso(request);
    }
	
	@GetMapping("/cpfEmailLoginExiste") //verifica se o: cpf, email e login já existem
    public @ResponseBody String cpfEmailLoginExiste(@ModelAttribute Usuario usuario) {
    	return usuarioService.cpfEmailLoginExiste(usuario.getMorador().getCpf(), usuario.getMorador().getEmail(), usuario.getLogin()).toString(); 
    }
}