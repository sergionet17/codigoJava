package co.gov.movilidadbogota.sipa.data.fin;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.doc.Carpeta;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class SolicitudDevolucion {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    Usuario radicador;
    @ManyToOne
    @NotNull
    Carpeta carpeta;
    @ManyToOne
    @NotNull
    Persona beneficiario;
    @NotNull
    Date fecha;
    @ManyToOne
    @NotNull
    EstadoDevolucion estado;
    @ManyToOne
    @NotNull
    Documento oficio;

}
