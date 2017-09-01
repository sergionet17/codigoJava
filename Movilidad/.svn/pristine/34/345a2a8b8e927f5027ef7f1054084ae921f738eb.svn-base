package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.biz.comp.Inmovilizacion;
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
public class StrategyFalloCobroPatiosGruas extends TransaccionStrategyAdapter {
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

        tx.with(Cuenta.ORD_DEU_PATIOS_GRUAS).debito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_PATIOS_GRUAS_CONTRA).credito(tx.getValor());

    }
}