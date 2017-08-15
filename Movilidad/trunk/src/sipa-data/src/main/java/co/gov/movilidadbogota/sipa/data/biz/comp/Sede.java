package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Sede donde se dictan los cursos pedagogicos
 *
 * @author paola.charry
 */
@Entity
public class Sede {

    @Id
    private
    UUID id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sede")
    private
    List<Salon> salones;

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

    public List<Salon> getSalones() {
        return salones;
    }

    public void setSalones(List<Salon> salones) {
        this.salones = salones;
    }
}
