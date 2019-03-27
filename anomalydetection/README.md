# anomalydetection


Antes de usar establecer theano como backend:
vim ~/.keras/keras.json

tensorflow
theano

La secuencia de uso fue la siguiente:

Inicialmente se deben crear 2 archivos en: 

"/home/javeriana/carpeta_roger/librerias/C3D/C3D v1.0/examples/c3d_feature_extraction_copy/my_scripts":

ALgunas carpetas importantes son:

copy_anomaly_Test.py: se encarga de copiar el set de prueba desde "/media/root/TOSHIBA_EXT/Dataset/UCF_Crimes/Videos" hasta "/home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_copy/input/ucf_test"

c3d_secuence.py: Prepara los archivos necesarios para la ejecución de c3d:

my_input_list_video.txt: se encuentra en ./prototxt, lista de videos (path) y sus respectivos segmentos  (clips) de 16 en 16 frames.
my_output_list_video_prefix.txt: se encuentra en ./prototxt, almacena la dirección de salida de las características, cada una correspondiente a uno de los clips de 16 frames.

my_c3d_sport1m_feature_extraction_video.sh : se encuentra en la raíz del proyecto, este archivo contiene el script que lanza la red neuronal, gpu a usar y numero y tamaño del batch de procesamiento. NOTA: el tamaño del batch establecido en este archivo debe también ser establecido en ./prototxt/c3d_sport1m_feature_extractor_video.prototxt

La secuencia de acciones es la siguiente: ejecutar copy_anomaly_Test.py (la carpeta de videos que usa este archivo al momento de la ejecución se cambio a ucf_test_copy), ejecutar c3d_secuence.py, y por ultimo sh my_c3d_sport1m_feature_extraction_video.sh. Despues de esto se empezara a ejecutar c3d.

Cuando c3d termine, ejecutamos el script copy_incomplete_features.m, que copiara todas las carpetas asociadas a los videos con la totalidad de caracteristicas procesadas y las enviarà al directorio (todas en el mismo directorio, sin la carpeta de categoría) "/home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_copy/new_output_copy". Este script también copiara cada video que haya sido procesado totalmente  y lo enviara a la carpeta "/home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_copy/Testing_Videos". Despues de esto, nos dirijimos a la carpeta "/home/javeriana/carpeta_roger/programas/AnomalyDetection_env/AnomalyDetectionCVPR2018" y ejecutamos Save_C3DFeatures_32Segments.m, esto convertirá las características fc-1 en formato .txt que se almacenaran en este mismo directorio en la carpeta C3D_Features_txt. Luego de esto se ejecuta el script Test_Anomaly_Detector_public.py, esto generara usara las características en formato .txt de la carpeta  C3D_Features_txt para generar los scores de 32 segmentos para cada video y los almacenara en formato .mat en la carpeta Eval_Res. Por ultimo  se ejecuta el script Evaluate_Anomaly_Detector.m el cual construye la curva ROC y la guarda en formato .png en este mismo directorio, este ultimo archivo requiere de las anotaciones temporales en formato .mat de graund true las cuales se descargaron de la pagina del proyecto y se almacenaron en la carpeta "Temporal_Anomaly_Annotation_For_Testing_Videos/Matlab_formate".
