package co.gov.movilidadbogota.sipa.serv.api;

import java.util.HashMap;

/**
 * Contexto mediante el cual se pasan parámetros hacia, entre y desde comandos
 */
public class CommandContext extends HashMap<String, Object> {

    /**
     * Adiciona un par clave-valor al contexto
     *
     * @param key   Clave
     * @param value Valor
     * @return Una referencia al contexto con el que se implementa el patrón
     * "builder".
     */
    public CommandContext put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * Obtiene un valor por la clave y hace la conversión de tipo a la clase
     * solicitada.
     *
     * @param key   La clave de búsqueda del valor
     * @param clazz La clase a la que se debe convertir el valor
     * @param <T>   El tipo de dato para realizar la conversión
     * @return El valor correspondiente a la clave solicitada
     */
    public <T> T get(String key, Class<T> clazz) {
        Object o = super.get(key);
        if (o == null) return null;
        if (clazz.isAssignableFrom(o.getClass())) return (T) o;
        else throw new ClassCastException(String.format(
                "El objeto '%s' no se puede convertir a '%s' para la clave '%s'", o, clazz, key
        ));
    }

}
