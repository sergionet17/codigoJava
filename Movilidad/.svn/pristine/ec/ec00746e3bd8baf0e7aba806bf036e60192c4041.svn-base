#!/bin/bash

SVN_USER="arturo.cruz"
SVN_PASSWORD="1234rewq"
SVN_URL="http://svn.ingenian.com/Movilidad/trunk/src"
SVN_WDIR="src/sipa"

SVN_CMD="svn --username $SVN_USER --password $SVN_PASSWORD "
SVN_CMD_UPDATE="$SVN_CMD up $SVN_WDIR "
SVN_CMD_CHECKOUT="$SVN_CMD co $SVN_URL $SVN_WDIR "

MVN_CMD="./mvnw -Dmaven.test.skip=true"
MVN_CLEAN_INSTALL="$MVN_CMD clean install"

echo "Getting source code..."
if [ -d "$HOME/src/sipa" ]; then
	ssh $1 "$SVN_CMD_UPDATE"
else
	ssh $1 "$SVN_CMD_CHECKOUT"
fi

#echo "Installing Oracle driver..."
#scp "../lib/ojdbc8-12.2.0.1.jar" "$1:"
#ssh $1 ". .bash_profile ; pushd $SVN_WDIR ; $MVN_CMD install:install-file -Dfile=\$HOME/ojdbc8-12.2.0.1.jar -DgroupId=com.oracle.jdbc -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar"
#ssh $1 "rm ojdbc8-12.2.0.1.jar"

echo "Building the system..."
#ssh $1 ". .bash_profile ; pushd $SVN_WDIR ; $MVN_CLEAN_INSTALL -f sipa-pmd ; popd"
ssh $1 ". .bash_profile ; pushd $SVN_WDIR ; $MVN_CLEAN_INSTALL ; popd"

echo "Installing the system..."
ssh $1 "mkdir -p lib/sipa-serv"
scp "$2" "$1:lib/sipa-serv/application.yml"
ssh $1 "cp $SVN_WDIR/sipa-serv/target/sipa-serv*.jar lib/sipa-serv"

echo -n "Killing previous app..."
ssh $1 "cd /home/sipaserv ; kill \$(cat lib/sipa-serv/sipa-serv.pid)"
echo "OK"

echo "Sleeping..." && sleep 20

echo -n "Starting application in background..."
ssh $1 ". .bash_profile ; cd lib/sipa-serv ; nohup java -jar sipa-serv*.jar &> sipa-serv.log < /dev/null & echo \$! > sipa-serv.pid"
echo "OK"

ssh $1 "tail -f lib/sipa-serv/sipa-serv.log"
