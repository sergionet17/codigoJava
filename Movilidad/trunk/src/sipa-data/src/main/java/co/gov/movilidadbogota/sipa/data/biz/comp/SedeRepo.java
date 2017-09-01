package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link Sede}
 * Created by paola.charry on 31/05/2017.
 */
public interface SedeRepo extends JpaRepository<Sede, UUID> {

    /**
     * Encuentra una sede que corresponda exáctamente con el nombre solicitado
     *
     * @param nombre El nombre de la sede
     * @return El objeto de la clase {@link Sede} que corresponde al nombre
     * solicitado, o null si no se encuentra.
     */
    Sede findByNombre(String nombre);

}
