mkdir -p /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/output/video_input/CR26_CL80C_QUINTAS_SOL_C-14-14_2017-07-14_07-40-00
mkdir -p /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/output/video_input/Av_4N_CL44_2017_06_28_18_17_26
mkdir -p /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/output/video_input/CR15_CL9-10_MIO_SAN_BOSCO_2017-03-26_19-15-30
mkdir -p /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/output/video_input/CR11_CL15_2016_03_17_16_11_50
mkdir -p /home/javeriana/carpeta_roger/librerias/C3D/C3D-v1.0/examples/c3d_feature_extraction_one/output/video_input/CR28D_CL72Y_EL_POLI-13-8_2017-06-19_01-43-00
GLOG_logtosterr=1 ../../build/tools/extract_image_features.bin prototxt/c3d_sport1m_feature_extractor_video.prototxt conv3d_deepnetA_sport1m_iter_1900000 0 1 2400 prototxt/my_output_list_video_prefix.txt fc6-1