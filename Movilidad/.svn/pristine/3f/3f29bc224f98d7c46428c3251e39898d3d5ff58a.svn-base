package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Entrada de una cuenta contable
 *
 * @author arturo.cruz
 */
@Entity
public class Entrada {

    private static final Boolean TIPO_MOVIMIENTO_DEBITO = Boolean.TRUE;
    private static final Boolean TIPO_MOVIMIENTO_CREDITO = Boolean.FALSE;

    @Id
    private UUID id;
    @ManyToOne
    @NotNull
    private Transaccion transaccion;
    @NotNull
    private Date fechaEvento;
    @NotNull
    private Date fechaRegistro;
    @ManyToOne
    @NotNull
    private Cuenta cuenta;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Boolean tipoMovimiento;
    @NotNull
    private String descripcion;
    private UUID referencia;
    @NotNull
    private String hash;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UUID getReferencia() {
        return referencia;
    }

    public void setReferencia(UUID referencia) {
        this.referencia = referencia;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void debito(BigDecimal valor) {
        this.valor = valor;
        this.tipoMovimiento = TIPO_MOVIMIENTO_DEBITO;
    }

    public void credito(BigDecimal valor) {
        this.valor = valor.negate();
        this.tipoMovimiento = TIPO_MOVIMIENTO_CREDITO;
    }

    public Transaccion and() {
        return transaccion;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "id=" + id +
                ", fechaEvento=" + fechaEvento +
                ", fechaRegistro=" + fechaRegistro +
                ", cuenta=" + cuenta +
                ", tipoMovimiento=" + tipoMovimiento +
                ", valor=" + valor +
                ", descripcion='" + descripcion + '\'' +
                ", referencia=" + referencia +
                ", hash='" + hash + '\'' +
                '}';
    }

    public Boolean getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Boolean tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
