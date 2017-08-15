package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * La interfaz Validator se encarga de validar los objetos de la clase {@link InconsistenciasFormValidator}.
 * Funciona utilizando un objeto Errors para que, al validar, los validadores puedan
 * informar fallos de validación al objeto Errors.
 *
 * @author Created by Marcel on 07-06-2017.
 */
public class InconsistenciasFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(CursoValidator.class);

    @Override
    public boolean supports(Class aClass) {
        return FormatoComparendo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("Se realiza validaciones a la data del formulario");
        FormatoComparendo comp = (FormatoComparendo) o;
        //Se valida que los campos no tenga vacia
        if (comp.getCodigoInfraccion().isEmpty() || comp.getCodigoInfraccion() == null) {
            errors.rejectValue("codigoInfraccion", "formatoComparendoForm.codigoInfraccion.required");
        }
        if (comp.getFecha().isEmpty() || comp.getFecha() == null) {
            errors.rejectValue("fecha", "formatoComparendoForm.fecha.required");
        }
        if (comp.getNumero().isEmpty() || comp.getNumero() == null) {
            errors.rejectValue("numero", "formatoComparendoForm.numero.required");
        }
        if (comp.getTipo().isEmpty() || comp.getTipo() == null) {
            errors.rejectValue("tipo", "formatoComparendoForm.tipo.required");
        }

    }

    public void validateQuery(Object obj, Errors e) {
        InconsistenciasForm inconsistenciasForm = (InconsistenciasForm) obj;
        if ((inconsistenciasForm.getNumero() == null || inconsistenciasForm.getNumero().isEmpty())
                && (inconsistenciasForm.getNumeroIdentificacionInfractor() == null || inconsistenciasForm.getNumeroIdentificacionInfractor().isEmpty())
                && (inconsistenciasForm.getCodigoInfraccion() == null || inconsistenciasForm.getCodigoInfraccion().isEmpty())) {
            e.rejectValue("message", "comparendoForm.message.allempty");
        }
    }

}
