package co.edu.usbcali.vas.model.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.dataaccess.dao.ISystemParameterDAO;
import co.edu.usbcali.vas.exceptions.ZMessManager;
import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.model.dto.SystemParameterDTO;
import co.edu.usbcali.vas.utilities.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SystemParameterLogic")
public class SystemParameterLogic implements ISystemParameterLogic {
    private static final Logger log = LoggerFactory.getLogger(SystemParameterLogic.class);

    /**
     * DAO injected by Spring that manages SystemParameter entities
     *
     */
    @Autowired
    private ISystemParameterDAO systemParameterDAO;

    @Transactional(readOnly = true)
    public List<SystemParameter> getSystemParameter() throws Exception {
        log.debug("finding all SystemParameter instances");

        List<SystemParameter> list = new ArrayList<SystemParameter>();

        try {
            list = systemParameterDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SystemParameter failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SystemParameter");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSystemParameter(SystemParameter entity)
        throws Exception {
        log.debug("saving SystemParameter instance");

        try {


            if (entity.getCode() == null) {
                throw new ZMessManager().new EmptyFieldException("code");
            }

            if ((entity.getCode() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCode(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("code");
            }

            if (entity.getCreatedAt() == null) {
                throw new ZMessManager().new EmptyFieldException("createdAt");
            }

            if (entity.getCreatedBy() == null) {
                throw new ZMessManager().new EmptyFieldException("createdBy");
            }

            if ((entity.getCreatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCreatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "createdBy");
            }


            systemParameterDAO.save(entity);

            log.debug("save SystemParameter successful");
        } catch (Exception e) {
            log.error("save SystemParameter failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSystemParameter(SystemParameter entity)
        throws Exception {
        log.debug("deleting SystemParameter instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SystemParameter");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            systemParameterDAO.delete(entity);

            log.debug("delete SystemParameter successful");
        } catch (Exception e) {
            log.error("delete SystemParameter failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSystemParameter(SystemParameter entity)
        throws Exception {
        log.debug("updating SystemParameter instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "SystemParameter");
            }


            if (entity.getCode() == null) {
                throw new ZMessManager().new EmptyFieldException("code");
            }

            if ((entity.getCode() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCode(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("code");
            }

            if (entity.getCreatedAt() == null) {
                throw new ZMessManager().new EmptyFieldException("createdAt");
            }

            if (entity.getCreatedBy() == null) {
                throw new ZMessManager().new EmptyFieldException("createdBy");
            }

            if ((entity.getCreatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCreatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "createdBy");
            }

            if ((entity.getDoubleValue() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDoubleValue(), 131089, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "doubleValue");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("name");
            }

            if ((entity.getTextValue() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTextValue(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "textValue");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            systemParameterDAO.update(entity);

            log.debug("update SystemParameter successful");
        } catch (Exception e) {
            log.error("update SystemParameter failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SystemParameterDTO> getDataSystemParameter()
        throws Exception {
        try {
            List<SystemParameter> systemParameter = systemParameterDAO.findAll();

            List<SystemParameterDTO> systemParameterDTO = new ArrayList<SystemParameterDTO>();

            for (SystemParameter systemParameterTmp : systemParameter) {
                SystemParameterDTO systemParameterDTO2 = new SystemParameterDTO();

                systemParameterDTO2.setId(systemParameterTmp.getId());
                systemParameterDTO2.setBooleanValue((systemParameterTmp.getBooleanValue() != null)
                    ? systemParameterTmp.getBooleanValue() : null);
                systemParameterDTO2.setCode((systemParameterTmp.getCode() != null)
                    ? systemParameterTmp.getCode() : null);
                systemParameterDTO2.setCreatedAt(systemParameterTmp.getCreatedAt());
                systemParameterDTO2.setCreatedBy((systemParameterTmp.getCreatedBy() != null)
                    ? systemParameterTmp.getCreatedBy() : null);
                systemParameterDTO2.setDateValue(systemParameterTmp.getDateValue());
                systemParameterDTO2.setDoubleValue((systemParameterTmp.getDoubleValue() != null)
                    ? systemParameterTmp.getDoubleValue() : null);
                systemParameterDTO2.setIntValue((systemParameterTmp.getIntValue() != null)
                    ? systemParameterTmp.getIntValue() : null);
                systemParameterDTO2.setName((systemParameterTmp.getName() != null)
                    ? systemParameterTmp.getName() : null);
                systemParameterDTO2.setTextValue((systemParameterTmp.getTextValue() != null)
                    ? systemParameterTmp.getTextValue() : null);
                systemParameterDTO2.setTimeValue(systemParameterTmp.getTimeValue());
                systemParameterDTO2.setUpdatedAt(systemParameterTmp.getUpdatedAt());
                systemParameterDTO2.setUpdatedBy((systemParameterTmp.getUpdatedBy() != null)
                    ? systemParameterTmp.getUpdatedBy() : null);
                systemParameterDTO.add(systemParameterDTO2);
            }

            return systemParameterDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SystemParameter getSystemParameter(Integer id)
        throws Exception {
        log.debug("getting SystemParameter instance");

        SystemParameter entity = null;

        try {
            entity = systemParameterDAO.findById(id);
        } catch (Exception e) {
            log.error("get SystemParameter failed", e);
            throw new ZMessManager().new FindingException("SystemParameter");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SystemParameter> findPageSystemParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<SystemParameter> entity = null;

        try {
            entity = systemParameterDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "SystemParameter Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSystemParameter() throws Exception {
        Long entity = null;

        try {
            entity = systemParameterDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "SystemParameter Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<SystemParameter> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SystemParameter> list = new ArrayList<SystemParameter>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = systemParameterDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
	// OBTENER PARAMETROS PERSONALIZADOS DE LA DB / POR
	// CODIGO---------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public String getParamByCodeTexValue(String code) throws Exception {
		log.info("VAS SystemParameterLogic getParamByCodeTexValue");
		SystemParameter entity = null;
		try {
			entity = (SystemParameter) systemParameterDAO.getSystemParameterByCode(code.trim());
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		log.info("SystemParameterLogic getParamByCodeTexValue: "+entity.getTextValue());
		return entity.getTextValue().trim();
		
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getParamByCodeNumValue(String code) throws Exception {
		log.info("VAS SystemParameterLogic getParamByCodeNumValue");
		SystemParameter entity = null;
		try {
			entity = (SystemParameter) systemParameterDAO.getSystemParameterByCode(code.trim());
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		return entity.getIntValue();
	}
	
	@Override
	@Transactional(readOnly = true)
	public long getParamByCodeLongValue(String code) throws Exception {
		log.info("VAS SystemParameterLogic getParamByCodeLongValue");
		SystemParameter entity = null;
		try {
			entity = (SystemParameter) systemParameterDAO.getSystemParameterByCode(code.trim());
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		return (long)entity.getIntValue();
	}

	@Override
	@Transactional(readOnly = true)
	public Date getParamByCodeDateValue(String code) throws Exception {
		log.info("VAS SystemParameterLogic getParamByCodeDateValue");
		SystemParameter entity = null;
		try {
			entity = (SystemParameter) systemParameterDAO.getSystemParameterByCode(code.trim());
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		return entity.getDateValue();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Boolean getParamByCodeBooleanValue(String code) throws Exception {
		log.info("VAS SystemParameterLogic getParamByCodeBooleanValue");
		SystemParameter entity = null;
		try {
			entity = (SystemParameter) systemParameterDAO.getSystemParameterByCode(code.trim());
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		return entity.getBooleanValue();
	}
	
	@Override
	@Transactional(readOnly = true)
	public SystemParameter getSystemParamByCode(String code) throws Exception {
		log.info("VAS SystemParameterLogic getSystemParamByCode");
		SystemParameter entity = null;
		try {
			entity = (SystemParameter) systemParameterDAO.getSystemParameterByCode(code.trim());
		
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		return entity;
	}
	
	
	
}
