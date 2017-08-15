package co.gov.movilidadbogota.sipa.data.biz.comp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link FormatoComparendo}
 * autor: Marcel.Bohorquez 06/06/2017
 */

@Transactional
public interface FormatoComparendoRepository extends JpaRepository<FormatoComparendo, UUID> {
    /**
     * Encuentra el comparendo inconsistente por el numero
     *
     * @param numero El numero del comparendo inconsistente
     * @return El objeto de la clase {@link FormatoComparendo} que corresponde al numero de comparendo
     * solicitado, o null si no se encuentra.
     */

    FormatoComparendo findBynumero(String numero);


    List<FormatoComparendo> findByEstado(Integer estado);

}
