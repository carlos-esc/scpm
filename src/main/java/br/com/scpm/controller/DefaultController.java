package br.com.scpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.scpm.model.entity.cpfAutorizado.CpfAutorizado;
import br.com.scpm.model.entity.usuario.Role;
import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.AutenticacaoService;
import br.com.scpm.service.CpfAutorizadoService;
import br.com.scpm.service.UsuarioService;

@Controller
public class DefaultController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private CpfAutorizadoService cpfAutorizadoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
    public String home1(Model model) {
		model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
		return "/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	return "/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	return "/login";
    }

    @GetMapping("/403")
    public String error403(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
        return "/error/403";
    }
    
    @GetMapping("/cpfAutorizado") //verifica se o cpf esta autorizado a se cadastrar
    public @ResponseBody String verificaCpf(@ModelAttribute CpfAutorizado cpfAutorizado, Model model) {
    	return cpfAutorizadoService.carregar(cpfAutorizado.getCpf()).toString();
    }
    
    @GetMapping("/{cpf}/formulario")
    public String cpfApto(Model model, @PathVariable String cpf) {
    	Usuario usuario = new Usuario();
    	usuario.getMorador().setCpf(cpf);
    	model.addAttribute("usuario", usuario);
    	return "/anonymous/formulario";
    }

    @PostMapping("/")
    public void usuario(@ModelAttribute Usuario usuario) {	
    	Role role = new Role();
    	role.setNomeRole("ROLE_CONTR");
    	usuario.getRoles().add(role);
    	usuarioService.salvar(usuario);
    }
    
}