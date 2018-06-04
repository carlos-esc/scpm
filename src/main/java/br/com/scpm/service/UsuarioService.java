package br.com.scpm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.morador.Contato;
import br.com.scpm.model.entity.usuario.Role;
import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.model.entity.usuario.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MoradorService moradorService;
	
	public Usuario carregar(String login) {
		
    	if (login.equals("usuarioLogado")) {
    		return usuarioRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    	} else {
    		return usuarioRepository.findByLogin(login);
    	}
	}
	
	public List<Usuario> listarTodos(HttpServletRequest request){
		
		if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SECRE")) {
			return (List<Usuario>) usuarioRepository.findAll();
    	}  else {
    		return null;
    	}
	}
	
	public String usuarioNivelAcesso (HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
    		return "/privado/usuario/admin/formulario";
    	} else if (request.isUserInRole("ROLE_SECRE")){
    		return "/privado/usuario/secre/formulario";
    	} else if (request.isUserInRole("ROLE_CONTR")) {
    		return "/privado/usuario/contr/formulario";
    	} else {
    		return null;
    	}
	}
	
	public Usuario salvar(Usuario usuario, HttpServletRequest request) {
	
		for (int i = 0 ; i < usuario.getMorador().getContatos().size() ; i++) {
    		Contato contato = usuario.getMorador().getContatos().get(i);
    		if (contato.getNumero().equalsIgnoreCase("")) {
    			usuario.getMorador().getContatos().remove(contato);
    		} else {
    			usuario.getMorador().getContatos().get(i).setMorador(usuario.getMorador());
    		}
    	}
    	
		if (usuario.getSenha() == null) {
			usuario.setSenha(usuarioRepository.findByLogin(usuario.getLogin()).getSenha());
		} else {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		}
		
		
		if (usuario.getId() != 0) {
			usuario.setDataCriacao(usuarioRepository.findByLogin(usuario.getLogin()).getDataCriacao());
		}
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			
    	} else if (request.isUserInRole("ROLE_SECRE")){
    		
    	} else if (request.isUserInRole("ROLE_CONTR")) {
    		Role role = new Role();
        	role.setNomeRole("ROLE_CONTR");
        	usuario.getRoles().add(role);
        	usuario.setSituacao(true);
    	} else {
    		Role role = new Role();
        	role.setNomeRole("ROLE_CONTR");
        	usuario.getRoles().add(role);
        	usuario.setSituacao(true);
    	}
		return usuarioRepository.save(usuario);
	}

	public JSONObject cpfEmailLoginExiste(String cpf, String email, String login){
		
		JSONObject checksDados = new JSONObject();
			
		checksDados.put("cpf", moradorService.findByCpf(cpf) == null ? false : true);
		checksDados.put("email", moradorService.findByEmail(email) == null ? false : true);
		checksDados.put("login", usuarioRepository.findByLogin(login) == null ? false : true);
			
		return checksDados;
	}
}
