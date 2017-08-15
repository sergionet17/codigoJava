package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author carlos.rodriguez, arturo.cruz
 */
@Component
public class StrategyRevocatoriaFalloImpusoMulta extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {


        // Verifica el tipo de referencia
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);

        // Verifica que el comparendo exista
        checkComparendo(tx.getReferencia());

        // Obtiene el saldo de la deuda
        List<Entrada> entradasDeuda = entradas(tx.getReferencia(), Cuenta.ACT_COMPARENDOS);
        BigDecimal saldoDeuda = saldo(entradasDeuda);

        // Verifica que el saldo de la deuda corresponda con el valor de la transacción
        if (saldoDeuda.compareTo(tx.getValor()) != 0) {
            throw new FinancieroException(String.format(
                    "La deuda de la referencia %s-%s es de %s y no corresponde con el valor de la transacción %s",
                    tx.getTipoReferencia(),
                    tx.getReferencia(),
                    saldoDeuda,
                    tx.getValor()
            ));
        }

        tx.with(Cuenta.ACT_COMPARENDOS).credito(tx.getValor());
        tx.with(Cuenta.ING_COMPARENDOS).debito(tx.getValor());

    }
}