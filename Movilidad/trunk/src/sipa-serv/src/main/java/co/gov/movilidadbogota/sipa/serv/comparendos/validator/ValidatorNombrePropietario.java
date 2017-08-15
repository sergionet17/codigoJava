package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 13/07/2017.
 */
@Component
public class ValidatorNombrePropietario extends ValidatorFiledEmpty implements ValidatorField {

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        super.setNameField("nombresPropietario");
        super.setField(formatoComparendo.getNombresPropietario());
        super.validatorField(formatoComparendo, errors,
                "Los nombres del propietario debe ser obligatorio ", "fmt.nombresPropietario.invalid");
    }
}
