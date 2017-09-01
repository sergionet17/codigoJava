package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para la clase {@link Festivo}
 * Created by paola.charry on 30/06/2017.
 */
public interface FestivoRepo extends JpaRepository<Festivo, Date> {

    /**
     * Retorna una coleccion de {@link Date}
     *
     * @return
     */
    @Query(value = "SELECT c.fecha FROM Festivo c")
    List<Date> findAllById();

    /**
     * Retorna dia festivo
     *
     * @return
     */
    @Query(value = "SELECT c.fecha FROM Festivo c WHERE c.fecha = ?1")
    List<Date> findFestivo(Date dia);
}
