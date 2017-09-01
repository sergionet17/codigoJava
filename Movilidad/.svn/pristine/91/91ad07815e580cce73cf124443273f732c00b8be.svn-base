package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 11/07/2017.
 */
@Component
public class ValidatorPlaca implements ValidatorField {

    private static final String ERROR_PLACA = "La placa del vehículo debe ser obligatoria";


    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        if (formatoComparendo.getPlacaVehiculo() == null) {
            errors.rejectValue("placaVehiculo", "fmt.placa.invalid", ERROR_PLACA);
        } else {
            if (formatoComparendo.getPlacaVehiculo().isEmpty()) {
                errors.rejectValue("placaVehiculo", "fmt.placa.invalid", ERROR_PLACA);
            }
        }
    }
}
