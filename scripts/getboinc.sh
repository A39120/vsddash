#!/bin/sh 

USER=$(whoami)

yum install boinc-client boinc-manager -y &&
systemctl enable boinc-client && 
systemctl start boinc-client && 
usermod -a -G boinc $USER && 
exec su $USER &&

echo "boincmgr -d /var/lib/boinc" > ~/Desktop/boinc && chmod -x ~/Desktop/boinc 
