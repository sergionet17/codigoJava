package co.gov.movilidadbogota.sipa.data.aut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Usuario}
 *
 * @author arturo.cruz
 */
public interface UsuarioRepo extends JpaRepository<Usuario, UUID> {

    /**
     * Encuentra un usuario por su nombre de usuario
     *
     * @param username El nombre de usuario
     * @return El objeto {@link Usuario} correspondiente al nombre de usuario
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    @Transactional
    Usuario findOneByUsername(String username);

    /**
     * Encuentra un usuario por su nombre de usuario
     *
     * @param email El email de usuario
     * @return El objeto {@link Usuario} correspondiente al nombre de usuario
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    @Transactional
    Usuario findOneByEmail(String email);

    /**
     * Encuentra un usuario que en el nombre del cargo empiece con la palabra "Autoridad"
     *
     * @param cargo El cargo del usuario
     * @return Una lista de usuarios {@link Usuario} que tienen como cargo "autoridad" de alguna de las dependencias
     * o empty en caso que encuentre coincidencias con la consulta
     */
    // TODO Especificar el cargo para que coincida con una autoridad de tránsito (cargos aún sin especificar)
    List<Usuario> findByCargoStartingWith(String cargo);

    /**
     * Cuenta la cantidad de usuarios que poseen el perfil
     *
     * @param id Identificador del perfil
     * @return Cantidad de registros
     */
    @Query(value = "SELECT count(u) FROM Usuario u where u.perfil.id = ?1")
    Integer countByUsuarioPerfilId(UUID id);

    @Query(nativeQuery = true, value = " select * from usuario where id not in(select autoridad_id from turno_firma) and cargo like 'Autoridad%'")
    List<Usuario> findAutoridadesSinTurno();

    @Query(value = "SELECT u FROM Usuario u WHERE u.email = ?1")
    List<Usuario> findAllByEmail(String email);

}
