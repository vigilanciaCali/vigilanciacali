package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.DeviceLogDTO;
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
@Service("DeviceLogLogic")
public class DeviceLogLogic implements IDeviceLogLogic {
    private static final Logger log = LoggerFactory.getLogger(DeviceLogLogic.class);

    /**
     * DAO injected by Spring that manages DeviceLog entities
     *
     */
    @Autowired
    private IDeviceLogDAO deviceLogDAO;

    @Transactional(readOnly = true)
    public List<DeviceLog> getDeviceLog() throws Exception {
        log.debug("finding all DeviceLog instances");

        List<DeviceLog> list = new ArrayList<DeviceLog>();

        try {
            list = deviceLogDAO.findAll();
        } catch (Exception e) {
            log.error("finding all DeviceLog failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "DeviceLog");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveDeviceLog(DeviceLog entity) throws Exception {
        log.debug("saving DeviceLog instance");

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

            if (entity.getDeviceId() == null) {
                throw new ZMessManager().new EmptyFieldException("deviceId");
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

            if (getDeviceLog(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            deviceLogDAO.save(entity);

            log.debug("save DeviceLog successful");
        } catch (Exception e) {
            log.error("save DeviceLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteDeviceLog(DeviceLog entity) throws Exception {
        log.debug("deleting DeviceLog instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("DeviceLog");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            deviceLogDAO.delete(entity);

            log.debug("delete DeviceLog successful");
        } catch (Exception e) {
            log.error("delete DeviceLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateDeviceLog(DeviceLog entity) throws Exception {
        log.debug("updating DeviceLog instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("DeviceLog");
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

            if (entity.getDeviceId() == null) {
                throw new ZMessManager().new EmptyFieldException("deviceId");
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

            deviceLogDAO.update(entity);

            log.debug("update DeviceLog successful");
        } catch (Exception e) {
            log.error("update DeviceLog failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<DeviceLogDTO> getDataDeviceLog() throws Exception {
        try {
            List<DeviceLog> deviceLog = deviceLogDAO.findAll();

            List<DeviceLogDTO> deviceLogDTO = new ArrayList<DeviceLogDTO>();

            for (DeviceLog deviceLogTmp : deviceLog) {
                DeviceLogDTO deviceLogDTO2 = new DeviceLogDTO();

                deviceLogDTO2.setId(deviceLogTmp.getId());
                deviceLogDTO2.setActionDate(deviceLogTmp.getActionDate());
                deviceLogDTO2.setActionName((deviceLogTmp.getActionName() != null)
                    ? deviceLogTmp.getActionName() : null);
                deviceLogDTO2.setDeviceId((deviceLogTmp.getDeviceId() != null)
                    ? deviceLogTmp.getDeviceId() : null);
                deviceLogDTO2.setIp((deviceLogTmp.getIp() != null)
                    ? deviceLogTmp.getIp() : null);
                deviceLogDTO2.setNote((deviceLogTmp.getNote() != null)
                    ? deviceLogTmp.getNote() : null);
                deviceLogDTO2.setUserId((deviceLogTmp.getUserId() != null)
                    ? deviceLogTmp.getUserId() : null);
                deviceLogDTO2.setUserName((deviceLogTmp.getUserName() != null)
                    ? deviceLogTmp.getUserName() : null);
                deviceLogDTO.add(deviceLogDTO2);
            }

            return deviceLogDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DeviceLog getDeviceLog(Long id) throws Exception {
        log.debug("getting DeviceLog instance");

        DeviceLog entity = null;

        try {
            entity = deviceLogDAO.findById(id);
        } catch (Exception e) {
            log.error("get DeviceLog failed", e);
            throw new ZMessManager().new FindingException("DeviceLog");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<DeviceLog> findPageDeviceLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<DeviceLog> entity = null;

        try {
            entity = deviceLogDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("DeviceLog Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberDeviceLog() throws Exception {
        Long entity = null;

        try {
            entity = deviceLogDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("DeviceLog Count");
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
    public List<DeviceLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<DeviceLog> list = new ArrayList<DeviceLog>();
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
            list = deviceLogDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
