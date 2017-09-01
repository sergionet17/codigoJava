package co.gov.movilidadbogota.sipa.data.gen;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by diego.gomez on 06/07/2017.
 */
@Transactional
public interface MensajeRepo extends JpaRepository<Mensaje, UUID> {

    /**
     * Trae el listado de los mensajes del sistema para el usuario logueado, que el tipo de Notificacion sea por "Sistema (1)" y
     * que aun no ha sido Leido (por defecto lo deja en false)
     *
     * @param usuario
     * @param tipoNotificacion
     * @param leido
     * @return Un listado de mensajes.
     */
    List<Mensaje> findAllByUsuarioDestinoAndTipoNotificacionAndLeido(Usuario usuario, TipoNotificacion tipoNotificacion, Boolean leido);


    /**
     * Trae el listado de los mensajes del sistema para el usuario logueado, que el tipo de Notificacion sea por "Sistema (1)"
     *
     * @param usuario
     * @param tipoNotificacion
     * @return Un listado de mensajes.
     */
    List<Mensaje> findAllByUsuarioDestinoAndTipoNotificacionOrderByFechaCreacionDesc(Usuario usuario, TipoNotificacion tipoNotificacion);


    /**
     * Trae el listado de los mensajes de correo, que el tipo de Notificacion sea por "Correo (2)" y
     * que aun no ha sido Leido (por defecto lo deja en false)
     *
     * @param tipoNotificacion
     * @param leido
     * @return Un listado de mensajes.
     */
    List<Mensaje> findAllByTipoNotificacionAndLeido(TipoNotificacion tipoNotificacion, Boolean leido);

    /**
     * Trae el listado de los mensajes del sistema para el usuario logueado, que el tipo de Notificacion sea por "Sistema (1)"
     *
     * @param tipoNotificacion
     * @return Un listado de mensajes.
     */
    List<Mensaje> findAllByTipoNotificacionIdOrderByFechaCreacionDesc(Integer tipoNotificacion);

}
