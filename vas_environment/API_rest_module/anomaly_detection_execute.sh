#!/bin/bash
#using Real-world Anomaly Detection https://arxiv.org/abs/1801.04264
echo "executing fully-connected module"
cd ../fc_module
rm -rf ./C3D_Features_txt/*
matlab -r -nodisplay Save_C3DFeatures_32Segments

name_video=$1
python anomaly_detector.py './C3D_Features_txt/'$name_video'_C.txt' './score_mat/'$name_video'_C.mat'

