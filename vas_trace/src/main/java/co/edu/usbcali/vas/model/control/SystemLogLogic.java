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

import co.edu.usbcali.vas.dataaccess.dao.ISystemLogDAO;
import co.edu.usbcali.vas.exceptions.ZMessManager;
import co.edu.usbcali.vas.model.SystemLog;
import co.edu.usbcali.vas.model.dto.SystemLogDTO;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SystemLogLogic")
public class SystemLogLogic implements ISystemLogLogic {
    private static final Logger log = LoggerFactory.getLogger(SystemLogLogic.class);

    /**
     * DAO injected by Spring that manages SystemLog entities
     *
     */
    @Autowired
    private ISystemLogDAO systemLogDAO;

    @Transactional(readOnly = true)
    public List<SystemLog> getSystemLog() throws Exception {
        log.debug("finding all SystemLog instances");

        List<SystemLog> list = new ArrayList<SystemLog>();

        try {
            list = systemLogDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SystemLog failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SystemLog");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSystemLog(SystemLog entity) throws Exception {
        log.debug("saving SystemLog instance");

        try {
            if (entity.getActionDate() == null) {
                throw new ZMessManager().new EmptyFieldException("actionDate");
            }

            if (entity.getActionName() == null) {
                throw new ZMessManager().new EmptyFieldException("actionName");
            }

            if ((entity.getActionName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getActionName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "actionName");
            }

            if ((entity.getNote() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNote(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("note");
            }

            systemLogDAO.save(entity);

            log.debug("save SystemLog successful");
        } catch (Exception e) {
            log.error("save SystemLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSystemLog(SystemLog entity) throws Exception {
        log.debug("deleting SystemLog instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SystemLog");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            systemLogDAO.delete(entity);

            log.debug("delete SystemLog successful");
        } catch (Exception e) {
            log.error("delete SystemLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSystemLog(SystemLog entity) throws Exception {
        log.debug("updating SystemLog instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SystemLog");
            }

            if (entity.getActionDate() == null) {
                throw new ZMessManager().new EmptyFieldException("actionDate");
            }

            if (entity.getActionName() == null) {
                throw new ZMessManager().new EmptyFieldException("actionName");
            }

            if ((entity.getActionName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getActionName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "actionName");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getIp() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getIp(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("ip");
            }

            if ((entity.getNote() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNote(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("note");
            }

            if ((entity.getUserName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUserName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("userName");
            }

            systemLogDAO.update(entity);

            log.debug("update SystemLog successful");
        } catch (Exception e) {
            log.error("update SystemLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SystemLogDTO> getDataSystemLog() throws Exception {
        try {
            List<SystemLog> systemLog = systemLogDAO.findAll();

            List<SystemLogDTO> systemLogDTO = new ArrayList<SystemLogDTO>();

            for (SystemLog systemLogTmp : systemLog) {
                SystemLogDTO systemLogDTO2 = new SystemLogDTO();

                systemLogDTO2.setId(systemLogTmp.getId());
                systemLogDTO2.setActionDate(systemLogTmp.getActionDate());
                systemLogDTO2.setActionName((systemLogTmp.getActionName() != null)
                    ? systemLogTmp.getActionName() : null);
                systemLogDTO2.setIp((systemLogTmp.getIp() != null)
                    ? systemLogTmp.getIp() : null);
                systemLogDTO2.setNote((systemLogTmp.getNote() != null)
                    ? systemLogTmp.getNote() : null);
                systemLogDTO2.setUserId((systemLogTmp.getUserId() != null)
                    ? systemLogTmp.getUserId() : null);
                systemLogDTO2.setUserName((systemLogTmp.getUserName() != null)
                    ? systemLogTmp.getUserName() : null);
                systemLogDTO.add(systemLogDTO2);
            }

            return systemLogDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SystemLog getSystemLog(Long id) throws Exception {
        log.debug("getting SystemLog instance");

        SystemLog entity = null;

        try {
            entity = systemLogDAO.findById(id);
        } catch (Exception e) {
            log.error("get SystemLog failed", e);
            throw new ZMessManager().new FindingException("SystemLog");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SystemLog> findPageSystemLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SystemLog> entity = null;

        try {
            entity = systemLogDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SystemLog Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSystemLog() throws Exception {
        Long entity = null;

        try {
            entity = systemLogDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SystemLog Count");
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
    public List<SystemLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SystemLog> list = new ArrayList<SystemLog>();
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
            list = systemLogDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
  //SAVE LOG------------------------------------------------------------------------
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void save_systemLog(SystemLogDTO systemLogDTO) throws Exception {
    	log.info("VAS_TRACE save_systemLog");
		
    	try {
    		SystemLog entity = new SystemLog();
    		//entity.setUserId(Integer.parseInt(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
    		entity.setUserName(systemLogDTO.getUserName());
    		entity.setActionDate(new Date());
    		entity.setActionName(systemLogDTO.getActionName());
    		entity.setNote(systemLogDTO.getNote());
    		entity.setIp(systemLogDTO.getIp());

    		saveSystemLog(entity);
			
		} catch (RuntimeException e) {			
			log.error("VAS_TRACE save_systemLog failed", e);
		}
    }
    
    
}
