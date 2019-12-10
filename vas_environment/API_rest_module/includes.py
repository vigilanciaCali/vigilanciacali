import time



#some important folders
g_c3d_intput_folder="../c3d_module/input/video_input"
g_c3d_execute="c3d_feature_execute.sh"
g_anl="anomaly_detection_execute.sh"
g_water_mark="water_mark_execute.sh"


g_listener_anl="http://localhost:8080/vas_monitor/controller/monitor/anomalyDetetectionResult"
g_listener_tr="http://localhost:8080/vas_monitor/controller/monitor/trackingResult"


#some functions


def sec2hms(sec):

 hms_str=time.strftime("%H:%M:%S", time.gmtime(sec))
 vec_hms_str=hms_str.split(":")
 vec_hms=[int(vec_hms_str[0]), int(vec_hms_str[1]), int(vec_hms_str[2])]
  
 return vec_hms

def sec2hms_str(sec):
 hms_str=time.strftime("%H:%M:%S", time.gmtime(sec))

 return hms_str



g_error_code_message={
10: "El archivo esta corrupto",
100: "El algoritmo ha terminado su ejecucion exitosamente"
}
