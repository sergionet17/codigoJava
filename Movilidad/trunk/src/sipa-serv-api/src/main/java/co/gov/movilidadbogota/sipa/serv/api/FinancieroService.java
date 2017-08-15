package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;

import java.util.UUID;

/**
 * Cuando se implementa ofrece los servicios financieros del sistema
 */
public interface FinancieroService {

    /**
     * Código de evento de la transacción para el registro de la imposición de un comparendo
     */
    String COD_IMPOSICION_COMPARENDO = "MultaComparendoPresuntivo";

    /**
     * Código de evento en la transacción para el registro de la deuda de orden a balance
     */
    String COD_CONSTANCIA_EJECUTORIA_COMPARENDO = "ConstanciaEjecutoriaConMulta";

    /**
     * Tipo de referencia de comparendo
     */
    String REF_COMPARENDO = "comparendo";

    /**
     * Registra un evento que tiene afectación financiera
     *
     * @param evento Es el objeto que contiene la información del evento a registar
     * @return El identificador único de transacción bajo la cual se registró la
     * información del evento
     * @throws FinancieroException cuando ocurre un error con los recursos externos
     * @throws RuntimeException    cuando existen inconsistencias en la información
     *                             del evento o de configuración de las transacciones.
     */
    UUID registrar(EventoFinanciero evento) throws FinancieroException;

}
