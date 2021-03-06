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

import co.edu.usbcali.vas.dataaccess.dao.IVideoTempDAO;
import co.edu.usbcali.vas.exceptions.ZMessManager;
import co.edu.usbcali.vas.model.VideoTemp;
import co.edu.usbcali.vas.model.dto.VideoTempDTO;
import co.edu.usbcali.vas.utilities.Utilities;

@Scope("singleton")
@Service("VideoTempLogic")
public class VideoTempLogic implements IVideoTempLogic {
    private static final Logger log = LoggerFactory.getLogger(VideoTempLogic.class);

    /**
     * DAO injected by Spring that manages VideoTemp entities
     *
     */
    @Autowired
    private IVideoTempDAO videoTempDAO;


    @Transactional(readOnly = true)
    public List<VideoTemp> getVideoTemp() throws Exception {
        log.debug("finding all VideoTemp instances");

        List<VideoTemp> list = new ArrayList<VideoTemp>();

        try {
            list = videoTempDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VideoTemp failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VideoTemp");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVideoTemp(VideoTemp entity) throws Exception {
        log.debug("saving VideoTemp instance");

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

            if (entity.getDescription() == null) {
                throw new ZMessManager().new EmptyFieldException("description");
            }

            if ((entity.getDescription() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescription(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "description");
            }

            if ((entity.getUrl() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUrl(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("url");
            }

            if (entity.getUsers().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_Users");
            }

            videoTempDAO.save(entity);

            log.debug("save VideoTemp successful");
        } catch (Exception e) {
            log.error("save VideoTemp failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVideoTemp(VideoTemp entity) throws Exception {
        log.debug("deleting VideoTemp instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VideoTemp");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            videoTempDAO.delete(entity);

            log.debug("delete VideoTemp successful");
        } catch (Exception e) {
            log.error("delete VideoTemp failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVideoTemp(VideoTemp entity) throws Exception {
        log.debug("updating VideoTemp instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VideoTemp");
            }

            if (entity.getUsers() == null) {
                throw new ZMessManager().new ForeignException("users");
            }

            if ((entity.getAnalyticData() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAnalyticData(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "analyticData");
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

            if ((entity.getFormat() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getFormat(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("format");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getInfo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getInfo(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("info");
            }

            if ((entity.getLenght() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLenght(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("lenght");
            }

            if ((entity.getMimeType() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getMimeType(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("mimeType");
            }

            if ((entity.getSrc() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSrc(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("src");
            }

            if ((entity.getType() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getType(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("type");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if ((entity.getUrl() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUrl(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("url");
            }

            if ((entity.getVideoData() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getVideoData(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "videoData");
            }

            if (entity.getUsers().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_Users");
            }

            videoTempDAO.update(entity);

            log.debug("update VideoTemp successful");
        } catch (Exception e) {
            log.error("update VideoTemp failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VideoTempDTO> getDataVideoTemp() throws Exception {
        try {
            List<VideoTemp> videoTemp = videoTempDAO.getVideoTempDataByIdASC();

            List<VideoTempDTO> videoTempDTO = new ArrayList<VideoTempDTO>();

            for (VideoTemp videoTempTmp : videoTemp) {
                VideoTempDTO videoTempDTO2 = new VideoTempDTO();

                this.setVideoData(videoTempDTO2, videoTempTmp);
                
                videoTempDTO.add(videoTempDTO2);
            }

            return videoTempDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<VideoTempDTO> getDataVideoTempTracker() throws Exception {
        try {
            List<VideoTemp> videoTemp = videoTempDAO.getDataVideoTempTracker();

            List<VideoTempDTO> videoTempDTO = new ArrayList<VideoTempDTO>();

            for (VideoTemp videoTempTmp : videoTemp) {
                VideoTempDTO videoTempDTO2 = new VideoTempDTO();

                this.setVideoData(videoTempDTO2, videoTempTmp);
                
                videoTempDTO.add(videoTempDTO2);
            }

            return videoTempDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<VideoTempDTO> getDataVideoTempAnomalous() throws Exception {
        try {
            List<VideoTemp> videoTemp = videoTempDAO.getDataVideoTempAnomalous();

            List<VideoTempDTO> videoTempDTO = new ArrayList<VideoTempDTO>();

            for (VideoTemp videoTempTmp : videoTemp) {
                VideoTempDTO videoTempDTO2 = new VideoTempDTO();

                this.setVideoData(videoTempDTO2, videoTempTmp);
                
                videoTempDTO.add(videoTempDTO2);
            }

            return videoTempDTO;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setVideoData(VideoTempDTO videoTempDTO2, VideoTemp videoTempTmp) {
    	
    	videoTempDTO2.setId(videoTempTmp.getId());
        videoTempDTO2.setAnalysisTime((videoTempTmp.getAnalysisTime() != null)
            ? videoTempTmp.getAnalysisTime() : null);
        videoTempDTO2.setAnalyticData((videoTempTmp.getAnalyticData() != null)
            ? videoTempTmp.getAnalyticData() : null);
        videoTempDTO2.setCreatedAt(videoTempTmp.getCreatedAt());
        videoTempDTO2.setCreatedBy((videoTempTmp.getCreatedBy() != null)
            ? videoTempTmp.getCreatedBy() : null);
        videoTempDTO2.setDescription((videoTempTmp.getDescription() != null)
            ? videoTempTmp.getDescription() : null);
        videoTempDTO2.setFormat((videoTempTmp.getFormat() != null)
            ? videoTempTmp.getFormat() : null);
        videoTempDTO2.setInfo((videoTempTmp.getInfo() != null)
            ? videoTempTmp.getInfo() : null);
        videoTempDTO2.setLenght((videoTempTmp.getLenght() != null)
            ? videoTempTmp.getLenght() : null);
        videoTempDTO2.setMimeType((videoTempTmp.getMimeType() != null)
            ? videoTempTmp.getMimeType() : null);
        videoTempDTO2.setSrc((videoTempTmp.getSrc() != null)
            ? videoTempTmp.getSrc() : null);
        videoTempDTO2.setTransferTime((videoTempTmp.getTransferTime() != null)
            ? videoTempTmp.getTransferTime() : null);
        videoTempDTO2.setType((videoTempTmp.getType() != null)
            ? videoTempTmp.getType() : null);
        videoTempDTO2.setUpdatedAt(videoTempTmp.getUpdatedAt());
        videoTempDTO2.setUpdatedBy((videoTempTmp.getUpdatedBy() != null)
            ? videoTempTmp.getUpdatedBy() : null);
        videoTempDTO2.setUrl((videoTempTmp.getUrl() != null)
            ? videoTempTmp.getUrl() : null);
        videoTempDTO2.setVideoData((videoTempTmp.getVideoData() != null)
            ? videoTempTmp.getVideoData() : null);
        videoTempDTO2.setId_Users((videoTempTmp.getUsers().getId() != null)
            ? videoTempTmp.getUsers().getId() : null);
    	
    }

    @Transactional(readOnly = true)
    public VideoTemp getVideoTemp(Long id) throws Exception {
        log.debug("getting VideoTemp instance");

        VideoTemp entity = null;

        try {
            entity = videoTempDAO.findById(id);
        } catch (Exception e) {
            log.error("get VideoTemp failed", e);
            throw new ZMessManager().new FindingException("VideoTemp");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VideoTemp> findPageVideoTemp(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VideoTemp> entity = null;

        try {
            entity = videoTempDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VideoTemp Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVideoTemp() throws Exception {
        Long entity = null;

        try {
            entity = videoTempDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VideoTemp Count");
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
    public List<VideoTemp> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VideoTemp> list = new ArrayList<VideoTemp>();
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
            list = videoTempDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
