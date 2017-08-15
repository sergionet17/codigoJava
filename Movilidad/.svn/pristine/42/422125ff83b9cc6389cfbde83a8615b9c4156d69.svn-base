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
public class StrategyAplicarDescuento extends TransaccionStrategyAdapter {

    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de transacción sea "comparendo"
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);

        // Verifica que el comparendo exista
        checkComparendo(tx.getReferencia());

        // Verifica que el comparendo tenga registros previos en cartera de balance
        List<Entrada> entradasBalance = entradas(
                tx.getReferencia(),
                Cuenta.ACT_COMPARENDOS
        );
        if (entradasBalance.size() == 0) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no tiene registros previos de deuda",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verificar que el saldo en cuenta de balance sea menor o igual con el valor del descuento
        // Obtiene el saldo de la deuda
        BigDecimal saldoDeuda = saldo(entradasBalance);
        if (saldoDeuda.compareTo(tx.getValor()) != 0) {
            throw new FinancieroException(String.format(
                    "El descuento sólo se puede aplicar cuando el saldo %s es igual al valor del descuento %s",
                    saldoDeuda,
                    tx.getValor()
            ));
        }

        // Crea las entradas correspondientes
        tx.with(Cuenta.ACT_COMPARENDOS).credito(saldoDeuda);
        tx.with(Cuenta.ACT_DESCUENTO_COMPARENDOS).debito(tx.getValor());
        BigDecimal remanente = tx.getValor().subtract(saldoDeuda);
        if (remanente.compareTo(BigDecimal.ZERO) > 0) {
            tx.with(Cuenta.PAS_CUENTAS_POR_PAGAR).credito(remanente);
        }

    }


}
