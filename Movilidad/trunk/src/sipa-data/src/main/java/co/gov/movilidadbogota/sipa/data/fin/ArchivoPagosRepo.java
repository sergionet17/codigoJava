package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by acpreda on 7/13/17.
 */
public interface ArchivoPagosRepo extends JpaRepository<ArchivoPagos, UUID> {
}
