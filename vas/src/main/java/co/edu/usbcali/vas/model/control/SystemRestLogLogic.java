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

import co.edu.usbcali.vas.dataaccess.dao.ISystemRestLogDAO;
import co.edu.usbcali.vas.exceptions.ZMessManager;
import co.edu.usbcali.vas.model.SystemRestLog;
import co.edu.usbcali.vas.model.dto.SystemRestLogDTO;
import co.edu.usbcali.vas.utilities.Utilities;


@Scope("singleton")
@Service("SystemRestLogLogic")
public class SystemRestLogLogic implements ISystemRestLogLogic {
    private static final Logger log = LoggerFactory.getLogger(SystemRestLogLogic.class);

    /**
     * DAO injected by Spring that manages SystemRestLog entities
     *
     */
    @Autowired
    private ISystemRestLogDAO systemRestLogDAO;

    @Transactional(readOnly = true)
    public List<SystemRestLog> getSystemRestLog() throws Exception {
        log.debug("finding all SystemRestLog instances");

        List<SystemRestLog> list = new ArrayList<SystemRestLog>();

        try {
            list = systemRestLogDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SystemRestLog failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SystemRestLog");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSystemRestLog(SystemRestLog entity)
        throws Exception {
        log.debug("saving SystemRestLog instance");

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

            if (entity.getUserId() == null) {
                throw new ZMessManager().new EmptyFieldException("userId");
            }

            if ((entity.getUserName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUserName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("userName");
            }

            if (getSystemRestLog(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            systemRestLogDAO.save(entity);

            log.debug("save SystemRestLog successful");
        } catch (Exception e) {
            log.error("save SystemRestLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSystemRestLog(SystemRestLog entity)
        throws Exception {
        log.debug("deleting SystemRestLog instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SystemRestLog");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            systemRestLogDAO.delete(entity);

            log.debug("delete SystemRestLog successful");
        } catch (Exception e) {
            log.error("delete SystemRestLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSystemRestLog(SystemRestLog entity)
        throws Exception {
        log.debug("updating SystemRestLog instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "SystemRestLog");
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

            if (entity.getUserId() == null) {
                throw new ZMessManager().new EmptyFieldException("userId");
            }

            if ((entity.getUserName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUserName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("userName");
            }

            systemRestLogDAO.update(entity);

            log.debug("update SystemRestLog successful");
        } catch (Exception e) {
            log.error("update SystemRestLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SystemRestLogDTO> getDataSystemRestLog()
        throws Exception {
        try {
            List<SystemRestLog> systemRestLog = systemRestLogDAO.findAll();

            List<SystemRestLogDTO> systemRestLogDTO = new ArrayList<SystemRestLogDTO>();

            for (SystemRestLog systemRestLogTmp : systemRestLog) {
                SystemRestLogDTO systemRestLogDTO2 = new SystemRestLogDTO();

                systemRestLogDTO2.setId(systemRestLogTmp.getId());
                systemRestLogDTO2.setActionDate(systemRestLogTmp.getActionDate());
                systemRestLogDTO2.setActionName((systemRestLogTmp.getActionName() != null)
                    ? systemRestLogTmp.getActionName() : null);
                systemRestLogDTO2.setIp((systemRestLogTmp.getIp() != null)
                    ? systemRestLogTmp.getIp() : null);
                systemRestLogDTO2.setNote((systemRestLogTmp.getNote() != null)
                    ? systemRestLogTmp.getNote() : null);
                systemRestLogDTO2.setUserId((systemRestLogTmp.getUserId() != null)
                    ? systemRestLogTmp.getUserId() : null);
                systemRestLogDTO2.setUserName((systemRestLogTmp.getUserName() != null)
                    ? systemRestLogTmp.getUserName() : null);
                systemRestLogDTO.add(systemRestLogDTO2);
            }

            return systemRestLogDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SystemRestLog getSystemRestLog(Long id) throws Exception {
        log.debug("getting SystemRestLog instance");

        SystemRestLog entity = null;

        try {
            entity = systemRestLogDAO.findById(id);
        } catch (Exception e) {
            log.error("get SystemRestLog failed", e);
            throw new ZMessManager().new FindingException("SystemRestLog");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SystemRestLog> findPageSystemRestLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SystemRestLog> entity = null;

        try {
            entity = systemRestLogDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SystemRestLog Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSystemRestLog() throws Exception {
        Long entity = null;

        try {
            entity = systemRestLogDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SystemRestLog Count");
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
    public List<SystemRestLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SystemRestLog> list = new ArrayList<SystemRestLog>();
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
            list = systemRestLogDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
