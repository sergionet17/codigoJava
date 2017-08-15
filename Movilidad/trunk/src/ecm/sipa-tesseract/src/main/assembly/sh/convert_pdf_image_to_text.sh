#!/bin/bash
ALFRESCO_HOME=/opt/alfresco-community
LOG_FILE=/opt/alfresco-community/alfresco.log
TESSERACT=/bin/tesseract
CONVERT=/bin/convert

# Revisar parametros
source="$1"
target="$2"
if [[ -z $source ]]; then
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] $0 fue invocado sin parametro 1 >> $LOG_FILE
 exit 1
fi
if [[ -z $target ]]; then
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] $0 fue invocado sin parametro 2 >> $LOG_FILE
 exit 1
fi
echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] Convirtiendo $source en $target >> $LOG_FILE

# Intentar pdf2text
TEMP_LOG=$(mktemp)
TEMP_FILE=$(mktemp)
pdftotext -nopgbrk $source $TEMP_FILE &> /dev/null
FILESIZE=$(stat -c%s "$TEMP_FILE")
if [ -s $TEMP_FILE ]; then
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] pdftotext leyo $FILESIZE bytes >> $LOG_FILE
 mv $TEMP_FILE  $target
 exit 0
fi
rm $TEMP_FILE

echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] convirtiendo PDF en TIFF >> $LOG_FILE
# Para usar tesseract tenemos que convertir el PDF en TIFF
$CONVERT  $source ${TEMP_FILE}.tiff &> $TEMP_LOG
if [ $? -ne 0 ]; then
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] No fue posible convertir a tiff >> $LOG_FILE
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] `cat $TEMP_LOG`  >> $LOG_FILE
 rm $TEMP_LOG
 exit 1
fi
# Intentar tesseract
$TESSERACT ${TEMP_FILE}.tiff  $TEMP_FILE -l spa &> $TEMP_LOG
FILESIZE=$(stat -c%s "${TEMP_FILE}.txt")
echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] tesseract leyo $FILESIZE bytes >> $LOG_FILE
EXIT=2
if [ -s "${TEMP_FILE}.txt" ]; then
 mv ${TEMP_FILE}.txt $target
 EXIT=0
else
 echo [`date  +"%Y-%m-%d %H:%M:%S"`] [convert] [$source] `cat $TEMP_LOG`  >> $LOG_FILE
fi
rm ${TEMP_FILE}.tiff &> /dev/null
rm ${TEMP_FILE}.txt &> /dev/null
rm $TEMP_LOG &> /dev/null
exit $EXIT

