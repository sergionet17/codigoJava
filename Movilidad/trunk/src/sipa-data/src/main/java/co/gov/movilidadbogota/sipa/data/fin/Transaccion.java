package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Representa una transacción financiera como resultado del registro de un evento de negocio que tiene afectación
 * financiera.
 *
 * @author arturo.cruz
 */
@Entity
public class Transaccion {

    @Id
    private UUID id;
    @NotNull
    private Date fechaRegistro;

    @NotNull
    private String codigoEvento;
    private String descripcionEvento;
    @NotNull
    private Date fechaEvento;
    @NotNull
    private String tipoReferencia;
    @NotNull
    private UUID referencia;

    @NotNull
    private BigDecimal valor;
    @Transient
    private Map<String, Serializable> parametros;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaccion")
    private List<Entrada> entradas;

    @NotNull
    private String hash;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }

    public Entrada with(Cuenta cuenta) {
        Entrada e = new Entrada();
        e.setId(UUID.randomUUID());
        e.setCuenta(cuenta);
        e.setTransaccion(this);
        e.setFechaEvento(this.fechaEvento);
        e.setFechaRegistro(this.fechaRegistro);
        e.setReferencia(this.referencia);
        e.setDescripcion(this.descripcionEvento);
        if (entradas == null)
            entradas = new ArrayList<Entrada>();
        entradas.add(e);
        return e;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigDecimal balance() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Entrada x : entradas) {
            sum = sum.add(x.getValor());
        }
        return sum;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Map<String, Serializable> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Serializable> parametros) {
        this.parametros = parametros;
    }

    public String getTipoReferencia() {
        return tipoReferencia;
    }

    public void setTipoReferencia(String tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    public UUID getReferencia() {
        return referencia;
    }

    public void setReferencia(UUID referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", fechaRegistro=" + fechaRegistro +
                ", codigoEvento='" + codigoEvento + '\'' +
                ", descripcionEvento='" + descripcionEvento + '\'' +
                ", fechaEvento=" + fechaEvento +
                ", tipoReferencia='" + tipoReferencia + '\'' +
                ", referencia=" + referencia +
                ", valor=" + valor +
                ", parametros=" + parametros +
                ", entradas=" + entradas +
                ", hash='" + hash + '\'' +
                '}';
    }

    /**
     * Verifica que la transacción tenga el tipo de referencia esperado
     *
     * @param expected el tipo de referencia esperado
     */
    public void checkTipoReferencia(String expected) {
        if (expected == null) {
            throw new IllegalArgumentException("Tipo de referencia no puede ser null");
        }
        if (expected.equals(tipoReferencia) == false) {
            throw new IllegalArgumentException(
                    String.format("Tipo de referencia no es válido: %s. Se esperaba %s",
                            tipoReferencia,
                            expected));
        }
    }

}
