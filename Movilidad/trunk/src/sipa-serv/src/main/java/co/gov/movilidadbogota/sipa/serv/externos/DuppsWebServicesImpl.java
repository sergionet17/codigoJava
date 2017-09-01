package co.gov.movilidadbogota.sipa.serv.externos;

import co.gov.movilidadbogota.sipa.data.biz.comp.DuupsRepo;

/**
 * Created by Admin on 27/06/2017.
 */
public class DuppsWebServicesImpl implements DuupsRepo {
    @Override
    public boolean validaUsuario(String tipoDoc, String numDoc) {
        return true;
    }

    @Override
    public boolean validaFallecido(String tipoDoc, String numDoc) {
        return true;
    }
}
