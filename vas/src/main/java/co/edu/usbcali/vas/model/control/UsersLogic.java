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

import co.edu.usbcali.vas.dataaccess.dao.IDocumentDAO;
import co.edu.usbcali.vas.dataaccess.dao.IMessageBoxDAO;
import co.edu.usbcali.vas.dataaccess.dao.ITicketDAO;
import co.edu.usbcali.vas.dataaccess.dao.IUsersDAO;
import co.edu.usbcali.vas.dataaccess.dao.IVideoDAO;
import co.edu.usbcali.vas.exceptions.ZMessManager;
import co.edu.usbcali.vas.model.Document;
import co.edu.usbcali.vas.model.MessageBox;
import co.edu.usbcali.vas.model.Ticket;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.dto.SystemLogDTO;
import co.edu.usbcali.vas.model.dto.UsersDTO;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("UsersLogic")
public class UsersLogic implements IUsersLogic {
    private static final Logger log = LoggerFactory.getLogger(UsersLogic.class);

    /**
     * DAO injected by Spring that manages Users entities
     *
     */
    @Autowired
    private IUsersDAO usersDAO;

    /**
    * DAO injected by Spring that manages Document entities
    *
    */
    @Autowired
    private IDocumentDAO documentDAO;

    /**
    * DAO injected by Spring that manages MessageBox entities
    *
    */
    @Autowired
    private IMessageBoxDAO messageBoxDAO;

    /**
    * DAO injected by Spring that manages Ticket entities
    *
    */
    @Autowired
    private ITicketDAO ticketDAO;

    /**
    * DAO injected by Spring that manages Video entities
    *
    */
    @Autowired
    private IVideoDAO videoDAO;

    /**
    * Logic injected by Spring that manages UserType entities
    *
    */
    @Autowired
    IUserTypeLogic logicUserType1;
    
    @Autowired
    private ISystemLogLogic systemLogLogic;

    @Transactional(readOnly = true)
    public List<Users> getUsers() throws Exception {
        log.debug("finding all Users instances");

        List<Users> list = new ArrayList<Users>();

        try {
            list = usersDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Users failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Users");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUsers(Users entity) throws Exception {
        log.debug("saving Users instance");

        try {

        	 if (entity.getUserType() == null) {
                 throw new ZMessManager().new ForeignException("userType");
             }

             if (entity.getActive() == null) {
                 throw new ZMessManager().new EmptyFieldException("active");
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


             if (entity.getFirstname() == null) {
                 throw new ZMessManager().new EmptyFieldException("firstname");
             }

             if ((entity.getFirstname() != null) &&
                     (Utilities.checkWordAndCheckWithlength(
                         entity.getFirstname(), 255) == false)) {
                 throw new ZMessManager().new NotValidFormatException(
                     "firstname");
             }

             if (entity.getId() == null) {
                 throw new ZMessManager().new EmptyFieldException("id");
             }

             if ((entity.getLastname() != null) &&
                     (Utilities.checkWordAndCheckWithlength(
                         entity.getLastname(), 255) == false)) {
                 throw new ZMessManager().new NotValidFormatException("lastname");
             }

             if (entity.getLogin() == null) {
                 throw new ZMessManager().new EmptyFieldException("login");
             }

             if ((entity.getLogin() != null) &&
                     (Utilities.checkWordAndCheckWithlength(entity.getLogin(),
                         255) == false)) {
                 throw new ZMessManager().new NotValidFormatException("login");
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

             if ((entity.getSessionHash() != null) &&
                     (Utilities.checkWordAndCheckWithlength(
                         entity.getSessionHash(), 255) == false)) {
                 throw new ZMessManager().new NotValidFormatException(
                     "sessionHash");
             }

             if ((entity.getToken() != null) &&
                     (Utilities.checkWordAndCheckWithlength(entity.getToken(),
                         100) == false)) {
                 throw new ZMessManager().new NotValidFormatException("token");
             }

             if ((entity.getUpdatedBy() != null) &&
                     (Utilities.checkWordAndCheckWithlength(
                         entity.getUpdatedBy(), 255) == false)) {
                 throw new ZMessManager().new NotValidFormatException(
                     "updatedBy");
             }

             if (entity.getUserType().getId() == null) {
                 throw new ZMessManager().new EmptyFieldException("id_UserType");
             }

             if (getUsers(entity.getId()) != null) {
                 throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
             }

            //SystemLog REST

            usersDAO.save(entity);

            log.debug("save Users successful");
        } catch (Exception e) {
            log.error("save Users failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUsers(Users entity) throws Exception {
        log.debug("deleting Users instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Users");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        List<Document> documents = null;
        List<MessageBox> messageBoxes = null;
        List<Ticket> tickets = null;
        List<Video> videos = null;

        try {
            documents = documentDAO.findByProperty("users.id", entity.getId());

            if (Utilities.validationsList(documents) == true) {
                throw new ZMessManager().new DeletingException("documents");
            }

            messageBoxes = messageBoxDAO.findByProperty("users.id",
                    entity.getId());

            if (Utilities.validationsList(messageBoxes) == true) {
                throw new ZMessManager().new DeletingException("messageBoxes");
            }

            tickets = ticketDAO.findByProperty("users.id", entity.getId());

            if (Utilities.validationsList(tickets) == true) {
                throw new ZMessManager().new DeletingException("tickets");
            }

            videos = videoDAO.findByProperty("users.id", entity.getId());

            if (Utilities.validationsList(videos) == true) {
                throw new ZMessManager().new DeletingException("videos");
            }

            usersDAO.delete(entity);

            log.debug("delete Users successful");
        } catch (Exception e) {
            log.error("delete Users failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUsers(Users entity) throws Exception {
        log.debug("updating Users instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Users");
            }

            if (entity.getUserType() == null) {
                throw new ZMessManager().new ForeignException("userType");
            }

            if (entity.getActive() == null) {
                throw new ZMessManager().new EmptyFieldException("active");
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

            if (entity.getFirstname() == null) {
                throw new ZMessManager().new EmptyFieldException("firstname");
            }

            if ((entity.getFirstname() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getFirstname(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "firstname");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if ((entity.getLastname() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getLastname(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("lastname");
            }

            if (entity.getLogin() == null) {
                throw new ZMessManager().new EmptyFieldException("login");
            }

            if ((entity.getLogin() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLogin(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("login");
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

            if ((entity.getSessionHash() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSessionHash(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "sessionHash");
            }

            if ((entity.getToken() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getToken(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("token");
            }

            if ((entity.getUpdatedBy() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUpdatedBy(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "updatedBy");
            }

            if (entity.getUserType().getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id_UserType");
            }
            
            

            usersDAO.update(entity);
            
            //SystemLog REST
            this.saveSystemLogRest(entity);

            log.debug("update Users successful");
        } catch (Exception e) {
            log.error("update Users failed", e);
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = false)
    public void saveSystemLogRest(Users entity) throws Exception {
        log.debug("VAS saveSystemLogRest");

        if(entity != null) {
        	
        	try {
        		
        		 SystemLogDTO systemLogDTO = new SystemLogDTO();
                 systemLogDTO.setActionDate(new Date());
                 systemLogDTO.setActionName("UPDATE USER");
                 systemLogDTO.setNote(entity.getLogin());
                 systemLogDTO.setUserName(entity.getUpdatedBy());
                 systemLogDTO.setIp(FacesUtils.getIpClient());
                 
                 systemLogLogic.save_systemLogRest(systemLogDTO);
                 
            } catch (Exception e) {
                log.error("saveSystemLogRest failed", e);
            } finally {
            }
        }

        

        
    }

    @Transactional(readOnly = true)
    public List<UsersDTO> getDataUsers() throws Exception {
        try {
            List<Users> users = usersDAO.findAll();

            List<UsersDTO> usersDTO = new ArrayList<UsersDTO>();

            for (Users usersTmp : users) {
                UsersDTO usersDTO2 = new UsersDTO();

                usersDTO2.setId(usersTmp.getId());
                usersDTO2.setActive((usersTmp.getActive() != null)
                    ? usersTmp.getActive() : null);
                usersDTO2.setCreatedAt(usersTmp.getCreatedAt());
                usersDTO2.setCreatedBy((usersTmp.getCreatedBy() != null)
                    ? usersTmp.getCreatedBy() : null);
                usersDTO2.setDeleted((usersTmp.getDeleted() != null)
                    ? usersTmp.getDeleted() : null);
                usersDTO2.setFirstname((usersTmp.getFirstname() != null)
                    ? usersTmp.getFirstname() : null);
                usersDTO2.setLastConnectionDate(usersTmp.getLastConnectionDate());
                usersDTO2.setLastname((usersTmp.getLastname() != null)
                    ? usersTmp.getLastname() : null);
                usersDTO2.setLogin((usersTmp.getLogin() != null)
                    ? usersTmp.getLogin() : null);
                usersDTO2.setNote((usersTmp.getNote() != null)
                    ? usersTmp.getNote() : null);
                usersDTO2.setOnSession((usersTmp.getOnSession() != null)
                    ? usersTmp.getOnSession() : null);
                usersDTO2.setPassword((usersTmp.getPassword() != null)
                    ? usersTmp.getPassword() : null);
                usersDTO2.setPasswordSystemGen((usersTmp.getPasswordSystemGen() != null)
                    ? usersTmp.getPasswordSystemGen() : null);
                usersDTO2.setSessionHash((usersTmp.getSessionHash() != null)
                    ? usersTmp.getSessionHash() : null);
                usersDTO2.setToken((usersTmp.getToken() != null)
                    ? usersTmp.getToken() : null);
                usersDTO2.setUpdatedAt(usersTmp.getUpdatedAt());
                usersDTO2.setUpdatedBy((usersTmp.getUpdatedBy() != null)
                    ? usersTmp.getUpdatedBy() : null);
                usersDTO2.setId_UserType((usersTmp.getUserType().getId() != null)
                    ? usersTmp.getUserType().getId() : null);
                usersDTO.add(usersDTO2);
            }

            return usersDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Users getUsers(Integer id) throws Exception {
        log.debug("getting Users instance");

        Users entity = null;

        try {
            entity = usersDAO.findById(id);
        } catch (Exception e) {
            log.error("get Users failed", e);
            throw new ZMessManager().new FindingException("Users");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Users> findPageUsers(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Users> entity = null;

        try {
            entity = usersDAO.findPage(sortColumnName, sortAscending, startRow,
                    maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Users Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberUsers() throws Exception {
        Long entity = null;

        try {
            entity = usersDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Users Count");
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
    public List<Users> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Users> list = new ArrayList<Users>();
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
            list = usersDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
