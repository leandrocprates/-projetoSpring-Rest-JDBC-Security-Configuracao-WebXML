/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.security;

import lombok.Data;

/**
 *
 * @author lprates
 */
@Data
public class UserAuthentication {
    
    private String nome;
    private String perfil;
    private boolean isAutenticado;
    
}
