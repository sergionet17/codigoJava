package co.gov.movilidadbogota.sipa.data.biz.coa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * Repositorio para la clase {@link TituloEjecutivo}
 *
 * @author lorenzo.pinango
 */
public interface TituloEjecutivoRepo extends JpaRepository<TituloEjecutivo, UUID> {

    /**
     * consulta cantidad de titulos ejecutivos de un comparendo
     *
     * @param id del comparendo
     * @return Cantidad de registros}
     */
    @Query("SELECT COUNT(t.id) FROM TituloEjecutivoReferencia tr JOIN tr.tituloEjecutivo t " +
            "WHERE tr.referencia = ?1 AND t.fuenteTituloEjecutivo.id = 1")
    Integer countByComparendoId(UUID id);
}
