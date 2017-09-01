package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 13/07/2017.
 */
@Component
public class ValidatorApellidoInfractor extends ValidatorFiledEmpty implements ValidatorField {


    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        super.setNameField("primerApellidoInfractor");
        super.setField(formatoComparendo.getPrimerNombreInfractor());
        super.validatorField(formatoComparendo, errors,
                "El apellido del infractor debe ser obligatorio ", "fmt.primerApellido.invalid");
    }
}
