package co.edu.usbcali.vas.presentation.businessDelegate;

import java.io.InputStream;
import java.util.List;

import co.edu.usbcali.vas.model.CronJob;
import co.edu.usbcali.vas.model.CronJobMonitoring;
import co.edu.usbcali.vas.model.SystemCompanyParameter;
import co.edu.usbcali.vas.model.SystemConfig;
import co.edu.usbcali.vas.model.SystemCronLog;
import co.edu.usbcali.vas.model.SystemLog;
import co.edu.usbcali.vas.model.SystemMonitoringLog;
import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.model.SystemRestLog;
import co.edu.usbcali.vas.model.SystemVideoLog;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoDocument;
import co.edu.usbcali.vas.model.VideoTemp;
import co.edu.usbcali.vas.model.dto.CronJobDTO;
import co.edu.usbcali.vas.model.dto.CronJobMonitoringDTO;
import co.edu.usbcali.vas.model.dto.SystemCompanyParameterDTO;
import co.edu.usbcali.vas.model.dto.SystemConfigDTO;
import co.edu.usbcali.vas.model.dto.SystemCronLogDTO;
import co.edu.usbcali.vas.model.dto.SystemLogDTO;
import co.edu.usbcali.vas.model.dto.SystemMonitoringLogDTO;
import co.edu.usbcali.vas.model.dto.SystemParameterDTO;
import co.edu.usbcali.vas.model.dto.SystemRestLogDTO;
import co.edu.usbcali.vas.model.dto.SystemVideoLogDTO;
import co.edu.usbcali.vas.model.dto.VideoDTO;
import co.edu.usbcali.vas.model.dto.VideoDocumentDTO;
import co.edu.usbcali.vas.model.dto.VideoTempDTO;



public interface IBusinessDelegatorView {
    public List<SystemParameter> getSystemParameter() throws Exception;

    public void saveSystemParameter(SystemParameter entity)
        throws Exception;

    public void deleteSystemParameter(SystemParameter entity)
        throws Exception;

    public void updateSystemParameter(SystemParameter entity)
        throws Exception;

    public SystemParameter getSystemParameter(Integer id)
        throws Exception;

    public List<SystemParameter> findByCriteriaInSystemParameter(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SystemParameter> findPageSystemParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSystemParameter() throws Exception;

    public List<SystemParameterDTO> getDataSystemParameter()
        throws Exception;

    public List<SystemCronLog> getSystemCronLog() throws Exception;

    public void saveSystemCronLog(SystemCronLog entity)
        throws Exception;

    public void deleteSystemCronLog(SystemCronLog entity)
        throws Exception;

    public void updateSystemCronLog(SystemCronLog entity)
        throws Exception;

    public SystemCronLog getSystemCronLog(Long id) throws Exception;

    public List<SystemCronLog> findByCriteriaInSystemCronLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SystemCronLog> findPageSystemCronLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemCronLog() throws Exception;

    public List<SystemCronLogDTO> getDataSystemCronLog()
        throws Exception;

    public List<SystemMonitoringLog> getSystemMonitoringLog()
        throws Exception;

    public void saveSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception;

    public void deleteSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception;

    public void updateSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception;

    public SystemMonitoringLog getSystemMonitoringLog(Long id)
        throws Exception;

    public List<SystemMonitoringLog> findByCriteriaInSystemMonitoringLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SystemMonitoringLog> findPageSystemMonitoringLog(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSystemMonitoringLog() throws Exception;

    public List<SystemMonitoringLogDTO> getDataSystemMonitoringLog()
        throws Exception;

    public List<SystemRestLog> getSystemRestLog() throws Exception;

    public void saveSystemRestLog(SystemRestLog entity)
        throws Exception;

    public void deleteSystemRestLog(SystemRestLog entity)
        throws Exception;

    public void updateSystemRestLog(SystemRestLog entity)
        throws Exception;

    public SystemRestLog getSystemRestLog(Long id) throws Exception;

    public List<SystemRestLog> findByCriteriaInSystemRestLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SystemRestLog> findPageSystemRestLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemRestLog() throws Exception;

    public List<SystemRestLogDTO> getDataSystemRestLog()
        throws Exception;

    public List<CronJob> getCronJob() throws Exception;

    public void saveCronJob(CronJob entity) throws Exception;

    public void deleteCronJob(CronJob entity) throws Exception;

    public void updateCronJob(CronJob entity) throws Exception;

    public CronJob getCronJob(Integer id) throws Exception;

    public List<CronJob> findByCriteriaInCronJob(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<CronJob> findPageCronJob(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCronJob() throws Exception;

    public List<CronJobDTO> getDataCronJob() throws Exception;

    public List<SystemConfig> getSystemConfig() throws Exception;

    public void saveSystemConfig(SystemConfig entity) throws Exception;

    public void deleteSystemConfig(SystemConfig entity)
        throws Exception;

    public void updateSystemConfig(SystemConfig entity)
        throws Exception;

    public SystemConfig getSystemConfig(Integer id) throws Exception;

    public List<SystemConfig> findByCriteriaInSystemConfig(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemConfig> findPageSystemConfig(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemConfig() throws Exception;

    public List<SystemConfigDTO> getDataSystemConfig()
        throws Exception;

    public List<Video> getVideo() throws Exception;

    public void saveVideo(Video entity) throws Exception;

    public void deleteVideo(Video entity) throws Exception;

    public void updateVideo(Video entity) throws Exception;

    public Video getVideo(Long id) throws Exception;

    public List<Video> findByCriteriaInVideo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Video> findPageVideo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideo() throws Exception;

    public List<VideoDTO> getDataVideo() throws Exception;

    public List<SystemCompanyParameter> getSystemCompanyParameter()
        throws Exception;

    public void saveSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception;

    public void deleteSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception;

    public void updateSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception;

    public SystemCompanyParameter getSystemCompanyParameter(Integer id)
        throws Exception;

    public List<SystemCompanyParameter> findByCriteriaInSystemCompanyParameter(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SystemCompanyParameter> findPageSystemCompanyParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSystemCompanyParameter()
        throws Exception;

    public List<SystemCompanyParameterDTO> getDataSystemCompanyParameter()
        throws Exception;

    public List<CronJobMonitoring> getCronJobMonitoring()
        throws Exception;

    public void saveCronJobMonitoring(CronJobMonitoring entity)
        throws Exception;

    public void deleteCronJobMonitoring(CronJobMonitoring entity)
        throws Exception;

    public void updateCronJobMonitoring(CronJobMonitoring entity)
        throws Exception;

    public CronJobMonitoring getCronJobMonitoring(Integer id)
        throws Exception;

    public List<CronJobMonitoring> findByCriteriaInCronJobMonitoring(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<CronJobMonitoring> findPageCronJobMonitoring(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberCronJobMonitoring() throws Exception;

    public List<CronJobMonitoringDTO> getDataCronJobMonitoring()
        throws Exception;

    public List<VideoDocument> getVideoDocument() throws Exception;

    public void saveVideoDocument(VideoDocument entity)
        throws Exception;

    public void deleteVideoDocument(VideoDocument entity)
        throws Exception;

    public void updateVideoDocument(VideoDocument entity)
        throws Exception;

    public VideoDocument getVideoDocument(Long id) throws Exception;

    public List<VideoDocument> findByCriteriaInVideoDocument(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<VideoDocument> findPageVideoDocument(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideoDocument() throws Exception;

    public List<VideoDocumentDTO> getDataVideoDocument()
        throws Exception;

    public List<SystemVideoLog> getSystemVideoLog() throws Exception;

    public void saveSystemVideoLog(SystemVideoLog entity)
        throws Exception;

    public void deleteSystemVideoLog(SystemVideoLog entity)
        throws Exception;

    public void updateSystemVideoLog(SystemVideoLog entity)
        throws Exception;

    public SystemVideoLog getSystemVideoLog(Long id) throws Exception;

    public List<SystemVideoLog> findByCriteriaInSystemVideoLog(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<SystemVideoLog> findPageSystemVideoLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemVideoLog() throws Exception;

    public List<SystemVideoLogDTO> getDataSystemVideoLog()
        throws Exception;

    public List<SystemLog> getSystemLog() throws Exception;

    public void saveSystemLog(SystemLog entity) throws Exception;

    public void deleteSystemLog(SystemLog entity) throws Exception;

    public void updateSystemLog(SystemLog entity) throws Exception;

    public SystemLog getSystemLog(Long id) throws Exception;

    public List<SystemLog> findByCriteriaInSystemLog(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemLog> findPageSystemLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemLog() throws Exception;

    public List<SystemLogDTO> getDataSystemLog() throws Exception;
    
    public List<VideoTemp> getVideoTemp() throws Exception;

    public void saveVideoTemp(VideoTemp entity) throws Exception;

    public void deleteVideoTemp(VideoTemp entity) throws Exception;

    public void updateVideoTemp(VideoTemp entity) throws Exception;

    public VideoTemp getVideoTemp(Long id) throws Exception;

    public List<VideoTemp> findByCriteriaInVideoTemp(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VideoTemp> findPageVideoTemp(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideoTemp() throws Exception;

    public List<VideoTempDTO> getDataVideoTemp() throws Exception;

    
    //AUTH-------------------------------------------------------------------------
  	public Users authenticate(String usuLogin, String usuPassword) throws Exception;
  	public Users getUserByLogin(String usuLogin) throws Exception;
  	public void restoreUserPassword(String usuLogin) throws Exception;
  	//VIDEO
  	
  	//TEMP
  	public String saveVideoToTempLocation(String contentType, String fileName, Long size, InputStream inputFile, String type)
			throws Exception;
  	//TRACKER
  	public void analyze_trackerAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception;
  	//ANOMALOUS EVENTS
  	 public String analyze_anomalousAlgFromTempLocation(String videoFileTemp, String videoSize, String initTimeParam,
 			String finalTimeParam, String info) throws Exception;
  	//TRACKER EVENTS
  	public String analyze_trackerAlgFromTempLocation(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param, String info) throws Exception;
  	
  	public void analyze_anomalousAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception;
  	public void uploadVideoToFtp(String contentType, String fileName, Long size, InputStream inputFile) throws Exception;

  	//LOCATIONS
	public String getTemp_video_folder() throws Exception;
	public String getWeb_server_temp() throws Exception;
	//SERVER LOCATIONS
	public String getAlg_anl_output_server() throws Exception;
	public String getAlg_trc_output_server() throws Exception;

	//ANL REST DATA
	public void getAnlDataFromRest(String data) throws Exception;

	//VIDEO DATA
	public List<VideoDTO> getDataVideoTracker() throws Exception;
	public List<VideoDTO> getDataVideoAnomalous() throws Exception;

	public List<VideoTempDTO> getDataVideoTempTracker() throws Exception;
	public List<VideoTempDTO> getDataVideoTempAnomalous() throws Exception;
	//DELETE
	public void deleteVideoTemp(Long id, String file) throws Exception;
	
	public void save_systemLog(SystemLogDTO systemLogDTO) throws Exception;


	

	


	
	
}
