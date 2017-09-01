package co.gov.movilidadbogota.sipa.data.gen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Clase que representa un parametro configurable del sistema
 *
 * @author lorenzo.pinango
 */
@Entity
public class Parametro {

    public static final String PORCENTAJE_DESCUENTO_25 = "PORCENTAJE_DESCUENTO_25";
    public static final String PORCENTAJE_DESCUENTO_50 = "PORCENTAJE_DESCUENTO_50";
    public static final String PORCENTAJE_INTERES = "PORCENTAJE_INTERES";
    public static final String DIAS_DESCUENTO_25_LAPIZ_OPTICO = "DIAS_DESCUENTO_25_LAPIZ_OPTICO";
    public static final String DIAS_DESCUENTO_50_LAPIZ_OPTICO = "DIAS_DESCUENTO_50_LAPIZ_OPTICO";
    public static final String DIAS_DESCUENTO_25_ELECTRONICO = "DIAS_DESCUENTO_25_ELECTRONICO";
    public static final String DIAS_DESCUENTO_50_ELECTRONICO = "DIAS_DESCUENTO_50_ELECTRONICO";
    public static final String MAXIMO_DIAS_SIN_INTERES = "MAXIMO_DIAS_SIN_INTERES";
    public static final String LOGITUD_MINIMA_PASSWORD = "LOGITUD_MINIMA_PASSWORD";
    public static final String CANTIDAD_MINIMA_MAYUSCULAS_PASSWORD = "CANTIDAD_MINIMA_MAYUSCULAS_PASSWORD";
    public static final String CANTIDAD_MINIMA_MINUSCULAS_PASSWORD = "CANTIDAD_MINIMA_MINUSCULAS_PASSWORD";
    public static final String CANTIDAD_MINIMA_NUMEROS_PASSWORD = "CANTIDAD_MINIMA_NUMEROS_PASSWORD";
    public static final String CANTIDAD_MINIMA_ESPECIALES_PASSWORD = "CANTIDAD_MINIMA_ESPECIALES_PASSWORD";
    public static final String CANTIDAD_PASSWORDS_ANTERIOR = "CANTIDAD_PASSWORDS_ANTERIOR";
    public static final String CANTIDAD_DIAS_VENCIMIENTO_PASSWORD = "CANTIDAD_DIAS_VENCIMIENTO_PASSWORD";
    public static final String CANTIDAD_DIAS_VENCIMIENTO_IMPOSICION_COMPARENDO = "CANTIDAD_DIAS_VENCIMIENTO_IMPOSICION_COMPARENDO";
    public static final String DIAS_MAXIMO_AUDIENCIA = "DIAS_MAXIMO_AUDIENCIA";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION =
            "PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA =
            "PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL =
            "PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE =
            "PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION =
            "PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA =
            "PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL =
            "PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL";
    public static final String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE =
            "PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE";
    public static final String MAX_HORAS_PERMITIDAS_CURSO_PEDAGOGICO =
            "MAX_HORAS_PERMITIDAS_CURSO_PEDAGOGICO";
    public static final String SMMLV = "SMMLV";


    @Id
    private
    String clave;

    @NotNull
    private String nombre;

    private String descripcion;

    @ManyToOne
    @JsonIgnore
    private
    CategoriaParametro categoria;

    @NotNull
    private String tipo;

    private String editor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARAMETRO_CLAVE")
    private
    List<ValorParametro> valores;

    public Parametro() {
    }

    public Parametro(String clave, String descripcion, String valor, CategoriaParametro categoria) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.descripcion = nombre;
        //this.valor = valor;
        this.categoria = categoria;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public CategoriaParametro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaParametro categoria) {
        this.categoria = categoria;
    }

    public List<ValorParametro> getValores() {
        return valores;
    }

    public void setValores(List<ValorParametro> valores) {
        this.valores = valores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

}
