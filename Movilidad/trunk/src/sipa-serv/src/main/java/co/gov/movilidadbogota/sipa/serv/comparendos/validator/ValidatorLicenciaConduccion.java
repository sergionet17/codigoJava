package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorLicenciaConduccion implements ValidatorField {

    private static final String ERROR_LICENCIA_INFRACTOR_VACIA = "El numero de licencia ingresado debe ser obligatoria";
    private static final String ERROR_LICENCIA_INFRACTOR_NO_ENCONTRADA = "El numero de licencia ingresado no se encuentra en el sistema.";
    private static final String ERROR_CATEGORIA_LICENCIA = "La categoria de la licencia ingresada no se encuentra configurada dentro del sistema.";
    private static final String ERROR_CATEGORIA_LICENCIA_VACIA = "La categoria de la licencia ingresada debe ser obligatoria";
    private static final String ERROR_ORGANISMO_TRANSITO = "El número de organismo de transito ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_ORGANISMO_TRANSITO_VACIO = "El número de organismo de transito debe ser obligatorio.";


    @Autowired
    LicenciaRepo licenciaRepo;

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        // Validacion que la sigla del tipo de documento del infractor exista en la base de datos
        Licencia licencia = null;
        if (formatoComparendo.getNumeroLicencia() == null) {
            errors.rejectValue("licencia", "fmt.licencia.invalid", ERROR_LICENCIA_INFRACTOR_VACIA);

        } else {
            if (formatoComparendo.getNumeroLicencia().isEmpty()) {
                errors.rejectValue("licencia", "fmt.licencia.invalid", ERROR_LICENCIA_INFRACTOR_VACIA);
            } else {
                licencia = licenciaRepo.findOneByNumero(formatoComparendo.getNumeroLicencia());
 /*               if (licencia == null) {
                    errors.rejectValue("licencia", "fmt.licencia.invalid", ERROR_LICENCIA_INFRACTOR_NO_ENCONTRADA);
                }*/
            }

        }


        // Validacion que la categoria de la licencia exista en la base de datos
        if (formatoComparendo.getCategoriaLicencia() == null) {
            errors.rejectValue("categoriaLicencia", "fmt.categoriaLicencia.invalid", ERROR_CATEGORIA_LICENCIA_VACIA);

        } else {
            if (formatoComparendo.getCategoriaLicencia().isEmpty()) {
                errors.rejectValue("categoriaLicencia", "fmt.categoriaLicencia.invalid", ERROR_CATEGORIA_LICENCIA_VACIA);
            } else {
                CategoriaLicencia categoriaLicencia = findByNombre(formatoComparendo.getCategoriaLicencia());
                if (categoriaLicencia == null) {
                    errors.rejectValue("categoriaLicencia", "fmt.categoriaLicencia.invalid", ERROR_CATEGORIA_LICENCIA);
                } else if (licencia != null) {
                    licencia.setCategoriaLicencia(categoriaLicencia);
                }

            }
        }


        // Validacion que el organismo de transito exista en la base de datos
        if (formatoComparendo.getOrganismoTransito() == null) {
            errors.rejectValue("organismoTransito", "fmt.organismoTransito.invalid", ERROR_ORGANISMO_TRANSITO_VACIO);

        } else {
            if (formatoComparendo.getOrganismoTransito().isEmpty()) {
                errors.rejectValue("organismoTransito", "fmt.organismoTransito.invalid", ERROR_ORGANISMO_TRANSITO_VACIO);

            } else {
                OrganismoTransito organismoTransito = findOrganismo(Integer.valueOf(formatoComparendo.getOrganismoTransito()));
                if (organismoTransito == null) {
                    errors.rejectValue("organismoTransito", "fmt.organismoTransito.invalid", ERROR_ORGANISMO_TRANSITO);
                } else {
                    if (licencia != null) {
                        licencia.setOrganismoTransito(organismoTransito);
                    }
                }
            }

        }

        comparendoDto.setlicencia(licencia);

    }

    private OrganismoTransito findOrganismo(Integer id) {
        return OrganismoTransito.FULL_SET.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    private CategoriaLicencia findByNombre(String categoriaLicencia) {
        return CategoriaLicencia.FULL_SET.stream().filter(x -> x.getNombre().equals(categoriaLicencia)).findFirst().orElse(null);
    }


}
