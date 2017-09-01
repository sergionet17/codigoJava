package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Rol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Clase que valida la información de {@link Rol}.
 *
 * @author lorenzo.pinango
 */
public class RolValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(RolValidator.class);

    @Override
    public boolean supports(Class aClass) {
        return Rol.class.equals(aClass);
    }

    /**
     * Valida la información ingresada en el formulario
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        logger.info("Se realiza validaciones a la data del formulario");
        Rol rol = (Rol) o;
        //Se valida que los campos no tenga vacia
        if (rol.getName() == null) {
            errors.rejectValue("name", "rol.name.required");
        } else if (rol.getName().trim().isEmpty()) {
            errors.rejectValue("name", "rol.name.required");
        }
        if (rol.getPermisos().size() == 0) {
            errors.rejectValue("permisos", "rol.permisos.required");
        }
    }

}
