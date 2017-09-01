package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

/**
 * Representa un curso pedagógico al que se pueden inscribir asistentes
 *
 * @author paola.charry
 */
@Entity
public class Curso {

    @Id
    private
    UUID id;

    @NotNull
    @Size(min = 1, max = 100)
    private String tema;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fecha;

    @NotNull
    private String horaInicial;

    @NotNull
    private String horaFinal;

    @NotNull(message = "El campo no debe ser vacío")
    @Max(200)
    private Integer maximoAsistentes;

    @ManyToOne
    private
    EstadoCurso estado;

    private
    Date fechaRegistro;

    @ManyToOne
    private
    Instructor instructor;

    @ManyToOne
    @NotNull(message = "El campo no debe ser vacío")
    private
    Salon salon;

    public Curso() {
        // Se asigna la fecha actual para poder usar la clase como receptor de datos de formulario
        fechaRegistro = new Date();
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", tema='" + tema + '\'' +
                ", fecha='" + fecha + '\'' +
                ", horaInicial=" + horaInicial +
                ", horaFinal=" + horaFinal +
                ", maximoAsistentes=" + maximoAsistentes +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                ", instructor=" + instructor +
                ", salon=" + salon +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getMaximoAsistentes() {
        return maximoAsistentes;
    }

    public void setMaximoAsistentes(Integer maximoAsistentes) {
        this.maximoAsistentes = maximoAsistentes;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        this.estado = estado;
    }
}
