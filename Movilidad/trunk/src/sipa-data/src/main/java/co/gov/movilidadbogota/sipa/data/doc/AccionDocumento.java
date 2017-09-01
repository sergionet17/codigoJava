package co.gov.movilidadbogota.sipa.data.doc;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class AccionDocumento {

    @Id
    Long id;
    @NotNull
    Date fecha;

    @ManyToOne
    @NotNull
    Usuario usuario;

    @ManyToOne
    @NotNull
    TipoAccion tipoAccion;

    String observaciones;

}
