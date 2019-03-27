clc
close all
clear all
%No olvidar colocar al final el /
absolute_path=...
'/home/javeriana/roger_gomez/DSST2/DSST2/sequences/DSVD/043Exp_OutFIG_FQ_C3.mp4';
[folder_video,name_video,ext_video] = fileparts(absolute_path)
path_mainFolder_Tracking=...
 '/home/javeriana/roger_gomez/DSST2/DSST2/results/'
path_save_results=strcat(path_mainFolder_Tracking,name_video);
mkdir(path_save_results);
x       = 668;
y       = 332;
width   = 124;
height  = 339;
BB=[x y width height]
tracker_dsst(BB,absolute_path, path_save_results)
