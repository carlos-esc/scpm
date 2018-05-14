package br.com.scpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.usuario.Usuario;

@Service
public class AutenticacaoService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public String isLogadoWhatName() {
		Usuario usuario = null;
		if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
		    usuario = usuarioService.carregar(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		return usuario == null ? "Anônimo" : usuario.getMorador().getNome();
	}
}
