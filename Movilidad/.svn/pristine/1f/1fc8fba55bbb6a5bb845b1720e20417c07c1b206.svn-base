package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.ctrl.*;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
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
 * Created by Marcel on 2/06/2017.
 */

@Service
public class TareasConsulta {

    private static final Logger LOG = LoggerFactory.getLogger(TareasConsulta.class);
    @Autowired
    EntityManager em;

    /**
     * Se encarga de realizar la búsqueda de personas de acuerdo a los parámetros modelados en
     * la clase {@link InconsistenciasForm}
     *
     * @param inconsistenciasForm
     * @return El listado de los comparendos con inconsistencias que coiciden con los critérios de búsqueda solicitados o
     * una lista vacía en caso de no encontrar coincidencias.
     */
    @SuppressWarnings("unchecked")
    public List<FormatoComparendo> findFormatoComparendo(InconsistenciasForm inconsistenciasForm) {

        Session session = em.unwrap(Session.class);
        Criteria cr = session.createCriteria(FormatoComparendo.class);
        if (!inconsistenciasForm.getNumero().isEmpty()) {

            cr.add(Restrictions.ilike("numero", inconsistenciasForm.getNumero()));
        }
        if (!inconsistenciasForm.getNumeroIdentificacionInfractor().isEmpty()) {

            cr.add(Restrictions.ilike("numeroIdentificacionInfractor", inconsistenciasForm.getNumeroIdentificacionInfractor()));
        }
        if (!inconsistenciasForm.getCodigoInfraccion().isEmpty()) {

            cr.add(Restrictions.ilike("codigoInfraccion", inconsistenciasForm.getCodigoInfraccion()));
        }

        return cr.list();
    }
}
