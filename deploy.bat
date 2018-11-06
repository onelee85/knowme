@echo off

@call gradle build

if %ERRORLEVEL% EQU 0 (
	echo ================SUCCESS=======================
	call:pubApi 192.168.31.82 "publish to 192.168.31.82"
	echo ================SUCCESS=======================
	sleep 15
	
) else (
	COLOR C
	    echo -------         !! FAILD !!      -------------
	pause
)
exit  

:pubApi
scp build/libs/knowme-0.1.0.jar root@%~1:~/project/
ssh root@%~1  "source /etc/profile;cd ~/project;mv knowme-0.1.0.jar knowme.jar;./run.sh restart"