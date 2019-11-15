import time



#some important folders
g_root_anl_algorithm="/home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/"
g_c3d_intput_folder=g_root_anl_algorithm+"input/video_input/"
g_c3d_execute="c3d_execute.sh"
g_anl="/home/javeriana/carpeta_roger/programas/AnomalyDetection_env/AnomalyDetectionCVPR2018/pruebaev.py"
g_path_features_txt="/home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/script_UCF/C3D_Features_txt/"
g_path_scores="/home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/script_UCF/scores/"


#g_listener_anl="http://localhost:5000/capture_post_test"
#g_listener_tr="http://localhost:5000/capture_post_test"

g_listener_anl="http://localhost:8080/vas_monitor/controller/monitor/anomalyDetetectionResult"
g_listener_tr="http://localhost:8080/vas_monitor/controller/monitor/trackingResult"

#g_listener_anl="https://webhook.site/1ab6a4cd-7088-4681-a8cc-1bf37f596988"
#g_listener_tr="https://webhook.site/1ab6a4cd-7088-4681-a8cc-1bf37f596988"

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
