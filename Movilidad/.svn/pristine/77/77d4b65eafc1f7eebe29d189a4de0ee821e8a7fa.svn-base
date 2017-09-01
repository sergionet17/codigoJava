package co.gov.movilidadbogota.sipa.data.loc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Dependencia}
 *
 * @author lorenzo.pinango
 */
public interface DependenciaRepo extends JpaRepository<Dependencia, UUID> {

    /**
     * Encuentra una dependencia por su nombre
     *
     * @param nombre El nombre de la dependencia
     * @return El objeto {@link Dependencia} correspondiente al nombre de la dependencia
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    Dependencia findByNombre(String nombre);

    /**
     * Encuentra una depedencia por su id
     *
     * @param id El id de la depedencia
     * @return El objeto {@link Dependencia} correspondiente al id de la dependencia
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    Dependencia findById(UUID id);

    /**
     * Encuentra las depedencias que no tienen dependencia superior
     *
     * @return Lista de dependencias
     */
    @Query("SELECT d FROM Dependencia d WHERE d.dependencia IS NULL")
    List<Dependencia> findAllDependenciasPrimarias();

    /**
     * Encuentra las depedencias excluyendo la del id enviado
     *
     * @return Lista de dependencias
     */
    @Query("SELECT d FROM Dependencia d WHERE d.id != ?1")
    List<Dependencia> findAllNotId(UUID id);

    /**
     * Encuentra una depedencia por el id del responsable
     *
     * @param id El id del responsable de la dependencia
     * @return El objeto {@link Dependencia} correspondiente al id de la dependencia
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    Dependencia findByResponsableId(UUID id);

    /**
     * Encuentra una depedencia por el id del responsable que no contenga la dependencia
     *
     * @param usuarioId El id de la depedencia
     * @return El objeto {@link Dependencia} correspondiente al id de la dependencia
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    @Query("SELECT d FROM Dependencia d WHERE d.responsable.id = ?1 AND d.id != ?2")
    Dependencia findByResponsableIdNotDependencia(UUID usuarioId, UUID dependenciaId);

    /**
     * Lista las dependencias que no tienen responsable
     *
     * @return Lista de registros
     */
    @Query("SELECT d FROM Dependencia d WHERE d.responsable IS NULL")
    List<Dependencia> findAllResponsableNull();
}
