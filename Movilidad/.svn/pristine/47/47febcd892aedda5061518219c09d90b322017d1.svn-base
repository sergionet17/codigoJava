package co.gov.movilidadbogota.sipa.data.biz.comp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Repositorio para la clase {@link Curso}
 * autor: paola.charry 25/05/2017
 */
@Transactional
public interface CursoPedagogicoRepo extends JpaRepository<Curso, UUID> {
    /**
     * Encuentra el curso por el nombre del Tema
     *
     * @param tema El nombre del tema del curso
     * @return El objeto de la clase {@link Curso} que corresponde al tema
     * solicitado, o null si no se encuentra.
     */
    Curso findByTema(String tema);

    /**
     * Encuentra el curso por el Id
     *
     * @param id El identificadordel curso
     * @return El objeto de la clase {@link Curso} que corresponde al identificador
     * solicitado, o null si no se encuentra.
     */
    Curso findById(UUID id);

    /**
     * Encuentra todos los cursos vigentes
     *
     * @return El objeto de la clase {@link Curso} a los cursos vigentes
     */
    List<Curso> findByFechaAfterAndEstadoIdOrFechaAndEstadoId(Date fecha, Integer estado, Date fech, Integer estad);

    /**
     * Encuentra todos los cursos vigentes  para el dia actual
     *
     * @return El objeto de la clase {@link Curso} a los cursos vigentes
     */
    List<Curso> findByFechaAndEstadoId(Date fecha, Integer estado);

    /**
     * Actualiza el estado del asistente en el curso pedagogico
     *
     * @param estado Estado del asistente en el curso pedagogico
     * @param id     Identificador del comparendo
     */
    @Modifying
    @Query("update Curso  set estado.id=?2 where id=?1")
    void updateCursobyId(UUID id, Integer estado);

    /**
     * @param salon
     */
    @Query(value = "SELECT c FROM Curso c where salon.id=?1 and  fecha=?2 ")
    List<Curso> findBySalonAndFecha(UUID salon, Date fecha);


}
