package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Estrategia de entradas de transacción para constancia ejecutoria con multa
 * CU242.9 Registrar constancia ejecutoria con multa
 *
 * @author arturo.cruz
 */
@Component
public class StrategyConstanciaEjecutoriaConMulta extends TransaccionStrategyAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyConstanciaEjecutoriaConMulta.class);

    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de transacción sea "comparendo"
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);

        // Verifica que el comparendo exista
        checkComparendo(tx.getReferencia());

        // Verifica que el comparendo tenga registros previos en cartera de orden
        List<Entrada> entradasOrden = entradas(tx.getReferencia(), Cuenta.ORD_DEU_COMPARENDOS);
        if (entradasOrden.size() == 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene registros previos de deuda presuntiva",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Obtiene el saldo de la deuda en cuenta de orden por el id de comparendo
        BigDecimal saldoOrden = saldo(entradasOrden);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format(
                    "La referencia %s-%s tiene saldo en cuentas de orden: %s",
                    tx.getTipoReferencia(),
                    tx.getReferencia(),
                    saldoOrden
            ));
        }

        // Verifica que el saldo en deuda presuntiva sea igual al valor de la transacción
        if (tx.getValor().compareTo(saldoOrden) != 0) {
            throw new FinancieroException(String.format(
                    "Para la referencia %s-%s la deuda presuntiva %s no es igual al valor de la transacción: %s",
                    tx.getTipoReferencia(),
                    tx.getReferencia(),
                    saldoOrden,
                    tx.getValor()
            ));
        }
        tx.with(Cuenta.ORD_DEU_COMPARENDOS).credito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_COMPARENDOS_CONTRA).debito(tx.getValor());
        tx.with(Cuenta.ACT_COMPARENDOS).debito(tx.getValor());
        tx.with(Cuenta.ING_COMPARENDOS).credito(tx.getValor());

    }
}
