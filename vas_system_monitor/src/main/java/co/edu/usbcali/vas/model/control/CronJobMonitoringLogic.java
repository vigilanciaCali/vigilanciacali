package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.CronJobMonitoringDTO;
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
@Service("CronJobMonitoringLogic")
public class CronJobMonitoringLogic implements ICronJobMonitoringLogic {
    private static final Logger log = LoggerFactory.getLogger(CronJobMonitoringLogic.class);

    /**
     * DAO injected by Spring that manages CronJobMonitoring entities
     *
     */
    @Autowired
    private ICronJobMonitoringDAO cronJobMonitoringDAO;

    @Transactional(readOnly = true)
    public List<CronJobMonitoring> getCronJobMonitoring()
        throws Exception {
        log.debug("finding all CronJobMonitoring instances");

        List<CronJobMonitoring> list = new ArrayList<CronJobMonitoring>();

        try {
            list = cronJobMonitoringDAO.findAll();
        } catch (Exception e) {
            log.error("finding all CronJobMonitoring failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "CronJobMonitoring");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveCronJobMonitoring(CronJobMonitoring entity)
        throws Exception {
        log.debug("saving CronJobMonitoring instance");

        try {
            if ((entity.getActive() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActive(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("active");
            }

            if ((entity.getArgs() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getArgs(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("args");
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

            if (entity.getDescription() == null) {
                throw new ZMessManager().new EmptyFieldException("description");
            }

            if ((entity.getDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescription(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "description");
            }

            if ((entity.getFunction() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getFunction(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("function");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getIntervalType() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getIntervalType(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "intervalType");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (getCronJobMonitoring(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            cronJobMonitoringDAO.save(entity);

            log.debug("save CronJobMonitoring successful");
        } catch (Exception e) {
            log.error("save CronJobMonitoring failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCronJobMonitoring(CronJobMonitoring entity)
        throws Exception {
        log.debug("deleting CronJobMonitoring instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "CronJobMonitoring");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            cronJobMonitoringDAO.delete(entity);

            log.debug("delete CronJobMonitoring successful");
        } catch (Exception e) {
            log.error("delete CronJobMonitoring failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateCronJobMonitoring(CronJobMonitoring entity)
        throws Exception {
        log.debug("updating CronJobMonitoring instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "CronJobMonitoring");
            }

            if ((entity.getActive() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActive(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("active");
            }

            if ((entity.getArgs() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getArgs(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("args");
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

            if (entity.getDescription() == null) {
                throw new ZMessManager().new EmptyFieldException("description");
            }

            if ((entity.getDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescription(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "description");
            }

            if ((entity.getFunction() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getFunction(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("function");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getIntervalType() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getIntervalType(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "intervalType");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            cronJobMonitoringDAO.update(entity);

            log.debug("update CronJobMonitoring successful");
        } catch (Exception e) {
            log.error("update CronJobMonitoring failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<CronJobMonitoringDTO> getDataCronJobMonitoring()
        throws Exception {
        try {
            List<CronJobMonitoring> cronJobMonitoring = cronJobMonitoringDAO.findAll();

            List<CronJobMonitoringDTO> cronJobMonitoringDTO = new ArrayList<CronJobMonitoringDTO>();

            for (CronJobMonitoring cronJobMonitoringTmp : cronJobMonitoring) {
                CronJobMonitoringDTO cronJobMonitoringDTO2 = new CronJobMonitoringDTO();

                cronJobMonitoringDTO2.setId(cronJobMonitoringTmp.getId());
                cronJobMonitoringDTO2.setActive((cronJobMonitoringTmp.getActive() != null)
                    ? cronJobMonitoringTmp.getActive() : null);
                cronJobMonitoringDTO2.setArgs((cronJobMonitoringTmp.getArgs() != null)
                    ? cronJobMonitoringTmp.getArgs() : null);
                cronJobMonitoringDTO2.setCode((cronJobMonitoringTmp.getCode() != null)
                    ? cronJobMonitoringTmp.getCode() : null);
                cronJobMonitoringDTO2.setCreatedAt(cronJobMonitoringTmp.getCreatedAt());
                cronJobMonitoringDTO2.setCreatedBy((cronJobMonitoringTmp.getCreatedBy() != null)
                    ? cronJobMonitoringTmp.getCreatedBy() : null);
                cronJobMonitoringDTO2.setDescription((cronJobMonitoringTmp.getDescription() != null)
                    ? cronJobMonitoringTmp.getDescription() : null);
                cronJobMonitoringDTO2.setFunction((cronJobMonitoringTmp.getFunction() != null)
                    ? cronJobMonitoringTmp.getFunction() : null);
                cronJobMonitoringDTO2.setIntervalNumber((cronJobMonitoringTmp.getIntervalNumber() != null)
                    ? cronJobMonitoringTmp.getIntervalNumber() : null);
                cronJobMonitoringDTO2.setIntervalType((cronJobMonitoringTmp.getIntervalType() != null)
                    ? cronJobMonitoringTmp.getIntervalType() : null);
                cronJobMonitoringDTO2.setNextcall(cronJobMonitoringTmp.getNextcall());
                cronJobMonitoringDTO2.setNumbercall((cronJobMonitoringTmp.getNumbercall() != null)
                    ? cronJobMonitoringTmp.getNumbercall() : null);
                cronJobMonitoringDTO2.setPriority((cronJobMonitoringTmp.getPriority() != null)
                    ? cronJobMonitoringTmp.getPriority() : null);
                cronJobMonitoringDTO2.setUpdatedAt(cronJobMonitoringTmp.getUpdatedAt());
                cronJobMonitoringDTO2.setUpdatedBy((cronJobMonitoringTmp.getUpdatedBy() != null)
                    ? cronJobMonitoringTmp.getUpdatedBy() : null);
                cronJobMonitoringDTO.add(cronJobMonitoringDTO2);
            }

            return cronJobMonitoringDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public CronJobMonitoring getCronJobMonitoring(Integer id)
        throws Exception {
        log.debug("getting CronJobMonitoring instance");

        CronJobMonitoring entity = null;

        try {
            entity = cronJobMonitoringDAO.findById(id);
        } catch (Exception e) {
            log.error("get CronJobMonitoring failed", e);
            throw new ZMessManager().new FindingException("CronJobMonitoring");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<CronJobMonitoring> findPageCronJobMonitoring(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<CronJobMonitoring> entity = null;

        try {
            entity = cronJobMonitoringDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "CronJobMonitoring Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberCronJobMonitoring() throws Exception {
        Long entity = null;

        try {
            entity = cronJobMonitoringDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "CronJobMonitoring Count");
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
    public List<CronJobMonitoring> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<CronJobMonitoring> list = new ArrayList<CronJobMonitoring>();
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
            list = cronJobMonitoringDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
