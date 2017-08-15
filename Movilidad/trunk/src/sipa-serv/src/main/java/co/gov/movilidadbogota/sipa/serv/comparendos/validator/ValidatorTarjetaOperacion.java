package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 14/07/2017.
 */
@Component
public class ValidatorTarjetaOperacion extends ValidatorFiledEmpty implements ValidatorField {

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        super.setNameField("tarjetaOperacion");
        super.setField(formatoComparendo.getTarjetaOperacion());
        super.validatorField(formatoComparendo, errors,
                "La tarjeta de operación  debe ser obligatoria", "fmt.tarjetaOperacion.invalid");
    }
}
