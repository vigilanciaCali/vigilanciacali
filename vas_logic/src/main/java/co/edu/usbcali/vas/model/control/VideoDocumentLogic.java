package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.VideoDocumentDTO;
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
@Service("VideoDocumentLogic")
public class VideoDocumentLogic implements IVideoDocumentLogic {
    private static final Logger log = LoggerFactory.getLogger(VideoDocumentLogic.class);

    /**
     * DAO injected by Spring that manages VideoDocument entities
     *
     */
    @Autowired
    private IVideoDocumentDAO videoDocumentDAO;

    /**
    * Logic injected by Spring that manages Video entities
    *
    */
    @Autowired
    IVideoLogic logicVideo1;

    @Transactional(readOnly = true)
    public List<VideoDocument> getVideoDocument() throws Exception {
        log.debug("finding all VideoDocument instances");

        List<VideoDocument> list = new ArrayList<VideoDocument>();

        try {
            list = videoDocumentDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VideoDocument failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VideoDocument");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVideoDocument(VideoDocument entity)
        throws Exception {
        log.debug("saving VideoDocument instance");

        try {
            if (entity.getVideo() == null) {
                throw new ZMessManager().new ForeignException("video");
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

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if (entity.getMimeType() == null) {
                throw new ZMessManager().new EmptyFieldException("mimeType");
            }

            if ((entity.getMimeType() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMimeType(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("mimeType");
            }

            if (entity.getSource() == null) {
                throw new ZMessManager().new EmptyFieldException("source");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (entity.getVideo().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_Video");
            }

            if (getVideoDocument(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            videoDocumentDAO.save(entity);

            log.debug("save VideoDocument successful");
        } catch (Exception e) {
            log.error("save VideoDocument failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVideoDocument(VideoDocument entity)
        throws Exception {
        log.debug("deleting VideoDocument instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VideoDocument");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            videoDocumentDAO.delete(entity);

            log.debug("delete VideoDocument successful");
        } catch (Exception e) {
            log.error("delete VideoDocument failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVideoDocument(VideoDocument entity)
        throws Exception {
        log.debug("updating VideoDocument instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "VideoDocument");
            }

            if (entity.getVideo() == null) {
                throw new ZMessManager().new ForeignException("video");
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

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if (entity.getMimeType() == null) {
                throw new ZMessManager().new EmptyFieldException("mimeType");
            }

            if ((entity.getMimeType() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMimeType(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("mimeType");
            }

            if (entity.getSource() == null) {
                throw new ZMessManager().new EmptyFieldException("source");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (entity.getVideo().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_Video");
            }

            videoDocumentDAO.update(entity);

            log.debug("update VideoDocument successful");
        } catch (Exception e) {
            log.error("update VideoDocument failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VideoDocumentDTO> getDataVideoDocument()
        throws Exception {
        try {
            List<VideoDocument> videoDocument = videoDocumentDAO.findAll();

            List<VideoDocumentDTO> videoDocumentDTO = new ArrayList<VideoDocumentDTO>();

            for (VideoDocument videoDocumentTmp : videoDocument) {
                VideoDocumentDTO videoDocumentDTO2 = new VideoDocumentDTO();

                videoDocumentDTO2.setId(videoDocumentTmp.getId());
                videoDocumentDTO2.setCreatedAt(videoDocumentTmp.getCreatedAt());
                videoDocumentDTO2.setCreatedBy((videoDocumentTmp.getCreatedBy() != null)
                    ? videoDocumentTmp.getCreatedBy() : null);
                videoDocumentDTO2.setDescription((videoDocumentTmp.getDescription() != null)
                    ? videoDocumentTmp.getDescription() : null);
                videoDocumentDTO2.setMimeType((videoDocumentTmp.getMimeType() != null)
                    ? videoDocumentTmp.getMimeType() : null);
                videoDocumentDTO2.setSource((videoDocumentTmp.getSource() != null)
                    ? videoDocumentTmp.getSource() : null);
                videoDocumentDTO2.setUpdatedAt(videoDocumentTmp.getUpdatedAt());
                videoDocumentDTO2.setUpdatedBy((videoDocumentTmp.getUpdatedBy() != null)
                    ? videoDocumentTmp.getUpdatedBy() : null);
                videoDocumentDTO2.setId_Video((videoDocumentTmp.getVideo()
                                                               .getId() != null)
                    ? videoDocumentTmp.getVideo().getId() : null);
                videoDocumentDTO.add(videoDocumentDTO2);
            }

            return videoDocumentDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VideoDocument getVideoDocument(Long id) throws Exception {
        log.debug("getting VideoDocument instance");

        VideoDocument entity = null;

        try {
            entity = videoDocumentDAO.findById(id);
        } catch (Exception e) {
            log.error("get VideoDocument failed", e);
            throw new ZMessManager().new FindingException("VideoDocument");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VideoDocument> findPageVideoDocument(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VideoDocument> entity = null;

        try {
            entity = videoDocumentDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VideoDocument Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVideoDocument() throws Exception {
        Long entity = null;

        try {
            entity = videoDocumentDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VideoDocument Count");
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
    public List<VideoDocument> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VideoDocument> list = new ArrayList<VideoDocument>();
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
            list = videoDocumentDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
