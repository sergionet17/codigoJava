package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author carlos.rodriguez, arturo.cruz
 */
@Component
public class StrategyDevolucionSaldoFavor extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        tx.with(Cuenta.PAS_CUENTAS_POR_PAGAR).debito(tx.getValor());
        tx.with(Cuenta.ACT_HACIENDA).credito(tx.getValor());

    }
}