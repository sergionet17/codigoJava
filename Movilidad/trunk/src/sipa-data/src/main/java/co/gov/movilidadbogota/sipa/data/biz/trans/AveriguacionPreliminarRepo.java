package co.gov.movilidadbogota.sipa.data.biz.trans;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
/**
 * Repositorio para la clase {@link AveriguacionPreliminar}
 * @author oscar.mellizo
 */
public interface AveriguacionPreliminarRepo extends JpaRepository<AveriguacionPreliminar, UUID> {
}
