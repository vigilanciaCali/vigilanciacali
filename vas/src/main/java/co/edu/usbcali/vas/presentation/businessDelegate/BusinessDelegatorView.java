package co.edu.usbcali.vas.presentation.businessDelegate;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.vas.security.control.ISecurityLogic;
import co.edu.usbcali.vas.video.control.IVideoAnalysisLogic;
import co.edu.usbcali.vas.video.control.IVideoAnalysisStatusLogic;
import co.edu.usbcali.vas.video.control.rest.IMonitorLogicRest;
import co.edu.usbcali.vas.video.control.rest.IVideoAnalysisLogicRest;
import co.edu.usbcali.vas.model.CronJob;
import co.edu.usbcali.vas.model.CronJobMonitoring;
import co.edu.usbcali.vas.model.Device;
import co.edu.usbcali.vas.model.DeviceLog;
import co.edu.usbcali.vas.model.Document;
import co.edu.usbcali.vas.model.MailServer;
import co.edu.usbcali.vas.model.MailTemplate;
import co.edu.usbcali.vas.model.MessageBox;
import co.edu.usbcali.vas.model.News;
import co.edu.usbcali.vas.model.SystemCompanyParameter;
import co.edu.usbcali.vas.model.SystemConfig;
import co.edu.usbcali.vas.model.SystemCronLog;
import co.edu.usbcali.vas.model.SystemLog;
import co.edu.usbcali.vas.model.SystemMailLog;
import co.edu.usbcali.vas.model.SystemMonitoringLog;
import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.model.SystemRestLog;
import co.edu.usbcali.vas.model.SystemVideoLog;
import co.edu.usbcali.vas.model.Ticket;
import co.edu.usbcali.vas.model.TicketType;
import co.edu.usbcali.vas.model.UserType;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoDocument;
import co.edu.usbcali.vas.model.VideoTemp;
import co.edu.usbcali.vas.model.control.ICronJobLogic;
import co.edu.usbcali.vas.model.control.ICronJobMonitoringLogic;
import co.edu.usbcali.vas.model.control.IDeviceLogLogic;
import co.edu.usbcali.vas.model.control.IDeviceLogic;
import co.edu.usbcali.vas.model.control.IDocumentLogic;
import co.edu.usbcali.vas.model.control.IMailServerLogic;
import co.edu.usbcali.vas.model.control.IMailTemplateLogic;
import co.edu.usbcali.vas.model.control.IMessageBoxLogic;
import co.edu.usbcali.vas.model.control.INewsLogic;
import co.edu.usbcali.vas.model.control.ISystemCompanyParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemConfigLogic;
import co.edu.usbcali.vas.model.control.ISystemCronLogLogic;
import co.edu.usbcali.vas.model.control.ISystemLogLogic;
import co.edu.usbcali.vas.model.control.ISystemMailLogLogic;
import co.edu.usbcali.vas.model.control.ISystemMonitoringLogLogic;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemRestLogLogic;
import co.edu.usbcali.vas.model.control.ISystemVideoLogLogic;
import co.edu.usbcali.vas.model.control.ITicketLogic;
import co.edu.usbcali.vas.model.control.ITicketTypeLogic;
import co.edu.usbcali.vas.model.control.IUserTypeLogic;
import co.edu.usbcali.vas.model.control.IUsersLogic;
import co.edu.usbcali.vas.model.control.IVideoDocumentLogic;
import co.edu.usbcali.vas.model.control.IVideoLogic;
import co.edu.usbcali.vas.model.control.IVideoTempLogic;
import co.edu.usbcali.vas.model.dto.CronJobDTO;
import co.edu.usbcali.vas.model.dto.CronJobMonitoringDTO;
import co.edu.usbcali.vas.model.dto.DeviceDTO;
import co.edu.usbcali.vas.model.dto.DeviceLogDTO;
import co.edu.usbcali.vas.model.dto.DocumentDTO;
import co.edu.usbcali.vas.model.dto.MailServerDTO;
import co.edu.usbcali.vas.model.dto.MailTemplateDTO;
import co.edu.usbcali.vas.model.dto.MessageBoxDTO;
import co.edu.usbcali.vas.model.dto.NewsDTO;
import co.edu.usbcali.vas.model.dto.SystemCompanyParameterDTO;
import co.edu.usbcali.vas.model.dto.SystemConfigDTO;
import co.edu.usbcali.vas.model.dto.SystemCronLogDTO;
import co.edu.usbcali.vas.model.dto.SystemLogDTO;
import co.edu.usbcali.vas.model.dto.SystemMailLogDTO;
import co.edu.usbcali.vas.model.dto.SystemMonitoringLogDTO;
import co.edu.usbcali.vas.model.dto.SystemParameterDTO;
import co.edu.usbcali.vas.model.dto.SystemRestLogDTO;
import co.edu.usbcali.vas.model.dto.SystemVideoLogDTO;
import co.edu.usbcali.vas.model.dto.TicketDTO;
import co.edu.usbcali.vas.model.dto.TicketTypeDTO;
import co.edu.usbcali.vas.model.dto.UserTypeDTO;
import co.edu.usbcali.vas.model.dto.UsersDTO;
import co.edu.usbcali.vas.model.dto.VideoDTO;
import co.edu.usbcali.vas.model.dto.VideoDocumentDTO;
import co.edu.usbcali.vas.model.dto.VideoTempDTO;

@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    
    @Autowired
    private ISystemParameterLogic systemParameterLogic;
    @Autowired
    private ITicketLogic ticketLogic;
    @Autowired
    private ISystemCronLogLogic systemCronLogLogic;
    @Autowired
    private ISystemMonitoringLogLogic systemMonitoringLogLogic;
    @Autowired
    private IDeviceLogic deviceLogic;
    @Autowired
    private ISystemRestLogLogic systemRestLogLogic;
    @Autowired
    private IDocumentLogic documentLogic;
    @Autowired
    private IUsersLogic usersLogic;
    @Autowired
    private ICronJobLogic cronJobLogic;
    @Autowired
    private IMailTemplateLogic mailTemplateLogic;
    @Autowired
    private ISystemConfigLogic systemConfigLogic;
    @Autowired
    private IMessageBoxLogic messageBoxLogic;
    @Autowired
    private IDeviceLogLogic deviceLogLogic;
    @Autowired
    private IVideoLogic videoLogic;
    @Autowired
    private ISystemCompanyParameterLogic systemCompanyParameterLogic;
    @Autowired
    private IUserTypeLogic userTypeLogic;
    @Autowired
    private IMailServerLogic mailServerLogic;
    @Autowired
    private ISystemMailLogLogic systemMailLogLogic;
    @Autowired
    private ICronJobMonitoringLogic cronJobMonitoringLogic;
    @Autowired
    private IVideoDocumentLogic videoDocumentLogic;
    @Autowired
    private ISystemVideoLogLogic systemVideoLogLogic;
    @Autowired
    private INewsLogic newsLogic;
    @Autowired
    private ITicketTypeLogic ticketTypeLogic;
    @Autowired
    private ISystemLogLogic systemLogLogic;
    @Autowired
    private ISecurityLogic securityLogic;
    @Autowired
    private IVideoAnalysisLogic videoAnalysisLogic;
    @Autowired
    private IVideoTempLogic videoTempLogic;
    @Autowired
    private IVideoAnalysisStatusLogic videoAnalysisStatusLogic;
    @Autowired
    private IMonitorLogicRest monitorLogicRest;
    @Autowired
    private IVideoAnalysisLogicRest videoAnalysisLogicRest;

    public List<SystemParameter> getSystemParameter() throws Exception {
        return systemParameterLogic.getSystemParameter();
    }

    public void saveSystemParameter(SystemParameter entity)
        throws Exception {
        systemParameterLogic.saveSystemParameter(entity);
    }

    public void deleteSystemParameter(SystemParameter entity)
        throws Exception {
        systemParameterLogic.deleteSystemParameter(entity);
    }

    public void updateSystemParameter(SystemParameter entity)
        throws Exception {
        systemParameterLogic.updateSystemParameter(entity);
    }

    public SystemParameter getSystemParameter(Integer id)
        throws Exception {
        SystemParameter systemParameter = null;

        try {
            systemParameter = systemParameterLogic.getSystemParameter(id);
        } catch (Exception e) {
            throw e;
        }

        return systemParameter;
    }

    public List<SystemParameter> findByCriteriaInSystemParameter(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemParameterLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemParameter> findPageSystemParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return systemParameterLogic.findPageSystemParameter(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemParameter() throws Exception {
        return systemParameterLogic.findTotalNumberSystemParameter();
    }

    public List<SystemParameterDTO> getDataSystemParameter()
        throws Exception {
        return systemParameterLogic.getDataSystemParameter();
    }

    public List<Ticket> getTicket() throws Exception {
        return ticketLogic.getTicket();
    }

    public void saveTicket(Ticket entity) throws Exception {
        ticketLogic.saveTicket(entity);
    }

    public void deleteTicket(Ticket entity) throws Exception {
        ticketLogic.deleteTicket(entity);
    }

    public void updateTicket(Ticket entity) throws Exception {
        ticketLogic.updateTicket(entity);
    }

    public Ticket getTicket(Long id) throws Exception {
        Ticket ticket = null;

        try {
            ticket = ticketLogic.getTicket(id);
        } catch (Exception e) {
            throw e;
        }

        return ticket;
    }

    public List<Ticket> findByCriteriaInTicket(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return ticketLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Ticket> findPageTicket(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return ticketLogic.findPageTicket(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberTicket() throws Exception {
        return ticketLogic.findTotalNumberTicket();
    }

    public List<TicketDTO> getDataTicket() throws Exception {
        return ticketLogic.getDataTicket();
    }

    public List<SystemCronLog> getSystemCronLog() throws Exception {
        return systemCronLogLogic.getSystemCronLog();
    }

    public void saveSystemCronLog(SystemCronLog entity)
        throws Exception {
        systemCronLogLogic.saveSystemCronLog(entity);
    }

    public void deleteSystemCronLog(SystemCronLog entity)
        throws Exception {
        systemCronLogLogic.deleteSystemCronLog(entity);
    }

    public void updateSystemCronLog(SystemCronLog entity)
        throws Exception {
        systemCronLogLogic.updateSystemCronLog(entity);
    }

    public SystemCronLog getSystemCronLog(Long id) throws Exception {
        SystemCronLog systemCronLog = null;

        try {
            systemCronLog = systemCronLogLogic.getSystemCronLog(id);
        } catch (Exception e) {
            throw e;
        }

        return systemCronLog;
    }

    public List<SystemCronLog> findByCriteriaInSystemCronLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemCronLogLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemCronLog> findPageSystemCronLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return systemCronLogLogic.findPageSystemCronLog(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemCronLog() throws Exception {
        return systemCronLogLogic.findTotalNumberSystemCronLog();
    }

    public List<SystemCronLogDTO> getDataSystemCronLog()
        throws Exception {
        return systemCronLogLogic.getDataSystemCronLog();
    }

    public List<SystemMonitoringLog> getSystemMonitoringLog()
        throws Exception {
        return systemMonitoringLogLogic.getSystemMonitoringLog();
    }

    public void saveSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception {
        systemMonitoringLogLogic.saveSystemMonitoringLog(entity);
    }

    public void deleteSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception {
        systemMonitoringLogLogic.deleteSystemMonitoringLog(entity);
    }

    public void updateSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception {
        systemMonitoringLogLogic.updateSystemMonitoringLog(entity);
    }

    public SystemMonitoringLog getSystemMonitoringLog(Long id)
        throws Exception {
        SystemMonitoringLog systemMonitoringLog = null;

        try {
            systemMonitoringLog = systemMonitoringLogLogic.getSystemMonitoringLog(id);
        } catch (Exception e) {
            throw e;
        }

        return systemMonitoringLog;
    }

    public List<SystemMonitoringLog> findByCriteriaInSystemMonitoringLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemMonitoringLogLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<SystemMonitoringLog> findPageSystemMonitoringLog(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return systemMonitoringLogLogic.findPageSystemMonitoringLog(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemMonitoringLog() throws Exception {
        return systemMonitoringLogLogic.findTotalNumberSystemMonitoringLog();
    }

    public List<SystemMonitoringLogDTO> getDataSystemMonitoringLog()
        throws Exception {
        return systemMonitoringLogLogic.getDataSystemMonitoringLog();
    }

    public List<Device> getDevice() throws Exception {
        return deviceLogic.getDevice();
    }

    public void saveDevice(Device entity) throws Exception {
        deviceLogic.saveDevice(entity);
    }

    public void deleteDevice(Device entity) throws Exception {
        deviceLogic.deleteDevice(entity);
    }

    public void updateDevice(Device entity) throws Exception {
        deviceLogic.updateDevice(entity);
    }

    public Device getDevice(Integer id) throws Exception {
        Device device = null;

        try {
            device = deviceLogic.getDevice(id);
        } catch (Exception e) {
            throw e;
        }

        return device;
    }

    public List<Device> findByCriteriaInDevice(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return deviceLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Device> findPageDevice(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return deviceLogic.findPageDevice(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberDevice() throws Exception {
        return deviceLogic.findTotalNumberDevice();
    }

    public List<DeviceDTO> getDataDevice() throws Exception {
        return deviceLogic.getDataDevice();
    }

    public List<SystemRestLog> getSystemRestLog() throws Exception {
        return systemRestLogLogic.getSystemRestLog();
    }

    public void saveSystemRestLog(SystemRestLog entity)
        throws Exception {
        systemRestLogLogic.saveSystemRestLog(entity);
    }

    public void deleteSystemRestLog(SystemRestLog entity)
        throws Exception {
        systemRestLogLogic.deleteSystemRestLog(entity);
    }

    public void updateSystemRestLog(SystemRestLog entity)
        throws Exception {
        systemRestLogLogic.updateSystemRestLog(entity);
    }

    public SystemRestLog getSystemRestLog(Long id) throws Exception {
        SystemRestLog systemRestLog = null;

        try {
            systemRestLog = systemRestLogLogic.getSystemRestLog(id);
        } catch (Exception e) {
            throw e;
        }

        return systemRestLog;
    }

    public List<SystemRestLog> findByCriteriaInSystemRestLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemRestLogLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemRestLog> findPageSystemRestLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return systemRestLogLogic.findPageSystemRestLog(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemRestLog() throws Exception {
        return systemRestLogLogic.findTotalNumberSystemRestLog();
    }

    public List<SystemRestLogDTO> getDataSystemRestLog()
        throws Exception {
        return systemRestLogLogic.getDataSystemRestLog();
    }

    public List<Document> getDocument() throws Exception {
        return documentLogic.getDocument();
    }

    public void saveDocument(Document entity) throws Exception {
        documentLogic.saveDocument(entity);
    }

    public void deleteDocument(Document entity) throws Exception {
        documentLogic.deleteDocument(entity);
    }

    public void updateDocument(Document entity) throws Exception {
        documentLogic.updateDocument(entity);
    }

    public Document getDocument(Long id) throws Exception {
        Document document = null;

        try {
            document = documentLogic.getDocument(id);
        } catch (Exception e) {
            throw e;
        }

        return document;
    }

    public List<Document> findByCriteriaInDocument(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return documentLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Document> findPageDocument(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return documentLogic.findPageDocument(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberDocument() throws Exception {
        return documentLogic.findTotalNumberDocument();
    }

    public List<DocumentDTO> getDataDocument() throws Exception {
        return documentLogic.getDataDocument();
    }

    public List<Users> getUsers() throws Exception {
        return usersLogic.getUsers();
    }

    public void saveUsers(Users entity) throws Exception {
        usersLogic.saveUsers(entity);
    }

    public void deleteUsers(Users entity) throws Exception {
        usersLogic.deleteUsers(entity);
    }

    public void updateUsers(Users entity) throws Exception {
        usersLogic.updateUsers(entity);
    }

    public Users getUsers(Integer id) throws Exception {
        Users users = null;

        try {
            users = usersLogic.getUsers(id);
        } catch (Exception e) {
            throw e;
        }

        return users;
    }

    public List<Users> findByCriteriaInUsers(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usersLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Users> findPageUsers(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usersLogic.findPageUsers(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsers() throws Exception {
        return usersLogic.findTotalNumberUsers();
    }

    public List<UsersDTO> getDataUsers() throws Exception {
        return usersLogic.getDataUsers();
    }

    public List<CronJob> getCronJob() throws Exception {
        return cronJobLogic.getCronJob();
    }

    public void saveCronJob(CronJob entity) throws Exception {
        cronJobLogic.saveCronJob(entity);
    }

    public void deleteCronJob(CronJob entity) throws Exception {
        cronJobLogic.deleteCronJob(entity);
    }

    public void updateCronJob(CronJob entity) throws Exception {
        cronJobLogic.updateCronJob(entity);
    }

    public CronJob getCronJob(Integer id) throws Exception {
        CronJob cronJob = null;

        try {
            cronJob = cronJobLogic.getCronJob(id);
        } catch (Exception e) {
            throw e;
        }

        return cronJob;
    }

    public List<CronJob> findByCriteriaInCronJob(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return cronJobLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<CronJob> findPageCronJob(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return cronJobLogic.findPageCronJob(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCronJob() throws Exception {
        return cronJobLogic.findTotalNumberCronJob();
    }

    public List<CronJobDTO> getDataCronJob() throws Exception {
        return cronJobLogic.getDataCronJob();
    }

    public List<MailTemplate> getMailTemplate() throws Exception {
        return mailTemplateLogic.getMailTemplate();
    }

    public void saveMailTemplate(MailTemplate entity) throws Exception {
        mailTemplateLogic.saveMailTemplate(entity);
    }

    public void deleteMailTemplate(MailTemplate entity)
        throws Exception {
        mailTemplateLogic.deleteMailTemplate(entity);
    }

    public void updateMailTemplate(MailTemplate entity)
        throws Exception {
        mailTemplateLogic.updateMailTemplate(entity);
    }

    public MailTemplate getMailTemplate(Integer id) throws Exception {
        MailTemplate mailTemplate = null;

        try {
            mailTemplate = mailTemplateLogic.getMailTemplate(id);
        } catch (Exception e) {
            throw e;
        }

        return mailTemplate;
    }

    public List<MailTemplate> findByCriteriaInMailTemplate(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return mailTemplateLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MailTemplate> findPageMailTemplate(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return mailTemplateLogic.findPageMailTemplate(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMailTemplate() throws Exception {
        return mailTemplateLogic.findTotalNumberMailTemplate();
    }

    public List<MailTemplateDTO> getDataMailTemplate()
        throws Exception {
        return mailTemplateLogic.getDataMailTemplate();
    }

    public List<SystemConfig> getSystemConfig() throws Exception {
        return systemConfigLogic.getSystemConfig();
    }

    public void saveSystemConfig(SystemConfig entity) throws Exception {
        systemConfigLogic.saveSystemConfig(entity);
    }

    public void deleteSystemConfig(SystemConfig entity)
        throws Exception {
        systemConfigLogic.deleteSystemConfig(entity);
    }

    public void updateSystemConfig(SystemConfig entity)
        throws Exception {
        systemConfigLogic.updateSystemConfig(entity);
    }

    public SystemConfig getSystemConfig(Integer id) throws Exception {
        SystemConfig systemConfig = null;

        try {
            systemConfig = systemConfigLogic.getSystemConfig(id);
        } catch (Exception e) {
            throw e;
        }

        return systemConfig;
    }

    public List<SystemConfig> findByCriteriaInSystemConfig(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return systemConfigLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemConfig> findPageSystemConfig(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return systemConfigLogic.findPageSystemConfig(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemConfig() throws Exception {
        return systemConfigLogic.findTotalNumberSystemConfig();
    }

    public List<SystemConfigDTO> getDataSystemConfig()
        throws Exception {
        return systemConfigLogic.getDataSystemConfig();
    }

    public List<MessageBox> getMessageBox() throws Exception {
        return messageBoxLogic.getMessageBox();
    }

    public void saveMessageBox(MessageBox entity) throws Exception {
        messageBoxLogic.saveMessageBox(entity);
    }

    public void deleteMessageBox(MessageBox entity) throws Exception {
        messageBoxLogic.deleteMessageBox(entity);
    }

    public void updateMessageBox(MessageBox entity) throws Exception {
        messageBoxLogic.updateMessageBox(entity);
    }

    public MessageBox getMessageBox(Long id) throws Exception {
        MessageBox messageBox = null;

        try {
            messageBox = messageBoxLogic.getMessageBox(id);
        } catch (Exception e) {
            throw e;
        }

        return messageBox;
    }

    public List<MessageBox> findByCriteriaInMessageBox(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return messageBoxLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MessageBox> findPageMessageBox(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return messageBoxLogic.findPageMessageBox(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMessageBox() throws Exception {
        return messageBoxLogic.findTotalNumberMessageBox();
    }

    public List<MessageBoxDTO> getDataMessageBox() throws Exception {
        return messageBoxLogic.getDataMessageBox();
    }

    public List<DeviceLog> getDeviceLog() throws Exception {
        return deviceLogLogic.getDeviceLog();
    }

    public void saveDeviceLog(DeviceLog entity) throws Exception {
        deviceLogLogic.saveDeviceLog(entity);
    }

    public void deleteDeviceLog(DeviceLog entity) throws Exception {
        deviceLogLogic.deleteDeviceLog(entity);
    }

    public void updateDeviceLog(DeviceLog entity) throws Exception {
        deviceLogLogic.updateDeviceLog(entity);
    }

    public DeviceLog getDeviceLog(Long id) throws Exception {
        DeviceLog deviceLog = null;

        try {
            deviceLog = deviceLogLogic.getDeviceLog(id);
        } catch (Exception e) {
            throw e;
        }

        return deviceLog;
    }

    public List<DeviceLog> findByCriteriaInDeviceLog(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return deviceLogLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<DeviceLog> findPageDeviceLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return deviceLogLogic.findPageDeviceLog(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberDeviceLog() throws Exception {
        return deviceLogLogic.findTotalNumberDeviceLog();
    }

    public List<DeviceLogDTO> getDataDeviceLog() throws Exception {
        return deviceLogLogic.getDataDeviceLog();
    }

    public List<Video> getVideo() throws Exception {
        return videoLogic.getVideo();
    }

    public void saveVideo(Video entity) throws Exception {
        videoLogic.saveVideo(entity);
    }

    public void deleteVideo(Video entity) throws Exception {
        videoLogic.deleteVideo(entity);
    }

    public void updateVideo(Video entity) throws Exception {
        videoLogic.updateVideo(entity);
    }

    public Video getVideo(Long id) throws Exception {
        Video video = null;

        try {
            video = videoLogic.getVideo(id);
        } catch (Exception e) {
            throw e;
        }

        return video;
    }

    public List<Video> findByCriteriaInVideo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return videoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Video> findPageVideo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return videoLogic.findPageVideo(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVideo() throws Exception {
        return videoLogic.findTotalNumberVideo();
    }

    public List<VideoDTO> getDataVideo() throws Exception {
        return videoLogic.getDataVideo();
    }

    public List<SystemCompanyParameter> getSystemCompanyParameter()
        throws Exception {
        return systemCompanyParameterLogic.getSystemCompanyParameter();
    }

    public void saveSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception {
        systemCompanyParameterLogic.saveSystemCompanyParameter(entity);
    }

    public void deleteSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception {
        systemCompanyParameterLogic.deleteSystemCompanyParameter(entity);
    }

    public void updateSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception {
        systemCompanyParameterLogic.updateSystemCompanyParameter(entity);
    }

    public SystemCompanyParameter getSystemCompanyParameter(Integer id)
        throws Exception {
        SystemCompanyParameter systemCompanyParameter = null;

        try {
            systemCompanyParameter = systemCompanyParameterLogic.getSystemCompanyParameter(id);
        } catch (Exception e) {
            throw e;
        }

        return systemCompanyParameter;
    }

    public List<SystemCompanyParameter> findByCriteriaInSystemCompanyParameter(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemCompanyParameterLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<SystemCompanyParameter> findPageSystemCompanyParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return systemCompanyParameterLogic.findPageSystemCompanyParameter(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemCompanyParameter()
        throws Exception {
        return systemCompanyParameterLogic.findTotalNumberSystemCompanyParameter();
    }

    public List<SystemCompanyParameterDTO> getDataSystemCompanyParameter()
        throws Exception {
        return systemCompanyParameterLogic.getDataSystemCompanyParameter();
    }

    public List<UserType> getUserType() throws Exception {
        return userTypeLogic.getUserType();
    }

    public void saveUserType(UserType entity) throws Exception {
        userTypeLogic.saveUserType(entity);
    }

    public void deleteUserType(UserType entity) throws Exception {
        userTypeLogic.deleteUserType(entity);
    }

    public void updateUserType(UserType entity) throws Exception {
        userTypeLogic.updateUserType(entity);
    }

    public UserType getUserType(Integer id) throws Exception {
        UserType userType = null;

        try {
            userType = userTypeLogic.getUserType(id);
        } catch (Exception e) {
            throw e;
        }

        return userType;
    }

    public List<UserType> findByCriteriaInUserType(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return userTypeLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<UserType> findPageUserType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return userTypeLogic.findPageUserType(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUserType() throws Exception {
        return userTypeLogic.findTotalNumberUserType();
    }

    public List<UserTypeDTO> getDataUserType() throws Exception {
        return userTypeLogic.getDataUserType();
    }

    public List<MailServer> getMailServer() throws Exception {
        return mailServerLogic.getMailServer();
    }

    public void saveMailServer(MailServer entity) throws Exception {
        mailServerLogic.saveMailServer(entity);
    }

    public void deleteMailServer(MailServer entity) throws Exception {
        mailServerLogic.deleteMailServer(entity);
    }

    public void updateMailServer(MailServer entity) throws Exception {
        mailServerLogic.updateMailServer(entity);
    }

    public MailServer getMailServer(Integer id) throws Exception {
        MailServer mailServer = null;

        try {
            mailServer = mailServerLogic.getMailServer(id);
        } catch (Exception e) {
            throw e;
        }

        return mailServer;
    }

    public List<MailServer> findByCriteriaInMailServer(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return mailServerLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<MailServer> findPageMailServer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return mailServerLogic.findPageMailServer(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberMailServer() throws Exception {
        return mailServerLogic.findTotalNumberMailServer();
    }

    public List<MailServerDTO> getDataMailServer() throws Exception {
        return mailServerLogic.getDataMailServer();
    }

    public List<SystemMailLog> getSystemMailLog() throws Exception {
        return systemMailLogLogic.getSystemMailLog();
    }

    public void saveSystemMailLog(SystemMailLog entity)
        throws Exception {
        systemMailLogLogic.saveSystemMailLog(entity);
    }

    public void deleteSystemMailLog(SystemMailLog entity)
        throws Exception {
        systemMailLogLogic.deleteSystemMailLog(entity);
    }

    public void updateSystemMailLog(SystemMailLog entity)
        throws Exception {
        systemMailLogLogic.updateSystemMailLog(entity);
    }

    public SystemMailLog getSystemMailLog(Long id) throws Exception {
        SystemMailLog systemMailLog = null;

        try {
            systemMailLog = systemMailLogLogic.getSystemMailLog(id);
        } catch (Exception e) {
            throw e;
        }

        return systemMailLog;
    }

    public List<SystemMailLog> findByCriteriaInSystemMailLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemMailLogLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemMailLog> findPageSystemMailLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return systemMailLogLogic.findPageSystemMailLog(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemMailLog() throws Exception {
        return systemMailLogLogic.findTotalNumberSystemMailLog();
    }

    public List<SystemMailLogDTO> getDataSystemMailLog()
        throws Exception {
        return systemMailLogLogic.getDataSystemMailLog();
    }

    public List<CronJobMonitoring> getCronJobMonitoring()
        throws Exception {
        return cronJobMonitoringLogic.getCronJobMonitoring();
    }

    public void saveCronJobMonitoring(CronJobMonitoring entity)
        throws Exception {
        cronJobMonitoringLogic.saveCronJobMonitoring(entity);
    }

    public void deleteCronJobMonitoring(CronJobMonitoring entity)
        throws Exception {
        cronJobMonitoringLogic.deleteCronJobMonitoring(entity);
    }

    public void updateCronJobMonitoring(CronJobMonitoring entity)
        throws Exception {
        cronJobMonitoringLogic.updateCronJobMonitoring(entity);
    }

    public CronJobMonitoring getCronJobMonitoring(Integer id)
        throws Exception {
        CronJobMonitoring cronJobMonitoring = null;

        try {
            cronJobMonitoring = cronJobMonitoringLogic.getCronJobMonitoring(id);
        } catch (Exception e) {
            throw e;
        }

        return cronJobMonitoring;
    }

    public List<CronJobMonitoring> findByCriteriaInCronJobMonitoring(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return cronJobMonitoringLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<CronJobMonitoring> findPageCronJobMonitoring(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return cronJobMonitoringLogic.findPageCronJobMonitoring(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberCronJobMonitoring() throws Exception {
        return cronJobMonitoringLogic.findTotalNumberCronJobMonitoring();
    }

    public List<CronJobMonitoringDTO> getDataCronJobMonitoring()
        throws Exception {
        return cronJobMonitoringLogic.getDataCronJobMonitoring();
    }

    public List<VideoDocument> getVideoDocument() throws Exception {
        return videoDocumentLogic.getVideoDocument();
    }

    public void saveVideoDocument(VideoDocument entity)
        throws Exception {
        videoDocumentLogic.saveVideoDocument(entity);
    }

    public void deleteVideoDocument(VideoDocument entity)
        throws Exception {
        videoDocumentLogic.deleteVideoDocument(entity);
    }

    public void updateVideoDocument(VideoDocument entity)
        throws Exception {
        videoDocumentLogic.updateVideoDocument(entity);
    }

    public VideoDocument getVideoDocument(Long id) throws Exception {
        VideoDocument videoDocument = null;

        try {
            videoDocument = videoDocumentLogic.getVideoDocument(id);
        } catch (Exception e) {
            throw e;
        }

        return videoDocument;
    }

    public List<VideoDocument> findByCriteriaInVideoDocument(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return videoDocumentLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VideoDocument> findPageVideoDocument(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return videoDocumentLogic.findPageVideoDocument(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVideoDocument() throws Exception {
        return videoDocumentLogic.findTotalNumberVideoDocument();
    }

    public List<VideoDocumentDTO> getDataVideoDocument()
        throws Exception {
        return videoDocumentLogic.getDataVideoDocument();
    }

    public List<SystemVideoLog> getSystemVideoLog() throws Exception {
        return systemVideoLogLogic.getSystemVideoLog();
    }

    public void saveSystemVideoLog(SystemVideoLog entity)
        throws Exception {
        systemVideoLogLogic.saveSystemVideoLog(entity);
    }

    public void deleteSystemVideoLog(SystemVideoLog entity)
        throws Exception {
        systemVideoLogLogic.deleteSystemVideoLog(entity);
    }

    public void updateSystemVideoLog(SystemVideoLog entity)
        throws Exception {
        systemVideoLogLogic.updateSystemVideoLog(entity);
    }

    public SystemVideoLog getSystemVideoLog(Long id) throws Exception {
        SystemVideoLog systemVideoLog = null;

        try {
            systemVideoLog = systemVideoLogLogic.getSystemVideoLog(id);
        } catch (Exception e) {
            throw e;
        }

        return systemVideoLog;
    }

    public List<SystemVideoLog> findByCriteriaInSystemVideoLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return systemVideoLogLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemVideoLog> findPageSystemVideoLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return systemVideoLogLogic.findPageSystemVideoLog(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberSystemVideoLog() throws Exception {
        return systemVideoLogLogic.findTotalNumberSystemVideoLog();
    }

    public List<SystemVideoLogDTO> getDataSystemVideoLog()
        throws Exception {
        return systemVideoLogLogic.getDataSystemVideoLog();
    }

    public List<News> getNews() throws Exception {
        return newsLogic.getNews();
    }

    public void saveNews(News entity) throws Exception {
        newsLogic.saveNews(entity);
    }

    public void deleteNews(News entity) throws Exception {
        newsLogic.deleteNews(entity);
    }

    public void updateNews(News entity) throws Exception {
        newsLogic.updateNews(entity);
    }

    public News getNews(Long id) throws Exception {
        News news = null;

        try {
            news = newsLogic.getNews(id);
        } catch (Exception e) {
            throw e;
        }

        return news;
    }

    public List<News> findByCriteriaInNews(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return newsLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<News> findPageNews(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return newsLogic.findPageNews(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberNews() throws Exception {
        return newsLogic.findTotalNumberNews();
    }

    public List<NewsDTO> getDataNews() throws Exception {
        return newsLogic.getDataNews();
    }

    public List<TicketType> getTicketType() throws Exception {
        return ticketTypeLogic.getTicketType();
    }

    public void saveTicketType(TicketType entity) throws Exception {
        ticketTypeLogic.saveTicketType(entity);
    }

    public void deleteTicketType(TicketType entity) throws Exception {
        ticketTypeLogic.deleteTicketType(entity);
    }

    public void updateTicketType(TicketType entity) throws Exception {
        ticketTypeLogic.updateTicketType(entity);
    }

    public TicketType getTicketType(Integer id) throws Exception {
        TicketType ticketType = null;

        try {
            ticketType = ticketTypeLogic.getTicketType(id);
        } catch (Exception e) {
            throw e;
        }

        return ticketType;
    }

    public List<TicketType> findByCriteriaInTicketType(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return ticketTypeLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TicketType> findPageTicketType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return ticketTypeLogic.findPageTicketType(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTicketType() throws Exception {
        return ticketTypeLogic.findTotalNumberTicketType();
    }

    public List<TicketTypeDTO> getDataTicketType() throws Exception {
        return ticketTypeLogic.getDataTicketType();
    }

    public List<SystemLog> getSystemLog() throws Exception {
        return systemLogLogic.getSystemLog();
    }

    public void saveSystemLog(SystemLog entity) throws Exception {
        systemLogLogic.saveSystemLog(entity);
    }

    public void deleteSystemLog(SystemLog entity) throws Exception {
        systemLogLogic.deleteSystemLog(entity);
    }

    public void updateSystemLog(SystemLog entity) throws Exception {
        systemLogLogic.updateSystemLog(entity);
    }

    public SystemLog getSystemLog(Long id) throws Exception {
        SystemLog systemLog = null;

        try {
            systemLog = systemLogLogic.getSystemLog(id);
        } catch (Exception e) {
            throw e;
        }

        return systemLog;
    }

    public List<SystemLog> findByCriteriaInSystemLog(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return systemLogLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<SystemLog> findPageSystemLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return systemLogLogic.findPageSystemLog(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberSystemLog() throws Exception {
        return systemLogLogic.findTotalNumberSystemLog();
    }

    public List<SystemLogDTO> getDataSystemLog() throws Exception {
        return systemLogLogic.getDataSystemLog();
    }
    
    public List<VideoTemp> getVideoTemp() throws Exception {
        return videoTempLogic.getVideoTemp();
    }

    public void saveVideoTemp(VideoTemp entity) throws Exception {
        videoTempLogic.saveVideoTemp(entity);
    }

    public void deleteVideoTemp(VideoTemp entity) throws Exception {
        videoTempLogic.deleteVideoTemp(entity);
    }

    public void updateVideoTemp(VideoTemp entity) throws Exception {
        videoTempLogic.updateVideoTemp(entity);
    }

    public VideoTemp getVideoTemp(Long id) throws Exception {
        VideoTemp videoTemp = null;

        try {
            videoTemp = videoTempLogic.getVideoTemp(id);
        } catch (Exception e) {
            throw e;
        }

        return videoTemp;
    }

    public List<VideoTemp> findByCriteriaInVideoTemp(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return videoTempLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VideoTemp> findPageVideoTemp(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return videoTempLogic.findPageVideoTemp(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVideoTemp() throws Exception {
        return videoTempLogic.findTotalNumberVideoTemp();
    }

    public List<VideoTempDTO> getDataVideoTemp() throws Exception {
        return videoTempLogic.getDataVideoTemp();
    }

    
    //AUTH---------------------------------------------------
    @Override
	public Users authenticate(String usuLogin, String usuPassword) throws Exception{
		return securityLogic.authenticate(usuLogin, usuPassword);
	}
    @Override
    public Users getUserByLogin(String usuLogin) throws Exception{
		return securityLogic.getUserByLogin(usuLogin);
    }
    @Override
    public void restoreUserPassword(String usuLogin) throws Exception{
    	securityLogic.restoreUserPassword(usuLogin);
    }
    
    //VIDEO ANALYSIS---------------------------------------------------
    //TEMP
    @Override
    public String saveVideoToTempLocation(String contentType, String fileName, Long size, InputStream inputFile, String type)
			throws Exception{
				return videoAnalysisLogic.saveVideoToTempLocation(contentType, fileName, size, inputFile, type);	
    }
    //TRACKER
    @Override
    public void analyze_trackerAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception{
    	videoAnalysisLogic.analyze_trackerAlg(contentType, fileName, size, inputFile);
    }
    //ANALIZE ANOMALOUS EVENTS REST
    @Override
    public String analyze_anomalousAlgFromTempLocation(String videoFileTemp, String videoSize, String initTimeParam,
			String finalTimeParam, String info) throws Exception{
    	
    	//local
    	return videoAnalysisLogic.analyze_anomalousAlgFromTempLocation(videoFileTemp, videoSize, initTimeParam, finalTimeParam, info);
   
    }
    //ANALIZE TRACKER EVENTS
    @Override
    public String analyze_trackerAlgFromTempLocation(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param, String info) throws Exception{
				return videoAnalysisLogic.analyze_trackerAlgFromTempLocation(
						videoFileTemp, videoSize, posXParam, posYParam, posX2Param, posY2Param, info);
    	
    }
    @Override
	public void analyze_anomalousAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception{
		videoAnalysisLogic.analyze_anomalousAlg(contentType, fileName, size, inputFile);
	}

	//LOCATIONS
	@Override
	public String getTemp_video_folder() throws Exception{
		return videoAnalysisLogic.getTemp_video_folder();
	}
	
	@Override
	public String getWeb_server_temp() throws Exception{
		return videoAnalysisLogic.getWeb_server_temp();
	}

	@Override
	public String getAlg_anl_output_server() throws Exception{
		return videoAnalysisLogic.getAlg_anl_output_server();
	}

	@Override
	public String getAlg_trc_output_server()throws Exception{
		return videoAnalysisLogic.getAlg_trc_output_server();
		
	}
	
	//VIDEO DATA
	@Override
	public List<VideoDTO> getDataVideoTracker() throws Exception{
		return videoLogic.getDataVideoTracker();
	}

	@Override
	public List<VideoDTO> getDataVideoAnomalous() throws Exception{
		return videoLogic.getDataVideoAnomalous();
	}
	
	@Override
	public List<VideoTempDTO> getDataVideoTempTracker() throws Exception{
		return videoTempLogic.getDataVideoTempTracker();	
	}
	@Override
	public List<VideoTempDTO> getDataVideoTempAnomalous() throws Exception{
		return videoTempLogic.getDataVideoTempAnomalous();	
	}
	
	//ALG STATUS
	@Override
	public Boolean isAlgAnlAvailable() {
		return videoAnalysisStatusLogic.isAlgAnlAvailable();
	}
	@Override
	public Boolean isAlgTrackerAvailable() {
		return videoAnalysisStatusLogic.isAlgTrackerAvailable();
	}
	
	//DELETE VIDEO
	@Override
	public void deleteVideoTemp(Long id, String file) throws Exception{
			videoAnalysisLogic.deleteVideoTemp(id, file);
	
	}
	
    //REST --------------------------------------------------------
	@Override
	public Boolean serverMonitorControllerStatus() throws Exception{
		return monitorLogicRest.serverMonitorControllerStatus();
	}
	//REST ANL DATA
	@Override
	public void getAnlDataFromRest(String data) throws Exception{
		videoLogic.getAnlDataFromRest(data);
	}
	@Override
    public String analyze_anomalousAlgFromTempLocationREST(String videoFileTemp, String videoSize, String initTimeParam,
			String finalTimeParam, String info) throws Exception{

		return videoAnalysisLogicRest.analyze_anomalousAlgFromTempLocationREST(videoFileTemp, videoSize, initTimeParam, finalTimeParam, info);
        
    }
	
    

}
