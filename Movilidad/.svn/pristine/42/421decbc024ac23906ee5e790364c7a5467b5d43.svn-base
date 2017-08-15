package co.gov.movilidadbogota.sipa.data.biz.seg;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class PruebaSegundaInstancia {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    TipoPruebaSegundaInstancia tipo;

    @ManyToOne
    @NotNull
    EstadoPruebaSegundaInstancia estado;

    @ManyToOne
    @NotNull
    Documento solicitud;

    @ManyToOne
    Documento respuesta;

    @ManyToOne
    @NotNull
    Persona tercero;

    @NotNull
    String descripcion;

}
