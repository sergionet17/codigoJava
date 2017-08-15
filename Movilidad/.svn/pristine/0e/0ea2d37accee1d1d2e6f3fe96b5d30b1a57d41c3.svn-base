package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link CategoriaLicencia}
 *
 * @author diego.gomez
 */

public interface CategoriaLicenciaRepo extends JpaRepository<CategoriaLicencia, Integer> {

    /**
     * Encuentra la Categoria de la licencia correspondiente al nombre solicitado.
     *
     * @param nombre de la categoria de la licencia que se solicita
     * @return El único registro que corresponde al nombre
     */
    CategoriaLicencia findByNombre(
            String nombre);


}