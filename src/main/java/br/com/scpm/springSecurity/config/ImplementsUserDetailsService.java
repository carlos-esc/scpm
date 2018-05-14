package br.com.scpm.springSecurity.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.UsuarioService;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
		
		System.out.println("===================================================== AQUI");
		Usuario usuario = usuarioService.carregar(login);
		System.out.println("===================================================== AQUI");
		if (usuario  != null) {
			System.out.println("===================================================== AQUI IF " + usuario.getLogin());
			return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, usuario.isEnabled(), usuario.getAuthorities());
		} else {
			System.out.println("===================================================== AQUI ELSE");
			throw new UsernameNotFoundException("usuário não localizado!");
		}
		
		
		
	}
}