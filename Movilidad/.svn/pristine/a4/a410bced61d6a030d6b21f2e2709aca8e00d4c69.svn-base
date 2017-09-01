package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.OrganismoTransito;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorOrganismoTransito implements ValidatorField {

    private static final String ERROR_ORGANISMO_TRANSITO = "El número de organismo de transito ingresado no se encuentra configurado dentro del sistema: %s";

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        if (formatoComparendo.getOrganismoTransito() != null
                && !formatoComparendo.getOrganismoTransito().isEmpty()) {
            OrganismoTransito organismoTransito = findOrganismo(Integer.valueOf(formatoComparendo.getOrganismoTransito()));
            if (null == organismoTransito) {
                errors.rejectValue("organismoTransito", "fmt.organismoTransito.invalid", ERROR_ORGANISMO_TRANSITO);
            }
            comparendoDto.setOrganismoTransito(organismoTransito);
        }
    }

    private OrganismoTransito findOrganismo(Integer id) {
        return OrganismoTransito.FULL_SET.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }
}
