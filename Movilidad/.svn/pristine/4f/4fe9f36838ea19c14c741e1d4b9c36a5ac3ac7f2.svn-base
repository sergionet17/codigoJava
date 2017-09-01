package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.CategoriaLicencia;
import co.gov.movilidadbogota.sipa.data.biz.comp.CategoriaLicenciaRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorCategoriaLicencia implements ValidatorField {

    private static final String ERROR_CATEGORIA_LICENCIA = "La categoria de la licencia ingresada no se encuentra configurada dentro del sistema.";

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        if (formatoComparendo.getCategoriaLicencia() != null
                && !formatoComparendo.getCategoriaLicencia().isEmpty()) {
            CategoriaLicencia categoriaLicencia = find(formatoComparendo.getCategoriaLicencia());
            if (null == categoriaLicencia) {
                errors.rejectValue("categoriaLicencia",
                        "fmt.categoriaLicencia.invalid",
                        ERROR_CATEGORIA_LICENCIA);
            }
            comparendoDto.setCategoriaLicencia(categoriaLicencia);
        }
    }

    private CategoriaLicencia find(String categoriaLicencia) {
        return CategoriaLicencia.FULL_SET.stream().filter(x -> x.getNombre().equals(categoriaLicencia)).findFirst().get();
    }
}
