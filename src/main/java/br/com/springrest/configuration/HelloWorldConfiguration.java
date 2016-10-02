package br.com.springrest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.springrest")
@PropertySource("/META-INF/springdb.properties")
@ImportResource("/META-INF/Spring-DataSource.xml")
public class HelloWorldConfiguration {
	

}