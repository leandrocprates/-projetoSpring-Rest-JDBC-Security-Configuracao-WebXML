/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.query;

/**
 *
 * @author el matador
 */
public class QueryEmpresa {
    
	public static String selectEmpresa(){
                StringBuilder query = new StringBuilder();
                query.append("SELECT ");
                query.append("	id, ");
                query.append("    nomeApp, ");
                query.append("    nome, ");
                query.append("    cnpj, ");
                query.append("    idioma, ");
                query.append("    timezone, ");
                query.append("    classificacao, ");
                query.append("    isDeficiente, ");
                query.append("    numeroLicensas, ");
                query.append("    imagem, ");
                query.append("    nomeAdministrador, ");
                query.append("    emailAdministrador, ");
                query.append("    passwordAdministrador, ");
                query.append("    isAtivo, ");
                query.append("    planoId ");
                query.append("FROM TB_Empresa   ");
                query.append(" limit :limitInicial , :limitFinal ");
		return query.toString();
	}

        
	public static String selectEmpresaById(){
                StringBuilder query = new StringBuilder();
                query.append("SELECT ");
                query.append("	id, ");
                query.append("    nomeApp, ");
                query.append("    nome, ");
                query.append("    cnpj, ");
                query.append("    idioma, ");
                query.append("    timezone, ");
                query.append("    classificacao, ");
                query.append("    isDeficiente, ");
                query.append("    numeroLicensas, ");
                query.append("    imagem, ");
                query.append("    nomeAdministrador, ");
                query.append("    emailAdministrador, ");
                query.append("    passwordAdministrador, ");
                query.append("    isAtivo, ");
                query.append("    planoId ");
                query.append("FROM TB_Empresa   ");
                query.append(" WHERE id = :id  ");    
		return query.toString();
	}
        
        
        public static String insertEmpresa(){
                StringBuilder query = new StringBuilder();
                query.append("INSERT INTO TB_Empresa ");
                query.append("(nomeApp,nome,cnpj,idioma,timezone,");
                query.append("classificacao,isDeficiente,numeroLicensas, ");
                query.append("imagem,nomeAdministrador,emailAdministrador,passwordAdministrador, ");
                query.append("isAtivo,planoId) ");
                query.append("VALUES ");
                query.append("( ");
                query.append(" :nomeApp, :nome,:cnpj,:idioma, ");
                query.append(":timezone,:classificacao,:isDeficiente,:numeroLicensas, ");
                query.append(":imagem,:nomeAdministrador,:emailAdministrador, ");
                query.append(":passwordAdministrador,:isAtivo, :planoId) ");                
                return query.toString();
        }
        
        
        public static String updateEmpresa(){
                StringBuilder query = new StringBuilder();
                query.append("UPDATE TB_Empresa ");
                query.append("SET ");
                query.append("nomeApp = :nomeApp, ");
                query.append("nome = :nome, ");
                query.append("cnpj = :cnpj, ");
                query.append("idioma = :idioma, ");
                query.append("timezone = :timezone, ");
                query.append("classificacao = :classificacao, ");
                query.append("isDeficiente = :isDeficiente, ");
                query.append("numeroLicensas = :numeroLicensas, ");
                query.append("imagem = :imagem, ");
                query.append("nomeAdministrador = :nomeAdministrador, ");
                query.append("emailAdministrador = :emailAdministrador, ");
                query.append("passwordAdministrador = :passwordAdministrador, ");
                query.append("isAtivo = :isAtivo, ");
                query.append("planoId = :planoId ");
                query.append("WHERE id =  :id "); 
                return query.toString();        
        }

        public static String updateLogoEmpresa(){
                StringBuilder query = new StringBuilder();
                query.append("UPDATE TB_Empresa ");
                query.append("SET ");
                query.append("imagem = :imagem ");
                query.append("WHERE id =  :id "); 
                return query.toString();        
        }
        
        public static String deleteEmpresaById(){
                StringBuilder query = new StringBuilder();
                query.append(" DELETE FROM TB_Empresa WHERE id = :id ");
                return query.toString();
        }
        
        public static String disableEmpresaById(){
                StringBuilder query = new StringBuilder();
                query.append(" UPDATE TB_Empresa SET isAtivo = 0  where id = :id ");
                return query.toString();
        }

        public static String enableEmpresaById(){
                StringBuilder query = new StringBuilder();
                query.append(" UPDATE TB_Empresa SET isAtivo = 1  where id = :id ");
                return query.toString();
        }
        
        public static String selectMaxId(){
                StringBuilder query = new StringBuilder();
                query.append(" SELECT * FROM TB_Empresa order by id desc limit 1  ");
                return query.toString();
        }
        
    
}
