package co.gov.movilidadbogota.sipa.ctrl;


import co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * La interfaz Validator se encarga de validar los objetos de la clase {@link co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoForm}.
 * Funciona utilizando un objeto Errors para que, al validar, los validadores puedan
 * informar fallos de validación al objeto Errors.
 * Created by maria on 6/12/17.
 */
@Component
public class VehiculoFormValidator implements Validator {

    public boolean supports(Class clazz) {
        return VehiculoForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        VehiculoForm vehiculoForm = (VehiculoForm) obj;

        if (vehiculoForm.getPlacaVehiculo().isEmpty() && vehiculoForm.getTipoDocumento().isEmpty() && vehiculoForm.getDocumentoIdentidad().isEmpty()) {

            e.rejectValue("message", "vehiculoForm.message.allempty");

        } else if (vehiculoForm.getPlacaVehiculo().isEmpty() && !vehiculoForm.getTipoDocumento().equals("none") && vehiculoForm.getDocumentoIdentidad().isEmpty()) {

            e.rejectValue("documentoIdentidad", "vehiculoForm.documentoIdentidad.required");

        } else if (vehiculoForm.getPlacaVehiculo().isEmpty() && "none".equals(vehiculoForm.getTipoDocumento()) && !vehiculoForm.getDocumentoIdentidad().isEmpty()) {

            e.rejectValue("tipoDocumento", "vehiculoForm.tipoDocumento.required");

        }


    }


}
