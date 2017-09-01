package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.DocumentoRepo;
import co.gov.movilidadbogota.sipa.data.fin.VolantePago;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import co.gov.movilidadbogota.sipa.serv.api.VolantePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VolantePagoServiceIml implements VolantePagoService {

    @Autowired
    ComparendoRepository comparendoRepository;

    @Autowired
    DocumentoService documentoService;

    @Autowired
    DocumentoRepo documentoRepo;

    @Override
    public Documento generarVolantePago(UUID comparendoId) throws Exception {
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        VolantePago volantePago = new VolantePago();
        CommandContext ctxResultado = documentoService.generarDocumento("volante-pago", new CommandContext().put("idComparendo", comparendoId).put("idExpediente",comparendoId.toString()));
        String idDocumento = (String) ctxResultado.get("idDocumento");
        Documento documento = documentoRepo.findOne(UUID.fromString(idDocumento));
        return documento;
    }
}
