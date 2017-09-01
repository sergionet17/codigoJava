#!/bin/bash
ALFRESCO_HOME=/opt/alfresco-community
LOG_FILE=/opt/alfresco-community/alfresco.log
TESSERACT=/bin/tesseract
CONVERT=/bin/convert

# Revisar parametros
source="$1"
target="$2"
if [[ -z $source ]]; then
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convertimg] $0 fue invocado sin parametro 1 >> $LOG_FILE
 exit 1
fi
if [[ -z $target ]]; then
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convertimg] $0 fue invocado sin parametro 2 >> $LOG_FILE
 exit 1
fi
echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convertimg] [$source] Convirtiendo $source en $target >> $LOG_FILE

# Intentar pdf2text
TEMP_LOG=$(mktemp)
TEMP_FILE=$(mktemp)

$TESSERACT $source  $TEMP_FILE -l spa &> $TEMP_LOG
FILESIZE=$(stat -c%s "${TEMP_FILE}.txt")
echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convertimg] [$source] tesseract leyo $FILESIZE bytes >> $LOG_FILE
EXIT=2
if [ -s "${TEMP_FILE}.txt" ]; then
 mv ${TEMP_FILE}.txt $target
 EXIT=0
else
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convertimg] [$source] `cat $TEMP_LOG`  >> $LOG_FILE
fi
rm ${TEMP_FILE}.tiff &> /dev/null
rm $TEMP_FILE &> /dev/null
rm ${TEMP_FILE}.txt &> /dev/null
rm $TEMP_LOG &> /dev/null
exit $EXIT

