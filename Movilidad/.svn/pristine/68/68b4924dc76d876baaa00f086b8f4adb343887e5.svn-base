package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.UUID;

/**
 * Repositorio para la clase {@link TurnoFirma}
 * @author maria.rodriguez
 */
public interface TurnoFirmaRepo extends JpaRepository<TurnoFirma, UUID> {

    TurnoFirma findByAutoridadId(UUID id);

    @Query("SELECT tf.autoridad FROM TurnoFirma tf WHERE tf.desde <= ?1 AND (tf.hasta IS NULL OR tf.hasta > ?1)")
    Usuario findByDate(Date today);


}
