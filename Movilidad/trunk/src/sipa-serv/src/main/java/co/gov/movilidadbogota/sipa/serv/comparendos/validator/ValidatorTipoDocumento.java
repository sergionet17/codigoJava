package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 12/07/2017.
 */
@Component
public class ValidatorTipoDocumento {

    private static final String ERROR_TIPO_DOCUMENTO = "El tipo documeto debe ser obligatorio";
    private static final String ERROR_TIPO_DOCUMENTO_NO_EXISTE = "El tipo documeto ingresado no esiste en el sistema";
    @Autowired
    TipoDocumentoRepo tipoDocumentoRepo;
    private TipoDocumento tipoDocumento;
    private String tipoDocumentoSigla;
    private String nameField;

    public void validarTipoDocumento(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        if (tipoDocumentoSigla == null) {
            errors.rejectValue(nameField, "fmt.tipoDocumento.invalid", ERROR_TIPO_DOCUMENTO);
        } else {
            if (tipoDocumentoSigla.isEmpty()) {
                errors.rejectValue(nameField, "fmt.tipoDocumento.invalid", ERROR_TIPO_DOCUMENTO);
            } else {
                tipoDocumento = tipoDocumentoRepo.findBySigla(tipoDocumentoSigla);
                if (tipoDocumento == null) {
                    errors.rejectValue(nameField, "fmt.tipoVehiculo.doesntExist", ERROR_TIPO_DOCUMENTO_NO_EXISTE);
                }
            }
        }

    }


    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumentoSigla() {
        return tipoDocumentoSigla;
    }

    public void setTipoDocumentoSigla(String tipoDocumentoSigla) {
        this.tipoDocumentoSigla = tipoDocumentoSigla;
    }


    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }


}
