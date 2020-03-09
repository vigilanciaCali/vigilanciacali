clc;
close all;
clear all;



% Path_Input ='/media/javeriana/HDD_4TB/datasets/LIVE_QUALCOMM/color/'
% Path_Output = '/media/javeriana/HDD_4TB/';
% Name_video = '0918_DelMarView_GS6_20150921_071851.avi'
% Distortion = 'MPEG-4';
% Level = 'high';
% Distort_Video(Path_Input, Path_Output, Name_video, Distortion, Level)

original_bitrate = 16500000;
high = 100000;
medium = 1e6/5;
low = 1e7;
cd '/media/javeriana/HDD_4TB/AppDrivenTracker/videos70/'
% addpath('/media/javeriana/HDD_4TB/AppDrivenTracker/videos70/');
% system('sudo cd /media/javeriana/HDD_4TB/AppDrivenTracker/videos70/');r
system('ls');
name = 'high_mp4.mp4';
system (['ffmpeg -i video1.mp4 -b ' num2str(high) ' ' name]);
system (['ffmpeg -i video1.mp4 -b ' num2str(medium) ' medium_mp4.mp4']);
system (['ffmpeg -i video1.mp4 -b ' num2str(low) ' low_mp4.mp4']);
