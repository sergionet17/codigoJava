package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Localidad}
 *
 * @author lorenzo.pinango
 */
public interface LocalidadRepo extends BaseTableReferenceRepo<Localidad, UUID> {
    /**
     * Encuentra la localidad correspondiente al id solicitado.
     *
     * @param id de la localidad que se solicita
     * @return El único registro que corresponde al id
     */
    @Override
    @Query("SELECT l FROM Localidad l WHERE l.identifier = ?1")
    Localidad findById(
            UUID id);

    /**
     * Encuentra una localidad por el id
     *
     * @param id    id de la localidad
     * @param fecha fecha de inicio de vigencia
     * @return El objeto {@link Localidad} correspondiente al id
     */
    @Override
    @Query("SELECT l FROM Localidad l WHERE l.id = ?1 AND l.inicioVigencia <= ?2 AND (l.finVigencia IS NULL OR l.finVigencia > ?2)")
    <String> Localidad findByIdAndFecha(String id, Date fecha);

    /**
     * Obtiene la lista de objetos
     *
     * @return lista de objetos
     */
    @Override
    @Query("SELECT l FROM Localidad l WHERE l.finVigencia IS NULL")
    List<Localidad> findAllByFinVigenciaNull();

    /**
     * Actualiza la fecha de cierre de los registros vigentes
     *
     * @param finVigencia fecha de cierre de los registros
     */
    @Override
    @Transactional
    @Modifying
    @Query("UPDATE Localidad SET finVigencia=?1 WHERE finVigencia IS NULL")
    void setFinVigenciaByVigentes(Date finVigencia);

    /**
     * Retorna la version actual de los registros
     */
    @Override
    @Query("SELECT MAX(version) FROM Localidad")
    Long getCurrentVersion();
}
