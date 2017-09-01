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
public class StrategyPagoSuperiorObligacion extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        Serializable tipoPago = tx.getParametros().get("tipoPago");

        if ("comparendo".equals(tipoPago)) {
            tx.with(Cuenta.ACT_COMPARENDOS).credito(tx.getValor());
        } else if ("transporte".equals(tipoPago)) {
            tx.with(Cuenta.ACT_TRANSPORTE_PUBLICO).credito(tx.getValor());
        } else if ("subsanacion".equals(tipoPago)) {
            tx.with(Cuenta.ACT_SUBSANACIONES).credito(tx.getValor());
        } else if ("patiosygruas".equals(tipoPago)) {
            tx.with(Cuenta.ACT_PATIOS_GRUAS).credito(tx.getValor());
        } else if ("transporte".equals(tipoPago)) {
            tx.with(Cuenta.ACT_INTERESES).credito(tx.getValor());
        } else if ("transporte".equals(tipoPago)) {
            tx.with(Cuenta.ACT_COSTAS).credito(tx.getValor());
        } else {
            throw new FinancieroException("El tipo de pago superior a obligación no es válido");
        }
        tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
        tx.with(Cuenta.PAS_CUENTAS_POR_PAGAR).credito(tx.getValor());
    }
}
