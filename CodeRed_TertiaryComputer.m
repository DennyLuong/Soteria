clear all; close all; clc;


hist = []
hist2 = []
distance = []
for w=1:100000000
    %%comp me
    pause(3)
tcpipClient = tcpip('172.25.145.231',55000) %,'Network Role','Client')
pause(1)
fopen(tcpipClient);
[lat] = fread(tcpipClient,1,'double');
hist = [hist lat]
[lon] = fread(tcpipClient,1,'double');
hist = [hist lon]
fclose(tcpipClient)

%% comp cruz
pause (3)
tcpipClient = tcpip('172.25.4.23',3000) %,'Network Role','Client')
pause(1) 
fopen(tcpipClient);
[lat] = fread(tcpipClient,1,'double');
hist2 = [hist2 lat]
[lon] = fread(tcpipClient,1,'double');
hist2 = [hist2 lon]

fclose(tcpipClient)

%% distance between latitudes and longitudes
if isempty(hist)>=0
    disp 'not ok'
elseif isempty(hist)<0
    lats = [hist(1,end-1) hist2(1,end-1)];
lons = [hist(1,end) hist2(1,end)];
dist = stdist (lats,lons);
distrans = deg2mi (dist);
distance = [distance disttrans];
end
end

fclose(tcpipClient)