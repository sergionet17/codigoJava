package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Pais}
 *
 * @author lorenzo.pinango
 */
public interface PaisRepo extends BaseTableReferenceRepo<Pais, UUID> {
    /**
     * Encuentra el pais correspondiente al id solicitado.
     *
     * @param id del pais que se solicita
     * @return El único registro que corresponde al id
     */
    @Override
    @Query("SELECT p FROM Pais p WHERE p.identifier = ?1")
    Pais findById(UUID id);

    /**
     * Encuentra un pais por el id
     *
     * @param id    id del pais
     * @param fecha fecha de inicio de vigencia
     * @return El objeto {@link Pais} correspondiente al id
     */
    @Override
    @Query("SELECT p FROM Pais p WHERE p.id = ?1 AND p.inicioVigencia <= ?2 AND (p.finVigencia IS NULL OR p.finVigencia > ?2)")
    <Integer> Pais findByIdAndFecha(Integer id, Date fecha);


    /**
     * Obtiene la lista de objetos
     *
     * @return lista de objetos
     */
    @Override
    @Query("SELECT p FROM Pais p WHERE p.finVigencia IS NULL")
    List<Pais> findAllByFinVigenciaNull();

    /**
     * Actualiza la fecha de cierre de los registros vigentes
     *
     * @param finVigencia fecha de cierre de los registros
     */
    @Override
    @Modifying
    @Transactional
    @Query("UPDATE Pais SET finVigencia=?1 WHERE finVigencia IS NULL")
    void setFinVigenciaByVigentes(Date finVigencia);

    /**
     * Retorna la version actual de los registros
     */
    @Override
    @Query("SELECT MAX(version) FROM Pais")
    Long getCurrentVersion();
}

