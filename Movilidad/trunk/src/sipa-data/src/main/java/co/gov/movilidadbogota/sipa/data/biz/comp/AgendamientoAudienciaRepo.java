package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la clase {@link AgendamientoAudiencia}
 * Created by paola.charry on 29/06/2017.
 */
public interface AgendamientoAudienciaRepo extends JpaRepository<AgendamientoAudiencia, Integer> {

    /**
     * Retorna toas las parametrizaciones de audiencia que se encuentran vigente
     *
     * @return colección del objecto {@link AgendamientoAudiencia}
     */
    List<AgendamientoAudiencia> findByFechaCierreIsNull();

}

