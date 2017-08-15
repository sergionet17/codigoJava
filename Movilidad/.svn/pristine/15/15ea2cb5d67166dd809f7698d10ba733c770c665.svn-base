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
public class StrategyConstanciaEjecutoriaSubsanacionIncumplimiento extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_INMOVILIZACION);
        Inmovilizacion inmovilizacion = findInmovilizacion(tx.getReferencia());
        if (inmovilizacion == null) {
            throw new FinancieroException(String.format(
                    "La referencia %s-%s no se encuentra",
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        // Verifica el saldo en cuenta de orden para que esta sea igual al valor de la transacción
        List<Entrada> entradasOrden = entradas(tx.getReferencia(), Cuenta.ORD_DEU_SUBSANACIONES);
        BigDecimal saldoOrden = saldo(entradasOrden);
        if (saldoOrden.compareTo(tx.getValor()) != 0) {
            throw new FinancieroException(String.format(
                    "Para la referencia %s-%s, el valor del saldo presuntivo %s no es igual a %s",
                    tx.getTipoReferencia(),
                    tx.getReferencia(),
                    saldoOrden,
                    tx.getValor()
            ));
        }

        tx.with(Cuenta.ORD_DEU_SUBSANACIONES).credito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_SUBSANACIONES_CONTRA).debito(tx.getValor());
        tx.with(Cuenta.ACT_SUBSANACIONES).debito(tx.getValor());
        tx.with(Cuenta.ING_SUBSANACIONES).credito(tx.getValor());

    }
}
