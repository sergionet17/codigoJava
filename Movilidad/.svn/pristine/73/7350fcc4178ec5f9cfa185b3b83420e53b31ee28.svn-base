package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Valida los datos del infractor en el formato de comparendo. Si los
 * datos son consistentes entonces asigna el infractor. Hay que tener
 * en cuenta que si la persona no existe en el sistema entonces la crea
 * junto con sus dependencias.
 */
@Component
public class ValInfractor implements FormatoComparendoValidator {

    @Autowired
    PersonaRepo personaRepo;

    /**
     * Correspondencia entre el tipo de documento de identidad y el tipo de persona
     */
    Map<TipoDocumento, TipoPersona> mapaTipoPersona = ImmutableMap.<TipoDocumento, TipoPersona>builder()
            .put(TipoDocumento.CC, TipoPersona.NATURAL)
            .put(TipoDocumento.CE, TipoPersona.NATURAL)
            .put(TipoDocumento.NIT, TipoPersona.JURIDICA)
            .put(TipoDocumento.NUIP, TipoPersona.NATURAL)
            .put(TipoDocumento.TI, TipoPersona.NATURAL).build();

    @Override
    public boolean validate(FormatoComparendo formato, Comparendo comparendo, FormatoComparendoValidatorErrors errors) {

        boolean ret = true;

        /*
        Verifica el tipo de documento y el número ya que son obligatorios para poder
        localizar a la persona.
         */
        TipoDocumento tipoDocumento = null;
        if (StringUtils.isBlank(formato.getTipoIdentificacionInfractor())) {
            errors.reject(
                    "tipoIdentificacionInfractor",
                    "El tipo de indentificación del infractor es obligatorio");
            ret = false;
        } else {
            tipoDocumento = findTipoDocumentoBySigla(formato.getTipoIdentificacionInfractor());
            if (tipoDocumento == null) {
                errors.reject(
                        "tipoIdentificacionInfractor",
                        "El tipo de indentificación no existe");
                ret = false;
            }
        }
        String numeroDocumento = formato.getNumeroIdentificacionInfractor();
        if (StringUtils.isBlank(numeroDocumento)) {
            errors.reject(
                    "numeroIdentificacionInfractor",
                    "El número de indentificación del infractor es obligatorio");
            ret = false;
        }

        /*
        Busca la persona en el sistema. Si no existe la persona con el tipo de documento y número
        de documento entonces la crea. En cualquier caso asigna la persona como infractor del
        comparendo
         */
        if (tipoDocumento != null && StringUtils.isNotBlank(numeroDocumento)) {
            Persona infractor = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                    tipoDocumento, numeroDocumento
            );
            if (infractor == null) {
                infractor = new Persona(
                        UUID.randomUUID(),
                        tipoDocumento,
                        numeroDocumento,
                        formato.getPrimerNombreInfractor(),
                        formato.getPrimerApellidoInfractor(),
                        resolveTipoPersona(tipoDocumento)
                );
                infractor.setSegundoNombre(formato.getSegundoNombreInfractor());
                infractor.setSegundoApellido(formato.getSegundoApellidoInfractor());
                personaRepo.save(infractor);
                // TODO: Completar con la dirección, teléfono y correo
            }
            comparendo.setInfractor(infractor);
        }

        return ret;
    }

    /**
     * Resuelve el tipo de persona a partir del tipo de documento
     *
     * @param tipoDocumento
     * @return
     */
    private TipoPersona resolveTipoPersona(TipoDocumento tipoDocumento) {
        return mapaTipoPersona.get(tipoDocumento);
    }

    /**
     * Encuentra el tipo de documento por su sigla
     *
     * @param sigla
     * @return
     */
    private TipoDocumento findTipoDocumentoBySigla(String sigla) {
        return TipoDocumento.FULL_SET.parallelStream()
                .filter(x -> x.getSigla().equals(sigla))
                .findAny().orElse(null);
    }
}
