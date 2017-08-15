package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;

import java.util.Date;
import java.util.Map;

/**
 * Created by Admin on 25/07/2017.
 */
public interface AlarmaStrategy<TID> {

    <TID> Map<TID, Date> findDateMap();

    MensajeAlarma getMessage(TID id);

    static class MensajeAlarma {

        private Usuario destinatario;

        private String mensaje;

        public Usuario getDestinatario() {
            return destinatario;
        }

        public void setDestinatario(Usuario destinatario) {
            this.destinatario = destinatario;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

    }
}
