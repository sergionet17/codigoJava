package co.gov.movilidadbogota.sipa.data.doc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para el manejo de la persistencia de la clase {@link Plantilla}
 */
public interface PlantillaRepo extends JpaRepository<Plantilla, UUID> {

    /**
     * Obtiene un objeto plantilla por el nombre de la misma.
     *
     * @param nombrePlantilla Es el nombre de la plantilla.
     * @return El objeto plantilla correspondiente o null en caso que no se
     * encuentre un objeto que corresponda.
     */
    Plantilla findByNombre(String nombrePlantilla);
}
