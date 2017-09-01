package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoInfractor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Valida que el tipo de infractor esté presente y que exista en el sistema
 */
@Component
public class ValTipoInfractor implements FormatoComparendoValidator {
    @Override
    public boolean validate(FormatoComparendo formato, Comparendo comparendo, FormatoComparendoValidatorErrors errors) {
        boolean ret = true;

        String tipoStr = formato.getTipoInfractor();
        if (StringUtils.isBlank(tipoStr) || !StringUtils.isNumeric(tipoStr)) {
            errors.reject("tipoInfractor", "El tipo de infractor no se indica o no es un número");
            ret = false;
        } else {
            // No requiere validación de conversión ya que esta se hizo anteriormente
            Integer id = Integer.valueOf(tipoStr);
            TipoInfractor tipoInfractor = findTipoInfractorById(id);
            if(tipoInfractor == null) {
                errors.reject("tipoInfractor", "El tipo de infractor es válido");
                ret = false;
            }
        }

        return ret;
    }

    private TipoInfractor findTipoInfractorById(Integer id) {
        return TipoInfractor.FULL_SET.parallelStream()
                .filter(x -> x.getId().equals(id))
                .findAny().orElse(null);
    }
}
