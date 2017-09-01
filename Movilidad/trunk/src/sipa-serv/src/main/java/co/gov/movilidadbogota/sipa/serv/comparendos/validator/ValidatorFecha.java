package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.conf.ApplicationProperties;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.Infraccion;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 11/07/2017.
 */
@Component
public class ValidatorFecha implements ValidatorField {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String ERROR_FECHA_FUTURA = "Fecha de comparendo mayor a la fecha actual.";
    private static final String ERROR_FECHA_NO_VALIDA = "Formato de fecha incorrecto";
    private static final String ERROR_FECHA_VACIA_O_NULA = "Formato de fecha es vacia o es nula";


    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    ValorParametroRepo valorParametroRepo;


    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        validarFecha(formatoComparendo.getFecha(), errors);
    }

    @PostConstruct
    public void init() {
        /*
        Date now = new Date();
        diasVencimiento = valorParametroRepo.findValorVigenteByClave(
                Parametro.CANTIDAD_DIAS_VENCIMIENTO_IMPOSICION_COMPARENDO,
                now
        ).getValor();
        */
    }

    String diasVencimiento;

    /**
     * Se realiza una serie de validaciones de la fecha para saber si es correcta o si tiene errores
     *
     * @param dateToValidate
     * @param errores
     * @return
     */
    private Date validarFecha(String dateToValidate, MapBindingResult errores) {
        Date fecha;
        if (dateToValidate != null && !dateToValidate.isEmpty()) {
            try {
                fecha = sdf.parse(dateToValidate);
                if (applicationProperties.getComparendos().getValidator().getFechaImposicion() == false)
                    return fecha;
                Date now = verificarFechaFutura(errores, fecha);
                int diasPermitidosLuegoDeImposicion = Integer.valueOf(diasVencimiento);
                return verificarFechaImposicionNoEsMayorANdias(errores, fecha, now, diasPermitidosLuegoDeImposicion);
            } catch (ParseException e) {
                errores.rejectValue("fecha", "fmt.fecha.invalid", ERROR_FECHA_NO_VALIDA);
                return null;
            }
        } else {
            errores.rejectValue("fecha", "fmt.fecha.invalid", ERROR_FECHA_VACIA_O_NULA);
            return null;
        }


    }


    private Date verificarFechaFutura(MapBindingResult errores, Date fecha) {
        Date now = new Date();
        if (fecha.after(now)) {
            errores.rejectValue("fecha", "fmt.fecha.invalid", ERROR_FECHA_FUTURA);
        }
        return now;
    }


    private Date verificarFechaImposicionNoEsMayorANdias(MapBindingResult errores, Date fecha, Date now, int diasPermitidosLuegoDeImposicion) {
        Calendar haceDias = Calendar.getInstance();
        haceDias.setTime(now);
        haceDias.add(Calendar.DAY_OF_MONTH, -diasPermitidosLuegoDeImposicion);
        if (fecha.before(haceDias.getTime())) {
            errores.rejectValue(
                    "fecha",
                    "fmt.fecha.invalid",
                    "Fecha de  imposición mayor a " + diasPermitidosLuegoDeImposicion + " dias."
            );
        }
        return fecha;
    }

}
