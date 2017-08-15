package co.gov.movilidadbogota.sipa.data.id;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el tipo de documento de identidad
 *
 * @author arturo.cruz
 */
@Entity
public class TipoDocumento {

    public static final TipoDocumento CC = new TipoDocumento(1, "Cédula de Ciudadanía", "CC");
    public static final TipoDocumento NIT = new TipoDocumento(2, "Número de Identificación Tributario", "NIT");
    public static final TipoDocumento NUIP = new TipoDocumento(3, "Número Único de Identificación Personal", "NUIP");
    public static final TipoDocumento CE = new TipoDocumento(4, "Cédula de Extranjería", "CE");
    public static final TipoDocumento TI = new TipoDocumento(5, "Tarjeta de Identidad", "TI");

    public static final List<TipoDocumento> FULL_SET = Arrays.asList(
            TipoDocumento.CC,
            TipoDocumento.NIT,
            TipoDocumento.NUIP,
            TipoDocumento.CE,
            TipoDocumento.TI
    );

    @Id
    private
    Integer id;

    @NotNull
    private
    String nombre;

    @NotNull
    private
    String sigla;

    public TipoDocumento(Integer id, String nombre, String sigla) {
        this.id = id;
        this.nombre = nombre;
        this.sigla = sigla;
    }

    public TipoDocumento() {
    }

    public TipoDocumento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TipoDocumento [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (nombre != null) {
            builder.append("nombre=");
            builder.append(nombre);
            builder.append(", ");
        }
        if (sigla != null) {
            builder.append("sigla=");
            builder.append(sigla);
        }
        builder.append("]");
        return builder.toString();
    }
}
