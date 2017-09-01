package co.gov.movilidadbogota.sipa.data.id;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la clase {@link TipoDocumento}
 *
 * @author arturo.cruz
 */
public interface TipoDocumentoRepo extends JpaRepository<TipoDocumento, Integer> {

    /**
     * Encuentra el tipo de documento correspondiente al código solicitado.
     *
     * @param sigla del tipo de documento que se solicita
     * @return El único registro que corresponde al código
     */
    TipoDocumento findBySigla(
            String sigla);

}
