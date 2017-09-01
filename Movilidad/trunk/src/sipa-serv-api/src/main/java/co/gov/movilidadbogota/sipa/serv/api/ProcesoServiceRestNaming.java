package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.gen.Constants;

/**
 * Nomenclatura de los nombres de los recursos REST para el controlador
 */
public interface ProcesoServiceRestNaming {
    String PATH = "/v" + Constants.API_VERSION + "/proc";
    String PATH_DELIVER_MESSAGE = PATH + "/message";
}
