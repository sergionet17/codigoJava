package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Componente de acceso a datos para la entidad {@link Inmovilizacion}
 *
 * @author marcel.bohorquez
 */

public interface InmovilizacionRepo extends JpaRepository<Inmovilizacion, UUID> {
    /**
     * Encuentra el número de inmovilizacion del comparendo para validar que exista en el sistema.
     *
     * @param numero Numero de la infracción que se solicita
     * @return El único registro que corresponde al numero de inmovilización
     */
    Inmovilizacion findByNumero(
            String numero);
}
