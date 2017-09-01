package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.Infraccion;
import co.gov.movilidadbogota.sipa.data.biz.comp.InfraccionRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de apoyo para la validación de un comparendo.
 *
 * @author marcel.bohorquez
 */
@Component
public class OldFormatoComparendoValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(OldFormatoComparendoValidator.class);

    private static final String ERROR_CODIGO_INFRACCION = "El código de la infracción ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_CODIGO_DE_INFRACCION = "El código de infracción no se encuentra en el sistema o no está vigente";
    private static final String ERROR_FOUND_BEAN = "No se encontro el bean dado";

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    InfraccionRepo infraccionRepo;

    /**
     * Valida los datos recibidos en el formato comparendo para Identificar comparendos de tránsito o infracciones de
     * transporte inconsistentes.
     */
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        try {
            if (formatoComparendo.getCodigoInfraccion() == null) {
                errors.rejectValue("coidgoInfracion", "fmt.codigoInfraccion.invalid", ERROR_CODIGO_INFRACCION);
            } else {
                if (formatoComparendo.getCodigoInfraccion().isEmpty()) {
                    errors.rejectValue("coidgoInfracion", "fmt.codigoInfraccion.invalid", ERROR_CODIGO_INFRACCION);

                } else {
                    List<ValidatorField> responsabiltyChain = creatResponsabilityChain(formatoComparendo.getCodigoInfraccion(), errors);
                    executeResponsabolityChain(formatoComparendo,
                            errors,
                            comparendoDto,
                            responsabiltyChain);
                }

            }
        } catch (Exception e) {
            LOGGER.error("Ejecutando la cadena de validación", e);
            errors.rejectValue("Error Inesperado",
                    "fmt.error", e.getMessage());

        }
    }

    private void executeResponsabolityChain(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto, List<ValidatorField> responsabiltyChain) {

        for (ValidatorField validatorField : responsabiltyChain) {
            validatorField.validar(formatoComparendo, errors, comparendoDto);
        }
    }

    private List<ValidatorField> creatResponsabilityChain(String codigoInfraccion, MapBindingResult errors) {
        List<ValidatorField> responsabiltyChain = null;
        Infraccion infraccion;
        String[] candenaBeansValidatorSplit;

        infraccion = infraccionRepo.findByCodigo(codigoInfraccion);
        responsabiltyChain = new ArrayList();
        if (infraccion != null) {
            try {
                candenaBeansValidatorSplit = infraccion.getValidador().split(",");
                for (int i = 0; i < candenaBeansValidatorSplit.length; i++) {
                    ValidatorField validatorField = applicationContext.getBean(candenaBeansValidatorSplit[i], ValidatorField.class);
                    responsabiltyChain.add(validatorField);
                }
            } catch (NoSuchBeanDefinitionException nsbd) {
                errors.rejectValue(
                        "NoFoundBean",
                        "frm.NoFoundBean",
                        ERROR_FOUND_BEAN + " " + nsbd.getBeanName()
                );
            }
        } else {
            errors.rejectValue(
                    "codigoInfraccion",
                    "frm.codigoInfraccion.invalid",
                    ERROR_CODIGO_DE_INFRACCION
            );
        }
        return responsabiltyChain;

    }


}
