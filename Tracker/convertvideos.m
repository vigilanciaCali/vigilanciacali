k=1;

video=30;
sections=6;
frames=823;

path=strcat('E:/Javeriana_OTV/AppDrivenTracker/videos70/video',num2str(video),'/groundtruth_sections.txt');


for i=1:size(gTruth.LabelData.vid,1)

    if isempty(gTruth.LabelData.vid{i}) == 0
        
        gtP(k,:)=gTruth.LabelData.vid{i};
        k=k+1;
    end
    
    csvwrite(path,gtP);
    
end

out_path = strcat('E:/Javeriana_OTV/AppDrivenTracker/videos70/video',num2str(video),'/groundtruth_rect.txt');

[~,gtP]=interpolate(path,out_path,sections,frames);

%save(strcat('E:/Javeriana_OTV/AppDrivenTracker/videos70/video1/video',num2str(j),'.mat'),gtP);
