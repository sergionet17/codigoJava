package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Diego Gomez on 1/06/2017.
 */
@Entity
public class CategoriaLicencia {

    public static final CategoriaLicencia A1 = new CategoriaLicencia(1, "A1", "A1");
    public static final CategoriaLicencia A2 = new CategoriaLicencia(2, "A2", "A2");
    public static final CategoriaLicencia B1 = new CategoriaLicencia(3, "B1", "B1");
    public static final CategoriaLicencia B2 = new CategoriaLicencia(4, "B2", "B2");
    public static final CategoriaLicencia B3 = new CategoriaLicencia(5, "B3", "B3");
    public static final CategoriaLicencia C1 = new CategoriaLicencia(6, "C1", "C1");
    public static final CategoriaLicencia C2 = new CategoriaLicencia(7, "C2", "C2");
    public static final CategoriaLicencia C3 = new CategoriaLicencia(8, "C3", "C3");
    public static final Collection<CategoriaLicencia> FULL_SET = Arrays.asList(
            A1, A2, B1, B2, B3, C1, C2, C3
    );

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    Integer id;

    @NotNull
    String nombre;

    @NotNull
    String descripcion;


    public CategoriaLicencia(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public CategoriaLicencia() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
