package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para de clase {@link HorarioFirma}
 *
 * @author maria.rodriguez
 */
public interface HorarioFirmaRepo extends JpaRepository<HorarioFirma, UUID> {


}
