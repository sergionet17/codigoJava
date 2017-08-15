package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.loc.Localidad;
import co.gov.movilidadbogota.sipa.data.loc.LocalidadRepo;
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
public class ValidatorLocalidad implements ValidatorField {

    private static final String ERROR_LOCALIDAD = "El código de la localidad ingresada no se encuentra configurada dentro del sistema.";


    @Autowired
    private LocalidadRepo localidadRepo;

    List<Localidad> localidades;

    @PostConstruct
    public void init() {
        localidades = localidadRepo.findAll();
    }

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        if (formatoComparendo.getLocalidad() != null
                && !formatoComparendo.getLocalidad().isEmpty()) {
            Localidad localidadInfraccion = findByIdAndFecha(formatoComparendo.getLocalidad(), new Date());
            if (null == localidadInfraccion) {
                errors.rejectValue("localidad", "fmt.localidad.invalid", ERROR_LOCALIDAD);
            }
            comparendoDto.setLocalidadInfraccion(localidadInfraccion);
        }

    }

    private Localidad findByIdAndFecha(String nombre, Date fecha) {
        return localidades.stream()
                .filter(x -> (x.getNombre().equals(nombre) && fecha.after(x.getInicioVigencia()) && fecha.before(x.getFinVigencia())))
                .findFirst().orElse(null);
    }
}