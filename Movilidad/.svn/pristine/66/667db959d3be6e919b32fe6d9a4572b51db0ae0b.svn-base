package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Permite la consulta de comparendos por medio de criterios de búsqueda.
 * @author maria.rodriguez
 */
@Service
public class ComparendoConsulta {

    private static final Logger LOG = LoggerFactory.getLogger(ComparendoConsulta.class);
    @Autowired
    EntityManager em;

    /**
     * Se encarga de realizar la búsqueda de comparendos de acuerdo a los parámetros modelados en
     * la clase {@link ComparendoForm}
     *
     * @param comparendoForm
     * @return El listado de los comaprendos que coiciden con los critérios de búsqueda solicitados o
     * una lista vacía en caso de no encontrar coincidencias.
     */
    @SuppressWarnings(value = "unchecked")
    public List<Comparendo> findComparendo(ComparendoForm comparendoForm) {
        Session session = em.unwrap(Session.class);
        Criteria cr = session.createCriteria(Comparendo.class);

        if (StringUtils.isNotBlank(comparendoForm.getNumeroComparendo())) {
            cr.createAlias("numero", "n")
                    .add(Restrictions.like("n.valor", comparendoForm.getNumeroComparendo(), MatchMode.END));
        }
        if (StringUtils.isNotBlank(comparendoForm.getPlaca())) {
            cr.createAlias("vehiculo", "v")
                    .add(Restrictions.like("v.placaVehiculo", comparendoForm.getPlaca()));
        }
        if (comparendoForm.getTipoDocumento() != null
                && comparendoForm.getTipoDocumento() != null
                && StringUtils.isNotBlank(comparendoForm.getNumeroIdentificacion())) {
            cr.createAlias("infractor", "i")
                    .add(Restrictions.eq("i.tipoDocumentoIdentidad.id", comparendoForm.getTipoDocumento()))
                    .add(Restrictions.eq("i.numeroDocumentoIdentidad", comparendoForm.getNumeroIdentificacion()));
        }
        return cr.list();
    }
}
