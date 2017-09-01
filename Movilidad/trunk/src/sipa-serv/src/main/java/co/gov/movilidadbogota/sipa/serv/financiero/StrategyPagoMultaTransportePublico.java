package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.biz.trans.Investigacion;
import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author carlos.rodriguez, arturo.cruz
 */
@Component
public class StrategyPagoMultaTransportePublico extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que se trate de una investigación que exista
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_INVESTIGACION);
        Investigacion investigacion = findInvestigacion(tx.getReferencia());
        if (investigacion == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no existe",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Obtiene el saldo de la deuda
        BigDecimal saldo = saldo(tx.getReferencia(), Cuenta.ACT_TRANSPORTE_PUBLICO);

        // Si el pago es superior al saldo entonces debe realizar el regristo en cuentas por pagar
        if (saldo.compareTo(tx.getValor()) < 0) {
            BigDecimal remanente = tx.getValor().subtract(saldo);
            tx.with(Cuenta.ACT_TRANSPORTE_PUBLICO).credito(saldo);
            tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
            tx.with(Cuenta.PAS_CUENTAS_POR_PAGAR).credito(remanente);
        } else {
            tx.with(Cuenta.ACT_TRANSPORTE_PUBLICO).credito(tx.getValor());
            tx.with(Cuenta.ACT_HACIENDA).debito(tx.getValor());
        }

    }
}
