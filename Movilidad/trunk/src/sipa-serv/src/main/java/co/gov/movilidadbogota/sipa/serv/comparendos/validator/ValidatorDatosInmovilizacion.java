package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.Inmovilizacion;
import co.gov.movilidadbogota.sipa.data.biz.comp.InmovilizacionRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 14/07/2017.
 */
@Component
public class ValidatorDatosInmovilizacion implements ValidatorField {


    private static final String ERROR_NUMERO_DE_INMOVILIZACION = "El numero de inmovilización debe ser obligatorio";
    private static final String ERROR_NUMERO_DE_INMOVILIZACION_NO_EXISTE = "El numero de inmovilización no se encuentra  en le sistema";


    @Autowired
    InmovilizacionRepo inmovilizacionRepo;


    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {

        if (formatoComparendo.getNumeroInmovilizacion() == null) {
            errors.rejectValue("numeroInmovilizacion",
                    "frm.numeroInmovilizacion.invalid", ERROR_NUMERO_DE_INMOVILIZACION);
        } else {
            if (formatoComparendo.getNumeroInmovilizacion().isEmpty()) {
                errors.rejectValue("numeroInmovilizacion",
                        "frm.numeroInmovilizacion.invalid", ERROR_NUMERO_DE_INMOVILIZACION);
            } else {
                Inmovilizacion inmovilizacion = inmovilizacionRepo.findByNumero(formatoComparendo.getNumeroInmovilizacion());
                if (inmovilizacion != null) {
                    errors.rejectValue("numeroInmovilizacion",
                            "frm.numeroInmovilizacion.invalid", ERROR_NUMERO_DE_INMOVILIZACION_NO_EXISTE);
                } else {
                    comparendoDto.setInmovilizacion(inmovilizacion);
                }
            }
        }
    }
}
