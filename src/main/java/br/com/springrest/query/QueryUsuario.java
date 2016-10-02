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
public class QueryUsuario {
    
        private final static String SELECT_SQL = 
		"SELECT id, " + 
		"    nome, " + 
		"    email, " + 
		"    password, " + 
		"    qtdAtendimentosSimultaneos, " + 
		"    isAtivo, " + 
		"    perfilId, " + 
		"    empresaId " + 
		"FROM TB_Usuario "  ;        
        

	public static String selectUsuarioById() {
		StringBuffer query = new StringBuffer();
		query.append(SELECT_SQL);
                query.append(" WHERE  id = :id  ");
		return query.toString();
	}

	public static String selectUsuarioByEmailPassword() {
		StringBuffer query = new StringBuffer();
		query.append(SELECT_SQL);
                query.append(" WHERE ");
		query.append("	email = :email AND ");
		query.append("	password = :password ");
		return query.toString();
	}

	public static String selectUsuarioByEmail() {
		StringBuffer query = new StringBuffer();
		query.append(SELECT_SQL);
                query.append(" WHERE ");
		query.append("	email = :email  ");
		return query.toString();
	}
        
	public static String selectUsuarioByEmpresaId() {
		StringBuffer query = new StringBuffer();
                query.append(SELECT_SQL);
                query.append(" WHERE  empresaId = :empresaId  ");
		query.append(" limit :limitInicial , :limitFinal ");
		return query.toString();
	}

        
	public static String selectUsuarioAtivos() {
		StringBuffer query = new StringBuffer();
                query.append(SELECT_SQL);
                query.append(" WHERE  isAtivo = 1  ");
                query.append(" AND  empresaId = :empresaId  ");
		return query.toString();
	}
        
        
	public static String selectUsuarioInativos() {
		StringBuffer query = new StringBuffer();
                query.append(SELECT_SQL);
                query.append(" WHERE  isAtivo = 0  ");
                query.append(" AND  empresaId = :empresaId  ");
		return query.toString();
	}
        
        
	public static String insertUsuario() {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO TB_Usuario ");
		query.append("(nome, email, password, qtdAtendimentosSimultaneos, ");
		query.append(" isAtivo, empresaId, perfilId) ");
		query.append("VALUES ( :nome, :email, :password, :qtdAtendimentosSimultaneos, ");
		query.append(" :isAtivo, :empresaId, :perfilId )");
		return query.toString();
	}

	public static String updateUsuario() {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE TB_Usuario ");
		query.append("SET ");
		query.append("nome = :nome, ");
		query.append("email = :email, ");
		query.append("password = :password, ");
		query.append("qtdAtendimentosSimultaneos = :qtdAtendimentosSimultaneos, ");
		query.append("isAtivo = :isAtivo, ");
		query.append("empresaId = :empresaId, ");
		query.append("perfilId = :perfilId ");
		query.append("WHERE id = :id  ");
		return query.toString();
	}

	public static String disableUsuarioById() {
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE TB_Usuario SET isAtivo = 0 WHERE id = :id  ");
		return query.toString();
	}

	public static String enableUsuarioById() {
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE TB_Usuario SET isAtivo = 1 WHERE id = :id  ");
		return query.toString();
	}

        
	public static String queryUserAuthentication() {
		StringBuffer query = new StringBuffer();
		query.append("SELECT email,password, isAtivo FROM TB_Usuario WHERE email=?");
		return query.toString();
	}
	
	public static String queryUserAndProfileAuthentication() {
		StringBuffer query = new StringBuffer();
		query.append("SELECT TB_Usuario.email, ");
		query.append("		 TB_Perfil.nome ");
		query.append("  FROM TB_Usuario INNER JOIN TB_Perfil ON ");
		query.append("		 TB_Usuario.perfilId = TB_Perfil.id ");
		query.append(" WHERE TB_Usuario.email= ? ");
		return query.toString();
	}
}
