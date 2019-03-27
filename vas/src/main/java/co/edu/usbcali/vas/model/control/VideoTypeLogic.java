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

import co.edu.usbcali.vas.dataaccess.dao.IVideoDAO;
import co.edu.usbcali.vas.dataaccess.dao.IVideoTypeDAO;
import co.edu.usbcali.vas.exceptions.ZMessManager;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoType;
import co.edu.usbcali.vas.model.dto.VideoTypeDTO;
import co.edu.usbcali.vas.utilities.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("VideoTypeLogic")
public class VideoTypeLogic implements IVideoTypeLogic {
    private static final Logger log = LoggerFactory.getLogger(VideoTypeLogic.class);

    /**
     * DAO injected by Spring that manages VideoType entities
     *
     */
    @Autowired
    private IVideoTypeDAO videoTypeDAO;

    /**
    * DAO injected by Spring that manages Video entities
    *
    */
    @Autowired
    private IVideoDAO videoDAO;

    @Transactional(readOnly = true)
    public List<VideoType> getVideoType() throws Exception {
        log.debug("finding all VideoType instances");

        List<VideoType> list = new ArrayList<VideoType>();

        try {
            list = videoTypeDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VideoType failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VideoType");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVideoType(VideoType entity) throws Exception {
        log.debug("saving VideoType instance");

        try {
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

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (getVideoType(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            videoTypeDAO.save(entity);

            log.debug("save VideoType successful");
        } catch (Exception e) {
            log.error("save VideoType failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVideoType(VideoType entity) throws Exception {
        log.debug("deleting VideoType instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VideoType");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        List<Video> videos = null;

        try {
            videos = videoDAO.findByProperty("videoType.id", entity.getId());

            if (Utilities.validationsList(videos) == true) {
                throw new ZMessManager().new DeletingException("videos");
            }

            videoTypeDAO.delete(entity);

            log.debug("delete VideoType successful");
        } catch (Exception e) {
            log.error("delete VideoType failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVideoType(VideoType entity) throws Exception {
        log.debug("updating VideoType instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VideoType");
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

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            videoTypeDAO.update(entity);

            log.debug("update VideoType successful");
        } catch (Exception e) {
            log.error("update VideoType failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VideoTypeDTO> getDataVideoType() throws Exception {
        try {
            List<VideoType> videoType = videoTypeDAO.findAll();

            List<VideoTypeDTO> videoTypeDTO = new ArrayList<VideoTypeDTO>();

            for (VideoType videoTypeTmp : videoType) {
                VideoTypeDTO videoTypeDTO2 = new VideoTypeDTO();

                videoTypeDTO2.setId(videoTypeTmp.getId());
                videoTypeDTO2.setCode((videoTypeTmp.getCode() != null)
                    ? videoTypeTmp.getCode() : null);
                videoTypeDTO2.setCreatedAt(videoTypeTmp.getCreatedAt());
                videoTypeDTO2.setCreatedBy((videoTypeTmp.getCreatedBy() != null)
                    ? videoTypeTmp.getCreatedBy() : null);
                videoTypeDTO2.setDescription((videoTypeTmp.getDescription() != null)
                    ? videoTypeTmp.getDescription() : null);
                videoTypeDTO2.setUpdatedAt(videoTypeTmp.getUpdatedAt());
                videoTypeDTO2.setUpdatedBy((videoTypeTmp.getUpdatedBy() != null)
                    ? videoTypeTmp.getUpdatedBy() : null);
                videoTypeDTO.add(videoTypeDTO2);
            }

            return videoTypeDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VideoType getVideoType(Integer id) throws Exception {
        log.debug("getting VideoType instance");

        VideoType entity = null;

        try {
            entity = videoTypeDAO.findById(id);
        } catch (Exception e) {
            log.error("get VideoType failed", e);
            throw new ZMessManager().new FindingException("VideoType");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VideoType> findPageVideoType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VideoType> entity = null;

        try {
            entity = videoTypeDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VideoType Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVideoType() throws Exception {
        Long entity = null;

        try {
            entity = videoTypeDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VideoType Count");
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
    public List<VideoType> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VideoType> list = new ArrayList<VideoType>();
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
            list = videoTypeDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Override
	@Transactional(readOnly = true)
	public VideoType getVideoTypeByCode(String code) throws Exception {
		log.info("VideoTypeLogic getVideoTypeByCode");
		VideoType entity = null;
		try {
			entity = (VideoType) videoTypeDAO.getVideoTypeByCode(code.trim());
		} catch (Exception e) {
			log.error("El parametro : " + code + " No existe en la base de datos", e);
		}
		log.info("VideoTypeLogic getVideoTypeByCode: "+entity.getDescription());
		return entity;
		
	}
    
    
    
}
