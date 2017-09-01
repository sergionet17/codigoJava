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
public class StrategyObligacionSancionesDisciplinarias extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        tx.with(Cuenta.ACT_SANCIONES_DISCIPLINARIAS).debito(tx.getValor());
        tx.with(Cuenta.ING_SANCIONES_DISCIPLINARIAS).credito(tx.getValor());

    }
}