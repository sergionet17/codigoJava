package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorNumeroDocumentoInfractor extends ValidatorNumeroDocumento implements ValidatorField {
    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        super.setNameField("numeroIdentificacionInfractor");
        super.setNumeroString(formatoComparendo.getNumeroIdentificacionInfractor());
        super.validarDocumento(formatoComparendo, errors, comparendoDto);
    }
}
