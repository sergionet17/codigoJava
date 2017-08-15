package co.gov.movilidadbogota.sipa.serv.financiero;


import co.gov.movilidadbogota.sipa.data.biz.trans.Investigacion;
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
public class StrategyFalloProcesosInvestigacionTransportePublico extends TransaccionStrategyAdapter {
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

        tx.with(Cuenta.ORD_DEU_TRANSPORTE_PUBLICO).debito(tx.getValor());
        tx.with(Cuenta.ORD_DEU_TRANSPORTE_PUBLICO_CONTRA).credito(tx.getValor());

    }

}
