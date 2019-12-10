#!../bin/python
import os
import sys
import cv2
import math


path_extract_image_features_bin="path/to/build/tools"
base_path_input="./input"
base_path_output="./output"
base_path_prototxt="./prototxt"


mini_batch_size=50
number_of_mini_batches=0
l_c3d=16


file_input_list_video=open(os.path.join(base_path_prototxt,"input_list_video.txt"), "w") 
file_output_list_video=open(os.path.join(base_path_prototxt,"output_list_video_prefix.txt"), "w") 
file_sh=open("feature_extraction.sh","w")


number_clips=0;

for subdir in os.listdir(base_path_input):
  print("****** Prepare folder "+subdir+" *****")
  path_subdir=os.path.join(base_path_input,subdir)
  path_subdir_out=os.path.join(base_path_output,subdir)
  #print(path_subdir)
  #c = sys.stdin.read(1)
  for name_video in os.listdir(path_subdir):
    print("* Prepare video "+name_video)
    path_video_input=os.path.join(path_subdir,name_video)
    path_c3d_features=os.path.join(path_subdir_out,name_video[0:-4])
    file_sh.write("mkdir -p "+path_c3d_features+"\n")
    #print(path_video)
    cap = cv2.VideoCapture(path_video_input)
    length=int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    
    print("** Number of frames: "+str(length))    
    print("** FPS: "+str(cap.get(cv2.CAP_PROP_FPS)))
 
    n=0
    while n<=(length-l_c3d):
       file_input_list_video.write(path_video_input+" "+str(n)+" 0\n")
       file_output_list_video.write(path_c3d_features+"/%06d" % n +"\n")
       n=n+l_c3d
       number_clips=1+number_clips



#NOTE: the algorith of UCF works whit fc6-1
number_of_mini_batches=math.ceil(float(number_clips)/float(mini_batch_size));
file_sh.write("GLOG_logtosterr=1 "+os.path.join(path_extract_image_features_bin,"extract_image_features.bin")+" prototxt/c3d_sport1m_feature_extractor_video.prototxt prototxt/conv3d_deepnetA_sport1m_iter_1900000 0 "+str(mini_batch_size)+" "+str(number_of_mini_batches)+" prototxt/output_list_video_prefix.txt fc6-1")

file_input_list_video.close()
file_output_list_video.close()
file_sh.close()
