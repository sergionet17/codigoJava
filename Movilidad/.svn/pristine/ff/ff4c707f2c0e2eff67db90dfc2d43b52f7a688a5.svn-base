package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link Abogado}
 * Created by paola.charry on 11/07/2017.
 */
public interface AbogadoRepo extends JpaRepository<Abogado, UUID> {
    /**
     * Consulta un {@link Instructor} por medio del identificador de una persona
     *
     * @param id Identificador de una persona
     * @return Devuelve un objeto {@link Abogado}
     */

    Abogado findByPersonaId(UUID id);
}
