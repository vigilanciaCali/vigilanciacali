package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.dataaccess.dao.*;
import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.DeviceDTO;
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
@Service("DeviceLogic")
public class DeviceLogic implements IDeviceLogic {
    private static final Logger log = LoggerFactory.getLogger(DeviceLogic.class);

    /**
     * DAO injected by Spring that manages Device entities
     *
     */
    @Autowired
    private IDeviceDAO deviceDAO;

    @Transactional(readOnly = true)
    public List<Device> getDevice() throws Exception {
        log.debug("finding all Device instances");

        List<Device> list = new ArrayList<Device>();

        try {
            list = deviceDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Device failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Device");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveDevice(Device entity) throws Exception {
        log.debug("saving Device instance");

        try {
            if (entity.getActive() == null) {
                throw new ZMessManager().new EmptyFieldException("active");
            }

            if ((entity.getActive() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActive(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("active");
            }

            if ((entity.getChannelAmount() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getChannelAmount(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "channelAmount");
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

            if (entity.getIpAddress() == null) {
                throw new ZMessManager().new EmptyFieldException("ipAddress");
            }

            if ((entity.getIpAddress() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getIpAddress(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ipAddress");
            }

            if ((entity.getLocation() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLocation(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("location");
            }

            if (entity.getLogin() == null) {
                throw new ZMessManager().new EmptyFieldException("login");
            }

            if ((entity.getLogin() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLogin(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("login");
            }

            if ((entity.getManufacturer() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getManufacturer(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "manufacturer");
            }

            if ((entity.getModel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getModel(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("model");
            }

            if ((entity.getNote() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNote(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("note");
            }

            if (entity.getPassword() == null) {
                throw new ZMessManager().new EmptyFieldException("password");
            }

            if ((entity.getPassword() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPassword(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("password");
            }

            if (entity.getPort() == null) {
                throw new ZMessManager().new EmptyFieldException("port");
            }

            if ((entity.getSerialNumber() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSerialNumber(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "serialNumber");
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

            if (getDevice(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            deviceDAO.save(entity);

            log.debug("save Device successful");
        } catch (Exception e) {
            log.error("save Device failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteDevice(Device entity) throws Exception {
        log.debug("deleting Device instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Device");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        try {
            deviceDAO.delete(entity);

            log.debug("delete Device successful");
        } catch (Exception e) {
            log.error("delete Device failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateDevice(Device entity) throws Exception {
        log.debug("updating Device instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Device");
            }

            if (entity.getActive() == null) {
                throw new ZMessManager().new EmptyFieldException("active");
            }

            if ((entity.getActive() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActive(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("active");
            }

            if ((entity.getChannelAmount() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getChannelAmount(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "channelAmount");
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

            if (entity.getIpAddress() == null) {
                throw new ZMessManager().new EmptyFieldException("ipAddress");
            }

            if ((entity.getIpAddress() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getIpAddress(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "ipAddress");
            }

            if ((entity.getLocation() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLocation(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("location");
            }

            if (entity.getLogin() == null) {
                throw new ZMessManager().new EmptyFieldException("login");
            }

            if ((entity.getLogin() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLogin(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("login");
            }

            if ((entity.getManufacturer() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getManufacturer(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "manufacturer");
            }

            if ((entity.getModel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getModel(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("model");
            }

            if ((entity.getNote() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNote(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("note");
            }

            if (entity.getPassword() == null) {
                throw new ZMessManager().new EmptyFieldException("password");
            }

            if ((entity.getPassword() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPassword(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("password");
            }

            if (entity.getPort() == null) {
                throw new ZMessManager().new EmptyFieldException("port");
            }

            if ((entity.getSerialNumber() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSerialNumber(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "serialNumber");
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

            deviceDAO.update(entity);

            log.debug("update Device successful");
        } catch (Exception e) {
            log.error("update Device failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<DeviceDTO> getDataDevice() throws Exception {
        try {
            List<Device> device = deviceDAO.findAll();

            List<DeviceDTO> deviceDTO = new ArrayList<DeviceDTO>();

            for (Device deviceTmp : device) {
                DeviceDTO deviceDTO2 = new DeviceDTO();

                deviceDTO2.setId(deviceTmp.getId());
                deviceDTO2.setActive((deviceTmp.getActive() != null)
                    ? deviceTmp.getActive() : null);
                deviceDTO2.setChannelAmount((deviceTmp.getChannelAmount() != null)
                    ? deviceTmp.getChannelAmount() : null);
                deviceDTO2.setCreatedAt(deviceTmp.getCreatedAt());
                deviceDTO2.setCreatedBy((deviceTmp.getCreatedBy() != null)
                    ? deviceTmp.getCreatedBy() : null);
                deviceDTO2.setDescription((deviceTmp.getDescription() != null)
                    ? deviceTmp.getDescription() : null);
                deviceDTO2.setIpAddress((deviceTmp.getIpAddress() != null)
                    ? deviceTmp.getIpAddress() : null);
                deviceDTO2.setLocation((deviceTmp.getLocation() != null)
                    ? deviceTmp.getLocation() : null);
                deviceDTO2.setLogin((deviceTmp.getLogin() != null)
                    ? deviceTmp.getLogin() : null);
                deviceDTO2.setManufacturer((deviceTmp.getManufacturer() != null)
                    ? deviceTmp.getManufacturer() : null);
                deviceDTO2.setModel((deviceTmp.getModel() != null)
                    ? deviceTmp.getModel() : null);
                deviceDTO2.setNote((deviceTmp.getNote() != null)
                    ? deviceTmp.getNote() : null);
                deviceDTO2.setPassword((deviceTmp.getPassword() != null)
                    ? deviceTmp.getPassword() : null);
                deviceDTO2.setPort((deviceTmp.getPort() != null)
                    ? deviceTmp.getPort() : null);
                deviceDTO2.setSerialNumber((deviceTmp.getSerialNumber() != null)
                    ? deviceTmp.getSerialNumber() : null);
                deviceDTO2.setType((deviceTmp.getType() != null)
                    ? deviceTmp.getType() : null);
                deviceDTO2.setUpdatedAt(deviceTmp.getUpdatedAt());
                deviceDTO2.setUpdatedBy((deviceTmp.getUpdatedBy() != null)
                    ? deviceTmp.getUpdatedBy() : null);
                deviceDTO.add(deviceDTO2);
            }

            return deviceDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Device getDevice(Integer id) throws Exception {
        log.debug("getting Device instance");

        Device entity = null;

        try {
            entity = deviceDAO.findById(id);
        } catch (Exception e) {
            log.error("get Device failed", e);
            throw new ZMessManager().new FindingException("Device");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Device> findPageDevice(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Device> entity = null;

        try {
            entity = deviceDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Device Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberDevice() throws Exception {
        Long entity = null;

        try {
            entity = deviceDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Device Count");
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
    public List<Device> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Device> list = new ArrayList<Device>();
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
            list = deviceDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
