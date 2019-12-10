#!/bin/bash
#applying water mark and generating anomaly score curve
echo "executing water mark generator module"
cd ../watermark_generator_module
name_video="$1"
python make_out_anl.py $name_video
