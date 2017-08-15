package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.loc.Municipio;
import co.gov.movilidadbogota.sipa.data.loc.MunicipioRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorMunicipioInfraccion implements ValidatorField {

    private static final String ERROR_MUNICIPIO = "El código del municipio ingresado no se encuentra configurado dentro del sistema.";

    @Autowired
    private MunicipioRepo municipioRepo;

    List<Municipio> municipios;

    @PostConstruct
    public void init() {
        municipios = municipioRepo.findAll();
    }

    private Municipio findMunicipioByIdAndFecha(String nombre, Date fecha) {
        return municipios.stream()
                .filter(x -> (x.getNombre().equals(nombre) && fecha.after(x.getInicioVigencia()) && fecha.before(x.getFinVigencia())))
                .findFirst().orElse(null);
    }

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        // Validacion que el municipio de la infraccion exista en la base de datos
        if (formatoComparendo.getMunicipio() != null
                && !formatoComparendo.getMunicipio().isEmpty()) {
            Municipio municipioInfraccion = findMunicipioByIdAndFecha(formatoComparendo.getMunicipio(), new Date());
            if (null == municipioInfraccion) {
                errors.rejectValue("municipio", "fmt.municipio.invalid", ERROR_MUNICIPIO);
            } else {
                comparendoDto.setMunicipioInfraccion(municipioInfraccion);
            }
        }
    }
}
