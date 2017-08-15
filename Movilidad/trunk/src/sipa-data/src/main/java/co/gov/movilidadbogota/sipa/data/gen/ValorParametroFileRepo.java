package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la clase {@link ValorParametroFile}
 *
 * @author lorenzo.pinango
 */
public interface ValorParametroFileRepo extends JpaRepository<ValorParametroFile, UUID> {
    /**
     * Encuentra un archivo asociado al valor parametro
     *
     * @param id Identificador del valor parametro
     * @return El objeto {@link ValorParametroFile} correspondiente.
     */
    ValorParametroFile findByValorParametroId(UUID id);
}
