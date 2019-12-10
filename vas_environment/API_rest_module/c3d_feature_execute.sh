#!/bin/bash
#feature extraction using c3d
echo "executing C3D module"
cd ../c3d_module
rm -rf ./output/*
python prepare_c3d_execution.py
chmod +x feature_extraction.sh
sh feature_extraction.sh
