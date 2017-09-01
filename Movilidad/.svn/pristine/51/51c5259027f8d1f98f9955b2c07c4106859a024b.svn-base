package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Perfil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Clase que valida la información de {@link Perfil}.
 *
 * @author lorenzo.pinango
 */
public class PerfilValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(PerfilValidator.class);

    @Override
    public boolean supports(Class aClass) {
        return Perfil.class.equals(aClass);
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
        Perfil perfil = (Perfil) o;
        //Se valida que los campos no tenga vacia
        if (perfil.getNombre() == null) {
            errors.rejectValue("nombre", "perfil.nombre.required");
        } else if (perfil.getNombre().trim().isEmpty()) {
            errors.rejectValue("nombre", "perfil.nombre.required");
        }
        if (perfil.getRoles().size() == 0) {
            errors.rejectValue("roles", "perfil.roles.required");
        }
    }

}
