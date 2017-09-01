package co.gov.movilidadbogota.sipa.data.fin;

import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by acpreda on 7/13/17.
 */
@Entity
public class Pago {

    @Id
    private
    UUID id;

    @ManyToOne
    private
    ArchivoPagos archivoPagos;

    @CsvBindByPosition(position = 1)
    private Date fecha;

    @CsvBindByPosition(position = 2)
    private BigDecimal valor;

    @CsvBindByPosition(position = 3)
    private String referencia;

    @ManyToOne
    private
    EstadoPago estadoPago;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArchivoPagos getArchivoPagos() {
        return archivoPagos;
    }

    public void setArchivoPagos(ArchivoPagos archivoPagos) {
        this.archivoPagos = archivoPagos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
