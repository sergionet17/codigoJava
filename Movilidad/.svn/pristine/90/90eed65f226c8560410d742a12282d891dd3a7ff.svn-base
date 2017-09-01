package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirma;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaHistorico;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaHistoricoRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Programa la verificación de la vigencia en los turno de firma para cierre.
 *
 * @author maria.rodriguez
 */
@Component
public class TurnoFirmaCron extends BaseController {

    @Autowired
    TurnoFirmaRepo turnoFirmaRepo;
    @Autowired
    TurnoFirmaHistoricoRepo turnoFirmaHistoricoRepo;

    /**
     * Especifica el tiempo en el que se debe cerrar las vigencias de los turnos de firma de las autoridades.
     * Luego de cerrarlas, pasa las vigencias vencidad a la tabla de histórico.
     */
    //@Scheduled(cron = "*/5 * * * * *")
    //Todos los días a las 12 de la noche.
    @Scheduled(cron = "0 0 0 * * *")
    public void cerrarVigencia() {
        List<TurnoFirma> turnosFirma = turnoFirmaRepo.findAll();
        //Marca los objetos encontrados en el stream como "cerrados"
        //Los elimina de la tabla turnos firma y los pasa a la tabla de histórico de turnos para consulta.
        System.out.println("Cerrando las vigencias por vencimiento");
        turnosFirma.stream().filter(tf -> tf.getCerrado() == true)
                .forEach(tf -> {
                    TurnoFirmaHistorico tfh = new TurnoFirmaHistorico();
                    tfh.setId(UUID.randomUUID());
                    tfh.setAutoridad(tf.getAutoridad());
                    tfh.setDesde(tf.getDesde());
                    tfh.setHasta(tf.getHasta());
                    tfh.setCerrado(tf.getCerrado());
                    tfh.setSuplenteOficial(tf.getSuplenteOficial());
                    turnoFirmaHistoricoRepo.save(tfh);
                    //Se elimina el registro del turno que acaba de cer cerrado.
                    TurnoFirma toRemove = turnoFirmaRepo.findOne(tf.getId());
                    turnoFirmaRepo.delete(toRemove);
                });
    }
}
