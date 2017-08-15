package co.gov.movilidadbogota.sipa.data.biz.num;

import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 22/06/2017.
 */
@Transactional
public interface AnulacionNumeracionRepo extends JpaRepository<AnulacionNumeracion, UUID> {

  /*  @Query("select an from  NumeroComparendo nc ,AnulacionNumeracion an" +
            " where nc.numero in (:numerosComparendos) and an.tipoRangoNumeracion.id = :idTipoRangoNumeracion ")
    List<AnulacionNumeracion> findAnyNumerosComparendoAndTipoRangoNumeracion();*/


    List<AnulacionNumeracion> findByNumerosInAndTipoRangoNumeracion(List<NumeroComparendo> numeros, TipoRangoNumeracion tipoRangoNumeracion);

}
