package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio de la clase {@link Entrada}
 *
 * @author arturo.cruz
 */
public interface EntradaRepo extends JpaRepository<Entrada, UUID> {
    /**
     * Encuentra las entradas de una cuenta que pertenecen a una referencia
     *
     * @param cuenta     Cuenta en la que se realiza la búsqueda
     * @param referencia Referencia a la que pertenecen las entradas
     * @return El listado de entradas que coinciden con los criterios de búsqueda
     */
    List<Entrada> findByCuentaAndReferencia(Cuenta cuenta, UUID referencia);

    List<Entrada> findByReferencia(UUID referencia, Sort sort);
}
