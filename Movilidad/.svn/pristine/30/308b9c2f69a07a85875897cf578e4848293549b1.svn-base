package co.gov.movilidadbogota.sipa.serv.financiero;


import co.gov.movilidadbogota.sipa.data.biz.comp.Inmovilizacion;
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
public class StrategyConstanciaEjecutoriaCobroPatiosGruas extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que se trate de una inmovilización
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_INMOVILIZACION);

        // Verifica la existencia de la inmovilización
        Inmovilizacion inmovilizacion = findInmovilizacion(tx.getReferencia());
        if (inmovilizacion == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no existe",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verifica que hay saldo en orden y que corresponda al valor de la transacción
        List<Entrada> entradasOrden = entradas(tx.getReferencia(), Cuenta.ORD_DEU_PATIOS_GRUAS);
        BigDecimal saldoOrden = saldo(entradasOrden);
        if (saldoOrden.compareTo(tx.getValor()) != 0) {
            throw new FinancieroException(String.format(
                    "Para la referencia %s-%s, el saldo de la cuenta de orden %s no corresponde al valor de la transacción %s",
                    tx.getTipoReferencia(),
                    tx.getReferencia(),
                    saldoOrden,
                    tx.getValor()
            ));
        }

        tx.with(Cuenta.ORD_DEU_PATIOS_GRUAS).credito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_PATIOS_GRUAS_CONTRA).debito(tx.getValor());
        tx.with(Cuenta.ACT_PATIOS_GRUAS).debito(tx.getValor());
        tx.with(Cuenta.ING_PATIOS_GRUAS).credito(tx.getValor());

    }
}

