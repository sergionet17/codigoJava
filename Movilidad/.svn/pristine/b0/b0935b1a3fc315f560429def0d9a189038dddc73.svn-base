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
public class StrategyInteresMensual extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        // Dependiendo del tipo de referencia obtiene la cartera correspondiente y calcula los intereses del mes


        tx.with(Cuenta.ACT_INTERESES).debito(tx.getValor());
        tx.with(Cuenta.ING_INTERESES).credito(tx.getValor());

    }
}