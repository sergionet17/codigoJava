package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by JohnOchoa on 10/08/2017.
 */

/**
 * Se llama a  la entidad TipoPruebaProcesoContravencional, para hacer relacion con la lista Tipo prueba
 */

@Entity
public class TipoProcesoContravencional {

    @Id
    private Integer id;

    @ManyToOne
    private TipoPruebaProcesoContravencional tipoPruebaProcesoContravencional;

    @ManyToOne
    private Documento documento;


    public TipoProcesoContravencional() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
