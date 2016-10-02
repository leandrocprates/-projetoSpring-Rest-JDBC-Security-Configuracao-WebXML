/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.abstractdao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author el matador
 */
public abstract class AbstractDAO<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(AbstractDAO.class);

    @SuppressWarnings("unused")
	private Class<T> entityClass;
    private RowMapper<T> rowMapClass;
    
    DataSource dataSource;
    
    JdbcTemplate jdbcTemplateObject;
    
    NamedParameterJdbcTemplate namedParameterjdbcTemplateObject; 

    protected AbstractDAO(Class<T> entityClass, RowMapper<T> rowMapClass, DataSource dataSource) {
        this.entityClass = entityClass;
        this.rowMapClass = rowMapClass; 
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.namedParameterjdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }
    
    @SuppressWarnings("hiding")
	public <T> void saveModel(T model) {
        logger.debug("Insert dados ");
    }

    public List<Map<String, Object >> selectModel(String SQL, SqlParameterSource namedParameters) {
        logger.debug("SQL: [{}] ", SQL);
        List<Map<String , Object >> rows = namedParameterjdbcTemplateObject.queryForList(SQL, namedParameters);
        return rows ; 
    }

    public List<T> selectModel2(String SQL, SqlParameterSource namedParameters) {
        logger.debug("SQL: [{}] ", SQL);
        List<T> rows = namedParameterjdbcTemplateObject.query(SQL, namedParameters, rowMapClass);
        return rows;
    }
    
    public T selectForObject(String SQL, SqlParameterSource namedParameters) {
        logger.debug("SQL: [{}] ", SQL);
        try{
            T object = namedParameterjdbcTemplateObject.queryForObject(SQL, namedParameters, rowMapClass);
            return object;
        }catch(EmptyResultDataAccessException ex){
            //tratamento caso nao retorne nenhum valor 
            return null;
        }
        
    }
    
    public Long updateForObject(String SQL, SqlParameterSource namedParameters) {
        logger.debug("SQL: [{}] ", SQL);
        KeyHolder holder = new GeneratedKeyHolder();
        int numRowsAffected = namedParameterjdbcTemplateObject.update(SQL, namedParameters, holder );
        Long newId = 0L; 
        
        if ( holder.getKeyList().size() > 0 ) {
            newId = holder.getKey().longValue();
        }
        logger.debug("Numeros de Linhas afetadas : [{}] ", numRowsAffected);
        logger.debug("Novo Id Gerado  : [{}] ", newId );
        
        return newId;
    }

    
}
