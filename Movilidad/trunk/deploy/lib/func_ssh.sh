#!/bin/bash

RED=$(tput setaf 1)
GREEN=$(tput setaf 2)
NORMAL=$(tput sgr0)
STATUS_COL=80

DEPLOY_KEY_NAME="$HOME/.ssh/deploy_id_rsa"

function ssh_check_key {
	if [ -f "$DEPLOY_KEY_NAME" ]; then
		return 0;
	else
		return 1;
	fi
}

function ssh_check_login {
	echo -n "Checking autologin with $2@$1..."
	SSH_CMD=$(ssh_cmd_nopassword $1 $2);
	$SSH_CMD -C "date" 
	local ans=$?
	if [ $ans = 0 ];then 
		msg_ok 
	else 
		msg_failed 
	fi
	return $ans
}

function ssh_cmd_nopassword {
	echo "ssh -i $DEPLOY_KEY_NAME -o PasswordAuthentication=No $2@$1"
}

function ssh_login () {
	ssh_check_key
	if [ $? != 0 ];then
		ssh_gen_key
	fi

	ssh_check_login $1 $2
	if [ $? != 0 ];then
		ssh_copy_key $1 $2
		if [ $? != 0 ];then
			exit 1;
		else
			ssh_check_login $1 $2
			if [ $? != 0 ];then
				exit 1;
			fi
		fi
	fi
}

function ssh_gen_key () {
	echo -n "Generating $DEPLOY_KEY_NAME key..."
	ssh-keygen -t rsa -f $DEPLOY_KEY_NAME -N ""
	local ans=$?
	if [ $ans = 0 ];then 
		msg_ok 
	else 
		msg_failed 
	fi
	return $ans
}

function ssh_copy_key () {
	echo -n "Copying $DEPLOY_KEY_NAME key to $2@$1..."
	ssh-copy-id -i ${DEPLOY_KEY_NAME}.pub $2@$1
	local ans=$?
	if [ $ans = 0 ];then 
		msg_ok 
	else 
		msg_failed 
	fi
	return $ans
}

function msg_cur_pos () {
    export $1
    exec < /dev/tty
    oldstty=$(stty -g)
    stty raw -echo min 0
    echo -en "\033[6n" > /dev/tty
    IFS=';' read -r -d R -a pos
    stty $oldstty
    eval "$1[0]=$((${pos[0]:2} - 2))"
    eval "$1[1]=$((${pos[1]} - 1))"
}

function msg_ok {
	msg_cur_pos current_pos;
	local col="$((STATUS_COL - current_pos[1]))"
	printf '%s%*s%s\n' "$GREEN" $col "[  OK  ]" "$NORMAL"
}

function msg_failed {
	printf '%s%*s%s\n' "$RED" $STATUS_COL "[FAILED]" "$NORMAL"
}

ssh_login 10.14.43.110 root
