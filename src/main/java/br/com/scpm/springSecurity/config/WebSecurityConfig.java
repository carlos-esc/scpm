package br.com.scpm.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Autowired
    private AuthProviderService authProvider;
    
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/home", "/cpfAutorizado", "/isAnonymous/**", "/usuarios/**").permitAll()
                .antMatchers("/administrador/**").hasAnyRole("ADMIN")
                .antMatchers("/secretario/**").hasAnyRole("ADMIN", "SECRE")
                .antMatchers("/contribuinte/**", "/isAuthenticated").hasAnyRole("ADMIN", "SECRE", "CONTR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/signin")
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }
	
	/*
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("contribuinte").password("123456").roles("CONTR")
        .and()
        .withUser("administrador").password("123456").roles("ADMIN");
         
    }
    */
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
    	auth
    	//.authenticationProvider(authProvider)
    	.userDetailsService(userDetailsService)
    	.passwordEncoder(new BCryptPasswordEncoder());
    	
    }
    
	
    @Override
	public void configure(WebSecurity web) throws Exception{
    	web.ignoring()
			.antMatchers("/webjars/**","/bootstrap-quick-search/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
