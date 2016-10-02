/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springrest.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.springrest.model.EmpresaBean;

/**
 *
 * @author el matador
 */
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