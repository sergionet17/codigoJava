package co.gov.movilidadbogota.sipa.data.aut;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la clase {@link Permiso}
 *
 * @author arturo.cruz
 */
public interface PermisoRepo extends JpaRepository<Permiso, UUID> {

    /**
     * Encuentra un permiso por su nombre
     *
     * @param nombre El nombre de usuario
     * @return El objeto {@link Permiso} correspondiente al nombre del permiso
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    Permiso findByNombre(String nombre);

}
