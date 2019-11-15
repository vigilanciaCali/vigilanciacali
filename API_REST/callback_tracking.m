function callback_tracking(path_in,video_in,x,y,w,h,t,path_out,video_out)




%________________descripciòn de los argumentos de entrada a esta funciòn_______________
%path_in: este es el path donde se encuentra el video de entrada, ejemplo: path_in='/media/javeriana/TOSHIBA_2TB/carpeta_roger/programas/API_REST_PYTHON/dir_test_input/'

%coordenadas

%Asuma que la imagen de entrada tiene una dimension HxW, donde H es la alto y W es el ancho

% (x,y) son las coordenadas de la esquina superior izquierda desde donde empieza el boundingbox
%x: es una coordenada entre 1 y H
%y: es una coordenada entre 1 y W
%w: es el ancho del bounding box
%h: es el altor del bounding box
%t: es el tiempo medido en frames desde el inicion, desde donde empieza el seguimiento de un determinado objeto

%path_out: es el path de se almacenarà el video de salida

%





%_________adaptando la interfaz de entrada a la funciòn de tracking______________


addpath('/home/javeriana/roger_gomez/DSST2/DSST2');

path_video=path_in;
video_in=video_in;

%x relacionado con el ancho
%y relacionado con el alto
%
'_______________________coordenadas_______________________________'
BB=[x,y,w,h]
path_mainFolder_Tracking=path_out;
video_out=video_out;

tracker_dsst(BB,video_in,path_video,video_out,path_mainFolder_Tracking);



end
