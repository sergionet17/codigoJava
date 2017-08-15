package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorTipoIdentificacionTestigo extends ValidatorTipoDocumento implements ValidatorField {


    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        super.setNameField("tipoIdentificacionTestigo");
        super.setTipoDocumentoSigla(formatoComparendo.getTipoIdentificacionTestigo());
        super.validarTipoDocumento(formatoComparendo, errors, comparendoDto);
        comparendoDto.setTipoDocumentoTestigo(super.getTipoDocumento());
    }
}
