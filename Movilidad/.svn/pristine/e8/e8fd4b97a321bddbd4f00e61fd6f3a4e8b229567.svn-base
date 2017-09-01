package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * Created by JohnOchoa on 10/08/2017.
 */

/**
 * Se llama a  la entidad TipoPruebaProcesoContravencional, para hacer relacion con la lista Tipo prueba
 */

@Entity
public class TipoProcesoContravencional {

    @Id
    private UUID id;

    private String otros;

    @ManyToOne
    private TipoPruebaProcesoContravencional tipoPruebaProcesoContravencional;

    @ManyToOne
    private Documento documento;


    public TipoProcesoContravencional() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public TipoPruebaProcesoContravencional getTipoPruebaProcesoContravencional() {
        return tipoPruebaProcesoContravencional;
    }

    public void setTipoPruebaProcesoContravencional(TipoPruebaProcesoContravencional tipoPruebaProcesoContravencional) {
        this.tipoPruebaProcesoContravencional = tipoPruebaProcesoContravencional;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}