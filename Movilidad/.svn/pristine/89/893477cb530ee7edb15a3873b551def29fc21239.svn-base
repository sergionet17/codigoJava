package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.gen.ValorParametro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Clase que valida la información de {@link ValorParametro}.
 *
 * @author lorenzo.pinango
 */
public class ValorParametroValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(ValorParametroValidator.class);

    @Override
    public boolean supports(Class aClass) {
        return ValorParametro.class.equals(aClass);
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
        ValorParametro valorParametro = (ValorParametro) o;
        //Se valida que los campos no tenga vacia
        if (valorParametro.getValor() == null) {
            errors.rejectValue("valor", "valorParametro.valor.required");
        } else if (valorParametro.getValor().isEmpty()) {
            errors.rejectValue("valor", "valorParametro.valor.required");
        } else if (valorParametro.getValor().trim().equals("")
                || valorParametro.getValor().equals("____-__-__")
                || valorParametro.getValor().equals("____-__-__ __:__")
                || valorParametro.getValor().equals("__:__")) {
            errors.rejectValue("valor", "valorParametro.valor.required");
        }
        if (valorParametro.getInicioVigencia() == null) {
            errors.rejectValue("inicioVigencia", "NotNull.valorParametroForm.inicioVigencia");
        }
    }

}
