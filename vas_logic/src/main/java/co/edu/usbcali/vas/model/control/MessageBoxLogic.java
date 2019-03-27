package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.MessageBoxDTO;
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
@Service("MessageBoxLogic")
public class MessageBoxLogic implements IMessageBoxLogic {
    private static final Logger log = LoggerFactory.getLogger(MessageBoxLogic.class);

    /**
     * DAO injected by Spring that manages MessageBox entities
     *
     */
    @Autowired
    private IMessageBoxDAO messageBoxDAO;

    /**
    * Logic injected by Spring that manages Users entities
    *
    */
    @Autowired
    IUsersLogic logicUsers1;

    @Transactional(readOnly = true)
    public List<MessageBox> getMessageBox() throws Exception {
        log.debug("finding all MessageBox instances");

        List<MessageBox> list = new ArrayList<MessageBox>();

        try {
            list = messageBoxDAO.findAll();
        } catch (Exception e) {
            log.error("finding all MessageBox failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "MessageBox");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveMessageBox(MessageBox entity) throws Exception {
        log.debug("saving MessageBox instance");

        try {
            if (entity.getUsers() == null) {
                throw new ZMessManager().new ForeignException("users");
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

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getLink() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLink(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("link");
            }

            if ((entity.getMessage() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMessage(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("message");
            }

            if ((entity.getPictureLink() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPictureLink(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "pictureLink");
            }

            if ((entity.getRead() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getRead(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("read");
            }

            if ((entity.getSent() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSent(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("sent");
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

            if (entity.getUsers().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_Users");
            }

            if (getMessageBox(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            messageBoxDAO.save(entity);

            log.debug("save MessageBox successful");
        } catch (Exception e) {
            log.error("save MessageBox failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMessageBox(MessageBox entity) throws Exception {
        log.debug("deleting MessageBox instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("MessageBox");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            messageBoxDAO.delete(entity);

            log.debug("delete MessageBox successful");
        } catch (Exception e) {
            log.error("delete MessageBox failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateMessageBox(MessageBox entity) throws Exception {
        log.debug("updating MessageBox instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("MessageBox");
            }

            if (entity.getUsers() == null) {
                throw new ZMessManager().new ForeignException("users");
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

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getLink() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLink(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("link");
            }

            if ((entity.getMessage() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMessage(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("message");
            }

            if ((entity.getPictureLink() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPictureLink(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "pictureLink");
            }

            if ((entity.getRead() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getRead(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("read");
            }

            if ((entity.getSent() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSent(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("sent");
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

            if (entity.getUsers().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_Users");
            }

            messageBoxDAO.update(entity);

            log.debug("update MessageBox successful");
        } catch (Exception e) {
            log.error("update MessageBox failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<MessageBoxDTO> getDataMessageBox() throws Exception {
        try {
            List<MessageBox> messageBox = messageBoxDAO.findAll();

            List<MessageBoxDTO> messageBoxDTO = new ArrayList<MessageBoxDTO>();

            for (MessageBox messageBoxTmp : messageBox) {
                MessageBoxDTO messageBoxDTO2 = new MessageBoxDTO();

                messageBoxDTO2.setId(messageBoxTmp.getId());
                messageBoxDTO2.setCreatedAt(messageBoxTmp.getCreatedAt());
                messageBoxDTO2.setCreatedBy((messageBoxTmp.getCreatedBy() != null)
                    ? messageBoxTmp.getCreatedBy() : null);
                messageBoxDTO2.setLink((messageBoxTmp.getLink() != null)
                    ? messageBoxTmp.getLink() : null);
                messageBoxDTO2.setMessage((messageBoxTmp.getMessage() != null)
                    ? messageBoxTmp.getMessage() : null);
                messageBoxDTO2.setPictureLink((messageBoxTmp.getPictureLink() != null)
                    ? messageBoxTmp.getPictureLink() : null);
                messageBoxDTO2.setRead((messageBoxTmp.getRead() != null)
                    ? messageBoxTmp.getRead() : null);
                messageBoxDTO2.setSent((messageBoxTmp.getSent() != null)
                    ? messageBoxTmp.getSent() : null);
                messageBoxDTO2.setSubject((messageBoxTmp.getSubject() != null)
                    ? messageBoxTmp.getSubject() : null);
                messageBoxDTO2.setUpdatedAt(messageBoxTmp.getUpdatedAt());
                messageBoxDTO2.setUpdatedBy((messageBoxTmp.getUpdatedBy() != null)
                    ? messageBoxTmp.getUpdatedBy() : null);
                messageBoxDTO2.setId_Users((messageBoxTmp.getUsers().getId() != null)
                    ? messageBoxTmp.getUsers().getId() : null);
                messageBoxDTO.add(messageBoxDTO2);
            }

            return messageBoxDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public MessageBox getMessageBox(Long id) throws Exception {
        log.debug("getting MessageBox instance");

        MessageBox entity = null;

        try {
            entity = messageBoxDAO.findById(id);
        } catch (Exception e) {
            log.error("get MessageBox failed", e);
            throw new ZMessManager().new FindingException("MessageBox");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<MessageBox> findPageMessageBox(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<MessageBox> entity = null;

        try {
            entity = messageBoxDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("MessageBox Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberMessageBox() throws Exception {
        Long entity = null;

        try {
            entity = messageBoxDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("MessageBox Count");
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
    public List<MessageBox> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<MessageBox> list = new ArrayList<MessageBox>();
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
            list = messageBoxDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
