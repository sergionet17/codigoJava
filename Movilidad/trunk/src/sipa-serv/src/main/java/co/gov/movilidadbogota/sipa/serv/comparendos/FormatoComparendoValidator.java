package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;

/**
 * Cuando se implementa permite validar un {@link FormatoComparendo}. En general las
 * implementaciones se ensamblan en cadenas de validadores que se ejecutan secuencialmente
 * en busca de errores en el formato. Los errores se acumulan en un mapa que se pasa
 * entre las diferentes implementaciones.
 */
public interface FormatoComparendoValidator {

    /**
     * Valida un formato de comparendo y deposita los errores en el parámetro errors
     *
     * @param formatoComparendo El objeto a validar
     * @param comparendo        El objeto comparendo que se debe ir completando cuando
     *                          se valida la información en el validador
     * @param errorAccumulator  Objeto en el que se acumulan los errores detectados,
     *                          este valor no puede ser nulo.
     * @return true si el validador no encuentra errores y false en caso contrario
     */
    boolean validate(
            FormatoComparendo formatoComparendo,
            Comparendo comparendo,
            FormatoComparendoValidatorErrors errorAccumulator);

}
