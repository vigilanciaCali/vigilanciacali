# FECHA

12/10/2019

# PROYECTO

Vigilancia Inteligente para la Red de Cámaras de la Policía Metropolitana de Cali

## SUB-SISTEMA

Algoritmo detector de actividades anómalas

## MÓDULO

API rest

### RESPONSABILIDADES

Recibir peticiones de un cliente externo y comunicarlas al algoritmo de detección de actividades anómalas

## AUTORES

Roger Figueroa Quintero - roger.figueroa@javerianacali.edu.co

#### TECNOLOGÍA Y DEPENDENCIAS

flask 1.0.2
requests 2.21.0

#### INSTALACIÓN Y COMPILACIÓN

Usando la herramienta pip instale las siguientes librerías:
```
pip install Flask==1.0.2
pip install requests==2.21.0
```
#### CONFIGURACIÓN Y EJECUCIÓN

Una vez instaladas las librerías, instale los demás módulos (modulo C3D, modulo fully-connected y modulo generador de marca de agua). Para iniciar el API rest ejecute el siguiente comando en una terminal:
```
sh virtual_env.sh
```
Luego, usando la herramienta curl, haga el siguiente post:
```
curl -X POST -H "Content-Type: application/json" --data '{"path_video_in":"/PATH/TO/VIDEO_IN/","name_video_in":"video.mp4","parameters":[{"time_start":"ti","time_end":"tf"}],"path_video_out":"/PATH/TO/VIDEO_OUT/","name_video_out":"video_out.mp4","video_transaction_id":"id"}' http://localhost:5000/anomalyDetetection
```
En el anterior POST path_video_in y name_video_in son la ruta absoluta y el nombre del video a procesar, path_video_out y name_video_out son la ruta absoluta y el nombre del video de salida, time_start y time_end representan los limites de tiempo en segundos entre los cuales el algoritmo procesa el video de entrada, y video_transaction_id es un número que se usa para llevar la trazabilidad al POST recibido, este mismo número se envía nuevamente al servicio externo (el servicio que llamó al API rest) junto con un código de error cuando el algoritmo de detección de actividades anómalas finaliza su ejecución. 

La disponibilidad del algoritmo, true si está disponible y false si el algoritmo se está ejecutando, puede consultarse en la siguiente url usando el método GET:

http://localhost:5000/anomalyDetetectionState

#### CONTENIDO Y VERSIONES
```
anomaly_detection_execute.sh
API_REST.py
c3d_feature_execute.sh
callback_tracking.m
executing_tracking.sh
includes.py
includes.pyc
list
virtual_env.sh
water_mark_execute.sh
```
