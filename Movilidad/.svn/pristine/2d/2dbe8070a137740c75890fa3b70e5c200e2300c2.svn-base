package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by acpreda on 4/8/17.
 */
@Entity
public class AudienciaComparendo {

    @Id
    private
    UUID id;

    @ManyToOne
    @NotNull
    private
    Comparendo comparendo;

    @ManyToOne
    @NotNull
    private
    Usuario abogado;

    @NotNull
    private
    Date fecha;

    @ManyToOne
    @NotNull
    private
    TipoAudienciaComparendo tipo;

    @ManyToOne
    private
    Documento documento;

    @ManyToOne
    private
    FalloAudienciaComparendo fallo;

    @ManyToOne
    private
    Persona apoderado;

    @ManyToOne
    private
    Persona representanteLegal;

    @ManyToOne
    private
    Persona deudorSolidario;

    private
    Integer porcentajeDeudorSolidario;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Usuario getAbogado() {
        return abogado;
    }

    public void setAbogado(Usuario abogado) {
        this.abogado = abogado;
    }

    public FalloAudienciaComparendo getFallo() {
        return fallo;
    }

    public void setFallo(FalloAudienciaComparendo fallo) {
        this.fallo = fallo;
    }

    public TipoAudienciaComparendo getTipo() {
        return tipo;
    }

    public void setTipo(TipoAudienciaComparendo tipo) {
        this.tipo = tipo;
    }

    public Persona getApoderado() {
        return apoderado;
    }

    public void setApoderado(Persona apoderado) {
        this.apoderado = apoderado;
    }

    public Persona getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(Persona representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    @Override
    public String toString() {
        return "AudienciaComparendo{" +
                "id=" + id +
                ", comparendo=" + comparendo +
                ", fecha=" + fecha +
                ", documento=" + documento +
                ", abogado=" + abogado +
                ", fallo=" + fallo +
                ", tipo=" + tipo +
                ", apoderado=" + apoderado +
                ", representanteLegal=" + representanteLegal +
                '}';
    }

    public Persona getDeudorSolidario() {
        return deudorSolidario;
    }

    public void setDeudorSolidario(Persona deudorSolidario) {
        this.deudorSolidario = deudorSolidario;
    }

    public Integer getPorcentajeDeudorSolidario() {
        return porcentajeDeudorSolidario;
    }

    public void setPorcentajeDeudorSolidario(Integer porcentajeDeudorSolidario) {
        this.porcentajeDeudorSolidario = porcentajeDeudorSolidario;
    }
}
