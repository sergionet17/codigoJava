package co.gov.movilidadbogota.sipa.serv.comparendos;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Acumulador de errores de la validación de {@link co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo}
 */
public class FormatoComparendoValidatorErrors {

    /**
     * Mapa para almacenar los mensajes de error
     */
    private Map<String, List<String>> map = new HashMap<>();

    /**
     * Registra un error asociado a un nombre de campo
     *
     * @param fieldName El nombre del campo que presenta el error
     * @param message   El mensaje de error
     */
    public void reject(String fieldName, String message) {
        List<String> list = map.get(fieldName);
        if (list == null) {
            list = new ArrayList<>();
            map.put(fieldName, list);
        }
        list.add(message);
    }

    /**
     * Devuelve un mapa inmutable con los valores rechazador. Se devuelve
     * de esta manera para evitar el mal uso de estos errores por fuera de
     * las cadenas de validación.
     *
     * @return un mapa inmutable con los valores rechazados
     */
    public Map<String, List<String>> toMap() {
        return ImmutableMap.copyOf(map);
    }

    /**
     * Verifica si existen errores
     *
     * @return true si hay errores, false de lo contrario
     */
    public boolean hasErrors() {
        return !map.isEmpty();
    }

}
