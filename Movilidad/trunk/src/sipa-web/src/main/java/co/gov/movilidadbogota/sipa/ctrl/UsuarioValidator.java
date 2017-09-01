package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que valida la información de {@link Usuario}.
 *
 * @author lorenzo.pinango
 */
public class UsuarioValidator implements Validator {
    private static final Logger logger = LoggerFactory.getLogger(RolValidator.class);

    @Override
    public boolean supports(Class aClass) {
        return Usuario.class.equals(aClass);
    }

    /**
     * Valida la información ingresada en el formulario
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        Pattern pattern;
        Matcher matcher;

        logger.info("Se realiza validaciones a la data del formulario");
        Usuario usuario = (Usuario) o;
        //Se valida que los campos no tenga vacia
        if (usuario.getUsername() == null) {
            errors.rejectValue("username", "usuario.username.required");
        } else if (usuario.getUsername().isEmpty()) {
            errors.rejectValue("username", "usuario.username.required");
        }
        if (usuario.getPassword() == null) {
            errors.rejectValue("password", "usuario.password.required");
        } else if (usuario.getPassword().isEmpty()) {
            errors.rejectValue("password", "usuario.password.required");
        }
        if (usuario.getEmail() == null) {
            errors.rejectValue("email", "usuario.email.required");
        } else if (usuario.getEmail().isEmpty()) {
            errors.rejectValue("email", "usuario.email.required");
        }
        if (!usuario.getEmail().isEmpty()) {
            pattern = Pattern.compile(Constants.EMAIL_PATTERN);
            matcher = pattern.matcher(usuario.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "usuario.email.format");
            }
        }
        if (usuario.getPerfil().getId() == null) {
            errors.rejectValue("perfil.id", "usuario.perfil.required");
        }
        if (usuario.getFechaCaducidad() == null) {
            errors.rejectValue("fechaCaducidad", "usuario.fechaCaducidad.required");
        }
        if (usuario.getCargo() == null) {
            errors.rejectValue("cargo", "usuario.cargo.required");
        } else if (usuario.getCargo().isEmpty()) {
            errors.rejectValue("cargo", "usuario.cargo.required");
        }
        if (usuario.getPersona().getTipoPersona().getId() == null) {
            errors.rejectValue("persona.tipoPersona.id", "usuario.persona.tipoPersona.required");
        }
        if (usuario.getPersona().getTipoDocumentoIdentidad().getId() == null) {
            errors.rejectValue("persona.documentoIdentidad.tipo.id", "usuario.persona.documentoIdentidad.tipo.required");
        }
        if (usuario.getPersona().getNumeroDocumentoIdentidad() == null) {
            errors.rejectValue("persona.documentoIdentidad.numero", "usuario.persona.documentoIdentidad.numero.required");
        } else if (usuario.getPersona().getNumeroDocumentoIdentidad().isEmpty()) {
            errors.rejectValue("persona.documentoIdentidad.numero", "usuario.persona.documentoIdentidad.numero.required");
        }
        if (usuario.getPersona().getPrimerNombre() == null) {
            errors.rejectValue("persona.primerNombre", "usuario.persona.primerNombre.required");
        } else if (usuario.getPersona().getPrimerNombre().isEmpty()) {
            errors.rejectValue("persona.primerNombre", "usuario.persona.primerNombre.required");
        }
        if (usuario.getPersona().getPrimerApellido() == null) {
            errors.rejectValue("persona.primerApellido", "usuario.persona.primerApellido.required");
        } else if (usuario.getPersona().getPrimerApellido().isEmpty()) {
            errors.rejectValue("persona.primerApellido", "usuario.persona.primerApellido.required");
        }
    }
}
