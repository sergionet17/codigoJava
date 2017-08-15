package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by maria on 5/4/17.
 */
@Entity
public class SecuestroBien {
    @Id
    Integer id;

    @ManyToOne
    Bien bien;


    @ManyToOne
    EstadoSecuestro estado;

    @ManyToOne
    Documento prueba;
}
