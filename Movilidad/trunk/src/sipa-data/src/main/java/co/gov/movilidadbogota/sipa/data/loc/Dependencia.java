package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Dependencia {

    @Id
    UUID id;

    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_DEPENDENCIA)
    String nombre;

    @ManyToOne
    private
    Dependencia dependencia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPENDENCIA_ID")
    private
    List<Dependencia> dependencias;

    @OneToOne
    private Usuario responsable;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public List<Dependencia> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Dependencia> dependencias) {
        this.dependencias = dependencias;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
}
