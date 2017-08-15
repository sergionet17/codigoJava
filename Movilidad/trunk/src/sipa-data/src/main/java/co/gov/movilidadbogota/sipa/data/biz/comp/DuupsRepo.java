package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.jws.WebService;

/**
 * Representa el repositorio de la base de datos de DUUPS
 *
 * @Author Marcel.Bohorquez
 */
@WebService
public interface DuupsRepo {
    boolean validaUsuario(String tipoDoc, String numDoc);

    boolean validaFallecido(String tipoDoc, String numDoc);

}
