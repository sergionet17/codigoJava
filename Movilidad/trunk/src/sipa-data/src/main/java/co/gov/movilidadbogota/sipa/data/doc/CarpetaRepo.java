package co.gov.movilidadbogota.sipa.data.doc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by paola.charry on 07/06/2017.
 */
public interface CarpetaRepo extends JpaRepository<Carpeta, UUID> {

    Carpeta findByReferencia(String referencia);

}

