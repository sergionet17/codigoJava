package co.gov.movilidadbogota.sipa.data.aut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Perfil}
 *
 * @author lorenzo.pinango
 */
public interface PerfilRepo extends JpaRepository<Perfil, UUID> {

    /**
     * Cuenta la cantidad de perfiles que tienen los roles consultados
     *
     * @param roles    Cadena de caracteres con los roles a consultar
     * @param cantidad cantidad de roles a consultar
     * @return La cantidad de perfiles que poseen los mismos roles
     */
    @Query(value = "SELECT nvl(max(COUNT(DISTINCT pr.PERFIL_ID)),0) " +
            "FROM PERFIL_ROLES pr WHERE pr.ROLES_ID IN (:roles) " +
            "AND (SELECT COUNT(*) FROM PERFIL_ROLES pr2 WHERE pr2.PERFIL_ID = pr.PERFIL_ID) = :cantidad " +
            "GROUP BY pr.PERFIL_ID",
            nativeQuery = true)
    Integer countByEqualRoles(@Param("roles") List<UUID> roles, @Param("cantidad") Integer cantidad);

    /**
     * Cuenta la cantidad de roles que tienen los roles consultados excluyendo un perfil
     *
     * @param roles    Cadena de caracteres con los roles a consultar
     * @param cantidad cantidad de roles a consultar
     * @param id       Identificador del perfil a excluir
     * @return La cantidad de perfiles que poseen los mismos roles excluyendo un perfil
     */
    @Query(value = "SELECT nvl(max(COUNT(DISTINCT pr.PERFIL_ID)),0) " +
            "FROM PERFIL_ROLES pr WHERE pr.ROLES_ID IN (:roles) " +
            "AND (SELECT COUNT(*) FROM PERFIL_ROLES pr2 WHERE pr2.PERFIL_ID = pr.PERFIL_ID) = :cantidad " +
            "AND pr.PERFIL_ID <> :id " +
            "GROUP BY pr.PERFIL_ID",
            nativeQuery = true)
    Integer countByEqualRolesNotPerfil(@Param("roles") List<UUID> roles, @Param("cantidad") Integer cantidad, @Param("id") String id);

    /**
     * Lista las perfiles activos
     *
     * @return Lista de registros
     */
    @Query("SELECT p FROM Perfil p WHERE p.activo = TRUE")
    List<Perfil> findAllActivos();

    /**
     * Cuenta la cantidad de perfiles que poseen el rol
     *
     * @param id Identificador del rol
     * @return Cantidad de registros
     */
    @Query(value = "SELECT count(p) FROM Perfil p " +
            "JOIN p.roles r where r.id = ?1")
    Integer countByPerfilRolId(UUID id);
}
