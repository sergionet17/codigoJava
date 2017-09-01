package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la gestión de datos de las los objetos de la clase {@link Transaccion}
 */
public interface TransaccionRepo extends JpaRepository<Transaccion, UUID> {

    /**
     * Encuentra una transacción que corresponda al mismo codigo de evento, tipo de referencia y referencia
     *
     * @param codigoEvento
     * @param tipoReferencia
     * @param referencia
     * @return
     */
    Transaccion findByCodigoEventoAndTipoReferenciaAndReferencia(
            String codigoEvento,
            String tipoReferencia,
            UUID referencia);
}
