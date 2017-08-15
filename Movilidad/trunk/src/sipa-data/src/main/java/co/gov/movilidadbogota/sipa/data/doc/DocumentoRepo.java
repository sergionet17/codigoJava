package co.gov.movilidadbogota.sipa.data.doc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la clase {@link Documento}
 *
 * @author arturo.cruz
 */
public interface DocumentoRepo extends JpaRepository<Documento, UUID> {
}
