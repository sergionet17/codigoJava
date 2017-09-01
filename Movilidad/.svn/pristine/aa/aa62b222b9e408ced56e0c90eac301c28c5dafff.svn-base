package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by maria on 7/28/17.
 */
@Service
@Component
public class ReincidenciaServiceImpl implements ReincidenciaService {

    private static final Logger LOG = LoggerFactory.getLogger(Comparendo.class);

    @Autowired
    PersonaRepo personaRepo;
    @Autowired
    ComparendoRepository comparendoRepository;

    @Override
    @Scheduled(cron = "0 0 5 * * *")
    public void identificarReincidencia() {


    }
}
