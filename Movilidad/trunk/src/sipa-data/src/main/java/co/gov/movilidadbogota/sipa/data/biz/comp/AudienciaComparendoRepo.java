package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link AudienciaComparendo}
 * Created by paola.charry on 11/07/2017.
 */
@Transactional
public interface AudienciaComparendoRepo extends JpaRepository<AudienciaComparendo, UUID> {

    /**
     * Busca todas las audiencias de un abogado
     *
     * @param id Identificador unico del usuario perteneciente a un  abogado
     * @return Colección de audiencias de un abogado
     */
    List<AudienciaComparendo> findByAbogadoId(UUID id);

    List<AudienciaComparendo> findByComparendo(Comparendo comparendo);
}
