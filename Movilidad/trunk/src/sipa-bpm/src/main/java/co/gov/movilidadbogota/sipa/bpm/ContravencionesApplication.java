package co.gov.movilidadbogota.sipa.bpm;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ProcessApplication("Contravenciones")
public class ContravencionesApplication extends ServletProcessApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContravencionesApplication.class);

    public ContravencionesApplication() {
        LOGGER.info("ContravencionesApplication started");
    }
}