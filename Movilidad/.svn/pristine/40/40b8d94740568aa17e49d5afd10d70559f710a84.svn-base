package co.gov.movilidadbogota.sipa.serv.comparendos.validator;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.serv.comparendos.ComparendoDto;
import co.gov.movilidadbogota.sipa.serv.externos.DuppsWebServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

/**
 * Created by Admin on 17/07/2017.
 */
@Component
public class ValidatorUsuarioExisteDuups implements ValidatorField {


    private static final String ERROR_USUARIO_NO_EXISTE_DUUPS = "Usuario no existe en DUUPS.";
    private static final String ERROR_USUARIO_FALLECIDO_DUUPS = "Usuario fallecido en DUUPS.";
    private static final String TIPO_DOCUMENTO_VACIO_O_NULO = "El tipo documento no pueder ser vacio o nulo";


    @Autowired
    private DuppsWebServicesImpl duupsWebsServices;

    @Override
    public void validar(FormatoComparendo formatoComparendo, MapBindingResult errors, ComparendoDto comparendoDto) {
        boolean usuarioExiste = validarDatosEntrada(formatoComparendo.getTipoIdentificacionInfractor(),
                formatoComparendo.getNumeroIdentificacionInfractor(),
                errors);
        if (usuarioExiste && !duupsWebsServices.validaUsuario(formatoComparendo.getTipoIdentificacionInfractor(),
                formatoComparendo.getNumeroIdentificacionInfractor())) {
            errors.rejectValue(
                    "tipoDocumento",
                    "frm.tipoDocumento.invalid",
                    ERROR_USUARIO_NO_EXISTE_DUUPS
            );
            usuarioExiste = false;
        }

        if (usuarioExiste && !duupsWebsServices.validaFallecido(formatoComparendo.getTipoIdentificacionInfractor(),
                formatoComparendo.getNumeroIdentificacionInfractor())) {
            errors.rejectValue(
                    "tipoDocumento",
                    "frm.tipoDocumento.invalid",
                    ERROR_USUARIO_FALLECIDO_DUUPS
            );
        }

    }


    private boolean validarDatosEntrada(String tipoDoc, String numDoc, MapBindingResult errors) {
        boolean datosValidos = false;

        if (tipoDoc != null && numDoc != null) {
            if (!tipoDoc.isEmpty() && !numDoc.isEmpty()) {
                datosValidos = true;
            } else {
                errors.rejectValue(
                        "tipoDocumento",
                        "frm.tipoDocumento.invalid",
                        TIPO_DOCUMENTO_VACIO_O_NULO
                );
            }
        } else {
            errors.rejectValue(
                    "tipoDocumento",
                    "frm.tipoDocumento.invalid",
                    TIPO_DOCUMENTO_VACIO_O_NULO
            );
        }
        return datosValidos;
    }
}
