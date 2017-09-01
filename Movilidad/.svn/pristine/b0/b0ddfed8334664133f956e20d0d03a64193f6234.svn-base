package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.persona.PersonaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * La interfaz Validator se encarga de validar los objetos de la clase {@link PersonaForm}.
 * Funciona utilizando un objeto Errors para que, al validar, los validadores puedan
 * informar fallos de validación al objeto Errors.
 * <p>
 * Created by maria on 5/26/17.
 */
@Component
public class PersonaFormValidator implements Validator {


    private static final Logger LOG = LoggerFactory.getLogger(ComparendoFormValidator.class);

    public boolean supports(Class clazz) {
        return PersonaForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        PersonaForm personaForm = (PersonaForm) obj;
        LOG.info("Validando los criterios de búsqueda de persona ParsonaFormValidator");
        if (personaForm.getNombre().isEmpty() && personaForm.getDocumentoIdentidad().isEmpty() && "none".equals(personaForm.getTipoDocumento())) {
            e.rejectValue("message", "personaForm.message.allempty");
        }
        if (personaForm.getNombre().isEmpty() && !personaForm.getDocumentoIdentidad().isEmpty() && "none".equals(personaForm.getTipoDocumento())) {
            e.rejectValue("tipoDocumento", "personaForm.tipoDocumento.required");
        }
        if (personaForm.getNombre().isEmpty() && personaForm.getDocumentoIdentidad().isEmpty() && !personaForm.getTipoDocumento().equals("none")) {

            e.rejectValue("documentoIdentidad", "personaForm.documentoIdentidad.required");

        }


    }

}
