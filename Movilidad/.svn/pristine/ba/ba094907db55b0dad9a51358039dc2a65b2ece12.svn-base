package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class RangoFromValidator implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(RangoFromValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        RangoNumeracion rangoNumeracion = (RangoNumeracion) object;
        LOG.info("Validando la creación del nuevo rango");
        if (sonNulos(rangoNumeracion)) {
            errors.rejectValue("inicio", "rangoFrom.message.anyEmpty");
        } else if (rangosIguales(rangoNumeracion)) {
            errors.rejectValue("inicio", "rangoFrom.message.same");
        } else if (rangoInicioMayor(rangoNumeracion)) {
            errors.rejectValue("inicio", "rangoFrom.message.gretter");
        }
    }

    private boolean rangoInicioMayor(RangoNumeracion rangoNumeracion) {
        return rangoNumeracion.getInicio().longValue() > rangoNumeracion.getFin().longValue();
    }

    private boolean rangosIguales(RangoNumeracion rangoNumeracion) {
        return rangoNumeracion.getInicio().equals(rangoNumeracion.getFin());
    }

    private boolean sonNulos(RangoNumeracion rangoNumeracion) {
        return rangoNumeracion.getInicio() == null || rangoNumeracion.getFin() == null;
    }
}
