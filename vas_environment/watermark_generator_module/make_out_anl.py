#!../bin/python
import sys
import os
import numpy as np
from scipy.io import loadmat
from scipy import signal

import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt

import cv2


#name_video="Robbery106_x264.avi"
name_video=sys.argv[1]


temp0=name_video.split('.')
name_video=temp0[0]
format_video=temp0[1]

path_video_input=os.path.join("../c3d_module/input/video_input",name_video)+"."+format_video
path_scores="../fc_module/score_mat"


mat_data=loadmat(os.path.join(path_scores,name_video)+"_C.mat")
scores=mat_data['scores']

 


cap = cv2.VideoCapture(path_video_input)
if(not(cap.isOpened())):
   sys.exit("File "+path_video_input+" not found")
  
length=int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
fps=cap.get(cv2.CAP_PROP_FPS)
duration=int(length/fps)

vec_scores=signal.resample(scores[0,0:-1],length)
axis_time=np.linspace(0, duration, length, endpoint=False)
threshold=np.mean(vec_scores)
vec_threshold=threshold*np.ones(length);

plt.plot(axis_time,vec_scores)
plt.plot(axis_time,vec_threshold, 'r--')
plt.xlabel('tiempo (s)')
plt.ylabel('score')
plt.axis([0, duration, 0, 1.3])
plt.legend(['score anomalía','umbral'], loc='upper right')
plt.savefig('score_out.png')


frame_width = int(cap.get(3))
frame_height = int(cap.get(4))
out_video=cv2.VideoWriter('video_out.mp4',cv2.VideoWriter_fourcc(*'MP4V'), fps, (frame_width,frame_height))

temp1=cv2.imread('waterMark.png');
temp2= cv2.cvtColor(temp1,cv2.COLOR_BGR2GRAY)
temp3=cv2.resize(temp2, (int(0.2*frame_width),int(0.2*frame_height)), interpolation=cv2.INTER_AREA);
water_mark=np.zeros((frame_height,frame_width),'uint8')#the image is 0 in mask and 255 in non-mask
x_warning=frame_width-temp3.shape[1]-8
y_warning=8
water_mark[y_warning:y_warning+temp3.shape[0],x_warning:x_warning+temp3.shape[1]]=temp3


cnt=0
time_warning=0;

while(cap.isOpened()):
    ret, frame = cap.read()
    if ret == True:

       if(vec_scores[cnt]>threshold):        
          time_warning=4
       else:
          if(time_warning>0):
            time_warning=time_warning-1

       if(time_warning>0):
          if(cnt%3>0):
           frame[water_mark>0,0]=0.3*frame[water_mark>0,0]
           frame[water_mark>0,1]=0.3*frame[water_mark>0,1]
           frame[water_mark>0,2]=255 
           frame=frame.astype(np.uint8)
       

       out_video.write(frame)
       cnt=cnt+1
      
    else: 
      break


cap.release()
out_video.release()

