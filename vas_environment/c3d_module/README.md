# FECHA

12/10/2019

# PROYECTO

Vigilancia Inteligente para la Red de Cámaras de la Policía Metropolitana de Cali

## SUB-SISTEMA

Algoritmo detector de actividades anómalas

## MÓDULO

C3D

### RESPONSABILIDADES

Extraer las características de la capa fc6 de la red convolucional C3D para un video de entrada

## AUTORES

Roger Figueroa Quintero - roger.figueroa@javerianacali.edu.co

#### TECNOLOGÍA Y DEPENDENCIAS

- C3D 1.0<br/>
- CUDA 9.0<br/>

#### INSTALACIÓN Y COMPILACIÓN

Para instalar CUDA 9.0 siga las instrucciones dadas en el siguiente link: 

https://docs.nvidia.com/cuda/cuda-installation-guide-linux/index.html

Para instalar C3D 1.0 siga las instrucciones dadas en el siguiente link: 

https://github.com/facebookarchive/C3D

Luego de compilar C3D ubique la ruta donde se encuentra almacenado el archivo extract_image_features.bin y establescala en la variable path_extract_image_features_bin en la linea 8 del archivo prepare_c3d_execution.py.

#### CONFIGURACIÓN Y EJECUCIÓN

Antes de usar C3D para extraer características de un video es necesario configurar el archivo .prototxt donde se define la estructura de la red
convolucional. En particular nosotros usamos la estructura de red convolucional c3d_sport1m_feature_extractor_video.prototxt disponible, junto
con los pesos de entrenamiento, en siguiente link:

http://vlg.cs.dartmouth.edu/c3d/

Cuando la descarga haya terminado copiamos los archivos c3d_sport1m_feature_extractor_video.prototxt, conv3d_deepnetA_sport1m_iter_1900000 y sport1m_train16_128_mean.binaryproto a la carpeta "./prototxt". Luego abrimos el archivo c3d_sport1m_feature_extractor_video.prototxt y modificamos la capa data para que se vea como sigue:
```
layers {
  name: "data"
  type: VIDEO_DATA
  top: "data"
  top: "label"
  image_data_param {
    source: "prototxt/input_list_video.txt"
    use_image: false
    mean_file: "prototxt/sport1m_train16_128_mean.binaryproto"
    batch_size: 50
    crop_size: 112
    mirror: false
    show_data: 0
    new_height: 128
    new_width: 171
    new_length: 16
    shuffle: false
  }
} 
```
Por último configure la ruta del interprete de python en la linea 1 del archivo prepare_c3d_execution.py.

Ahora, asumiendo que existe un video en formato avi o mp4 en la ruta "./input/video_input", proceda como sigue para extraer las características fc6 con la red convolucional C3D:
```
python prepare_c3d_execution.py
chmod +x feature_extraction.sh
sh feature_extraction.sh
```
Cuando la ejecución termina las características son almacenadas en la ruta "./output".

**NOTA**: Este modulo es automáticamente llamado por el API rest cuando recibe un POST para ejecutar el algoritmo de detección de actividades anómalas.

#### CONTENIDO Y VERSIONES

- feature_extraction.sh<br/>
- prepare_c3d_execution.py<br/>
