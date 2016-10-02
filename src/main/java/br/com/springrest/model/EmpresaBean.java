/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author el matador
 */
@Data
@XmlRootElement
public class EmpresaBean {
    
    private long id ; 
    private String nomeApp;
    private String nome;
    private String cnpj;
    private String idioma;
    private String timezone;
    private String classificacao;
    private boolean isDeficiente ; 
    private int numeroLicensas ; 
    private String imagem;
    private String nomeAdministrador;
    private String emailAdministrador;
    private String passwordAdministrador;
    private boolean isAtivo ; 
    private int planoId ;
	
}

