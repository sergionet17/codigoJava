package co.gov.movilidadbogota.sipa.data.biz.num;

import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link NumeroComparendo}
 */

@Transactional
public interface NumeroComparendoRepository extends JpaRepository<NumeroComparendo, UUID> {
    List<NumeroComparendo> findByValorIn(List<String> valores);

    List<NumeroComparendo> findByValorInAndRangoNumeracionTipoRangoNumeracion(List<String> valores, TipoRangoNumeracion tipoRangoNumeracion);

    /**
     * Obtiene un número de comparendo a partir del valor del mismo y el tipo de numeración
     * del rango que lo contiene
     *
     * @param valor Es el valor del número de comparendo con prefijo y ancho fijo. No confundir
     *              con el consecutivo.
     * @param tipo  Es el tipo del rango de numeración al que pertenece. Esto se requiere
     *              ya que es posible que haya dos tipos direfentes de numeración que produzcan
     *              el mismo valor.
     * @return El objeto que contiene el número y que ya se encuentra almacenado en la base de
     * datos. En caso que el número no se encuentre en la base de datos entonces devuelve null.
     */
    NumeroComparendo findByValorAndRangoNumeracionTipoRangoNumeracion(String valor, TipoRangoNumeracion tipo);
}
