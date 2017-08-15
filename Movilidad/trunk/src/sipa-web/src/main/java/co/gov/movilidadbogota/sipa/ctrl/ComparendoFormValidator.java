package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoForm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * La interfaz Validator se encarga de validar los objetos de la clase {@link ComparendoForm}.
 * Funciona utilizando un objeto Errors para que, al validar, los validadores puedan
 * informar fallos de validación al objeto Errors.
 *
 * @author maria.rodriguez on 5/24/17.
 */
@Component
public class ComparendoFormValidator implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(ComparendoFormValidator.class);

    public boolean supports(Class clazz) {
        return ComparendoForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ComparendoForm x = (ComparendoForm) obj;

        int count = 0;

        // Verifica la longitud del número de comparendo en caso que se haya suministrado
        if (StringUtils.isNotBlank(x.getNumeroComparendo())) {
            if (x.getNumeroComparendo().length() > 50) {
                e.rejectValue("numeroComparendo", "comparendoForm.message.allempty", "Valor demasiado largo");
            }
            count++;
        }

        // Verifica que el tipo y número de documento de identificación hayan sido suministrados para establecer si
        // cuenta como criterio de búsqueda
        if (StringUtils.isNotBlank(x.getNumeroIdentificacion()) && x.getTipoDocumento() != null) {
            count++;
        }

        if (x.getTipoDocumento() != null && StringUtils.isBlank(x.getNumeroIdentificacion())) {
            e.rejectValue("numeroIdentificacion", "comparendoForm.numeroIdentificacion.empty");
        }

        if (StringUtils.isNotBlank(x.getNumeroIdentificacion()) && x.getTipoDocumento() == null) {
            e.rejectValue("tipoDocumento", "comparendoForm.tipoDocumento.required");
        }

        // Verifica si ninguno de los criterio de búsqueda se especifica
        if (count < 1) {
            e.rejectValue("message", "comparendoForm.message.allempty");
        }

    }
}
