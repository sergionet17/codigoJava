package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * Created by Admin on 23/07/2017.
 */
public interface AlarmaRangoRepo extends JpaRepository<AlarmaRango, String> {
    /**
     * Encuentra una depedencia por su id
     *
     * @param id La clave de la depedencia
     * @return El objeto {@link AlarmaRango} correspondiente al clave de la alarma
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    @Query("SELECT r FROM AlarmaRango r WHERE r.id = ?1")
    AlarmaRango findRangoById(UUID id);

}
