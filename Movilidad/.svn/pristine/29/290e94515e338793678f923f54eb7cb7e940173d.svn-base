package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link FuenteTituloEjecutivo}
 *
 * @author lorenzo.pinango
 */
public interface FuenteTituloEjecutivoRepo extends BaseTableReferenceRepo<FuenteTituloEjecutivo, UUID> {
    /**
     * Encuentra la fuente correspondiente al id solicitado.
     *
     * @param id de la fuente que se solicita
     * @return El único registro que corresponde al id
     */
    @Override
    @Query("SELECT f FROM FuenteTituloEjecutivo f WHERE f.identifier = ?1")
    FuenteTituloEjecutivo findById(UUID id);

    /**
     * Encuentra una fuente por el id
     *
     * @param id    id de la fuente
     * @param fecha fecha de inicio de vigencia
     * @return El objeto {@link FuenteTituloEjecutivo} correspondiente al id
     */
    @Override
    @Query("SELECT f FROM FuenteTituloEjecutivo f WHERE f.id = ?1 AND f.inicioVigencia <= ?2 AND (f.finVigencia IS NULL OR f.finVigencia > ?2)")
    <Integer> FuenteTituloEjecutivo findByIdAndFecha(Integer id, Date fecha);


    /**
     * Obtiene la lista de objetos
     *
     * @return lista de objetos
     */
    @Override
    @Query("SELECT f FROM FuenteTituloEjecutivo f WHERE f.finVigencia IS NULL")
    List<FuenteTituloEjecutivo> findAllByFinVigenciaNull();

    /**
     * Actualiza la fecha de cierre de los registros vigentes
     *
     * @param finVigencia fecha de cierre de los registros
     */
    @Override
    @Modifying
    @Transactional
    @Query("UPDATE FuenteTituloEjecutivo SET finVigencia=?1 WHERE finVigencia IS NULL")
    void setFinVigenciaByVigentes(Date finVigencia);

    /**
     * Retorna la version actual de los registros
     */
    @Override
    @Query("SELECT MAX(version) FROM FuenteTituloEjecutivo")
    Long getCurrentVersion();


    List<FuenteTituloEjecutivo> findByIdIn(List<Integer> ids);


    FuenteTituloEjecutivo findById(Integer id);
}

