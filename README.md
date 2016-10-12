# Projeto utilizando Spring criando serviços Rest , JDBC NamedParameter e  Security Security com Basic Authentication.


Para criacao deste projeto foi criada uma classe **AbstractDAO<T>** no pacote **br.com.springTeste.abstractdao** que é uma classe 
Generica que deve ser estendida para cada DAO criado para acesso ao Banco de Dados.

No pacote **br.com.springTeste.dao** estão as classes que fazem acesso ao Banco de Dados como por exemplo a classe **EmpresaDAO**,
que estende **AbstractDAO** implementando as funcoes de acesso ao Banco de Dados para a **Tabela Empresa**. 

```javascript
public class EmpresaDAO extends AbstractDAO<EmpresaBean>
```

A classe **EmpresaDAO** passa no construtor o tipo da Classe Bean , o Mapper **EmpresaMapper** receber o recordset que ira preencher e retornar o Bean **EmpresaBean.java** e o **DataSource** que é a conexao com o Banco de Dados.   

```javascript
	public EmpresaDAO(DataSource dataSource) {
		super(EmpresaBean.class, new EmpresaMapper(), dataSource);
	}
```

No pacote **br.com.springTeste.mapper** serão encontrados os Mappers que receber o recordset da Query Select executada e 
preenche o Bean. 

```javascript
public class EmpresaMapper implements RowMapper<EmpresaBean> {
    public EmpresaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmpresaBean empresaBean = new EmpresaBean();
        empresaBean.setId(rs.getLong("id"));
        empresaBean.setNomeApp(rs.getString("nomeApp"));
        empresaBean.setNome(rs.getString("nome"));
        empresaBean.setCnpj(rs.getString("cnpj"));
        empresaBean.setIdioma(rs.getString("idioma"));
        empresaBean.setTimezone(rs.getString("timezone"));
        empresaBean.setClassificacao(rs.getString("classificacao"));
        empresaBean.setDeficiente(rs.getBoolean("isDeficiente"));
        empresaBean.setNumeroLicensas(rs.getInt("numeroLicensas"));
        empresaBean.setImagem(rs.getString("imagem"));
        empresaBean.setNomeAdministrador(rs.getString("nomeAdministrador"));
        empresaBean.setEmailAdministrador(rs.getString("emailAdministrador"));
        empresaBean.setPasswordAdministrador(rs.getString("passwordAdministrador"));
        empresaBean.setAtivo(rs.getBoolean("isAtivo"));
        empresaBean.setPlanoId(rs.getInt("planoId"));
        return empresaBean;
    }

}

```


No pacote **br.com.springTeste.model** estao os Modelos que representam os dados das tabelas e serão utilizados para armazenar os dados selecionados. 



No pacote **br.com.springTeste.query** estao as querys que serao executadas no banco de dados. Foram criadas funcoes static para que as querys sejam acessadas em qualquer classe. 




As Autenticacoes aos serviços REST serão realizadas pelo Spring Security e serão configuradas no pacote **br.com.springTeste.security**.
A classe **CustomBasicAuthenticationEntryPoint.java** configura uma resposta padrao no caso do usuario tentar uma autenticacao e nao for autorizado a acessar um recurso. 

```javascript
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, 
    		final HttpServletResponse response, 
    		final AuthenticationException authException) throws IOException, ServletException {
    	
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : " + authException.getMessage());
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("MY_TEST_REALM");
        super.afterPropertiesSet();
    }
}
```



A classe **SecurityConfiguration.java** configura as permissoes de acesso de acordo com o perfil do Usuario. Faz a autenticacao do usuario no Banco de Dados . E ainda possui uma funcao chamada **public static UserAuthentication getPrincipal()** que retorna o usuario autenticado no Sistema com o seu Perfil de Acesso. 




```javascript

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
				.antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/login**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/perfil**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/usuario**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/empresa**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/client**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/plano**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/message**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/teste**").access("hasRole('ROLE_ADMIN')").and().httpBasic().realmName(REALM)
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

	/*
	 * @Bean public DriverManagerDataSource dataSource() {
	 * 
	 * logger.debug("Banco de dados ; "+ env.getProperty("url")); logger.debug(
	 * "Usuario : "+ env.getProperty("user")); logger.debug("Senha : "+
	 * env.getProperty("password"));
	 * 
	 * DriverManagerDataSource driverManagerDataSource = new
	 * DriverManagerDataSource();
	 * driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	 * driverManagerDataSource.setUrl(env.getProperty("url"));
	 * driverManagerDataSource.setUsername(env.getProperty("user"));
	 * driverManagerDataSource.setPassword(env.getProperty("password")); return
	 * driverManagerDataSource; }
	 */

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(QueryUsuario.queryUserAuthentication()).authoritiesByUsernameQuery(QueryUsuario.queryUserAndProfileAuthentication());

	}
        
        /**
         * Retorna o Usuario autenticado no sistema
         * @return 
         */
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

```



O pacote **br.com.springTeste.configuration** faz a configuracao do **Spring MVC** por meio de anotacoes na classe **HelloWorldConfiguration.java**


```javascript
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.springTeste")
public class HelloWorldConfiguration {
	

}

```


No pacote **br.com.springTeste.controller** ficam os serviços REST que serão chamados via interface ou qualquer client REST que queira utilizar.  A classe **EmpresaRestController.java** representa um modelo de Serviço REST com seus serviços utilizando a biblioteca do Spring. 


Essa Classe possui os seguintes serviços : 

1 - Servico de Busca de empresas Cadastradas: ( Method GET ) 

URL :  http://localhost:8080/service-rest/empresa?limitInicial=0&limitFinal=20

2 - Servico de Upload do LOGO para a Empresa : ( METHOD POST enctype="multipart/form-data" ) 

URL :  http://localhost:8080/service-rest/empresa/12/upload

3 - Serviço de Criacao de Empresa : ( Method POST  content-type : application/json ) 

URL :  http://localhost:8080/service-rest/empresa

```json 
{
"id":null,
"nomeApp":"EmpresaApp",
"nome":"EmpresaNome",
"cnpj":"13256984",
"idioma":"Portugues",
"timezone":"pt/BR",
"classificacao":"Livre",
"numeroLicensas":10,
"imagem":"C:/Desktop/imagem.png",
"nomeAdministrador":"Leandro Prates",
"emailAdministrador":"lprates@springTeste.com.br",
"passwordAdministrador":"123456",
"planoId":0,
"deficiente":false,
"ativo":true
}

```` 

4 - Servico de Delete de Empresa : ( Method DELETE) 

URL : http://localhost:8080/service-rest/empresa/1 







