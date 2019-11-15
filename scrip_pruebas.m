clear all;
clc;


BB = [668,332,124,339];
video_in='prueba_tr.mp4';
path_video='/media/javeriana/TOSHIBA_2TB/carpeta_roger/programas/API_REST_PYTHON/dir_test_input/';
video_out='out_tr.mp4';
path_mainFolder_Tracking='/media/javeriana/TOSHIBA_2TB/carpeta_roger/programas/API_REST_PYTHON/dir_test_output/';


addpath('/home/javeriana/roger_gomez/DSST2/DSST2');
tracker_dsst(BB,video_in,path_video,video_out,path_mainFolder_Tracking);




