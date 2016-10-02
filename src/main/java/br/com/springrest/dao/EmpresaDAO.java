package br.com.springrest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import br.com.springrest.abstractdao.AbstractDAO;
import br.com.springrest.mapper.EmpresaMapper;
import br.com.springrest.model.EmpresaBean;
import br.com.springrest.query.QueryEmpresa;

public class EmpresaDAO extends AbstractDAO<EmpresaBean>{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(EmpresaDAO.class);

	public EmpresaDAO(DataSource dataSource) {
		super(EmpresaBean.class, new EmpresaMapper(), dataSource);
	}

	public List<EmpresaBean> getEmpresa(int limitInicial, int limitFinal) {

		List<EmpresaBean> listaEmpresas = new ArrayList<EmpresaBean>();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("limitInicial", limitInicial);
		namedParameters.addValue("limitFinal", limitFinal);

		listaEmpresas = selectModel2(QueryEmpresa.selectEmpresa() , namedParameters);

		return listaEmpresas;
	}

	public EmpresaBean getEmpresaById(long id) {
		logger.debug("EmpresaBean getEmpresaById(long id) ");
		EmpresaBean empresaBean = null;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		empresaBean = selectForObject(QueryEmpresa.selectEmpresaById() , namedParameters);
		return empresaBean;
	}

	public Long insertEmpresa(EmpresaBean empresaBean) {
		logger.debug("insertEmpresa(EmpresaBean empresaBean) ");
		Long newId = updateForObject(QueryEmpresa.insertEmpresa() , mapInsertEmpresa(empresaBean));
		return newId;
	}
        
	public void updateEmpresa(EmpresaBean empresaBean) {
		logger.debug("updateEmpresa(EmpresaBean empresaBean) ");
		updateForObject(QueryEmpresa.updateEmpresa() , mapInsertEmpresa(empresaBean));
		return;
	}
        
	public void updateLogoEmpresa(EmpresaBean empresaBean) {
		logger.debug("updateLogoEmpresa(EmpresaBean empresaBean) ");
		updateForObject(QueryEmpresa.updateLogoEmpresa() , mapInsertEmpresa(empresaBean));
		return;
	}

	public void deleteEmpresaById(long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		updateForObject(QueryEmpresa.deleteEmpresaById() , namedParameters);
		return;
	}

	public void disableEmpresaById(long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		updateForObject(QueryEmpresa.disableEmpresaById() , namedParameters);
		return;
	}
        
	public void enableEmpresaById(long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		updateForObject(QueryEmpresa.enableEmpresaById() , namedParameters);
		return;
	}
        
        
        public EmpresaBean getMaxEmpresaId(){
                EmpresaBean empresaBean = null;
                MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		empresaBean = selectForObject(QueryEmpresa.selectMaxId() , namedParameters);
		return empresaBean;
        }
        
	private MapSqlParameterSource mapInsertEmpresa(EmpresaBean empresaBean) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
                namedParameters.addValue("id", empresaBean.getId() == 0 ? null: empresaBean.getId() ) ;
                namedParameters.addValue("nomeApp", empresaBean.getNomeApp() ) ; 
                namedParameters.addValue("nome" , empresaBean.getNome() ) ; 
                namedParameters.addValue("cnpj", empresaBean.getCnpj() ) ; 
                namedParameters.addValue("idioma", empresaBean.getIdioma() ) ; 
                namedParameters.addValue("timezone", empresaBean.getTimezone() ) ; 
                namedParameters.addValue("classificacao", empresaBean.getClassificacao() )  ; 
                namedParameters.addValue("isDeficiente", empresaBean.isDeficiente() ) ; 
                namedParameters.addValue("numeroLicensas" , empresaBean.getNumeroLicensas() ) ; 
                namedParameters.addValue("imagem", empresaBean.getImagem() ) ; 
                namedParameters.addValue("nomeAdministrador", empresaBean.getNomeAdministrador() ) ; 
                namedParameters.addValue("emailAdministrador", empresaBean.getEmailAdministrador() ) ; 
                namedParameters.addValue("passwordAdministrador", empresaBean.getPasswordAdministrador() ) ; 
                namedParameters.addValue("isAtivo", empresaBean.isAtivo() ) ; 
                namedParameters.addValue("planoId", empresaBean.getPlanoId()==0 ? null: empresaBean.getPlanoId() ) ; 
		return namedParameters;
	}

}
