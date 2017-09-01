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
public class StrategyObligacionProvenienteEntidadesExternas extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        tx.with(Cuenta.ACT_ENTIDADES_EXTERNAS).debito(tx.getValor());
        tx.with(Cuenta.ING_ENTIDADES_EXTERNAS).credito(tx.getValor());

    }
}