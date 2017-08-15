package co.gov.movilidadbogota.sipa.serv.bpm;

import co.gov.movilidadbogota.sipa.data.biz.comp.AudienciaComparendo;
import co.gov.movilidadbogota.sipa.serv.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementación de los servicios de audiencia
 */
@Service
public class AudienciaServiceImpl implements AudienciaService {

    @Autowired
    ProcesoService procesoService;

    @Override
    public AudienciaComparendo aperturaAudiencia(AudienciaComparendo audienciaComparendo) throws AudienciaException {
        try {
            Map<String, ProcessVariableValue> map = new HashMap<>();
            map.put("comparendoId", new ProcessVariableValue(audienciaComparendo.getComparendo().getId().toString()));
            map.put("tipoAudienciaComparendo", new ProcessVariableValue(audienciaComparendo.getTipo().getId().toString()));
            procesoService.deliverMessage("inicio-audiencia", map);
            return audienciaComparendo;
        } catch (ProcesoException e) {
            throw new AudienciaException("Apertura de audiencia falló", e);
        }
    }
}
