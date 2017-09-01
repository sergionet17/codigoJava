package co.gov.movilidadbogota.sipa.data.doc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarpetaDocumentoRepo extends JpaRepository<CarpetaDocumento, UUID> {
    List<CarpetaDocumento> findByCarpeta(Carpeta carpeta);
}
