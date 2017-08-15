package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.biz.comp.Inmovilizacion;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoSalida;
import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author arturo.cruz
 */
@Component
public class StrategyIncumplimientoSubsanacion extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de referencia sea comparendo
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_INMOVILIZACION);

        // Verifica que el exista la inmovilización y la subsanación se encuentre fuera de términos
        Inmovilizacion inmovilizacion = findInmovilizacion(tx.getReferencia());
        if (inmovilizacion == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no existe",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }
        if (inmovilizacion.getSalida() == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene salida previa",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }
        if (!TipoSalida.PROVISIONAL.equals(inmovilizacion.getSalida().getTipoSalida())) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene salida de tipo provisional",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }
        if (inmovilizacion.getSalida().getSubsanacion() == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene subsanaciones",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }
        if (inmovilizacion.getSalida().getSubsanacion().getFechaLimite() == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene fecha límite de subsanación",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }
        if (inmovilizacion.getSalida().getSubsanacion().getFechaLimite().after(new Date())) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene subsanación con fecha límite posterior al día de hoy",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Crea las entradas correspondientes a la transacción
        tx.with(Cuenta.ORD_DEU_SUBSANACIONES).debito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_SUBSANACIONES_CONTRA).credito(tx.getValor());

    }

}
