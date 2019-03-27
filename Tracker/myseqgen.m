clear
clc

fvideos = '../videos/';
fname = 'pedestrian3';

load([fvideos, fname '.mat'])

nimg = size(frames,4);
ndig = length(num2str(nimg));
for ii = 1:nimg
    imwrite(squeeze(frames(:,:,:,ii)),['./sequences/' fname '/imgs/img' num2str(ii,['%0' num2str(ndig) '.f']) '.jpg'])
end

load([fvideos, fname '_gt.mat'])

dlmwrite(['./sequences/' fname '/' fname '_gt.txt'],gtP)

dlmwrite(['./sequences/' fname '/' fname '_frames.txt'],[1, nimg])