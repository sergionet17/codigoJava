package co.gov.movilidadbogota.sipa.data.gen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Clase que representa la categoria de los parametros configurable del sistema
 *
 * @author lorenzo.pinango
 */
@Entity
public class CategoriaParametro {

    @Id
    private
    String clave;

    @NotNull
    private String nombre;

    private String descripcion;

    @ManyToOne
    @JsonIgnore
    private
    CategoriaParametro parent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORIA_CLAVE")
    private
    List<Parametro> parametros;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_CLAVE")
    private
    List<CategoriaParametro> categorias;

    public CategoriaParametro() {
    }

    public CategoriaParametro(String clave, String nombre, String descripcion) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaParametro getParent() {
        return parent;
    }

    public void setParent(CategoriaParametro parent) {
        this.parent = parent;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public List<CategoriaParametro> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaParametro> categorias) {
        this.categorias = categorias;
    }
}
