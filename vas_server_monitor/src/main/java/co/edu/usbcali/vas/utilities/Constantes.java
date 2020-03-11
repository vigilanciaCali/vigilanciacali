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

  //QUEUE SQS------------------------------------------------
  //ANL
  public static String SQS_ANL_URL_HOST = "SQS_ANL_URL_HOST";
  public static String SQS_ANL_ACCESS_KEYID = "SQS_ANL_ACCESS_KEYID";
  public static String SQS_ANL_SECRETKEY = "SQS_ANL_SECRETKEY";
  //TRC
  public static String SQS_TRC_URL_HOST = "SQS_TRC_URL_HOST";
  public static String SQS_TRC_ACCESS_KEYID = "SQS_TRC_ACCESS_KEYID";
  public static String SQS_TRC_SECRETKEY = "SQS_TRC_SECRETKEY";
  //OUT
  public static String SQS_OUT_URL_HOST = "SQS_OUT_URL_HOST";
  public static String SQS_OUT_ACCESS_KEYID = "SQS_OUT_ACCESS_KEYID";
  public static String SQS_OUT_SECRETKEY = "SQS_OUT_SECRETKEY";
 
  //SERVERS & SRVICES---------------------------------------------------
  public static String WEB_SERVER = "WEB_SERVER";
  public static String WEB_SERVER_TEMP = "WEB_SERVER_TEMP";
  public static String ALG_ANL_OUTPUT_SERVER = "ALG_ANL_OUTPUT_SERVER";
  public static String ALG_TRC_OUTPUT_SERVER = "ALG_TRC_OUTPUT_SERVER";
  //VAS SERVER CONTROLLER
  public static String VAS_CONTROLLER = "VAS_CONTROLLER";
  //ALG SERVICE
  public static String SERVER_ALGORITHM_CONTROLLER = "SERVER_ALGORITHM_CONTROLLER";
  //ALG SERVER
  public static String ALGORITHM_SERVER = "ALGORITHM_SERVER";
  public static String ALGORITHM_SERVER_PORT = "ALGORITHM_SERVER_PORT";
  //MAIL SERVICE
  public static String SYSTEM_NOTIFICATOR_CONTROLLER = "SYSTEM_NOTIFICATOR_CONTROLLER";
  //TRACE SERVICE
  public static String SYSTEM_TRACE_CONTROLLER = "SYSTEM_TRACE_CONTROLLER";
  
  //----------------------------------------------------------------
  
  //VIDEO FOLDERS LOCATIONS-----------------------------------------
  public static String INPUT_VIDEO_FOLDER = "INPUT_VIDEO_FOLDER";
  public static String OUTPUT_ALG_ANL_VIDEO_FOLDER = "OUTPUT_ALG_ANL_VIDEO_FOLDER";
  public static String OUTPUT_ALG_TRC_VIDEO_FOLDER = "OUTPUT_ALG_TRC_VIDEO_FOLDER";
  public static String TEMP_VIDEO_FOLDER = "TEMP_VIDEO_FOLDER";
  //FORMAT
  public static String VIDEO_FORMAT = "VIDEO_FORMAT";
 //ALG TYPE
  public static String ALG_TYPE_ANOMALOUS = "EVENTOS ANOMALOS";
  public static String ALG_TYPE_TRACKER = "SEGUIMIENTO OBJETOS";
 //VIDEO STATUS------------------------------------
  public static String NEW = "NEW";
  public static String SENT = "SENT";
  public static String PENDING = "PENDING";
  public static String PROCESSED = "PROCESSED";
  //VIDEO TYPE
  public static String ANOMALOUS = "ANOMALOUS";
  public static String TRACKER = "TRACKER";
  //TIME INTERVAL
  //WAIT FOR ANLALG
  public static String TIME_WAITFOR_ALG = "TIME_WAITFOR_ALG";
  //TEST---------------------------------
  public static String ALG_ANL_AVAILABLE = "ALG_ANL_AVAILABLE";
  public static String ALG_TRC_AVAILABLE = "ALG_TRC_AVAILABLE";
  //TEST MODE
  public static String TEST_MODE = "TEST_MODE";
  public static String TEST_TIME = "TEST_TIME";
  //ALG REQUEST
  public static String ALG_ANL_REQUEST = "ALG_ANL_REQUEST";
  public static String ALG_TRC_REQUEST = "ALG_TRC_REQUEST";
  
  
  
  
  
}

