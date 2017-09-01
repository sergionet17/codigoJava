package co.gov.movilidadbogota.sipa.data.biz.gen;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Componente de acceso a datos para la entidad {@link AgenteTransito}
 * Created by paola.charry on 07/06/2017.
 */

public interface AgenteTransitoRepo extends JpaRepository<AgenteTransito, Integer> {

    /**
     * Encuentra el agente de transito correspondiente al código solicitado.
     *
     * @param placa de la infracción que se solicita
     * @return El único registro que corresponde al código
     */
    AgenteTransito findByPlaca(
            String placa);
}
