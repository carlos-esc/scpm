package br.com.scpm.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.scpm.model.entity.morador.Contato;
import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.model.entity.usuario.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MoradorService moradorService;
	
	public Usuario salvar(Usuario usuario) {
	
		for (int i = 0 ; i < usuario.getMorador().getContatos().size() ; i++) {
    		Contato contato = usuario.getMorador().getContatos().get(i);
    		if (contato.getNumero().equalsIgnoreCase("")) {
    			usuario.getMorador().getContatos().remove(contato);
    		} else {
    			usuario.getMorador().getContatos().get(i).setMorador(usuario.getMorador());
    		}
    	}
    	
		if (usuario.getFaturas() == null) usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		Usuario usuarioDataCriacao = usuarioRepository.findById(usuario.getId());
		if (usuarioDataCriacao != null) {
			if (usuarioDataCriacao.getDataCriacao() != null) {
				usuario.setDataCriacao(usuarioDataCriacao.getDataCriacao());
			}
		}
		if (usuario.getRoles() == null) {
			usuario.setRoles(usuarioRepository.findById(usuario.getId()).getRoles());
			usuario.setSituacao(usuarioRepository.findById(usuario.getId()).isEnabled());
		}
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario carregar(String login) {
		Usuario usuario = usuarioRepository.findByLogin(login);
		return usuario;
	}
	
	
	
	public JSONObject checksCpfEmailLoginIfExistin(String cpf, String email, String login){
		
		//try {
			JSONObject checksDados = new JSONObject();
			
			checksDados.put("cpf", moradorService.cpfExist(cpf));
			checksDados.put("email", moradorService.emailExist(email));
			checksDados.put("login", usuarioRepository.findByLogin(login) == null ? false : true);
			
			System.out.println("checksDados = " + checksDados);
			
		//} catch (JSONException e) {
			//e.printStackTrace();
		//}
		
		return checksDados;
	}
	
	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
}
