package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

/**
 * Repositorio para el manejo de la persistencia de {@link PlantillaTransaccion}
 *
 * @author arturo.cruz
 */
public interface PlantillaTransaccionRepo extends JpaRepository<PlantillaTransaccion, UUID> {

    /**
     * Encuentra la plantilla de transacción que corresponde al código de evento y que está vigente a la fecha que se
     * solicita. Nótese que las dos fechas deben ser iguales por motivos de limitación de la herramienta.
     *
     * @param codigoEvento Es el código del evento que se va a registrar
     * @param fecha1       Es la fecha de referencia en la cual debe estar vigente la plantilla
     * @param fecha2       Es igual a la fecha1
     * @return La plantilla correspondiente a los parámetros solicitados.
     */
    PlantillaTransaccion findByCodigoEventoAndInicioVigenciaBeforeAndFinVigenciaAfter(
            String codigoEvento,
            Date fecha1,
            Date fecha2);

}
