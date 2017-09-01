package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by diego.gomez on 07/06/2017.
 */
public interface LicenciaRepo extends JpaRepository<Licencia, UUID> {

    /**
     * Metodo que consulta La licencia teniendo en cuenta el numero
     *
     * @return Licencia
     */
    Licencia findOneByNumero(String numero);


    /**
     * Metodo que consulta La licencia teniendo en cuenta el numero de usuario
     *
     * @return Licencia
     */
    Licencia findOneByPersonaId(UUID idUsuario);

    /**
     * Metodo que consulta La licencia teniendo en cuenta el numero de usuario
     *
     * @return Licencia
     */
    Licencia findOneByPersonaIdAndNumero(UUID idUsuario, String numero);

}

