package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 11/07/2017.
 */
@Component
public class ValidatorCodigoInfraccion implements ValidatorField {
    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {

    }
}
