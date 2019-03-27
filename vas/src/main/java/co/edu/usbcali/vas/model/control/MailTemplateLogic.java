package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.MailTemplateDTO;
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
@Service("MailTemplateLogic")
public class MailTemplateLogic implements IMailTemplateLogic {
    private static final Logger log = LoggerFactory.getLogger(MailTemplateLogic.class);

    /**
     * DAO injected by Spring that manages MailTemplate entities
     *
     */
    @Autowired
    private IMailTemplateDAO mailTemplateDAO;

    /**
    * Logic injected by Spring that manages MailServer entities
    *
    */
    @Autowired
    IMailServerLogic logicMailServer1;

    @Transactional(readOnly = true)
    public List<MailTemplate> getMailTemplate() throws Exception {
        log.debug("finding all MailTemplate instances");

        List<MailTemplate> list = new ArrayList<MailTemplate>();

        try {
            list = mailTemplateDAO.findAll();
        } catch (Exception e) {
            log.error("finding all MailTemplate failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "MailTemplate");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveMailTemplate(MailTemplate entity) throws Exception {
        log.debug("saving MailTemplate instance");

        try {
            if (entity.getMailServer() == null) {
                throw new ZMessManager().new ForeignException("mailServer");
            }

            if ((entity.getBodyHtml() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getBodyHtml(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("bodyHtml");
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

            if ((entity.getEmailCc() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmailCc(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("emailCc");
            }

            if ((entity.getEmailTo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmailTo(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("emailTo");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getLang() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLang(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("lang");
            }

            if ((entity.getSubject() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSubject(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("subject");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (entity.getMailServer().getId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "id_MailServer");
            }

            if (getMailTemplate(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            mailTemplateDAO.save(entity);

            log.debug("save MailTemplate successful");
        } catch (Exception e) {
            log.error("save MailTemplate failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMailTemplate(MailTemplate entity)
        throws Exception {
        log.debug("deleting MailTemplate instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("MailTemplate");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            mailTemplateDAO.delete(entity);

            log.debug("delete MailTemplate successful");
        } catch (Exception e) {
            log.error("delete MailTemplate failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateMailTemplate(MailTemplate entity)
        throws Exception {
        log.debug("updating MailTemplate instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("MailTemplate");
            }

            if (entity.getMailServer() == null) {
                throw new ZMessManager().new ForeignException("mailServer");
            }

            if ((entity.getBodyHtml() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getBodyHtml(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("bodyHtml");
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

            if ((entity.getEmailCc() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmailCc(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("emailCc");
            }

            if ((entity.getEmailTo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmailTo(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("emailTo");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getLang() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLang(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("lang");
            }

            if ((entity.getSubject() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSubject(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("subject");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (entity.getMailServer().getId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "id_MailServer");
            }

            mailTemplateDAO.update(entity);

            log.debug("update MailTemplate successful");
        } catch (Exception e) {
            log.error("update MailTemplate failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<MailTemplateDTO> getDataMailTemplate()
        throws Exception {
        try {
            List<MailTemplate> mailTemplate = mailTemplateDAO.findAll();

            List<MailTemplateDTO> mailTemplateDTO = new ArrayList<MailTemplateDTO>();

            for (MailTemplate mailTemplateTmp : mailTemplate) {
                MailTemplateDTO mailTemplateDTO2 = new MailTemplateDTO();

                mailTemplateDTO2.setId(mailTemplateTmp.getId());
                mailTemplateDTO2.setBodyHtml((mailTemplateTmp.getBodyHtml() != null)
                    ? mailTemplateTmp.getBodyHtml() : null);
                mailTemplateDTO2.setCreatedAt(mailTemplateTmp.getCreatedAt());
                mailTemplateDTO2.setCreatedBy((mailTemplateTmp.getCreatedBy() != null)
                    ? mailTemplateTmp.getCreatedBy() : null);
                mailTemplateDTO2.setEmailCc((mailTemplateTmp.getEmailCc() != null)
                    ? mailTemplateTmp.getEmailCc() : null);
                mailTemplateDTO2.setEmailTo((mailTemplateTmp.getEmailTo() != null)
                    ? mailTemplateTmp.getEmailTo() : null);
                mailTemplateDTO2.setLang((mailTemplateTmp.getLang() != null)
                    ? mailTemplateTmp.getLang() : null);
                mailTemplateDTO2.setSubject((mailTemplateTmp.getSubject() != null)
                    ? mailTemplateTmp.getSubject() : null);
                mailTemplateDTO2.setUpdatedAt(mailTemplateTmp.getUpdatedAt());
                mailTemplateDTO2.setUpdatedBy((mailTemplateTmp.getUpdatedBy() != null)
                    ? mailTemplateTmp.getUpdatedBy() : null);
                mailTemplateDTO2.setId_MailServer((mailTemplateTmp.getMailServer()
                                                                  .getId() != null)
                    ? mailTemplateTmp.getMailServer().getId() : null);
                mailTemplateDTO.add(mailTemplateDTO2);
            }

            return mailTemplateDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public MailTemplate getMailTemplate(Integer id) throws Exception {
        log.debug("getting MailTemplate instance");

        MailTemplate entity = null;

        try {
            entity = mailTemplateDAO.findById(id);
        } catch (Exception e) {
            log.error("get MailTemplate failed", e);
            throw new ZMessManager().new FindingException("MailTemplate");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<MailTemplate> findPageMailTemplate(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<MailTemplate> entity = null;

        try {
            entity = mailTemplateDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("MailTemplate Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberMailTemplate() throws Exception {
        Long entity = null;

        try {
            entity = mailTemplateDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("MailTemplate Count");
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
    public List<MailTemplate> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<MailTemplate> list = new ArrayList<MailTemplate>();
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
            list = mailTemplateDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
