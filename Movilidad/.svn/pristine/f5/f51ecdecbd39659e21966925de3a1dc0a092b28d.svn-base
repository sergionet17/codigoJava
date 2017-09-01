package co.gov.movilidadbogota.sipa.serv.comparendos.extractor;

import co.gov.movilidadbogota.sipa.data.biz.comp.AudienciaComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaRepo;
import co.gov.movilidadbogota.sipa.serv.api.Command;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Se encarga de pasar los datos del comparendo al contexto que requiere el proceso que inicia la resolución de reincidencia.
 *
 * @author maria.rodriguez
 */
@Component
public class ResolucionReincidenciaExtractor implements Command {

    @Autowired
    ComparendoRepository comparendoRepository;
    @Autowired
    TurnoFirmaRepo turnoFirmaRepo;

    @Override
    public void execute(CommandContext ctx) throws Exception {

        String idComparendo = (String) ctx.get("idComparendo");
        Comparendo comparendo = comparendoRepository.findOne(UUID.fromString(idComparendo));

        /*ctx.put("comparendo", comparendo);
        //Se consulta la autoridad
        Usuario autoridad = turnoFirmaRepo.findByDate(new Date());
        ctx.put("usuario", autoridad);*/
        //ctx.put(ComparendoService.CTX_NUMERO_RESOLUCION_AUTOMATICA_FALLO, "6574523957");
        AudienciaComparendo audiencia = new AudienciaComparendo();
        audiencia.setComparendo(comparendo);
        ctx.put("audiencia", audiencia);

    }
}
