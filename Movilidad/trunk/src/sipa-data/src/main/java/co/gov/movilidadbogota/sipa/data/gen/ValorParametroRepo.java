package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.UUID;

/**
 * Repositorio para la clase {@link ValorParametro}
 *
 * @author lorenzo.pinango
 */
public interface ValorParametroRepo extends JpaRepository<ValorParametro, UUID> {

    /**
     * Encuentra un valor de parametro
     *
     * @param clave Clave del parametro
     * @return El objeto {@link ValorParametro} correspondiente a la clave del parametro
     */
    @Query("SELECT v FROM ValorParametro v WHERE v.parametro.clave = ?1 AND v.inicioVigencia <= ?2 AND (v.finVigencia IS NULL OR v.finVigencia > ?2)")
    ValorParametro findValorVigenteByClave(String clave, Date fecha);

    /**
     * Encuentra un valor de parametro
     *
     * @param clave Clave del parametro
     * @return El objeto {@link ValorParametro} correspondiente a la clave del parametro
     */
    @Query("SELECT v FROM ValorParametro v WHERE v.parametro.clave = ?1 AND v.finVigencia IS NULL")
    ValorParametro findValorVigente(String clave);

}
