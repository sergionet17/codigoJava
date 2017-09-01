package co.gov.movilidadbogota.sipa.data.persona;

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
 * Clase que se encarga de realizar la consulta de personas bajo unos criterios.
 * @author maria.rodriguez
 */
@Service
public class PersonaConsulta {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaConsulta.class);

    @Autowired
    private EntityManager em;

    /**
     * Se encarga de realizar la búsqueda de personas de acuerdo a los parámetros modelados en
     * la clase {@link PersonaForm}
     *
     * @param personaForm Clase que mapea los criterios de búsqueda de persona.
     * @return El listado de las personas que coiciden con los critérios de búsqueda solicitados o
     * una lista vacía en caso de no encontrar coincidencias.
     */
    @SuppressWarnings(value = "unchecked")
    public List<Persona> findPersona(PersonaForm personaForm) {

        Session session = em.unwrap(Session.class);

        Criteria cr = session.createCriteria(Persona.class);

        String[] splited = personaForm.getNombre().split("\\s+");

        if (!personaForm.getNombre().isEmpty()) {

            cr.add(Restrictions.like("primerNombre", personaForm.getNombre()));
        }
        if (!personaForm.getTipoDocumento().isEmpty() && !personaForm.getDocumentoIdentidad().isEmpty()) {

            cr.createAlias("tipoDocumentoIdentidad", "ti")
                    .add(Restrictions.like("ti.sigla", personaForm.getTipoDocumento()));

            cr.add(Restrictions.like("numeroDocumentoIdentidad", personaForm.getDocumentoIdentidad()));
        }

        return cr.list();

    }


}
