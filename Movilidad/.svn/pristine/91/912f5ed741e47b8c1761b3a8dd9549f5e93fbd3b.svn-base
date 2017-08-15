package co.gov.movilidadbogota.sipa.data.biz.num;

import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para {@link RangoNumeracion}
 *
 * @author arturo.cruz
 */
@Transactional
public interface RangoNumeracionRepo extends JpaRepository<RangoNumeracion, UUID> {


    /**
     * Metodo que verifica si un numero pertenece a cualquier rango en la base de datos
     *
     * @param inicio
     * @param fin
     * @return
     */
    //List<RangoNumeracion> findByInicioLessThanAndFinGreaterThanAndTipoRangoNumeracion(Integer inicio, Integer fin, TipoRangoNumeracion tipoRangoNumeracion);

    List<RangoNumeracion> findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion(Integer inicio, Integer inicio1, TipoRangoNumeracion tipoRangoNumeracion);

    /**
     * Metodo que consulta la lista de rango de numeracion para Contravenciones
     *
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM RANGO_NUMERACION ranNum "
            + "WHERE ranNum.TIPO_RANGO_NUMERACION_ID=1"
            + "ORDER BY  ranNum.FECHA_RESPUESTA DESC ")
    List<RangoNumeracion> findByRangoNumeracionOrdenadoContravenciones();


    /**
     * Metodo que consulta la lista de rango de numeracion para Infracción de Transporte público
     *
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM RANGO_NUMERACION ranNum "
            + "WHERE ranNum.TIPO_RANGO_NUMERACION_ID=2"
            + "ORDER BY  ranNum.FECHA_RESPUESTA DESC ")
    List<RangoNumeracion> findByRangoNumeracionOrdenadoTransportePublico();


}
