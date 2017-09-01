package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Constructor de transacciones financieras. Produce objetos de la clase {@link Transaccion}
 *
 * @author arturo.cruz
 */
class TransaccionBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransaccionBuilder.class);

    private Transaccion tx = new Transaccion();

    private TransaccionBuilder(String codigoEvento, Date fechaEvento, String descripcionEvento) {
        tx.setCodigoEvento(codigoEvento);
        tx.setFechaEvento(fechaEvento);
        tx.setDescripcionEvento(descripcionEvento);
        tx.setId(UUID.randomUUID());
        tx.setFechaRegistro(new Date());
    }

    static TransaccionBuilder forEvent(String codigoEvento, Date fechaEvento, String descripcionEvento) {
        return new TransaccionBuilder(
                codigoEvento,
                fechaEvento,
                descripcionEvento
        );
    }

    Transaccion build() throws FinancieroException {

        // Selecciona la estrategia de creación de entradas y la aplica a la transacción
        TransaccionStrategy transaccionStrategy = TransaccionStrategyFactory.get(tx.getCodigoEvento());
        if (transaccionStrategy == null) {
            String msg = String.format(
                    "No existe una estrategia para el código de evento %s. Los posibles valores son %s",
                    tx.getCodigoEvento(),
                    TransaccionStrategyFactory.getCodigosRegistrados());
            LOGGER.warn(msg);
            throw new FinancieroException(msg);
        }
        transaccionStrategy.buildEntradas(tx);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Entradas de transacción creadas por la estrategia: %s", tx));
        }

        // Verifica que la transacción sea balanceada
        BigDecimal balance = tx.balance();
        if (!(balance.compareTo(BigDecimal.ZERO) == 0)) {
            String msg = String.format("El balance de la transacción no es cero: %s", balance);
            LOGGER.warn(msg);
            throw new FinancieroException(msg);
        }

        // Verifica que no haya entradas en cero
        for (Entrada entrada : tx.getEntradas()) {
            if (entrada.getValor().compareTo(BigDecimal.ZERO) == 0) {
                throw new FinancieroException(String.format(
                        "Para la referencia %s-%s, contiene entradas con valor cero en la cuenta: %s (%s)",
                        tx.getTipoReferencia(),
                        tx.getReferencia(),
                        entrada.getCuenta().getNombre(),
                        entrada.getCuenta().getId()
                ));
            }
        }

        // Calcula los hash de todos los objetos
        StringBuilder hashBase = new StringBuilder();
        for (Entrada x : tx.getEntradas()) {
            StringBuilder xHash = new StringBuilder();
            xHash.append(x.getCuenta().getLastHash());
            xHash.append(x.getValor());
            if (x.getReferencia() != null)
                xHash.append(x.getReferencia());
            xHash.append(x.getFechaEvento().getTime());
            xHash.append(x.getFechaRegistro().getTime());
            x.setHash(DigestUtils.md5DigestAsHex(xHash.toString().getBytes()));
            x.getCuenta().setLastHash(x.getHash());
            hashBase.append(x.getHash());
        }
        tx.setHash(DigestUtils.md5DigestAsHex(hashBase.toString().getBytes()));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Transacción con hash: %s", tx));
        }

        return tx;
    }

    TransaccionBuilder withReference(String tipoReferencia, UUID referencia) {
        tx.setTipoReferencia(tipoReferencia);
        tx.setReferencia(referencia);
        return this;
    }

    TransaccionBuilder withValues(BigDecimal valor, Map<String, Serializable> parametros) {
        tx.setValor(valor);
        tx.setParametros(parametros);
        return this;
    }
}
