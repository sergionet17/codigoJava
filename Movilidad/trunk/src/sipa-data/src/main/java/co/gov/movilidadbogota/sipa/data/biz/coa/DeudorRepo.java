package co.gov.movilidadbogota.sipa.data.biz.coa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la clase {@link Deudor}
 *
 * @author lorenzo.pinango
 */
public interface DeudorRepo extends JpaRepository<Deudor, UUID> {


}
