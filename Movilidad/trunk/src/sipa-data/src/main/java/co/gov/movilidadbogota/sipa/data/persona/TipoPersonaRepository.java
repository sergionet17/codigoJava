package co.gov.movilidadbogota.sipa.data.persona;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by maria on 6/2/17.
 */
public interface TipoPersonaRepository extends JpaRepository<TipoPersona, Integer> {

    TipoPersona findById(Integer id);
}
