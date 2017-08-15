package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.gen.AgenteTransito;
import co.gov.movilidadbogota.sipa.data.biz.gen.AgenteTransitoRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

import java.util.List;

/**
 * Created by Admin on 14/07/2017.
 */
@Component
public class ValidatorAgenteTransito implements ValidatorField {

    private static final String ERROR_AGENTE_TRANSITO = "El agente de tránsito ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_PLACA_AGENTE_TRANSITO_VACIA = "La placa del agente no debe estar vacia";


    @Autowired
    private AgenteTransitoRepo agenteTransitoRepo;
    private List<AgenteTransito> agentes;

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {

        // Validación que el agente de tránsito exista en la base de datos
        AgenteTransito agenteTransito;
        if (formatoComparendo.getNumeroLicencia() == null) {
            errors.rejectValue("placaAgente", "fmt.placaAgente.invalid", ERROR_PLACA_AGENTE_TRANSITO_VACIA);
        } else {
            if (formatoComparendo.getNumeroLicencia().isEmpty()) {
                errors.rejectValue("placaAgente", "fmt.placaAgente.invalid", ERROR_PLACA_AGENTE_TRANSITO_VACIA);
            } else {
                agenteTransito = findAgente(formatoComparendo.getPlacaAgente());
                if (agenteTransito == null) {
                    errors.rejectValue("placaAgente", "fmt.placaAgente.invalid", ERROR_AGENTE_TRANSITO);
                } else {
                    comparendoDto.setAgenteTransito(agenteTransito);
                }
            }
        }
    }

    private AgenteTransito findAgente(String placaAgente) {
        if (agentes == null) {
            agentes = agenteTransitoRepo.findAll();
        }
        return agentes.stream().filter(x -> x.getPlaca().equals(placaAgente)).findFirst().get();
    }
}
