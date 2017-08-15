package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author carlos.rodriguez, arturo.cruz
 */
@Component
public class StrategyPerdidaFuerzaEjecutoria extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        Serializable tipoPerdida = tx.getParametros().get("tipoPerdida");

        if ("comparendo".equals(tipoPerdida)) {
            tx.with(Cuenta.ACT_COMPARENDOS).credito(tx.getValor());
            tx.with(Cuenta.PAT_COMPARENDOS).debito(tx.getValor());
        } else if ("transporte".equals(tipoPerdida)) {
            tx.with(Cuenta.ACT_TRANSPORTE_PUBLICO).credito(tx.getValor());
            tx.with(Cuenta.PAT_TRANSPORTE_PUBLICO).debito(tx.getValor());
        } else if ("subsanacion".equals(tipoPerdida)) {
            tx.with(Cuenta.ACT_SUBSANACIONES).credito(tx.getValor());
            tx.with(Cuenta.PAT_SUBSANACIONES).debito(tx.getValor());
        } else if ("patiosygruas".equals(tipoPerdida)) {
            tx.with(Cuenta.ACT_PATIOS_GRUAS).credito(tx.getValor());
            tx.with(Cuenta.PAT_PATIOS_GRUAS).debito(tx.getValor());
        } else {
            throw new FinancieroException("El tipo de perdida de fuerza ejecutoria no es válido");
        }

    }
}

