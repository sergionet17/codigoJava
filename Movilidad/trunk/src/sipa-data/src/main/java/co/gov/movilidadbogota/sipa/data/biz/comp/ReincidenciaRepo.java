package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by maria on 7/27/17.
 */
public interface ReincidenciaRepo extends JpaRepository<Reincidencia, UUID> {

    List<Reincidencia> findByReincidenteId(UUID id);
}
