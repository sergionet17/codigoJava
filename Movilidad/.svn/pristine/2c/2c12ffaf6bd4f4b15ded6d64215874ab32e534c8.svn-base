package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorNumeroDocumento {

    private static final String ERROR_NUMERO_DOCUMENTO = "El número de indentificación  debe ser obligatorio";
    private static final String ERROR_NUMERO_DOCUMENTO_NO_NUMBER = "El número de indentificación  debe ser numérico";


    private String numeroString;

    private String nameField;


    public void validarDocumento(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        if (numeroString == null) {
            errors.rejectValue(nameField, "fmt.numeroIdenfificacion.invalid", ERROR_NUMERO_DOCUMENTO);
        } else {
            if (formatoComparendo.getPlacaVehiculo().isEmpty()) {
                errors.rejectValue(nameField, "fmt.numeroIdenfificacion.invalid", ERROR_NUMERO_DOCUMENTO);
            } else {
                try {
                    Integer.parseInt(numeroString);
                } catch (NumberFormatException nfe) {
                    errors.rejectValue(nameField, "fmt.numeroIdenfificacion.invalid", ERROR_NUMERO_DOCUMENTO_NO_NUMBER);
                }
            }
        }

    }

    public String getNumeroString() {
        return numeroString;
    }

    public void setNumeroString(String numeroString) {
        this.numeroString = numeroString;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }
}
