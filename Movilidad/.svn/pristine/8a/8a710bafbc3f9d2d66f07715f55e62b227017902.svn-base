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
public class StrategyCasosEspeciales extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        Serializable tipoCasoEspecial = tx.getParametros().get("tipoCasoEspecial");

        if ("comparendo".equals(tipoCasoEspecial)) {
            tx.with(Cuenta.ACT_COMPARENDOS).debito(tx.getValor());
            tx.with(Cuenta.PAT_COMPARENDOS).credito(tx.getValor());
        } else if ("transporte".equals(tipoCasoEspecial)) {
            tx.with(Cuenta.ACT_TRANSPORTE_PUBLICO).debito(tx.getValor());
            tx.with(Cuenta.PAT_TRANSPORTE_PUBLICO).credito(tx.getValor());
        } else if ("subsanacion".equals(tipoCasoEspecial)) {
            tx.with(Cuenta.ACT_SUBSANACIONES).debito(tx.getValor());
            tx.with(Cuenta.PAT_SUBSANACIONES).credito(tx.getValor());
        } else if ("patiosygruas".equals(tipoCasoEspecial)) {
            tx.with(Cuenta.ACT_PATIOS_GRUAS).debito(tx.getValor());
            tx.with(Cuenta.PAT_PATIOS_GRUAS).credito(tx.getValor());
        } else {
            throw new FinancieroException("El tipo de caso especial no es válido");
        }

    }
}
