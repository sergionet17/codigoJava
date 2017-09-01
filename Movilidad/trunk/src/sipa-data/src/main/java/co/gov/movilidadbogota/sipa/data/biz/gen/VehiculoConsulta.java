package co.gov.movilidadbogota.sipa.data.biz.gen;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Se encarga de realizar la consulta de vehiculos basado en criterios de búsqueda.
 */
@Service
public class VehiculoConsulta {

    private static final Logger LOG = LoggerFactory.getLogger(VehiculoConsulta.class);
    @Autowired
    private
    EntityManager em;

    /**
     * Se encarga de realizar la búsqueda de vehiculos de acuerdo a los parámetros modelados en
     * la clase {@link co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoForm}
     *
     * @param vehiculoForm clase que mapea los criterios de búsqueda.
     * @return El listado de los vechiculos que coiciden con los critérios de búsqueda solicitados o
     * una lista vacía en caso de no encontrar coincidencias.
     */
    @SuppressWarnings(value = "unchecked")
    public List<Vehiculo> findVehiculo(VehiculoForm vehiculoForm) {

        Session session = em.unwrap(Session.class);
        Criteria cr = session.createCriteria(Vehiculo.class);

        if (!vehiculoForm.getPlacaVehiculo().isEmpty()) {
            cr.add(Restrictions.eq("placaVehiculo", vehiculoForm.getPlacaVehiculo()));

        }
        if (!vehiculoForm.getTipoDocumento().isEmpty() && !vehiculoForm.getDocumentoIdentidad().isEmpty()) {

            cr.createAlias("propietario", "prop")
                    .add(Restrictions.like("prop.numeroDocumentoIdentidad", vehiculoForm.getDocumentoIdentidad()))
                    .createAlias("prop.tipoDocumentoIdentidad", "ti")
                    .add(Restrictions.eq("ti.sigla", vehiculoForm.getTipoDocumento()));

        }
        return cr.list();
    }
}

