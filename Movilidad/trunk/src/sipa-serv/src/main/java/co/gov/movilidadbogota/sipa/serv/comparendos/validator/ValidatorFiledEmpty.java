package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 13/07/2017.
 */
@Component
public class ValidatorFiledEmpty {


    private String nameField;

    private String field;


    public void validatorField(FormatoComparendo formatoComparendo, MapBindingResult errors, String errorName, String errorCode) {
        if (field == null && nameField == null) {
            errors.rejectValue(nameField, errorCode, errorName);
        } else {
            if (field.isEmpty() && nameField.isEmpty()) {
                errors.rejectValue(nameField, errorCode, errorName);
            }
        }
    }


    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
