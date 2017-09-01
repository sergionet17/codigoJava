package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Transaccion;

/**
 * Estrategia mediante la cual se crean las entradas de una transacción
 */
public interface TransaccionStrategy {

    /**
     * Construye las entradas de una transacción
     *
     * @param tx La transacción a la que se le contruyen las entradas
     */
    void buildEntradas(Transaccion tx);

    /**
     * Devuelve el código de evento al que responde la implementación
     *
     * @return El valor del código con el que se debe registrar la implementación
     */
    String getCodigoEvento();
}
