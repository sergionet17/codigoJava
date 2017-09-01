package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Valida que el número de comparendo exista para el tipo de rango de numeración
 * COMPARENDO_TRANSITO y que se encuentre en estado DISPONIBLE. Si el número se
 * encuentra en condiciones entonces lo asigna al comparendo.
 */
@Component
public class ValNumeroComparendo implements FormatoComparendoValidator {

    @Autowired
    NumeroComparendoRepository numeroComparendoRepository;

    @Override
    public boolean validate(FormatoComparendo formato, Comparendo comparendo, FormatoComparendoValidatorErrors errors) {
        NumeroComparendo numero = numeroComparendoRepository
                .findByValorAndRangoNumeracionTipoRangoNumeracion(
                        formato.getNumero(),
                        TipoRangoNumeracion.COMPARENDO_TRANSITO
                );
        if (numero == null || !numero.getEstado().equals(EstadoNumeroComparendo.DISPONIBLE)) {
            errors.reject("numero", "Número de comparendo no es válido: " + formato.getNumero());
            return false;
        } else {
            comparendo.setNumero(numero);
            return true;
        }
    }
}
