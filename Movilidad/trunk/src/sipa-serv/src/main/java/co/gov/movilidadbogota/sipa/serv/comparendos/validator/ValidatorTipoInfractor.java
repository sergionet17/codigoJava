package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoInfractor;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoInfractorRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorTipoInfractor implements ValidatorField {


    private static final String ERROR_TIPO_INFRACTOR_NO_NUMERICO = "El campo tipo infractor debe ser numérico";
    private static final String ERROR_TIPO_INFRACTOR = "El tipo infractor ingresado para el infractor no se encuentra configurado dentro del sistema.";

    @Autowired
    private TipoInfractorRepo tipoInfractorRepo;
    private TipoInfractor tipoInfractor;

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        // Validacion que el tipo de infraccion exista en la base de datos
        if (formatoComparendo.getTipoInfractor() != null
                && !formatoComparendo.getTipoInfractor().isEmpty()) {
            try {
                TipoInfractor tipoInfractor = tipoInfractorRepo.findOne(Integer.valueOf(formatoComparendo.getTipoInfractor()));
                if (null == tipoInfractor) {
                    errors.rejectValue("tipoInfractor", "fmt.tipoInfractor.invalid", ERROR_TIPO_INFRACTOR);
                }
            } catch (NumberFormatException nfe) {
                errors.rejectValue("tipoInfractor",
                        "fmt.tipoInfractor.invalid",
                        ERROR_TIPO_INFRACTOR_NO_NUMERICO);
            }

            comparendoDto.setTipoInfractor(tipoInfractor);
        }
    }
}
