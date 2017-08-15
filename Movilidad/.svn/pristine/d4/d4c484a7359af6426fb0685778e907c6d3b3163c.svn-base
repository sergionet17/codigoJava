package co.gov.movilidadbogota.sipa.util;

import co.gov.movilidadbogota.sipa.commons.log.LogUtils;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by acpreda on 7/8/17.
 */
public final class ControllerUtils {

    /**
     * Asigna el mensaje al slot del modelo correspondiente al error genérico
     *
     * @param model   El modelo en el que se debe agragar el mensaje
     * @param message El mensaje
     */
    public static void setModelError(Model model, String message) {
        String msg = message;
        if(StringUtils.isBlank(msg)) {
            msg = "Ocurrió un error inesperado";
        }
        model.addAttribute(Constants.FLASH_MESSAGE_ERROR, msg);
    }

    /**
     * Maneja una excepción realizando la publicación necesaria
     *
     * @param logger    El log
     * @param model     El modelo
     * @param throwable La excepción
     */
    public static void handleException(Logger logger, Model model, Throwable throwable) {
        setModelError(model, LogUtils.logAndGetMessage(logger, throwable));
    }

    /**
     * Clase para empaquetar la información que se recolecta para la transformación de una lista
     * de entidades a un mapa
     */
    private static class ListToMapEntry {
        Class<?> clazz;
        Method idGetter;
        Method[][] labelGetters;
    }

    /**
     * Mapa que mantiene en memoria la información que se recolecta por reflection acerca de las
     * clases de entidad. Esto se usa para la tranformación de lista a mapa.
     */
    private static final Map<Class<?>, ListToMapEntry> listMapCache = new HashMap<>();

    /**
     * Inicializa el mapa para para transformar una lista de entidades a un mapa
     *
     * @param collection La colección de entidades
     * @param labelField La descripción de propiedades que se debe usar para la construcción del valor del mapa. Es
     *                   una cadena de texto separada por coma en la cual cada elemento es la ruta para llegar a una
     *                   propiedad. Por ejemplo "persona.nombre, persona.apellido, persona.identifiacion.numero".
     * @return Un mapa en el que la clave es el string de la propiedad que está marcada con la anotación @Id y el valor
     * es el resultado de concatenar todos los toString de las propiedades que se expresan en labelField
     */
    public static <T> Map<String, String> listToMap(Collection<T> collection, String labelField) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        listToMap(collection, labelField, map);
        return map;
    }

    /**
     * Inicializa el mapa para por defecto para transformar una lista de entidades a un mapa
     *
     * @param collection La colección de entidades
     * @param labelField La descripción de propiedades que se debe usar para la construcción del valor del mapa. Es
     *                   una cadena de texto separada por coma en la cual cada elemento es la ruta para llegar a una
     *                   propiedad. Por ejemplo "persona.nombre, persona.apellido, persona.identifiacion.numero".
     * @param <T>        Es el tipo de la entidad.
     * @param locale
     * @return Un mapa en el que la clave es el string de la propiedad que está marcada con la anotación @Id y el valor
     * es el resultado de concatenar todos los toString de las propiedades que se expresan en labelField
     */
    public static <T> Map<String, String> listToMapWithDefault(Collection<T> collection, String labelField, MessageSource messageSource, Locale locale) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(
                Constants.LIST_TO_MAP_EMPTY_KEY,
                messageSource.getMessage(
                        Constants.LIST_TO_MAP_EMPTY_VALUE_MESSAGE_CODE,
                        new Object[0],
                        locale
                )
        );
        listToMap(collection, labelField, map);
        return map;
    }

    /**
     * Transforma una lista de entidades a un mapa
     *
     * @param collection La colección de entidades
     * @param labelField La descripción de propiedades que se debe usar para la construcción del valor del mapa. Es
     *                   una cadena de texto separada por coma en la cual cada elemento es la ruta para llegar a una
     *                   propiedad. Por ejemplo "persona.nombre, persona.apellido, persona.identifiacion.numero".
     * @param <T>        Es el tipo de la entidad.
     */
    public static <T> void listToMap(Collection<T> collection, String labelField, Map<String, String> map) {


        // Verifica que haya algo que procesar, si no entonces devuelve de inmediato
        if (collection == null || collection.size() == 0)
            return;

        // Obtiene el tipo de entidad del primer elemento de la colección. Como ya se verificó la existencia de por lo
        // menos un elemento entonces es seguro invocar diréctamente iterador;
        Class<?> clazz = collection.iterator().next().getClass();

        // Verifica si la clase ya ha sido inspeccionada y en caso que no sea así entonces recolecta la información de
        // la clase por reflection y la almacena en el cache para su posterior uso
        ListToMapEntry entry = listMapCache.get(clazz);
        if (entry == null) {

            entry = new ListToMapEntry();
            entry.clazz = clazz;
            Boolean superclass = null;

            // Busca el campo que esté marcado con la anotación @Id
            Field idField = null;
            for (Field f : clazz.getDeclaredFields()) {
                Id id = f.getAnnotation(Id.class);
                if (id != null) {
                    superclass = false;
                    idField = f;
                    break;
                }
            }

            //Se verifica si el id se encuentra en la clase padre
            if (idField == null) {
                for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                    Id id = f.getAnnotation(Id.class);
                    if (id != null) {
                        superclass = true;
                        idField = f;
                        break;
                    }
                }
            }
            // Si no se encontró el campo marcado con @Id entonces arroja una excepción de tiempo de ejecución
            if (idField == null)
                throw new RuntimeException(String.format("No @Id field found for class %s", clazz.getName()));

            // Obtiene el getter correspondiente al campo de identificación. En caso que no se encuentre se arroja
            // una excepción de tiempo de ejecución
            try {
                if (superclass) {
                    //En caso de que el id se encuentre en la clase padre
                    entry.idGetter = clazz.getSuperclass().getDeclaredMethod(
                            "get" + idField.getName().substring(0, 1).toUpperCase() + idField.getName().substring(1));
                } else {
                    entry.idGetter = clazz.getDeclaredMethod(
                            "get" + idField.getName().substring(0, 1).toUpperCase() + idField.getName().substring(1));
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(
                        String.format("No getter found for field %s in class %s",
                                idField.getName(), clazz.getName()), e);
            }

            // Verifica que haya información para construir los valores del mapa
            if (labelField == null || labelField.isEmpty())
                throw new RuntimeException(String.format("'labelFields' is not defined for clazz '%s'", clazz));

            // Determina la cantidad de cadenas de métodos que se definen en la expresión labelFields e inicializa el
            // arreglo para las cadenas
            String[] labelExpressions = labelField.split(",");
            entry.labelGetters = new Method[labelExpressions.length][];

            // Por cada una de las expresiones se obtiene la cadena de getters que se usa para construir cada elemento
            int chainIndex = 0;
            for (String expr : labelExpressions) {
                if (expr.isEmpty())
                    continue;
                expr = expr.trim();

                // Parte la expresión por el separador '.' e inicia el arreglo de métodos correspondiente a la cadena
                // con el tamaño de elementos que componen el camino
                String[] pathElements = expr.split("[.]");
                entry.labelGetters[chainIndex] = new Method[pathElements.length];

                // Por cada elemento del camino obtiene los métodos que se deben invocar sucesivamente
                Class<?> currentClass = clazz;
                int pathIndex = 0;
                for (String pathElement : pathElements) {
                    // Obtiene el método y cambia la clase actual a la que devuelve el getter para poder continuar
                    // con la siguiente iteración
                    try {
                        String getterName = "get" + pathElement.substring(0, 1).toUpperCase() + pathElement.substring(1);
                        Method getter = currentClass.getDeclaredMethod(getterName);
                        entry.labelGetters[chainIndex][pathIndex] = getter;
                        currentClass = getter.getReturnType();
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(
                                String.format("Method not found in class %s", currentClass.getName()), e);
                    }
                    pathIndex++;
                }
                chainIndex++;
            }

            // Guarda en el cache la información recolectada
            listMapCache.put(clazz, entry);
        }

        // Por cada uno de los elementos de la colección aplica las cadenas de métodos para construir el mapa
        for (T x : collection) {

            // Obtiene la clave
            Object key;
            try {
                key = entry.idGetter.invoke(x);
            } catch (Exception e) {
                throw new RuntimeException("Cannot obtain key value for " + clazz);
            }

            // Obtiene el valor
            StringBuilder value = new StringBuilder();
            for (int i = 0; i < entry.labelGetters.length; i++) {
                Method[] chain = entry.labelGetters[i];
                Object currentObject = x;
                for (Method method : chain) {
                    try {
                        currentObject = method.invoke(currentObject);
                    } catch (Exception e) {
                        throw new RuntimeException(
                                String.format("Cannot obtain value for class %s", clazz));
                    }
                }
                value.append(i == 0 ? "" : " ").append(currentObject == null ? "" : currentObject);
            }
            // Adiciona el par clave valor al mapa
            map.put(key.toString(), value.toString());
        }

    }

    /**
     * Permite convertir un valor string a otra clase
     *
     * @param clazz Clase a la que se desea convertir
     * @param value Valor string a convertir
     */
    public static Object stringToObject(Class clazz, String value) {
        if (Boolean.class == clazz) return Boolean.parseBoolean(value);
        if (Byte.class == clazz) return Byte.parseByte(value);
        if (Short.class == clazz) return Short.parseShort(value);
        if (Integer.class == clazz) return Integer.parseInt(value);
        if (Long.class == clazz) return Long.parseLong(value);
        if (Float.class == clazz) return Float.parseFloat(value);
        if (Double.class == clazz) return Double.parseDouble(value);
        if (String.class == clazz) return value;
        return value;
    }


}
