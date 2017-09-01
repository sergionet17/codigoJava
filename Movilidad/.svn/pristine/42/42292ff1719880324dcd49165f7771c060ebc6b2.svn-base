package co.gov.movilidadbogota.sipa.data.persona;

import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 *  Repo que se encarga de Persona.
 *  @author maria.rodriguez
 */
public interface PersonaRepo extends JpaRepository<Persona, UUID> {

    Persona findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(TipoDocumento tipoDocumento, String numero);

}
