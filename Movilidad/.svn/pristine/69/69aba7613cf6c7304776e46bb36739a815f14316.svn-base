package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Componente de acceso a datos para la entidad {@link Infraccion}
 *
 * @author marcel.bohorquez
 */
public interface InfraccionRepo extends JpaRepository<Infraccion, Integer> {
    /**
     * Encuentra la infracción que corresponde al código solicitado, teniendo
     * en cuenta la vigencia del registro. Debido a las limitaciones de búsqueda
     * la fecha1 y fecha2 deben ser iguales y por lo general es ela fecha de
     * imposición del comparendo.
     *
     * @param codigo de la infracción que se solicita
     * @param fecha1 fecha que debe estar dentro del rango de vigencia de la infracción
     * @param fecha2 igual a la fecha 1 <b>SIEMPRE</b>
     * @return El único registro que corresponde al código y está vigente a la fecha solicitada
     */
    Infraccion findByCodigoAndInicioVigenciaBeforeAndFinVigenciaAfter(
            String codigo,
            Date fecha1,
            Date fecha2);

    /**
     * Encuentra la infraccion de transito correspondiente al código solicitado.
     *
     * @param codigo de la infracción que se solicita
     * @return El único registro que corresponde al código
     */
    Infraccion findByCodigo(
            String codigo);
}
