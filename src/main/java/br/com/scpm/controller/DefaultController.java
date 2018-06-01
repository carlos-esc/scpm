package br.com.scpm.controller;

import javax.servlet.http.HttpServletRequest;

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
    public @ResponseBody String cpfAutorizado(@ModelAttribute CpfAutorizado cpfAutorizado) {
    	return cpfAutorizadoService.carregar(cpfAutorizado.getCpf()).toString();
    }
    
    @GetMapping("/cadastrar") //html verificacao de cpf para dar inicio ao auto cadastro
    public String cpfVerificacao(@ModelAttribute CpfAutorizado cpfAutorizado, Model model) {
    	return "/cadastrar";
    }
    
    @GetMapping("/autoCadastroConcluido") 
    public String autoCadastroConcluido() {
    	return "/anonimo/autoCadastroConcluido";
    }
    
    @GetMapping("/{cpf}/formulario")
    public String cpfApto(Model model, @PathVariable String cpf) {
    	Usuario usuario = new Usuario();
    	usuario.getMorador().setCpf(cpf);
    	model.addAttribute("usuario", usuario);
    	return "/anonimo/formulario";
    }

    @PostMapping("/novo")
    public @ResponseBody String usuario(@ModelAttribute Usuario usuario,HttpServletRequest request) {	
    	usuarioService.salvar(usuario, request);
    	return "Anônimo gravado com sucesso!!!";
    }
    
}