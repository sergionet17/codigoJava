package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by acpreda on 7/13/17.
 */
public interface PagoRepo extends JpaRepository<Pago, UUID> {

    List<Pago> findByArchivoPagos(ArchivoPagos archivoPagos);

    List<Pago> findByEstadoPago(EstadoPago estado);
}
