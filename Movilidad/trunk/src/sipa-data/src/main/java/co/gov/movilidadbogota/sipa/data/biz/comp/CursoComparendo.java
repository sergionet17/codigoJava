package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Relación entre un comparendo y el curso
 *
 * @author paola.charry
 */
@Entity
public class CursoComparendo {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private
    Integer id;

    @ManyToOne
    private
    Comparendo comparendo;

    @ManyToOne
    private
    Curso curso;

    @NotNull
    private
    Date fechaRegistro;

    @ManyToOne
    @NotNull
    private
    EstadoAsistenciaCurso estado;

    @ManyToOne
    Documento imagen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public EstadoAsistenciaCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistenciaCurso estado) {
        this.estado = estado;
    }

    public Documento getImagen() {
        return imagen;
    }

    public void setImagen(Documento imagen) {
        this.imagen = imagen;
    }

}
