package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoComparendo;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Utilidades de base para obtener información y funciones comunes a algunos controladores
 * Created by paola.charry on 04/07/2017.
 */
@Controller
public class UtilController extends BaseController {

    private static UtilController utils;

    @Autowired
    private
    ValorParametroRepo valorParametroRepo;

    /**
     * Retorna el porcentaje de descuento de un comparendo
     *
     * @param comparendo Ingresa la informacion del comparendo
     * @return Retorna el porcentaje de descuento
     */

    static BigDecimal porcentajeDescuento(Comparendo comparendo) {

        if (comparendo.getTipoComparendo().getId().equals(TipoComparendo.MANUAL.getId())) {
            if (new Date().before(DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_50_ELECTRONICO, new Date()).getValor())))) {
                return new BigDecimal(utils.valorParametroRepo.findValorVigenteByClave(
                        Parametro.PORCENTAJE_DESCUENTO_50, new Date()).getValor());
            } else if (new Date().before(DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_25_ELECTRONICO, new Date()).getValor())))) {
                return new BigDecimal(utils.valorParametroRepo.findValorVigenteByClave(
                        Parametro.PORCENTAJE_DESCUENTO_25, new Date()).getValor());
            } else {
                return BigDecimal.ZERO;
            }
        } else if (comparendo.getTipoComparendo().getId().equals(TipoComparendo.ELECTRONICO.getId())) {
            if (new Date().before(DateUtils.addDays(
                    comparendo.getFechaImposicion(), Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_50_LAPIZ_OPTICO, new Date()).getValor())))) {
                return new BigDecimal(
                        utils.valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_DESCUENTO_50, new Date()).getValor());
            } else if (new Date().before(DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_25_LAPIZ_OPTICO, new Date()).getValor())))) {
                return new BigDecimal(utils.valorParametroRepo.findValorVigenteByClave(
                        Parametro.PORCENTAJE_DESCUENTO_25, new Date()).getValor());
            } else {
                return BigDecimal.ZERO;
            }
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Retorna el valor del descuento de acuerdo al porcentaje
     *
     * @param saldo               Saldo de la deuda de un comparendo
     * @param porcentajeDescuento Porcentaje del descuento
     * @return Retorna el valor del decuento
     */
    static BigDecimal valorDescuento(BigDecimal saldo, BigDecimal porcentajeDescuento) {
        if (BigDecimal.ZERO.equals(porcentajeDescuento)) {
            return BigDecimal.ZERO;
        } else {
            return saldo.multiply(porcentajeDescuento);
        }
    }

    /**
     * Retorna la fecha maxima de descuento
     *
     * @param comparendo Ingresa la informacion del comparendo
     * @return Retorna la fecha maxima del descuento
     */

    static Date fechaMaximaDescuento(Comparendo comparendo) {
        if (comparendo.getTipoComparendo().getId().equals(TipoComparendo.MANUAL.getId())) {
            Date fecha = DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_50_ELECTRONICO, new Date()).getValor()));
            if (new Date().before(fecha)) {
                return fecha;
            }
            fecha = DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_25_ELECTRONICO, new Date()).getValor()));
            if (new Date().before(DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_25_ELECTRONICO, new Date()).getValor())))) {
                return fecha;
            }
        } else if (comparendo.getTipoComparendo().getId().equals(TipoComparendo.ELECTRONICO.getId())) {
            Date fecha = DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_50_LAPIZ_OPTICO, new Date()).getValor()));
            if (new Date().before(fecha)) {
                return fecha;
            }
            fecha = DateUtils.addDays(comparendo.getFechaImposicion(),
                    Integer.valueOf(utils.valorParametroRepo.findValorVigenteByClave(
                            Parametro.DIAS_DESCUENTO_25_LAPIZ_OPTICO, new Date()).getValor()));
            if (new Date().before(fecha)) {
                return fecha;
            }
        }
        return comparendo.getFechaImposicion();
    }

    @PostConstruct
    public void init() {
        utils = this;
        utils.valorParametroRepo = this.valorParametroRepo;
    }

    /**
     * Genera el numero completo del comparendo unico nacional de 20 digitos
     *
     * @param numero            Entero que corresponde a la serie unica dada por el RUNT
     * @param organismoTransito Organismo de transito quien registra el comparendo
     * @return Retorna un numero entero que corresponde al comparendo
     */
    String generarNumeroComparendoCompleto(Integer numero, String organismoTransito) {

        //Completa de ceros a la izquierda para completa el numero con 12 digitos
        String result = StringUtils.leftPad(numero.toString(), 12, "0");
        //Retorna el organismo de transito concatenado con el numero de comparendo
        return organismoTransito.concat(result);
    }
}
