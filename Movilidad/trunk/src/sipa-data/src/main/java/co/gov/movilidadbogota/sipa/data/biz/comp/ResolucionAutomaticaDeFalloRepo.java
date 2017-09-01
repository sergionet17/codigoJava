package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la clase {@link ResolucionAutomaticaDeFallo}
 *
 * @author or maria.rodriguez
 */
public interface ResolucionAutomaticaDeFalloRepo extends JpaRepository<ResolucionAutomaticaDeFallo, UUID> {
    /**
     * Encuentra una resolucion automática de fallo
     *
     * @param id Identificador del comparendo
     * @return El objeto {@link ResolucionAutomaticaDeFallo}.
     */
    ResolucionAutomaticaDeFallo findByComparendoId(UUID id);


}
