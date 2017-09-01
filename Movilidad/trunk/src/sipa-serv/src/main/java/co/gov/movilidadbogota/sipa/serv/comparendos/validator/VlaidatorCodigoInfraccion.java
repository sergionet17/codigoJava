package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.InfraccionRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 14/07/2017.
 */
@Component
public class VlaidatorCodigoInfraccion implements ValidatorField {

    @Autowired
    InfraccionRepo infraccionRepo;


    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
       /* if (formatoComparendo.getCodigoInfraccion() != null && !formatoComparendo.getCodigoInfraccion().isEmpty()) {
            Infraccion infraccion = infraccionRepo.findByCodigoAndInicioVigenciaBeforeAndFinVigenciaAfter(
                    formatoComparendo.getCodigoInfraccion(),
                    formatoComparendo.getFecha(),
                    formatoComparendo.getFecha());
            if (infraccion == null) {
                errors.rejectValue(
                        "codigoInfraccion",
                        "frm.codigoInfraccion.invalid",
                        ERROR_CODIGO_DE_INFRACCION
                );
            }
        } else {
            errors.rejectValue(
                    "codigoInfraccion",
                    "frm.codigoInfraccion.invalid",
                    CODIGO_DE_INFRACCION_VACIA_O_NULA
            );
        }*/
    }
}
