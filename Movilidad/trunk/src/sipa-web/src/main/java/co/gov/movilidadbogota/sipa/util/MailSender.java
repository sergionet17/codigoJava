package co.gov.movilidadbogota.sipa.util;

import com.sun.mail.smtp.SMTPTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * Created by Diego Gomez on 11/07/2017.
 */
public class MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);


    /**
     * Directorio de plantillas
     */
    private static final String FOLDER_TEMPLATES = "templates/correo";

    /**
     * Conforma las direcciones destino del mensaje como objetos
     * InternetAddress.
     *
     * @param recipients
     * @return Address[] direcciones destino del mensaje.
     * @throws AddressException
     */
    private static Address[] getAddresses(List<String> recipients) throws AddressException {
        int i = 0;
        Address[] addresses = new Address[recipients.size()];
        for (String address : recipients) {
            addresses[i] = new InternetAddress(address);
            i++;
        }
        return addresses;
    }

    /**
     * Envía un mensaje de correo de acuerdo a la configuración especificada
     * en la configuracion
     *
     * @param mensaje datos del mensaje.
     * @return true/false para mensaje enviado.
     */
    public boolean sendMail(MensajeDto mensaje) {

        String smtpHost = "correo.ingenian.gov.co";
        String smtpPort = "25";
        String senderAddress = "diego.gomez@ingenian.com";
        String user = "";
        String password = "";
        boolean auth = false;


        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", smtpHost);
            properties.put("mail.smtp.port", smtpPort);

            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setHeader("text/html", "charset=iso-8859-1");
            message.setFrom(new InternetAddress(senderAddress));
            message.setRecipients(Message.RecipientType.TO, getAddresses(mensaje.getDestinos()));
            message.setSubject(mensaje.getAsunto());
            message.setSentDate(new Date());

            // Cuerpo
            String contenido = VelocityParser.getInstance().getContenidoPorPlantilla(mensaje.getPlantilla());
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent("<img src=\"cid:image\">" + contenido, "text/html; charset=iso-8859-1");

            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(mbp);

            // Image
            MimeBodyPart mbpImage = new MimeBodyPart();
            mbpImage.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(mbpImage);
            message.setContent(multipart);

            // Envía el mensaje
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            try {
                if (auth) {
                    t.connect(user, password);
                } else {
                    t.connect();
                }
                t.sendMessage(message, message.getAllRecipients());
                return true;

            } finally {
                t.close();
            }
        } catch (Exception ex) {
            LOGGER.info("Error enviando email = " + ex);
            ex.printStackTrace();
            return false;
        }
    }


}
