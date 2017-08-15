package co.gov.movilidadbogota.sipa.data.aut;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link HistoricoPassword}
 *
 * @author lorenzo.pinango
 */
public interface HistoricoPasswordRepo extends JpaRepository<HistoricoPassword, UUID> {

    /**
     * Obtiene una lista de registros a partir del id del usuario
     *
     * @param id
     * @param pageable
     * @return Lista
     */
    List<HistoricoPassword> findByUsuarioId(UUID id, Pageable pageable);


}
