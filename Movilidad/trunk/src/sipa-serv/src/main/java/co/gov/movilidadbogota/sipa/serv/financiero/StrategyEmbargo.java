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
public class StrategyEmbargo extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        tx.with(Cuenta.ORD_DEU_POSIBLE_RECAUDO).debito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_POSIBLE_RECAUDO_CONTRA).credito(tx.getValor());

    }
}