package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.biz.comp.AudienciaComparendo;

/**
 * Cuando se implementa provee las operaciones relativas a las audiencias
 */
public interface AudienciaService {

    /**
     * Realiza la apertura de una audiencia ya sea esta de aceptación o de impugnación
     *
     * @param audienciaComparendo
     */
    AudienciaComparendo aperturaAudiencia(AudienciaComparendo audienciaComparendo) throws AudienciaException;

}
