package br.com.scpm.springSecurity.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	public void onAuthenticationSuccess(HttpServletRequest request,   HttpServletResponse response, Authentication authentication) throws IOException  {
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if (roles.contains("ROLE_ADMIN")) {
		    
			response.sendRedirect("/");
			
		} else if (roles.contains("ROLE_SECRE")) {
			
			response.sendRedirect("/");
			
		} else if (roles.contains("ROLE_CONTR")) {
			
			response.sendRedirect("/");
			
		}
	}	
}
