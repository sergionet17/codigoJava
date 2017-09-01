package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.loc.Localidad;
import co.gov.movilidadbogota.sipa.data.loc.LocalidadRepo;
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
 * Created by Admin on 11/07/2017.
 */
@Component
public class ValidatorLugarInfraccion implements ValidatorField {

    private static final String ERROR_TIPO_VIA_PRINCIPAL = "La sigla del tipo de via principal ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_TIPO_VIA_SECUNDARIO = "La sigla del tipo de via secundario ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_MUNICIPIO = "El código del municipio ingresado no se encuentra configurado dentro del sistema.";
    private static final String ERROR_LOCALIDAD = "El código de la localidad ingresada no se encuentra configurada dentro del sistema.";

    @Autowired
    private MunicipioRepo municipioRepo;
    @Autowired
    private LocalidadRepo localidadRepo;

    List<Municipio> municipios;

    List<Localidad> localidades;

    @PostConstruct
    public void init() {
        localidades = localidadRepo.findAll();
        municipios = municipioRepo.findAll();
    }

    private Localidad findLocalidadByIdAndFecha(String nombre, Date fecha) {
        return localidades.stream()
                .filter(x -> (x.getNombre().equals(nombre) && fecha.after(x.getInicioVigencia()) && fecha.before(x.getFinVigencia())))
                .findFirst().orElse(null);
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
            }
            comparendoDto.setMunicipioInfraccion(municipioInfraccion);
        }

        // Validacion que la localidad exista en la base de datos
        if (formatoComparendo.getLocalidad() != null
                && !formatoComparendo.getLocalidad().isEmpty()) {
            Localidad localidadInfraccion = findLocalidadByIdAndFecha(formatoComparendo.getLocalidad(), new Date());
            if (null == localidadInfraccion) {
                errors.rejectValue("localidad", "fmt.localidad.invalid", ERROR_LOCALIDAD);
            }
            comparendoDto.setLocalidadInfraccion(localidadInfraccion);
        }

    }

}
