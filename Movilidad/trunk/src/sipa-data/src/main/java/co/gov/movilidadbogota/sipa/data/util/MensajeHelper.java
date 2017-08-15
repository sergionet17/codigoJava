package co.gov.movilidadbogota.sipa.data.util;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.gen.Mensaje;
import co.gov.movilidadbogota.sipa.data.gen.MensajeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class MensajeHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MensajeHelper.class);

    @Autowired
    MensajeRepo mensajeRepo;

    /**
     * Crea un mensaje para ser notificado entre usuarios
     *
     * @param destinatario
     * @param remitente
     * @param texto
     * @param tiposNotificacion
     */
    public void enviarMensaje(
            Usuario destinatario,
            Usuario remitente,
            String texto,
            TipoNotificacion... tiposNotificacion
    ) {
        Stream.of(tiposNotificacion)
                .map(x -> new Mensaje(remitente, destinatario, texto, x))
                .forEach(x -> {
                    LOGGER.info(String.format("Nuevo mensaje de sistema: %s", x));
                    mensajeRepo.save(x);
                });
    }

    /**
     * Envía un mensaje para ser notificado al destinatario, proveniente del usuario  SISTEMA
     *
     * @param destinatario
     * @param texto
     * @param tiposNotificacion
     */
    public void enviarMensaje(
            Usuario destinatario,
            String texto,
            TipoNotificacion... tiposNotificacion
    ) {
        enviarMensaje(destinatario, Usuario.SISTEMA, texto, tiposNotificacion);
    }

    /**
     * Envía un mensaje para ser notificado a los destinatarios, proveniente del usuario  SISTEMA
     *
     * @param destinatarios
     * @param texto
     * @param tiposNotificacion
     */
    public void enviarMensaje(
            Collection<Usuario> destinatarios,
            String texto,
            TipoNotificacion... tiposNotificacion
    ) {
        enviarMensaje(destinatarios, Usuario.SISTEMA, texto, tiposNotificacion);
    }

    /**
     * Crea un mensaje para ser notificado entre usuarios
     *
     * @param destinatarios
     * @param remitente
     * @param texto
     * @param tiposNotificacion
     */
    public void enviarMensaje(
            Collection<Usuario> destinatarios,
            Usuario remitente,
            String texto,
            TipoNotificacion... tiposNotificacion
    ) {
        for (Usuario destinatario : destinatarios) {
            enviarMensaje(destinatario, remitente, texto, tiposNotificacion);
        }
    }

}
