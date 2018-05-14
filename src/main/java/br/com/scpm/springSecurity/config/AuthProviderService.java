package br.com.scpm.springSecurity.config;
	
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.scpm.model.entity.usuario.Usuario;
import br.com.scpm.service.UsuarioService;

@Component
public class AuthProviderService implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    	
    	String login = auth.getName();
        String senha = auth.getCredentials().toString();
        
       
        /*
        if (usuario != null) {
        	
        	if (usuarioAtivo(usuario)) {
                Collection<? extends GrantedAuthority> authorities = usuario.getRoles();
                return new UsernamePasswordAuthenticationToken(login, senha, authorities);
            } else {
                throw new BadCredentialsException("Este usuário está desativado.");
            }
        }
		*/
        
        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean usuarioAtivo(Usuario usuario) {
        if (usuario != null) {
            if (usuario.isEnabled() == true) {
                return true;
            }
        }
        return false;
    }
}