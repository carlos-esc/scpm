package br.com.scpm.controller.publico;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.scpm.model.entity.cpfAutorizado.CpfAutorizado;
import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.CpfAutorizadoService;
import br.com.scpm.service.UsuarioService;

@Controller
@RequestMapping("/anonimo")
public class AnonimoController {
	
	@Autowired
	private CpfAutorizadoService cpfAutorizadoService;
	
	@Autowired
	private UsuarioService usuarioService;
    
    @GetMapping("/cpfAutorizado") //verifica se o cpf esta autorizado a se cadastrar
    public @ResponseBody String cpfAutorizado(@ModelAttribute CpfAutorizado cpfAutorizado) {
    	return cpfAutorizadoService.carregar(cpfAutorizado.getCpf()).toString();
    }
    
    @GetMapping("/cadastrar") //html verificacao de cpf para dar inicio ao auto cadastro
    public String cpfVerificacao(@ModelAttribute CpfAutorizado cpfAutorizado, Model model) {
    	return "/publico/anonimo/cadastrar";
    }
    
    @GetMapping("/autoCadastroConcluido") 
    public String autoCadastroConcluido() {
    	return "/publico/anonimo/autoCadastroConcluido";
    }
    
    @GetMapping("/{cpf}/formulario")
    public String cpfApto(Model model, @PathVariable String cpf) {
    	Usuario usuario = new Usuario();
    	usuario.getMorador().setCpf(cpf);
    	model.addAttribute("usuario", usuario);
    	return "/publico/anonimo/formulario";
    }

    @PostMapping("/novo")
    public String usuario(@ModelAttribute Usuario usuario, HttpServletRequest request) {	
    	
    	String _usuario = usuario.getLogin();
    	String _senha = usuario.getSenha();
    	usuarioService.salvar(usuario, request).getSenha();
    	
   		try {
   			request.login(_usuario, _senha);
   		} catch (ServletException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}    	
   		return "Sucesso";
    }
    
}