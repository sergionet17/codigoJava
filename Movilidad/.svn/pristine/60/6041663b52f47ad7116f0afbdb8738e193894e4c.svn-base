package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clases con atributos constantes de todo el sistema
 *
 * @author paola.charry
 */
public class Constants {

    /**
     * Patrón de formato de fecha que aplica a todo el sistema
     */
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    /**
     * Patrón de formato de fecha dd/mm/yy que se solicita para los reportes
     */
    public static final String DATE_FORMAT_PATTERN_DDMMYY = "dd/MM/yy";

    /**
     * Patrón de formato de tiempo que aplica a todo el sistema
     */
    public static final String TIME_FORMAT_PATTERN = "HH:mm";

    /**
     * Patrón de formato de fecha que aplica a JQuery DatePicker. Este patrón debe coincidir con
     * DATE_TIME_FORMAT_PATTERN
     */
    public static final String JQUERY_DATETIME_FORMAT_PATTERN = "Y-m-d H:i";

    /**
     * Patrón de formato de fecha que aplica a JQuery DatePicker. Este patrón debe coincidir con
     * DATE_FORMAT_PATTERN
     */
    public static final String JQUERY_DATE_FORMAT_PATTERN = "Y-m-d";

    /**
     * Patrón de formato de tiempo que aplica a JQuery DatePicker. Este patrón debe coincidir con
     * TIME_FORMAT_PATTERN
     */
    public static final String JQUERY_TIME_FORMAT_PATTERN = "H:i";

    /**
     * Patrón de formato de fecha y hora globales para el sistema
     */
    public static final String DATE_TIME_FORMAT_PATTERN = DATE_FORMAT_PATTERN + " HH:mm";

    /**
     * Objeto único para serializar y deserializar fechas con el formato estándar que aplica
     * a nivel global del sistema TIME_FORMAT_PATTERN
     */
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT_PATTERN);

    /**
     * Objeto único para serializar y deserializar fechas con el formato estándar que aplica
     * a nivel global del sistema DATE_FORMAT_PATTERN
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    /**
     * Objeto único para serializar y deserializar fechas con hora en el formato estándar que aplica
     * a nivel global del sistema DATE_TIME_FORMAT_PATTERN
     */
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN);

    public static final String CURRENCY_FORMAT = "";

    /**
     * Constante que identifica el nombre de los mensajes flash exitosos
     */
    public static final String FLASH_MESSAGE_OK = "FLASH_MESSAGE_OK";

    /**
     * Constante que identifica el nombre de los mensajes flash con error
     */
    public static final String FLASH_MESSAGE_ERROR = "FLASH_MESSAGE_ERROR";

    /**
     * Constante que identifica el organismo de transito de la secretaria de movilidad de bogota
     */
    public static final String ORGANISMO_TRANSITO_SDM = "11001000";
    /**
     * Constante que identifica la cuenta de la empresa que hace parte del codigo de barras
     */
    public static final String RECAUDO_IDENTIFICADOR_EMPRESA = "(415)7707202600090";
    /**
     * Constante que identifica la referencia de pago que hace parte del codigo de barras
     */
    public static final String RECAUDO_REFENCIA_PAGO = "(8020)";
    /**
     * Constante que identifica el valor del pago que hace parte del codigo de barras
     */
    public static final String RECAUDO_VALOR_PAGO = "(3900)";
    /**
     * Constante que identifica la fecha de pago que hace parte del codigo de barras
     */
    public static final String RECAUDO_FECHA_PAGO = "(96)";

    /**
     * Implementación de {@link Formatter} para manejar las fechas
     */
    public static final Formatter<Date> DATE_FORMATTER = new Formatter<Date>() {
        @Override
        public String print(Date date, Locale locale) {
            if (date == null)
                return "";
            return Constants.DATE_FORMAT.format(date);
        }

        @Override
        public Date parse(String s, Locale locale) throws ParseException {
            if (s == null || s.isEmpty())
                return null;
            Date date = null;
            try {
                date = Constants.TIME_FORMAT.parse(s);
            } catch (Exception e) {
                // No se hace nada para que continúe con el siguiente formato
            }
            try {
                date = Constants.DATE_TIME_FORMAT.parse(s);
            } catch (Exception e) {
                // No se hace nada para que continúe con el siguiente formato
            }
            try {
                if (date == null)
                    date = Constants.DATE_FORMAT.parse(s);
            } catch (Exception e) {
                throw new IllegalArgumentException("La fecha no es válida");
            }
            return date;
        }
    };

    /**
     * Constante que se usa para armar las rutas de los servicios expuestos por la aplicación
     */
    public static final String API_VERSION = "1";
    public static final String LIST_TO_MAP_EMPTY_KEY = "";
    public static final String LIST_TO_MAP_EMPTY_VALUE_MESSAGE_CODE = "list2map.empty.value";

    /**
     * Constantes para los tipo de contenido de los archivos
     */
    public static final String[] CONTENIDO_IMAGENES = {"image/jpeg",
            "image/png", "image/tiff", "image/gif"};
    public static final String[] CONTENIDO_PDF = {"application/pdf"};
    public static final String[] CONTENIDO_XLSX = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};

    /**
     * Contantes para los MIME Types
     */
    public static final String MIME_TYPE_PDF = "application/pdf";
    public static final String MIME_TYPE_MSWORD = "application/msword";
    public static final String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MEDIA_TYPE_MSWORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_TYPE_TIFF = "image/tiff";

    /**
     * Expresion regular para validar email
     */
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Constantes para valor minimo y maximo de campos
     */
    public static final int VALOR_MAXIMO_NOMBRE = 40;
    public static final int VALOR_MAXIMO_DESCRIPCION = 100;
    public static final int VALOR_MAXIMO_EMAIL = 50;
    public static final int VALOR_MAXIMO_USERNAME = 50;
    public static final int VALOR_MAXIMO_PASSWORD = 50;
    public static final int VALOR_MAXIMO_DEPENDENCIA = 100;
    public static final int VALOR_MAXIMO_CARGO = 100;
    public static final int VALOR_MAXIMO_NUMERO_DOCUMENTO = 40;
    public static final int VALOR_MAXIMO_NUMERO_ACTO = 50;

    /**
     * Regex string de caractes especiales
     */
    public static String SPECIAL_CHARS_REGEX = "[!@#$%^&*()\\[\\]|;',./{}\\\\:\"<>?]";

    /**
     * Regex string de caractes numéricos
     */
    public static String DECIMAL_REGEX_STRING = "^\\\\d+(\\\\.\\\\d{1,10})?$";

}
