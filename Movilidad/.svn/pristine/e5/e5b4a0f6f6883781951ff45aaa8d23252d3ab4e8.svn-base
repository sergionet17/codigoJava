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
public class StrategyExoneracionTransportePublico extends TransaccionStrategyAdapter {
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

        // Obtiene el saldo en cuentas de orden
        BigDecimal saldoOrden = saldo(entradas(tx.getReferencia(), Cuenta.ORD_DEU_TRANSPORTE_PUBLICO));

        tx.with(Cuenta.ORD_DEU_TRANSPORTE_PUBLICO).credito(saldoOrden);
        tx.with(Cuenta.ORD_DEU_TRANSPORTE_PUBLICO_CONTRA).debito(saldoOrden);

    }
}