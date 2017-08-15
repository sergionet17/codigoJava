#!/bin/bash
. ./env.sh

# Shutdown all services

# Web application
IFS=',' read -ra IPS <<< "$IP_APPLICATION"
for IP in "${IPS[@]}"; do
	echo "Shutting down application in $IP";
	ssh sipaweb@$IP "cd /home/sipaweb ; kill \$(cat lib/sipa-web/sipa-web.pid)";
done

# Services
IFS=',' read -ra IPS <<< "$IP_ESB"
for IP in "${IPS[@]}"; do
	echo "Shutting down services in $IP";
	ssh sipaserv@$IP "cd /home/sipaserv ; kill \$(cat lib/sipa-serv/sipa-serv.pid)";
done

