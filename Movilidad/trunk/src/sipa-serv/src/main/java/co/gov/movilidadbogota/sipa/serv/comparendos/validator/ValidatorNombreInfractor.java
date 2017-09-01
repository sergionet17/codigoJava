package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 13/07/2017.
 */
@Component
public class ValidatorNombreInfractor extends ValidatorFiledEmpty implements ValidatorField {
    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        super.setNameField("primerNombreInfractor");
        super.setField(formatoComparendo.getPrimerNombreInfractor());
        super.validatorField(formatoComparendo, errors,
                "El nombre del infractor debe ser obligatorio ", "fmt.primerNombre.invalid");
    }
}
