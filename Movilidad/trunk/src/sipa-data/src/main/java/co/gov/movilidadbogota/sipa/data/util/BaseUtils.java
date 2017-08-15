package co.gov.movilidadbogota.sipa.data.util;

import co.gov.movilidadbogota.sipa.data.gen.Festivo;
import co.gov.movilidadbogota.sipa.data.gen.FestivoRepo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * Utilidades de base para obtener información y funciones comunes a todos
 * Created by paola.charry on 04/07/2017.
 */
public class BaseUtils {

    public static Date sumarDiasHabilesAFecha(Date fecha, Integer diasLab) {
        int diasCal = (diasLab + ((diasLab / 5) * 2));
        Date fechaSuma = DateUtils.addDays(fecha, diasCal);
        if (fechaSuma.getDay() == 6) {
            diasCal = diasCal + 2;
            fechaSuma = DateUtils.addDays(fechaSuma, diasCal);
        } else if (fechaSuma.getDay() == 7) {
            diasCal++;
            fechaSuma = DateUtils.addDays(fechaSuma, diasCal);
        }
        return fechaSuma;
    }

    public static int laboralesToCalendarioRevert(Date fecha, Integer diasLab, FestivoRepo festivoRepo) {
        Integer numDias;
        diasLab = diasLab * -1;
        int diasCal = (diasLab + ((diasLab / 5) * 2));
        Date fechaSuma = DateUtils.addDays(fecha, diasCal);
        if (fechaSuma.getDay() == 6) {
            diasCal = diasCal - 2;
        } else if (fechaSuma.getDay() == 7) {
            diasCal--;
        }
        while (fecha.before(fechaSuma)) {
            Festivo festivo = festivoRepo.findOne(fechaSuma);
            if (festivo != null) {
                diasCal--;
                fechaSuma = DateUtils.addDays(fecha, -1);
            }
            fecha = DateUtils.addDays(fecha, -1);
        }
        return diasCal;
    }
}
