package co.gov.movilidadbogota.sipa.serv.financiero;


import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.springframework.stereotype.Component;

/**
 * Implementación de estrategia de entradas de transaccción
 *
 * @author carlos.rodriguez, arturo.cruz
 */
@Component
public class StrategyContraventorAbandonoVehiculo extends TransaccionStrategyAdapter {
    @Override
    public void buildEntradas(Transaccion tx) {

        // Verifica que el tipo de transacción sea "comparendo"
        tx.checkTipoReferencia(FinancieroConstants.TIPO_REFERENCIA_COMPARENDO);

        // Verifica que el comparendo exista
        checkComparendo(tx.getReferencia());

        // Verifica que no exista una transacción del mismo tipo para la misma referencia
        Transaccion previa = findTransaccionByCodigoEventoAndTipoReferenciaAndReferencia(
                tx.getCodigoEvento(),
                tx.getTipoReferencia(),
                tx.getReferencia()
        );
        if (previa != null) {
            throw new FinancieroException(String.format(
                    "Ya existe una transacción %s con el mismo concepto para la referencia %s - %s - %s ",
                    previa.getId(),
                    tx.getCodigoEvento(),
                    tx.getTipoReferencia(),
                    tx.getReferencia()
            ));
        }

        tx.with(Cuenta.ORD_DEU_COMPARENDOS).debito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_COMPARENDOS_CONTRA).credito(tx.getValor());

    }
}

