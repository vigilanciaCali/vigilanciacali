package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.CronJobDTO;
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
@Service("CronJobLogic")
public class CronJobLogic implements ICronJobLogic {
    private static final Logger log = LoggerFactory.getLogger(CronJobLogic.class);

    /**
     * DAO injected by Spring that manages CronJob entities
     *
     */
    @Autowired
    private ICronJobDAO cronJobDAO;

    @Transactional(readOnly = true)
    public List<CronJob> getCronJob() throws Exception {
        log.debug("finding all CronJob instances");

        List<CronJob> list = new ArrayList<CronJob>();

        try {
            list = cronJobDAO.findAll();
        } catch (Exception e) {
            log.error("finding all CronJob failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "CronJob");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveCronJob(CronJob entity) throws Exception {
        log.debug("saving CronJob instance");

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

            if (entity.getNextcall() == null) {
                throw new ZMessManager().new EmptyFieldException("nextcall");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (getCronJob(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            cronJobDAO.save(entity);

            log.debug("save CronJob successful");
        } catch (Exception e) {
            log.error("save CronJob failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCronJob(CronJob entity) throws Exception {
        log.debug("deleting CronJob instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("CronJob");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            cronJobDAO.delete(entity);

            log.debug("delete CronJob successful");
        } catch (Exception e) {
            log.error("delete CronJob failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateCronJob(CronJob entity) throws Exception {
        log.debug("updating CronJob instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("CronJob");
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

            if (entity.getNextcall() == null) {
                throw new ZMessManager().new EmptyFieldException("nextcall");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            cronJobDAO.update(entity);

            log.debug("update CronJob successful");
        } catch (Exception e) {
            log.error("update CronJob failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<CronJobDTO> getDataCronJob() throws Exception {
        try {
            List<CronJob> cronJob = cronJobDAO.findAll();

            List<CronJobDTO> cronJobDTO = new ArrayList<CronJobDTO>();

            for (CronJob cronJobTmp : cronJob) {
                CronJobDTO cronJobDTO2 = new CronJobDTO();

                cronJobDTO2.setId(cronJobTmp.getId());
                cronJobDTO2.setActive((cronJobTmp.getActive() != null)
                    ? cronJobTmp.getActive() : null);
                cronJobDTO2.setArgs((cronJobTmp.getArgs() != null)
                    ? cronJobTmp.getArgs() : null);
                cronJobDTO2.setCode((cronJobTmp.getCode() != null)
                    ? cronJobTmp.getCode() : null);
                cronJobDTO2.setCreatedAt(cronJobTmp.getCreatedAt());
                cronJobDTO2.setCreatedBy((cronJobTmp.getCreatedBy() != null)
                    ? cronJobTmp.getCreatedBy() : null);
                cronJobDTO2.setDescription((cronJobTmp.getDescription() != null)
                    ? cronJobTmp.getDescription() : null);
                cronJobDTO2.setFunction((cronJobTmp.getFunction() != null)
                    ? cronJobTmp.getFunction() : null);
                cronJobDTO2.setIntervalNumber((cronJobTmp.getIntervalNumber() != null)
                    ? cronJobTmp.getIntervalNumber() : null);
                cronJobDTO2.setIntervalType((cronJobTmp.getIntervalType() != null)
                    ? cronJobTmp.getIntervalType() : null);
                cronJobDTO2.setNextcall(cronJobTmp.getNextcall());
                cronJobDTO2.setNumbercall((cronJobTmp.getNumbercall() != null)
                    ? cronJobTmp.getNumbercall() : null);
                cronJobDTO2.setPriority((cronJobTmp.getPriority() != null)
                    ? cronJobTmp.getPriority() : null);
                cronJobDTO2.setUpdatedAt(cronJobTmp.getUpdatedAt());
                cronJobDTO2.setUpdatedBy((cronJobTmp.getUpdatedBy() != null)
                    ? cronJobTmp.getUpdatedBy() : null);
                cronJobDTO.add(cronJobDTO2);
            }

            return cronJobDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public CronJob getCronJob(Integer id) throws Exception {
        log.debug("getting CronJob instance");

        CronJob entity = null;

        try {
            entity = cronJobDAO.findById(id);
        } catch (Exception e) {
            log.error("get CronJob failed", e);
            throw new ZMessManager().new FindingException("CronJob");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<CronJob> findPageCronJob(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<CronJob> entity = null;

        try {
            entity = cronJobDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("CronJob Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberCronJob() throws Exception {
        Long entity = null;

        try {
            entity = cronJobDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("CronJob Count");
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
    public List<CronJob> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<CronJob> list = new ArrayList<CronJob>();
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
            list = cronJobDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
