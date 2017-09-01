package co.gov.movilidadbogota.sipa.trans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio base para las tablas de referencia
 */
@NoRepositoryBean
public interface BaseTableReferenceRepo<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * Encuentra el municipio correspondiente al id solicitado.
     *
     * @param id del municipio que se solicita
     * @return El único registro que corresponde al id
     */
    T findById(
            UUID id);

    /**
     * Encuentra un objecto por el id
     *
     * @param id    id del objecto
     * @param fecha fecha de inicio de vigencia
     * @return El objeto {@link Object} correspondiente al id
     */
    <X> T findByIdAndFecha(X id, Date fecha);

    /**
     * Obtiene la lista de objetos
     *
     * @return lista de objetos
     */
    List<T> findAllByFinVigenciaNull();

    /**
     * Actualiza la fecha de cierre de los registros vigentes
     *
     * @param finVigencia fecha de cierre de los registros
     */
    void setFinVigenciaByVigentes(Date finVigencia);

    /**
     * Retorna la version actual de los registros
     */
    Long getCurrentVersion();


}
