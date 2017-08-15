package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import co.gov.movilidadbogota.sipa.data.fin.TransaccionRepo;
import co.gov.movilidadbogota.sipa.serv.api.EventoFinanciero;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Implementación del servicio financiero
 *
 * @author arturo.cruz
 */
@Component
public class FinancieroServiceImpl implements FinancieroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinancieroServiceImpl.class);

    @Autowired
    TransaccionRepo transaccionRepo;

    @Override
    public UUID registrar(EventoFinanciero evento) throws FinancieroException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Evento financiero: %s", evento));
        }

        Transaccion tx = TransaccionBuilder.forEvent(
                evento.getCodigoEvento(),
                evento.getFechaEvento(),
                evento.getDescripcionEvento()
        ).withReference(
                evento.getTipoReferencia(),
                evento.getReferencia()
        ).withValues(
                evento.getValor(),
                evento.getParametros()
        ).build();

        transaccionRepo.save(tx);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Transacción: %s", tx));
        }

        return tx.getId();
    }

}
