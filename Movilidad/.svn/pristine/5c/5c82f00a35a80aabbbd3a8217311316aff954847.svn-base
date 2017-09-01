package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.Infraccion;
import co.gov.movilidadbogota.sipa.data.biz.comp.InfraccionRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorInfraccion implements ValidatorField {


    private static final String ERROR_CODIGO_INFRACCION = "El código de la infracción ingresado no se encuentra configurado dentro del sistema.";

    @Autowired
    private InfraccionRepo infraccionRepo;

    List<Infraccion> infracciones;

    @PostConstruct
    public void init() {
        infracciones = infraccionRepo.findAll();
    }

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {

        // Validacion que el codigo de la Infraccion exista en la base de datos
        Infraccion infraccion;
        if (formatoComparendo.getCodigoInfraccion() == null
                || (infraccion = find(formatoComparendo.getCodigoInfraccion())) == null) {
            errors.rejectValue("codigoInfraccion", "fmt.codigoInfraccion.invalid", ERROR_CODIGO_INFRACCION);
        } else {
            comparendoDto.setInfraccion(infraccion);
        }

    }

    private Infraccion find(String codigoInfraccion) {
        return infracciones.stream().filter(x -> x.getCodigo().equals(codigoInfraccion)).findFirst().get();
    }
}
