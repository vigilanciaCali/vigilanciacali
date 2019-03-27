% save all frames in a video, in jpg format

clc;
close all;
clear all;
% This code extracts all frames from a video, in an specific format and
% size. Too generate a txt with the range of number of frames existent
%Roger Gomez Nieto - rogergomez@ieee.org    
% Last modified 14-august-2018

destinationFolder = 'C:\Users\roger\Downloads\DSST2\DSST2\sequences\DSVD\imgs';
Name_Video='043Exp_OutFIG_FQ_C3.mp4';
Want_Resize = 0 % write 1 if want resize frame
desired_size = [227 227]; %Resolution desired if wants resize frame
Name_Images         = '';  %Initial string, desired sufix for name of image
%type of format desired
Desired_Type_File   ='.jpg';
a                   =VideoReader(Name_Video);
Number_Frames_Video =a.NumberOfFrames;
%Add an offset for don't initiate since frame 1
Last_Numbre=0;
for img = 1+Last_Numbre:Number_Frames_Video+Last_Numbre
    %esta numeración es la que pide el tracker STRUCK para no dar error en
    %la consecución de los frames. 
    filename        =strcat(Name_Images,num2str(img,'%08i'),Desired_Type_File);
    fullDestinationFileName = fullfile(destinationFolder, filename);
    b               = read(a, img-Last_Numbre); 
    if Want_Resize == 1
        b               = imresize(b , desired_size); %Si se quiere convertir en QVGA
    end
    %imshow(b);
    imwrite(b,fullDestinationFileName,'jpg');
    img
end
%this part generate a txt file with number of frames
%last_frame = length(number_frames_obtained);
last_frame=img
name_file_frames=strcat(Name_Video,'_frames.txt');
fullDestinationFile_frames = fullfile(destinationFolder, name_file_frames);
fileID = fopen(fullDestinationFile_frames,'w');
nbytes = fprintf(fileID,'1,%d',last_frame);
fclose(fileID);