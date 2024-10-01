package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.CorreosDTO;
import cr.ac.una.chatandmailapi.model.Parametros;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class EmailService {

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());
    
    public String enviarCorreo(String destinatario, String asunto, String mensajeTexto) {
        try {
   
            Parametros parametros = obtenerParametrosCorreo();

            Properties props = new Properties();
            props.put("mail.smtp.host", parametros.getParServer());
            props.put("mail.smtp.port", parametros.getParPuerto());
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");



            String correoRemitente = parametros.getParCorreo(); // Correo remitente
            String passwordRemitente = parametros.getParClave(); // Contraseña 

            // Crear la sesión de correo
            Session session = Session.getInstance(props);
            session.setDebug(true);

            // Crear el mensaje de correo
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(correoRemitente)); // Establecer remitente
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); // Establecer destinatario
            mensaje.setSubject(asunto); // Asunto del correo
            mensaje.setText(mensajeTexto, "UTF-8", "html"); // Cuerpo del correo en formato HTML

            // Enviar el correo
            Transport transport = session.getTransport("smtp");
            transport.connect(correoRemitente, passwordRemitente); // Autenticarse
            transport.sendMessage(mensaje, mensaje.getAllRecipients()); // Enviar mensaje
            transport.close(); // Cerrar la conexión

            return "Correo enviado exitosamente.";
        } catch (MessagingException e) {
            return "Error enviando el correo: " + e.getMessage();
        }
    }


    private Parametros obtenerParametrosCorreo() {
        return em.find(Parametros.class, 1L); 
    }
    
//No se utiliza
    public String enviarCorreoConEspera(String destinatario, String asunto, String mensajeTexto) {
        try {
       
            Parametros parametros = obtenerParametrosCorreo();

   
            long tiempoDeEspera = parametros.getParTimeout(); 

            // Esperar el tiempo configurado antes de enviar el correo
            Thread.sleep(tiempoDeEspera);

            Properties props = new Properties();
            props.put("mail.smtp.host", parametros.getParServer());
            props.put("mail.smtp.port", parametros.getParPuerto());
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            String correoRemitente = parametros.getParCorreo(); 
            String passwordRemitente = parametros.getParClave(); 

       
            Session session = Session.getInstance(props);
            session.setDebug(true);  


            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(correoRemitente)); 
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); // Establecer destinatario
            mensaje.setSubject(asunto); // Asunto del correo
            mensaje.setText(mensajeTexto, "UTF-8", "html"); 

     
            Transport transport = session.getTransport("smtp");
            transport.connect(correoRemitente, passwordRemitente);
            transport.sendMessage(mensaje, mensaje.getAllRecipients()); 
            transport.close();

            return "Correo enviado exitosamente después de " + tiempoDeEspera + " ms.";
        } catch (MessagingException e) {
            return "Error enviando el correo: " + e.getMessage();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            return "Error: La espera fue interrumpida.";
        }
    }
    
    
    public String enviarCorreos(List<CorreosDTO> correosList) {
        Parametros parametros = obtenerParametrosCorreo();

        Properties props = new Properties();
        props.put("mail.smtp.host", parametros.getParServer());
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        String correoRemitente = parametros.getParCorreo();
        String passwordRemitente = parametros.getParClave();

        Session session = Session.getInstance(props);
        session.setDebug(true); 

        for (CorreosDTO correoDto : correosList) {
            try {
                MimeMessage mensaje = new MimeMessage(session);
                mensaje.setFrom(new InternetAddress(correoRemitente));
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDto.getCorDestinatario()));
                mensaje.setSubject(correoDto.getCorAsunto());
                mensaje.setText(correoDto.getCorResultado(), "UTF-8", "html");
                
  
                Transport transport = session.getTransport("smtp");
                transport.connect(correoRemitente, passwordRemitente);
                transport.sendMessage(mensaje, mensaje.getAllRecipients());
                transport.close();
                
                correoDto.setCorEstado("E");
            } catch (MessagingException e) {
                LOG.severe("Error al enviar el correo a: " + correoDto.getCorDestinatario() + " - " + e.getMessage());
                correoDto.setCorEstado("P"); 
            }
        }
        return "Todos los correos fueron procesados. Resultados:\n" +
                correosList.stream()
                        .map(c -> "Correo a: " + c.getCorDestinatario() + " - Estado: " + c.getCorEstado())
                        .collect(Collectors.joining("\n"));
    }
    
    

}

