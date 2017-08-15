package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link Comparendo}
 *
 * @author maria.rodriguez
 */

public interface ComparendoRepository extends JpaRepository<Comparendo, UUID> {

    /**
     * Realiza la búsqueda de un comparendo por el número del comparendo.
     *
     * @param numero de comparendo
     * @return Un objeto comparendo.
     */
    Comparendo findByNumeroConsecutivo(Integer numero);


    /**
     * Devuelve el número de comparendos asociados a un número de documento de identidad.
     *
     * @param id El id de la persona
     * @return Un número entero que represesta la cantidad de comparendos asociados a una persona.
     */
    Integer countByInfractorId(UUID id);

    /**
     * Devuelve el número de comparendos asociados a la placa de un vehículo.
     *
     * @param id de un vehículo
     * @return Un número entero que represesta la cantidad de comparendos asociados a un vehículo.
     */
    Integer countByVehiculoId(UUID id);

    /**
     * Obtiene los comparendos cuya fecha de imposición es anterior a la fecha
     * del parámetro que se encuentren en el estado especificado y que sean del
     * tipo especificado
     *
     * @param tipo   Tipo de comparendo
     * @param estado Estado del comparendo
     * @param fecha  Fecha de referencia
     * @return Lista de comparendos que cumplen con las especificaciones
     */
    List<Comparendo> findByTipoComparendoAndEstadoAndFechaImposicionBefore(
            TipoComparendo tipo,
            EstadoComparendo estado,
            Date fecha
    );

    List<Comparendo> findByNumeroIn(List<NumeroComparendo> numeroComparendos);

    /**
     * Realiza la búsqueda de los comparendos por placa de vehículo.
     *
     * @param id del vehículo.
     * @return La lista de comparendos por id del vehículo.
     */
    List<Comparendo> findByVehiculoId(UUID id);

    /**
     * Realiza la búsqueda de los comparendos por el id del infractor.
     *
     * @param id del infractor.
     * @return La lista de comparendos por id de persona
     */
    List<Comparendo> findByInfractorId(UUID id);
}


