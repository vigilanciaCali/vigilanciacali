function  call_tracker(path_video)
% absolute_path=...
% '/home/javeriana/roger_gomez/DSST2/DSST2/sequences/DSVD/043Exp_OutFIG_FQ_C3.mp4'
x=668;
y=332;
width=124;
height=339;
BB=[x y width height]
path_mainFolder_Tracking= '/home/javeriana/roger_gomez/DSST2/DSST2/sequences/DSVD/results'
tracker_dsst(BB,path_video,path_mainFolder_Tracking)
end
