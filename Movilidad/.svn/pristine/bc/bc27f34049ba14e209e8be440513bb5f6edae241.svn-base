package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 07/06/2017.
 */
public interface DepartamentoRepo extends BaseTableReferenceRepo<Departamento, UUID> {

    /**
     * Encuentra el departamento correspondiente al id solicitado.
     *
     * @param id del departamento que se solicita
     * @return El único registro que corresponde al id
     */
    @Override
    Departamento findById(UUID id);

    /**
     * Encuentra un departamento por el id
     *
     * @param id    id del departamento
     * @param fecha fecha de inicio de vigencia
     * @return El objeto {@link Departamento} correspondiente al id
     */
    @Override
    @Query("SELECT d FROM Departamento d WHERE d.id = ?1 AND d.inicioVigencia <= ?2 AND (d.finVigencia IS NULL OR d.finVigencia > ?2)")
    <String> Departamento findByIdAndFecha(String id, Date fecha);


    /**
     * Obtiene la lista de objetos
     *
     * @return lista de objetos
     */
    @Override
    @Query("SELECT d FROM Departamento d WHERE d.finVigencia IS NULL")
    List<Departamento> findAllByFinVigenciaNull();

    /**
     * Actualiza la fecha de cierre de los registros vigentes
     *
     * @param finVigencia fecha de cierre de los registros
     */
    @Override
    @Modifying
    @Transactional
    @Query("UPDATE Departamento SET finVigencia=?1 WHERE finVigencia IS NULL")
    void setFinVigenciaByVigentes(Date finVigencia);

    /**
     * Retorna la version actual de los registros
     */
    @Override
    @Query("SELECT MAX(version) FROM Departamento")
    Long getCurrentVersion();
}

