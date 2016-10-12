# Projeto utilizando Spring criando serviços Rest , JDBC NamedParameter e  Security Security com Basic Authentication e Configuracao via WEB.xml 


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




As Autenticacoes aos serviços REST serão realizadas pelo Spring Security e serão configuradas pelo arquivo **WEB-INF/spring-security.xml**, 
onde serao definidos os perfis que podem fazer acesso aos serviços e as querys para autenticacao no banco de dados.


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security"
	xmlns:beans="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context-4.3.xsd        
	http://www.springframework.org/schema/security
	http://www.springframework.org/schema/security/spring-security-4.1.xsd">

        <!-- Regras de Acesso -->
        <http use-expressions="true">
            <csrf disabled="true"/>
            <intercept-url pattern="/empresa*" access="hasRole('ADMIN')" />
            <intercept-url pattern="/message**" access="isAuthenticated()" />
            <intercept-url pattern="/teste**" access="hasRole('ADMIN')" />
            <http-basic />
            <logout />
        </http>

        <!-- Busca Autorizacoes do Usuario -->
        <authentication-manager>
            <authentication-provider>
                <jdbc-user-service data-source-ref="dataSource"
                    users-by-username-query="
                            SELECT email,password, isAtivo FROM TB_Usuario WHERE email=? ; "

                    authorities-by-username-query="
                            SELECT TB_Usuario.email, TB_Perfil.nome  
                                  FROM TB_Usuario INNER JOIN TB_Perfil ON 
                                     TB_Usuario.perfilId = TB_Perfil.id 
                            WHERE TB_Usuario.email= ? ; "
                />
                
            </authentication-provider>
        </authentication-manager>
        
        
</beans:beans>

```



O arquivo **WEB-INF/spring-beans.xml** configura o Spring Core importando o arquivo **META-INF/Spring-DataSource.xml** 
que possui as configuraçoes do Banco de Dados. E varre os pacotes **br.com.springrest** para identificar Anotacoes
do Spring e inicializar os objectos. A anotacao **<mvc:annotation-driven />** faz com que os Serviços Rest sejam identificados 
e inicializados. 
O Bean **multipartResolver** é utilizados para realizar o Upload De Arquivos(png, jpeg, etc.. ) 


```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
                        http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
                        http://www.springframework.org/schema/mvc 
                        http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
                        http://www.springframework.org/schema/context 
                        http://www.springframework.org/schema/context/spring-context-4.3.xsd">
 
    
    <import resource="../META-INF/Spring-DataSource.xml" />
    
    <context:component-scan base-package="br.com.springrest" />
 
    <mvc:annotation-driven />
    
    <!-- Upload de Arquivo -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
    
 
</beans>

```


Arquivo **META-INF/Spring-DataSource.xml**  com acesso ao Banco de Dados descrido abaixo possui as configuracoes padrao 
de acesso e configura o Objecto de Transacao no Spring. 

```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                            http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
                            http://www.springframework.org/schema/context 
                            http://www.springframework.org/schema/context/spring-context-4.3.xsd
                            http://www.springframework.org/schema/tx
                            http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
    
    
    
        <!-- Enable Annotation based Declarative Transaction Management -->
	<tx:annotation-driven proxy-target-class="true" transaction-manager="transactionManager" />
    
        <!-- Creating TransactionManager Bean, since JDBC we are creating of type 
                DataSourceTransactionManager -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                <property name="dataSource" ref="dataSource" />
        </bean>    

        <!-- Initialization for data source -->
        <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
            <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/springrest"/>
            <property name="username" value="root"/>
            <property name="password" value="root"/>
        </bean>
    
    

</beans>
```





O pacote **br.com.springTeste.configuration** faz a configuracao do **Spring MVC** para carregar o arquivo de properties.


```javascript
@Configuration
@PropertySource("/META-INF/springdb.properties")
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







