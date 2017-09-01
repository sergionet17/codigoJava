package co.gov.movilidadbogota.sipa.serv.financiero;


import co.gov.movilidadbogota.sipa.data.biz.comp.Inmovilizacion;
import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author carlos.rodriguez, arturo.cruz
 */
@Component
public class StrategyExoneracionSubsanacion extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica la existencia de la inmovilización
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_INMOVILIZACION);
        Inmovilizacion inmovilizacion = findInmovilizacion(tx.getReferencia());
        if (inmovilizacion == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no existe",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verifica que la referencia tenga saldo en cuenta de orden
        BigDecimal saldoOrden = saldo(entradas(tx.getReferencia(), Cuenta.ORD_DEU_SUBSANACIONES));
        if (saldoOrden.compareTo(BigDecimal.ZERO) == 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene deuda en orden",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verifica que la exonecación sea igual al saldo en cuenta de orden
        if (saldoOrden.compareTo(tx.getValor()) != 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s tiene un saldo de deuda en orden %s que no es igual al valor de la transacción %s",
                    tx.getTipoReferencia(),
                    tx.getReferencia(),
                    saldoOrden,
                    tx.getValor()
            ));
        }

        tx.with(Cuenta.ORD_DEU_SUBSANACIONES).credito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_SUBSANACIONES_CONTRA).debito(tx.getValor());

    }
}
