package co.edu.usbcali.vas.utilities;

public class Constantes
{
 
  public static String MYME_TYPE_PDF = "application/pdf";
  public static String MYME_TYPE_DOC = "application/msword";
  public static String MYME_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
  public static String MYME_TYPE_XLS = "application/vnd.ms-excel";
  public static String MYME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  
  public static enum REPORT_OUTPUT_TYPE
  {
    XLS,  XLSX,  PDF,  CSV,  DOC,  DOCX,  HTML;
  }
 
  public static String[] ENERO = { "ENE", "Enero" };
  public static String[] FEBRERO = { "FEB", "Febrero" };
  public static String[] MARZO = { "MAR", "Marzo" };
  public static String[] ABRIL = { "ABR", "Abril" };
  public static String[] MAYO = { "MAY", "Mayo" };
  public static String[] JUNIO = { "JUN", "Junio" };
  public static String[] JULIO = { "JUL", "Julio" };
  public static String[] AGOSTO = { "AGO", "Agosto" };
  public static String[] SEPTIEMBRE = { "SEP", "Septiembre" };
  public static String[] OCTUBRE = { "OCT", "Octubre" };
  public static String[] NOVIEMBRE = { "NOV", "Noviembre" };
  public static String[] DICIEMBRE = { "DIC", "Diciembre" };
  public static boolean TRUE = true;
  public static boolean FALSE = false;
  //SESSION
  public final static String SESSION_USER_NAME = "sessionUserName";
  public final static String SESSION_USER_OBJECT = "sessionUserObject";
  public final static String SESSION_USER_ID = "sessionUserId";
  public final static String SESSION_USER_LOGIN = "sessionLogin";
  public final static String SESSION_USER_TYPE = "sessionTipoUser";
  public final static String SESSION_USER_TYPE_DESCRIPTION = "sessionTipoUserDescription";
  //USERS
  public final static String USER_TYPE_ADMIN = "SYSTEM_ADMIN";
  public final static String USER_TYPE_SYSTEM = "SYSTEM";
  public final static String USER_TYPE_USER_CODE = "CUSTOMER_USER";
  public final static String USER_TYPE_ADMIN_CODE = "CUSTOMER_ADMIN";
  //PARAMETERS CODE
  public final static String SYSTEM_NAME_PARAMETER_CODE = "SYSTEM_NAME";
  public final static String SYSTEM_URL_PARAMETER_CODE = "URL";
  public final static String SYSTEM_APP_SERVICE = "APP_SERVICE";
  public final static String REPORTS_SOURCE = "REPORTS_SOURCE";
  //COMPANY
  public final static String COMPANY_LOGO_URL_PARAMETER_CODE = "COMPANY_LOGO_URL";
  public final static String COMPANY_NIT_PARAMETER_CODE = "COMPANY_NIT";
  public final static String COMPANY_NAME_PARAMETER_CODE = "COMPANY_NAME";
  public final static String COMPANY_ADDRESS_PARAMETER_CODE = "COMPANY_ADDRESS";
  public final static String COMPANY_EMAIL_PARAMETER_CODE = "COMPANY_EMAIL";
  public final static String COMPANY_WEB_PARAMETER_CODE = "COMPANY_WEB";
  public final static String COMPANY_PHONE_PARAMETER_CODE = "COMPANY_PHONE";
  //FORMATO FECHAS
  public static String FORMATO_FECHA_CORTA = "dd/MM/yyyy";
  public static String FORMATO_FECHA_CORTA_YYYY_MM_DD = "yyyy-MM-dd";
  public static String FORMATO_FECHA_CORTA_24HORA = "dd/MM/yyyy HH:mm:ss";
  public static String FORMATO_FECHA_YYYYMMDD_24HORA = "yyyy-MM-dd HH:mm:ss";
  public static String FORMATO_FECHA_YYYYMMDD_24HORAS_DEVICE = "yyyy-MM-dd HH:mm:ss";
  public static String FORMATO_FECHA_YYYY_MM_DD_24H = "yyyy/MM/dd HH:mm:ss";
  public static String FORMATO_FECHA_12HORA = "dd/MM/yyyy hh:mm a";
  public static String FORMATO_FECHA_VIDEO = "ddMMyyyyHHmmss";
  //FORMATO HORAS
  public static String HORA_TURNO_DEFAULT = "00:00:00";
  public static String HORA_TURNO_DEFAULT_FIN = "23:59:59";
  public static String HORA_FIN_DIA = "23:59:59";
  public static String HORA_INICIO_DIA = "00:00:00";
  public static String HORA_MEDIO_DIA = "12:00:00";
  public static String FORMATO_HORA24H = "HH:mm:ss";
  public static String FORMATO_HORA24H_HHMM = "HH:mm";
  public static String FORMATO_HORA12H = "hh:mm a";

  //MONITOR SERVICE
  public static String MONITOR_REST_SERVICE_UPLOADED_VIDEO = "MONITOR_REST_SERVICE_UPLOADED_VIDEO";

  //SERVERS
  public static String WEB_SERVER = "WEB_SERVER";
  public static String APP_SERVER = "APP_SERVER";
  public static String APP_SERVER_PORT = "APP_SERVER_PORT";
  public static String WEB_SERVER_TEMP = "WEB_SERVER_TEMP";
  public static String ALG_ANL_OUTPUT_SERVER = "ALG_ANL_OUTPUT_SERVER";
  public static String ALG_TRC_OUTPUT_SERVER = "ALG_TRC_OUTPUT_SERVER";

  public static String SFTP_HOST = "SFTP_HOST";
  public static String SFTP_PORT = "SFTP_PORT";
  public static String SFTP_USER = "SFTP_USER";
  public static String SFTP_PASS = "SFTP_PASS";
  
  public static String SERVER_MONITOR_CONTROLLER = "SERVER_MONITOR_CONTROLLER";
  //QUEUE SQS
  public static String SQS_URL_HOST = "SQS_URL_HOST";
  public static String SQS_ACCESS_KEYID = "SQS_ACCESS_KEYID";
  public static String SQS_SECRETKEY = "SQS_SECRETKEY";
 
  //VIDEO FOLDERS
  public static String INPUT_VIDEO_FOLDER = "INPUT_VIDEO_FOLDER";
  public static String OUTPUT_ALG_ANL_VIDEO_FOLDER = "OUTPUT_ALG_ANL_VIDEO_FOLDER";
  public static String OUTPUT_ALG_TRC_VIDEO_FOLDER = "OUTPUT_ALG_TRC_VIDEO_FOLDER";
  public static String TEMP_VIDEO_FOLDER = "TEMP_VIDEO_FOLDER";

  //FORMAT
  public static String VIDEO_FORMAT = "VIDEO_FORMAT";
  //EXTERNAL PROGRAMS
  public static String EXTERNAL_PROGRAM_ALG_ANL_LOCATION = "EXTERNAL_PROGRAM_ALG_ANL_LOCATION";
  public static String EXTERNAL_PROGRAM_ALG_TRC_LOCATION = "EXTERNAL_PROGRAM_ALG_TRC_LOCATION";
  //MATLAB RESULT FILE
  public static String MATLAB_ANOMALOUS_RESULT_FILE = "MATLAB_ANOMALOUS_RESULT_FILE";
  public static String MATLAB_TRACKER_RESULT_FILE = "MATLAB_TRACKER_RESULT_FILE";
  //MATLAB RESULT FOLDER
  public static String MATLAB_ANOMALOUS_RESULT_FOLDER = "MATLAB_ANOMALOUS_RESULT_FOLDER";
  public static String MATLAB_TRACKER_RESULT_FOLDER = "MATLAB_TRACKER_RESULT_FOLDER";
  //ALG INPUT FILES
  public static String ALG_ANL_PARAMS_INPUT_FILE = "ALG_ANL_PARAMS_INPUT_FILE";
  public static String ALG_TRC_PARAMS_INPUT_FILE = "ALG_TRC_PARAMS_INPUT_FILE";
  //ALG TYPE
  public static String ALG_TYPE_ANOMALOUS = "EVENTOS ANOMALOS";
  public static String ALG_TYPE_TRACKER = "SEGUIMIENTO OBJETOS";
  //ALG STATUS FILE
  public static String STATUS_ANOMALOUS_FILE = "STATUS_ANOMALOUS_FILE";
  public static String STATUS_TRACKER_FILE = "STATUS_TRACKER_FILE";
  //VIDEO STATUS
  public static String NEW = "NEW";
  public static String PROCESSED = "PROCESSED";
  //VIDEO TYPE
  public static String ANOMALOUS = "ANOMALOUS";
  public static String TRACKER = "TRACKER";
  
  
  
  
  
  
}

