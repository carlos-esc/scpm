package br.com.scpm.controller.privado;

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

import br.com.scpm.model.entity.fatura.Fatura;
import br.com.scpm.service.FaturaService;
import br.com.scpm.service.MoradorService;

@Controller
@RequestMapping("/faturas")
public class FaturaController {
	
	@Autowired
	private FaturaService faturaService;
	
	@Autowired
	private MoradorService moradorService;
	
	@GetMapping("/{cpf}/") //retorna o extrato das faturas do cpf informado
    public String listarTodas(Model model, @PathVariable String cpf, HttpServletRequest request) {  
		model.addAttribute("faturas", faturaService.listarTodas(cpf, request));
		return model.asMap().get("faturas") == null ? "/publico/error/403" : "/privado/fatura/listarTodas";
    }
	
	@GetMapping("/{cpf}/nova") // preencher nova fatura
	public String nova(Model model, @PathVariable String cpf) {
		model.addAttribute("morador", moradorService.findByCpf(cpf));
		model.addAttribute("fatura", new Fatura());
		return "/private/fatura/formulario";
	}

	@PostMapping("/{cpf}/") // salvar fatura
	public @ResponseBody String salvar(@ModelAttribute Fatura fatura, @PathVariable String cpf) {
		fatura.setMorador(moradorService.findByCpf(cpf));
		faturaService.salvar(fatura);
		return "É nós!!! fatura...";
	}
}
