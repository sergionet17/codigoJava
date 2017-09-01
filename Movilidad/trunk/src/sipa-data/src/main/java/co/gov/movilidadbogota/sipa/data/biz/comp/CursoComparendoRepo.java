package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link CursoComparendo}
 * Created by paola.charry on 26/05/2017.
 */
@Transactional
public interface CursoComparendoRepo extends JpaRepository<CursoComparendo, Integer> {

    /**
     * consulta si tiene un curso asociado a un comparendo
     *
     * @param id Identificador del curso
     * @return El objeto de la clase {@link CursoComparendo}
     */
    @Query(value = "SELECT count(id) FROM CursoComparendo where curso.id = ?1")
    Integer countByCursoId(UUID id);

    /**
     * consulta si tiene un comparendo asociado a un curso
     *
     * @param id Identificador del comparendo
     * @return El objeto de la clase {@link CursoComparendo}
     */
    @Query(value = "SELECT count(id) FROM CursoComparendo where estado=1 and  comparendo.id = ?1")
    Integer countByComparendoId(UUID id);

    /**
     * Consulta todos los comparendos registrados a un curso
     *
     * @param id Identificador del curso
     * @return Una coleccion de la clase {@link CursoComparendo}
     */
    @Query(value = "SELECT c.comparendo FROM CursoComparendo c where c.curso.id = ?1")
    List<Comparendo> findByCursoId(UUID id);

    /**
     * Consulta todos los {@link CursoComparendo} de un curso
     *
     * @param id Identificador del curso
     * @return Una coleccion de la clase {@link CursoComparendo}
     */
    @Query(value = "SELECT c FROM CursoComparendo c where c.comparendo.id = ?1")
    CursoComparendo findBycomaprendoId(UUID id);

    /**
     * Actualiza el estado del asistente en el curso pedagogico
     *
     * @param id          Identificador del comparendo
     * @param estado      Estado del asistente en el curso pedagogico
     * @param certificado Documento de certificacion de asistencia al curso
     */
    @Modifying
    @Query("update CursoComparendo c set imagen=?3, estado.id=?2 where c.comparendo.id = ?1")
    void setCursoComparendoByComparendoId(UUID id, Integer estado, Documento certificado);
}
