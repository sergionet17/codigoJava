package co.gov.movilidadbogota.bpm;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.ComparendoException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IniciarProcesoCobroPersuasivoDelegate implements JavaDelegate {

    @Autowired
    SipaServicesClient sipaServicesClient;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String idTituloEjecutivo = (String) execution.getVariable("idTituloEjecutivo");

        if (idTituloEjecutivo.isEmpty()) {
            throw new ComparendoException("La variable idComparendo se encuentra vacía");
        }

        CommandContext ret = sipaServicesClient.getTitulosEjecutivos().iniciarProcesoCobroPersuasivo(
                UUID.fromString(idTituloEjecutivo),
                CommandContextUtils.from(execution)
        );

        CommandContextUtils.to(ret, execution);
    }
}
