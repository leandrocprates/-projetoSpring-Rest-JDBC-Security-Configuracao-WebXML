/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.error;

import br.com.springrest.model.ErrorResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author lprates
 */

/**
 * Classe Responsavel por Tratar os erros lancados dentro dos Servicos REST.
 * Retorna um objeto ErrorResponseBean para a interface.
 * 
 * @author lprates
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseBean> exceptionHandler2(ApplicationException ex) {
        ErrorResponseBean error = new ErrorResponseBean();
        error.setErrorCode(ex.getHttpStatus().value());
        error.setMessage(ex.getErrorMessage());
        return new ResponseEntity<ErrorResponseBean>(error, ex.getHttpStatus());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseBean> exceptionHandler(Exception ex) {
        ErrorResponseBean error = new ErrorResponseBean();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Contate o Administrador do Sistema");
        return new ResponseEntity<ErrorResponseBean>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
