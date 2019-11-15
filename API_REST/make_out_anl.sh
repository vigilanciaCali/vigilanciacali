#!/bin/bash

cd /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/script_UCF/

#name_video="'video.mp4'"
#path_video_out="'/media/javeriana/TOSHIBA_2TB/carpeta_roger/programas/API_REST_PYTHON/dir_test_output/'"
#name_video_out="'out'"

name_video="$1"
path_video_out="$2"
name_video_out="$3"


matlab -nodisplay -nodesktop -r "make_out_anl('$name_video','$path_video_out','$name_video_out');exit;"


