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
public class StrategyApropiacionRemateCuentaSalarios extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        Serializable tipoApropiacion = tx.getParametros().get("tipoApropiacion");

        if ("comparendo".equals(tipoApropiacion)) {
            tx.with(Cuenta.ACT_COMPARENDOS).credito(tx.getValor());
        } else if ("transporte".equals(tipoApropiacion)) {
            tx.with(Cuenta.ACT_TRANSPORTE_PUBLICO).credito(tx.getValor());
        } else if ("subsanacion".equals(tipoApropiacion)) {
            tx.with(Cuenta.ACT_SUBSANACIONES).credito(tx.getValor());
        } else if ("patiosygruas".equals(tipoApropiacion)) {
            tx.with(Cuenta.ACT_PATIOS_GRUAS).credito(tx.getValor());
        } else if ("transporte".equals(tipoApropiacion)) {
            tx.with(Cuenta.ACT_INTERESES).credito(tx.getValor());
        } else if ("transporte".equals(tipoApropiacion)) {
            tx.with(Cuenta.ACT_COSTAS).credito(tx.getValor());
        } else {
            throw new FinancieroException("El tipo de apropiacion no es válido");
        }
        tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
        tx.with(Cuenta.PAS_DEPOSITO_JUDICIAL).credito(tx.getValor());
        tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
    }
}
