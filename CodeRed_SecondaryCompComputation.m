%% Connect to Device's Matlab

clear all; close all; clc;

 

connector('on', 'password', 'codered');

% Check to make sure that the app runs in the background

 

%% Fill out the IP address and password on the device

 

% Turn on all Sensors

history = []

 

m = mobiledev

 

m.AccelerationSensorEnabled = 1

m.AngularVelocitySensorEnabled = 1

m.MagneticSensorEnabled = 1

m.OrientationSensorEnabled = 1

m.PositionSensorEnabled = 1

m.Logging = 1

m.SampleRate = 'medium'; %low= 1 medium = 10 high = 100Hz

m.Connected

 

%% Connect to secondary computer; 

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)
  

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)

[lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

pause(3)

 

for i = 1:1000000000000
tcpipServer = tcpip('0.0.0.0',3000,'NetworkRole','Server');

    set(tcpipServer);

    fopen(tcpipServer);
    %% Log the variables: This updates the logs

    [AccLog, timestamp] = accellog(m);

    [AndVelLog, timestamp] = angvellog(m);

    [MagSenLog, timestamp] = magfieldlog(m);

    [OriSenLog, timestamp] = orientlog(m);

    [lat, lon, timestamp, speed, course, alt, horizacc] = poslog(m);

    lat = double(lat);

    lon = double(lon);

    fwrite(tcpipServer,lat,'double');

    fwrite(tcpipServer,lon,'double');

    fclose(tcpipServer);

end

 



 

 

 

 

 %% To close

m.Logging = 0

m.AccelerationSensorEnabled = 0

m.AngularVelocitySensorEnabled = 0

m.MagneticSensorEnabled = 0

m.OrientationSensorEnabled = 0

m.PositionSensorEnabled = 0

clear m;