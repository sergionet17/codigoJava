package co.gov.movilidadbogota.sipa.serv.financiero;

/**
 * Interfaz de un predicado
 *
 * @author arturo.cruz
 */
public interface Predicate<T> {

    /**
     * Verifica si la condición se cumple
     *
     * @param t el objeto para el cual se debe verificar la condición
     * @return true si el objeto cumple la condición o falso de lo contrario
     */
    boolean test(T t);
}
