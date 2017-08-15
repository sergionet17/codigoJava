package co.gov.movilidadbogota.sipa.data.doc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExpedienteDocumentoRepo extends JpaRepository<ExpedienteDocumento, UUID> {
    List<Documento> findByExpediente(Expediente expediente);

    List<Documento> findByDocumentoIn(List<Documento> ids);

    List<ExpedienteDocumento> findByExpedienteIdOrigen(UUID id);

    ExpedienteDocumento findByDocumentoAndExpediente(Documento documento, Expediente expedienteParam);

    ExpedienteDocumento findByDocumento(Documento documento);
}
