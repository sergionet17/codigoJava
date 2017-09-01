package co.gov.movilidadbogota.sipa.commons.log;

import org.slf4j.Logger;

/**
 * Métodos utilitarios para las funciones de log del sistema
 */
public final class LogUtils {

    /**
     * Publica en el log el mensaje y la excepción. Luego lanza la excepción que se pasa como parámetro
     *
     * @param logger    El logger en el que se debe publicar la excepción
     * @param message   El mensaje asociado al error
     * @param throwable La excepción que se debe lanzar
     * @param <E>       El tipo de excepción que se lanza
     * @throws E Siempre arroja la excepción
     */
    public static <E extends Throwable> void logAndRethrow(Logger logger, String message, E throwable) throws E {
        logger.warn(message, throwable);
        throw throwable;
    }

    /**
     * Publica en el log la excepción. Luego lanza la excepción que se pasa como parámetro
     *
     * @param logger    El logger en el que se debe publicar la excepción
     * @param throwable La excepción que se debe lanzar
     * @param <E>       El tipo de excepción que se lanza
     * @throws E Siempre arroja la excepción
     */
    public static <E extends Throwable> void logAndRethrow(Logger logger, E throwable) throws E {
        logAndGetMessage(logger, throwable);
        throw throwable;
    }

    /**
     * Publica en el log la excepción y devuelve el mensaje de la excepción
     *
     * @param logger    El logger en el que se debe publicar la excepción
     * @param throwable La excepción que se debe lanzar
     * @return El mensaje de la excepción
     */
    public static String logAndGetMessage(Logger logger, Throwable throwable) {
        Throwable cause = throwable.getCause();
        String message = throwable.getMessage();
        logger.warn(message, cause == null ? throwable : cause);
        return message;
    }
}
