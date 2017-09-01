package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.UUID;

/**
 * Repositorio para la clase {@link ConstanciaEjecutoriaAutomatica}
 *
 * @author or maria.rodriguez
 */

public interface ConstanciaEjecutoriaAutomaticaRepo extends JpaRepository<ConstanciaEjecutoriaAutomatica, UUID> {
    /**
     * Encuentra una constancia ejecutoria automática
     *
     * @param id Identificador del comparendo
     * @return El objeto {@link ConstanciaEjecutoriaAutomatica}.
     */
    ConstanciaEjecutoriaAutomatica findByComparendoId(UUID id);

    /**
     * Permite identificar los infractores reincidentes (almenos 2 fallos en menos de 6 meses)
     *
     * @param infractorId          el identificador del infractor
     * @param sixMonthsBeforeToday fecha seis meses atras al día de hoy
     * @param today                la fecha actual
     * @return El lsiatdo de constancias generadas en los ultimos 6 meses a un mismo infractor.
     */
    @Query(value = "SELECT COUNT(raf.id) FROM ResolucionAutomaticaDeFallo raf WHERE raf.comparendo.infractor.id =?1 AND raf.comparendo.fechaImposicion BETWEEN ?2 AND ?3")
    Integer findByInfractorIdAndFechaImposicionBetween(UUID infractorId, Date sixMonthsBeforeToday, Date today);
}
