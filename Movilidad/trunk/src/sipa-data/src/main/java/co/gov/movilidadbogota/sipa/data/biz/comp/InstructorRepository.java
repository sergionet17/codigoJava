package co.gov.movilidadbogota.sipa.data.biz.comp;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link Instructor}
 * Created by paola.charry on 31/05/2017.
 */
public interface InstructorRepository extends JpaRepository<Instructor, UUID> {

    /**
     * Consulta un {@link Instructor} por medio del identificador de una persona
     *
     * @param id Identificador de una persona
     * @return Devuelve un objeto {@link Instructor}
     */

    Instructor findByPersonaId(UUID id);
}
