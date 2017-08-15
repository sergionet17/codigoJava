package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendoRepository;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorNumeroComparendo implements ValidatorField {

    private static final String ERROR_NUMERO_COMPARENDO_BD = "El número de comparendo ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_NUMERO_COMPARENDO_NO_DISPONIBLE = "El número de comparendo ingresado no esta disponible para ser utilizado.";

    private
    NumeroComparendoRepository numeroComparendoRepo;

    public ValidatorNumeroComparendo(NumeroComparendoRepository numeroComparendoRepo) {
        this.numeroComparendoRepo = numeroComparendoRepo;
    }

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        // Validacion que el municipio infractor exista en la base de datos
        NumeroComparendo numeroComparendo = null;
        if (formatoComparendo.getNumero() == null) {
            errors.rejectValue("numero", "fmt.numero.invalid", ERROR_NUMERO_COMPARENDO_BD);
        } else {
            numeroComparendo = numeroComparendoRepo.
                    findByValorAndRangoNumeracionTipoRangoNumeracion(formatoComparendo.getNumero(), TipoRangoNumeracion.COMPARENDO_TRANSITO);
            if (null == numeroComparendo) {
                errors.rejectValue("numero",
                        "fmt.numero.invalid",
                        ERROR_NUMERO_COMPARENDO_BD);
            } else if (!numeroComparendo.getEstado().getId().equals(EstadoNumeroComparendo.DISPONIBLE.getId())) {
                errors.rejectValue("numero",
                        "fmt.numero.invalid",
                        ERROR_NUMERO_COMPARENDO_NO_DISPONIBLE);
            } else {
                comparendoDto.setNumeroComparendo(numeroComparendo);
            }
        }
    }
}
