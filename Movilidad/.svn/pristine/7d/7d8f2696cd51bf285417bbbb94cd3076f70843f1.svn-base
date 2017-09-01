package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.AgendamientoAudiencia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * La interfaz Validator se encarga de validar la información de AgendamientoAudiencia
 * Created by paola.charry on 29/06/2017.
 */
@Component
public class AgendamientoAudienciaValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(AgendamientoAudienciaValidator.class);

    @Override
    public boolean supports(Class aClass) {
        return AgendamientoAudiencia.class.equals(aClass);
    }

    /**
     * Valida la información ingresada en el formulario
     *
     * @param o      Data del formulario
     * @param errors Errores luego de realizar la inspeccion de validaciones de negocio
     *               la data ingresada en el formulario
     */

    @Override
    public void validate(Object o, Errors errors) {
        logger.debug("Se realiza validaciones a la data del formulario");
        AgendamientoAudiencia agendamientoAudiencia = (AgendamientoAudiencia) o;

        //Validación de tipo de audiencia
        if (agendamientoAudiencia.getTipo() == null) {
            errors.rejectValue("tipo", "audiencia.tipo.requiered");
        } else if (agendamientoAudiencia.getTipo().getNombre() == null) {
            errors.rejectValue("tipo", "audiencia.tipo.requiered");
        } else if (agendamientoAudiencia.getTipo().getNombre().isEmpty()) {
            errors.rejectValue("tipo", "audiencia.tipo.requiered");
        }
        //Validación de número de días
        if (agendamientoAudiencia.getDiaInicial() > agendamientoAudiencia.getDiaFinal()) {
            errors.rejectValue("diaInicial", "audiencia.dia.greater");
        } else if (agendamientoAudiencia.getDiaInicial() == agendamientoAudiencia.getDiaFinal()) {
            errors.rejectValue("diaFinal", "audiencia.dia.noEquals");
        }
    }
}
