package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la clase {@link Alarma}
 *
 *
 */
public interface AlarmaRepo extends JpaRepository<Alarma, String> {
}
