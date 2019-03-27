# RogerGomezNieto
Code created/used by Roger Gomez Nieto

The primary function is tracker_dsst. 

function  [Video_BB_Name]= tracker_dsst(BB,path_video,path_mainFolder_Tracking)

The parameters needed to use this tracker are:

Video_BB_Name: The function generates the name of the video with tracking results. 

BB: This is the Bounding Box, with the following structure: [x y width height].

path_video: Path where is located the video to track. 

path_mainFolder_Tracking: Folder where will be saved the resulting video. 
