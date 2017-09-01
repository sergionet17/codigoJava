package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.gen.TipoVehiculo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorTipoDeVehiculo implements ValidatorField {

    private static final String ERROR_TIPO_VERHICULO = "El tipo vehículo debe ser obligatorio";
    private static final String ERROR_TIPO_VERHICULO_NUMERO = "El campo tipo vehículo debe ser numerico";
    private static final String ERROR_TIPO_VERHICULO_NO_EXISTE = "El tipo vehículo ingresado no esiste en el sistema";

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {

        if (formatoComparendo.getTipoVehiculo() == null) {
            errors.rejectValue("tipoVehiculo", "fmt.tipoVehiculo.invalid", ERROR_TIPO_VERHICULO);
        } else {
            if (formatoComparendo.getClaseServicioVehiculo().isEmpty()) {
                errors.rejectValue("tipoVehiculo", "fmt.tipoVehiculo.invalid", ERROR_TIPO_VERHICULO);
            }
            try {
                Integer idTipoVehiculo = Integer.parseInt(formatoComparendo.getTipoVehiculo());
                TipoVehiculo tipoVehiculo;
                tipoVehiculo = findTipoVehiculo(idTipoVehiculo);
                if (tipoVehiculo != null) {
                    comparendoDto.setTipoVehiculo(tipoVehiculo);
                } else {
                    errors.rejectValue("tipoVehiculo", "fmt.tipoVehiculo.doesntExist", ERROR_TIPO_VERHICULO_NO_EXISTE);
                }
            } catch (NumberFormatException nfe) {
                errors.rejectValue("tipoVehiculo", "fmt.tipoVehiculo.invalid.numero", ERROR_TIPO_VERHICULO_NUMERO);
            }

        }
    }

    private TipoVehiculo findTipoVehiculo(Integer idTipoVehiculo) {
        return TipoVehiculo.FULL_SET.stream().filter(x -> x.getId().equals(idTipoVehiculo)).findFirst().orElse(null);
    }
}
