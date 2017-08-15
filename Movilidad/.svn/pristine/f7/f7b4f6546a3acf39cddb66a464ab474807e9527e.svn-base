package co.gov.movilidadbogota.sipa.data.aut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Rol}
 *
 * @author lorenzo.pinango
 */
public interface RolRepo extends JpaRepository<Rol, UUID> {

    /**
     * Encuentra un rol por su nombre
     *
     * @param nombre El nombre de rol
     * @return El objeto {@link Rol} correspondiente al nombre del rol
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    Rol findByName(String nombre);

    /**
     * Encuentra un rol por su id
     *
     * @param id El id de rol
     * @return El objeto {@link Rol} correspondiente al id del rol
     * solicitado o null en caso que no se encuentre una correspondencia.
     */
    Rol findById(UUID id);

    /**
     * Cuenta la cantidad de roles que tienen los permisos consultados
     *
     * @param permisos Cadena de caracteres con los permisos a consultar
     * @param cantidad cantidad de permisos a consultar
     * @return La cantidad de roles que poseen los mismos permisos
     */
    @Query(value = "SELECT nvl(max(COUNT(DISTINCT rp.ROL_ID)),0) " +
            "FROM ROL_PERMISOS rp WHERE rp.PERMISOS_NOMBRE IN (:permisos) " +
            "AND (SELECT COUNT(*) FROM ROL_PERMISOS rp2 WHERE rp2.ROL_ID = rp.ROL_ID) = :cantidad " +
            "GROUP BY rp.ROL_ID",
            nativeQuery = true)
    Integer countByEqualPermisos(@Param("permisos") List<String> permisos, @Param("cantidad") Integer cantidad);

    /**
     * Cuenta la cantidad de roles que tienen los permisos consultados excluyendo un rol
     *
     * @param permisos Cadena de caracteres con los permisos a consultar
     * @param cantidad cantidad de permisos a consultar
     * @param id       Identificador del rol a excluir
     * @return La cantidad de roles que poseen los mismos permisos excluyendo un rol
     */
    @Query(value = "SELECT nvl(max(COUNT(DISTINCT rp.ROL_ID)),0) " +
            "FROM ROL_PERMISOS rp WHERE rp.PERMISOS_NOMBRE IN (:permisos) " +
            "AND (SELECT COUNT(*) FROM ROL_PERMISOS rp2 WHERE rp2.ROL_ID = rp.ROL_ID) = :cantidad " +
            "AND rp.ROL_ID <> :id " +
            "GROUP BY rp.ROL_ID",
            nativeQuery = true)
    Integer countByEqualPermisosNotRol(@Param("permisos") List<String> permisos, @Param("cantidad") Integer cantidad, @Param("id") String id);

    /**
     * Lista las roles activos
     *
     * @return Lista de registros
     */
    @Query("SELECT r FROM Rol r WHERE r.activo = TRUE")
    List<Rol> findAllActivos();
}
