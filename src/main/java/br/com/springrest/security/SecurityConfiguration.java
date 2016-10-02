package br.com.springrest.security;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.springrest.query.QueryUsuario;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*

@Configuration
@EnableWebSecurity
@PropertySource("/META-INF/springdb.properties")
@ImportResource("/META-INF/Spring-DataSource.xml")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Inject
	Environment env;
        
	private static String REALM = "MY_TEST_REALM";
	private static final Logger logger = LogManager.getLogger(SecurityConfiguration.class);

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
		// auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("*****Start Service*****");
		http.csrf().disable().authorizeRequests()
				.antMatchers("/login**").permitAll()
                                .antMatchers("/client**").authenticated()
                                .antMatchers("/empresa**").authenticated()
                                .antMatchers("/planoExpediente**").authenticated()
                                .antMatchers("/plano**").authenticated()
                                .antMatchers("/usuario**").authenticated()
				.antMatchers("/perfil**").authenticated()
                                .antMatchers("/faq**").authenticated()
                                .antMatchers("/motivo**").authenticated()
                                .antMatchers("/respostaPronta**").authenticated()
                                .antMatchers("/mensagemAutomatica**").authenticated()
                                .antMatchers("/departamento**").authenticated()
				.antMatchers("/message**").authenticated()
				.antMatchers("/teste**").access("hasRole('ROLE_ADMIN')")
                                .and().httpBasic().realmName(REALM)
				.authenticationEntryPoint(getBasicAuthEntryPoint());

	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}


	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(QueryUsuario.queryUserAuthentication()).authoritiesByUsernameQuery(QueryUsuario.queryUserAndProfileAuthentication());

	}
        
        public static UserAuthentication getPrincipal(){
            
            UserAuthentication userAuthentication = new UserAuthentication();
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if ( authentication!= null ){
                userAuthentication.setNome(authentication.getName());
                userAuthentication.setPerfil(authentication.getAuthorities().toString());
                userAuthentication.setAutenticado(authentication.isAuthenticated());
                
                logger.info(" Usuario autenticado : {} " , userAuthentication.getNome()  ); 
                logger.info(" Authorities : {} " , userAuthentication.getPerfil()  ); 
                logger.info(" isAuthenticated : {} " , userAuthentication.isAutenticado()  ); 
            }
            
            return userAuthentication;
            
        }

}


*/