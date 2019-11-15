#!/bin/bash
cd /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/
#rm -rf ./input/video_input/*
rm -rf ./output/*
rm -rf ./script_UCF/C3D_Features_txt/*

cd my_scripts
. bin/activate
python c3d_secuence.py
deactivate
cd ..
chmod +x my_c3d_sport1m_feature_extraction_video.sh

sh my_c3d_sport1m_feature_extraction_video.sh
cd script_UCF
matlab -r -nodisplay Save_C3DFeatures_32Segments
