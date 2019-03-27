%comment this line in the first execution  
%stop(timerObj)


  clear all
  clc
%se le pasa la carpeta donde esta alojada la carpeta new_video, la otra
%funcion no es tan importante porque igual el tracker lo llamo desde la
%funcion detectfile
timerObj = ...
detectFile('/home/javeriana/roger_gomez/DSST2/DSST2/sequences/DSVD', '/home/javeriana/roger_gomez/DSST2/DSST2/call_tracker.m')
% timerObj=detectFile('/home/javeriana/new_videos_input', '/home/javeriana/roger_gomez/DSST2/DSST2/call_tracker.m')
 