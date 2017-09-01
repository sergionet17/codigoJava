package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.gen.ClaseServicioVehiculo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorClaseDeServicioVehiculo implements ValidatorField {


    private static final String ERROR_CLASE_SERVICIO = "El campo clase servicio debe ser obligatorio";
    private static final String ERROR_CLASE_SERVICIO_NUMERO = "El campo clase debe ser numero";
    private static final String ERROR_CLASE_SERVICIO_NO_EXISTE = "La clase de vehículo ingresada no esiste en el sistema";

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {

        if (formatoComparendo.getClaseServicioVehiculo() == null) {
            errors.rejectValue("claseServicioVehiculo", "fmt.claseServicio.invalid", ERROR_CLASE_SERVICIO);
        } else {
            if (formatoComparendo.getClaseServicioVehiculo().isEmpty()) {
                errors.rejectValue("claseServicioVehiculo", "fmt.claseDeServicio.invalid", ERROR_CLASE_SERVICIO);
            }
            try {
                Integer idClaseVehiculo = Integer.parseInt(formatoComparendo.getClaseServicioVehiculo());
                ClaseServicioVehiculo claseServicioVehiculo;
                claseServicioVehiculo = find(idClaseVehiculo);
                if (claseServicioVehiculo != null) {
                    comparendoDto.setClaseVehiculo(claseServicioVehiculo);
                } else {
                    errors.rejectValue("claseServicioVehiculo", "fmt.claseDeServicio.doesntExist", ERROR_CLASE_SERVICIO_NO_EXISTE);
                }
            } catch (NumberFormatException nfe) {
                errors.rejectValue("claseServicioVehiculo", "fmt.claseDeServicio.invalid.numero", ERROR_CLASE_SERVICIO_NUMERO);
            }

        }
    }

    private ClaseServicioVehiculo find(Integer idClaseVehiculo) {
        return ClaseServicioVehiculo.FULL_SET.stream().filter(x -> x.getId().equals(idClaseVehiculo)).findFirst().get();
    }
}