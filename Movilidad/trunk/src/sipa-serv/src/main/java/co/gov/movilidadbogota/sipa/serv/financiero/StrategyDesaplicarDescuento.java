package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Estrategia de creación de entradas para la aplicación de descuentos
 * CU242.3 Aplicar Descuento
 *
 * @author arturo.cruz
 */
@Component
public class StrategyDesaplicarDescuento extends TransaccionStrategyAdapter {

    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de transacción sea "comparendo"
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);

        // Verifica que el comparendo exista
        checkComparendo(tx.getReferencia());

        // Verifica que el comparendo tenga registros previos en cartera de balance
        List<Entrada> entradasBalance = entradas(tx.getReferencia(), Cuenta.ACT_COMPARENDOS);
        if (entradasBalance.size() == 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene registros previos de deuda",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verifica que el comparendo tenga descuentos aplicados
        List<Entrada> entradasDescuento = entradas(tx.getReferencia(), Cuenta.ACT_DESCUENTO_COMPARENDOS);
        if (entradasDescuento.size() == 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene registros previos de descuento",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verificar que el saldo en cuenta descuentos sea igual con el valor de la transacción
        // Obtiene el saldo del descuento
        BigDecimal saldoDescuento = saldo(entradasDescuento);
        if (saldoDescuento.compareTo(tx.getValor()) != 0) {
            throw new FinancieroException(String.format(
                    "El descuento sólo se puede desaplicar cuando el saldo %s es igual al valor de la transacción %s",
                    saldoDescuento,
                    tx.getValor()
            ));
        }

        // Crea las entradas correspondientes
        tx.with(Cuenta.ACT_COMPARENDOS).debito(tx.getValor());
        tx.with(Cuenta.ACT_DESCUENTO_COMPARENDOS).credito(tx.getValor());

    }
}
