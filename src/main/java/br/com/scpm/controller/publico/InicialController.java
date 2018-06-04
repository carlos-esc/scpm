package br.com.scpm.controller.publico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.scpm.service.AutenticacaoService;

@Controller
public class InicialController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@GetMapping("/")
    public String home1(Model model) {
		model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
		return "/publico/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	return "/publico/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
    	return "/publico/login";
    }

    @GetMapping("/403")
    public String error403(Model model) {
    	model.addAttribute("nome", autenticacaoService.isLogadoWhatName());
        return "/publico/error/403";
    }
}