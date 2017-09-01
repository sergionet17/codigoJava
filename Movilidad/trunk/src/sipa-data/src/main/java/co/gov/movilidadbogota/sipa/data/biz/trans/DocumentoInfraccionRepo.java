package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link DocumentoInfraccion}
 * @author oscar.mellizo
 */
public interface DocumentoInfraccionRepo extends JpaRepository<DocumentoInfraccion, UUID>{
    List<DocumentoInfraccion> findByDocumentoTipoDocumental(TipoDocumental tipoDocumental);
}
