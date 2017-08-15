package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link Salon}
 * Created by paola.charry on 31/05/2017.
 */
public interface SalonRepo extends JpaRepository<Salon, Integer> {

}
