package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.SystemMailLogDTO;
import co.edu.usbcali.vas.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SystemMailLogLogic")
public class SystemMailLogLogic implements ISystemMailLogLogic {
    private static final Logger log = LoggerFactory.getLogger(SystemMailLogLogic.class);

    /**
     * DAO injected by Spring that manages SystemMailLog entities
     *
     */
    @Autowired
    private ISystemMailLogDAO systemMailLogDAO;

    @Transactional(readOnly = true)
    public List<SystemMailLog> getSystemMailLog() throws Exception {
        log.debug("finding all SystemMailLog instances");

        List<SystemMailLog> list = new ArrayList<SystemMailLog>();

        try {
            list = systemMailLogDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SystemMailLog failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SystemMailLog");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSystemMailLog(SystemMailLog entity)
        throws Exception {
        log.debug("saving SystemMailLog instance");

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

            if (getSystemMailLog(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            systemMailLogDAO.save(entity);

            log.debug("save SystemMailLog successful");
        } catch (Exception e) {
            log.error("save SystemMailLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSystemMailLog(SystemMailLog entity)
        throws Exception {
        log.debug("deleting SystemMailLog instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SystemMailLog");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            systemMailLogDAO.delete(entity);

            log.debug("delete SystemMailLog successful");
        } catch (Exception e) {
            log.error("delete SystemMailLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSystemMailLog(SystemMailLog entity)
        throws Exception {
        log.debug("updating SystemMailLog instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "SystemMailLog");
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

            systemMailLogDAO.update(entity);

            log.debug("update SystemMailLog successful");
        } catch (Exception e) {
            log.error("update SystemMailLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SystemMailLogDTO> getDataSystemMailLog()
        throws Exception {
        try {
            List<SystemMailLog> systemMailLog = systemMailLogDAO.findAll();

            List<SystemMailLogDTO> systemMailLogDTO = new ArrayList<SystemMailLogDTO>();

            for (SystemMailLog systemMailLogTmp : systemMailLog) {
                SystemMailLogDTO systemMailLogDTO2 = new SystemMailLogDTO();

                systemMailLogDTO2.setId(systemMailLogTmp.getId());
                systemMailLogDTO2.setActionDate(systemMailLogTmp.getActionDate());
                systemMailLogDTO2.setActionName((systemMailLogTmp.getActionName() != null)
                    ? systemMailLogTmp.getActionName() : null);
                systemMailLogDTO2.setIp((systemMailLogTmp.getIp() != null)
                    ? systemMailLogTmp.getIp() : null);
                systemMailLogDTO2.setNote((systemMailLogTmp.getNote() != null)
                    ? systemMailLogTmp.getNote() : null);
                systemMailLogDTO2.setUserId((systemMailLogTmp.getUserId() != null)
                    ? systemMailLogTmp.getUserId() : null);
                systemMailLogDTO2.setUserName((systemMailLogTmp.getUserName() != null)
                    ? systemMailLogTmp.getUserName() : null);
                systemMailLogDTO.add(systemMailLogDTO2);
            }

            return systemMailLogDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SystemMailLog getSystemMailLog(Long id) throws Exception {
        log.debug("getting SystemMailLog instance");

        SystemMailLog entity = null;

        try {
            entity = systemMailLogDAO.findById(id);
        } catch (Exception e) {
            log.error("get SystemMailLog failed", e);
            throw new ZMessManager().new FindingException("SystemMailLog");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SystemMailLog> findPageSystemMailLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SystemMailLog> entity = null;

        try {
            entity = systemMailLogDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SystemMailLog Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSystemMailLog() throws Exception {
        Long entity = null;

        try {
            entity = systemMailLogDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SystemMailLog Count");
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
    public List<SystemMailLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SystemMailLog> list = new ArrayList<SystemMailLog>();
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
            list = systemMailLogDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
