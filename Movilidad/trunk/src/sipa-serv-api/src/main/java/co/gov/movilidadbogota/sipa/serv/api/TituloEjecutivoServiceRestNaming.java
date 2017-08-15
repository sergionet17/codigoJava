package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.gen.Constants;

public interface TituloEjecutivoServiceRestNaming {

    String PATH = "/v" + Constants.API_VERSION + "/titulo-ejecutivo";
    String PATH_REGISTRAR = PATH + "/{comparendoId}/registrar";
    String PATH_INICIAR_COBRO_PERSUASIVO = PATH + "/{tituloEjecutivoId}/iniciar-cobro-persuasivo";

    String VAR_COMPARENDO_ID = "comparendoId";
    String VAR_TITULO_EJECUTIVO_ID = "tituloEjecutivoId";

}
