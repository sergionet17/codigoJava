package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Estrategia de entradas de transacción para aceptación de comparendo
 * CU242.7 Registrar aceptación de comparendo
 *
 * @author arturo.cruz
 */
@Component
public class StrategyAceptacionComparendo extends TransaccionStrategyAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyAceptacionComparendo.class);

    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de transacción sea "comparendo"
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);

        // Verifica que el comparendo exista
        checkComparendo(tx.getReferencia());

        BigDecimal saldo = saldo(tx.getReferencia(), Cuenta.ORD_DEU_COMPARENDOS);

        tx.with(Cuenta.ORD_DEU_COMPARENDOS).credito(saldo);
        tx.with(Cuenta.ORD_DEU_COMPARENDOS_CONTRA).debito(saldo);
        tx.with(Cuenta.ACT_COMPARENDOS).debito(saldo);
        tx.with(Cuenta.ING_COMPARENDOS).credito(saldo);

    }

}
