function  [Video_BB_Name]= tracker_dsst(BB,path_video,path_mainFolder_Tracking)
%Escribe un código de fallo de tracking, que se sobreescribe al final
%cuando se completa de forma exitosa el tracking

path_mainFolder_Tracking=  '/tmp/rf2'

Y = ['el Bounding Box que ingreso el usuario es ',num2str(BB)];
disp(Y)


% Code_Error=10;
% path_write_ResultsCode='/home/javeriana/vas/tracker/tracker.txt';
% %escribiendo el 100 si el tracker fue exitoso
% fileID = fopen(path_write_ResultsCode,'w');
% nbytes = fprintf(fileID,'%d',Code_Error);
% fclose(fileID);

addpath(genpath('/home/javeriana/roger_gomez/Dropbox/Javeriana/code/phd'))
addpath(genpath('/home/javeriana/roger_gomez/STRCF'))
cd '/home/javeriana/roger_gomez/STRCF'


%suprimiendo warnings de matconvnet
id = 'MATLAB:mpath:nameNonexistentOrNotADirectory';
warning('off',id)

id2='MATLAB:MKDIR:DirectoryExists';
warning('off',id2)




 [Number_Frames_Video, Name_Video]=generate_imgs_groudtruth_from_video(path_video,BB);
 
 %% Tracker DeepSTRCF
 disp('Tracker DeepSTRCF started');
 setup_paths();

%  Load video information
base_path  =  './sequences';
video=Name_Video;
% video  = choose_video(base_path);

video_path = [base_path '/' video];
% esto es para cuando no se tiene o no se quiere todo el ground truth de la
% imagen, entonces se le mete el nùmero de imàgenes de la secuencia y solo
% se mete la primer anotaciòn dle bounding box



length_sequence= Number_Frames_Video;
[seq, gt_boxes] = load_video_info(video_path,length_sequence);

%Si no se quiere visualizar se debe colocar en 0 la linea 64 de
%run_DeepSTRCF: params.visualization = 0; 

% Run STRCF
%results = run_STRCF(seq);

results = run_DeepSTRCF_NoVisualization(seq);
Name_Results=strcat(video,'results_DeepSTRCF.txt');
dlmwrite(Name_Results,results.res,'delimiter',',');

pd_boxes = results.res;
thresholdSetOverlap = 0: 0.05 : 1;
success_num_overlap = zeros(1, numel(thresholdSetOverlap));
res = calcRectInt(gt_boxes, pd_boxes);
for t = 1: length(thresholdSetOverlap)
    success_num_overlap(1, t) = sum(res > thresholdSetOverlap(t));
end
positions_originals=pd_boxes;
disp('tracking successful');

%% generating video with Bounding Box
gt_Initial = positions_originals;
Original_Video=path_video;
Video_BB_Name=strcat(path_mainFolder_Tracking);
vid1 = VideoReader(Original_Video);
writerObj1 = VideoWriter(Video_BB_Name);


writerObj1.FrameRate=vid1.FrameRate;
open(writerObj1);
Number_Of_Frames=vid1.NumberOfFrames
disp('generating output results video');
for i = 1 : vid1.NumberOfFrames
    im=read(vid1,i);
%                 f = @() rectangle('position',[x y w h]);
    f = @() rectangle('position',gt_Initial(i,:));
    params = {'linewidth',2,'edgecolor','r'};
    imgOut = insertInImage(im,f,params);
    writeVideo(writerObj1,imgOut);
    if mod(i,200)==0
        i
    end
end
X = ['el video resultado se guardó en ',num2str(path_mainFolder_Tracking)];
disp(X)
close(writerObj1)


% disp('writing succesful code for tracking');
% Code_OK=100;
% path_write_ResultsCode='/home/javeriana/vas/tracker/tracker.txt';
% %escribiendo el 100 si el tracker fue exitoso
% fileID = fopen(path_write_ResultsCode,'w');
% nbytes = fprintf(fileID,'%d',Code_OK);
% fclose(fileID);
end
