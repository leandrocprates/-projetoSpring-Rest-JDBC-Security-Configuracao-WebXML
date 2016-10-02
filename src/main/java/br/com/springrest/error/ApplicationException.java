/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Classe Geral de tratamento de Erro para Servicos REST
 * @author lprates
 */
@Data
public class ApplicationException extends Exception {
    
    private String errorMessage;
    private HttpStatus httpStatus;
    

    public String getErrorMessage() {
        return errorMessage;
    }

    public ApplicationException(String errorMessage,HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
    
    public ApplicationException() {
        super();
    }
    
    
}
