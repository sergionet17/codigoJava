package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Actuacion {

    public static final Actuacion IMPOSICION_COMPARENDO = new Actuacion(1, "Imposición de comparendo");
    public static final Actuacion FALLO_PRIMERA_INSTANCIA = new Actuacion(2, "Fallo primera instancia");
    public static final Actuacion AUDIENCIA_ACEPTACION = new Actuacion(3, "Audiencia de aceptación");
    public static final Actuacion AUDIENCIA_IMPUGNACION = new Actuacion(4, "Audiencia de impugnación");
    public static final Actuacion ASOCIACION = new Actuacion(5, "Acociación de documento a expediente");
    public static final Actuacion REGISTRO_TITULO_EJECUTIVO = new Actuacion(6, "Registro titulo ejecutivo");
    public static final Actuacion VOLANTE_PAGO = new Actuacion(7, "Volante de Pago");

    public static final Collection<Actuacion> FULL_SET = Arrays.asList(
            IMPOSICION_COMPARENDO, FALLO_PRIMERA_INSTANCIA,
            AUDIENCIA_ACEPTACION, AUDIENCIA_IMPUGNACION,
            ASOCIACION, REGISTRO_TITULO_EJECUTIVO
    );

    @Id
    private
    Integer id;

    @NotNull
    private
    String nombre;

    public Actuacion() {
    }

    public Actuacion(Integer id, String nombre) {
        this.setId(id);
        this.setNombre(nombre);
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
}
