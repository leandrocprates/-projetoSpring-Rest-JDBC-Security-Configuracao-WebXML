/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.model;

import lombok.Data;

/**
 * Classe Bean de Erros devolvidos para a Interface WEB
 * @author lprates
 */
@Data
public class ErrorResponseBean {
    
    private int errorCode;
    private String message;
    
}
