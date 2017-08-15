package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Estrategia de transacción para el pago de un comparendo
 * CU242.2 Registrar pago de un comparendo
 *
 * @author arturo.cruz
 */
@Component
public class StrategyPagoComparendo extends TransaccionStrategyAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyPagoComparendo.class);

    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de transacción sea "comparendo" y que el comparendo exista
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);
        checkComparendo(tx.getReferencia());

        // Verifica que el comparendo tenga registros previos en alguna de las carteras (orden o balance)
        List<Entrada> entradasOrden = entradas(tx.getReferencia(), Cuenta.ORD_DEU_COMPARENDOS);
        List<Entrada> entradasBalance = entradas(tx.getReferencia(), Cuenta.ACT_COMPARENDOS);
        if (entradasOrden.size() == 0 && entradasBalance.size() == 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene registros previos de deuda",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Si existe saldo en cuenta de orden se debe realizar el paso de dicho saldo a cuentas de balance
        BigDecimal saldoOrden = saldo(entradasOrden);
        if (BigDecimal.ZERO.compareTo(saldoOrden) < 0) {
            tx.with(Cuenta.ORD_DEU_COMPARENDOS).credito(saldoOrden);
            tx.with(Cuenta.ORD_DEU_COMPARENDOS_CONTRA).debito(saldoOrden);
            tx.with(Cuenta.ACT_COMPARENDOS).debito(saldoOrden);
            tx.with(Cuenta.ING_COMPARENDOS).credito(saldoOrden);
        }

        // Calcula la deuda en balance juntando los registros que ya existían más los que eventualmente fueron creados
        BigDecimal saldoDeuda = saldo(entradasBalance).add(saldoOrden);

        // Calcula los intereses a la fecha del evento
        //BigDecimal intereses = interes(saldoDeuda);

        // Si el valor del pago supera el valor de la deuda entonces parte el valor del pago
        if (tx.getValor().compareTo(saldoDeuda) > 0) {
            // Crea las entradas del pago separando el remanente en cuentas por pagar
            BigDecimal remanente = tx.getValor().subtract(saldoDeuda);
            tx.with(Cuenta.ACT_COMPARENDOS).credito(saldoDeuda);
            tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
            tx.with(Cuenta.PAS_CUENTAS_POR_PAGAR).credito(remanente);
        } else {
            // Crea las entradas del pago
            tx.with(Cuenta.ACT_COMPARENDOS).credito(tx.getValor());
            tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
        }

    }

}
