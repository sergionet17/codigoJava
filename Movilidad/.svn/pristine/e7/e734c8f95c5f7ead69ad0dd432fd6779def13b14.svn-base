package co.gov.movilidadbogota.sipa.serv.comparendos.extractor;

import co.gov.movilidadbogota.sipa.data.biz.num.AnulacionNumeracionRepo;
import co.gov.movilidadbogota.sipa.serv.api.Command;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by mcr on 13/08/17.
 */
@Component
public class ResolucionAnulacionRangoExtractor implements Command {

    @Autowired
    AnulacionNumeracionRepo anulacionNumeracionRepo;

    @Override
    public void execute(CommandContext ctx) throws Exception {
        ctx.put("anulacion", anulacionNumeracionRepo.findOne(
                UUID.fromString((String) ctx.get("idAnulacionNumeracion"))));
    }
}
