# FECHA

12/10/2019

# PROYECTO

Vigilancia Inteligente para la Red de Cámaras de la Policía Metropolitana de Cali

## SUB-SISTEMA

Algoritmo detector de actividades anómalas

## MÓDULO

Fully-connected

### RESPONSABILIDADES

Detectar actividades anómalas en el video de entrada con base en las características fc6 entregadas por el modulo C3D

## AUTORES

Roger Figueroa Quintero - roger.figueroa@javerianacali.edu.co

#### TECNOLOGÍA Y DEPENDENCIAS

- Matlab R2018a<br/>
- keras 1.1.0<br/>
- scipy 1.1.0<br/>
- theano 1.0.2<br/>
- skimage 0.14.0<br/>

#### INSTALACIÓN Y COMPILACIÓN

Usando la herramienta pip instale las siguientes librerías:
```
pip install Keras==1.1.0
pip install scipy==1.1.0
pip install Theano==1.0.2
pip install scikit-image==0.14.0
```
#### CONFIGURACIÓN Y EJECUCIÓN

**NOTA**: El modulo fully-connected es una adaptación del algoritmo presentado en https://arxiv.org/pdf/1801.04264.pdf. Nosotros descargamos del repositorio GitHub del autor algunos archivos y los modificamos para adaptarlos a nuestro proceso algorítmico.

Descargue los archivos model.json y weights_L1L2.mat del siguiente link:

https://github.com/WaqasSultani/AnomalyDetectionCVPR2018

Despues ubique estos archivos en este directorio. Por último configure la ruta del interprete de python en la linea 1 del archivo anomaly_detector.py.

#### CONTENIDO Y VERSIONES

- anomaly_detector.py<br/>
- read_binary_blob.m<br/>
- Save_C3DFeatures_32Segments.m<br/>
