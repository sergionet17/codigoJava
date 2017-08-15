package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Municipio}
 *
 * @author lorenzo.pinango
 */
public interface MunicipioRepo extends BaseTableReferenceRepo<Municipio, UUID> {

    /**
     * Encuentra el municipio correspondiente al id solicitado.
     *
     * @param id del municipio que se solicita
     * @return El único registro que corresponde al id
     */
    @Override
    @Query("SELECT m FROM Municipio m WHERE m.identifier = ?1")
    Municipio findById(
            UUID id);

    /**
     * Encuentra un municipio por el id
     *
     * @param id    id del municipio
     * @param fecha fecha de inicio de vigencia
     * @return El objeto {@link Municipio} correspondiente al id
     */
    @Override
    @Query("SELECT m FROM Municipio m WHERE m.id = ?1 AND m.inicioVigencia <= ?2 AND (m.finVigencia IS NULL OR m.finVigencia > ?2)")
    <String> Municipio findByIdAndFecha(String id, Date fecha);

    /**
     * Obtiene la lista de objetos
     *
     * @return lista de objetos
     */
    @Override
    @Query("SELECT m FROM Municipio m WHERE m.finVigencia IS NULL")
    List<Municipio> findAllByFinVigenciaNull();

    /**
     * Actualiza la fecha de cierre de los registros vigentes
     *
     * @param finVigencia fecha de cierre de los registros
     */
    @Override
    @Modifying
    @Transactional
    @Query("UPDATE Municipio SET finVigencia=?1 WHERE finVigencia IS NULL")
    void setFinVigenciaByVigentes(Date finVigencia);

    /**
     * Retorna la version actual de los registros
     */
    @Override
    @Query("SELECT MAX(version) FROM Municipio")
    Long getCurrentVersion();
}
